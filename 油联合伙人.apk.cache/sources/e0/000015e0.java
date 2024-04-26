package com.yltx.oil.partner.data.response;

/* loaded from: classes.dex */
public class VersionResponse {
    private String changeLog;
    private String forced;
    private String sequence;
    private String version;
    private String versionUrl;

    public String getChangeLog() {
        return this.changeLog;
    }

    public void setChangeLog(String str) {
        this.changeLog = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getForced() {
        return this.forced;
    }

    public void setForced(String str) {
        this.forced = str;
    }

    public String getVersionUrl() {
        return this.versionUrl;
    }

    public void setVersionUrl(String str) {
        this.versionUrl = str;
    }

    public String getSequence() {
        return this.sequence;
    }

    public void setSequence(String str) {
        this.sequence = str;
    }
}