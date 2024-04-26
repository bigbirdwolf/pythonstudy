package com.umeng.commonsdk;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.framework.UMModuleRegister;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.internal.d;
import com.umeng.commonsdk.internal.utils.b;
import com.umeng.commonsdk.internal.utils.j;
import com.umeng.commonsdk.internal.utils.l;
import com.umeng.commonsdk.proguard.c;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.commonsdk.stateless.f;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class UMConfigureImpl {
    private static boolean a = false;
    private static boolean b = false;

    public static void init(Context context) {
        if (context == null) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        b(applicationContext);
        a(applicationContext);
    }

    private static synchronized void a(final Context context) {
        synchronized (UMConfigureImpl.class) {
            if (context != null) {
                try {
                    if (!b) {
                        String currentProcessName = UMFrUtils.getCurrentProcessName(context);
                        String packageName = context.getPackageName();
                        if (!TextUtils.isEmpty(currentProcessName) && !TextUtils.isEmpty(packageName) && currentProcessName.equals(packageName)) {
                            new Thread(new Runnable() { // from class: com.umeng.commonsdk.UMConfigureImpl.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    try {
                                        JSONArray b2 = c.b(context);
                                        if (b2 == null || b2.length() <= 0) {
                                            return;
                                        }
                                        f.a(context, context.getFilesDir() + "/" + com.umeng.commonsdk.stateless.a.e + "/" + Base64.encodeToString(com.umeng.commonsdk.internal.a.n.getBytes(), 0), 10);
                                        JSONObject jSONObject = new JSONObject();
                                        jSONObject.put("lbs", b2);
                                        JSONObject jSONObject2 = new JSONObject();
                                        jSONObject2.put(SocializeProtocolConstants.PROTOCOL_KEY_REQUEST_TYPE, jSONObject);
                                        UMSLEnvelopeBuild uMSLEnvelopeBuild = new UMSLEnvelopeBuild();
                                        uMSLEnvelopeBuild.buildSLEnvelope(context, uMSLEnvelopeBuild.buildSLBaseHeader(context), jSONObject2, com.umeng.commonsdk.internal.a.n);
                                    } catch (Exception e) {
                                        UMCrashManager.reportCrash(context, e);
                                    }
                                }
                            }).start();
                        }
                        b = true;
                    }
                } finally {
                }
            }
        }
    }

    private static synchronized void b(final Context context) {
        synchronized (UMConfigureImpl.class) {
            if (context != null) {
                try {
                    if (!a) {
                        new Thread(new Runnable() { // from class: com.umeng.commonsdk.UMConfigureImpl.2
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    String currentProcessName = UMFrUtils.getCurrentProcessName(context);
                                    String packageName = context.getPackageName();
                                    if (TextUtils.isEmpty(currentProcessName) || TextUtils.isEmpty(packageName) || !currentProcessName.equals(packageName)) {
                                        return;
                                    }
                                    com.umeng.commonsdk.proguard.a.a(context);
                                    c.a(context);
                                    if (!com.umeng.commonsdk.internal.utils.c.a(context).a()) {
                                        com.umeng.commonsdk.internal.utils.c.a(context).b();
                                    }
                                    l.b(context);
                                    com.umeng.commonsdk.internal.utils.a.n(context);
                                    com.umeng.commonsdk.internal.utils.a.d(context);
                                    j.b(context);
                                    d.b(context);
                                    try {
                                        d.c(context);
                                    } catch (Throwable unused) {
                                    }
                                } catch (Throwable th) {
                                    UMCrashManager.reportCrash(context, th);
                                }
                            }
                        }).start();
                        if (!b.a(context).a()) {
                            b.a(context).b();
                        }
                        a = true;
                    }
                } catch (Throwable th) {
                    ULog.e(UMModuleRegister.INNER, "e is " + th.getMessage());
                    UMCrashManager.reportCrash(context, th);
                }
            }
        }
    }
}