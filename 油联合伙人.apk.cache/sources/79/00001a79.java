package com.yltx.oil.partner.oss;

import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;

/* loaded from: classes.dex */
public class PauseableUploadResult extends CompleteMultipartUploadResult {
    public PauseableUploadResult(CompleteMultipartUploadResult completeMultipartUploadResult) {
        setBucketName(completeMultipartUploadResult.getBucketName());
        setObjectKey(completeMultipartUploadResult.getObjectKey());
        setETag(completeMultipartUploadResult.getETag());
        setLocation(completeMultipartUploadResult.getLocation());
        setRequestId(completeMultipartUploadResult.getRequestId());
        setResponseHeader(completeMultipartUploadResult.getResponseHeader());
        setStatusCode(completeMultipartUploadResult.getStatusCode());
    }
}