package com.alipay.sdk.widget;

import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import com.alipay.sdk.widget.WebViewWindow;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class s extends WebChromeClient {
    final /* synthetic */ WebViewWindow a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public s(WebViewWindow webViewWindow) {
        this.a = webViewWindow;
    }

    @Override // android.webkit.WebChromeClient
    public void onProgressChanged(WebView webView, int i) {
        ProgressBar progressBar;
        ProgressBar progressBar2;
        ProgressBar progressBar3;
        ProgressBar progressBar4;
        if (i == 100) {
            progressBar4 = this.a.d;
            progressBar4.setVisibility(4);
            return;
        }
        progressBar = this.a.d;
        if (4 == progressBar.getVisibility()) {
            progressBar3 = this.a.d;
            progressBar3.setVisibility(0);
        }
        progressBar2 = this.a.d;
        progressBar2.setProgress(i);
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        WebViewWindow.a aVar;
        aVar = this.a.g;
        return aVar.a(this.a, str, str2, str3, jsPromptResult);
    }

    @Override // android.webkit.WebChromeClient
    public void onReceivedTitle(WebView webView, String str) {
        WebViewWindow.a aVar;
        aVar = this.a.g;
        aVar.a(this.a, str);
    }
}