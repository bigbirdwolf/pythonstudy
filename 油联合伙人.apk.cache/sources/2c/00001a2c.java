package com.yltx.oil.partner.modules.profit.response;

import java.util.List;

/* loaded from: classes.dex */
public class ManageResponse {
    private String msg;
    private String result;
    private StationDataBean stationData;
    private List<StationRecordData> stationRecordData;

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

    public StationDataBean getStationData() {
        return this.stationData;
    }

    public void setStationData(StationDataBean stationDataBean) {
        this.stationData = stationDataBean;
    }

    public List<StationRecordData> getStationRecordData() {
        return this.stationRecordData;
    }

    public void setStationRecordData(List<StationRecordData> list) {
        this.stationRecordData = list;
    }

    /* loaded from: classes.dex */
    public static class StationDataBean {
        private int cancelAmount;
        private int cancelCount;
        private double getAmount;
        private int getCount;
        private double realpayAmount;
        private Object year;

        public int getCancelCount() {
            return this.cancelCount;
        }

        public void setCancelCount(int i) {
            this.cancelCount = i;
        }

        public double getRealpayAmount() {
            return this.realpayAmount;
        }

        public void setRealpayAmount(double d) {
            this.realpayAmount = d;
        }

        public Object getYear() {
            return this.year;
        }

        public void setYear(Object obj) {
            this.year = obj;
        }

        public int getCancelAmount() {
            return this.cancelAmount;
        }

        public void setCancelAmount(int i) {
            this.cancelAmount = i;
        }

        public int getGetCount() {
            return this.getCount;
        }

        public void setGetCount(int i) {
            this.getCount = i;
        }

        public double getGetAmount() {
            return this.getAmount;
        }

        public void setGetAmount(double d) {
            this.getAmount = d;
        }
    }

    /* loaded from: classes.dex */
    public static class StationRecordData {
        String phone;
        String realpayAmount;
        String time;

        public String getRealpayAmount() {
            return this.realpayAmount;
        }

        public void setRealpayAmount(String str) {
            this.realpayAmount = str;
        }

        public String getTime() {
            return this.time;
        }

        public void setTime(String str) {
            this.time = str;
        }

        public String getPhone() {
            return this.phone;
        }

        public void setPhone(String str) {
            this.phone = str;
        }
    }
}