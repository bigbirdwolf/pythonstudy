package com.yltx.oil.partner.oss;

import android.content.Context;
import android.support.annotation.NonNull;
import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.yltx.oil.partner.data.network.Config;
import com.yltx.oil.partner.utils.CommonUtils;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

@Singleton
/* loaded from: classes.dex */
public class OSSFileHelper {
    public static final String OSS_BLOB_FILE_NAME = "blob";
    public static final String OSS_CIRCLE_FILE_NAME = "circle";
    public static final String OSS_HEAD_FILE_NAME = "head";
    public static final String OSS_HOUSE_FILE_NAME = "house";
    public static final String OSS_OTHER_FILE_NAME = "other";
    private Context context;
    private final String endpoint = Config.OSS_BASE_URL;
    private final String callbackAddress = "";
    private String bucket = Config.OSS_BUCKET_NAME;
    private String pathName = "test/";
    private OSSService ossService = initOSS(Config.OSS_BASE_URL, this.bucket, null);

    @Inject
    public OSSFileHelper(@NonNull Context context) {
        this.context = context;
    }

    private OSSService initOSS(String str, String str2, ImageDisplayer imageDisplayer) {
        OSSPlainTextAKSKCredentialProvider oSSPlainTextAKSKCredentialProvider = new OSSPlainTextAKSKCredentialProvider(Config.OSS_ACCESS_KEY, Config.OSS_SECRET_KEY);
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setConnectionTimeout(15000);
        clientConfiguration.setSocketTimeout(15000);
        clientConfiguration.setMaxConcurrentRequest(5);
        clientConfiguration.setMaxErrorRetry(2);
        return new OSSService(new OSSClient(this.context, str, oSSPlainTextAKSKCredentialProvider, clientConfiguration), str2, imageDisplayer);
    }

    public static String getFileRemoteUrl(String str) {
        return String.format("http://%s.%s/%s", Config.OSS_BUCKET_NAME, Config.OSS_BASE_URL, str);
    }

    public void asyncGet(String str, OSSCompletedCallback<GetObjectRequest, GetObjectResult> oSSCompletedCallback) {
        this.ossService.asyncGetImage(str, oSSCompletedCallback);
    }

    public void asyncUpload(int i, String str, @NonNull OSSCompletedCallback<PutObjectRequest, PutObjectResult> oSSCompletedCallback) {
        String generateFileName;
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 5:
                generateFileName = CommonUtils.generateFileName(CommonUtils.FileType.FILE_TYPE_IMAGE);
                break;
            case 3:
                generateFileName = CommonUtils.generateFileName(CommonUtils.FileType.FILE_TYPE_AUDIO);
                break;
            case 4:
                generateFileName = CommonUtils.generateFileName(CommonUtils.FileType.FILE_TYPE_VIDEO);
                break;
            default:
                generateFileName = null;
                break;
        }
        this.ossService.asyncPutImage(generateFileName, str, oSSCompletedCallback, null);
    }

    public Observable<String> asyncUpload(int i, String str) {
        String generateFileName;
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 5:
                generateFileName = CommonUtils.generateFileName(CommonUtils.FileType.FILE_TYPE_IMAGE);
                break;
            case 3:
                generateFileName = CommonUtils.generateFileName(CommonUtils.FileType.FILE_TYPE_AUDIO);
                break;
            case 4:
                generateFileName = CommonUtils.generateFileName(CommonUtils.FileType.FILE_TYPE_VIDEO);
                break;
            default:
                generateFileName = "nofile.test";
                break;
        }
        return Observable.create(new CallOnSubscribe(Config.IMG_PATH.concat(generateFileName), str)).subscribeOn(Schedulers.io());
    }

    /* loaded from: classes.dex */
    final class CallOnSubscribe<T> implements Observable.OnSubscribe<String> {
        private String object;
        private String path;

        public CallOnSubscribe(String str, String str2) {
            this.object = str;
            this.path = str2;
        }

        @Override // rx.functions.Action1
        public void call(Subscriber<? super String> subscriber) {
            subscriber.add(Subscriptions.create(new Action0() { // from class: com.yltx.oil.partner.oss.OSSFileHelper.CallOnSubscribe.1
                @Override // rx.functions.Action0
                public void call() {
                }
            }));
            try {
                OSSFileHelper.this.ossService.putImage(this.object, this.path);
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(this.object);
                }
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onCompleted();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onError(th);
            }
        }
    }
}