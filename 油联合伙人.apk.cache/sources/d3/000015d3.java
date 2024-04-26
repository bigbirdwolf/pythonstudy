package com.yltx.oil.partner.data.response;

/* loaded from: classes.dex */
public class RegisterResp {
    private String attribute;
    private String isPush;
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

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String str) {
        this.phone = str;
    }

    public String getAttribute() {
        return this.attribute;
    }

    public void setAttribute(String str) {
        this.attribute = str;
    }

    public String getIsPush() {
        return this.isPush;
    }

    public void setIsPush(String str) {
        this.isPush = str;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }
}