package com.alipay.sdk.app;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import com.alipay.sdk.util.n;

/* loaded from: classes.dex */
public class H5PayActivity extends Activity {
    private com.alipay.sdk.widget.g a;
    private String b;
    private String c;
    private String d;
    private String e;
    private boolean f;
    private String g;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        b();
        super.onCreate(bundle);
        try {
            Bundle extras = getIntent().getExtras();
            this.b = extras.getString("url", null);
            if (!n.f(this.b)) {
                finish();
                return;
            }
            this.d = extras.getString("cookie", null);
            this.c = extras.getString(com.alipay.sdk.packet.e.q, null);
            this.e = extras.getString("title", null);
            this.g = extras.getString("version", "v1");
            this.f = extras.getBoolean("backisexit", false);
            try {
                if ("v2".equals(this.g)) {
                    com.alipay.sdk.widget.j jVar = new com.alipay.sdk.widget.j(this);
                    setContentView(jVar);
                    jVar.a(this.e, this.c, this.f);
                    jVar.a(this.b);
                    this.a = jVar;
                    return;
                }
                this.a = new com.alipay.sdk.widget.h(this);
                setContentView(this.a);
                this.a.a(this.b, this.d);
                this.a.a(this.b);
            } catch (Throwable th) {
                com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, "GetInstalledAppEx", th);
                finish();
            }
        } catch (Exception unused) {
            finish();
        }
    }

    private void b() {
        try {
            super.requestWindowFeature(1);
        } catch (Throwable th) {
            com.alipay.sdk.util.c.a(th);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.a instanceof com.alipay.sdk.widget.h) {
            this.a.b();
            return;
        }
        if (!this.a.b()) {
            super.onBackPressed();
        }
        j.a(j.c());
        finish();
    }

    @Override // android.app.Activity
    public void finish() {
        a();
        super.finish();
    }

    public void a() {
        Object obj = PayTask.a;
        synchronized (obj) {
            try {
                obj.notify();
            } catch (Exception unused) {
            }
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.a.a();
    }
}