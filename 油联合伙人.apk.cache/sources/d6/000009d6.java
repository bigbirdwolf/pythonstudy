package com.alipay.sdk.widget;

import android.content.DialogInterface;

/* loaded from: classes.dex */
class p implements DialogInterface.OnClickListener {
    final /* synthetic */ n a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public p(n nVar) {
        this.a = nVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.a.cancel();
        this.a.b.w = false;
        com.alipay.sdk.app.j.a(com.alipay.sdk.app.j.c());
        this.a.b.a.finish();
    }
}