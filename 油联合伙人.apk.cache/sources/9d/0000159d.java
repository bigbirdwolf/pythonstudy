package com.yltx.oil.partner.data.network;

/* loaded from: classes.dex */
public class UserToken {
    private static UserToken instance;
    private String commonoil;
    private String headerPic;
    private String mail;
    private String nickName;
    private String phone;
    private String sex;
    private String token;
    private String userID;
    private String ylAccount;

    public static UserToken getInstance() {
        if (instance == null) {
            instance = new UserToken();
        }
        return instance;
    }

    public UserToken(String str, String str2, String str3) {
        this.userID = str;
        this.phone = str2;
        this.token = str3;
    }

    public UserToken() {
    }

    public String getYlAccount() {
        return this.ylAccount;
    }

    public void setYlAccount(String str) {
        this.ylAccount = str;
    }

    public String getCommonoil() {
        return this.commonoil;
    }

    public void setCommonoil(String str) {
        this.commonoil = str;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String str) {
        this.userID = str;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String str) {
        this.phone = str;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public void release() {
        instance = null;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public String getHeaderPic() {
        return this.headerPic;
    }

    public void setHeaderPic(String str) {
        this.headerPic = str;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String str) {
        this.sex = str;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String str) {
        this.mail = str;
    }

    public String toString() {
        return "UserToken{userID='" + this.userID + "', phone='" + this.phone + "', token='" + this.token + "'}";
    }
}