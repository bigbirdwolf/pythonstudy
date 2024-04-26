package com.yltx.oil.partner.oss;

import android.support.annotation.NonNull;
import android.util.Log;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import java.io.File;
import java.util.HashMap;

/* loaded from: classes.dex */
public class OSSService {
    private static final int partSize = 262144;
    private ImageDisplayer ImageDisplayer;
    private String bucket;
    private String callbackAddress;
    private MultiPartUploadManager multiPartUploadManager;
    private OSS oss;

    public OSSService(OSS oss, String str, ImageDisplayer imageDisplayer) {
        this.oss = oss;
        this.bucket = str;
        this.ImageDisplayer = imageDisplayer;
        this.multiPartUploadManager = new MultiPartUploadManager(oss, str, 262144, imageDisplayer);
    }

    public void setCallbackAddress(String str) {
        this.callbackAddress = str;
    }

    public void asyncGetImage(String str, @NonNull OSSCompletedCallback<GetObjectRequest, GetObjectResult> oSSCompletedCallback) {
        if (str == null || str.equals("")) {
            Log.w("AsyncGetImage", "ObjectNull");
            return;
        }
        Log.d("GetImage", "Start");
        this.oss.asyncGetObject(new GetObjectRequest(this.bucket, str), oSSCompletedCallback);
    }

    public PutObjectResult putImage(String str, String str2) throws ClientException, ServiceException {
        if (str == null || str.equals("")) {
            Log.w("PutImage", "ObjectNull");
            return null;
        } else if (!new File(str2).exists()) {
            Log.w("PutImage", "FileNotExist");
            Log.w("LocalFile", str2);
            return null;
        } else {
            PutObjectRequest putObjectRequest = new PutObjectRequest(this.bucket, str, str2);
            if (this.callbackAddress != null) {
                putObjectRequest.setCallbackParam(new HashMap<String, String>() { // from class: com.yltx.oil.partner.oss.OSSService.1
                    {
                        put("callbackUrl", OSSService.this.callbackAddress);
                        put("callbackBody", "filename=${object}");
                    }
                });
            }
            return this.oss.putObject(putObjectRequest);
        }
    }

    public OSSAsyncTask<PutObjectResult> asyncPutImage(String str, String str2, @NonNull OSSCompletedCallback<PutObjectRequest, PutObjectResult> oSSCompletedCallback, OSSProgressCallback<PutObjectRequest> oSSProgressCallback) {
        if (str == null || str.equals("")) {
            Log.w("AsyncPutImage", "ObjectNull");
            return null;
        } else if (!new File(str2).exists()) {
            Log.w("AsyncPutImage", "FileNotExist");
            Log.w("LocalFile", str2);
            return null;
        } else {
            PutObjectRequest putObjectRequest = new PutObjectRequest(this.bucket, str, str2);
            if (this.callbackAddress != null) {
                putObjectRequest.setCallbackParam(new HashMap<String, String>() { // from class: com.yltx.oil.partner.oss.OSSService.2
                    {
                        put("callbackUrl", OSSService.this.callbackAddress);
                        put("callbackBody", "filename=${object}");
                    }
                });
            }
            if (oSSProgressCallback != null) {
                putObjectRequest.setProgressCallback(oSSProgressCallback);
            }
            return this.oss.asyncPutObject(putObjectRequest, oSSCompletedCallback);
        }
    }

    public PauseableUploadTask asyncMultiPartUpload(String str, String str2, @NonNull OSSCompletedCallback<PauseableUploadRequest, PauseableUploadResult> oSSCompletedCallback, OSSProgressCallback<PauseableUploadRequest> oSSProgressCallback) {
        if (str.equals("")) {
            Log.w("AsyncMultiPartUpload", "ObjectNull");
            return null;
        } else if (!new File(str2).exists()) {
            Log.w("AsyncMultiPartUpload", "FileNotExist");
            Log.w("LocalFile", str2);
            return null;
        } else {
            Log.d("MultiPartUpload", str2);
            return this.multiPartUploadManager.asyncUpload(str, str2, oSSCompletedCallback, oSSProgressCallback);
        }
    }
}