package com.alibaba.sdk.android.oss.common.utils;

import com.alipay.sdk.util.i;

/* loaded from: classes.dex */
public class VersionInfoUtils {
    private static String userAgent;
    private static String version;

    public static String getVersion() {
        return "2.2.0";
    }

    public static String getUserAgent() {
        if (userAgent == null) {
            userAgent = "aliyun-sdk-android/" + getVersion() + "/" + getDefaultUserAgent();
        }
        return userAgent;
    }

    public static String getDefaultUserAgent() {
        String property = System.getProperty("http.agent");
        if (OSSUtils.isEmptyString(property)) {
            property = "(" + System.getProperty("os.name") + "/" + System.getProperty("os.version") + "/" + System.getProperty("os.arch") + i.b + System.getProperty("java.version") + ")";
        }
        return property.replaceAll("[^\\p{ASCII}]", "?");
    }
}