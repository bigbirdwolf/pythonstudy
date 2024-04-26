package com.allenliu.versionchecklib.core;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import com.allenliu.versionchecklib.R;
import com.allenliu.versionchecklib.callback.DownloadListener;
import com.allenliu.versionchecklib.core.http.AllenHttp;
import com.allenliu.versionchecklib.utils.ALog;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import java.io.File;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes.dex */
public abstract class AVersionService extends Service implements DownloadListener {
    public static final String PERMISSION_ACTION = "com.allenliu.versionchecklib.filepermisssion.action";
    public static final String VERSION_PARAMS_EXTRA_KEY = "VERSION_PARAMS_EXTRA_KEY";
    public static final String VERSION_PARAMS_KEY = "VERSION_PARAMS_KEY";
    String downloadUrl;
    Bundle paramBundle;
    Callback stringCallback = new Callback() { // from class: com.allenliu.versionchecklib.core.AVersionService.1
        @Override // okhttp3.Callback
        public void onFailure(Call call, IOException iOException) {
            AVersionService.this.pauseRequest();
        }

        @Override // okhttp3.Callback
        public void onResponse(Call call, Response response) throws IOException {
            if (!response.isSuccessful()) {
                AVersionService.this.pauseRequest();
                return;
            }
            final String string = response.body().string();
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.allenliu.versionchecklib.core.AVersionService.1.1
                @Override // java.lang.Runnable
                public void run() {
                    AVersionService.this.onResponses(AVersionService.this, string);
                }
            });
        }
    };
    String title;
    String updateMsg;
    protected VersionParams versionParams;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // com.allenliu.versionchecklib.callback.DownloadListener
    public void onCheckerDownloading(int i) {
    }

    @Override // com.allenliu.versionchecklib.callback.DownloadListener
    public void onCheckerStartDownload() {
    }

    public abstract void onResponses(AVersionService aVersionService, String str);

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            try {
                this.versionParams = (VersionParams) intent.getParcelableExtra(VERSION_PARAMS_KEY);
                verfiyAndDeleteAPK();
                if (this.versionParams.isOnlyDownload()) {
                    showVersionDialog(this.versionParams.getDownloadUrl(), this.versionParams.getTitle(), this.versionParams.getUpdateMsg(), this.versionParams.getParamBundle());
                } else {
                    requestVersionUrlSync();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.onStartCommand(intent, i, i2);
    }

    private void verfiyAndDeleteAPK() {
        try {
            String str = this.versionParams.getDownloadAPKPath() + getApplicationContext().getString(R.string.versionchecklib_download_apkname, getApplicationContext().getPackageName());
            if (DownloadManager.checkAPKIsExists(getApplicationContext(), str)) {
                return;
            }
            ALog.e("删除本地apk");
            new File(str).delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestVersionUrlSync() {
        requestVersionUrl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pauseRequest() {
        long pauseRequestTime = this.versionParams.getPauseRequestTime();
        if (pauseRequestTime > 0) {
            ALog.e("请求版本接口失败，下次请求将在" + pauseRequestTime + "ms后开始");
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.allenliu.versionchecklib.core.AVersionService.2
                @Override // java.lang.Runnable
                public void run() {
                    AVersionService.this.requestVersionUrlSync();
                }
            }, pauseRequestTime);
        }
    }

    private void requestVersionUrl() {
        Request build;
        OkHttpClient httpClient = AllenHttp.getHttpClient();
        switch (this.versionParams.getRequestMethod()) {
            case GET:
                build = AllenHttp.get(this.versionParams).build();
                break;
            case POST:
                build = AllenHttp.post(this.versionParams).build();
                break;
            case POSTJSON:
                build = AllenHttp.postJson(this.versionParams).build();
                break;
            default:
                build = null;
                break;
        }
        httpClient.newCall(build).enqueue(this.stringCallback);
    }

    public void showVersionDialog(String str, String str2, String str3) {
        showVersionDialog(str, str2, str3, null);
    }

    public void showVersionDialog(String str, String str2, String str3, Bundle bundle) {
        this.downloadUrl = str;
        this.title = str2;
        this.updateMsg = str3;
        this.paramBundle = bundle;
        if (this.versionParams.isSilentDownload()) {
            registerReceiver(new VersionBroadCastReceiver(), new IntentFilter(PERMISSION_ACTION));
            Intent intent = new Intent(this, PermissionDialogActivity.class);
            intent.addFlags(CommonNetImpl.FLAG_AUTH);
            startActivity(intent);
            return;
        }
        goToVersionDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void silentDownload() {
        DownloadManager.downloadAPK(this.downloadUrl, this.versionParams, this);
    }

    @Override // com.allenliu.versionchecklib.callback.DownloadListener
    public void onCheckerDownloadSuccess(File file) {
        goToVersionDialog();
    }

    @Override // com.allenliu.versionchecklib.callback.DownloadListener
    public void onCheckerDownloadFail() {
        stopSelf();
    }

    private void goToVersionDialog() {
        Intent intent = new Intent(getApplicationContext(), this.versionParams.getCustomDownloadActivityClass());
        if (this.updateMsg != null) {
            intent.putExtra("text", this.updateMsg);
        }
        if (this.downloadUrl != null) {
            intent.putExtra("downloadUrl", this.downloadUrl);
        }
        if (this.title != null) {
            intent.putExtra("title", this.title);
        }
        if (this.paramBundle != null) {
            this.versionParams.setParamBundle(this.paramBundle);
        }
        intent.putExtra(VERSION_PARAMS_KEY, this.versionParams);
        intent.addFlags(CommonNetImpl.FLAG_AUTH);
        startActivity(intent);
        stopSelf();
    }

    public void setVersionParams(VersionParams versionParams) {
        this.versionParams = versionParams;
    }

    /* loaded from: classes.dex */
    public class VersionBroadCastReceiver extends BroadcastReceiver {
        public VersionBroadCastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(AVersionService.PERMISSION_ACTION)) {
                if (intent.getBooleanExtra("result", false)) {
                    AVersionService.this.silentDownload();
                }
                AVersionService.this.unregisterReceiver(this);
            }
        }
    }
}