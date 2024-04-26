package com.alipay.sdk.widget;

import android.webkit.SslErrorHandler;

/* loaded from: classes.dex */
class n implements Runnable {
    final /* synthetic */ SslErrorHandler a;
    final /* synthetic */ j b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(j jVar, SslErrorHandler sslErrorHandler) {
        this.b = jVar;
        this.a = sslErrorHandler;
    }

    @Override // java.lang.Runnable
    public void run() {
        e.a(this.b.a, "安全警告", "安全連接證書校驗無效，將無法保證訪問資料的安全性，可能存在風險，請選擇是否繼續？", "繼續", new o(this), "退出", new p(this));
    }
}