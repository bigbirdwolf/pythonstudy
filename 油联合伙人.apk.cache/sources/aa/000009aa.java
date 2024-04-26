package com.alipay.sdk.tid;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.alipay.sdk.util.c;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class TidHelper {
    public static Tid loadTID(Context context) {
        a(context);
        Tid a = a(context, b.a(context));
        if (a == null) {
            c.b("TidHelper.loadTID", "TidHelper:::loadTID > null");
        } else {
            c.b("TidHelper.loadTID", "TidHelper:::loadTID > " + a.toString());
        }
        return a;
    }

    public static synchronized Tid loadOrCreateTID(Context context) {
        Tid tid;
        synchronized (TidHelper.class) {
            c.b("TidHelper", "TidHelper.loadOrCreateTID");
            if (context == null) {
                com.alipay.sdk.app.statistic.a.a(context, "tid", com.alipay.sdk.app.statistic.c.T, "");
            }
            a(context);
            Tid loadTID = loadTID(context);
            if (Tid.isEmpty(loadTID)) {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    return null;
                }
                try {
                    tid = b(context);
                } catch (Throwable unused) {
                }
                return tid;
            }
            tid = loadTID;
            return tid;
        }
    }

    public static synchronized String getTIDValue(Context context) {
        String tid;
        synchronized (TidHelper.class) {
            Tid loadOrCreateTID = loadOrCreateTID(context);
            tid = Tid.isEmpty(loadOrCreateTID) ? "" : loadOrCreateTID.getTid();
        }
        return tid;
    }

    public static boolean resetTID(Context context) throws Exception {
        Tid tid;
        c.b("TidHelper.resetTID", "resetTID");
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new Exception("不能在主线程中调用此方法");
        }
        a(context);
        clearTID(context);
        try {
            tid = b(context);
        } catch (Throwable unused) {
            tid = null;
        }
        return !Tid.isEmpty(tid);
    }

    public static void clearTID(Context context) {
        b.a(context).g();
    }

    public static String getIMEI(Context context) {
        a(context);
        return com.alipay.sdk.util.a.a(context).b();
    }

    public static String getIMSI(Context context) {
        a(context);
        return com.alipay.sdk.util.a.a(context).a();
    }

    public static String getVirtualImei(Context context) {
        a(context);
        return com.alipay.sdk.data.c.b().c();
    }

    public static String getVirtualImsi(Context context) {
        a(context);
        return com.alipay.sdk.data.c.b().d();
    }

    private static void a(Context context) {
        if (context == null) {
            return;
        }
        com.alipay.sdk.sys.b.a().a(context, com.alipay.sdk.data.c.b());
    }

    private static Tid b(Context context) throws Exception {
        try {
            com.alipay.sdk.packet.b a = new com.alipay.sdk.packet.impl.c().a(context);
            if (a != null) {
                JSONObject jSONObject = new JSONObject(a.b());
                b a2 = b.a(context);
                String optString = jSONObject.optString("tid");
                String string = jSONObject.getString(b.e);
                if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(string)) {
                    a2.a(optString, string);
                }
                return a(context, a2);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Tid a(Context context, b bVar) {
        if (bVar == null || bVar.e()) {
            return null;
        }
        return new Tid(bVar.a(), bVar.b(), bVar.i().longValue());
    }

    public static Tid loadLocalTid(Context context) {
        b a = b.a(context);
        if (a.h()) {
            return null;
        }
        return new Tid(a.a(), a.b(), a.i().longValue());
    }
}