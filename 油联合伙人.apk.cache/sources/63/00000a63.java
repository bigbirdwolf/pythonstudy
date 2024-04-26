package com.allenliu.versionchecklib.v2.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.allenliu.versionchecklib.R;
import com.allenliu.versionchecklib.core.http.AllenHttp;
import com.allenliu.versionchecklib.utils.ALog;
import com.allenliu.versionchecklib.v2.eventbus.CommonEvent;

/* loaded from: classes.dex */
public class DownloadingActivity extends AllenBaseActivity implements DialogInterface.OnCancelListener {
    public static final String PROGRESS = "progress";
    private Dialog downloadingDialog;
    private int currentProgress = 0;
    protected boolean isDestroy = false;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.allenliu.versionchecklib.v2.ui.AllenBaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ALog.e("loading activity create");
        showLoadingDialog();
    }

    public void onCancel(boolean z) {
        if (!z) {
            AllenHttp.getHttpClient().dispatcher().cancelAll();
            cancelHandler();
            checkForceUpdate();
        }
        finish();
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        onCancel(false);
    }

    @Override // com.allenliu.versionchecklib.v2.ui.AllenBaseActivity
    public void receiveEvent(CommonEvent commonEvent) {
        switch (commonEvent.getEventType()) {
            case 100:
                this.currentProgress = ((Integer) commonEvent.getData()).intValue();
                updateProgress();
                return;
            case 101:
                onCancel(true);
                return;
            case 102:
                destroy();
                return;
            default:
                return;
        }
    }

    @Override // com.allenliu.versionchecklib.v2.ui.AllenBaseActivity
    public void showDefaultDialog() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.downloading_layout, (ViewGroup) null);
        this.downloadingDialog = new AlertDialog.Builder(this).setTitle("").setView(inflate).create();
        if (getVersionBuilder().getForceUpdateListener() != null) {
            this.downloadingDialog.setCancelable(false);
        } else {
            this.downloadingDialog.setCancelable(true);
        }
        this.downloadingDialog.setCanceledOnTouchOutside(false);
        ((TextView) inflate.findViewById(R.id.tv_progress)).setText(String.format(getString(R.string.versionchecklib_progress), Integer.valueOf(this.currentProgress)));
        ((ProgressBar) inflate.findViewById(R.id.pb)).setProgress(this.currentProgress);
        this.downloadingDialog.show();
    }

    @Override // com.allenliu.versionchecklib.v2.ui.AllenBaseActivity
    public void showCustomDialog() {
        if (getVersionBuilder() != null) {
            this.downloadingDialog = getVersionBuilder().getCustomDownloadingDialogListener().getCustomDownloadingDialog(this, this.currentProgress, getVersionBuilder().getVersionBundle());
            View findViewById = this.downloadingDialog.findViewById(R.id.versionchecklib_loading_dialog_cancel);
            if (findViewById != null) {
                findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.allenliu.versionchecklib.v2.ui.DownloadingActivity.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        DownloadingActivity.this.onCancel(false);
                    }
                });
            }
            this.downloadingDialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        destroyWithOutDismiss();
        this.isDestroy = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.isDestroy = false;
        if (this.downloadingDialog == null || this.downloadingDialog.isShowing()) {
            return;
        }
        this.downloadingDialog.show();
    }

    private void destroyWithOutDismiss() {
        if (this.downloadingDialog == null || !this.downloadingDialog.isShowing()) {
            return;
        }
        this.downloadingDialog.dismiss();
    }

    private void destroy() {
        ALog.e("loading activity destroy");
        if (this.downloadingDialog != null && this.downloadingDialog.isShowing()) {
            this.downloadingDialog.dismiss();
        }
        finish();
    }

    private void updateProgress() {
        if (this.isDestroy) {
            return;
        }
        if (getVersionBuilder() != null && getVersionBuilder().getCustomDownloadingDialogListener() != null) {
            getVersionBuilder().getCustomDownloadingDialogListener().updateUI(this.downloadingDialog, this.currentProgress, getVersionBuilder().getVersionBundle());
            return;
        }
        ((ProgressBar) this.downloadingDialog.findViewById(R.id.pb)).setProgress(this.currentProgress);
        ((TextView) this.downloadingDialog.findViewById(R.id.tv_progress)).setText(String.format(getString(R.string.versionchecklib_progress), Integer.valueOf(this.currentProgress)));
        if (this.downloadingDialog.isShowing()) {
            return;
        }
        this.downloadingDialog.show();
    }

    private void showLoadingDialog() {
        ALog.e("show loading");
        if (this.isDestroy) {
            return;
        }
        if (getVersionBuilder() != null && getVersionBuilder().getCustomDownloadingDialogListener() != null) {
            showCustomDialog();
        } else {
            showDefaultDialog();
        }
        this.downloadingDialog.setOnCancelListener(this);
    }
}