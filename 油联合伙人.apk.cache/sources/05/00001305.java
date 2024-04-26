package com.umeng.commonsdk.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.proguard.e;
import com.umeng.commonsdk.statistics.common.ULog;
import org.json.JSONObject;

/* compiled from: UMInternalDataProtocol.java */
/* loaded from: classes.dex */
public class c implements UMLogDataProtocol {
    private static final String b = "info";
    private static final String c = "stat";
    private Context a;

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public void removeCacheData(Object obj) {
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public JSONObject setupReportData(long j) {
        return null;
    }

    public c(Context context) {
        if (context != null) {
            this.a = context.getApplicationContext();
        }
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public void workEvent(Object obj, int i) {
        ULog.i("walle", "[internal] workEvent");
        try {
            switch (i) {
                case a.e /* 32769 */:
                    ULog.i("walle", "[internal] workEvent send envelope");
                    Class<?> cls = Class.forName("com.umeng.commonsdk.internal.UMInternalManagerAgent");
                    if (cls != null) {
                        cls.getMethod("sendInternalEnvelopeByStateful2", Context.class).invoke(cls, this.a);
                        break;
                    }
                    break;
                case a.f /* 32770 */:
                default:
                    return;
                case a.g /* 32771 */:
                    ULog.i("walle", "[internal] workEvent cache battery, event is " + obj.toString());
                    Class<?> cls2 = Class.forName("com.umeng.commonsdk.internal.utils.UMInternalUtilsAgent");
                    if (cls2 != null) {
                        cls2.getMethod("saveBattery", Context.class, String.class).invoke(cls2, this.a, (String) obj);
                        break;
                    }
                    break;
                case a.h /* 32772 */:
                    ULog.i("walle", "[internal] workEvent cache station, event is " + obj.toString());
                    Class<?> cls3 = Class.forName("com.umeng.commonsdk.internal.utils.UMInternalUtilsAgent");
                    if (cls3 != null) {
                        cls3.getMethod("saveBaseStationStrength", Context.class, String.class).invoke(cls3, this.a, (String) obj);
                        break;
                    }
                    break;
                case a.i /* 32773 */:
                    Class<?> cls4 = Class.forName("com.umeng.commonsdk.internal.utils.InfoPreferenceAgent");
                    if (cls4 != null) {
                        cls4.getMethod("saveBluetoothInfo", Context.class, Object.class).invoke(cls4, this.a, obj);
                        return;
                    }
                    return;
                case a.j /* 32774 */:
                    Class<?> cls5 = Class.forName("com.umeng.commonsdk.internal.utils.ApplicationLayerUtilAgent");
                    if (cls5 != null) {
                        cls5.getMethod("wifiChange", Context.class).invoke(cls5, this.a);
                        return;
                    }
                    return;
                case a.k /* 32775 */:
                    Class<?> cls6 = Class.forName("com.umeng.commonsdk.internal.utils.InfoPreferenceAgent");
                    if (cls6 != null) {
                        cls6.getMethod("saveUA", Context.class, String.class).invoke(cls6, this.a, (String) obj);
                        return;
                    }
                    return;
                case a.l /* 32776 */:
                    SharedPreferences sharedPreferences = this.a.getApplicationContext().getSharedPreferences(b, 0);
                    if (sharedPreferences != null) {
                        sharedPreferences.edit().putString(c, (String) obj).commit();
                        return;
                    }
                    return;
                case a.m /* 32777 */:
                    ULog.i("walle", "[internal] workEvent send envelope");
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(e.aw, a.d);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(e.ak, new JSONObject());
                    JSONObject buildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(this.a, jSONObject, jSONObject2);
                    if (buildEnvelopeWithExtHeader != null && !buildEnvelopeWithExtHeader.has("exception")) {
                        ULog.i("walle", "[internal] workEvent send envelope back, result is ok");
                        break;
                    }
                    break;
            }
        } catch (Throwable unused) {
        }
    }
}