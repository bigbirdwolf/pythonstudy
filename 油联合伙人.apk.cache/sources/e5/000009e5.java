package com.alipay.security.mobile.module.c;

import android.content.Context;
import java.util.HashMap;

/* loaded from: classes.dex */
public class a {
    public static String a(Context context, String str, String str2) {
        String str3;
        String a;
        synchronized (a.class) {
            if (context != null) {
                try {
                    if (!com.alipay.security.mobile.module.a.a.a(str) && !com.alipay.security.mobile.module.a.a.a(str2)) {
                        try {
                            a = e.a(context, str, str2, "");
                        } catch (Throwable unused) {
                            str3 = null;
                        }
                        if (com.alipay.security.mobile.module.a.a.a(a)) {
                            return null;
                        }
                        str3 = com.alipay.security.mobile.module.a.a.c.b(com.alipay.security.mobile.module.a.a.c.a(), a);
                        return str3;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return null;
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        synchronized (a.class) {
            if (com.alipay.security.mobile.module.a.a.a(str) || com.alipay.security.mobile.module.a.a.a(str2) || context == null) {
                return;
            }
            try {
                String a = com.alipay.security.mobile.module.a.a.c.a(com.alipay.security.mobile.module.a.a.c.a(), str3);
                HashMap hashMap = new HashMap();
                hashMap.put(str2, a);
                e.a(context, str, hashMap);
            } catch (Throwable unused) {
            }
        }
    }
}