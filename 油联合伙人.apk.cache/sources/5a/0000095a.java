package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import com.alipay.sdk.util.l;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AlipayResultActivity extends Activity {
    public static final int a = 9000;
    public static final int b = 5000;
    public static final int c = 4000;
    private static final Map<String, WeakReference<a>> e = new ConcurrentHashMap();
    public static final ConcurrentHashMap<String, WeakReference<b>> d = new ConcurrentHashMap<>();

    /* loaded from: classes.dex */
    public interface a {
        void a(int i, String str, Bundle bundle);
    }

    /* loaded from: classes.dex */
    public interface b {
        void a(int i, String str, String str2);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Bundle bundle2;
        super.onCreate(bundle);
        com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, "BSPReturned", "");
        Intent intent = getIntent();
        try {
            String stringExtra = intent.getStringExtra(com.umeng.analytics.pro.b.at);
            Bundle bundleExtra = intent.getBundleExtra("result");
            String stringExtra2 = intent.getStringExtra("scene");
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, "BSPSession", stringExtra);
            if (TextUtils.equals("mqpSchemePay", stringExtra2)) {
                a(stringExtra, bundleExtra);
                return;
            }
            if ((TextUtils.isEmpty(stringExtra) || bundleExtra == null) && intent.getData() != null) {
                try {
                    JSONObject jSONObject = new JSONObject(new String(Base64.decode(intent.getData().getQuery(), 2), "UTF-8"));
                    JSONObject jSONObject2 = jSONObject.getJSONObject("result");
                    String string = jSONObject.getString(com.umeng.analytics.pro.b.at);
                    try {
                        com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, "BSPUriSession", string);
                        bundle2 = new Bundle();
                    } catch (Throwable th) {
                        stringExtra = string;
                        th = th;
                    }
                    try {
                        Iterator<String> keys = jSONObject2.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            bundle2.putString(next, jSONObject2.getString(next));
                        }
                        bundleExtra = bundle2;
                        stringExtra = string;
                    } catch (Throwable th2) {
                        stringExtra = string;
                        th = th2;
                        bundleExtra = bundle2;
                        com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, "BSPResEx", th);
                        if (TextUtils.isEmpty(stringExtra)) {
                        }
                        return;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            if (!TextUtils.isEmpty(stringExtra) || bundleExtra == null) {
                return;
            }
            try {
                a(stringExtra, a, "OK", bundleExtra);
            } finally {
                com.alipay.sdk.app.statistic.a.b(this, "");
                finish();
            }
        } catch (Throwable th4) {
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, "BSPSerError", th4);
            finish();
        }
    }

    static void a(String str, int i, String str2, Bundle bundle) {
        WeakReference<a> weakReference = e.get(str);
        if (weakReference == null) {
            return;
        }
        e.remove(str);
        a aVar = weakReference.get();
        if (aVar != null) {
            aVar.a(i, str2, bundle);
        }
    }

    private void a(String str, Bundle bundle) {
        b bVar;
        WeakReference<b> weakReference = d.get(str);
        if (weakReference != null) {
            bVar = weakReference.get();
            d.remove(str);
        } else {
            bVar = null;
        }
        if (bVar == null) {
            return;
        }
        try {
            bVar.a(bundle.getInt("endCode"), bundle.getString(l.b), bundle.getString("result"));
        } finally {
            finish();
        }
    }
}