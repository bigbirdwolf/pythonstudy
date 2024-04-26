package com.yltx.oil.partner.data.response;

import java.util.List;

/* loaded from: classes.dex */
public class RechargePayTypeResp {
    private OutPayBean outPay;

    public OutPayBean getOutPay() {
        return this.outPay;
    }

    public void setOutPay(OutPayBean outPayBean) {
        this.outPay = outPayBean;
    }

    /* loaded from: classes.dex */
    public static class OutPayBean {
        private String name;
        private List<OutPayListBean> outPayList;
        private String outUsePay;
        private String type;

        public String getOutUsePay() {
            return this.outUsePay;
        }

        public void setOutUsePay(String str) {
            this.outUsePay = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public List<OutPayListBean> getOutPayList() {
            return this.outPayList;
        }

        public void setOutPayList(List<OutPayListBean> list) {
            this.outPayList = list;
        }

        /* loaded from: classes.dex */
        public static class OutPayListBean {
            private boolean isChecked;
            private String name;
            private String type;

            public String getName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }

            public String getType() {
                return this.type;
            }

            public void setType(String str) {
                this.type = str;
            }

            public boolean isChecked() {
                return this.isChecked;
            }

            public void setChecked(boolean z) {
                this.isChecked = z;
            }
        }
    }
}