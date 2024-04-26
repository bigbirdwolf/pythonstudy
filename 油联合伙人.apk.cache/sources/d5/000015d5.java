package com.yltx.oil.partner.data.response;

import java.util.List;

/* loaded from: classes.dex */
public class ShareEntity {
    private List<String> channel;
    private String desc;
    private String pic;
    private String title;
    private String url;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public String getPic() {
        return this.pic;
    }

    public void setPic(String str) {
        this.pic = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public List<String> getChannel() {
        return this.channel;
    }

    public void setChannel(List<String> list) {
        this.channel = list;
    }

    public String toString() {
        return "ShareEntity{title='" + this.title + "', desc='" + this.desc + "', pic='" + this.pic + "', url='" + this.url + "', channel=" + this.channel + '}';
    }
}