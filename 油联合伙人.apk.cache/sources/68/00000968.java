package com.alipay.sdk.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alipay.sdk.util.n;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class b extends WebViewClient {
    @Nullable
    private Activity a;
    private boolean b;
    private Handler c;
    private com.alipay.sdk.widget.a d;
    private boolean e;

    public b(@NonNull Activity activity) {
        this.a = activity;
        this.c = new Handler(this.a.getMainLooper());
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        this.e = true;
        super.onReceivedError(webView, i, str, str2);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        Activity activity = this.a;
        if (activity == null) {
            return;
        }
        com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.a, com.alipay.sdk.app.statistic.c.r, "证书错误");
        if (this.b) {
            sslErrorHandler.proceed();
            this.b = false;
            return;
        }
        activity.runOnUiThread(new c(this, activity, sslErrorHandler));
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return n.a(webView, str, this.a);
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        Activity activity = this.a;
        if (this.c != null && activity != null && !activity.isFinishing()) {
            c();
            this.c.postDelayed(new a(this), com.umeng.commonsdk.proguard.c.d);
        }
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        Activity activity = this.a;
        if (this.c == null || activity == null || activity.isFinishing()) {
            return;
        }
        d();
        this.c.removeCallbacksAndMessages(null);
    }

    private void c() {
        Activity activity = this.a;
        if (activity == null) {
            return;
        }
        if (this.d == null) {
            this.d = new com.alipay.sdk.widget.a(activity, com.alipay.sdk.widget.a.a);
            this.d.a(true);
        }
        this.d.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.d != null) {
            this.d.c();
        }
        this.d = null;
    }

    /* loaded from: classes.dex */
    private static final class a implements Runnable {
        private final WeakReference<b> a;

        a(b bVar) {
            this.a = new WeakReference<>(bVar);
        }

        @Override // java.lang.Runnable
        public void run() {
            b bVar = this.a.get();
            if (bVar != null) {
                bVar.d();
            }
        }
    }

    public void a() {
        this.c = null;
        this.a = null;
    }

    public boolean b() {
        return this.e;
    }
}