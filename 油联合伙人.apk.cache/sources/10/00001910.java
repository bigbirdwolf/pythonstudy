package com.yltx.oil.partner.modules.mine.response;

/* loaded from: classes.dex */
public class MyfeedbackResponse {
    private String context;
    private String create_time;

    public MyfeedbackResponse(String str, String str2) {
        this.create_time = str;
        this.context = str2;
    }

    public String getCreate_time() {
        return this.create_time;
    }

    public void setCreate_time(String str) {
        this.create_time = str;
    }

    public String getContext() {
        return this.context;
    }

    public void setContext(String str) {
        this.context = str;
    }
}