package com.alipay.sdk.app;

/* loaded from: classes.dex */
public enum k {
    SUCCEEDED(AlipayResultActivity.a, "处理成功"),
    FAILED(AlipayResultActivity.c, "系统繁忙，请稍后再试"),
    CANCELED(6001, "用户取消"),
    NETWORK_ERROR(6002, "网络连接异常"),
    PARAMS_ERROR(4001, "参数错误"),
    DOUBLE_REQUEST(AlipayResultActivity.b, "重复请求"),
    PAY_WAITTING(8000, "支付结果确认中");
    
    private int h;
    private String i;

    k(int i, String str) {
        this.h = i;
        this.i = str;
    }

    public void a(int i) {
        this.h = i;
    }

    public int a() {
        return this.h;
    }

    public void a(String str) {
        this.i = str;
    }

    public String b() {
        return this.i;
    }

    public static k b(int i) {
        if (i != 4001) {
            if (i != 5000) {
                if (i != 8000) {
                    if (i == 9000) {
                        return SUCCEEDED;
                    }
                    switch (i) {
                        case 6001:
                            return CANCELED;
                        case 6002:
                            return NETWORK_ERROR;
                        default:
                            return FAILED;
                    }
                }
                return PAY_WAITTING;
            }
            return DOUBLE_REQUEST;
        }
        return PARAMS_ERROR;
    }
}