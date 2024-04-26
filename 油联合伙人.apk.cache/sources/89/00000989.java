package com.alipay.sdk.authjs;

import com.alipay.sdk.authjs.a;
import org.json.JSONException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class e implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ d b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(d dVar, a aVar) {
        this.b = dVar;
        this.a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        a.EnumC0005a b;
        b = this.b.b(this.a);
        if (b != a.EnumC0005a.NONE_ERROR) {
            try {
                this.b.a(this.a.b(), b, true);
            } catch (JSONException unused) {
            }
        }
    }
}