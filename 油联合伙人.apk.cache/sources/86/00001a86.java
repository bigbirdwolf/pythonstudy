package com.yltx.oil.partner.utils;

import android.text.TextUtils;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.PartnerApplication;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class ADFilterTool {
    private static String PATTERN = "";

    static {
        initPattern();
    }

    private static void initPattern() {
        PATTERN = getPatternStr();
    }

    public static boolean hasNotAd(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (TextUtils.isEmpty(PATTERN)) {
            initPattern();
        }
        return Pattern.matches(PATTERN, str);
    }

    private static String getPatternStr() {
        String[] stringArray = PartnerApplication.getInstance().getResources().getStringArray(R.array.legal_domain);
        if (stringArray == null || stringArray.length <= 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer("^(https|http)://.*(");
        for (String str : stringArray) {
            if (str != null && str.length() > 0) {
                stringBuffer.append(str);
                stringBuffer.append("|");
            }
        }
        return stringBuffer.substring(0, stringBuffer.length() - 1) + ").*";
    }
}