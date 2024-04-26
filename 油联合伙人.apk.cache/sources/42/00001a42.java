package com.yltx.oil.partner.modules.web;

/* loaded from: classes.dex */
final class DrillKind {
    public static final String KIND_DETAIL = "1";
    public static final String KIND_LIST = "0";
    private String detailClzName;
    private String listClzName;

    public DrillKind() {
    }

    public DrillKind(String str) {
        this.listClzName = str;
    }

    public DrillKind(String str, String str2) {
        this.listClzName = str;
        this.detailClzName = str2;
    }

    public String getListClzName() {
        return this.listClzName;
    }

    public void setListClzName(String str) {
        this.listClzName = str;
    }

    public String getDetailClzName() {
        return this.detailClzName;
    }

    public void setDetailClzName(String str) {
        this.detailClzName = str;
    }
}