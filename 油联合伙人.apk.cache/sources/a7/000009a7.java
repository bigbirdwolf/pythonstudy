package com.alipay.sdk.sys;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.alipay.sdk.util.c;
import com.alipay.sdk.util.n;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class a {
    public static final String a = "\"&";
    public static final String b = "&";
    public static final String c = "bizcontext=\"";
    public static final String d = "bizcontext=";
    public static final String e = "\"";
    public static final String f = "appkey";
    public static final String g = "ty";
    public static final String h = "sv";
    public static final String i = "an";
    public static final String j = "setting";
    public static final String k = "av";
    public static final String l = "sdk_start_time";
    public static final String m = "UTF-8";
    private String n;
    private String o;
    private Context p;

    public a(Context context) {
        this.n = "";
        this.o = "";
        this.p = null;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.n = packageInfo.versionName;
            this.o = packageInfo.packageName;
            this.p = context.getApplicationContext();
        } catch (Exception unused) {
        }
    }

    public String a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("new_external_info==")) {
            return str;
        }
        if (b(str)) {
            return c(str);
        }
        return d(str);
    }

    private boolean b(String str) {
        return !str.contains(a);
    }

    private String c(String str) {
        String str2;
        try {
            String a2 = a(str, b, d);
            if (TextUtils.isEmpty(a2)) {
                str2 = str + b + b(d, "");
            } else {
                int indexOf = str.indexOf(a2);
                str2 = str.substring(0, indexOf) + a(a2, d, "", true) + str.substring(indexOf + a2.length());
            }
            return str2;
        } catch (Throwable unused) {
            return str;
        }
    }

    private String d(String str) {
        String str2;
        try {
            String a2 = a(str, a, c);
            if (TextUtils.isEmpty(a2)) {
                str2 = str + b + b(c, "\"");
            } else {
                if (!a2.endsWith("\"")) {
                    a2 = a2 + "\"";
                }
                int indexOf = str.indexOf(a2);
                str2 = str.substring(0, indexOf) + a(a2, c, "\"", false) + str.substring(indexOf + a2.length());
            }
            return str2;
        } catch (Throwable unused) {
            return str;
        }
    }

    private String a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(str2);
        for (int i2 = 0; i2 < split.length; i2++) {
            if (!TextUtils.isEmpty(split[i2]) && split[i2].startsWith(str3)) {
                return split[i2];
            }
        }
        return null;
    }

    private String b(String str, String str2) throws JSONException, UnsupportedEncodingException {
        String a2 = a("", "");
        return str + a2 + str2;
    }

    public String a(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appkey", com.alipay.sdk.cons.a.d);
            jSONObject.put(g, "and_lite");
            jSONObject.put(h, "h.a.3.6.2");
            if (!this.o.contains(j) || !n.b(this.p)) {
                jSONObject.put(i, this.o);
            }
            jSONObject.put(k, this.n);
            jSONObject.put(l, System.currentTimeMillis());
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put(str, str2);
            }
            return jSONObject.toString();
        } catch (Throwable th) {
            c.a(th);
            return "";
        }
    }

    private String a(String str, String str2, String str3, boolean z) throws JSONException, UnsupportedEncodingException {
        String substring = str.substring(str2.length());
        JSONObject jSONObject = new JSONObject(substring.substring(0, substring.length() - str3.length()));
        if (!jSONObject.has("appkey")) {
            jSONObject.put("appkey", com.alipay.sdk.cons.a.d);
        }
        if (!jSONObject.has(g)) {
            jSONObject.put(g, "and_lite");
        }
        if (!jSONObject.has(h)) {
            jSONObject.put(h, "h.a.3.6.2");
        }
        if (!jSONObject.has(i) && (!this.o.contains(j) || !n.b(this.p))) {
            jSONObject.put(i, this.o);
        }
        if (!jSONObject.has(k)) {
            jSONObject.put(k, this.n);
        }
        if (!jSONObject.has(l)) {
            jSONObject.put(l, System.currentTimeMillis());
        }
        String jSONObject2 = jSONObject.toString();
        return str2 + jSONObject2 + str3;
    }
}