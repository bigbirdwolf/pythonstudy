package com.umeng.socialize.media;

import android.os.Parcel;
import com.umeng.socialize.media.UMediaObject;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class UMusic extends BaseMediaObject {
    private String f;
    private String g;
    private String h;
    private String i;
    private int j;
    private String k;

    public void setmTargetUrl(String str) {
        this.k = str;
    }

    public String getmTargetUrl() {
        return this.k;
    }

    public int getDuration() {
        return this.j;
    }

    public void setDuration(int i) {
        this.j = i;
    }

    public String getLowBandUrl() {
        return this.i;
    }

    public void setLowBandUrl(String str) {
        this.i = str;
    }

    public UMusic(String str) {
        super(str);
    }

    public String getHighBandDataUrl() {
        return this.g;
    }

    public void setHighBandDataUrl(String str) {
        this.g = str;
    }

    public String getH5Url() {
        return this.h;
    }

    public void setH5Url(String str) {
        this.h = str;
    }

    @Override // com.umeng.socialize.media.UMediaObject
    public UMediaObject.MediaType getMediaType() {
        return UMediaObject.MediaType.MUSIC;
    }

    protected UMusic(Parcel parcel) {
        super(parcel);
    }

    @Override // com.umeng.socialize.media.UMediaObject
    public final Map<String, Object> toUrlExtraParams() {
        HashMap hashMap = new HashMap();
        if (isUrlMedia()) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FURL, this.a);
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FTYPE, getMediaType());
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_TITLE, this.b);
        }
        return hashMap;
    }

    @Override // com.umeng.socialize.media.UMediaObject
    public byte[] toByte() {
        if (this.e != null) {
            return this.e.toByte();
        }
        return null;
    }

    @Override // com.umeng.socialize.media.BaseMediaObject
    public String toString() {
        return "UMusic [title=" + this.b + "media_url=" + this.a + ", qzone_title=" + this.b + ", qzone_thumb=]";
    }

    @Override // com.umeng.socialize.media.BaseMediaObject
    public UMImage getThumbImage() {
        return this.e;
    }

    public String getLowBandDataUrl() {
        return this.f;
    }

    public void setLowBandDataUrl(String str) {
        this.f = str;
    }
}