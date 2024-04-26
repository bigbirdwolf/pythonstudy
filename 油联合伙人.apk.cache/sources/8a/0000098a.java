package com.alipay.sdk.authjs;

import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class f extends TimerTask {
    final /* synthetic */ a a;
    final /* synthetic */ d b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(d dVar, a aVar) {
        this.b = dVar;
        this.a = aVar;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        c cVar;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("toastCallBack", "true");
        } catch (JSONException unused) {
        }
        a aVar = new a(a.b);
        aVar.a(this.a.b());
        aVar.a(jSONObject);
        cVar = this.b.a;
        cVar.a(aVar);
    }
}