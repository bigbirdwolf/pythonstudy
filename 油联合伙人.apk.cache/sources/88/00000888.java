package com.alibaba.sdk.android.httpdns;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* loaded from: classes.dex */
public class j {
    private static Context b = null;
    private static String c = null;

    /* renamed from: c  reason: collision with other field name */
    static boolean f6c = false;

    /* JADX INFO: Access modifiers changed from: private */
    public static String b() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) b.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return "None_Network";
        }
        String typeName = activeNetworkInfo.getTypeName();
        e.d("[detectCurrentNetwork] - Network name:" + typeName + " subType name: " + activeNetworkInfo.getSubtypeName());
        return typeName == null ? "None_Network" : typeName;
    }

    public static void setContext(Context context) {
        if (context == null) {
            throw new IllegalStateException("Context can't be null");
        }
        b = context;
        k kVar = new k();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        b.registerReceiver(kVar, intentFilter);
    }
}