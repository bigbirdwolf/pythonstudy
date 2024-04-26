package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.data.a;
import com.alipay.sdk.util.e;
import com.alipay.sdk.util.l;
import com.alipay.sdk.util.n;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class AuthTask {
    static final Object a = com.alipay.sdk.util.e.class;
    private Activity b;
    private com.alipay.sdk.widget.a c;

    public AuthTask(Activity activity) {
        this.b = activity;
        com.alipay.sdk.sys.b.a().a(this.b, com.alipay.sdk.data.c.b());
        com.alipay.sdk.app.statistic.a.a(activity);
        this.c = new com.alipay.sdk.widget.a(activity, com.alipay.sdk.widget.a.c);
    }

    private e.a a() {
        return new a(this);
    }

    private void b() {
        if (this.c != null) {
            this.c.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.c != null) {
            this.c.c();
        }
    }

    public synchronized Map<String, String> authV2(String str, boolean z) {
        return l.a(auth(str, z));
    }

    public synchronized String auth(String str, boolean z) {
        String c;
        if (z) {
            try {
                b();
            } catch (Throwable th) {
                throw th;
            }
        }
        com.alipay.sdk.sys.b.a().a(this.b, com.alipay.sdk.data.c.b());
        c = j.c();
        i.a("");
        try {
            String a2 = a(this.b, str);
            com.alipay.sdk.data.a.g().a(this.b);
            c();
            com.alipay.sdk.app.statistic.a.b(this.b, str);
            c = a2;
        } catch (Exception e) {
            com.alipay.sdk.util.c.a(e);
            com.alipay.sdk.data.a.g().a(this.b);
            c();
            com.alipay.sdk.app.statistic.a.b(this.b, str);
        }
        return c;
    }

    private String a(Activity activity, String str) {
        String a2 = new com.alipay.sdk.sys.a(this.b).a(str);
        List<a.C0006a> f = com.alipay.sdk.data.a.g().f();
        if (!com.alipay.sdk.data.a.g().p || f == null) {
            f = i.a;
        }
        if (n.b(this.b, f)) {
            String a3 = new com.alipay.sdk.util.e(activity, a()).a(a2);
            if (!TextUtils.equals(a3, com.alipay.sdk.util.e.a) && !TextUtils.equals(a3, com.alipay.sdk.util.e.b)) {
                return TextUtils.isEmpty(a3) ? j.c() : a3;
            }
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.M, "");
            return b(activity, a2);
        }
        com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.N, "");
        return b(activity, a2);
    }

    private String b(Activity activity, String str) {
        k kVar;
        b();
        try {
            try {
                List<com.alipay.sdk.protocol.b> a2 = com.alipay.sdk.protocol.b.a(new com.alipay.sdk.packet.impl.a().a(activity, str).c().optJSONObject(com.alipay.sdk.cons.c.c).optJSONObject(com.alipay.sdk.cons.c.d));
                c();
                for (int i = 0; i < a2.size(); i++) {
                    if (a2.get(i).b() == com.alipay.sdk.protocol.a.WapPay) {
                        return a(a2.get(i));
                    }
                }
            } catch (IOException e) {
                k b = k.b(k.NETWORK_ERROR.a());
                com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.a, e);
                c();
                kVar = b;
            } catch (Throwable th) {
                com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.t, th);
            }
            c();
            kVar = null;
            if (kVar == null) {
                kVar = k.b(k.FAILED.a());
            }
            return j.a(kVar.a(), kVar.b(), "");
        } finally {
            c();
        }
    }

    private String a(com.alipay.sdk.protocol.b bVar) {
        String[] c = bVar.c();
        Bundle bundle = new Bundle();
        bundle.putString("url", c[0]);
        Intent intent = new Intent(this.b, H5AuthActivity.class);
        intent.putExtras(bundle);
        this.b.startActivity(intent);
        synchronized (a) {
            try {
                a.wait();
            } catch (InterruptedException unused) {
                return j.c();
            }
        }
        String a2 = j.a();
        return TextUtils.isEmpty(a2) ? j.c() : a2;
    }
}