package com.yltx.oil.partner.utils;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import com.yltx.oil.partner.base.PartnerApplication;
import java.io.File;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class UpdateManager {
    private static final String DOWNLOAD_ID = "download_id";
    private static final String TAG = "UpdateManager";
    private static UpdateManager instance;
    private UpdateConfiguration configuration;
    private File mApkFile;
    private DownloadManager mDownloadManager;
    private SharedPreferences prefs;
    private WeakReference<Context> weakReference;
    private long mReqId = -1;
    private DownloadReceiver mDownloadReceiver = new DownloadReceiver();

    public static UpdateManager getInstance(Context context) {
        if (instance == null) {
            synchronized (UpdateManager.class) {
                if (instance == null) {
                    instance = new UpdateManager(context);
                }
            }
        }
        return instance;
    }

    private UpdateManager(@NonNull Context context) {
        this.weakReference = new WeakReference<>(context);
        this.mDownloadManager = (DownloadManager) context.getSystemService("download");
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void init(UpdateConfiguration updateConfiguration) {
        if (updateConfiguration == null) {
            throw new IllegalArgumentException("configuration can not be initialized with null");
        }
        this.configuration = updateConfiguration;
    }

    public void startDownload(String str) throws Exception {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("downloadUrl can not be null.");
        }
        if (queryDownloadStatus()) {
            return;
        }
        String str2 = TAG;
        Log.d(str2, "download url:" + str);
        String fileName = getFileName(str);
        this.mApkFile = new File(this.weakReference.get().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), fileName);
        if (this.mApkFile.exists()) {
            this.mApkFile.delete();
        }
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
        request.setAllowedNetworkTypes(this.configuration.allowedNetworkTypes);
        request.setTitle(this.configuration.title);
        request.setDescription(this.configuration.description);
        request.setMimeType(this.configuration.mimeType);
        request.setDestinationInExternalFilesDir(this.weakReference.get(), this.configuration.destinationDir, fileName);
        request.setNotificationVisibility(this.configuration.notificationVisibility);
        request.setVisibleInDownloadsUi(this.configuration.visibleInDownloads);
        this.mReqId = this.mDownloadManager.enqueue(request);
        this.prefs.edit().putLong(DOWNLOAD_ID, this.mReqId).apply();
    }

    public void cancelDownload(long... jArr) {
        this.mDownloadManager.remove(jArr);
    }

    private boolean queryDownloadStatus() {
        DownloadManager.Query query = new DownloadManager.Query();
        boolean z = true;
        query.setFilterById(this.mReqId);
        Cursor query2 = this.mDownloadManager.query(query);
        if (query2.moveToFirst()) {
            int i = query2.getInt(query2.getColumnIndex("status"));
            if (i == 4) {
                Log.d(TAG, "download status: paused");
            } else if (i == 8) {
                Log.d(TAG, "download status: successful");
                openFile(this.mReqId);
                query2.close();
                return z;
            } else if (i != 16) {
                switch (i) {
                    case 2:
                        Log.d(TAG, "download status: running");
                        break;
                }
            } else {
                Log.d(TAG, "download status: failed");
                cancelDownload(this.mReqId);
                this.mReqId = -1L;
                this.prefs.edit().clear().apply();
            }
            Log.d(TAG, "download status: pending");
            Log.d(TAG, "download status: running");
        }
        z = false;
        query2.close();
        return z;
    }

    private String getFileName(String str) {
        String lastPathSegment = Uri.parse(str).getLastPathSegment();
        String str2 = TAG;
        Log.d(str2, "download file name: " + lastPathSegment);
        return lastPathSegment;
    }

    public void resume() {
        this.weakReference.get().registerReceiver(this.mDownloadReceiver, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
    }

    public void onPause() {
        this.weakReference.get().unregisterReceiver(this.mDownloadReceiver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openFile(long j) {
        Uri uriForFile;
        Intent intent = new Intent();
        intent.addFlags(CommonNetImpl.FLAG_AUTH);
        intent.setAction("android.intent.action.VIEW");
        if (Build.VERSION.SDK_INT < 23) {
            uriForFile = this.mDownloadManager.getUriForDownloadedFile(j);
        } else if (Build.VERSION.SDK_INT < 24) {
            uriForFile = Uri.fromFile(queryDownloadedApk(j));
        } else if (this.mApkFile == null) {
            ToastUtil.showMiddleScreenToast("mApkFile為空");
            return;
        } else {
            uriForFile = FileProvider.getUriForFile(this.weakReference.get(), "com.yltx.oil.partner.fileprovider", this.mApkFile);
            intent = new Intent("android.intent.action.VIEW");
            intent.setFlags(CommonNetImpl.FLAG_AUTH);
            intent.addFlags(1);
        }
        if (uriForFile != null) {
            intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
            if (intent.resolveActivity(PartnerApplication.mContext.getPackageManager()) != null) {
                this.weakReference.get().startActivity(intent);
                return;
            }
            Log.d(TAG, "自动安装失败，请手动安装");
            ToastUtil.showMiddleScreenToast("下载完成，请点击下拉列表的通知手动安装");
            return;
        }
        Log.d(TAG, "自动安装失败，请手动安装");
        ToastUtil.showMiddleScreenToast("下载完成，请点击下拉列表的通知手动安装");
    }

    private File queryDownloadedApk(long j) {
        File file = null;
        if (j != -1) {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(j);
            query.setFilterByStatus(8);
            Cursor query2 = this.mDownloadManager != null ? this.mDownloadManager.query(query) : null;
            if (query2 != null) {
                if (query2.moveToFirst()) {
                    String string = query2.getString(query2.getColumnIndex("local_uri"));
                    if (!TextUtils.isEmpty(string)) {
                        file = new File(Uri.parse(string).getPath());
                    }
                }
                query2.close();
            }
        }
        return file;
    }

    /* loaded from: classes.dex */
    class DownloadReceiver extends BroadcastReceiver {
        DownloadReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.d(UpdateManager.TAG, "download complete");
            long longExtra = intent.getLongExtra("extra_download_id", -1L);
            if (longExtra != PreferenceManager.getDefaultSharedPreferences(context).getLong(UpdateManager.DOWNLOAD_ID, -1L) || longExtra == -1) {
                return;
            }
            UpdateManager.this.openFile(longExtra);
        }
    }
}