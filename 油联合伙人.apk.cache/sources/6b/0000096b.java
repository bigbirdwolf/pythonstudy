package com.alipay.sdk.app;

import android.content.DialogInterface;

/* loaded from: classes.dex */
class d implements DialogInterface.OnClickListener {
    final /* synthetic */ c a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(c cVar) {
        this.a = cVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.c.b = true;
        this.a.b.proceed();
        dialogInterface.dismiss();
    }
}