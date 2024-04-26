package com.yltx.oil.partner.oss;

import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.OSSRequest;

/* loaded from: classes.dex */
public class PauseableUploadRequest extends OSSRequest {
    private String bucket;
    private String localFile;
    private String object;
    private int partSize;
    private OSSProgressCallback<PauseableUploadRequest> progressCallback;

    public OSSProgressCallback<PauseableUploadRequest> getProgressCallback() {
        return this.progressCallback;
    }

    public void setProgressCallback(OSSProgressCallback<PauseableUploadRequest> oSSProgressCallback) {
        this.progressCallback = oSSProgressCallback;
    }

    public PauseableUploadRequest(String str, String str2, String str3, int i) {
        this.bucket = str;
        this.object = str2;
        this.localFile = str3;
        this.partSize = i;
    }

    public String getBucket() {
        return this.bucket;
    }

    public void setBucket(String str) {
        this.bucket = str;
    }

    public String getObjectKey() {
        return this.object;
    }

    public void setObject(String str) {
        this.object = str;
    }

    public String getLocalFile() {
        return this.localFile;
    }

    public void setLocalFile(String str) {
        this.localFile = str;
    }

    public int getPartSize() {
        return this.partSize;
    }

    public void setPartSize(int i) {
        this.partSize = i;
    }
}