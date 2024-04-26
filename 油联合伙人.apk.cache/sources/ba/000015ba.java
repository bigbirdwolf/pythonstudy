package com.yltx.oil.partner.data.response;

/* loaded from: classes.dex */
public class BaseInfo {
    private DataBean data;

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    /* loaded from: classes.dex */
    public static class DataBean {
        private String code;
        private String msg;

        public String getCode() {
            return this.code;
        }

        public void setCode(String str) {
            this.code = str;
        }

        public String getMsg() {
            return this.msg;
        }

        public void setMsg(String str) {
            this.msg = str;
        }
    }
}