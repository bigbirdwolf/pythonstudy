package com.alipay.sdk.app;

import android.content.DialogInterface;

/* loaded from: classes.dex */
class e implements DialogInterface.OnClickListener {
    final /* synthetic */ c a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(c cVar) {
        this.a = cVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.b.cancel();
        this.a.c.b = false;
        j.a(j.c());
        this.a.a.finish();
    }
}