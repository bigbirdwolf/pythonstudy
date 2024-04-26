package com.yltx.oil.partner.modules.profit.response;

import java.util.List;

/* loaded from: classes.dex */
public class UserAccountConsumeResponse {
    private List<AccountConsumeitem> consumeList;
    private String isAuth;
    private String totalMoney;

    public String getIsAuth() {
        return this.isAuth;
    }

    public void setIsAuth(String str) {
        this.isAuth = str;
    }

    public String getTotalMoney() {
        return this.totalMoney;
    }

    public void setTotalMoney(String str) {
        this.totalMoney = str;
    }

    public List<AccountConsumeitem> getConsumeList() {
        return this.consumeList;
    }

    public void setConsumeList(List<AccountConsumeitem> list) {
        this.consumeList = list;
    }

    /* loaded from: classes.dex */
    public class AccountConsumeitem {
        private double consumeAmt;
        private String consumeTime;
        private String consumeType;
        private String remark;

        public AccountConsumeitem() {
        }

        public double getConsumeAmt() {
            return this.consumeAmt;
        }

        public void setConsumeAmt(double d) {
            this.consumeAmt = d;
        }

        public String getConsumeTime() {
            return this.consumeTime;
        }

        public void setConsumeTime(String str) {
            this.consumeTime = str;
        }

        public String getConsumeType() {
            return this.consumeType;
        }

        public void setConsumeType(String str) {
            this.consumeType = str;
        }

        public String getRemark() {
            return this.remark;
        }

        public void setRemark(String str) {
            this.remark = str;
        }
    }
}