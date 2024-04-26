package com.yltx.oil.partner.data.network;

/* loaded from: classes.dex */
public class Config {
    public static final String API_BASE_URL = "https://chinayltx.com/app";
    public static final String API_SP_URL = "http://weixin.ylsp188.com/?wechat#/";
    public static final String API_WeiXin_URL = "http://wx.chinayltx.com/";
    public static long BINNER_DURATION = 5000;
    public static final String CASHCOUNPONTOPIC_URL = "http://weixin.chinayltx.com/?wechat=#/cashcounpontopic";
    public static final String CDN_BASE_URL = "http://yltx-x.oss-cn-hangzhou.aliyuncs.com/";
    public static final String ERROR_TAG = "error_tag";
    public static final String EXCHANGEPROCESS_URL = "http://weixin.chinayltx.com/?wechat=#/exchangeProcess";
    public static final String FILE_BASE_URL = "http://114.215.220.245";
    public static final long FastClickTime = 300;
    public static final String ISADMIN = "isAdmin";
    public static final String ISLOGIN = "isLogin";
    public static boolean IS_FIRST_LOGIN_PAY_WEB = true;
    public static final String KETADDRESS = "Address";
    public static final String KETADDRESSID = "RowId";
    public static final String KETDEVICEIMEI = "DeviceIMEI";
    public static final String KETSPEAKTEXT = "speakText";
    public static final int LOAD_FIRST = 16;
    public static final int LOAD_MORE = 18;
    public static final int LOAD_SEARCH = 19;
    public static final String OSS_ACCESS_KEY = "LTAItqtURSzezOcx";
    public static final String OSS_BASE_URL = "oss-cn-hangzhou.aliyuncs.com/";
    public static final String OSS_BUCKET_NAME = "yltx-x";
    public static final String OSS_SECRET_KEY = "QJ0kAzqCYN3il3diw7yYKldEKrha6O";
    public static final int PAGE_DEFAULT_OFFSET = 10;
    public static final String PHONE = "phone";
    public static final int REFRESH = 17;
    public static final String RESULT_CLIPPED_BITMAP = "result_clipped_bitmap";
    public static final String UMENGKEY = "5cd0e2943fc195f01500051d";
    public static final String USERID = "userID";
    public static final String USERNAME = "userName";
    public static final String USERPASSWORD = "userPassword";
    public static final String USERTYPE = "userType";
    public static final boolean DEBUG = Boolean.parseBoolean("true");
    public static String IMG_PATH = "headPic/";
    public static String IMAGE_HEAD = "headPic/";
    public static String IMAGE_BX = "bx/";
    public static boolean IS_FIRST_LOGIN_FROM_WEB = true;
    public static boolean LOGIN_CONFLICT = false;
    public static boolean IS_SHOW_LOADING = true;

    public static String getAppApiUrl() {
        return API_BASE_URL.concat("/");
    }
}