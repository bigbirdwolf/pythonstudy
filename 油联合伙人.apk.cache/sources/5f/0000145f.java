package com.umeng.socialize.media;

import android.os.Parcel;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class BaseMediaObject implements UMediaObject {
    protected String a;
    protected String b;
    protected Map<String, Object> c;
    protected String d;
    protected UMImage e;
    public String mText;

    public BaseMediaObject() {
        this.mText = null;
        this.a = "";
        this.b = "";
        this.c = new HashMap();
        this.d = "";
    }

    public void setThumb(UMImage uMImage) {
        this.e = uMImage;
    }

    public BaseMediaObject(String str) {
        this.mText = null;
        this.a = "";
        this.b = "";
        this.c = new HashMap();
        this.d = "";
        this.a = str;
    }

    public String getDescription() {
        return this.d;
    }

    public Map<String, Object> getmExtra() {
        return this.c;
    }

    public void setmExtra(String str, Object obj) {
        this.c.put(str, obj);
    }

    public void setDescription(String str) {
        this.d = str;
    }

    @Override // com.umeng.socialize.media.UMediaObject
    public String toUrl() {
        return this.a;
    }

    public UMImage getThumbImage() {
        return this.e;
    }

    @Override // com.umeng.socialize.media.UMediaObject
    public boolean isUrlMedia() {
        return !TextUtils.isEmpty(this.a);
    }

    public String getTitle() {
        return this.b;
    }

    public void setTitle(String str) {
        this.b = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseMediaObject(Parcel parcel) {
        this.mText = null;
        this.a = "";
        this.b = "";
        this.c = new HashMap();
        this.d = "";
        if (parcel != null) {
            this.a = parcel.readString();
            this.b = parcel.readString();
        }
    }

    public String toString() {
        return "BaseMediaObject [media_url=" + this.a + ", qzone_title=" + this.b + ", qzone_thumb=]";
    }
}