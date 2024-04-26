package com.allenliu.versionchecklib.v2.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import com.allenliu.versionchecklib.R;
import com.allenliu.versionchecklib.utils.ALog;
import com.allenliu.versionchecklib.utils.AllenEventBusUtil;
import com.allenliu.versionchecklib.utils.AppUtils;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.eventbus.CommonEvent;
import java.io.File;

/* loaded from: classes.dex */
public class UIActivity extends AllenBaseActivity implements DialogInterface.OnCancelListener {
    private boolean isDestroy = false;
    private Dialog versionDialog;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.allenliu.versionchecklib.v2.ui.AllenBaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ALog.e("version activity create");
        showVersionDialog();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.allenliu.versionchecklib.v2.ui.AllenBaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.isDestroy = true;
        ALog.e("version activity destroy");
        super.onDestroy();
    }

    @Override // com.allenliu.versionchecklib.v2.ui.AllenBaseActivity
    public void receiveEvent(CommonEvent commonEvent) {
        if (commonEvent.getEventType() != 97) {
            return;
        }
        showVersionDialog();
    }

    @Override // com.allenliu.versionchecklib.v2.ui.AllenBaseActivity
    public void showDefaultDialog() {
        if (getVersionBuilder() != null) {
            UIData versionBundle = getVersionBuilder().getVersionBundle();
            String str = "提示";
            String str2 = "检测到新版本";
            if (versionBundle != null) {
                str = versionBundle.getTitle();
                str2 = versionBundle.getContent();
            }
            AlertDialog.Builder positiveButton = new AlertDialog.Builder(this).setTitle(str).setMessage(str2).setPositiveButton(getString(R.string.versionchecklib_confirm), new DialogInterface.OnClickListener() { // from class: com.allenliu.versionchecklib.v2.ui.UIActivity.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    UIActivity.this.dealVersionDialogCommit();
                }
            });
            if (getVersionBuilder().getForceUpdateListener() == null) {
                positiveButton.setNegativeButton(getString(R.string.versionchecklib_cancel), new DialogInterface.OnClickListener() { // from class: com.allenliu.versionchecklib.v2.ui.UIActivity.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UIActivity.this.onCancel(UIActivity.this.versionDialog);
                    }
                });
                positiveButton.setCancelable(false);
            } else {
                positiveButton.setCancelable(false);
            }
            this.versionDialog = positiveButton.create();
            this.versionDialog.setCanceledOnTouchOutside(false);
            this.versionDialog.show();
        }
    }

    @Override // com.allenliu.versionchecklib.v2.ui.AllenBaseActivity
    public void showCustomDialog() {
        if (getVersionBuilder() != null) {
            ALog.e("show customization dialog");
            this.versionDialog = getVersionBuilder().getCustomVersionDialogListener().getCustomVersionDialog(this, getVersionBuilder().getVersionBundle());
            try {
                View findViewById = this.versionDialog.findViewById(R.id.versionchecklib_version_dialog_commit);
                if (findViewById != null) {
                    ALog.e("view not null");
                    findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.allenliu.versionchecklib.v2.ui.UIActivity.3
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            ALog.e("click");
                            UIActivity.this.dealVersionDialogCommit();
                        }
                    });
                } else {
                    throwWrongIdsException();
                }
                View findViewById2 = this.versionDialog.findViewById(R.id.versionchecklib_version_dialog_cancel);
                if (findViewById2 != null) {
                    findViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.allenliu.versionchecklib.v2.ui.UIActivity.4
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            UIActivity.this.onCancel(UIActivity.this.versionDialog);
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
                throwWrongIdsException();
            }
            this.versionDialog.show();
        }
    }

    private void showVersionDialog() {
        if (getVersionBuilder() != null && getVersionBuilder().getCustomVersionDialogListener() != null) {
            showCustomDialog();
        } else {
            showDefaultDialog();
        }
        if (this.versionDialog != null) {
            this.versionDialog.setOnCancelListener(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.versionDialog == null || !this.versionDialog.isShowing()) {
            return;
        }
        this.versionDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.versionDialog == null || this.versionDialog.isShowing()) {
            return;
        }
        this.versionDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealVersionDialogCommit() {
        DownloadBuilder versionBuilder = getVersionBuilder();
        if (versionBuilder != null) {
            if (versionBuilder.isSilentDownload()) {
                StringBuilder sb = new StringBuilder();
                sb.append(versionBuilder.getDownloadAPKPath());
                int i = R.string.versionchecklib_download_apkname;
                Object[] objArr = new Object[1];
                objArr[0] = versionBuilder.getApkName() != null ? versionBuilder.getApkName() : getPackageName();
                sb.append(getString(i, objArr));
                AppUtils.installApk(this, new File(sb.toString()), versionBuilder.getCustomInstallListener());
                checkForceUpdate();
            } else {
                AllenEventBusUtil.sendEventBus(98);
            }
            finish();
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        cancelHandler();
        checkForceUpdate();
        AllenVersionChecker.getInstance().cancelAllMission(this);
        finish();
    }
}