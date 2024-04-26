package com.yltx.oil.partner.data.response;

import java.io.Serializable;

/* loaded from: classes.dex */
public class HomeBannerBean implements Serializable {
    private String drillType;
    private String drillValue;
    private String fileName;
    private String insideUrl;
    private String jumpType;
    private int rowId;
    private String url;

    public String toString() {
        return "HomeBannerBean{insideUrl='" + this.insideUrl + "', jumpType='" + this.jumpType + "', url='" + this.url + "', fileName='" + this.fileName + "', drillType='" + this.drillType + "', drillValue='" + this.drillValue + "', rowId=" + this.rowId + '}';
    }

    public String getInsideUrl() {
        return this.insideUrl;
    }

    public void setInsideUrl(String str) {
        this.insideUrl = str;
    }

    public String getJumpType() {
        return this.jumpType;
    }

    public void setJumpType(String str) {
        this.jumpType = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public String getDrillType() {
        return this.drillType;
    }

    public void setDrillType(String str) {
        this.drillType = str;
    }

    public String getDrillValue() {
        return this.drillValue;
    }

    public void setDrillValue(String str) {
        this.drillValue = str;
    }

    public int getRowId() {
        return this.rowId;
    }

    public void setRowId(int i) {
        this.rowId = i;
    }
}