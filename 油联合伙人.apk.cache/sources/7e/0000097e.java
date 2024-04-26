package com.alipay.sdk.auth;

import android.webkit.WebView;
import com.github.lzyzsd.jsbridge.BridgeUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class c implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ AuthActivity b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(AuthActivity authActivity, String str) {
        this.b = authActivity;
        this.a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        WebView webView;
        try {
            webView = this.b.c;
            webView.loadUrl(BridgeUtil.JAVASCRIPT_STR + this.a);
        } catch (Exception unused) {
        }
    }
}