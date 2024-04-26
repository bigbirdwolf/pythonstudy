package com.allenliu.versionchecklib.core;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.allenliu.versionchecklib.R;
import com.allenliu.versionchecklib.callback.APKDownloadListener;
import com.allenliu.versionchecklib.callback.CommitClickListener;
import com.allenliu.versionchecklib.callback.DialogDismissListener;
import com.allenliu.versionchecklib.callback.DownloadListener;
import com.allenliu.versionchecklib.core.http.AllenHttp;
import com.allenliu.versionchecklib.utils.ALog;
import com.allenliu.versionchecklib.utils.AppUtils;
import com.allenliu.versionchecklib.v2.ui.AllenBaseActivity;
import java.io.File;

/* loaded from: classes.dex */
public class VersionDialogActivity extends AllenBaseActivity implements DownloadListener, DialogInterface.OnDismissListener {
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 291;
    public static VersionDialogActivity instance;
    private APKDownloadListener apkDownloadListener;
    private DialogDismissListener cancelListener;
    private CommitClickListener commitListener;
    private String downloadUrl;
    protected Dialog failDialog;
    boolean isDestroy = false;
    protected Dialog loadingDialog;
    private View loadingView;
    private String title;
    private String updateMsg;
    protected Dialog versionDialog;
    private VersionParams versionParams;

    @Override // com.allenliu.versionchecklib.v2.ui.AllenBaseActivity
    public void showCustomDialog() {
    }

    @Override // com.allenliu.versionchecklib.v2.ui.AllenBaseActivity
    public void showDefaultDialog() {
    }

    @Override // com.allenliu.versionchecklib.v2.ui.AllenBaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        instance = this;
        boolean booleanExtra = getIntent().getBooleanExtra("isRetry", false);
        Log.e("isRetry", booleanExtra + "");
        if (booleanExtra) {
            retryDownload(getIntent());
        } else {
            initialize();
        }
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public VersionParams getVersionParams() {
        return this.versionParams;
    }

    public String getVersionTitle() {
        return this.title;
    }

    public String getVersionUpdateMsg() {
        return this.updateMsg;
    }

    public Bundle getVersionParamBundle() {
        return this.versionParams.getParamBundle();
    }

    private void initialize() {
        this.title = getIntent().getStringExtra("title");
        this.updateMsg = getIntent().getStringExtra("text");
        this.versionParams = (VersionParams) getIntent().getParcelableExtra(AVersionService.VERSION_PARAMS_KEY);
        this.downloadUrl = getIntent().getStringExtra("downloadUrl");
        if (this.title == null || this.updateMsg == null || this.downloadUrl == null || this.versionParams == null) {
            return;
        }
        showVersionDialog();
    }

