package com.umeng.commonsdk.proguard;

import java.lang.reflect.InvocationTargetException;

/* compiled from: TEnumHelper.java */
/* loaded from: classes.dex */
public class o {
    public static n a(Class<? extends n> cls, int i) {
        try {
            return (n) cls.getMethod("findByValue", Integer.TYPE).invoke(null, Integer.valueOf(i));
        } catch (IllegalAccessException unused) {
            return null;
        } catch (NoSuchMethodException unused2) {
            return null;
        } catch (InvocationTargetException unused3) {
            return null;
        }
    }
}