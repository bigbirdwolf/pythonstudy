package com.yltx.oil.partner.data.response;

import java.util.HashMap;

/* loaded from: classes.dex */
public class JsBridgeDrillBean {
    private String kind;
    private String module;
    private HashMap<String, String> params;

    public String getModule() {
        return this.module;
    }

    public void setModule(String str) {
        this.module = str;
    }

    public String getKind() {
        return this.kind;
    }

    public void setKind(String str) {
        this.kind = str;
    }

    public HashMap<String, String> getParams() {
        return this.params;
    }

    public void setParams(HashMap<String, String> hashMap) {
        this.params = hashMap;
    }

    public String toString() {
        return "JsBridgeDrillBean{module='" + this.module + "', kind='" + this.kind + "', params=" + this.params + '}';
    }

    /* loaded from: classes.dex */
    private class ParamsBean {
        private ParamsBean() {
        }
    }
}