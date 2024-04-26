package com.alipay.sdk.auth;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class h implements Runnable {
    final /* synthetic */ Activity a;
    final /* synthetic */ StringBuilder b;
    final /* synthetic */ APAuthInfo c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(Activity activity, StringBuilder sb, APAuthInfo aPAuthInfo) {
        this.a = activity;
        this.b = sb;
        this.c = aPAuthInfo;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.alipay.sdk.widget.a aVar;
        com.alipay.sdk.widget.a aVar2;
        com.alipay.sdk.widget.a aVar3;
        com.alipay.sdk.widget.a aVar4;
        com.alipay.sdk.packet.b bVar;
        com.alipay.sdk.widget.a aVar5;
        String str;
        String str2;
        com.alipay.sdk.widget.a aVar6;
        String str3;
        com.alipay.sdk.widget.a aVar7;
        com.alipay.sdk.widget.a aVar8;
        String str4;
        com.alipay.sdk.widget.a aVar9;
        com.alipay.sdk.widget.a aVar10;
        com.alipay.sdk.widget.a aVar11;
        try {
            try {
                bVar = new com.alipay.sdk.packet.impl.a().a(this.a, this.b.toString());
            } catch (Throwable th) {
                com.alipay.sdk.util.c.a("msp", th);
                bVar = null;
            }
            aVar5 = g.c;
            if (aVar5 != null) {
                aVar11 = g.c;
                aVar11.c();
                com.alipay.sdk.widget.a unused = g.c = null;
            }
        } catch (Exception unused2) {
            aVar3 = g.c;
            if (aVar3 == null) {
                return;
            }
        } catch (Throwable th2) {
            aVar = g.c;
            if (aVar != null) {
                aVar2 = g.c;
                aVar2.c();
            }
            throw th2;
        }
        if (bVar == null) {
            String unused3 = g.d = this.c.getRedirectUri() + "?resultCode=202";
            Activity activity = this.a;
            str4 = g.d;
            g.a(activity, str4);
            aVar9 = g.c;
            if (aVar9 != null) {
                aVar10 = g.c;
                aVar10.c();
                return;
            }
            return;
        }
        List<com.alipay.sdk.protocol.b> a = com.alipay.sdk.protocol.b.a(bVar.c().optJSONObject(com.alipay.sdk.cons.c.c).optJSONObject(com.alipay.sdk.cons.c.d));
        int i = 0;
        while (true) {
            if (i >= a.size()) {
                break;
            } else if (a.get(i).b() == com.alipay.sdk.protocol.a.WapPay) {
                String unused4 = g.d = a.get(i).c()[0];
                break;
            } else {
                i++;
            }
        }
        str = g.d;
        if (!TextUtils.isEmpty(str)) {
            Intent intent = new Intent(this.a, AuthActivity.class);
            str2 = g.d;
            intent.putExtra("params", str2);
            intent.putExtra("redirectUri", this.c.getRedirectUri());
            this.a.startActivity(intent);
            aVar6 = g.c;
            if (aVar6 == null) {
                return;
            }
            aVar4 = g.c;
            aVar4.c();
            return;
        }
        String unused5 = g.d = this.c.getRedirectUri() + "?resultCode=202";
        Activity activity2 = this.a;
        str3 = g.d;
        g.a(activity2, str3);
        aVar7 = g.c;
        if (aVar7 != null) {
            aVar8 = g.c;
            aVar8.c();
        }
    }
}