package com.umeng.socialize;

/* loaded from: classes.dex */
public class Config {
    public static String EntityKey = "-1";
    public static String EntityName = "share";
    public static final int MINIPTOGRAM_TYPE_RELEASE = 0;
    @Deprecated
    public static boolean OpenEditor = true;
    @Deprecated
    public static String appName = null;
    public static final boolean mEncrypt = false;
    public static Boolean isUmengSina = true;
    public static Boolean isUmengWx = true;
    public static Boolean isUmengQQ = true;
    public static String Descriptor = "com.umeng.share";
    public static String SessionId = null;
    @Deprecated
    public static int QQWITHQZONE = 2;
    @Deprecated
    public static String QQAPPNAME = "";
    public static String shareType = "native";
    @Deprecated
    public static int KaKaoLoginType = 0;
    public static String MORE_TITLE = "分享";
    @Deprecated
    public static int LinkedInProfileScope = 0;
    @Deprecated
    public static int LinkedInShareCode = 0;
    public static int connectionTimeOut = 30000;
    public static int readSocketTimeOut = 30000;
    @Deprecated
    public static boolean isNeedAuth = false;
    public static boolean isJumptoAppStore = false;
    public static boolean isFacebookRead = false;
    private static int a = 0;

    public static void setMiniTest() {
        a = 1;
    }

    public static void setMiniPreView() {
        a = 2;
    }

    public static int getMINITYPE() {
        return a;
    }
}