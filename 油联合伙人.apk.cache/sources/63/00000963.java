package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class PayResultActivity extends Activity {
    public static final String a = "{\"isLogin\":\"false\"}";
    public static final HashMap<String, Object> b = new HashMap<>();
    public static final String c = "hk.alipay.wallet";
    public static final String d = "phonecashier.pay.hash";
    public static final String e = "orderSuffix";
    public static final String f = "externalPkgName";
    public static final String g = "phonecashier.pay.result";
    public static final String h = "phonecashier.pay.resultOrderHash";
    private static String i;

    /* loaded from: classes.dex */
    public static final class a {
        public static volatile String a;
        public static volatile String b;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (!TextUtils.isEmpty(intent.getStringExtra(e))) {
            a.a = intent.getStringExtra(d);
            a(this, a.a, intent.getStringExtra(e), intent.getStringExtra(f));
            a(this, 300);
            return;
        }
        String stringExtra = intent.getStringExtra(g);
        int intExtra = intent.getIntExtra(h, 0);
        if (intExtra == 0 || !TextUtils.equals(a.a, String.valueOf(intExtra))) {
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.P, "Expected " + a.a + ", got " + intExtra);
            a(a.a);
            a(this, 300);
            return;
        }
        if (!TextUtils.isEmpty(stringExtra)) {
            a(stringExtra, a.a);
        } else {
            a(a.a);
        }
        a.a = "";
        a(this, 300);
    }

    private static void a(Activity activity, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        Intent intent = new Intent();
        try {
            intent.setPackage(c);
            intent.setData(Uri.parse("alipayhk://platformapi/startApp?appId=20000125&schemePaySession=" + URLEncoder.encode(str, "UTF-8") + "&orderSuffix=" + URLEncoder.encode(str2, "UTF-8") + "&packageName=" + URLEncoder.encode(str3, "UTF-8") + "&externalPkgName=" + URLEncoder.encode(str3, "UTF-8")));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            com.alipay.sdk.util.c.b("msp", "PayTask:payReuslt: UnsupportedEncodingException:" + e2);
        }
        if (activity != null) {
            activity.startActivity(intent);
        }
    }

    private static void a(String str) {
        a.b = j.c();
        a(b, str);
    }

    private static void a(String str, String str2) {
        a.b = str;
        a(b, str2);
    }

    private static void a(Activity activity, int i2) {
        new Handler().postDelayed(new f(activity), i2);
    }

    private static boolean a(HashMap<String, Object> hashMap, String str) {
        Object obj;
        if (hashMap == null || str == null || (obj = hashMap.get(str)) == null) {
            return false;
        }
        synchronized (obj) {
            obj.notifyAll();
        }
        return true;
    }
}