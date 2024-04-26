package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.alipay.sdk.app.PayResultActivity;
import com.alipay.sdk.data.a;
import com.alipay.sdk.util.H5PayResultModel;
import com.alipay.sdk.util.e;
import com.alipay.sdk.util.l;
import com.alipay.sdk.util.n;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class PayTask {
    static final Object a = com.alipay.sdk.util.e.class;
    private static long h = 0;
    private static final long i = 3000;
    private static long j = -1;
    private Activity b;
    private com.alipay.sdk.widget.a c;
    private String d = "wappaygw.alipay.com/service/rest.htm";
    private String e = "mclient.alipay.com/service/rest.htm";
    private String f = "mclient.alipay.com/home/exterfaceAssign.htm";
    private Map<String, a> g = new HashMap();

    public String getVersion() {
        return "15.6.2";
    }

    public PayTask(Activity activity) {
        this.b = activity;
        com.alipay.sdk.sys.b.a().a(this.b, com.alipay.sdk.data.c.b());
        com.alipay.sdk.app.statistic.a.a(activity);
        this.c = new com.alipay.sdk.widget.a(activity, com.alipay.sdk.widget.a.b);
    }

    public synchronized String pay(String str, boolean z) {
        if (b()) {
            return j.d();
        }
        if (z) {
            showLoading();
        }
        if (str.contains("payment_inst=")) {
            String substring = str.substring(str.indexOf("payment_inst=") + 13);
            int indexOf = substring.indexOf(38);
            if (indexOf > 0) {
                substring = substring.substring(0, indexOf);
            }
            i.a(substring.replaceAll("\"", "").toLowerCase(Locale.getDefault()).replaceAll("alipay", ""));
        } else {
            i.a("");
        }
        if (str.contains(com.alipay.sdk.cons.a.r)) {
            com.alipay.sdk.cons.a.s = true;
        }
        if (com.alipay.sdk.cons.a.s) {
            if (str.startsWith(com.alipay.sdk.cons.a.t)) {
                str = str.substring(str.indexOf(com.alipay.sdk.cons.a.t) + com.alipay.sdk.cons.a.t.length());
            } else if (str.startsWith(com.alipay.sdk.cons.a.u)) {
                str = str.substring(str.indexOf(com.alipay.sdk.cons.a.u) + com.alipay.sdk.cons.a.u.length());
            }
        }
        String a2 = a(str);
        com.alipay.sdk.util.i.a(this.b.getApplicationContext(), a2);
        com.alipay.sdk.data.a.g().a(this.b.getApplicationContext());
        dismissLoading();
        com.alipay.sdk.app.statistic.a.b(this.b.getApplicationContext(), str);
        return a2;
    }

    public synchronized Map<String, String> payV2(String str, boolean z) {
        return l.a(pay(str, z));
    }

    public synchronized String fetchTradeToken() {
        return com.alipay.sdk.util.i.a(this.b.getApplicationContext());
    }

    public synchronized boolean payInterceptorWithUrl(String str, boolean z, H5PayCallback h5PayCallback) {
        String fetchOrderInfoFromH5PayUrl;
        fetchOrderInfoFromH5PayUrl = fetchOrderInfoFromH5PayUrl(str);
        if (!TextUtils.isEmpty(fetchOrderInfoFromH5PayUrl)) {
            new Thread(new g(this, fetchOrderInfoFromH5PayUrl, z, h5PayCallback)).start();
        }
        return !TextUtils.isEmpty(fetchOrderInfoFromH5PayUrl);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x00dc, code lost:
        if (r9.startsWith("http://" + r19.e) != false) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0179, code lost:
        if (r9.startsWith("http://" + r19.f) != false) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x003f, code lost:
        if (r9.startsWith("http://" + r19.d) != false) goto L95;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized java.lang.String fetchOrderInfoFromH5PayUrl(java.lang.String r20) {
        /*
            Method dump skipped, instructions count: 1222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.PayTask.fetchOrderInfoFromH5PayUrl(java.lang.String):java.lang.String");
    }

    private static final String a(String... strArr) {
        if (strArr == null) {
            return "";
        }
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return "";
    }

    public static synchronized boolean fetchSdkConfig(Context context) {
        synchronized (PayTask.class) {
            try {
                com.alipay.sdk.sys.b.a().a(context, com.alipay.sdk.data.c.b());
                long elapsedRealtime = SystemClock.elapsedRealtime() / 1000;
                if (elapsedRealtime - h < com.alipay.sdk.data.a.g().e()) {
                    return false;
                }
                h = elapsedRealtime;
                com.alipay.sdk.data.a.g().a(context.getApplicationContext());
                return true;
            } catch (Exception e) {
                com.alipay.sdk.util.c.a(e);
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class a {
        private String b;
        private String c;
        private String d;
        private String e;

        private a() {
            this.b = "";
            this.c = "";
            this.d = "";
            this.e = "";
        }

        /* synthetic */ a(PayTask payTask, g gVar) {
            this();
        }

        public String a() {
            return this.b;
        }

        public void a(String str) {
            this.b = str;
        }

        public String b() {
            return this.d;
        }

        public void b(String str) {
            this.d = str;
        }

        public String c() {
            return this.c;
        }

        public void c(String str) {
            this.c = str;
        }

        public String d() {
            return this.e;
        }

        public void d(String str) {
            this.e = str;
        }
    }

    private boolean a(boolean z, boolean z2, String str, StringBuilder sb, Map<String, String> map, String... strArr) {
        String str2 = "";
        int length = strArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            String str3 = strArr[i2];
            if (!TextUtils.isEmpty(map.get(str3))) {
                str2 = map.get(str3);
                break;
            }
            i2++;
        }
        if (TextUtils.isEmpty(str2)) {
            return !z2;
        } else if (z) {
            sb.append(com.alipay.sdk.sys.a.b);
            sb.append(str);
            sb.append("=\"");
            sb.append(str2);
            sb.append("\"");
            return true;
        } else {
            sb.append(str);
            sb.append("=\"");
            sb.append(str2);
            sb.append("\"");
            return true;
        }
    }

    public synchronized H5PayResultModel h5Pay(String str, boolean z) {
        H5PayResultModel h5PayResultModel;
        h5PayResultModel = new H5PayResultModel();
        String[] split = pay(str, z).split(com.alipay.sdk.util.i.b);
        HashMap hashMap = new HashMap();
        for (String str2 : split) {
            String substring = str2.substring(0, str2.indexOf("={"));
            hashMap.put(substring, a(str2, substring));
        }
        if (hashMap.containsKey(l.a)) {
            h5PayResultModel.setResultCode(hashMap.get(l.a));
        }
        h5PayResultModel.setReturnUrl(a(str, hashMap));
        if (TextUtils.isEmpty(h5PayResultModel.getReturnUrl())) {
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.R, "");
        }
        return h5PayResultModel;
    }

    private String a(String str, Map<String, String> map) throws UnsupportedEncodingException {
        boolean equals = "9000".equals(map.get(l.a));
        String str2 = map.get("result");
        a remove = this.g.remove(str);
        String[] strArr = new String[2];
        strArr[0] = remove != null ? remove.b() : "";
        strArr[1] = remove != null ? remove.d() : "";
        a(strArr);
        if (map.containsKey("callBackUrl")) {
            return map.get("callBackUrl");
        }
        if (str2.length() > 15) {
            String a2 = a(n.a("&callBackUrl=\"", "\"", str2), n.a("&call_back_url=\"", "\"", str2), n.a(com.alipay.sdk.cons.a.p, "\"", str2), URLDecoder.decode(n.a(com.alipay.sdk.cons.a.q, com.alipay.sdk.sys.a.b, str2), "utf-8"), URLDecoder.decode(n.a("&callBackUrl=", com.alipay.sdk.sys.a.b, str2), "utf-8"), n.a("call_back_url=\"", "\"", str2));
            if (!TextUtils.isEmpty(a2)) {
                return a2;
            }
        }
        if (remove != null) {
            String a3 = equals ? remove.a() : remove.c();
            if (!TextUtils.isEmpty(a3)) {
                return a3;
            }
        }
        return com.alipay.sdk.data.a.g().d();
    }

    private String a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf(com.alipay.sdk.util.i.d));
    }

    private e.a a() {
        return new h(this);
    }

    public void showLoading() {
        if (this.c != null) {
            this.c.b();
        }
    }

    public void dismissLoading() {
        if (this.c != null) {
            this.c.c();
            this.c = null;
        }
    }

    private String a(String str) {
        String a2 = new com.alipay.sdk.sys.a(this.b).a(str);
        if (a2.contains("paymethod=\"expressGateway\"")) {
            return b(a2);
        }
        List<a.C0006a> f = com.alipay.sdk.data.a.g().f();
        if (!com.alipay.sdk.data.a.g().p || f == null) {
            f = i.a;
        }
        if (n.b(this.b, f)) {
            com.alipay.sdk.util.e eVar = new com.alipay.sdk.util.e(this.b, a());
            String a3 = eVar.a(a2);
            eVar.a();
            if (TextUtils.equals(a3, com.alipay.sdk.util.e.a) || TextUtils.equals(a3, com.alipay.sdk.util.e.b)) {
                com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.M, "");
                return b(a2);
            } else if (TextUtils.isEmpty(a3)) {
                return j.c();
            } else {
                if (a3.contains(PayResultActivity.a)) {
                    com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.O, "");
                    return a(a2, f, a3, this.b);
                }
                return a3;
            }
        }
        com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.N, "");
        return b(a2);
    }

    private static String a(String str, List<a.C0006a> list, String str2, Activity activity) {
        n.a a2 = n.a(activity, list);
        if (a2 == null || a2.a() || a2.b() || !TextUtils.equals(a2.a.packageName, PayResultActivity.c)) {
            return str2;
        }
        com.alipay.sdk.util.c.b("msp", "PayTask:payResult: NOT_LOGIN");
        String valueOf = String.valueOf(str.hashCode());
        PayResultActivity.b.put(valueOf, new Object());
        Intent intent = new Intent(activity, PayResultActivity.class);
        intent.putExtra(PayResultActivity.e, str);
        intent.putExtra(PayResultActivity.f, activity.getPackageName());
        intent.putExtra(PayResultActivity.d, valueOf);
        activity.startActivity(intent);
        synchronized (PayResultActivity.b.get(valueOf)) {
            try {
                com.alipay.sdk.util.c.b("msp", "PayTask:payResult: wait");
                PayResultActivity.b.get(valueOf).wait();
            } catch (InterruptedException e) {
                com.alipay.sdk.util.c.b("msp", "PayTask:payResult: InterruptedException:" + e);
                return j.c();
            }
        }
        String str3 = PayResultActivity.a.b;
        com.alipay.sdk.util.c.b("msp", "PayTask:payResult: result:" + str3);
        return str3;
    }

    private String b(String str) {
        showLoading();
        k kVar = null;
        try {
            try {
                JSONObject c = new com.alipay.sdk.packet.impl.e().a(this.b.getApplicationContext(), str).c();
                String optString = c.optString("end_code", null);
                List<com.alipay.sdk.protocol.b> a2 = com.alipay.sdk.protocol.b.a(c.optJSONObject(com.alipay.sdk.cons.c.c).optJSONObject(com.alipay.sdk.cons.c.d));
                for (int i2 = 0; i2 < a2.size(); i2++) {
                    if (a2.get(i2).b() == com.alipay.sdk.protocol.a.Update) {
                        com.alipay.sdk.protocol.b.a(a2.get(i2));
                    }
                }
                a(c);
                dismissLoading();
                for (int i3 = 0; i3 < a2.size(); i3++) {
                    com.alipay.sdk.protocol.b bVar = a2.get(i3);
                    if (bVar.b() == com.alipay.sdk.protocol.a.WapPay) {
                        return a(bVar);
                    }
                    if (bVar.b() == com.alipay.sdk.protocol.a.OpenWeb) {
                        return a(bVar, optString);
                    }
                }
            } catch (IOException e) {
                kVar = k.b(k.NETWORK_ERROR.a());
                com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.a, e);
            }
            if (kVar == null) {
                kVar = k.b(k.FAILED.a());
            }
            return j.a(kVar.a(), kVar.b(), "");
        } finally {
            dismissLoading();
        }
    }

    private void a(JSONObject jSONObject) {
        try {
            String optString = jSONObject.optString("tid");
            String optString2 = jSONObject.optString(com.alipay.sdk.tid.b.e);
            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                return;
            }
            com.alipay.sdk.tid.b.a(com.alipay.sdk.sys.b.a().b()).a(optString, optString2);
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.F, th);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x008e, code lost:
        r0 = r6.c();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x00a8, code lost:
        r10 = com.alipay.sdk.app.j.a(java.lang.Integer.valueOf(r0[1]).intValue(), r0[0], com.alipay.sdk.util.n.e(r0[2]));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String a(com.alipay.sdk.protocol.b r10, java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 277
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.PayTask.a(com.alipay.sdk.protocol.b, java.lang.String):java.lang.String");
    }

    private String a(com.alipay.sdk.protocol.b bVar) {
        String[] c = bVar.c();
        Intent intent = new Intent(this.b, H5PayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", c[0]);
        if (c.length == 2) {
            bundle.putString("cookie", c[1]);
        }
        intent.putExtras(bundle);
        this.b.startActivity(intent);
        synchronized (a) {
            try {
                a.wait();
            } catch (InterruptedException e) {
                com.alipay.sdk.util.c.a(e);
                return j.c();
            }
        }
        String a2 = j.a();
        return TextUtils.isEmpty(a2) ? j.c() : a2;
    }

    private static boolean b() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - j >= i) {
            j = elapsedRealtime;
            return false;
        }
        return true;
    }
}