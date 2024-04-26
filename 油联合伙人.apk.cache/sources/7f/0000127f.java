package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.pro.h;
import com.umeng.analytics.pro.z;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.utils.UMUtils;

/* loaded from: classes.dex */
public class AnalyticsConfig {
    public static String GPU_RENDERER = "";
    public static String GPU_VENDER = "";
    private static String b;
    private static String c;
    private static String d;
    private static int e;
    public static String mWrapperType;
    public static String mWrapperVersion;
    public static MobclickAgent.PageMode AUTO_ACTIVITY_PAGE_COLLECTION = MobclickAgent.PageMode.LEGACY_AUTO;
    public static boolean CHANGE_CATCH_EXCEPTION_NOTALLOW = false;
    public static boolean CATCH_EXCEPTION = true;
    public static long kContinueSessionMillis = com.umeng.commonsdk.proguard.c.d;
    public static boolean CLEAR_EKV_BL = false;
    public static boolean CLEAR_EKV_WL = false;
    static double[] a = null;

    static void a(String str) {
        c = str;
    }

    public static String getAppkey(Context context) {
        return UMUtils.getAppkey(context);
    }

    public static String getChannel(Context context) {
        return UMUtils.getChannel(context);
    }

    public static double[] getLocation() {
        return a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            d = str;
            z.a(context).a(d);
            return;
        }
        UMLog.aq(h.A, 0, "\\|");
    }

    public static String getSecretKey(Context context) {
        if (TextUtils.isEmpty(d)) {
            d = z.a(context).c();
        }
        return d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Context context, int i) {
        e = i;
        z.a(context).a(e);
    }

    public static int getVerticalType(Context context) {
        if (e == 0) {
            e = z.a(context).d();
        }
        return e;
    }
}