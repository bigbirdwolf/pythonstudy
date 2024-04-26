package com.alipay.android.phone.mrpc.core;

import android.content.Context;

/* loaded from: classes.dex */
final class i implements g {
    final /* synthetic */ aa a;
    final /* synthetic */ h b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(h hVar, aa aaVar) {
        this.b = hVar;
        this.a = aaVar;
    }

    @Override // com.alipay.android.phone.mrpc.core.g
    public final String a() {
        return this.a.a();
    }

    @Override // com.alipay.android.phone.mrpc.core.g
    public final ab b() {
        Context context;
        context = this.b.a;
        return l.a(context.getApplicationContext());
    }

    @Override // com.alipay.android.phone.mrpc.core.g
    public final aa c() {
        return this.a;
    }

    @Override // com.alipay.android.phone.mrpc.core.g
    public final boolean d() {
        return this.a.c();
    }
}