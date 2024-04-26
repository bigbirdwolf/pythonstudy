package com.yltx.oil.partner.utils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alibaba.sdk.android.httpdns.HttpDns;
import com.alibaba.sdk.android.httpdns.HttpDnsService;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.alipay.sdk.cons.b;
import com.yltx.oil.partner.base.PartnerApplication;
import com.yltx.oil.partner.data.network.Config;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes.dex */
public class WebViewUtil {
    private static final String TAG = "WebViewUtil";
    private static final String HTTP_DNS_ACCOUNT = "156280";
    private static HttpDnsService httpDns = HttpDns.getService(PartnerApplication.getInstance(), HTTP_DNS_ACCOUNT);

    static {
        ArrayList arrayList = new ArrayList();
        arrayList.add("www.antaishenghuo.com");
        arrayList.add("api.antaishenghuo.com");
        httpDns.setPreResolveHosts(arrayList);
    }

    public static void setupWebView(WebView webView) {
        setupWebView(webView, null, null);
    }

    public static void setupWebView(WebView webView, WebViewClient webViewClient) {
        setupWebView(webView, webViewClient, null);
    }

    public static void setupWebView(WebView webView, WebChromeClient webChromeClient) {
        setupWebView(webView, null, webChromeClient);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public static void setupWebView(WebView webView, WebViewClient webViewClient, WebChromeClient webChromeClient) {
        WebSettings settings = webView.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setGeolocationEnabled(true);
        settings.setCacheMode(2);
        webView.setWebViewClient(new WebViewClientWrapper(webViewClient));
        webView.setWebChromeClient(webChromeClient);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class WebViewClientWrapper extends WebViewClient {
        private WebViewClient childWebViewClient;

        public WebViewClientWrapper(WebViewClient webViewClient) {
            this.childWebViewClient = webViewClient;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (this.childWebViewClient != null) {
                return this.childWebViewClient.shouldOverrideUrlLoading(webView, str);
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (this.childWebViewClient != null) {
                this.childWebViewClient.onPageStarted(webView, str, bitmap);
            } else {
                super.onPageStarted(webView, str, bitmap);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            if (this.childWebViewClient != null) {
                this.childWebViewClient.onPageFinished(webView, str);
            } else {
                super.onPageFinished(webView, str);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onLoadResource(WebView webView, String str) {
            if (this.childWebViewClient != null) {
                this.childWebViewClient.onLoadResource(webView, str);
            } else {
                super.onLoadResource(webView, str);
            }
        }

        @Override // android.webkit.WebViewClient
        @SuppressLint({"NewApi"})
        public void onPageCommitVisible(WebView webView, String str) {
            if (this.childWebViewClient != null) {
                this.childWebViewClient.onPageCommitVisible(webView, str);
            } else {
                super.onPageCommitVisible(webView, str);
            }
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            if (Config.DEBUG) {
                return super.shouldInterceptRequest(webView, str);
            }
            if (!TextUtils.isEmpty(str) && Uri.parse(str).getScheme() != null) {
                String trim = Uri.parse(str).getScheme().trim();
                String str2 = WebViewUtil.TAG;
                Log.d(str2, "url: " + str);
                if ((trim.equalsIgnoreCase("http") || trim.equalsIgnoreCase(b.a)) && str.contains(".css")) {
                    try {
                        URL url = new URL(str);
                        URLConnection openConnection = url.openConnection();
                        String ipByHostAsync = WebViewUtil.httpDns.getIpByHostAsync(url.getHost());
                        if (ipByHostAsync != null) {
                            String str3 = WebViewUtil.TAG;
                            Log.d(str3, "Get IP: " + ipByHostAsync + " for host: " + url.getHost() + " from HTTPDNS successfully!");
                            openConnection = new URL(str.replaceFirst(url.getHost(), ipByHostAsync)).openConnection();
                            openConnection.setRequestProperty(HttpHeaders.HOST, url.getHost());
                        }
                        String str4 = WebViewUtil.TAG;
                        Log.d(str4, "ContentType: " + openConnection.getContentType());
                        WebResourceResponse shouldInterceptRequest = this.childWebViewClient != null ? this.childWebViewClient.shouldInterceptRequest(webView, str) : null;
                        if (shouldInterceptRequest == null) {
                            return new WebResourceResponse(openConnection.getContentType(), "UTF-8", openConnection.getInputStream());
                        }
                        shouldInterceptRequest.setMimeType(openConnection.getContentType());
                        shouldInterceptRequest.setEncoding("UTF-8");
                        shouldInterceptRequest.setData(openConnection.getInputStream());
                        return shouldInterceptRequest;
                    } catch (IOException e) {
                        String str5 = WebViewUtil.TAG;
                        Log.e(str5, "shouldInterceptRequest: " + e.getMessage(), e);
                    }
                }
            }
            return this.childWebViewClient != null ? this.childWebViewClient.shouldInterceptRequest(webView, str) : super.shouldInterceptRequest(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            if (Config.DEBUG) {
                return super.shouldInterceptRequest(webView, webResourceRequest);
            }
            if (webResourceRequest != null && webResourceRequest.getUrl() != null && webResourceRequest.getMethod().equalsIgnoreCase("get")) {
                String trim = webResourceRequest.getUrl().getScheme().trim();
                Map<String, String> requestHeaders = webResourceRequest.getRequestHeaders();
                String uri = webResourceRequest.getUrl().toString();
                String str = WebViewUtil.TAG;
                Log.d(str, "request.getUrl().toString(): " + uri);
                if ((trim.equalsIgnoreCase("http") || trim.equalsIgnoreCase(b.a)) && webResourceRequest.getUrl().toString().contains(".css")) {
                    try {
                        URL url = new URL(uri);
                        URLConnection openConnection = url.openConnection();
                        String ipByHostAsync = WebViewUtil.httpDns.getIpByHostAsync(url.getHost());
                        if (ipByHostAsync != null) {
                            String str2 = WebViewUtil.TAG;
                            Log.d(str2, "Get IP: " + ipByHostAsync + " for host: " + url.getHost() + " from HTTPDNS successfully!");
                            openConnection = new URL(uri.replaceFirst(url.getHost(), ipByHostAsync)).openConnection();
                            openConnection.setRequestProperty(HttpHeaders.HOST, url.getHost());
                        }
                        for (String str3 : requestHeaders.keySet()) {
                            openConnection.setRequestProperty(str3, requestHeaders.get(str3));
                        }
                        String str4 = WebViewUtil.TAG;
                        Log.d(str4, "ContentType: " + openConnection.getContentType());
                        WebResourceResponse shouldInterceptRequest = this.childWebViewClient != null ? this.childWebViewClient.shouldInterceptRequest(webView, webResourceRequest) : null;
                        if (shouldInterceptRequest == null) {
                            return new WebResourceResponse(openConnection.getContentType(), "UTF-8", openConnection.getInputStream());
                        }
                        shouldInterceptRequest.setMimeType(openConnection.getContentType());
                        shouldInterceptRequest.setEncoding("UTF-8");
                        shouldInterceptRequest.setData(openConnection.getInputStream());
                        return shouldInterceptRequest;
                    } catch (IOException e) {
                        String str5 = WebViewUtil.TAG;
                        Log.e(str5, "shouldInterceptRequest: " + e.getMessage(), e);
                    }
                }
            }
            return this.childWebViewClient != null ? this.childWebViewClient.shouldInterceptRequest(webView, webResourceRequest) : super.shouldInterceptRequest(webView, webResourceRequest);
        }

        @Override // android.webkit.WebViewClient
        public void onTooManyRedirects(WebView webView, Message message, Message message2) {
            if (this.childWebViewClient != null) {
                this.childWebViewClient.onTooManyRedirects(webView, message, message2);
            } else {
                super.onTooManyRedirects(webView, message, message2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (this.childWebViewClient != null) {
                this.childWebViewClient.onReceivedError(webView, i, str, str2);
            } else {
                super.onReceivedError(webView, i, str, str2);
            }
        }

        @Override // android.webkit.WebViewClient
        @SuppressLint({"NewApi"})
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            if (this.childWebViewClient != null) {
                this.childWebViewClient.onReceivedError(webView, webResourceRequest, webResourceError);
            } else {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
            }
        }

        @Override // android.webkit.WebViewClient
        @SuppressLint({"NewApi"})
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            if (this.childWebViewClient != null) {
                this.childWebViewClient.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            } else {
                super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onFormResubmission(WebView webView, Message message, Message message2) {
            if (this.childWebViewClient != null) {
                this.childWebViewClient.onFormResubmission(webView, message, message2);
            } else {
                super.onFormResubmission(webView, message, message2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
            if (this.childWebViewClient != null) {
                this.childWebViewClient.doUpdateVisitedHistory(webView, str, z);
            } else {
                super.doUpdateVisitedHistory(webView, str, z);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (this.childWebViewClient != null) {
                this.childWebViewClient.onReceivedSslError(webView, sslErrorHandler, sslError);
            } else {
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
            }
        }

        @Override // android.webkit.WebViewClient
        @SuppressLint({"NewApi"})
        public void onReceivedClientCertRequest(WebView webView, ClientCertRequest clientCertRequest) {
            if (this.childWebViewClient != null) {
                this.childWebViewClient.onReceivedClientCertRequest(webView, clientCertRequest);
            } else {
                super.onReceivedClientCertRequest(webView, clientCertRequest);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
            if (this.childWebViewClient != null) {
                this.childWebViewClient.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
            } else {
                super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
            }
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
            if (this.childWebViewClient != null) {
                return this.childWebViewClient.shouldOverrideKeyEvent(webView, keyEvent);
            }
            return super.shouldOverrideKeyEvent(webView, keyEvent);
        }

        @Override // android.webkit.WebViewClient
        public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
            if (this.childWebViewClient != null) {
                this.childWebViewClient.onUnhandledKeyEvent(webView, keyEvent);
            } else {
                super.onUnhandledKeyEvent(webView, keyEvent);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onScaleChanged(WebView webView, float f, float f2) {
            if (this.childWebViewClient != null) {
                this.childWebViewClient.onScaleChanged(webView, f, f2);
            } else {
                super.onScaleChanged(webView, f, f2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
            if (this.childWebViewClient != null) {
                this.childWebViewClient.onReceivedLoginRequest(webView, str, str2, str3);
            } else {
                super.onReceivedLoginRequest(webView, str, str2, str3);
            }
        }
    }
}