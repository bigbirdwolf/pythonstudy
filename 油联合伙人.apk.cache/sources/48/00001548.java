package com.yltx.oil.partner.base;

import android.graphics.Bitmap;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.yltx.oil.partner.utils.ADFilterTool;

/* loaded from: classes.dex */
public class JsWebViewClient extends BridgeWebViewClient {
    public JsWebViewClient(BridgeWebView bridgeWebView) {
        super(bridgeWebView);
    }

    @Override // com.github.lzyzsd.jsbridge.BridgeWebViewClient, android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // com.github.lzyzsd.jsbridge.BridgeWebViewClient, android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
    }

    @Override // com.github.lzyzsd.jsbridge.BridgeWebViewClient, android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return super.shouldOverrideUrlLoading(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        if (!ADFilterTool.hasNotAd(str)) {
            return super.shouldInterceptRequest(webView, str);
        }
        return new WebResourceResponse(null, null, null);
    }
}