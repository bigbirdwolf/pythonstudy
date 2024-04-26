package com.yltx.oil.partner.modules.profit.response;

import java.util.List;

/* loaded from: classes.dex */
public class GeneralizeResponse {
    private List<DataBean> data;
    private TotalDataBean totalData;

    public TotalDataBean getTotalData() {
        return this.totalData;
    }

    public void setTotalData(TotalDataBean totalDataBean) {
        this.totalData = totalDataBean;
    }

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> list) {
        this.data = list;
    }

    /* loaded from: classes.dex */
    public static class TotalDataBean {
        private double orderAmount;
        private int orderCount;
        private double rebateAmount;
        private int watchCount;

        public double getOrderAmount() {
            return this.orderAmount;
        }

        public void setOrderAmount(double d) {
            this.orderAmount = d;
        }

        public double getRebateAmount() {
            return this.rebateAmount;
        }

        public void setRebateAmount(double d) {
            this.rebateAmount = d;
        }

        public int getOrderCount() {
            return this.orderCount;
        }

        public void setOrderCount(int i) {
            this.orderCount = i;
        }

        public int getWatchCount() {
            return this.watchCount;
        }

        public void setWatchCount(int i) {
            this.watchCount = i;
        }
    }

    /* loaded from: classes.dex */
    public static class DataBean {
        private int buyCount;
        private String dateTime;
        private double orderAmount;
        private double rebateAmount;
        private int watchCount;

        public String getDateTime() {
            return this.dateTime;
        }

        public void setDateTime(String str) {
            this.dateTime = str;
        }

        public int getBuyCount() {
            return this.buyCount;
        }

        public void setBuyCount(int i) {
            this.buyCount = i;
        }

        public double getOrderAmount() {
            return this.orderAmount;
        }

        public void setOrderAmount(double d) {
            this.orderAmount = d;
        }

        public double getRebateAmount() {
            return this.rebateAmount;
        }

        public void setRebateAmount(double d) {
            this.rebateAmount = d;
        }

        public int getWatchCount() {
            return this.watchCount;
        }

        public void setWatchCount(int i) {
            this.watchCount = i;
        }
    }
}