package com.yltx.oil.partner.data.response;

import java.io.Serializable;

/* loaded from: classes.dex */
public class LoginInfo implements Serializable {
    private double goodProportion;
    private String headPic;
    private String isPartner;
    private int level;
    private String levelName;
    private String name;
    private String nickname;
    private PartnerInfo partnerInfo;
    private String partnerLevel;
    private String phone;
    private double sale;
    private double saleNeed;
    private int userId;
    private UserInfo userInfo;
    private double ylBalanceAmount;

    public String getPartnerLevel() {
        return this.partnerLevel;
    }

    public void setPartnerLevel(String str) {
        this.partnerLevel = str;
    }

    public String getIsPartner() {
        return this.isPartner;
    }

    public void setIsPartner(String str) {
        this.isPartner = str;
    }

    public PartnerInfo getPartnerInfo() {
        return this.partnerInfo;
    }

    public void setPartnerInfo(PartnerInfo partnerInfo) {
        this.partnerInfo = partnerInfo;
    }

    public UserInfo getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public double getGoodProportion() {
        return this.goodProportion;
    }

    public void setGoodProportion(double d) {
        this.goodProportion = d;
    }

    public String getHeadPic() {
        return this.headPic;
    }

    public void setHeadPic(String str) {
        this.headPic = str;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public String getLevelName() {
        return this.levelName;
    }

    public void setLevelName(String str) {
        this.levelName = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
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

    public double getSale() {
        return this.sale;
    }

    public void setSale(double d) {
        this.sale = d;
    }

    public double getSaleNeed() {
        return this.saleNeed;
    }

    public void setSaleNeed(double d) {
        this.saleNeed = d;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int i) {
        this.userId = i;
    }

    public double getYlBalanceAmount() {
        return this.ylBalanceAmount;
    }

    public void setYlBalanceAmount(double d) {
        this.ylBalanceAmount = d;
    }

    /* loaded from: classes.dex */
    public class PartnerInfo {
        private String goodProportion;
        private String isPartner;
        private String level;
        private String levelName;
        private String row_id;
        private String sale;
        private String saleNeed;

        public PartnerInfo() {
        }

        public String getIsPartner() {
            return this.isPartner;
        }

        public void setIsPartner(String str) {
            this.isPartner = str;
        }

        public String getGoodProportion() {
            return this.goodProportion;
        }

        public void setGoodProportion(String str) {
            this.goodProportion = str;
        }

        public String getLevel() {
            return this.level;
        }

        public void setLevel(String str) {
            this.level = str;
        }

        public String getLevelName() {
            return this.levelName;
        }

        public void setLevelName(String str) {
            this.levelName = str;
        }

        public String getRow_id() {
            return this.row_id;
        }

        public void setRow_id(String str) {
            this.row_id = str;
        }

        public String getSale() {
            return this.sale;
        }

        public void setSale(String str) {
            this.sale = str;
        }

        public String getSaleNeed() {
            return this.saleNeed;
        }

        public void setSaleNeed(String str) {
            this.saleNeed = str;
        }
    }

    /* loaded from: classes.dex */
    public class UserInfo {
        private String account;
        private String attribute;
        private String avatar;
        private String idcard;
        private String isPush;
        private String mail;
        private String nickname;
        private String partnerType;
        private String phone;
        private String rowId;
        private String sex;
        private String token;

        public UserInfo() {
        }

        public String getIsPush() {
            return this.isPush;
        }

        public void setIsPush(String str) {
            this.isPush = str;
        }

        public String getPartnerType() {
            return this.partnerType;
        }

        public void setPartnerType(String str) {
            this.partnerType = str;
        }

        public String getRowId() {
            return this.rowId;
        }

        public void setRowId(String str) {
            this.rowId = str;
        }

        public String getNickname() {
            return this.nickname;
        }

        public void setNickname(String str) {
            this.nickname = str;
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

        public String getPhone() {
            return this.phone;
        }

        public void setPhone(String str) {
            this.phone = str;
        }

        public String getAvatar() {
            return this.avatar;
        }

        public void setAvatar(String str) {
            this.avatar = str;
        }

        public String getAccount() {
            return this.account;
        }

        public void setAccount(String str) {
            this.account = str;
        }

        public String getAttribute() {
            return this.attribute;
        }

        public void setAttribute(String str) {
            this.attribute = str;
        }

        public String getToken() {
            return this.token;
        }

        public void setToken(String str) {
            this.token = str;
        }

        public String getIdcard() {
            return this.idcard;
        }

        public void setIdcard(String str) {
            this.idcard = str;
        }
    }
}