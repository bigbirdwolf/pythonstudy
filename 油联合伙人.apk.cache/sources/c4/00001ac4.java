package com.yltx.oil.partner.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.widget.EditText;
import android.widget.Toast;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class StringUtils {
    private static final String MONEY_FORMAT = "###0.00";
    private static final String PHONE_REGULAR_EXPRESSION = "[1]\\d{10}";
    private static DecimalFormat decimalFormat;

    private static boolean isEmojiCharacter(char c) {
        return c == 0 || c == '\t' || c == '\n' || c == '\r' || (c >= ' ' && c <= 55295) || ((c >= 57344 && c <= 65533) || (c >= 0 && c <= 65535));
    }

    public static String format(String str) {
        if (decimalFormat == null) {
            decimalFormat = new DecimalFormat(MONEY_FORMAT);
        }
        try {
            if (TextUtils.isEmpty(str)) {
                str = "0.00";
            }
            return decimalFormat.format(Double.parseDouble(str));
        } catch (Exception unused) {
            return str;
        }
    }

    public String rvZeroAndDot(String str) {
        if (str.isEmpty()) {
            return null;
        }
        return str.indexOf(".") > 0 ? str.replaceAll("0+?$", "").replaceAll("[.]$", "") : str;
    }

    public static Double reserveTwoDecimalPlaces(String str) {
        double doubleValue = Double.valueOf(str).doubleValue();
        if (0.0d == doubleValue) {
            return Double.valueOf(0.0d);
        }
        return Double.valueOf(new DecimalFormat("#,###.00").format(doubleValue));
    }

    public static String reserveTwoDecimalPlacesW(String str) {
        double doubleValue = Double.valueOf(str).doubleValue();
        if (0.0d == doubleValue) {
            return "0.00";
        }
        DecimalFormat decimalFormat2 = new DecimalFormat("#,###,###.00");
        return decimalFormat2.format(doubleValue) + "";
    }

    public static String reserveTwoW(String str) {
        double doubleValue = Double.valueOf(str).doubleValue();
        if (0.0d == doubleValue) {
            return "0";
        }
        DecimalFormat decimalFormat2 = new DecimalFormat("#,###,###");
        return decimalFormat2.format(doubleValue) + "";
    }

    public static boolean isMobileNO(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches("[1][3456789]\\d{9}");
    }

    public static boolean isMobile(String str) {
        return Pattern.matches("(\\+\\d+)?[1][356789]\\d{9}$", str);
    }

    public static boolean isAlipay(String str) {
        return Pattern.matches("^[2][8]\\d{16}$", str);
    }

    public static boolean isORderNum(String str) {
        return Pattern.matches("^[A-Za-z]{3}[0-9]+$", str);
    }

    public static boolean isWechatpay(String str) {
        return Pattern.matches("^[1][0-5]\\d{16}$", str);
    }

    public static boolean isFixedPhone(String str) {
        return Pattern.matches("(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)", str);
    }

    public static boolean isPostCode(String str) {
        return Pattern.matches("[1-9]\\d{5}", str);
    }

    public static boolean isLetterOrDigit(String str) {
        boolean z = false;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetterOrDigit(str.charAt(i))) {
                z = true;
            }
        }
        return z && str.matches("^[a-zA-Z0-9]+$");
    }

    public static boolean isLetterDigit(String str) {
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                z = true;
            } else if (Character.isLetter(str.charAt(i))) {
                z2 = true;
            }
        }
        return z && z2 && str.matches("^[a-zA-Z0-9]+$");
    }

    public static boolean isContainAll(String str) {
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                z = true;
            } else if (Character.isLowerCase(str.charAt(i))) {
                z2 = true;
            } else if (Character.isUpperCase(str.charAt(i))) {
                z3 = true;
            }
        }
        return z && z2 && z3 && str.matches("^[a-zA-Z0-9]+$");
    }

    public static boolean isPwd(String str) {
        return Pattern.matches("^[a-zA-Z]\\w{5,17}$", str);
    }

    public static boolean isNumPwd(String str) {
        return Pattern.matches("^\\d{6,18}$", str);
    }

    public static void whatIsInput(Context context, EditText editText) {
        String obj = editText.getText().toString();
        if (Pattern.compile("[0-9]*").matcher(obj).matches()) {
            Toast.makeText(context, "输入的是数字", 0).show();
        }
        if (Pattern.compile("[a-zA-Z]").matcher(obj).matches()) {
            Toast.makeText(context, "输入的是字母", 0).show();
        }
        if (Pattern.compile("[一-龥]").matcher(obj).matches()) {
            Toast.makeText(context, "输入的是汉字", 0).show();
        }
    }

    public static SpannedString setHint(String str, int i) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new AbsoluteSizeSpan(i, true), 0, spannableString.length(), 33);
        return new SpannedString(spannableString);
    }

    public static boolean isEmpty(Object obj) {
        return obj == null || obj.toString().trim().length() == 0 || "null".equals(obj.toString().trim());
    }

    public static String utf8Encode(String str) {
        if (isEmpty(str) || str.getBytes().length == str.length()) {
            return str;
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException occurred. ", e);
        }
    }

    public static boolean containsEmoji(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!isEmojiCharacter(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}