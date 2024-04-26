package com.yltx.oil.partner.data.network;

/* loaded from: classes.dex */
public class HttpResult<T> {
    private String code;
    private T data;
    private String msg;

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T t) {
        this.data = t;
    }

    public String toString() {
        return "HttpResult{code='" + this.code + "', msg='" + this.msg + "', data=" + this.data + '}';
    }
}