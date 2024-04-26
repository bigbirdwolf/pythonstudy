package com.yltx.oil.partner.modules.web;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.yltx.oil.partner.utils.ADFilterTool;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled", "NewApi"})
/* loaded from: classes.dex */
public class WVJBWebViewClient extends WebViewClient {
    private static final String kCustomProtocolScheme = "wvjbscheme";
    private static final String kInterface = "WVJBInterface";
    private static final String kQueueHasMessage = "__WVJB_QUEUE_MESSAGE__";
    private static final String kTag = "WVJB";
    private static boolean logging = false;
    private WVJBHandler messageHandler;
    private Map<String, WVJBHandler> messageHandlers;
    private MyJavascriptInterface myInterface;
    private Map<String, WVJBResponseCallback> responseCallbacks;
    private ArrayList<WVJBMessage> startupMessageQueue;
    private long uniqueId;
    protected WebView webView;

    /* loaded from: classes.dex */
    public interface JavascriptCallback {
        void onReceiveValue(String str);
    }

    /* loaded from: classes.dex */
    public interface WVJBHandler {
        void request(Object obj, WVJBResponseCallback wVJBResponseCallback);
    }

    /* loaded from: classes.dex */
    public interface WVJBResponseCallback {
        void callback(Object obj);
    }

    public WVJBWebViewClient(WebView webView) {
        this(webView, null);
    }

    public WVJBWebViewClient(WebView webView, WVJBHandler wVJBHandler) {
        this.startupMessageQueue = null;
        this.responseCallbacks = null;
        this.messageHandlers = null;
        this.uniqueId = 0L;
        this.myInterface = new MyJavascriptInterface();
        this.webView = webView;
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.addJavascriptInterface(this.myInterface, kInterface);
        this.responseCallbacks = new HashMap();
        this.messageHandlers = new HashMap();
        this.startupMessageQueue = new ArrayList<>();
        this.messageHandler = wVJBHandler;
    }

    public void enableLogging() {
        logging = true;
    }

    public void send(Object obj) {
        send(obj, null);
    }

    public void send(Object obj, WVJBResponseCallback wVJBResponseCallback) {
        sendData(obj, wVJBResponseCallback, null);
    }

    public void callHandler(String str) {
        callHandler(str, null, null);
    }

    public void callHandler(String str, Object obj) {
        callHandler(str, obj, null);
    }

    public void callHandler(String str, Object obj, WVJBResponseCallback wVJBResponseCallback) {
        sendData(obj, wVJBResponseCallback, str);
    }

    public void registerHandler(String str, WVJBHandler wVJBHandler) {
        if (str == null || str.length() == 0 || wVJBHandler == null) {
            return;
        }
        this.messageHandlers.put(str, wVJBHandler);
    }

