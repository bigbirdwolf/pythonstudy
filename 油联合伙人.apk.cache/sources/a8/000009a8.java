package com.alipay.sdk.sys;

import android.content.Context;
import com.alipay.sdk.data.c;
import com.ta.utdid2.device.UTDevice;
import java.io.File;

/* loaded from: classes.dex */
public class b {
    private static b a;
    private Context b;

    private b() {
    }

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    public Context b() {
        return this.b;
    }

    public void a(Context context, c cVar) {
        this.b = context.getApplicationContext();
    }

    public c c() {
        return c.b();
    }

    public static boolean d() {
        for (String str : new String[]{"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"}) {
            if (new File(str).exists()) {
                return true;
            }
        }
        return false;
    }

    public String e() {
        try {
            return UTDevice.getUtdid(this.b);
        } catch (Throwable th) {
            com.alipay.sdk.util.c.a(th);
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.e, com.alipay.sdk.app.statistic.c.k, th);
            return "";
        }
    }
}