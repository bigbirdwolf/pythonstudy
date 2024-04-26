package com.yltx.oil.partner.data.response;

/* loaded from: classes.dex */
public class AppLoginStatusResp {
    private String attribute;
    private String avatar;
    private String idcard;
    private String isPush;
    private String mail;
    private int nationFuelcardLevel;
    private String nickname;
    private String phone;
    private int rowId;
    private String token;

    public int getRowId() {
        return this.rowId;
    }

    public void setRowId(int i) {
        this.rowId = i;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String str) {
        this.nickname = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String str) {
        this.phone = str;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String str) {
        this.mail = str;
    }

    public String getAttribute() {
        return this.attribute;
    }

    public void setAttribute(String str) {
        this.attribute = str;
    }

    public String getIdcard() {
        return this.idcard;
    }

    public void setIdcard(String str) {
        this.idcard = str;
    }

    public String getIsPush() {
        return this.isPush;
    }

    public void setIsPush(String str) {
        this.isPush = str;
    }

    public int getNationFuelcardLevel() {
        return this.nationFuelcardLevel;
    }

    public void setNationFuelcardLevel(int i) {
        this.nationFuelcardLevel = i;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }
}