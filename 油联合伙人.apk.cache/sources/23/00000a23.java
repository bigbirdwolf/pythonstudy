package com.allenliu.versionchecklib.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;
import com.allenliu.versionchecklib.R;
import com.allenliu.versionchecklib.v2.eventbus.CommonEvent;
import com.allenliu.versionchecklib.v2.ui.AllenBaseActivity;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class PermissionDialogActivity extends AllenBaseActivity {
    @Override // com.allenliu.versionchecklib.v2.ui.AllenBaseActivity
    public void showCustomDialog() {
    }

    @Override // com.allenliu.versionchecklib.v2.ui.AllenBaseActivity
    public void showDefaultDialog() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.allenliu.versionchecklib.v2.ui.AllenBaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, VersionDialogActivity.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                return;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, VersionDialogActivity.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                return;
            }
        }
        sendBroadcast(true);
    }

    private void sendBroadcast(boolean z) {
        Intent intent = new Intent();
        intent.setAction(AVersionService.PERMISSION_ACTION);
        intent.putExtra("result", z);
        sendBroadcast(intent);
        CommonEvent commonEvent = new CommonEvent();
        commonEvent.setEventType(99);
        commonEvent.setSuccessful(true);
        commonEvent.setData(Boolean.valueOf(z));
        EventBus.getDefault().post(commonEvent);
        finish();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != 291) {
            return;
        }
        if (iArr.length > 0 && iArr[0] == 0) {
            sendBroadcast(true);
            return;
        }
        Toast.makeText(this, getString(R.string.versionchecklib_write_permission_deny), 1).show();
        sendBroadcast(false);
    }
}