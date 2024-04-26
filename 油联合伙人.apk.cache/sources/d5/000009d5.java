package com.alipay.sdk.widget;

import android.content.DialogInterface;

/* loaded from: classes.dex */
class o implements DialogInterface.OnClickListener {
    final /* synthetic */ n a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public o(n nVar) {
        this.a = nVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.b.w = true;
        this.a.a.proceed();
        dialogInterface.dismiss();
    }
}