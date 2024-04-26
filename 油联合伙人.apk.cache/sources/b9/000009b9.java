package com.alipay.sdk.util;

import android.content.Context;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/* loaded from: classes.dex */
public class j {
    private static String a;

    public static boolean a(Context context, String str) {
        try {
            return PreferenceManager.getDefaultSharedPreferences(context).contains(str);
        } catch (Throwable th) {
            c.a(th);
            return false;
        }
    }

    public static void b(Context context, String str) {
        try {
            PreferenceManager.getDefaultSharedPreferences(context).edit().remove(str).commit();
        } catch (Throwable th) {
            c.a(th);
        }
    }

    public static void a(Context context, String str, String str2) {
        try {
            String a2 = com.alipay.sdk.encrypt.e.a(a(context), str2);
            if (!TextUtils.isEmpty(str2) && TextUtils.isEmpty(a2)) {
                com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.c, com.alipay.sdk.app.statistic.c.x, String.format("%s,%s", str, str2));
            }
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(str, a2).commit();
        } catch (Throwable th) {
            c.a(th);
        }
    }

    public static String b(Context context, String str, String str2) {
        try {
            String string = PreferenceManager.getDefaultSharedPreferences(context).getString(str, str2);
            r0 = TextUtils.isEmpty(string) ? null : com.alipay.sdk.encrypt.e.b(a(context), string);
            if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(r0)) {
                com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.c, com.alipay.sdk.app.statistic.c.w, String.format("%s,%s", str, string));
            }
        } catch (Exception e) {
            c.a(e);
        }
        return r0;
    }

    private static String a(Context context) {
        String str;
        if (TextUtils.isEmpty(a)) {
            try {
                str = context.getApplicationContext().getPackageName();
            } catch (Throwable th) {
                c.a(th);
                str = "";
            }
            a = (str + "0000000000000000000000000000").substring(0, 24);
        }
        return a;
    }
}