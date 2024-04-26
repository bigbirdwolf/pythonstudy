package com.yltx.oil.partner.data.response;

/* loaded from: classes.dex */
public class BannerResponse {
    private String description;
    private String drillValue;
    private String fileName;
    private String isDrill;
    private String orderNo;
    private int rowId;
    private String state;

    public int getRowId() {
        return this.rowId;
    }

    public void setRowId(int i) {
        this.rowId = i;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public String getIsDrill() {
        return this.isDrill;
    }

    public void setIsDrill(String str) {
        this.isDrill = str;
    }

    public String getDrillValue() {
        return this.drillValue;
    }

    public void setDrillValue(String str) {
        this.drillValue = str;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String str) {
        this.state = str;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String str) {
        this.orderNo = str;
    }
}