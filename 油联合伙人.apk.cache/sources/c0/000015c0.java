package com.yltx.oil.partner.data.response;

/* loaded from: classes.dex */
public class HomeGoodsResponse {
    private String pbuys;
    private String pcash;
    private String pcommend;
    private String pname;
    private String pnorm;
    private String pphoto;
    private String pprice;
    private String prodid;
    private String pstocks;
    private String ptype;
    private String quotas;
    private String quotastype;
    private String storeName;
    private String storeid;
    private int type;

    public String toString() {
        return "HomeGoodsResponse{pstocks='" + this.pstocks + "', quotas='" + this.quotas + "', pbuys='" + this.pbuys + "', pname='" + this.pname + "', pprice='" + this.pprice + "', ptype='" + this.ptype + "', prodid='" + this.prodid + "', storeid='" + this.storeid + "', quotastype='" + this.quotastype + "', pnorm='" + this.pnorm + "', pcommend='" + this.pcommend + "', pphoto='" + this.pphoto + "', storeName='" + this.storeName + "', pcash='" + this.pcash + "'}";
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getPstocks() {
        return this.pstocks;
    }

    public void setPstocks(String str) {
        this.pstocks = str;
    }

    public String getQuotas() {
        return this.quotas;
    }

    public void setQuotas(String str) {
        this.quotas = str;
    }

    public String getPbuys() {
        return this.pbuys;
    }

    public void setPbuys(String str) {
        this.pbuys = str;
    }

    public String getPname() {
        return this.pname;
    }

    public void setPname(String str) {
        this.pname = str;
    }

    public String getPprice() {
        return this.pprice;
    }

    public void setPprice(String str) {
        this.pprice = str;
    }

    public String getPtype() {
        return this.ptype;
    }

    public void setPtype(String str) {
        this.ptype = str;
    }

    public String getProdid() {
        return this.prodid;
    }

    public void setProdid(String str) {
        this.prodid = str;
    }

    public String getStoreid() {
        return this.storeid;
    }

    public void setStoreid(String str) {
        this.storeid = str;
    }

    public String getQuotastype() {
        return this.quotastype;
    }

    public void setQuotastype(String str) {
        this.quotastype = str;
    }

    public String getPnorm() {
        return this.pnorm;
    }

    public void setPnorm(String str) {
        this.pnorm = str;
    }

    public String getPcommend() {
        return this.pcommend;
    }

    public void setPcommend(String str) {
        this.pcommend = str;
    }

    public String getPphoto() {
        return this.pphoto;
    }

    public void setPphoto(String str) {
        this.pphoto = str;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreName(String str) {
        this.storeName = str;
    }

    public String getPcash() {
        return this.pcash;
    }

    public void setPcash(String str) {
        this.pcash = str;
    }
}