package com.umeng.commonsdk.internal.crash;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.commonsdk.stateless.f;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class UMCrashManager {
    private static boolean isReportCrash = false;
    private static Object mObject = new Object();

    public static void reportCrash(final Context context, final Throwable th) {
        if (isReportCrash) {
            return;
        }
        ULog.i("walle-crash", "report is " + isReportCrash);
        new Thread(new Runnable() { // from class: com.umeng.commonsdk.internal.crash.UMCrashManager.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    synchronized (UMCrashManager.mObject) {
                        if (context != null && th != null && !UMCrashManager.isReportCrash) {
                            boolean unused = UMCrashManager.isReportCrash = true;
                            ULog.i("walle-crash", "report thread is " + UMCrashManager.isReportCrash);
                            String a = a.a(th);
                            if (!TextUtils.isEmpty(a)) {
                                f.a(context, context.getFilesDir() + "/" + com.umeng.commonsdk.stateless.a.e + "/" + Base64.encodeToString(com.umeng.commonsdk.internal.a.a.getBytes(), 0), 10);
                                UMSLEnvelopeBuild uMSLEnvelopeBuild = new UMSLEnvelopeBuild();
                                JSONObject buildSLBaseHeader = uMSLEnvelopeBuild.buildSLBaseHeader(context);
                                try {
                                    JSONObject jSONObject = new JSONObject();
                                    jSONObject.put("content", a);
                                    jSONObject.put("ts", System.currentTimeMillis());
                                    JSONObject jSONObject2 = new JSONObject();
                                    jSONObject2.put("crash", jSONObject);
                                    JSONObject jSONObject3 = new JSONObject();
                                    jSONObject3.put(SocializeProtocolConstants.PROTOCOL_KEY_REQUEST_TYPE, jSONObject2);
                                    JSONObject buildSLEnvelope = uMSLEnvelopeBuild.buildSLEnvelope(context, buildSLBaseHeader, jSONObject3, com.umeng.commonsdk.internal.a.a);
                                    if (buildSLEnvelope != null) {
                                        buildSLEnvelope.has("exception");
                                    }
                                } catch (JSONException unused2) {
                                }
                            }
                        }
                    }
                } catch (Throwable unused3) {
                }
            }
        }).start();
    }
}