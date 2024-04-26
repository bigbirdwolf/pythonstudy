package com.allenliu.versionchecklib.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import com.allenliu.versionchecklib.core.AllenChecker;
import com.allenliu.versionchecklib.core.VersionFileProvider;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.callback.CustomInstallListener;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import java.io.File;

/* loaded from: classes.dex */
public final class AppUtils {
    private AppUtils() {
        throw new Error("Do not need instantiate!");
    }

    public static void installApk(Context context, File file) {
        Uri fromFile;
        Intent intent = new Intent();
        intent.addFlags(CommonNetImpl.FLAG_AUTH);
        intent.setAction("android.intent.action.VIEW");
        if (Build.VERSION.SDK_INT >= 24) {
            fromFile = VersionFileProvider.getUriForFile(context, context.getPackageName() + ".versionProvider", file);
            intent.addFlags(1);
        } else {
            fromFile = Uri.fromFile(file);
        }
        intent.setDataAndType(fromFile, "application/vnd.android.package-archive");
        context.startActivity(intent);
        AllenChecker.cancelMission();
        AllenVersionChecker.getInstance().cancelAllMission(context);
    }

    public static void installApk(Context context, File file, CustomInstallListener customInstallListener) {
        Uri fromFile;
        Intent intent = new Intent();
        intent.addFlags(CommonNetImpl.FLAG_AUTH);
        intent.setAction("android.intent.action.VIEW");
        if (Build.VERSION.SDK_INT >= 24) {
            fromFile = VersionFileProvider.getUriForFile(context, context.getPackageName() + ".versionProvider", file);
            intent.addFlags(1);
        } else {
            fromFile = Uri.fromFile(file);
        }
        if (customInstallListener != null) {
            customInstallListener.install(context, fromFile);
            return;
        }
        intent.setDataAndType(fromFile, "application/vnd.android.package-archive");
        context.startActivity(intent);
        AllenChecker.cancelMission();
        AllenVersionChecker.getInstance().cancelAllMission(context);
    }
}