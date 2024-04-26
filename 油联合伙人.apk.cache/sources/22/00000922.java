package com.alipay.android.phone.mrpc.core;

import android.text.format.Time;
import com.bigkoo.pickerview.utils.LunarCalendar;
import com.contrarywind.timer.MessageHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class k {
    private static final Pattern a = Pattern.compile("([0-9]{1,2})[- ]([A-Za-z]{3,9})[- ]([0-9]{2,4})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])");
    private static final Pattern b = Pattern.compile("[ ]([A-Za-z]{3,9})[ ]+([0-9]{1,2})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])[ ]([0-9]{2,4})");

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a {
        int a;
        int b;
        int c;

        a(int i, int i2, int i3) {
            this.a = i;
            this.b = i2;
            this.c = i3;
        }
    }

    public static long a(String str) {
        int d;
        int i;
        int i2;
        a aVar;
        int i3;
        int i4;
        int i5;
        Matcher matcher = a.matcher(str);
        if (matcher.find()) {
            int b2 = b(matcher.group(1));
            int c = c(matcher.group(2));
            int d2 = d(matcher.group(3));
            aVar = e(matcher.group(4));
            i = c;
            i2 = b2;
            d = d2;
        } else {
            Matcher matcher2 = b.matcher(str);
            if (!matcher2.find()) {
                throw new IllegalArgumentException();
            }
            int c2 = c(matcher2.group(1));
            int b3 = b(matcher2.group(2));
            a e = e(matcher2.group(3));
            d = d(matcher2.group(4));
            i = c2;
            i2 = b3;
            aVar = e;
        }
        if (d >= 2038) {
            i4 = 1;
            i5 = 0;
            i3 = 2038;
        } else {
            i3 = d;
            i4 = i2;
            i5 = i;
        }
        Time time = new Time("UTC");
        time.set(aVar.c, aVar.b, aVar.a, i4, i5, i3);
        return time.toMillis(false);
    }

    private static int b(String str) {
        return str.length() == 2 ? ((str.charAt(0) - '0') * 10) + (str.charAt(1) - '0') : str.charAt(0) - '0';
    }

    private static int c(String str) {
        int lowerCase = ((Character.toLowerCase(str.charAt(0)) + Character.toLowerCase(str.charAt(1))) + Character.toLowerCase(str.charAt(2))) - 291;
        if (lowerCase != 22) {
            if (lowerCase != 26) {
                if (lowerCase != 29) {
                    if (lowerCase != 32) {
                        if (lowerCase != 40) {
                            if (lowerCase != 42) {
                                if (lowerCase != 48) {
                                    switch (lowerCase) {
                                        case 9:
                                            return 11;
                                        case 10:
                                            return 1;
                                        default:
                                            switch (lowerCase) {
                                                case 35:
                                                    return 9;
                                                case 36:
                                                    return 4;
                                                case 37:
                                                    return 8;
                                                default:
                                                    throw new IllegalArgumentException();
                                            }
                                    }
                                }
                                return 10;
                            }
                            return 5;
                        }
                        return 6;
                    }
                    return 3;
                }
                return 2;
            }
            return 7;
        }
        return 0;
    }

    private static int d(String str) {
        if (str.length() == 2) {
            int charAt = ((str.charAt(0) - '0') * 10) + (str.charAt(1) - '0');
            return charAt >= 70 ? charAt + LunarCalendar.MIN_YEAR : charAt + MessageHandler.WHAT_SMOOTH_SCROLL;
        } else if (str.length() == 3) {
            return ((str.charAt(0) - '0') * 100) + ((str.charAt(1) - '0') * 10) + (str.charAt(2) - '0') + LunarCalendar.MIN_YEAR;
        } else {
            if (str.length() == 4) {
                return ((str.charAt(0) - '0') * 1000) + ((str.charAt(1) - '0') * 100) + ((str.charAt(2) - '0') * 10) + (str.charAt(3) - '0');
            }
            return 1970;
        }
    }

    private static a e(String str) {
        int i;
        int i2;
        int i3;
        int charAt = str.charAt(0) - '0';
        if (str.charAt(1) != ':') {
            i = 2;
            charAt = (charAt * 10) + (str.charAt(1) - '0');
        } else {
            i = 1;
        }
        int i4 = i + 1 + 1 + 1 + 1;
        return new a(charAt, ((str.charAt(i2) - '0') * 10) + (str.charAt(i3) - '0'), ((str.charAt(i4) - '0') * 10) + (str.charAt(i4 + 1) - '0'));
    }
}