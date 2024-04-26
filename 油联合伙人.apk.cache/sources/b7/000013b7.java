package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;

/* compiled from: UMTTThreeTracker.java */
/* loaded from: classes.dex */
public class n extends a {
    private static final String a = "umtt3";
    private Context b;

    public n(Context context) {
        super(a);
        this.b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        try {
            Class<?> cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
            if (cls != null) {
                return (String) cls.getMethod("getUmtt3", Context.class).invoke(cls, this.b);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}