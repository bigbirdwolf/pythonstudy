package com.yltx.oil.partner.modules.oiltrade.response;

/* loaded from: classes.dex */
public class FuelCardDetailsResponse {
    private double SpecialPrice;
    private int amt;
    private int buyCount;
    private double commission;
    private String commissionRate;
    private double discount;
    private String fmname;
    private int fuelId;
    private String name;
    private int num;
    private String photo;
    private double preferentialAmt;
    private int rowId;

    public int getBuyCount() {
        return this.buyCount;
    }

    public void setBuyCount(int i) {
        this.buyCount = i;
    }

    public String getFmname() {
        return this.fmname;
    }

    public void setFmname(String str) {
        this.fmname = str;
    }

    public String getCommissionRate() {
        return this.commissionRate;
    }

    public void setCommissionRate(String str) {
        this.commissionRate = str;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int i) {
        this.num = i;
    }

    public double getSpecialPrice() {
        return this.SpecialPrice;
    }

    public void setSpecialPrice(double d) {
        this.SpecialPrice = d;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String str) {
        this.photo = str;
    }

    public int getAmt() {
        return this.amt;
    }

    public void setAmt(int i) {
        this.amt = i;
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setDiscount(double d) {
        this.discount = d;
    }

    public double getCommission() {
        return this.commission;
    }

    public void setCommission(double d) {
        this.commission = d;
    }

    public double getPreferentialAmt() {
        return this.preferentialAmt;
    }

    public void setPreferentialAmt(double d) {
        this.preferentialAmt = d;
    }

    public int getFuelId() {
        return this.fuelId;
    }

    public void setFuelId(int i) {
        this.fuelId = i;
    }

    public int getRowId() {
        return this.rowId;
    }

    public void setRowId(int i) {
        this.rowId = i;
    }
}