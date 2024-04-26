package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;

/* compiled from: UMTTFiveTracker.java */
/* loaded from: classes.dex */
public class k extends a {
    private static final String a = "umtt5";
    private Context b;

    public k(Context context) {
        super(a);
        this.b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        try {
            Class<?> cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
            if (cls != null) {
                return (String) cls.getMethod("getUmtt5", Context.class).invoke(cls, this.b);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}