package com.yltx.oil.partner.data.response;

/* loaded from: classes.dex */
public class ALiPayResp {
    private String aliPayStr;
    private String isfinish;
    private String payAmt;
    private String payType;
    private String rowId;
    private String voucherCode;

    public String getPayType() {
        return this.payType;
    }

    public void setPayType(String str) {
        this.payType = str;
    }

    public String getAliPayStr() {
        return this.aliPayStr;
    }

    public void setAliPayStr(String str) {
        this.aliPayStr = str;
    }

    public String getIsfinish() {
        return this.isfinish;
    }

    public void setIsfinish(String str) {
        this.isfinish = str;
    }

    public String getPayAmt() {
        return this.payAmt;
    }

    public void setPayAmt(String str) {
        this.payAmt = str;
    }

    public String getVoucherCode() {
        return this.voucherCode;
    }

    public void setVoucherCode(String str) {
        this.voucherCode = str;
    }

    public String getRowId() {
        return this.rowId;
    }

    public void setRowId(String str) {
        this.rowId = str;
    }
}