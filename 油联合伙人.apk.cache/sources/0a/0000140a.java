package com.umeng.socialize;

import com.alipay.sdk.app.AlipayResultActivity;

/* loaded from: classes.dex */
public class SocializeException extends RuntimeException {
    private static final long b = 1;
    protected int a;
    private String c;

    public int getErrorCode() {
        return this.a;
    }

    public SocializeException(int i, String str) {
        super(str);
        this.a = AlipayResultActivity.b;
        this.c = "";
        this.a = i;
        this.c = str;
    }

    public SocializeException(String str, Throwable th) {
        super(str, th);
        this.a = AlipayResultActivity.b;
        this.c = "";
        this.c = str;
    }

    public SocializeException(String str) {
        super(str);
        this.a = AlipayResultActivity.b;
        this.c = "";
        this.c = str;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.c;
    }
}