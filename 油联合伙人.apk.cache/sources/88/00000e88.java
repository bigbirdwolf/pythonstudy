package com.github.lzyzsd.jsbridge;

/* loaded from: classes.dex */
public interface WebViewJavascriptBridge {
    void send(String str);

    void send(String str, CallBackFunction callBackFunction);
}