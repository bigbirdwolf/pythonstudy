package com.yltx.oil.partner.oss;

import android.util.Log;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.utils.IOUtils;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.ListPartsRequest;
import com.alibaba.sdk.android.oss.model.ListPartsResult;
import com.alibaba.sdk.android.oss.model.PartETag;
import com.alibaba.sdk.android.oss.model.PartSummary;
import com.alibaba.sdk.android.oss.model.UploadPartRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class PauseableUploadTask {
    private OSSCompletedCallback<PauseableUploadRequest, PauseableUploadResult> callback;
    private OSS oss;
    private PauseableUploadRequest request;
    private List<PartETag> partETags = new ArrayList();
    private long currentUploadLength = 0;
    private long fileLength = 0;
    private boolean isPaused = false;
    private boolean isComplete = false;

    public PauseableUploadTask(OSS oss, PauseableUploadRequest pauseableUploadRequest, OSSCompletedCallback<PauseableUploadRequest, PauseableUploadResult> oSSCompletedCallback) {
        this.oss = oss;
        this.request = pauseableUploadRequest;
        this.callback = oSSCompletedCallback;
    }

    public synchronized void pause() {
        this.isPaused = true;
    }

    public synchronized boolean isPause() {
        return this.isPaused;
    }

    public synchronized void setComplete() {
        this.isComplete = true;
    }

    public synchronized boolean isComplete() {
        return this.isComplete;
    }

    public void upload(String str) throws ClientException, ServiceException, IOException {
        CompleteMultipartUploadResult completeMultipartUpload;
        String bucket = this.request.getBucket();
        String objectKey = this.request.getObjectKey();
        String localFile = this.request.getLocalFile();
        int partSize = this.request.getPartSize();
        try {
            ListPartsResult listParts = this.oss.listParts(new ListPartsRequest(bucket, objectKey, str));
            Log.d("ListPartsFound", String.valueOf(listParts.getParts().size()));
            for (PartSummary partSummary : listParts.getParts()) {
                this.partETags.add(new PartETag(partSummary.getPartNumber(), partSummary.getETag()));
            }
            long j = partSize;
            int size = this.partETags.size() + 1;
            File file = new File(localFile);
            this.fileLength = file.length();
            final OSSProgressCallback<PauseableUploadRequest> progressCallback = this.request.getProgressCallback();
            long j2 = 0;
            int i = ((int) (this.fileLength / j)) + (this.fileLength % j == 0 ? 0 : 1);
            if (size <= i) {
                this.currentUploadLength = (size - 1) * j;
            } else {
                this.currentUploadLength = this.fileLength;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            while (j2 < this.currentUploadLength) {
                long skip = fileInputStream.skip(this.currentUploadLength - j2);
                if (skip == -1) {
                    throw new IOException("Skip onFailed! [fileLength]: " + this.fileLength + " [needSkip]: " + this.currentUploadLength);
                }
                j2 += skip;
            }
            while (size <= i) {
                UploadPartRequest uploadPartRequest = new UploadPartRequest(bucket, objectKey, str, size);
                uploadPartRequest.setProgressCallback(new OSSProgressCallback<UploadPartRequest>() { // from class: com.yltx.oil.partner.oss.PauseableUploadTask.1
                    @Override // com.alibaba.sdk.android.oss.callback.OSSProgressCallback
                    public void onProgress(UploadPartRequest uploadPartRequest2, long j3, long j4) {
                        if (progressCallback != null) {
                            progressCallback.onProgress(PauseableUploadTask.this.request, PauseableUploadTask.this.currentUploadLength + j3, PauseableUploadTask.this.fileLength);
                        }
                    }
                });
                int min = (int) Math.min(j, this.fileLength - this.currentUploadLength);
                uploadPartRequest.setPartContent(IOUtils.readStreamAsBytesArray(fileInputStream, min));
                this.partETags.add(new PartETag(size, this.oss.uploadPart(uploadPartRequest).getETag()));
                long j3 = j;
                this.currentUploadLength += min;
                size++;
                Log.d("UploadPartIndex", String.valueOf(size - 1));
                Log.d("UploadPartSize", String.valueOf(this.currentUploadLength));
                if (isPause()) {
                    Log.w("MultiPartUpload", "Pause");
                    Log.w("UploadId", str);
                    return;
                }
                j = j3;
            }
            PauseableUploadResult pauseableUploadResult = new PauseableUploadResult(this.oss.completeMultipartUpload(new CompleteMultipartUploadRequest(bucket, objectKey, str, this.partETags)));
            setComplete();
            Log.d("multipartUpload", "multipart upload success! Location: " + completeMultipartUpload.getLocation());
            this.callback.onSuccess(this.request, pauseableUploadResult);
        } catch (ClientException e) {
            this.callback.onFailure(this.request, e, null);
            throw e;
        } catch (ServiceException e2) {
            Log.e("ErrorCode", e2.getErrorCode());
            Log.e("RequestId", e2.getRequestId());
            Log.e("HostId", e2.getHostId());
            Log.e("RawMessage", e2.getRawMessage());
            this.callback.onFailure(this.request, null, e2);
            throw e2;
        } catch (IOException e3) {
            this.callback.onFailure(this.request, new ClientException(e3.toString(), e3), null);
            throw e3;
        }
    }

    public String initUpload() throws ClientException, ServiceException {
        try {
            String localFile = this.request.getLocalFile();
            String bucket = this.request.getBucket();
            String objectKey = this.request.getObjectKey();
            Log.d("InitUpload", localFile);
            return this.oss.initMultipartUpload(new InitiateMultipartUploadRequest(bucket, objectKey)).getUploadId();
        } catch (ClientException e) {
            this.callback.onFailure(this.request, e, null);
            throw e;
        } catch (ServiceException e2) {
            Log.e("ErrorCode", e2.getErrorCode());
            Log.e("RequestId", e2.getRequestId());
            Log.e("HostId", e2.getHostId());
            Log.e("RawMessage", e2.getRawMessage());
            this.callback.onFailure(this.request, null, e2);
            throw e2;
        }
    }
}