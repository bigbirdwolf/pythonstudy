package com.yltx.oil.partner.data.response;

import java.util.List;

/* loaded from: classes.dex */
public class InviteDetailResp {
    private HeaderInfoBean headerInfo;
    private List<ListInfoBeanX> listInfo;

    public HeaderInfoBean getHeaderInfo() {
        return this.headerInfo;
    }

    public void setHeaderInfo(HeaderInfoBean headerInfoBean) {
        this.headerInfo = headerInfoBean;
    }

    public List<ListInfoBeanX> getListInfo() {
        return this.listInfo;
    }

    public void setListInfo(List<ListInfoBeanX> list) {
        this.listInfo = list;
    }

    /* loaded from: classes.dex */
    public static class HeaderInfoBean {
        private int cumulativeCount;
        private int effectiveCount;
        private int payCount;
        private int registerCount;

        public int getEffectiveCount() {
            return this.effectiveCount;
        }

        public void setEffectiveCount(int i) {
            this.effectiveCount = i;
        }

        public int getRegisterCount() {
            return this.registerCount;
        }

        public void setRegisterCount(int i) {
            this.registerCount = i;
        }

        public int getCumulativeCount() {
            return this.cumulativeCount;
        }

        public void setCumulativeCount(int i) {
            this.cumulativeCount = i;
        }

        public int getPayCount() {
            return this.payCount;
        }

        public void setPayCount(int i) {
            this.payCount = i;
        }
    }

    /* loaded from: classes.dex */
    public static class ListInfoBeanX {
        private String date;
        private List<ListInfoBean> listInfo;

        public String getDate() {
            return this.date;
        }

        public void setDate(String str) {
            this.date = str;
        }

        public List<ListInfoBean> getListInfo() {
            return this.listInfo;
        }

        public void setListInfo(List<ListInfoBean> list) {
            this.listInfo = list;
        }

        /* loaded from: classes.dex */
        public static class ListInfoBean {
            private String createTime;
            private String month;
            private String phone;
            private int rowId;
            private String type;

            public int getRowId() {
                return this.rowId;
            }

            public void setRowId(int i) {
                this.rowId = i;
            }

            public String getPhone() {
                return this.phone;
            }

            public void setPhone(String str) {
                this.phone = str;
            }

            public String getCreateTime() {
                return this.createTime;
            }

            public void setCreateTime(String str) {
                this.createTime = str;
            }

            public String getMonth() {
                return this.month;
            }

            public void setMonth(String str) {
                this.month = str;
            }

            public String getType() {
                return this.type;
            }

            public void setType(String str) {
                this.type = str;
            }
        }
    }
}