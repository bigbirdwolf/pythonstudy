package com.yltx.oil.partner.modules.profit.response;

import java.util.List;

/* loaded from: classes.dex */
public class AllordersResponse {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> list) {
        this.data = list;
    }

    /* loaded from: classes.dex */
    public static class DataBean {
        private int count;
        private String days;
        private String name;
        private double orderAmount;
        private String orderTime;
        private Object photo;
        private double rebateAmount;
        private double rebateRate;
        private String status;
        private String voucherCode;

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public double getOrderAmount() {
            return this.orderAmount;
        }

        public void setOrderAmount(double d) {
            this.orderAmount = d;
        }

        public String getOrderTime() {
            return this.orderTime;
        }

        public void setOrderTime(String str) {
            this.orderTime = str;
        }

        public double getRebateAmount() {
            return this.rebateAmount;
        }

        public void setRebateAmount(double d) {
            this.rebateAmount = d;
        }

        public int getCount() {
            return this.count;
        }

        public void setCount(int i) {
            this.count = i;
        }

        public double getRebateRate() {
            return this.rebateRate;
        }

        public void setRebateRate(double d) {
            this.rebateRate = d;
        }

        public String getDays() {
            return this.days;
        }

        public void setDays(String str) {
            this.days = str;
        }

        public Object getPhoto() {
            return this.photo;
        }

        public void setPhoto(Object obj) {
            this.photo = obj;
        }

        public String getVoucherCode() {
            return this.voucherCode;
        }

        public void setVoucherCode(String str) {
            this.voucherCode = str;
        }

        public String getStatus() {
            return this.status;
        }

        public void setStatus(String str) {
            this.status = str;
        }
    }
}