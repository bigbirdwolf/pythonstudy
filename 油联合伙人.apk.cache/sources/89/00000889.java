package com.alibaba.sdk.android.httpdns;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class k extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String b;
        String str;
        if (!isInitialStickyBroadcast() && intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            b = j.b();
            if (b != "None_Network") {
                str = j.c;
                if (!b.equalsIgnoreCase(str)) {
                    e.d("[BroadcastReceiver.onReceive] - Network state changed");
                    ArrayList m8a = a.a().m8a();
                    a.a().clear();
                    if (j.f6c && HttpDns.instance != null) {
                        e.d("[BroadcastReceiver.onReceive] - refresh host");
                        HttpDns.instance.setPreResolveHosts(m8a);
                    }
                }
            }
            String unused = j.c = b;
        }
    }
}