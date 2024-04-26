package com.umeng.socialize.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.os.EnvironmentCompat;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;

/* loaded from: classes.dex */
public class DeviceConfig {
    protected static final String LOG_TAG = "DeviceConfig";
    private static final String MOBILE_NETWORK = "2G/3G";
    protected static final String UNKNOW = "Unknown";
    private static final String WIFI = "Wi-Fi";
    public static Context context;
    private static Object object = new Object();

    public static boolean isAppInstalled(String str, Context context2) {
        boolean z = false;
        if (context2 == null) {
            return false;
        }
        synchronized (object) {
            try {
                context2.getPackageManager().getPackageInfo(str, 1);
                z = true;
            } catch (PackageManager.NameNotFoundException | RuntimeException unused) {
            }
        }
        return z;
    }

    public static String getAppVersion(String str, Context context2) {
        if (context2 == null) {
            return "";
        }
        try {
            return context2.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean checkPermission(Context context2, String str) {
        return com.umeng.commonsdk.statistics.common.DeviceConfig.checkPermission(context2, str);
    }

    public static String getDeviceId(Context context2) {
        return com.umeng.commonsdk.statistics.common.DeviceConfig.getDeviceId(context2);
    }

    public static String getDeviceSN() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, "ro.serialno", EnvironmentCompat.MEDIA_UNKNOWN);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String[] getNetworkAccessMode(Context context2) {
        return com.umeng.commonsdk.statistics.common.DeviceConfig.getNetworkAccessMode(context2);
    }

    public static boolean isOnline(Context context2) {
        return com.umeng.commonsdk.statistics.common.DeviceConfig.isOnline(context2);
    }

    public static boolean isNetworkAvailable(Context context2) {
        return context2 != null && checkPermission(context2, "android.permission.ACCESS_NETWORK_STATE") && isOnline(context2);
    }

    public static boolean isSdCardWrittenable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static String getAndroidID(Context context2) {
        return context2 == null ? "" : Settings.Secure.getString(context2.getContentResolver(), SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID);
    }

    public static String getOsVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getMac(Context context2) {
        return com.umeng.commonsdk.statistics.common.DeviceConfig.getMac(context2);
    }

    public static String getPackageName(Context context2) {
        return context2 == null ? "" : context2.getPackageName();
    }
}