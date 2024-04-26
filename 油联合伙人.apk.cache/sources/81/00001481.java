package com.umeng.socialize.net.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.socialize.Config;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMMin;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMediaObject;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.net.utils.URequest;
import com.umeng.socialize.utils.SocializeUtils;

/* loaded from: classes.dex */
public class AnalyticsReqeust extends SocializeRequest {
    private static final String a = "/share/multi_add/";
    private static final int b = 9;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private UMediaObject j;

    public AnalyticsReqeust(Context context, String str, String str2) {
        super(context, "", AnalyticsResponse.class, 9, URequest.RequestMethod.POST);
        this.mContext = context;
        this.d = str;
        this.i = str2;
    }

    public void setPlatform(String str) {
        this.d = str;
    }

    public void setUID(String str) {
        this.e = str;
    }

    public void setType(String str) {
        this.f = str;
    }

    public void setText(String str) {
        this.i = str;
    }

    public void setmUsid(String str) {
        this.c = str;
    }

    public void setMedia(UMediaObject uMediaObject) {
        if (uMediaObject instanceof UMImage) {
            this.j = uMediaObject;
        } else if (uMediaObject instanceof UMusic) {
            UMusic uMusic = (UMusic) uMediaObject;
            this.g = uMusic.getTitle();
            this.h = uMusic.toUrl();
            this.i = uMusic.getDescription();
            this.j = uMusic.getThumbImage();
        } else if (uMediaObject instanceof UMVideo) {
            UMVideo uMVideo = (UMVideo) uMediaObject;
            this.g = uMVideo.getTitle();
            this.h = uMVideo.toUrl();
            this.i = uMVideo.getDescription();
            this.j = uMVideo.getThumbImage();
        } else if (uMediaObject instanceof UMWeb) {
            UMWeb uMWeb = (UMWeb) uMediaObject;
            this.g = uMWeb.getTitle();
            this.h = uMWeb.toUrl();
            this.i = uMWeb.getDescription();
            this.j = uMWeb.getThumbImage();
        } else if (uMediaObject instanceof UMMin) {
            UMMin uMMin = (UMMin) uMediaObject;
            this.g = uMMin.getTitle();
            this.h = uMMin.toUrl();
            this.i = uMMin.getDescription();
            this.j = uMMin.getThumbImage();
        }
    }

    @Override // com.umeng.socialize.net.base.SocializeRequest, com.umeng.socialize.net.utils.URequest
    public void onPrepareRequest() {
        super.onPrepareRequest();
        Object[] objArr = new Object[2];
        objArr[0] = this.d;
        objArr[1] = this.c == null ? "" : this.c;
        String format = String.format("{\"%s\":\"%s\"}", objArr);
        String appkey = SocializeUtils.getAppkey(this.mContext);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_DESCRIPTOR, Config.Descriptor);
        addStringParams("to", format);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_SHARE_SNS, format);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_AK, appkey);
        addStringParams("type", this.f);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_SHARE_USID, this.c);
        addStringParams("ct", this.i);
        if (!TextUtils.isEmpty(this.h)) {
            addStringParams("url", this.h);
        }
        if (!TextUtils.isEmpty(this.g)) {
            addStringParams("title", this.g);
        }
        addMediaParams(this.j);
    }

    @Override // com.umeng.socialize.net.base.SocializeRequest
    protected String getPath() {
        return a + SocializeUtils.getAppkey(this.mContext) + "/" + Config.EntityKey + "/";
    }
}