    protected void showVersionDialog() {
        if (this.isDestroy) {
            return;
        }
        this.versionDialog = new AlertDialog.Builder(this).setTitle(this.title).setMessage(this.updateMsg).setPositiveButton(getString(R.string.versionchecklib_confirm), new DialogInterface.OnClickListener() { // from class: com.allenliu.versionchecklib.core.VersionDialogActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (VersionDialogActivity.this.commitListener != null) {
                    VersionDialogActivity.this.commitListener.onCommitClick();
                }
                VersionDialogActivity.this.dealAPK();
            }
        }).setNegativeButton(getString(R.string.versionchecklib_cancel), new DialogInterface.OnClickListener() { // from class: com.allenliu.versionchecklib.core.VersionDialogActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                VersionDialogActivity.this.finish();
            }
        }).create();
        this.versionDialog.setOnDismissListener(this);
        this.versionDialog.setCanceledOnTouchOutside(false);
        this.versionDialog.setCancelable(false);
        this.versionDialog.show();
    }

    public void showLoadingDialog(int i) {
        ALog.e("show default downloading dialog");
        if (this.isDestroy) {
            return;
        }
        if (this.loadingDialog == null) {
            this.loadingView = LayoutInflater.from(this).inflate(R.layout.downloading_layout, (ViewGroup) null);
            this.loadingDialog = new AlertDialog.Builder(this).setTitle("").setView(this.loadingView).create();
            this.loadingDialog.setCancelable(true);
            this.loadingDialog.setCanceledOnTouchOutside(false);
            this.loadingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.allenliu.versionchecklib.core.VersionDialogActivity.3
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    AllenHttp.getHttpClient().dispatcher().cancelAll();
                }
            });
        }
        ((TextView) this.loadingView.findViewById(R.id.tv_progress)).setText(String.format(getString(R.string.versionchecklib_progress), Integer.valueOf(i)));
        ((ProgressBar) this.loadingView.findViewById(R.id.pb)).setProgress(i);
        this.loadingDialog.show();
    }

    public void showFailDialog() {
        if (this.isDestroy) {
            return;
        }
        if (this.versionParams != null && this.versionParams.isShowDownloadFailDialog()) {
            if (this.failDialog == null) {
                this.failDialog = new AlertDialog.Builder(this).setMessage(getString(R.string.versionchecklib_download_fail_retry)).setPositiveButton(getString(R.string.versionchecklib_confirm), new DialogInterface.OnClickListener() { // from class: com.allenliu.versionchecklib.core.VersionDialogActivity.4
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (VersionDialogActivity.this.commitListener != null) {
                            VersionDialogActivity.this.commitListener.onCommitClick();
                        }
                        VersionDialogActivity.this.dealAPK();
                    }
                }).setNegativeButton(getString(R.string.versionchecklib_cancel), (DialogInterface.OnClickListener) null).create();
                this.failDialog.setOnDismissListener(this);
                this.failDialog.setCanceledOnTouchOutside(false);
                this.failDialog.setCancelable(false);
            }
            this.failDialog.show();
            return;
        }
        onDismiss(null);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        boolean booleanExtra = intent.getBooleanExtra("isRetry", false);
        Log.e("isRetry", booleanExtra + "");
        if (booleanExtra) {
            retryDownload(intent);
        }
    }

    private void retryDownload(Intent intent) {
        dismissAllDialog();
        this.versionParams = (VersionParams) intent.getParcelableExtra(AVersionService.VERSION_PARAMS_KEY);
        this.downloadUrl = intent.getStringExtra("downloadUrl");
        requestPermissionAndDownloadFile();
    }

    public void setApkDownloadListener(APKDownloadListener aPKDownloadListener) {
        this.apkDownloadListener = aPKDownloadListener;
    }

    public void setCommitClickListener(CommitClickListener commitClickListener) {
        this.commitListener = commitClickListener;
    }

    public void setDialogDimissListener(DialogDismissListener dialogDismissListener) {
        this.cancelListener = dialogDismissListener;
    }

    public void dealAPK() {
        if (this.versionParams.isSilentDownload()) {
            AppUtils.installApk(this, new File(this.versionParams.getDownloadAPKPath() + getString(R.string.versionchecklib_download_apkname, new Object[]{getPackageName()})));
            finish();
            return;
        }
        if (this.versionParams.isShowDownloadingDialog()) {
            showLoadingDialog(0);
        }
        requestPermissionAndDownloadFile();
    }

    protected void downloadFile() {
        if (this.versionParams.isShowDownloadingDialog()) {
            showLoadingDialog(0);
        }
        DownloadManager.downloadAPK(this.downloadUrl, this.versionParams, this);
    }

    protected void requestPermissionAndDownloadFile() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                return;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                return;
            }
        }
        downloadFile();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != 291) {
            return;
        }
        if (iArr.length > 0 && iArr[0] == 0) {
            downloadFile();
            return;
        }
        Toast.makeText(this, getString(R.string.versionchecklib_write_permission_deny), 1).show();
        finish();
    }

    @Override // com.allenliu.versionchecklib.callback.DownloadListener
    public void onCheckerDownloading(int i) {
        if (this.versionParams.isShowDownloadingDialog()) {
            showLoadingDialog(i);
        } else {
            if (this.loadingDialog != null) {
                this.loadingDialog.dismiss();
            }
            finish();
        }
        if (this.apkDownloadListener != null) {
            this.apkDownloadListener.onDownloading(i);
        }
    }

    @Override // com.allenliu.versionchecklib.callback.DownloadListener
    public void onCheckerDownloadSuccess(File file) {
        if (this.apkDownloadListener != null) {
            this.apkDownloadListener.onDownloadSuccess(file);
        }
        dismissAllDialog();
    }

    @Override // com.allenliu.versionchecklib.callback.DownloadListener
    public void onCheckerDownloadFail() {
        if (this.apkDownloadListener != null) {
            this.apkDownloadListener.onDownloadFail();
        }
        dismissAllDialog();
        showFailDialog();
    }

    @Override // com.allenliu.versionchecklib.callback.DownloadListener
    public void onCheckerStartDownload() {
        if (this.versionParams.isShowDownloadingDialog()) {
            return;
        }
        finish();
    }

    @Override // com.allenliu.versionchecklib.v2.ui.AllenBaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.isDestroy = true;
        instance = null;
        super.onDestroy();
    }

    private void dismissAllDialog() {
        if (this.isDestroy) {
            return;
        }
        ALog.e("dismiss all dialog");
        if (this.loadingDialog != null && this.loadingDialog.isShowing()) {
            this.loadingDialog.dismiss();
        }
        if (this.versionDialog != null && this.versionDialog.isShowing()) {
            this.versionDialog.dismiss();
        }
        if (this.failDialog == null || !this.failDialog.isShowing()) {
            return;
        }
        this.failDialog.dismiss();
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (this.versionParams.isSilentDownload() || ((!this.versionParams.isSilentDownload() && this.loadingDialog == null && this.versionParams.isShowDownloadingDialog()) || !(this.versionParams.isSilentDownload() || this.loadingDialog == null || this.loadingDialog.isShowing() || !this.versionParams.isShowDownloadingDialog()))) {
            if (this.cancelListener != null) {
                this.cancelListener.dialogDismiss(dialogInterface);
            }
            finish();
            AllenChecker.cancelMission();
        }
    }
}