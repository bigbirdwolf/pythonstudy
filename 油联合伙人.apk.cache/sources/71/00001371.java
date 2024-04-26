package com.umeng.commonsdk.stateless;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.s;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class UMSLEnvelopeBuild {
    private static final String TAG = "UMSLEnvelopeBuild";
    private static String cacheSystemheader;
    private static boolean isEncryptEnabled;
    public static Context mContext;
    public static String module;

    /* JADX WARN: Removed duplicated region for block: B:57:0x021e A[Catch: Exception -> 0x0223, Throwable -> 0x0294, all -> 0x02b8, TRY_LEAVE, TryCatch #2 {, blocks: (B:3:0x0001, B:8:0x0026, B:11:0x0032, B:48:0x01f4, B:50:0x01f8, B:52:0x0200, B:55:0x0218, B:57:0x021e, B:58:0x0223, B:60:0x022f, B:61:0x0234, B:63:0x0238, B:65:0x023e, B:66:0x0247, B:75:0x0298, B:9:0x002a, B:47:0x01e2, B:68:0x0257, B:70:0x025d, B:14:0x003e, B:16:0x0090, B:18:0x009d, B:20:0x00a7, B:21:0x00ac, B:23:0x00b6, B:24:0x00bb, B:26:0x00c5, B:27:0x00ca, B:29:0x0128, B:30:0x0145, B:32:0x0189, B:37:0x01aa, B:39:0x01b4, B:40:0x01bb, B:42:0x01d1, B:43:0x01d8, B:33:0x0191, B:35:0x019b, B:36:0x01a3, B:17:0x0096), top: B:88:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized org.json.JSONObject buildSLBaseHeader(android.content.Context r10) {
        /*
            Method dump skipped, instructions count: 699
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.stateless.UMSLEnvelopeBuild.buildSLBaseHeader(android.content.Context):org.json.JSONObject");
    }

    private synchronized JSONObject makeErrorResult(int i, JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                jSONObject.put("exception", i);
            } catch (Exception unused) {
            }
            return jSONObject;
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("exception", i);
        } catch (Exception unused2) {
        }
        return jSONObject2;
    }

    public synchronized JSONObject buildSLEnvelope(Context context, JSONObject jSONObject, JSONObject jSONObject2, String str) {
        Context context2;
        Throwable th;
        c cVar;
        String str2;
        ULog.i("walle", "[stateless] build envelope, heade is " + jSONObject.toString());
        ULog.i("walle", "[stateless] build envelope, body is " + jSONObject2.toString());
        ULog.i("walle", "[stateless] build envelope, thread is " + Thread.currentThread());
        if (context == null || jSONObject == null || jSONObject2 == null || str == null) {
            ULog.i("walle", "[stateless] build envelope, context is null or header is null or body is null");
            return makeErrorResult(110, null);
        }
        try {
            context2 = context.getApplicationContext();
            if (jSONObject != null && jSONObject2 != null) {
                try {
                    Iterator<String> keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        if (next != null && (next instanceof String) && (str2 = next) != null && jSONObject2.opt(str2) != null) {
                            try {
                                jSONObject.put(str2, jSONObject2.opt(str2));
                            } catch (Exception unused) {
                            }
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    UMCrashManager.reportCrash(context2, th);
                    ULog.i("walle", "build envelope end, thread is " + Thread.currentThread());
                    return makeErrorResult(110, null);
                }
            }
            if (jSONObject != null) {
                try {
                    com.umeng.commonsdk.statistics.idtracking.e a = com.umeng.commonsdk.statistics.idtracking.e.a(context2);
                    if (a != null) {
                        a.a();
                        String encodeToString = Base64.encodeToString(new s().a(a.b()), 0);
                        if (!TextUtils.isEmpty(encodeToString)) {
                            JSONObject jSONObject3 = jSONObject.getJSONObject("header");
                            jSONObject3.put(com.umeng.commonsdk.proguard.e.V, encodeToString);
                            jSONObject.put("header", jSONObject3);
                        }
                    }
                } catch (Exception unused2) {
                }
            }
            if (jSONObject != null && f.a(jSONObject.toString().getBytes().length, a.c)) {
                ULog.i("walle", "[stateless] build envelope, json overstep!!!! size is " + jSONObject.toString().getBytes().length);
                return makeErrorResult(113, jSONObject);
            }
            ULog.i("walle", "[stateless] build envelope, json size is " + jSONObject.toString().getBytes().length);
            if (jSONObject != null) {
                cVar = constructEnvelope(context2, jSONObject.toString().getBytes());
                if (cVar == null) {
                    ULog.i("walle", "[stateless] build envelope, envelope is null !!!!");
                    return makeErrorResult(111, jSONObject);
                }
            } else {
                cVar = null;
            }
            if (cVar != null && f.a(cVar.b().length, a.d)) {
                ULog.i("walle", "[stateless] build envelope, envelope overstep!!!! size is " + cVar.b().length);
                return makeErrorResult(114, jSONObject);
            }
            if (!f.a(context2, Base64.encodeToString(str.getBytes(), 0), Base64.encodeToString((str + "_" + System.currentTimeMillis()).getBytes(), 0), cVar.b())) {
                ULog.i("walle", "[stateless] build envelope, save fail ----->>>>>");
                return makeErrorResult(101, jSONObject);
            }
            ULog.i("walle", "[stateless] build envelope, save ok ----->>>>>");
            ULog.i("walle", "[stateless] envelope file size is " + jSONObject.toString().getBytes().length);
            new d(context2);
            d.b(273);
            ULog.i("walle", "[stateless] build envelope end, thread is " + Thread.currentThread());
            return jSONObject;
        } catch (Throwable th3) {
            context2 = context;
            th = th3;
        }
    }

    private synchronized c constructEnvelope(Context context, byte[] bArr) {
        c a;
        int i = -1;
        String imprintProperty = UMEnvelopeBuild.imprintProperty(context, "slcodex", null);
        ULog.i("walle", "[stateless] build envelope, codexStr is " + imprintProperty);
        try {
            if (!TextUtils.isEmpty(imprintProperty)) {
                i = Integer.valueOf(imprintProperty).intValue();
            }
        } catch (NumberFormatException e) {
            UMCrashManager.reportCrash(context, e);
        }
        if (i == 0) {
            ULog.i("walle", "[stateless] build envelope, codexValue is 0");
            a = c.a(context, UMUtils.getAppkey(context), bArr);
        } else if (i == 1) {
            ULog.i("walle", "[stateless] build envelope, codexValue is 1");
            a = c.b(context, UMUtils.getAppkey(context), bArr);
        } else if (isEncryptEnabled) {
            ULog.i("walle", "[stateless] build envelope, isEncryptEnabled is true");
            a = c.b(context, UMUtils.getAppkey(context), bArr);
        } else {
            ULog.i("walle", "[stateless] build envelope, isEncryptEnabled is false");
            a = c.a(context, UMUtils.getAppkey(context), bArr);
        }
        return a;
    }

    public static void setEncryptEnabled(boolean z) {
        isEncryptEnabled = z;
    }
}