package com.alibaba.sdk.android.oss;

import android.content.Context;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.internal.ExtensionRequestOperation;
import com.alibaba.sdk.android.oss.internal.InternalRequestOperation;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.internal.ObjectURLPresigner;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.AppendObjectRequest;
import com.alibaba.sdk.android.oss.model.AppendObjectResult;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.CopyObjectRequest;
import com.alibaba.sdk.android.oss.model.CopyObjectResult;
import com.alibaba.sdk.android.oss.model.CreateBucketRequest;
import com.alibaba.sdk.android.oss.model.CreateBucketResult;
import com.alibaba.sdk.android.oss.model.DeleteBucketRequest;
import com.alibaba.sdk.android.oss.model.DeleteBucketResult;
import com.alibaba.sdk.android.oss.model.DeleteObjectRequest;
import com.alibaba.sdk.android.oss.model.DeleteObjectResult;
import com.alibaba.sdk.android.oss.model.GetBucketACLRequest;
import com.alibaba.sdk.android.oss.model.GetBucketACLResult;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.HeadObjectRequest;
import com.alibaba.sdk.android.oss.model.HeadObjectResult;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.ListObjectsRequest;
import com.alibaba.sdk.android.oss.model.ListObjectsResult;
import com.alibaba.sdk.android.oss.model.ListPartsRequest;
import com.alibaba.sdk.android.oss.model.ListPartsResult;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.alibaba.sdk.android.oss.model.ResumableUploadRequest;
import com.alibaba.sdk.android.oss.model.ResumableUploadResult;
import com.alibaba.sdk.android.oss.model.UploadPartRequest;
import com.alibaba.sdk.android.oss.model.UploadPartResult;
import java.net.URI;
import java.net.URISyntaxException;

/* loaded from: classes.dex */
public class OSSClient implements OSS {
    private OSSCredentialProvider credentialProvider;
    private URI endpointURI;
    private ExtensionRequestOperation extensionRequestOperation;
    private InternalRequestOperation internalRequestOperation;

    public OSSClient(Context context, String str, OSSCredentialProvider oSSCredentialProvider) {
        this(context, str, oSSCredentialProvider, null);
    }

