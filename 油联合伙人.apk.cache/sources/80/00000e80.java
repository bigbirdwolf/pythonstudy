package com.github.lzyzsd.jsbridge;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.WebView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint({"SetJavaScriptEnabled"})
/* loaded from: classes.dex */
public class BridgeWebView extends WebView implements WebViewJavascriptBridge {
    public static final String toLoadJs = "WebViewJavascriptBridge.js";
    private final String TAG;
    BridgeHandler defaultHandler;
    Map<String, BridgeHandler> messageHandlers;
    Map<String, CallBackFunction> responseCallbacks;
    private List<Message> startupMessage;
    private long uniqueId;

    public List<Message> getStartupMessage() {
        return this.startupMessage;
    }

    public void setStartupMessage(List<Message> list) {
        this.startupMessage = list;
    }

    public BridgeWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "BridgeWebView";
        this.responseCallbacks = new HashMap();
        this.messageHandlers = new HashMap();
        this.defaultHandler = new DefaultHandler();
        this.startupMessage = new ArrayList();
        this.uniqueId = 0L;
        init();
    }

    public BridgeWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = "BridgeWebView";
        this.responseCallbacks = new HashMap();
        this.messageHandlers = new HashMap();
        this.defaultHandler = new DefaultHandler();
        this.startupMessage = new ArrayList();
        this.uniqueId = 0L;
        init();
    }

    public BridgeWebView(Context context) {
        super(context);
        this.TAG = "BridgeWebView";
        this.responseCallbacks = new HashMap();
        this.messageHandlers = new HashMap();
        this.defaultHandler = new DefaultHandler();
        this.startupMessage = new ArrayList();
        this.uniqueId = 0L;
        init();
    }

    public void setDefaultHandler(BridgeHandler bridgeHandler) {
        this.defaultHandler = bridgeHandler;
    }

    private void init() {
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        setWebViewClient(generateBridgeWebViewClient());
    }

    protected BridgeWebViewClient generateBridgeWebViewClient() {
        return new BridgeWebViewClient(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handlerReturnData(String str) {
        String functionFromReturnUrl = BridgeUtil.getFunctionFromReturnUrl(str);
        CallBackFunction callBackFunction = this.responseCallbacks.get(functionFromReturnUrl);
        String dataFromReturnUrl = BridgeUtil.getDataFromReturnUrl(str);
        if (callBackFunction != null) {
            callBackFunction.onCallBack(dataFromReturnUrl);
            this.responseCallbacks.remove(functionFromReturnUrl);
        }
    }

    @Override // com.github.lzyzsd.jsbridge.WebViewJavascriptBridge
    public void send(String str) {
        send(str, null);
    }

    @Override // com.github.lzyzsd.jsbridge.WebViewJavascriptBridge
    public void send(String str, CallBackFunction callBackFunction) {
        doSend(null, str, callBackFunction);
    }

    private void doSend(String str, String str2, CallBackFunction callBackFunction) {
        Message message = new Message();
        if (!TextUtils.isEmpty(str2)) {
            message.setData(str2);
        }
        if (callBackFunction != null) {
            StringBuilder sb = new StringBuilder();
            long j = this.uniqueId + 1;
            this.uniqueId = j;
            sb.append(j);
            sb.append("_");
            sb.append(SystemClock.currentThreadTimeMillis());
            String format = String.format("JAVA_CB_%s", sb.toString());
            this.responseCallbacks.put(format, callBackFunction);
            message.setCallbackId(format);
        }
        if (!TextUtils.isEmpty(str)) {
            message.setHandlerName(str);
        }
        queueMessage(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queueMessage(Message message) {
        if (this.startupMessage != null) {
            this.startupMessage.add(message);
        } else {
            dispatchMessage(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchMessage(Message message) {
        String format = String.format("javascript:WebViewJavascriptBridge._handleMessageFromNative('%s');", message.toJson().replaceAll("(\\\\)([^utrn])", "\\\\\\\\$1$2").replaceAll("(?<=[^\\\\])(\")", "\\\\\""));
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            loadUrl(format);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void flushMessageQueue() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            loadUrl("javascript:WebViewJavascriptBridge._fetchQueue();", new CallBackFunction() { // from class: com.github.lzyzsd.jsbridge.BridgeWebView.1
                @Override // com.github.lzyzsd.jsbridge.CallBackFunction
                public void onCallBack(String str) {
                    CallBackFunction callBackFunction;
                    BridgeHandler bridgeHandler;
                    try {
                        List<Message> arrayList = Message.toArrayList(str);
                        if (arrayList == null || arrayList.size() == 0) {
                            return;
                        }
                        for (int i = 0; i < arrayList.size(); i++) {
                            Message message = arrayList.get(i);
                            String responseId = message.getResponseId();
                            if (!TextUtils.isEmpty(responseId)) {
                                BridgeWebView.this.responseCallbacks.get(responseId).onCallBack(message.getResponseData());
                                BridgeWebView.this.responseCallbacks.remove(responseId);
                            } else {
                                final String callbackId = message.getCallbackId();
                                if (!TextUtils.isEmpty(callbackId)) {
                                    callBackFunction = new CallBackFunction() { // from class: com.github.lzyzsd.jsbridge.BridgeWebView.1.1
                                        @Override // com.github.lzyzsd.jsbridge.CallBackFunction
                                        public void onCallBack(String str2) {
                                            Message message2 = new Message();
                                            message2.setResponseId(callbackId);
                                            message2.setResponseData(str2);
                                            BridgeWebView.this.queueMessage(message2);
                                        }
                                    };
                                } else {
                                    callBackFunction = new CallBackFunction() { // from class: com.github.lzyzsd.jsbridge.BridgeWebView.1.2
                                        @Override // com.github.lzyzsd.jsbridge.CallBackFunction
                                        public void onCallBack(String str2) {
                                        }
                                    };
                                }
                                if (!TextUtils.isEmpty(message.getHandlerName())) {
                                    bridgeHandler = BridgeWebView.this.messageHandlers.get(message.getHandlerName());
                                } else {
                                    bridgeHandler = BridgeWebView.this.defaultHandler;
                                }
                                if (bridgeHandler != null) {
                                    bridgeHandler.handler(message.getData(), callBackFunction);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void loadUrl(String str, CallBackFunction callBackFunction) {
        loadUrl(str);
        this.responseCallbacks.put(BridgeUtil.parseFunctionName(str), callBackFunction);
    }

    public void registerHandler(String str, BridgeHandler bridgeHandler) {
        if (bridgeHandler != null) {
            this.messageHandlers.put(str, bridgeHandler);
        }
    }

    public void callHandler(String str, String str2, CallBackFunction callBackFunction) {
        doSend(str, str2, callBackFunction);
    }
}