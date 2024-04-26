package com.alibaba.sdk.android.httpdns;

/* loaded from: classes.dex */
class d {
    static String b;

    /* renamed from: b  reason: collision with other field name */
    static final String[] f4b = new String[0];

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void c(String str) {
        synchronized (d.class) {
            b = str;
        }
    }
}