    private void sendData(Object obj, WVJBResponseCallback wVJBResponseCallback, String str) {
        if (obj == null && (str == null || str.length() == 0)) {
            return;
        }
        WVJBMessage wVJBMessage = new WVJBMessage();
        if (obj != null) {
            wVJBMessage.data = obj;
        }
        if (wVJBResponseCallback != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("objc_cb_");
            long j = this.uniqueId + 1;
            this.uniqueId = j;
            sb.append(j);
            String sb2 = sb.toString();
            this.responseCallbacks.put(sb2, wVJBResponseCallback);
            wVJBMessage.callbackId = sb2;
        }
        if (str != null) {
            wVJBMessage.handlerName = str;
        }
        queueMessage(wVJBMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queueMessage(WVJBMessage wVJBMessage) {
        if (this.startupMessageQueue != null) {
            this.startupMessageQueue.add(wVJBMessage);
        } else {
            dispatchMessage(wVJBMessage);
        }
    }

    private void dispatchMessage(WVJBMessage wVJBMessage) {
        String replaceAll = message2JSONObject(wVJBMessage).toString().replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", "\\\\\"").replaceAll("'", "\\\\'").replaceAll("\n", "\\\\\n").replaceAll("\r", "\\\\\r").replaceAll("\f", "\\\\\f");
        log("SEND", replaceAll);
        executeJavascript("WebViewJavascriptBridge._handleMessageFromObjC('" + replaceAll + "');");
    }

    private JSONObject message2JSONObject(WVJBMessage wVJBMessage) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (wVJBMessage.callbackId != null) {
                jSONObject.put("callbackId", wVJBMessage.callbackId);
            }
            if (wVJBMessage.data != null) {
                jSONObject.put("data", wVJBMessage.data);
            }
            if (wVJBMessage.handlerName != null) {
                jSONObject.put("handlerName", wVJBMessage.handlerName);
            }
            if (wVJBMessage.responseId != null) {
                jSONObject.put("responseId", wVJBMessage.responseId);
            }
            if (wVJBMessage.responseData != null) {
                jSONObject.put("responseData", wVJBMessage.responseData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private WVJBMessage JSONObject2WVJBMessage(JSONObject jSONObject) {
        WVJBMessage wVJBMessage = new WVJBMessage();
        try {
            if (jSONObject.has("callbackId")) {
                wVJBMessage.callbackId = jSONObject.getString("callbackId");
            }
            if (jSONObject.has("data")) {
                wVJBMessage.data = jSONObject.get("data");
            }
            if (jSONObject.has("handlerName")) {
                wVJBMessage.handlerName = jSONObject.getString("handlerName");
            }
            if (jSONObject.has("responseId")) {
                wVJBMessage.responseId = jSONObject.getString("responseId");
            }
            if (jSONObject.has("responseData")) {
                wVJBMessage.responseData = jSONObject.get("responseData");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return wVJBMessage;
    }

    private void flushMessageQueue() {
        executeJavascript("WebViewJavascriptBridge._fetchQueue()", new JavascriptCallback() { // from class: com.yltx.oil.partner.modules.web.WVJBWebViewClient.1
            @Override // com.yltx.oil.partner.modules.web.WVJBWebViewClient.JavascriptCallback
            public void onReceiveValue(String str) {
                if (str == null || str.length() == 0) {
                    return;
                }
                WVJBWebViewClient.this.processQueueMessage(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processQueueMessage(String str) {
        WVJBHandler wVJBHandler;
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                log("RCVD", jSONObject);
                WVJBMessage JSONObject2WVJBMessage = JSONObject2WVJBMessage(jSONObject);
                if (JSONObject2WVJBMessage.responseId != null) {
                    WVJBResponseCallback remove = this.responseCallbacks.remove(JSONObject2WVJBMessage.responseId);
                    if (remove != null) {
                        remove.callback(JSONObject2WVJBMessage.responseData);
                    }
                } else {
                    WVJBResponseCallback wVJBResponseCallback = null;
                    if (JSONObject2WVJBMessage.callbackId != null) {
                        final String str2 = JSONObject2WVJBMessage.callbackId;
                        wVJBResponseCallback = new WVJBResponseCallback() { // from class: com.yltx.oil.partner.modules.web.WVJBWebViewClient.2
                            @Override // com.yltx.oil.partner.modules.web.WVJBWebViewClient.WVJBResponseCallback
                            public void callback(Object obj) {
                                WVJBMessage wVJBMessage = new WVJBMessage();
                                wVJBMessage.responseId = str2;
                                wVJBMessage.responseData = obj;
                                WVJBWebViewClient.this.queueMessage(wVJBMessage);
                            }
                        };
                    }
                    if (JSONObject2WVJBMessage.handlerName != null) {
                        wVJBHandler = this.messageHandlers.get(JSONObject2WVJBMessage.handlerName);
                    } else {
                        wVJBHandler = this.messageHandler;
                    }
                    if (wVJBHandler != null) {
                        wVJBHandler.request(JSONObject2WVJBMessage.data, wVJBResponseCallback);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void log(String str, Object obj) {
        if (logging) {
            String valueOf = String.valueOf(obj);
            if (valueOf.length() > 500) {
                Log.i(kTag, str + ": " + valueOf.substring(0, 500) + " [...]");
                return;
            }
            Log.i(kTag, str + ": " + valueOf);
        }
    }

    public void executeJavascript(String str) {
        executeJavascript(str, null);
    }

    public void executeJavascript(String str, final JavascriptCallback javascriptCallback) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.webView.evaluateJavascript(str, new ValueCallback<String>() { // from class: com.yltx.oil.partner.modules.web.WVJBWebViewClient.3
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(String str2) {
                    if (javascriptCallback != null) {
                        if (str2 != null && str2.startsWith("\"") && str2.endsWith("\"")) {
                            str2 = str2.substring(1, str2.length() - 1).replaceAll("\\\\\"", "\"");
                        }
                        javascriptCallback.onReceiveValue(str2);
                    }
                }
            });
        } else if (javascriptCallback != null) {
            MyJavascriptInterface myJavascriptInterface = this.myInterface;
            StringBuilder sb = new StringBuilder();
            long j = this.uniqueId + 1;
            this.uniqueId = j;
            sb.append(j);
            sb.append("");
            myJavascriptInterface.addCallback(sb.toString(), javascriptCallback);
            WebView webView = this.webView;
            webView.loadUrl("javascript:window.WVJBInterface.onResultForScript(" + this.uniqueId + "," + str + ")");
        } else {
            WebView webView2 = this.webView;
            webView2.loadUrl(BridgeUtil.JAVASCRIPT_STR + str);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        try {
            InputStream open = this.webView.getContext().getAssets().open("WebViewJavascriptBridge.js.txt");
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            executeJavascript(new String(bArr));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (this.startupMessageQueue != null) {
            for (int i = 0; i < this.startupMessageQueue.size(); i++) {
                dispatchMessage(this.startupMessageQueue.get(i));
            }
            this.startupMessageQueue = null;
        }
        super.onPageFinished(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str.startsWith(kCustomProtocolScheme)) {
            if (str.indexOf(kQueueHasMessage) > 0) {
                flushMessageQueue();
                return true;
            }
            return true;
        }
        return super.shouldOverrideUrlLoading(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class WVJBMessage {
        String callbackId;
        Object data;
        String handlerName;
        Object responseData;
        String responseId;

        private WVJBMessage() {
            this.data = null;
            this.callbackId = null;
            this.handlerName = null;
            this.responseId = null;
            this.responseData = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class MyJavascriptInterface {
        Map<String, JavascriptCallback> map;

        private MyJavascriptInterface() {
            this.map = new HashMap();
        }

        public void addCallback(String str, JavascriptCallback javascriptCallback) {
            this.map.put(str, javascriptCallback);
        }

        @JavascriptInterface
        public void onResultForScript(String str, String str2) {
            Log.i(WVJBWebViewClient.kTag, "onResultForScript: " + str2);
            JavascriptCallback remove = this.map.remove(str);
            if (remove != null) {
                remove.onReceiveValue(str2);
            }
        }
    }

    @Override // android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        if (!ADFilterTool.hasNotAd(str)) {
            return super.shouldInterceptRequest(webView, str);
        }
        return new WebResourceResponse(null, null, null);
    }
}