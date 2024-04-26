package com.alipay.sdk.auth;

import android.webkit.SslErrorHandler;
import com.alipay.sdk.auth.AuthActivity;

/* loaded from: classes.dex */
class d implements Runnable {
    final /* synthetic */ SslErrorHandler a;
    final /* synthetic */ AuthActivity.c b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(AuthActivity.c cVar, SslErrorHandler sslErrorHandler) {
        this.b = cVar;
        this.a = sslErrorHandler;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.alipay.sdk.widget.e.a(AuthActivity.this, "安全警告", "由于您的设备缺少根证书，将无法校验该访问站点的安全性，可能存在风险，请选择是否继续？", "继续", new e(this), "退出", new f(this));
    }
}