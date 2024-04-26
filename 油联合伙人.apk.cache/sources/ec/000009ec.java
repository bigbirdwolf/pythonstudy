package com.alipay.security.mobile.module.d;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class b {
    private File a;
    private com.alipay.security.mobile.module.http.v2.a b;

    public b(String str, com.alipay.security.mobile.module.http.v2.a aVar) {
        this.a = null;
        this.b = null;
        this.a = new File(str);
        this.b = aVar;
    }

    private static String a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "id");
            jSONObject.put(com.umeng.analytics.pro.b.N, str);
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b() {
        if (this.a == null) {
            return;
        }
        if (this.a.exists() && this.a.isDirectory() && this.a.list().length != 0) {
            ArrayList arrayList = new ArrayList();
            for (String str : this.a.list()) {
                arrayList.add(str);
            }
            Collections.sort(arrayList);
            String str2 = (String) arrayList.get(arrayList.size() - 1);
            int size = arrayList.size();
            if (str2.equals(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".log")) {
                if (arrayList.size() < 2) {
                    return;
                }
                str2 = (String) arrayList.get(arrayList.size() - 2);
                size--;
            }
            if (!this.b.a(a(com.alipay.security.mobile.module.a.b.a(this.a.getAbsolutePath(), str2)))) {
                size--;
            }
            for (int i = 0; i < size; i++) {
                new File(this.a, (String) arrayList.get(i)).delete();
            }
        }
    }

    public final void a() {
        new Thread(new c(this)).start();
    }
}