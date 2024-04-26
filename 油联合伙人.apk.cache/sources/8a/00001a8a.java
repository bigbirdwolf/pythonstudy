package com.yltx.oil.partner.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.yltx.oil.partner.base.PartnerApplication;

/* loaded from: classes.dex */
public class AndroidUtil {
    public static boolean isNetWorkAvailable(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) ? false : true;
    }

    public static String getAppVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "1";
        }
    }

    public static int getAppVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    public static String getAndroidVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getAndroidId() {
        String string = Settings.Secure.getString(PartnerApplication.getInstance().getContentResolver(), SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID);
        Log.d(">>androidId>>>>>", "androidId>>" + string);
        return string;
    }
}