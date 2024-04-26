package com.yltx.oil.partner.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import com.yltx.oil.partner.base.ActivityStackManager;

/* loaded from: classes.dex */
public class CheckPermissionsUtils {
    public static void showMissingPermissionDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("当前应用缺少必要权限。请点击-\\\"设置\\\"-\\\"权限\\\"-打开所需权限。");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { // from class: com.yltx.oil.partner.utils.CheckPermissionsUtils.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityStackManager.getDefault().finishAllActivity();
                System.exit(0);
            }
        });
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() { // from class: com.yltx.oil.partner.utils.CheckPermissionsUtils.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                CheckPermissionsUtils.startAppSettings(context);
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    public static void startAppSettings(Context context) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }

    public static boolean verifyPermissions(int[] iArr) {
        for (int i : iArr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}