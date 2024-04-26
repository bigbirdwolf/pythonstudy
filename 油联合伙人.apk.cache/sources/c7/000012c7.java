package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;

/* compiled from: SessionIdGenerateServiceImpl.java */
/* loaded from: classes.dex */
class t implements s {
    private long a = AnalyticsConfig.kContinueSessionMillis;

    @Override // com.umeng.analytics.pro.s
    public void a(long j) {
        this.a = j;
    }

    @Override // com.umeng.analytics.pro.s
    public long a() {
        return this.a;
    }

    @Override // com.umeng.analytics.pro.s
    public String a(Context context) {
        String deviceId = DeviceConfig.getDeviceId(context);
        String appkey = UMUtils.getAppkey(context);
        long currentTimeMillis = System.currentTimeMillis();
        if (appkey == null) {
            throw new RuntimeException("Appkey is null or empty, Please check!");
        }
        return UMUtils.MD5(currentTimeMillis + appkey + deviceId);
    }

    @Override // com.umeng.analytics.pro.s
    public boolean a(long j, long j2) {
        long currentTimeMillis = System.currentTimeMillis();
        return (j == 0 || currentTimeMillis - j >= this.a) && j2 > 0 && currentTimeMillis - j2 > this.a;
    }

    @Override // com.umeng.analytics.pro.s
    public void a(Context context, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            SharedPreferences.Editor edit = PreferenceWrapper.getDefault(context).edit();
            edit.putString(q.c, str);
            edit.putLong(q.b, 0L);
            edit.putLong(q.e, currentTimeMillis);
            edit.putLong(q.f, 0L);
            edit.commit();
        } catch (Exception unused) {
        }
    }
}