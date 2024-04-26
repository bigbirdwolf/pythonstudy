package com.alibaba.sdk.android.httpdns;

import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
class b {
    private long a;

    /* renamed from: a  reason: collision with other field name */
    private String f2a;

    /* renamed from: a  reason: collision with other field name */
    private String[] f3a;
    private long b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f2a = jSONObject.getString(com.alipay.sdk.cons.c.f);
            JSONArray jSONArray = jSONObject.getJSONArray("ips");
            int length = jSONArray.length();
            this.f3a = new String[length];
            for (int i = 0; i < length; i++) {
                this.f3a[i] = jSONArray.getString(i);
            }
            this.a = jSONObject.getLong("ttl");
            this.b = System.currentTimeMillis() / 1000;
        } catch (Exception e) {
            e.a(e);
        }
    }

    long a() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a  reason: collision with other method in class */
    public boolean m11a() {
        return b() + a() < System.currentTimeMillis() / 1000;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a  reason: collision with other method in class */
    public String[] m12a() {
        return this.f3a;
    }

    long b() {
        return this.b;
    }

    public String toString() {
        String str = "host: " + this.f2a + " ip cnt: " + this.f3a.length + " ttl: " + this.a;
        for (int i = 0; i < this.f3a.length; i++) {
            str = str + "\n ip: " + this.f3a[i];
        }
        return str;
    }
}