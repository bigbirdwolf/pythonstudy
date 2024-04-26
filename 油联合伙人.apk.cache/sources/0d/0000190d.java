package com.yltx.oil.partner.modules.mine.response;

/* loaded from: classes.dex */
public class ComplaintResponse {
    private String goodsName;
    private String orderTime;
    private String photo;
    private int remainDays;
    private String saleAmount;
    private String storeName;
    private double totalAmount;
    private String voucherCode;

    public String getSaleAmount() {
        return this.saleAmount;
    }

    public void setSaleAmount(String str) {
        this.saleAmount = str;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreName(String str) {
        this.storeName = str;
    }

    public String getGoodsName() {
        return this.goodsName;
    }

    public void setGoodsName(String str) {
        this.goodsName = str;
    }

    public String getOrderTime() {
        return this.orderTime;
    }

    public void setOrderTime(String str) {
        this.orderTime = str;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String str) {
        this.photo = str;
    }

    public int getRemainDays() {
        return this.remainDays;
    }

    public void setRemainDays(int i) {
        this.remainDays = i;
    }

    public double getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(double d) {
        this.totalAmount = d;
    }

    public String getVoucherCode() {
        return this.voucherCode;
    }

    public void setVoucherCode(String str) {
        this.voucherCode = str;
    }
}