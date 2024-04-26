package com.alipay.sdk.auth;

import android.content.DialogInterface;

/* loaded from: classes.dex */
class e implements DialogInterface.OnClickListener {
    final /* synthetic */ d a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(d dVar) {
        this.a = dVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        AuthActivity.this.g = true;
        this.a.a.proceed();
        dialogInterface.dismiss();
    }
}