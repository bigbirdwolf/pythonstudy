package com.alipay.sdk.auth;

import android.content.DialogInterface;

/* loaded from: classes.dex */
class f implements DialogInterface.OnClickListener {
    final /* synthetic */ d a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(d dVar) {
        this.a = dVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        String str;
        this.a.a.cancel();
        AuthActivity.this.g = false;
        StringBuilder sb = new StringBuilder();
        str = AuthActivity.this.d;
        sb.append(str);
        sb.append("?resultCode=150");
        g.a(AuthActivity.this, sb.toString());
        AuthActivity.this.finish();
    }
}