package com.yltx.oil.partner.data.response;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class LnvoicePayResp {
    private String aliPayStr;
    private String appid;
    private String charset;
    @SerializedName("data")
    private String dataX;
    private String extend;
    private String noncestr;
    private String orderId;
    @SerializedName("package")
    private String packageX;
    private String partnerid;
    private String payAmt;
    private String prepayid;
    private String quickIndexUrl;
    private String sign;
    private String signType;
    private String timestamp;
    private String voucherCode;

    public String getPackageX() {
        return this.packageX;
    }

    public void setPackageX(String str) {
        this.packageX = str;
    }

    public String getAppid() {
        return this.appid;
    }

    public void setAppid(String str) {
        this.appid = str;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public String getPartnerid() {
        return this.partnerid;
    }

    public void setPartnerid(String str) {
        this.partnerid = str;
    }

    public String getPrepayid() {
        return this.prepayid;
    }

    public void setPrepayid(String str) {
        this.prepayid = str;
    }

    public String getNoncestr() {
        return this.noncestr;
    }

    public void setNoncestr(String str) {
        this.noncestr = str;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }

    public String getExtend() {
        return this.extend;
    }

    public void setExtend(String str) {
        this.extend = str;
    }

    public String getCharset() {
        return this.charset;
    }

    public void setCharset(String str) {
        this.charset = str;
    }

    public String getDataX() {
        return this.dataX;
    }

    public void setDataX(String str) {
        this.dataX = str;
    }

    public String getSignType() {
        return this.signType;
    }

    public void setSignType(String str) {
        this.signType = str;
    }

    public String getQuickIndexUrl() {
        return this.quickIndexUrl;
    }

    public void setQuickIndexUrl(String str) {
        this.quickIndexUrl = str;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public String getAliPayStr() {
        return this.aliPayStr;
    }

    public void setAliPayStr(String str) {
        this.aliPayStr = str;
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
}