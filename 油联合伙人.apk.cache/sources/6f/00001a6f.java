package com.yltx.oil.partner.oss;

import android.support.annotation.NonNull;
import android.util.Log;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.utils.BinaryUtil;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class MultiPartUploadManager {
    private ImageDisplayer ImageDisplayer;
    private String bucket;
    private Map<String, String> multiPartStatus = new ConcurrentHashMap();
    private OSS oss;
    private int partSize;

    public MultiPartUploadManager(OSS oss, String str, int i, ImageDisplayer imageDisplayer) {
        this.oss = oss;
        this.bucket = str;
        this.partSize = i;
        this.ImageDisplayer = imageDisplayer;
    }

    public PauseableUploadTask asyncUpload(final String str, final String str2, @NonNull OSSCompletedCallback<PauseableUploadRequest, PauseableUploadResult> oSSCompletedCallback, OSSProgressCallback<PauseableUploadRequest> oSSProgressCallback) {
        PauseableUploadRequest pauseableUploadRequest = new PauseableUploadRequest(this.bucket, str, str2, this.partSize);
        if (oSSProgressCallback != null) {
            pauseableUploadRequest.setProgressCallback(oSSProgressCallback);
        }
        final PauseableUploadTask pauseableUploadTask = new PauseableUploadTask(this.oss, pauseableUploadRequest, oSSCompletedCallback);
        Log.d("AsyncMultiPartUpload", "Begin");
        Log.d("Object", str);
        Log.d("LocalFile", str2);
        new Thread(new Runnable() { // from class: com.yltx.oil.partner.oss.MultiPartUploadManager.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    String calculateMd5Str = BinaryUtil.calculateMd5Str(str2);
                    String calculateMd5Str2 = BinaryUtil.calculateMd5Str((calculateMd5Str + MultiPartUploadManager.this.bucket + str + String.valueOf(MultiPartUploadManager.this.partSize)).getBytes());
                    Log.d("MultipartUploadMd5", calculateMd5Str2);
                    String str3 = (String) MultiPartUploadManager.this.multiPartStatus.get(calculateMd5Str2);
                    if (str3 == null) {
                        str3 = pauseableUploadTask.initUpload();
                        Log.d("InitUploadId", str3);
                        MultiPartUploadManager.this.multiPartStatus.put(calculateMd5Str2, str3);
                    } else {
                        Log.d("GetPausedUploadId", str3);
                    }
                    pauseableUploadTask.upload(str3);
                    if (pauseableUploadTask.isComplete()) {
                        MultiPartUploadManager.this.multiPartStatus.remove(calculateMd5Str2);
                    }
                } catch (ClientException | ServiceException | IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return pauseableUploadTask;
    }
}