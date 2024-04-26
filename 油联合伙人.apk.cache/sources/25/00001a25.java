package com.yltx.oil.partner.modules.profit.response;

/* loaded from: classes.dex */
public class CommissionResponse {
    private int buyCount;
    private String msg;
    private double orderAmount;
    private double rebateAmount;
    private String result;
    private int watchCount;

    public String getResult() {
        return this.result;
    }

    public void setResult(String str) {
        this.result = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public int getBuyCount() {
        return this.buyCount;
    }

    public void setBuyCount(int i) {
        this.buyCount = i;
    }

    public double getOrderAmount() {
        return this.orderAmount;
    }

    public void setOrderAmount(double d) {
        this.orderAmount = d;
    }

    public double getRebateAmount() {
        return this.rebateAmount;
    }

    public void setRebateAmount(double d) {
        this.rebateAmount = d;
    }

    public int getWatchCount() {
        return this.watchCount;
    }

    public void setWatchCount(int i) {
        this.watchCount = i;
    }
}