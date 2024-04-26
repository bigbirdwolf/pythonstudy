package com.alipay.apmobilesecuritysdk.e;

import android.content.Context;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class e {
    public static f a(Context context) {
        if (context == null) {
            return null;
        }
        String a = com.alipay.apmobilesecuritysdk.f.a.a(context, "device_feature_prefs_name", "device_feature_prefs_key");
        if (com.alipay.security.mobile.module.a.a.a(a)) {
            a = com.alipay.apmobilesecuritysdk.f.a.a("device_feature_file_name", "device_feature_file_key");
        }
        if (com.alipay.security.mobile.module.a.a.a(a)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(a);
            f fVar = new f();
            fVar.a(jSONObject.getString("imei"));
            fVar.b(jSONObject.getString("imsi"));
            fVar.c(jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_MAC));
            fVar.d(jSONObject.getString(com.umeng.commonsdk.proguard.e.X));
            fVar.e(jSONObject.getString("gsi"));
            return fVar;
        } catch (Exception e) {
            com.alipay.apmobilesecuritysdk.c.a.a(e);
            return null;
        }
    }
}