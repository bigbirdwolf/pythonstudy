package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.z;

/* compiled from: InternalConfig.java */
/* loaded from: classes.dex */
public class c {
    private static String[] a = new String[2];

    public static void a(Context context, String str, String str2) {
        a[0] = str;
        a[1] = str2;
        if (context != null) {
            z.a(context).a(str, str2);
        }
    }

    public static String[] a(Context context) {
        String[] a2;
        if (TextUtils.isEmpty(a[0]) || TextUtils.isEmpty(a[1])) {
            if (context == null || (a2 = z.a(context).a()) == null) {
                return null;
            }
            a[0] = a2[0];
            a[1] = a2[1];
            return a;
        }
        return a;
    }

    public static void b(Context context) {
        a[0] = null;
        a[1] = null;
        if (context != null) {
            z.a(context).b();
        }
    }
}