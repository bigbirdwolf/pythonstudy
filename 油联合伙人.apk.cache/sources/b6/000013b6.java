package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;

/* compiled from: UMTTOneTracker.java */
/* loaded from: classes.dex */
public class m extends a {
    private static final String a = "umtt1";
    private Context b;

    public m(Context context) {
        super(a);
        this.b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        try {
            Class<?> cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
            if (cls != null) {
                return (String) cls.getMethod("getUmtt1", Context.class).invoke(cls, this.b);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}