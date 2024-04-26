package com.alibaba.sdk.android.oss.model;

/* loaded from: classes.dex */
public class ResumableUploadResult extends CompleteMultipartUploadResult {
    public ResumableUploadResult(CompleteMultipartUploadResult completeMultipartUploadResult) {
        setBucketName(completeMultipartUploadResult.getBucketName());
        setObjectKey(completeMultipartUploadResult.getObjectKey());
        setETag(completeMultipartUploadResult.getETag());
        setLocation(completeMultipartUploadResult.getLocation());
        setRequestId(completeMultipartUploadResult.getRequestId());
        setResponseHeader(completeMultipartUploadResult.getResponseHeader());
        setStatusCode(completeMultipartUploadResult.getStatusCode());
        setServerCallbackReturnBody(completeMultipartUploadResult.getServerCallbackReturnBody());
    }
}