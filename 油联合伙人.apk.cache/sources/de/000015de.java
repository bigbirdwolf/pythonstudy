package com.yltx.oil.partner.data.response;

import java.util.List;

/* loaded from: classes.dex */
public class TxHistoryResp {
    private String totalMoney;
    private List<TxListBean> txList;

    public String getTotalMoney() {
        return this.totalMoney;
    }

    public void setTotalMoney(String str) {
        this.totalMoney = str;
    }

    public List<TxListBean> getTxList() {
        return this.txList;
    }

    public void setTxList(List<TxListBean> list) {
        this.txList = list;
    }

    /* loaded from: classes.dex */
    public static class TxListBean {
        private String applyTime;
        private Object finishTime;
        private String rowId;
        private String status;
        private String statusName;
        private String txFee;
        private String txMoney;
        private String txNo;
        private String value;

        public Object getFinishTime() {
            return this.finishTime;
        }

        public void setFinishTime(Object obj) {
            this.finishTime = obj;
        }

        public String getTxNo() {
            return this.txNo;
        }

        public void setTxNo(String str) {
            this.txNo = str;
        }

        public String getStatusName() {
            return this.statusName;
        }

        public void setStatusName(String str) {
            this.statusName = str;
        }

        public String getTxMoney() {
            return this.txMoney;
        }

        public void setTxMoney(String str) {
            this.txMoney = str;
        }

        public String getTxFee() {
            return this.txFee;
        }

        public void setTxFee(String str) {
            this.txFee = str;
        }

        public String getApplyTime() {
            return this.applyTime;
        }

        public void setApplyTime(String str) {
            this.applyTime = str;
        }

        public String getValue() {
            return this.value;
        }

        public void setValue(String str) {
            this.value = str;
        }

        public String getRowId() {
            return this.rowId;
        }

        public void setRowId(String str) {
            this.rowId = str;
        }

        public String getStatus() {
            return this.status;
        }

        public void setStatus(String str) {
            this.status = str;
        }
    }
}