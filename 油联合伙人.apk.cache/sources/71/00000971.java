package com.alipay.sdk.app;

/* loaded from: classes.dex */
public class j {
    private static boolean a = false;
    private static String b;

    public static void a(String str) {
        b = str;
    }

    public static String a() {
        return b;
    }

    public static boolean b() {
        return a;
    }

    public static void a(boolean z) {
        a = z;
    }

    public static String c() {
        k b2 = k.b(k.CANCELED.a());
        return a(b2.a(), b2.b(), "");
    }

    public static String d() {
        k b2 = k.b(k.DOUBLE_REQUEST.a());
        return a(b2.a(), b2.b(), "");
    }

    public static String e() {
        k b2 = k.b(k.PARAMS_ERROR.a());
        return a(b2.a(), b2.b(), "");
    }

    public static String a(int i, String str, String str2) {
        return "resultStatus={" + i + "};memo={" + str + "};result={" + str2 + com.alipay.sdk.util.i.d;
    }
}