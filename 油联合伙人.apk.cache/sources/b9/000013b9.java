package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;

/* compiled from: UMTTZeroTracker.java */
/* loaded from: classes.dex */
public class p extends a {
    private static final String a = "umtt0";
    private Context b;

    public p(Context context) {
        super(a);
        this.b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        try {
            Class<?> cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
            if (cls != null) {
                return (String) cls.getMethod("getUmtt0", Context.class).invoke(cls, this.b);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}