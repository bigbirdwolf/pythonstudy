package com.allenliu.versionchecklib.v2.net;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.allenliu.versionchecklib.core.http.AllenHttp;
import com.allenliu.versionchecklib.core.http.HttpRequestMethod;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.RequestVersionBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
import java.io.IOException;
import java.util.concurrent.Executors;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes.dex */
public class RequestVersionManager {

    /* loaded from: classes.dex */
    public static class Holder {
        static RequestVersionManager instance = new RequestVersionManager();
    }

    public static RequestVersionManager getInstance() {
        return Holder.instance;
    }

    public void requestVersion(final DownloadBuilder downloadBuilder, final Context context) {
        Executors.newSingleThreadExecutor().submit(new Runnable() { // from class: com.allenliu.versionchecklib.v2.net.RequestVersionManager.1
            @Override // java.lang.Runnable
            public void run() {
                Request build;
                RequestVersionBuilder requestVersionBuilder = downloadBuilder.getRequestVersionBuilder();
                OkHttpClient httpClient = AllenHttp.getHttpClient();
                switch (AnonymousClass2.$SwitchMap$com$allenliu$versionchecklib$core$http$HttpRequestMethod[requestVersionBuilder.getRequestMethod().ordinal()]) {
                    case 1:
                        build = AllenHttp.get(requestVersionBuilder).build();
                        break;
                    case 2:
                        build = AllenHttp.post(requestVersionBuilder).build();
                        break;
                    case 3:
                        build = AllenHttp.postJson(requestVersionBuilder).build();
                        break;
                    default:
                        build = null;
                        break;
                }
                final RequestVersionListener requestVersionListener = requestVersionBuilder.getRequestVersionListener();
                Handler handler = new Handler(Looper.getMainLooper());
                if (requestVersionListener != null) {
                    try {
                        final Response execute = httpClient.newCall(build).execute();
                        if (execute.isSuccessful()) {
                            final String string = execute.body() != null ? execute.body().string() : null;
                            handler.post(new Runnable() { // from class: com.allenliu.versionchecklib.v2.net.RequestVersionManager.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    UIData onRequestVersionSuccess = requestVersionListener.onRequestVersionSuccess(string);
                                    if (onRequestVersionSuccess != null) {
                                        downloadBuilder.setVersionBundle(onRequestVersionSuccess);
                                        downloadBuilder.download(context);
                                    }
                                }
                            });
                            return;
                        }
                        handler.post(new Runnable() { // from class: com.allenliu.versionchecklib.v2.net.RequestVersionManager.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                requestVersionListener.onRequestVersionFailure(execute.message());
                                AllenVersionChecker.getInstance().cancelAllMission(context);
                            }
                        });
                        return;
                    } catch (IOException e) {
                        e.printStackTrace();
                        handler.post(new Runnable() { // from class: com.allenliu.versionchecklib.v2.net.RequestVersionManager.1.3
                            @Override // java.lang.Runnable
                            public void run() {
                                requestVersionListener.onRequestVersionFailure(e.getMessage());
                                AllenVersionChecker.getInstance().cancelAllMission(context);
                            }
                        });
                        return;
                    }
                }
                throw new RuntimeException("using request version function,you must set a requestVersionListener");
            }
        });
    }

    /* renamed from: com.allenliu.versionchecklib.v2.net.RequestVersionManager$2  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$allenliu$versionchecklib$core$http$HttpRequestMethod = new int[HttpRequestMethod.values().length];

        static {
            try {
                $SwitchMap$com$allenliu$versionchecklib$core$http$HttpRequestMethod[HttpRequestMethod.GET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$allenliu$versionchecklib$core$http$HttpRequestMethod[HttpRequestMethod.POST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$allenliu$versionchecklib$core$http$HttpRequestMethod[HttpRequestMethod.POSTJSON.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}