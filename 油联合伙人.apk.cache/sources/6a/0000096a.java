package com.alipay.sdk.app;

import android.app.Activity;
import android.webkit.SslErrorHandler;

/* loaded from: classes.dex */
class c implements Runnable {
    final /* synthetic */ Activity a;
    final /* synthetic */ SslErrorHandler b;
    final /* synthetic */ b c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(b bVar, Activity activity, SslErrorHandler sslErrorHandler) {
        this.c = bVar;
        this.a = activity;
        this.b = sslErrorHandler;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.alipay.sdk.widget.e.a(this.a, "安全警告", "安全连接证书校验无效，将无法保证访问数据的安全性，可能存在风险，请选择是否继续？", "继续", new d(this), "退出", new e(this));
    }
}