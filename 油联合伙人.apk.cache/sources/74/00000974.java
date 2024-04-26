package com.alipay.sdk.app.statistic;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.packet.impl.d;
import com.alipay.sdk.util.j;
import java.io.IOException;

/* loaded from: classes.dex */
final class b implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        d dVar = new d();
        try {
            String b = j.b(this.a, a.a, null);
            if (!TextUtils.isEmpty(b) && dVar.a(this.a, b) != null) {
                j.b(this.a, a.a);
            }
        } catch (Throwable unused) {
        }
        try {
            if (TextUtils.isEmpty(this.b)) {
                return;
            }
            dVar.a(this.a, this.b);
        } catch (IOException unused2) {
            j.a(this.a, a.a, this.b);
        } catch (Throwable unused3) {
        }
    }
}