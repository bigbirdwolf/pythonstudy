package com.yltx.oil.partner.data.response;

/* loaded from: classes.dex */
public class GiftCardResp {
    private int buyCount;
    private String commission;
    private String commissionRate;
    private String name;
    private String photo;
    private int rowId;
    private double saleprice;

    public int getBuyCount() {
        return this.buyCount;
    }

    public void setBuyCount(int i) {
        this.buyCount = i;
    }

    public String getCommissionRate() {
        return this.commissionRate;
    }

    public void setCommissionRate(String str) {
        this.commissionRate = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public double getSaleprice() {
        return this.saleprice;
    }

    public void setSaleprice(double d) {
        this.saleprice = d;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String str) {
        this.photo = str;
    }

    public String getCommission() {
        return this.commission;
    }

    public void setCommission(String str) {
        this.commission = str;
    }

    public int getRowId() {
        return this.rowId;
    }

    public void setRowId(int i) {
        this.rowId = i;
    }
}