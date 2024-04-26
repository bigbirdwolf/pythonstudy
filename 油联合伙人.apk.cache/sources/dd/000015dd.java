package com.yltx.oil.partner.data.response;

/* loaded from: classes.dex */
public class StoredValueCardResp {
    private double amt;
    private int buyCount;
    private int cardId;
    private int cardLisId;
    private String cardName;
    private double commission;
    private String commissionRate;
    private String content;
    private String issueType;
    private String photo;
    private String type;
    private String useRule;

    public int getBuyCount() {
        return this.buyCount;
    }

    public void setBuyCount(int i) {
        this.buyCount = i;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getIssueType() {
        return this.issueType;
    }

    public void setIssueType(String str) {
        this.issueType = str;
    }

    public String getCommissionRate() {
        return this.commissionRate;
    }

    public void setCommissionRate(String str) {
        this.commissionRate = str;
    }

    public String getCardName() {
        return this.cardName;
    }

    public void setCardName(String str) {
        this.cardName = str;
    }

    public int getCardLisId() {
        return this.cardLisId;
    }

    public void setCardLisId(int i) {
        this.cardLisId = i;
    }

    public int getCardId() {
        return this.cardId;
    }

    public void setCardId(int i) {
        this.cardId = i;
    }

    public double getAmt() {
        return this.amt;
    }

    public void setAmt(double d) {
        this.amt = d;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String str) {
        this.photo = str;
    }

    public double getCommission() {
        return this.commission;
    }

    public void setCommission(double d) {
        this.commission = d;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getUseRule() {
        return this.useRule;
    }

    public void setUseRule(String str) {
        this.useRule = str;
    }
}