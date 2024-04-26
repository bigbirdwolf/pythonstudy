package com.alipay.sdk.widget;

import android.content.Context;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/* loaded from: classes.dex */
public class WebViewWindow extends LinearLayout {
    private static Handler f = new Handler(Looper.getMainLooper());
    private ImageView a;
    private TextView b;
    private ImageView c;
    private ProgressBar d;
    private WebView e;
    private a g;
    private b h;
    private c i;
    private View.OnClickListener j;
    private final float k;

    /* loaded from: classes.dex */
    public interface a {
        void a(WebViewWindow webViewWindow, String str);

        boolean a(WebViewWindow webViewWindow, String str, String str2, String str3, JsPromptResult jsPromptResult);
    }

    /* loaded from: classes.dex */
    public interface b {
        boolean a(WebViewWindow webViewWindow, int i, String str, String str2);

        boolean a(WebViewWindow webViewWindow, SslErrorHandler sslErrorHandler, SslError sslError);

        boolean b(WebViewWindow webViewWindow, String str);

        boolean c(WebViewWindow webViewWindow, String str);
    }

    /* loaded from: classes.dex */
    public interface c {
        void a(WebViewWindow webViewWindow);

        void b(WebViewWindow webViewWindow);
    }

    public WebViewWindow(Context context) {
        this(context, null);
    }

    public WebViewWindow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = new q(this);
        this.k = context.getResources().getDisplayMetrics().density;
        setOrientation(1);
        a(context);
        b(context);
        c(context);
    }

    private void a(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundColor(-218103809);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        this.a = new ImageView(context);
        this.a.setOnClickListener(this.j);
        this.a.setScaleType(ImageView.ScaleType.CENTER);
        this.a.setImageDrawable(com.alipay.sdk.util.k.a(com.alipay.sdk.util.k.a, context));
        this.a.setPadding(a(12), 0, a(12), 0);
        linearLayout.addView(this.a, new LinearLayout.LayoutParams(-2, -2));
        View view = new View(context);
        view.setBackgroundColor(-2500135);
        linearLayout.addView(view, new LinearLayout.LayoutParams(a(1), a(25)));
        this.b = new TextView(context);
        this.b.setTextColor(-15658735);
        this.b.setTextSize(17.0f);
        this.b.setMaxLines(1);
        this.b.setEllipsize(TextUtils.TruncateAt.END);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(a(17), 0, 0, 0);
        layoutParams.weight = 1.0f;
        linearLayout.addView(this.b, layoutParams);
        this.c = new ImageView(context);
        this.c.setOnClickListener(this.j);
        this.c.setScaleType(ImageView.ScaleType.CENTER);
        this.c.setImageDrawable(com.alipay.sdk.util.k.a(com.alipay.sdk.util.k.b, context));
        this.c.setPadding(a(12), 0, a(12), 0);
        linearLayout.addView(this.c, new LinearLayout.LayoutParams(-2, -2));
        addView(linearLayout, new LinearLayout.LayoutParams(-1, a(48)));
    }

    private void b(Context context) {
        this.d = new ProgressBar(context, null, 16973855);
        this.d.setProgressDrawable(context.getResources().getDrawable(17301612));
        this.d.setMax(100);
        this.d.setBackgroundColor(-218103809);
        addView(this.d, new LinearLayout.LayoutParams(-1, a(2)));
    }

    private void c(Context context) {
        this.e = new WebView(context);
        this.e.setVerticalScrollbarOverlay(true);
        a(this.e, context);
        WebSettings settings = this.e.getSettings();
        settings.setUseWideViewPort(true);
        settings.setAppCacheMaxSize(5242880L);
        settings.setAppCachePath(context.getCacheDir().getAbsolutePath());
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(-1);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDomStorageEnabled(true);
        try {
            this.e.removeJavascriptInterface("searchBoxJavaBridge_");
            this.e.removeJavascriptInterface("accessibility");
            this.e.removeJavascriptInterface("accessibilityTraversal");
        } catch (Exception unused) {
        }
        addView(this.e, new LinearLayout.LayoutParams(-1, -1));
    }

    protected void a(WebView webView, Context context) {
        String userAgentString = webView.getSettings().getUserAgentString();
        String packageName = context.getPackageName();
        String i = com.alipay.sdk.util.n.i(context);
        webView.getSettings().setUserAgentString(userAgentString + " AlipaySDK(" + packageName + "/" + i + "/15.6.2)");
    }

    public void setChromeProxy(a aVar) {
        this.g = aVar;
        if (aVar == null) {
            this.e.setWebChromeClient(null);
        } else {
            this.e.setWebChromeClient(new s(this));
        }
    }

    public void setWebClientProxy(b bVar) {
        this.h = bVar;
        if (bVar == null) {
            this.e.setWebViewClient(null);
        } else {
            this.e.setWebViewClient(new t(this));
        }
    }

    public void setWebEventProxy(c cVar) {
        this.i = cVar;
    }

    public String getUrl() {
        return this.e.getUrl();
    }

    public void a(String str) {
        this.e.loadUrl(str);
    }

    public void a(String str, byte[] bArr) {
        this.e.postUrl(str, bArr);
    }

    public ImageView getBackButton() {
        return this.a;
    }

    public TextView getTitle() {
        return this.b;
    }

    public ImageView getRefreshButton() {
        return this.c;
    }

    public ProgressBar getProgressbar() {
        return this.d;
    }

    public WebView getWebView() {
        return this.e;
    }

    public void a() {
        removeAllViews();
        this.e.removeAllViews();
        this.e.setWebViewClient(null);
        this.e.setWebChromeClient(null);
        this.e.destroy();
    }

    private int a(int i) {
        return (int) (i * this.k);
    }
}