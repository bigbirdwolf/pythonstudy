package com.yltx.oil.partner.oss.glide;

import com.yltx.oil.partner.oss.OSSFileHelper;

/* loaded from: classes.dex */
public class OSSFile {
    private static final Format DEFAULT_FORMAT = Format.jpg;
    private static final String DEFAULT_QUALITY = "70";
    private String format;
    private String name;
    private String orient;
    private String quality;
    private String rotate;
    private String type;

    /* loaded from: classes.dex */
    public enum Format {
        jpg,
        webp,
        png,
        bmp
    }

    public static OSSFile buildFixed(String str, int i, Format format) {
        OSSFile oSSFile = new OSSFile(str);
        oSSFile.setType("image/resize,w_%s,h_%s,m_fixed");
        oSSFile.setQuality(String.format("/quality,q_%s", Integer.valueOf(i)));
        oSSFile.setFormat(String.format("/format,%s", format.toString()));
        oSSFile.setOrient("/auto-orient,1");
        return oSSFile;
    }

    public static OSSFile buildFixed(String str) {
        OSSFile oSSFile = new OSSFile(str);
        oSSFile.setType("image/resize,w_%s,h_%s,m_fixed");
        oSSFile.setQuality(String.format("/quality,q_%s", DEFAULT_QUALITY));
        oSSFile.setFormat(String.format("/format,%s", DEFAULT_FORMAT));
        oSSFile.setOrient("/auto-orient,1");
        return oSSFile;
    }

    public static OSSFile buildFill(String str, int i, Format format) {
        OSSFile oSSFile = new OSSFile(str);
        oSSFile.setType("image/resize,w_%s,h_%s,m_fill");
        oSSFile.setQuality(String.format("/quality,q_%s", Integer.valueOf(i)));
        oSSFile.setFormat(String.format("/format,%s", format.toString()));
        oSSFile.setOrient("/auto-orient,1");
        return oSSFile;
    }

    public static OSSFile buildFill(String str) {
        OSSFile oSSFile = new OSSFile(str);
        oSSFile.setType("image/resize,w_%s,h_%s,m_fill");
        oSSFile.setQuality(String.format("/quality,q_%s", DEFAULT_QUALITY));
        oSSFile.setFormat(String.format("/format,%s", DEFAULT_FORMAT));
        oSSFile.setOrient("/auto-orient,1");
        return oSSFile;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String buildUrl(int i, int i2) {
        return OSSFileHelper.getFileRemoteUrl(this.name).concat(buildStyle(i, i2));
    }

    String buildStyle(int i, int i2) {
        return "?x-oss-process=" + String.format(this.type, Integer.valueOf(i), Integer.valueOf(i2)) + this.orient + this.quality + this.format;
    }

    public OSSFile(String str) {
        this.name = str;
        setType("image/resize,w_%s,h_%s,m_fill");
        setQuality(String.format("/quality,q_%s", DEFAULT_QUALITY));
        setFormat(String.format("/format,%s", DEFAULT_FORMAT));
        setOrient("/auto-orient,1");
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getQuality() {
        return this.quality;
    }

    public void setQuality(String str) {
        this.quality = str;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String str) {
        this.format = str;
    }

    public String getOrient() {
        return this.orient;
    }

    public void setOrient(String str) {
        this.orient = str;
    }

    public String getRotate() {
        return this.rotate;
    }

    public void setRotate(String str) {
        this.rotate = str;
    }
}