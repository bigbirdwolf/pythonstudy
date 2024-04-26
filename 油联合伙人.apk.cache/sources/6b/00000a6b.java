package com.allenliu.versionchecklib.v2.ui;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.allenliu.versionchecklib.R;
import com.allenliu.versionchecklib.callback.DownloadListener;
import com.allenliu.versionchecklib.core.DownloadManager;
import com.allenliu.versionchecklib.core.PermissionDialogActivity;
import com.allenliu.versionchecklib.core.http.AllenHttp;
import com.allenliu.versionchecklib.utils.ALog;
import com.allenliu.versionchecklib.utils.AllenEventBusUtil;
import com.allenliu.versionchecklib.utils.AppUtils;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.eventbus.CommonEvent;
import com.allenliu.versionchecklib.v2.net.DownloadMangerV2;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes.dex */
public class VersionService extends Service {
    public static DownloadBuilder builder;
    private BuilderHelper builderHelper;
    private ExecutorService executors;
    private boolean isServiceAlive = false;
    private NotificationHelper notificationHelper;

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        ALog.e("version service create");
        init();
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ALog.e("version service destroy");
        this.builderHelper = null;
        if (this.notificationHelper != null) {
            this.notificationHelper.onDestroy();
        }
        this.notificationHelper = null;
        this.isServiceAlive = false;
        if (this.executors != null) {
            this.executors.shutdown();
        }
        stopForeground(true);
        AllenHttp.getHttpClient().dispatcher().cancelAll();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public static void enqueueWork(Context context) {
        Intent intent = new Intent(context, VersionService.class);
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    protected void onHandleWork() {
        downloadAPK();
    }

    private void downloadAPK() {
        if (builder != null && builder.getVersionBundle() != null) {
            if (builder.isDirectDownload()) {
                AllenEventBusUtil.sendEventBus(98);
                return;
            } else if (builder.isSilentDownload()) {
                requestPermissionAndDownload();
                return;
            } else {
                showVersionDialog();
                return;
            }
        }
        AllenVersionChecker.getInstance().cancelAllMission(getApplicationContext());
    }

    private void showVersionDialog() {
        if (builder != null) {
            Intent intent = new Intent(this, UIActivity.class);
            intent.addFlags(CommonNetImpl.FLAG_AUTH);
            startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDownloadingDialog() {
        if (builder == null || !builder.isShowDownloadingDialog()) {
            return;
        }
        Intent intent = new Intent(this, DownloadingActivity.class);
        intent.addFlags(CommonNetImpl.FLAG_AUTH);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDownloadingDialogProgress(int i) {
        CommonEvent commonEvent = new CommonEvent();
        commonEvent.setEventType(100);
        commonEvent.setData(Integer.valueOf(i));
        commonEvent.setSuccessful(true);
        EventBus.getDefault().post(commonEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDownloadFailedDialog() {
        if (builder != null) {
            Intent intent = new Intent(this, DownloadFailedActivity.class);
            intent.addFlags(CommonNetImpl.FLAG_AUTH);
            startActivity(intent);
        }
    }

    private void requestPermissionAndDownload() {
        if (builder != null) {
            Intent intent = new Intent(this, PermissionDialogActivity.class);
            intent.addFlags(CommonNetImpl.FLAG_AUTH);
            startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void install() {
        AllenEventBusUtil.sendEventBus(101);
        String downloadFilePath = getDownloadFilePath();
        if (builder.isSilentDownload()) {
            showVersionDialog();
            return;
        }
        AppUtils.installApk(getApplicationContext(), new File(downloadFilePath), builder.getCustomInstallListener());
        this.builderHelper.checkForceUpdate();
    }

    private String getDownloadFilePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(builder.getDownloadAPKPath());
        int i = R.string.versionchecklib_download_apkname;
        Object[] objArr = new Object[1];
        objArr[0] = builder.getApkName() != null ? builder.getApkName() : getPackageName();
        sb.append(getString(i, objArr));
        return sb.toString();
    }

    @WorkerThread
    private void startDownloadApk() {
        String downloadFilePath = getDownloadFilePath();
        if (DownloadManager.checkAPKIsExists(getApplicationContext(), downloadFilePath, builder.getNewestVersionCode()) && !builder.isForceRedownload()) {
            ALog.e("using cache");
            install();
            return;
        }
        this.builderHelper.checkAndDeleteAPK();
        String downloadUrl = builder.getDownloadUrl();
        if (downloadUrl == null && builder.getVersionBundle() != null) {
            downloadUrl = builder.getVersionBundle().getDownloadUrl();
        }
        if (downloadUrl == null) {
            AllenVersionChecker.getInstance().cancelAllMission(getApplicationContext());
            throw new RuntimeException("you must set a download url for download function using");
        }
        ALog.e("downloadPath:" + downloadFilePath);
        String downloadAPKPath = builder.getDownloadAPKPath();
        int i = R.string.versionchecklib_download_apkname;
        Object[] objArr = new Object[1];
        objArr[0] = builder.getApkName() != null ? builder.getApkName() : getPackageName();
        DownloadMangerV2.download(downloadUrl, downloadAPKPath, getString(i, objArr), new DownloadListener() { // from class: com.allenliu.versionchecklib.v2.ui.VersionService.1
            @Override // com.allenliu.versionchecklib.callback.DownloadListener
            public void onCheckerDownloading(int i2) {
                if (!VersionService.this.isServiceAlive || VersionService.builder == null) {
                    return;
                }
                if (!VersionService.builder.isSilentDownload()) {
                    VersionService.this.notificationHelper.updateNotification(i2);
                    VersionService.this.updateDownloadingDialogProgress(i2);
                }
                if (VersionService.builder.getApkDownloadListener() != null) {
                    VersionService.builder.getApkDownloadListener().onDownloading(i2);
                }
            }

            @Override // com.allenliu.versionchecklib.callback.DownloadListener
            public void onCheckerDownloadSuccess(File file) {
                if (VersionService.this.isServiceAlive) {
                    if (!VersionService.builder.isSilentDownload()) {
                        VersionService.this.notificationHelper.showDownloadCompleteNotifcation(file);
                    }
                    if (VersionService.builder.getApkDownloadListener() != null) {
                        VersionService.builder.getApkDownloadListener().onDownloadSuccess(file);
                    }
                    VersionService.this.install();
                }
            }

            @Override // com.allenliu.versionchecklib.callback.DownloadListener
            public void onCheckerDownloadFail() {
                if (VersionService.this.isServiceAlive) {
                    if (VersionService.builder.getApkDownloadListener() != null) {
                        VersionService.builder.getApkDownloadListener().onDownloadFail();
                    }
                    if (!VersionService.builder.isSilentDownload()) {
                        AllenEventBusUtil.sendEventBus(102);
                        if (VersionService.builder.isShowDownloadFailDialog()) {
                            VersionService.this.showDownloadFailedDialog();
                        }
                        VersionService.this.notificationHelper.showDownloadFailedNotification();
                        return;
                    }
                    AllenVersionChecker.getInstance().cancelAllMission(VersionService.this.getApplicationContext());
                }
            }

            @Override // com.allenliu.versionchecklib.callback.DownloadListener
            public void onCheckerStartDownload() {
                ALog.e("start download apk");
                if (VersionService.builder.isSilentDownload()) {
                    return;
                }
                VersionService.this.notificationHelper.showNotification();
                VersionService.this.showDownloadingDialog();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(CommonEvent commonEvent) {
        switch (commonEvent.getEventType()) {
            case 98:
                requestPermissionAndDownload();
                return;
            case 99:
                if (((Boolean) commonEvent.getData()).booleanValue()) {
                    startDownloadApk();
                    return;
                } else if (this.builderHelper != null) {
                    this.builderHelper.checkForceUpdate();
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= 26) {
            startForeground(1, NotificationHelper.createSimpleNotification(this));
        }
        if (builder != null) {
            this.isServiceAlive = true;
            this.builderHelper = new BuilderHelper(getApplicationContext(), builder);
            this.notificationHelper = new NotificationHelper(getApplicationContext(), builder);
            startForeground(1, this.notificationHelper.getServiceNotification());
            this.executors = Executors.newSingleThreadExecutor();
            this.executors.submit(new Runnable() { // from class: com.allenliu.versionchecklib.v2.ui.VersionService.2
                @Override // java.lang.Runnable
                public void run() {
                    VersionService.this.onHandleWork();
                }
            });
            return;
        }
        AllenVersionChecker.getInstance().cancelAllMission(this);
    }
}