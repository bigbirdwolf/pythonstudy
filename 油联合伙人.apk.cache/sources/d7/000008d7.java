package com.alibaba.sdk.android.oss.model;

/* loaded from: classes.dex */
public class GetBucketACLRequest extends OSSRequest {
    private String bucketName;

    public GetBucketACLRequest(String str) {
        this.bucketName = str;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public String getBucketName() {
        return this.bucketName;
    }
}