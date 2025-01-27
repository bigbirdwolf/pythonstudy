package com.alibaba.sdk.android.oss.internal;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.utils.BinaryUtil;
import com.alibaba.sdk.android.oss.common.utils.IOUtils;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.HeadObjectRequest;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.ListPartsRequest;
import com.alibaba.sdk.android.oss.model.PartETag;
import com.alibaba.sdk.android.oss.model.PartSummary;
import com.alibaba.sdk.android.oss.model.ResumableUploadRequest;
import com.alibaba.sdk.android.oss.model.ResumableUploadResult;
import com.alibaba.sdk.android.oss.model.UploadPartRequest;
import com.alibaba.sdk.android.oss.network.ExecutionContext;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class ExtensionRequestOperation {
    private static final ExecutorService executor = Executors.newFixedThreadPool(3);
    private InternalRequestOperation apiOperation;

    public ExtensionRequestOperation(InternalRequestOperation internalRequestOperation) {
        this.apiOperation = internalRequestOperation;
    }

    public boolean doesObjectExist(String str, String str2) throws ClientException, ServiceException {
        try {
            this.apiOperation.headObject(new HeadObjectRequest(str, str2), null).getResult();
            return true;
        } catch (ServiceException e) {
            if (e.getStatusCode() == 404) {
                return false;
            }
            throw e;
        }
    }

    public OSSAsyncTask<ResumableUploadResult> resumableUpload(ResumableUploadRequest resumableUploadRequest, OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult> oSSCompletedCallback) {
        ExecutionContext executionContext = new ExecutionContext(this.apiOperation.getInnerClient(), resumableUploadRequest);
        return OSSAsyncTask.wrapRequestTask(executor.submit(new ResumableUploadTask(resumableUploadRequest, oSSCompletedCallback, executionContext)), executionContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ResumableUploadTask implements Callable<ResumableUploadResult> {
        private OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult> completedCallback;
        private ExecutionContext context;
        private long currentUploadLength;
        private long fileLength;
        private List<PartETag> partETags = new ArrayList();
        private File recordFile;
        private ResumableUploadRequest request;
        private String uploadId;

        public ResumableUploadTask(ResumableUploadRequest resumableUploadRequest, OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult> oSSCompletedCallback, ExecutionContext executionContext) {
            this.request = resumableUploadRequest;
            this.completedCallback = oSSCompletedCallback;
            this.context = executionContext;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public ResumableUploadResult call() throws Exception {
            try {
                initUploadId();
                ResumableUploadResult doMultipartUpload = doMultipartUpload();
                if (this.completedCallback != null) {
                    this.completedCallback.onSuccess(this.request, doMultipartUpload);
                }
                return doMultipartUpload;
            } catch (ClientException e) {
                if (this.completedCallback != null) {
                    this.completedCallback.onFailure(this.request, e, null);
                }
                throw e;
            } catch (ServiceException e2) {
                if (this.completedCallback != null) {
                    this.completedCallback.onFailure(this.request, null, e2);
                }
                throw e2;
            } catch (IOException e3) {
                ClientException clientException = new ClientException(e3.toString(), e3);
                if (this.completedCallback != null) {
                    this.completedCallback.onFailure(this.request, clientException, null);
                }
                throw clientException;
            }
        }

        private void initUploadId() throws IOException, ServiceException, ClientException {
            String uploadFilePath = this.request.getUploadFilePath();
            if (this.request.getRecordDirectory() != null) {
                String calculateMd5Str = BinaryUtil.calculateMd5Str(uploadFilePath);
                String calculateMd5Str2 = BinaryUtil.calculateMd5Str((calculateMd5Str + this.request.getBucketName() + this.request.getObjectKey() + String.valueOf(this.request.getPartSize())).getBytes());
                StringBuilder sb = new StringBuilder();
                sb.append(this.request.getRecordDirectory());
                sb.append("/");
                sb.append(calculateMd5Str2);
                this.recordFile = new File(sb.toString());
                if (this.recordFile.exists()) {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(this.recordFile));
                    this.uploadId = bufferedReader.readLine();
                    bufferedReader.close();
                    OSSLog.logD("[initUploadId] - Found record file, uploadid: " + this.uploadId);
                    try {
                        for (PartSummary partSummary : ExtensionRequestOperation.this.apiOperation.listParts(new ListPartsRequest(this.request.getBucketName(), this.request.getObjectKey(), this.uploadId), null).getResult().getParts()) {
                            this.partETags.add(new PartETag(partSummary.getPartNumber(), partSummary.getETag()));
                        }
                        return;
                    } catch (ClientException e) {
                        throw e;
                    } catch (ServiceException e2) {
                        if (e2.getStatusCode() == 404) {
                            this.uploadId = null;
                        } else {
                            throw e2;
                        }
                    }
                }
                if (!this.recordFile.exists() && !this.recordFile.createNewFile()) {
                    throw new ClientException("Can't create file at path: " + this.recordFile.getAbsolutePath() + "\nPlease make sure the directory exist!");
                }
            }
            this.uploadId = ExtensionRequestOperation.this.apiOperation.initMultipartUpload(new InitiateMultipartUploadRequest(this.request.getBucketName(), this.request.getObjectKey(), this.request.getMetadata()), null).getResult().getUploadId();
            if (this.recordFile != null) {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.recordFile));
                bufferedWriter.write(this.uploadId);
                bufferedWriter.close();
            }
        }

        private ResumableUploadResult doMultipartUpload() throws IOException, ClientException, ServiceException {
            if (this.context.getCancellationHandler().isCancelled()) {
                abortThisResumableUpload();
                if (this.recordFile != null) {
                    this.recordFile.delete();
                }
                throwOutInterruptClientException();
            }
            long partSize = this.request.getPartSize();
            int size = this.partETags.size() + 1;
            File file = new File(this.request.getUploadFilePath());
            this.fileLength = file.length();
            final OSSProgressCallback<ResumableUploadRequest> progressCallback = this.request.getProgressCallback();
            long j = 0;
            int i = ((int) (this.fileLength / partSize)) + (this.fileLength % partSize == 0 ? 0 : 1);
            if (size <= i) {
                this.currentUploadLength = (size - 1) * partSize;
            } else {
                this.currentUploadLength = this.fileLength;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            while (j < this.currentUploadLength) {
                long skip = fileInputStream.skip(this.currentUploadLength - j);
                if (skip == -1) {
                    throw new IOException("Skip failed! [fileLength]: " + this.fileLength + " [needSkip]: " + this.currentUploadLength);
                }
                j += skip;
            }
            while (size <= i) {
                UploadPartRequest uploadPartRequest = new UploadPartRequest(this.request.getBucketName(), this.request.getObjectKey(), this.uploadId, size);
                uploadPartRequest.setProgressCallback(new OSSProgressCallback<UploadPartRequest>() { // from class: com.alibaba.sdk.android.oss.internal.ExtensionRequestOperation.ResumableUploadTask.1
                    @Override // com.alibaba.sdk.android.oss.callback.OSSProgressCallback
                    public void onProgress(UploadPartRequest uploadPartRequest2, long j2, long j3) {
                        if (progressCallback != null) {
                            progressCallback.onProgress(ResumableUploadTask.this.request, ResumableUploadTask.this.currentUploadLength + j2, ResumableUploadTask.this.fileLength);
                        }
                    }
                });
                int min = (int) Math.min(partSize, this.fileLength - this.currentUploadLength);
                byte[] readStreamAsBytesArray = IOUtils.readStreamAsBytesArray(fileInputStream, min);
                uploadPartRequest.setPartContent(readStreamAsBytesArray);
                uploadPartRequest.setMd5Digest(BinaryUtil.calculateBase64Md5(readStreamAsBytesArray));
                this.partETags.add(new PartETag(size, ExtensionRequestOperation.this.apiOperation.uploadPart(uploadPartRequest, null).getResult().getETag()));
                this.currentUploadLength += min;
                size++;
                if (this.context.getCancellationHandler().isCancelled()) {
                    abortThisResumableUpload();
                    if (this.recordFile != null) {
                        this.recordFile.delete();
                    }
                    throwOutInterruptClientException();
                }
            }
            CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest(this.request.getBucketName(), this.request.getObjectKey(), this.uploadId, this.partETags);
            if (this.request.getCallbackParam() != null) {
                completeMultipartUploadRequest.setCallbackParam(this.request.getCallbackParam());
            }
            if (this.request.getCallbackVars() != null) {
                completeMultipartUploadRequest.setCallbackVars(this.request.getCallbackVars());
            }
            CompleteMultipartUploadResult result = ExtensionRequestOperation.this.apiOperation.completeMultipartUpload(completeMultipartUploadRequest, null).getResult();
            if (this.recordFile != null) {
                this.recordFile.delete();
            }
            return new ResumableUploadResult(result);
        }

        private void abortThisResumableUpload() {
            if (this.uploadId != null) {
                ExtensionRequestOperation.this.apiOperation.abortMultipartUpload(new AbortMultipartUploadRequest(this.request.getBucketName(), this.request.getObjectKey(), this.uploadId), null).waitUntilFinished();
            }
        }

        private void throwOutInterruptClientException() throws ClientException {
            IOException iOException = new IOException();
            throw new ClientException(iOException.getMessage(), iOException);
        }
    }
}