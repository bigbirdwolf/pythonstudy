package com.umeng.commonsdk.proguard;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* compiled from: FieldMetaData.java */
/* loaded from: classes.dex */
public class v implements Serializable {
    private static Map<Class<? extends j>, Map<? extends q, v>> d = new HashMap();
    public final String a;
    public final byte b;
    public final w c;

    public v(String str, byte b, w wVar) {
        this.a = str;
        this.b = b;
        this.c = wVar;
    }

    public static void a(Class<? extends j> cls, Map<? extends q, v> map) {
        d.put(cls, map);
    }

    public static Map<? extends q, v> a(Class<? extends j> cls) {
        if (!d.containsKey(cls)) {
            try {
                cls.newInstance();
            } catch (IllegalAccessException e) {
                throw new RuntimeException("IllegalAccessException for TBase class: " + cls.getName() + ", message: " + e.getMessage());
            } catch (InstantiationException e2) {
                throw new RuntimeException("InstantiationException for TBase class: " + cls.getName() + ", message: " + e2.getMessage());
            }
        }
        return d.get(cls);
    }
}