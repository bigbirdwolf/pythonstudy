package com.github.lzyzsd.jsbridge;

/* loaded from: classes.dex */
public class DefaultHandler implements BridgeHandler {
    String TAG = "DefaultHandler";

    @Override // com.github.lzyzsd.jsbridge.BridgeHandler
    public void handler(String str, CallBackFunction callBackFunction) {
        if (callBackFunction != null) {
            callBackFunction.onCallBack("DefaultHandler response data");
        }
    }
}