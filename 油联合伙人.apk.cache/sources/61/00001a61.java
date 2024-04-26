package com.yltx.oil.partner.mvp.exception;

/* loaded from: classes.dex */
public class BizException extends Exception {
    private final String code;
    private final String message;

    public BizException(String str, String str2) {
        super(str2);
        this.code = str;
        this.message = str2;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }
}