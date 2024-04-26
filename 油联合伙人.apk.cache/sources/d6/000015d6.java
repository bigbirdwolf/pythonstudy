package com.yltx.oil.partner.data.response;

import java.util.List;

/* loaded from: classes.dex */
public class ShiftInfo {
    private DataBean data;
    private String img;
    private int itemType;
    private String msg;

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String str) {
        this.img = str;
    }

    public int getItemType() {
        return this.itemType;
    }

    public void setItemType(int i) {
        this.itemType = i;
    }

    /* loaded from: classes.dex */
    public class DataBean {
        private List<CardInfo> cardInfo;
        private List<CardInfo> machionData;
        private List<CardInfo> recordDetailMap;
        private List<CardInfo> recordMap;
        private List<CardInfo> stationDetailMap;

        public DataBean() {
        }

        public List<CardInfo> getRecordMap() {
            return this.recordMap;
        }

        public void setRecordMap(List<CardInfo> list) {
            this.recordMap = list;
        }

        public List<CardInfo> getRecordDetailMap() {
            return this.recordDetailMap;
        }

        public void setRecordDetailMap(List<CardInfo> list) {
            this.recordDetailMap = list;
        }

        public List<CardInfo> getStationDetailMap() {
            return this.stationDetailMap;
        }

        public void setStationDetailMap(List<CardInfo> list) {
            this.stationDetailMap = list;
        }

        public List<CardInfo> getMachionData() {
            return this.machionData;
        }

        public void setMachionData(List<CardInfo> list) {
            this.machionData = list;
        }

        public List<CardInfo> getCardInfo() {
            return this.cardInfo;
        }

        public void setCardInfo(List<CardInfo> list) {
            this.cardInfo = list;
        }
    }

    /* loaded from: classes.dex */
    public class CardInfo {
        private String balance;
        private String card;
        private String cardNumber;
        private String code;
        private String data;
        private String device_id;
        private String gCode;
        private boolean isSelected = false;
        private String money;
        private String msg;
        private String pcr_id;
        private String photo;
        private int rowId;
        private String row_id;
        private String sDeviceID;
        private String stationId;
        private String station_id;
        private String time;
        private int type;
        private String vol;
        private String vol_count;
        private String vtot;

        public CardInfo() {
        }

        public String getMsg() {
            return this.msg;
        }

        public void setMsg(String str) {
            this.msg = str;
        }

        public String getData() {
            return this.data;
        }

        public void setData(String str) {
            this.data = str;
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String str) {
            this.code = str;
        }

        public String getDevice_id() {
            return this.device_id;
        }

        public void setDevice_id(String str) {
            this.device_id = str;
        }

        public String getPcr_id() {
            return this.pcr_id;
        }

        public void setPcr_id(String str) {
            this.pcr_id = str;
        }

        public String getStation_id() {
            return this.station_id;
        }

        public void setStation_id(String str) {
            this.station_id = str;
        }

        public String getVol_count() {
            return this.vol_count;
        }

        public void setVol_count(String str) {
            this.vol_count = str;
        }

        public boolean isSelected() {
            return this.isSelected;
        }

        public void setSelected(boolean z) {
            this.isSelected = z;
        }

        public String getgCode() {
            return this.gCode;
        }

        public void setgCode(String str) {
            this.gCode = str;
        }

        public String getsDeviceID() {
            return this.sDeviceID;
        }

        public void setsDeviceID(String str) {
            this.sDeviceID = str;
        }

        public String getTime() {
            return this.time;
        }

        public void setTime(String str) {
            this.time = str;
        }

        public String getVol() {
            return this.vol;
        }

        public void setVol(String str) {
            this.vol = str;
        }

        public String getVtot() {
            return this.vtot;
        }

        public void setVtot(String str) {
            this.vtot = str;
        }

        public String getBalance() {
            return this.balance;
        }

        public void setBalance(String str) {
            this.balance = str;
        }

        public String getCardNumber() {
            return this.cardNumber;
        }

        public void setCardNumber(String str) {
            this.cardNumber = str;
        }

        public int getRowId() {
            return this.rowId;
        }

        public void setRowId(int i) {
            this.rowId = i;
        }

        public String getStationId() {
            return this.stationId;
        }

        public void setStationId(String str) {
            this.stationId = str;
        }

        public String getCard() {
            return this.card;
        }

        public void setCard(String str) {
            this.card = str;
        }

        public String getMoney() {
            return this.money;
        }

        public void setMoney(String str) {
            this.money = str;
        }

        public String getPhoto() {
            return this.photo;
        }

        public void setPhoto(String str) {
            this.photo = str;
        }

        public String getRow_id() {
            return this.row_id;
        }

        public void setRow_id(String str) {
            this.row_id = str;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int i) {
            this.type = i;
        }
    }

    /* loaded from: classes.dex */
    public class machion {
        private String gCode;
        private String sDeviceID;
        private String stationId;
        private String time;
        private String vol;
        private String vtot;

        public machion() {
        }

        public String getgCode() {
            return this.gCode;
        }

        public void setgCode(String str) {
            this.gCode = str;
        }

        public String getsDeviceID() {
            return this.sDeviceID;
        }

        public void setsDeviceID(String str) {
            this.sDeviceID = str;
        }

        public String getStationId() {
            return this.stationId;
        }

        public void setStationId(String str) {
            this.stationId = str;
        }

        public String getTime() {
            return this.time;
        }

        public void setTime(String str) {
            this.time = str;
        }

        public String getVol() {
            return this.vol;
        }

        public void setVol(String str) {
            this.vol = str;
        }

        public String getVtot() {
            return this.vtot;
        }

        public void setVtot(String str) {
            this.vtot = str;
        }
    }
}