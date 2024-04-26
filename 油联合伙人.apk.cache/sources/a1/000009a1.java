package com.alipay.sdk.packet.impl;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class b extends com.alipay.sdk.packet.e {
    @Override // com.alipay.sdk.packet.e
    protected String b() {
        return "5.0.0";
    }

    @Override // com.alipay.sdk.packet.e
    protected JSONObject a() throws JSONException {
        return com.alipay.sdk.packet.e.a("sdkConfig", "obtain");
    }
}