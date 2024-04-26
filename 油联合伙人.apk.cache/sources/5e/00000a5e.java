package com.allenliu.versionchecklib.v2.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import com.allenliu.versionchecklib.R;
import com.allenliu.versionchecklib.utils.ALog;
import com.allenliu.versionchecklib.utils.AllenEventBusUtil;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;

/* loaded from: classes.dex */
public class DownloadFailedActivity extends AllenBaseActivity implements DialogInterface.OnCancelListener {
    private Dialog downloadFailedDialog;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.allenliu.versionchecklib.v2.ui.AllenBaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        showDowloadFailedDialog();
    }

    @Override // com.allenliu.versionchecklib.v2.ui.AllenBaseActivity
    public void showDefaultDialog() {
        this.downloadFailedDialog = new AlertDialog.Builder(this).setMessage(getString(R.string.versionchecklib_download_fail_retry)).setPositiveButton(getString(R.string.versionchecklib_confirm), new DialogInterface.OnClickListener() { // from class: com.allenliu.versionchecklib.v2.ui.DownloadFailedActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                DownloadFailedActivity.this.retryDownload();
            }
        }).setNegativeButton(getString(R.string.versionchecklib_cancel), new DialogInterface.OnClickListener() { // from class: com.allenliu.versionchecklib.v2.ui.DownloadFailedActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                DownloadFailedActivity.this.onCancel(DownloadFailedActivity.this.downloadFailedDialog);
            }
        }).create();
        this.downloadFailedDialog.setCanceledOnTouchOutside(false);
        this.downloadFailedDialog.setCancelable(true);
        this.downloadFailedDialog.show();
    }

    @Override // com.allenliu.versionchecklib.v2.ui.AllenBaseActivity
    public void showCustomDialog() {
        if (getVersionBuilder() != null) {
            this.downloadFailedDialog = getVersionBuilder().getCustomDownloadFailedListener().getCustomDownloadFailed(this, getVersionBuilder().getVersionBundle());
            View findViewById = this.downloadFailedDialog.findViewById(R.id.versionchecklib_failed_dialog_retry);
            if (findViewById != null) {
                findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.allenliu.versionchecklib.v2.ui.DownloadFailedActivity.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        DownloadFailedActivity.this.retryDownload();
                    }
                });
            }
            View findViewById2 = this.downloadFailedDialog.findViewById(R.id.versionchecklib_failed_dialog_cancel);
            if (findViewById2 != null) {
                findViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.allenliu.versionchecklib.v2.ui.DownloadFailedActivity.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        DownloadFailedActivity.this.onCancel(DownloadFailedActivity.this.downloadFailedDialog);
                    }
                });
            }
            this.downloadFailedDialog.show();
        }
    }

    private void showDowloadFailedDialog() {
        AllenEventBusUtil.sendEventBus(102);
        if (getVersionBuilder() != null && getVersionBuilder().getCustomDownloadFailedListener() != null) {
            ALog.e("show customization failed dialog");
            showCustomDialog();
        } else {
            ALog.e("show default failed dialog");
            showDefaultDialog();
        }
        this.downloadFailedDialog.setOnCancelListener(this);
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        ALog.e("on cancel");
        cancelHandler();
        checkForceUpdate();
        AllenVersionChecker.getInstance().cancelAllMission(this);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void retryDownload() {
        AllenEventBusUtil.sendEventBus(98);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.downloadFailedDialog == null || !this.downloadFailedDialog.isShowing()) {
            return;
        }
        this.downloadFailedDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.downloadFailedDialog == null || this.downloadFailedDialog.isShowing()) {
            return;
        }
        this.downloadFailedDialog.show();
    }
}