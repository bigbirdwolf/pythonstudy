package com.alipay.sdk.packet;

import android.text.TextUtils;
import com.alipay.sdk.util.i;

/* loaded from: classes.dex */
public class a {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split(com.alipay.sdk.sys.a.b);
        if (split.length == 0) {
            return "";
        }
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        for (String str6 : split) {
            if (TextUtils.isEmpty(str2)) {
                str2 = b(str6);
            }
            if (TextUtils.isEmpty(str3)) {
                str3 = c(str6);
            }
            if (TextUtils.isEmpty(str4)) {
                str4 = d(str6);
            }
            if (TextUtils.isEmpty(str5)) {
                str5 = f(str6);
            }
        }
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str2)) {
            sb.append("biz_type=" + str2 + i.b);
        }
        if (!TextUtils.isEmpty(str3)) {
            sb.append("biz_no=" + str3 + i.b);
        }
        if (!TextUtils.isEmpty(str4)) {
            sb.append("trade_no=" + str4 + i.b);
        }
        if (!TextUtils.isEmpty(str5)) {
            sb.append("app_userid=" + str5 + i.b);
        }
        String sb2 = sb.toString();
        return sb2.endsWith(i.b) ? sb2.substring(0, sb2.length() - 1) : sb2;
    }

    private static String b(String str) {
        if (str.contains("biz_type")) {
            return e(str);
        }
        return null;
    }

    private static String c(String str) {
        if (str.contains("biz_no")) {
            return e(str);
        }
        return null;
    }

    private static String d(String str) {
        if (!str.contains(com.alipay.sdk.app.statistic.c.W) || str.startsWith(com.alipay.sdk.app.statistic.c.V)) {
            return null;
        }
        return e(str);
    }

    private static String e(String str) {
        String[] split = str.split("=");
        if (split.length > 1) {
            String str2 = split[1];
            return str2.contains("\"") ? str2.replaceAll("\"", "") : str2;
        }
        return null;
    }

    private static String f(String str) {
        if (str.contains("app_userid")) {
            return e(str);
        }
        return null;
    }
}