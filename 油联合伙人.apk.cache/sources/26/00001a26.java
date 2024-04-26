package com.yltx.oil.partner.modules.profit.response;

import java.util.List;

/* loaded from: classes.dex */
public class FragmentProfit_yjjsjl_Response {
    private double currentRebateAmount;
    private double lastMonthRebateAmount;
    private String msg;
    private List<RecordBean> record;
    private String result;

    public String getResult() {
        return this.result;
    }

    public void setResult(String str) {
        this.result = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public double getCurrentRebateAmount() {
        return this.currentRebateAmount;
    }

    public void setCurrentRebateAmount(double d) {
        this.currentRebateAmount = d;
    }

    public double getLastMonthRebateAmount() {
        return this.lastMonthRebateAmount;
    }

    public void setLastMonthRebateAmount(double d) {
        this.lastMonthRebateAmount = d;
    }

    public List<RecordBean> getRecord() {
        return this.record;
    }

    public void setRecord(List<RecordBean> list) {
        this.record = list;
    }

    /* loaded from: classes.dex */
    public static class RecordBean {
        private String dateTime;
        private double rebateAmount;

        public String getDateTime() {
            return this.dateTime;
        }

        public void setDateTime(String str) {
            this.dateTime = str;
        }

        public double getRebateAmount() {
            return this.rebateAmount;
        }

        public void setRebateAmount(double d) {
            this.rebateAmount = d;
        }
    }
}