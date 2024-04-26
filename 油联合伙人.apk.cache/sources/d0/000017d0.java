package com.yltx.oil.partner.modules.home.response;

/* loaded from: classes.dex */
public class MessageNotificationResponse {
    private String content;
    private String createTime;
    private String isjump;
    private String msgType;
    private String rowId;
    private String status;
    private String subHeading;
    private String title;
    private String type;

    public String getRowid() {
        return this.rowId;
    }

    public void setRowid(String str) {
        this.rowId = str;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getSubHeading() {
        return this.subHeading;
    }

    public void setSubHeading(String str) {
        this.subHeading = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getMsgType() {
        return this.msgType;
    }

    public void setMsgType(String str) {
        this.msgType = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getIsjump() {
        return this.isjump;
    }

    public void setIsjump(String str) {
        this.isjump = str;
    }
}