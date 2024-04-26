package com.yltx.oil.partner.data.response;

/* loaded from: classes.dex */
public class WeChatPayResultEvent {
    public static int wechat_cancel_pay = -1001;
    public static int wechat_success_pay = 1001;
    private int what = -1;

    public int getWhat() {
        return this.what;
    }

    public void setWhat(int i) {
        this.what = i;
    }
}