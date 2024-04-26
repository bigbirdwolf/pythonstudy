package com.yltx.oil.partner.mvp.exception;

/* loaded from: classes.dex */
public class ServerError extends Exception {
    private String msg;
    private String respCode;

    public ServerError(String str, String str2) {
        super(str2);
        this.respCode = str;
        this.msg = str2;
    }

    public String getRespCode() {
        return this.respCode;
    }

    public String getMsg() {
        return this.msg;
    }
}