    public OSSClient(Context context, String str, OSSCredentialProvider oSSCredentialProvider, ClientConfiguration clientConfiguration) {
        try {
            String trim = str.trim();
            if (!trim.startsWith("http")) {
                trim = "http://" + trim;
            }
            this.endpointURI = new URI(trim);
            if (oSSCredentialProvider == null) {
                throw new IllegalArgumentException("CredentialProvider can't be null.");
            }
            this.credentialProvider = oSSCredentialProvider;
            this.internalRequestOperation = new InternalRequestOperation(context, this.endpointURI, oSSCredentialProvider, clientConfiguration);
            this.extensionRequestOperation = new ExtensionRequestOperation(this.internalRequestOperation);
        } catch (URISyntaxException unused) {
            throw new IllegalArgumentException("Endpoint must be a string like 'http://oss-cn-****.aliyuncs.com',or your cname like 'http://image.cnamedomain.com'!");
        }
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public OSSAsyncTask<CreateBucketResult> asyncCreateBucket(CreateBucketRequest createBucketRequest, OSSCompletedCallback<CreateBucketRequest, CreateBucketResult> oSSCompletedCallback) {
        return this.internalRequestOperation.createBucket(createBucketRequest, oSSCompletedCallback);
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public CreateBucketResult createBucket(CreateBucketRequest createBucketRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.createBucket(createBucketRequest, null).getResult();
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public OSSAsyncTask<DeleteBucketResult> asyncDeleteBucket(DeleteBucketRequest deleteBucketRequest, OSSCompletedCallback<DeleteBucketRequest, DeleteBucketResult> oSSCompletedCallback) {
        return this.internalRequestOperation.deleteBucket(deleteBucketRequest, oSSCompletedCallback);
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public DeleteBucketResult deleteBucket(DeleteBucketRequest deleteBucketRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.deleteBucket(deleteBucketRequest, null).getResult();
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public OSSAsyncTask<GetBucketACLResult> asyncGetBucketACL(GetBucketACLRequest getBucketACLRequest, OSSCompletedCallback<GetBucketACLRequest, GetBucketACLResult> oSSCompletedCallback) {
        return this.internalRequestOperation.getBucketACL(getBucketACLRequest, oSSCompletedCallback);
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public GetBucketACLResult getBucketACL(GetBucketACLRequest getBucketACLRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.getBucketACL(getBucketACLRequest, null).getResult();
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public OSSAsyncTask<PutObjectResult> asyncPutObject(PutObjectRequest putObjectRequest, OSSCompletedCallback<PutObjectRequest, PutObjectResult> oSSCompletedCallback) {
        return this.internalRequestOperation.putObject(putObjectRequest, oSSCompletedCallback);
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public PutObjectResult putObject(PutObjectRequest putObjectRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.putObject(putObjectRequest, null).getResult();
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public OSSAsyncTask<GetObjectResult> asyncGetObject(GetObjectRequest getObjectRequest, OSSCompletedCallback<GetObjectRequest, GetObjectResult> oSSCompletedCallback) {
        return this.internalRequestOperation.getObject(getObjectRequest, oSSCompletedCallback);
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public GetObjectResult getObject(GetObjectRequest getObjectRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.getObject(getObjectRequest, null).getResult();
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public OSSAsyncTask<DeleteObjectResult> asyncDeleteObject(DeleteObjectRequest deleteObjectRequest, OSSCompletedCallback<DeleteObjectRequest, DeleteObjectResult> oSSCompletedCallback) {
        return this.internalRequestOperation.deleteObject(deleteObjectRequest, oSSCompletedCallback);
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public DeleteObjectResult deleteObject(DeleteObjectRequest deleteObjectRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.deleteObject(deleteObjectRequest, null).getResult();
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public OSSAsyncTask<AppendObjectResult> asyncAppendObject(AppendObjectRequest appendObjectRequest, OSSCompletedCallback<AppendObjectRequest, AppendObjectResult> oSSCompletedCallback) {
        return this.internalRequestOperation.appendObject(appendObjectRequest, oSSCompletedCallback);
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public AppendObjectResult appendObject(AppendObjectRequest appendObjectRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.appendObject(appendObjectRequest, null).getResult();
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public OSSAsyncTask<HeadObjectResult> asyncHeadObject(HeadObjectRequest headObjectRequest, OSSCompletedCallback<HeadObjectRequest, HeadObjectResult> oSSCompletedCallback) {
        return this.internalRequestOperation.headObject(headObjectRequest, oSSCompletedCallback);
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public HeadObjectResult headObject(HeadObjectRequest headObjectRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.headObject(headObjectRequest, null).getResult();
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public OSSAsyncTask<CopyObjectResult> asyncCopyObject(CopyObjectRequest copyObjectRequest, OSSCompletedCallback<CopyObjectRequest, CopyObjectResult> oSSCompletedCallback) {
        return this.internalRequestOperation.copyObject(copyObjectRequest, oSSCompletedCallback);
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public CopyObjectResult copyObject(CopyObjectRequest copyObjectRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.copyObject(copyObjectRequest, null).getResult();
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public OSSAsyncTask<ListObjectsResult> asyncListObjects(ListObjectsRequest listObjectsRequest, OSSCompletedCallback<ListObjectsRequest, ListObjectsResult> oSSCompletedCallback) {
        return this.internalRequestOperation.listObjects(listObjectsRequest, oSSCompletedCallback);
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public ListObjectsResult listObjects(ListObjectsRequest listObjectsRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.listObjects(listObjectsRequest, null).getResult();
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public OSSAsyncTask<InitiateMultipartUploadResult> asyncInitMultipartUpload(InitiateMultipartUploadRequest initiateMultipartUploadRequest, OSSCompletedCallback<InitiateMultipartUploadRequest, InitiateMultipartUploadResult> oSSCompletedCallback) {
        return this.internalRequestOperation.initMultipartUpload(initiateMultipartUploadRequest, oSSCompletedCallback);
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public InitiateMultipartUploadResult initMultipartUpload(InitiateMultipartUploadRequest initiateMultipartUploadRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.initMultipartUpload(initiateMultipartUploadRequest, null).getResult();
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public OSSAsyncTask<UploadPartResult> asyncUploadPart(UploadPartRequest uploadPartRequest, OSSCompletedCallback<UploadPartRequest, UploadPartResult> oSSCompletedCallback) {
        return this.internalRequestOperation.uploadPart(uploadPartRequest, oSSCompletedCallback);
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public UploadPartResult uploadPart(UploadPartRequest uploadPartRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.uploadPart(uploadPartRequest, null).getResult();
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public OSSAsyncTask<CompleteMultipartUploadResult> asyncCompleteMultipartUpload(CompleteMultipartUploadRequest completeMultipartUploadRequest, OSSCompletedCallback<CompleteMultipartUploadRequest, CompleteMultipartUploadResult> oSSCompletedCallback) {
        return this.internalRequestOperation.completeMultipartUpload(completeMultipartUploadRequest, oSSCompletedCallback);
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public CompleteMultipartUploadResult completeMultipartUpload(CompleteMultipartUploadRequest completeMultipartUploadRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.completeMultipartUpload(completeMultipartUploadRequest, null).getResult();
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public OSSAsyncTask<AbortMultipartUploadResult> asyncAbortMultipartUpload(AbortMultipartUploadRequest abortMultipartUploadRequest, OSSCompletedCallback<AbortMultipartUploadRequest, AbortMultipartUploadResult> oSSCompletedCallback) {
        return this.internalRequestOperation.abortMultipartUpload(abortMultipartUploadRequest, oSSCompletedCallback);
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public AbortMultipartUploadResult abortMultipartUpload(AbortMultipartUploadRequest abortMultipartUploadRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.abortMultipartUpload(abortMultipartUploadRequest, null).getResult();
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public OSSAsyncTask<ListPartsResult> asyncListParts(ListPartsRequest listPartsRequest, OSSCompletedCallback<ListPartsRequest, ListPartsResult> oSSCompletedCallback) {
        return this.internalRequestOperation.listParts(listPartsRequest, oSSCompletedCallback);
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public ListPartsResult listParts(ListPartsRequest listPartsRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.listParts(listPartsRequest, null).getResult();
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public void updateCredentialProvider(OSSCredentialProvider oSSCredentialProvider) {
        this.credentialProvider = oSSCredentialProvider;
        this.internalRequestOperation.setCredentialProvider(oSSCredentialProvider);
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public OSSAsyncTask<ResumableUploadResult> asyncResumableUpload(ResumableUploadRequest resumableUploadRequest, OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult> oSSCompletedCallback) {
        return this.extensionRequestOperation.resumableUpload(resumableUploadRequest, oSSCompletedCallback);
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public ResumableUploadResult resumableUpload(ResumableUploadRequest resumableUploadRequest) throws ClientException, ServiceException {
        return this.extensionRequestOperation.resumableUpload(resumableUploadRequest, null).getResult();
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public String presignConstrainedObjectURL(String str, String str2, long j) throws ClientException {
        return new ObjectURLPresigner(this.endpointURI, this.credentialProvider).presignConstrainedURL(str, str2, j);
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public String presignPublicObjectURL(String str, String str2) {
        return new ObjectURLPresigner(this.endpointURI, this.credentialProvider).presignPublicURL(str, str2);
    }

    @Override // com.alibaba.sdk.android.oss.OSS
    public boolean doesObjectExist(String str, String str2) throws ClientException, ServiceException {
        return this.extensionRequestOperation.doesObjectExist(str, str2);
    }
}