package com.yltx.oil.partner.base;

import com.umeng.analytics.pro.b;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class HttpStatusCodes {
    public static final int CODE_400 = 400;
    public static final int CODE_401 = 401;
    public static final int CODE_402 = 402;
    public static final int CODE_403 = 403;
    public static final int CODE_404 = 404;
    public static final int CODE_405 = 405;
    public static final int CODE_406 = 406;
    public static final int CODE_407 = 407;
    public static final int CODE_408 = 408;
    public static final int CODE_409 = 409;
    public static final int CODE_410 = 410;
    public static final int CODE_411 = 411;
    public static final int CODE_412 = 412;
    public static final int CODE_413 = 413;
    public static final int CODE_414 = 414;
    public static final int CODE_415 = 415;
    public static final int CODE_416 = 416;
    public static final int CODE_417 = 417;
    public static final int CODE_500 = 500;
    public static final int CODE_501 = 501;
    public static final int CODE_502 = 502;
    public static final int CODE_503 = 503;
    public static final int CODE_504 = 504;
    public static final int CODE_505 = 505;
    public static final int CODE_800 = 800;
    public static final int CODE_900 = 900;

    public static Map<Integer, String> getCodesMap() {
        HashMap hashMap = new HashMap();
        hashMap.put(Integer.valueOf((int) CODE_400), "Bad Request");
        hashMap.put(Integer.valueOf((int) CODE_401), "Unauthorized");
        hashMap.put(Integer.valueOf((int) CODE_402), "Payment Required");
        hashMap.put(Integer.valueOf((int) CODE_403), "Forbidden");
        hashMap.put(404, "Not Found");
        hashMap.put(Integer.valueOf((int) CODE_405), "Method Not Allowed");
        hashMap.put(Integer.valueOf((int) CODE_406), "Not Acceptable");
        hashMap.put(Integer.valueOf((int) CODE_407), "Proxy Authentication Required");
        hashMap.put(Integer.valueOf((int) CODE_408), "Request Timeout");
        hashMap.put(Integer.valueOf((int) CODE_409), "Conflict");
        hashMap.put(Integer.valueOf((int) CODE_410), "Gone");
        hashMap.put(Integer.valueOf((int) CODE_411), "Length Required");
        hashMap.put(Integer.valueOf((int) CODE_412), "Precondition Failed");
        hashMap.put(Integer.valueOf((int) CODE_413), "Request Entity Too Large");
        hashMap.put(Integer.valueOf((int) CODE_414), "Request-URI Too Long");
        hashMap.put(Integer.valueOf((int) CODE_415), "Unsupported Media Type");
        hashMap.put(Integer.valueOf((int) CODE_416), "Requested Range Not Satisfiable");
        hashMap.put(Integer.valueOf((int) CODE_417), "Expectation Failed");
        hashMap.put(500, "Internal Server Error");
        hashMap.put(501, "Not Implemented");
        hashMap.put(Integer.valueOf((int) CODE_502), "Bad Gateway");
        hashMap.put(Integer.valueOf((int) CODE_503), "Service Unavailable");
        hashMap.put(Integer.valueOf((int) CODE_504), "Gateway Timeout");
        hashMap.put(Integer.valueOf((int) CODE_505), "HTTP Version Not Supported");
        hashMap.put(Integer.valueOf((int) CODE_800), b.N);
        hashMap.put(Integer.valueOf((int) CODE_900), b.N);
        return hashMap;
    }
}