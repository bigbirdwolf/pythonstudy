package com.alipay.sdk.auth;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.ConsoleMessage;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alipay.sdk.app.i;
import com.alipay.sdk.util.n;
import java.lang.ref.WeakReference;
import org.json.JSONException;

@SuppressLint({"SetJavaScriptEnabled", "DefaultLocale"})
/* loaded from: classes.dex */
public class AuthActivity extends Activity {
    static final String a = "params";
    static final String b = "redirectUri";
    private WebView c;
    private String d;
    private com.alipay.sdk.widget.a e;
    private Handler f;
    private boolean g;
    private boolean h;

    /* JADX WARN: Removed duplicated region for block: B:24:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onCreate(android.os.Bundle r6) {
        /*
            Method dump skipped, instructions count: 356
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.auth.AuthActivity.onCreate(android.os.Bundle):void");
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.c.canGoBack()) {
            if (this.h) {
                g.a(this, this.d + "?resultCode=150");
                finish();
                return;
            }
            return;
        }
        g.a(this, this.d + "?resultCode=150");
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class c extends WebViewClient {
        private c() {
        }

        /* synthetic */ c(AuthActivity authActivity, com.alipay.sdk.auth.a aVar) {
            this();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            AuthActivity.this.h = true;
            super.onReceivedError(webView, i, str, str2);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (AuthActivity.this.g) {
                sslErrorHandler.proceed();
                AuthActivity.this.g = false;
                return;
            }
            AuthActivity.this.runOnUiThread(new d(this, sslErrorHandler));
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            n.a a;
            if (!str.toLowerCase().startsWith(com.alipay.sdk.cons.a.j.toLowerCase()) && !str.toLowerCase().startsWith(com.alipay.sdk.cons.a.k.toLowerCase())) {
                if (AuthActivity.this.a(str)) {
                    webView.stopLoading();
                    return true;
                }
                return super.shouldOverrideUrlLoading(webView, str);
            }
            try {
                a = n.a(AuthActivity.this, i.a);
            } catch (Throwable unused) {
            }
            if (a != null && !a.a() && !a.b()) {
                if (str.startsWith("intent://platformapi/startapp")) {
                    str = str.replaceFirst(com.alipay.sdk.cons.a.k, com.alipay.sdk.cons.a.j);
                }
                AuthActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            }
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            AuthActivity.this.a();
            AuthActivity.this.f.postDelayed(new a(AuthActivity.this, null), com.umeng.commonsdk.proguard.c.d);
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            AuthActivity.this.b();
            AuthActivity.this.f.removeCallbacksAndMessages(null);
        }
    }

    /* loaded from: classes.dex */
    private class b extends WebChromeClient {
        private b() {
        }

        /* synthetic */ b(AuthActivity authActivity, com.alipay.sdk.auth.a aVar) {
            this();
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            String message = consoleMessage.message();
            if (TextUtils.isEmpty(message)) {
                return super.onConsoleMessage(consoleMessage);
            }
            String replaceFirst = message.startsWith("h5container.message: ") ? message.replaceFirst("h5container.message: ", "") : null;
            if (!TextUtils.isEmpty(replaceFirst)) {
                AuthActivity.this.b(replaceFirst);
                return super.onConsoleMessage(consoleMessage);
            }
            return super.onConsoleMessage(consoleMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("http://") || str.startsWith("https://")) {
            return false;
        }
        if (!"SDKLite://h5quit".equalsIgnoreCase(str)) {
            if (TextUtils.equals(str, this.d)) {
                str = str + "?resultCode=150";
            }
            g.a(this, str);
        }
        finish();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        new com.alipay.sdk.authjs.d(getApplicationContext(), new com.alipay.sdk.auth.b(this)).a(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.alipay.sdk.authjs.a aVar) {
        if (this.c == null || aVar == null) {
            return;
        }
        try {
            runOnUiThread(new com.alipay.sdk.auth.c(this, String.format("AlipayJSBridge._invokeJS(%s)", aVar.g())));
        } catch (JSONException e) {
            com.alipay.sdk.util.c.a("msp", e);
        }
    }

    /* loaded from: classes.dex */
    private static final class a implements Runnable {
        private final WeakReference<AuthActivity> a;

        /* synthetic */ a(AuthActivity authActivity, com.alipay.sdk.auth.a aVar) {
            this(authActivity);
        }

        private a(AuthActivity authActivity) {
            this.a = new WeakReference<>(authActivity);
        }

        @Override // java.lang.Runnable
        public void run() {
            AuthActivity authActivity = this.a.get();
            if (authActivity != null) {
                authActivity.b();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        try {
            if (this.e == null) {
                this.e = new com.alipay.sdk.widget.a(this, com.alipay.sdk.widget.a.a);
            }
            this.e.b();
        } catch (Exception unused) {
            this.e = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.e != null) {
            this.e.c();
        }
        this.e = null;
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.c != null) {
            this.c.removeAllViews();
            try {
                this.c.destroy();
            } catch (Throwable unused) {
            }
            this.c = null;
        }
    }
}