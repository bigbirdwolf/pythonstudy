package com.alipay.sdk.data;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.util.j;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class a {
    public static final int a = 3500;
    public static final String b = "https://h5.m.taobao.com/mlapp/olist.html";
    public static final int c = 10;
    public static final boolean d = true;
    public static final boolean e = true;
    public static final int f = 1000;
    public static final int g = 20000;
    public static final String h = "alipay_cashier_dynamic_config";
    public static final String i = "timeout";
    public static final String j = "st_sdk_config";
    public static final String k = "tbreturl";
    public static final String l = "launchAppSwitch";
    public static final String m = "configQueryInterval";
    public static final String n = "scheme_pay";
    public static final String o = "intercept_batch";
    private static a w;
    private int q = a;
    private String r = b;
    private int s = 10;
    private boolean t = true;
    private boolean u = true;
    public boolean p = false;
    private List<C0006a> v = null;

    public int a() {
        if (this.q < 1000 || this.q > 20000) {
            com.alipay.sdk.util.c.b("", "DynamicConfig::getJumpTimeout(default) >3500");
            return a;
        }
        com.alipay.sdk.util.c.b("", "DynamicConfig::getJumpTimeout >" + this.q);
        return this.q;
    }

    public boolean b() {
        return this.t;
    }

    public boolean c() {
        return this.u;
    }

    public String d() {
        return this.r;
    }

    public int e() {
        return this.s;
    }

    public List<C0006a> f() {
        return this.v;
    }

    public void a(boolean z) {
        this.p = z;
    }

    public static a g() {
        if (w == null) {
            w = new a();
            w.h();
        }
        return w;
    }

    private void h() {
        a(j.b(com.alipay.sdk.sys.b.a().b(), h, null));
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.q = jSONObject.optInt(i, a);
            this.r = jSONObject.optString(k, b).trim();
            this.s = jSONObject.optInt(m, 10);
            this.v = C0006a.a(jSONObject.optJSONArray(l));
            this.t = jSONObject.optBoolean(n, true);
            this.u = jSONObject.optBoolean(o, true);
        } catch (Throwable th) {
            com.alipay.sdk.util.c.a(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(i, a());
            jSONObject.put(k, d());
            jSONObject.put(m, e());
            jSONObject.put(l, C0006a.a(f()));
            jSONObject.put(n, b());
            jSONObject.put(o, c());
            j.a(com.alipay.sdk.sys.b.a().b(), h, jSONObject.toString());
        } catch (Exception e2) {
            com.alipay.sdk.util.c.a(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject optJSONObject = new JSONObject(str).optJSONObject(j);
            if (optJSONObject != null) {
                this.q = optJSONObject.optInt(i, a);
                this.r = optJSONObject.optString(k, b).trim();
                this.s = optJSONObject.optInt(m, 10);
                this.v = C0006a.a(optJSONObject.optJSONArray(l));
                this.t = optJSONObject.optBoolean(n, true);
                this.u = optJSONObject.optBoolean(o, true);
            } else {
                com.alipay.sdk.util.c.d("msp", "config is null");
            }
        } catch (Throwable th) {
            com.alipay.sdk.util.c.a(th);
        }
    }

    public void a(Context context) {
        new Thread(new b(this, context)).start();
    }

    /* renamed from: com.alipay.sdk.data.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0006a {
        public final String a;
        public final int b;
        public final String c;

        public C0006a(String str, int i, String str2) {
            this.a = str;
            this.b = i;
            this.c = str2;
        }

        public static C0006a a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            return new C0006a(jSONObject.optString(com.umeng.analytics.pro.b.ad), jSONObject.optInt("v", 0), jSONObject.optString("pk"));
        }

        public static List<C0006a> a(JSONArray jSONArray) {
            if (jSONArray == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                C0006a a = a(jSONArray.optJSONObject(i));
                if (a != null) {
                    arrayList.add(a);
                }
            }
            return arrayList;
        }

        public static JSONObject a(C0006a c0006a) {
            if (c0006a == null) {
                return null;
            }
            try {
                return new JSONObject().put(com.umeng.analytics.pro.b.ad, c0006a.a).put("v", c0006a.b).put("pk", c0006a.c);
            } catch (JSONException e) {
                com.alipay.sdk.util.c.a(e);
                return null;
            }
        }

        public static JSONArray a(List<C0006a> list) {
            if (list == null) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            for (C0006a c0006a : list) {
                jSONArray.put(a(c0006a));
            }
            return jSONArray;
        }

        public String toString() {
            return String.valueOf(a(this));
        }
    }
}