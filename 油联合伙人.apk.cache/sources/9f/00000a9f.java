package com.bigkoo.pickerview.utils;

import android.support.v4.media.session.PlaybackStateCompat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/* loaded from: classes.dex */
public class ChinaDate {
    private static final long[] lunarInfo = {19416, 19168, 42352, 21717, 53856, 55632, 91476, 22176, 39632, 21970, 19168, 42422, 42192, 53840, 119381, 46400, 54944, 44450, 38320, 84343, 18800, 42160, 46261, 27216, 27968, 109396, 11104, 38256, 21234, 18800, 25958, 54432, 59984, 28309, 23248, 11104, 100067, 37600, 116951, 51536, 54432, 120998, 46416, 22176, 107956, 9680, 37584, 53938, 43344, 46423, 27808, 46416, 86869, 19872, 42416, 83315, 21168, 43432, 59728, 27296, 44710, 43856, 19296, 43748, 42352, 21088, 62051, 55632, 23383, 22176, 38608, 19925, 19152, 42192, 54484, 53840, 54616, 46400, 46752, 103846, 38320, 18864, 43380, 42160, 45690, 27216, 27968, 44870, 43872, 38256, 19189, 18800, 25776, 29859, 59984, 27480, 21952, 43872, 38613, 37600, 51552, 55636, 54432, 55888, 30034, 22176, 43959, 9680, 37584, 51893, 43344, 46240, 47780, 44368, 21977, 19360, 42416, 86390, 21168, 43312, 31060, 27296, 44368, 23378, 19296, 42726, 42208, 53856, 60005, 54576, 23200, 30371, 38608, 19195, 19152, 42192, 118966, 53840, 54560, 56645, 46496, 22224, 21938, 18864, 42359, 42160, 43600, 111189, 27936, 44448, 84835, 37744, 18936, 18800, 25776, 92326, 59984, 27424, 108228, 43744, 41696, 53987, 51552, 54615, 54432, 55888, 23893, 22176, 42704, 21972, 21200, 43448, 43344, 46240, 46758, 44368, 21920, 43940, 42416, 21168, 45683, 26928, 29495, 27296, 44368, 84821, 19296, 42352, 21732, 53600, 59752, 54560, 55968, 92838, 22224, 19168, 43476, 41680, 53584, 62034, 54560};
    private static final int[] year20 = {1, 4, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1};
    private static final int[] year19 = {0, 3, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0};
    private static final int[] year2000 = {0, 3, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1};
    public static final String[] nStr1 = {"", "正", "二", "三", "四", "五", "六", "七", "八", "九", "十", "冬", "腊"};
    private static final String[] Gan = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    private static final String[] Zhi = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
    private static final String[] Animals = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日 EEEEE");

    private static final int lYearDays(int i) {
        int i2 = 348;
        for (int i3 = 32768; i3 > 8; i3 >>= 1) {
            if ((lunarInfo[i - 1900] & i3) != 0) {
                i2++;
            }
        }
        return i2 + leapDays(i);
    }

    public static final int leapDays(int i) {
        if (leapMonth(i) != 0) {
            return (lunarInfo[i + (-1900)] & PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) != 0 ? 30 : 29;
        }
        return 0;
    }

    public static final int leapMonth(int i) {
        return (int) (lunarInfo[i - 1900] & 15);
    }

    public static final int monthDays(int i, int i2) {
        return (((long) (65536 >> i2)) & lunarInfo[i + (-1900)]) == 0 ? 29 : 30;
    }

    public static final String AnimalsYear(int i) {
        return Animals[(i - 4) % 12];
    }

    private static final String cyclicalm(int i) {
        return Gan[i % 10] + Zhi[i % 12];
    }

    public static final String cyclical(int i) {
        return cyclicalm((i - 1900) + 36);
    }

    private final long[] Lunar(int i, int i2) {
        long[] jArr = new long[7];
        long time = (new GregorianCalendar(i + LunarCalendar.MIN_YEAR, i2, 1).getTime().getTime() - new GregorianCalendar(3800, 1, 31).getTime().getTime()) / 86400000;
        if (i < 2000) {
            time += year19[i2 - 1];
        }
        if (i > 2000) {
            time += year20[i2 - 1];
        }
        if (i == 2000) {
            time += year2000[i2 - 1];
        }
        jArr[5] = 40 + time;
        jArr[4] = 14;
        int i3 = LunarCalendar.MIN_YEAR;
        int i4 = 0;
        while (i3 < 2050 && time > 0) {
            i4 = lYearDays(i3);
            time -= i4;
            jArr[4] = jArr[4] + 12;
            i3++;
        }
        if (time < 0) {
            time += i4;
            i3--;
            jArr[4] = jArr[4] - 12;
        }
        jArr[0] = i3;
        jArr[3] = i3 - 1864;
        int leapMonth = leapMonth(i3);
        jArr[6] = 0;
        int i5 = i4;
        long j = time;
        int i6 = 1;
        while (i6 < 13 && j > 0) {
            if (leapMonth > 0 && i6 == leapMonth + 1 && jArr[6] == 0) {
                i6--;
                jArr[6] = 1;
                i5 = leapDays((int) jArr[0]);
            } else {
                i5 = monthDays((int) jArr[0], i6);
            }
            if (jArr[6] == 1 && i6 == leapMonth + 1) {
                jArr[6] = 0;
            }
            int i7 = i6;
            j -= i5;
            if (jArr[6] == 0) {
                jArr[4] = jArr[4] + 1;
            }
            i6 = i7 + 1;
        }
        if (j == 0 && leapMonth > 0 && i6 == leapMonth + 1) {
            if (jArr[6] == 1) {
                jArr[6] = 0;
            } else {
                jArr[6] = 1;
                i6--;
                jArr[4] = jArr[4] - 1;
            }
        }
        if (j < 0) {
            j += i5;
            i6--;
            jArr[4] = jArr[4] - 1;
        }
        jArr[1] = i6;
        jArr[2] = j + 1;
        return jArr;
    }

    public static final long[] calElement(int i, int i2, int i3) {
        long[] jArr = new long[7];
        int i4 = LunarCalendar.MIN_YEAR;
        char c = 0;
        long time = (new GregorianCalendar(i, i2 - 1, i3).getTime().getTime() - new GregorianCalendar(LunarCalendar.MIN_YEAR, 0, 31).getTime().getTime()) / 86400000;
        jArr[5] = 40 + time;
        jArr[4] = 14;
        int i5 = 0;
        while (i4 < 2050 && time > 0) {
            i5 = lYearDays(i4);
            time -= i5;
            jArr[4] = jArr[4] + 12;
            i4++;
        }
        if (time < 0) {
            time += i5;
            i4--;
            jArr[4] = jArr[4] - 12;
        }
        jArr[0] = i4;
        jArr[3] = i4 - 1864;
        int leapMonth = leapMonth(i4);
        jArr[6] = 0;
        int i6 = i5;
        int i7 = 1;
        while (i7 < 13 && time > 0) {
            if (leapMonth > 0 && i7 == leapMonth + 1 && jArr[6] == 0) {
                i7--;
                jArr[6] = 1;
                i6 = leapDays((int) jArr[c]);
            } else {
                i6 = monthDays((int) jArr[c], i7);
            }
            if (jArr[6] == 1 && i7 == leapMonth + 1) {
                jArr[6] = 0;
            }
            int i8 = i7;
            time -= i6;
            if (jArr[6] == 0) {
                jArr[4] = jArr[4] + 1;
            }
            i7 = i8 + 1;
            c = 0;
        }
        if (time == 0 && leapMonth > 0 && i7 == leapMonth + 1) {
            if (jArr[6] == 1) {
                jArr[6] = 0;
            } else {
                jArr[6] = 1;
                i7--;
                jArr[4] = jArr[4] - 1;
            }
        }
        if (time < 0) {
            time += i6;
            i7--;
            jArr[4] = jArr[4] - 1;
        }
        jArr[1] = i7;
        jArr[2] = time + 1;
        return jArr;
    }

    public static final String getChinaDate(int i) {
        if (i == 10) {
            return "初十";
        }
        if (i == 20) {
            return "二十";
        }
        if (i == 30) {
            return "三十";
        }
        int i2 = i / 10;
        String str = i2 == 0 ? "初" : "";
        if (i2 == 1) {
            str = "十";
        }
        if (i2 == 2) {
            str = "廿";
        }
        if (i2 == 3) {
            str = "三";
        }
        switch (i % 10) {
            case 1:
                return str + "一";
            case 2:
                return str + "二";
            case 3:
                return str + "三";
            case 4:
                return str + "四";
            case 5:
                return str + "五";
            case 6:
                return str + "六";
            case 7:
                return str + "七";
            case 8:
                return str + "八";
            case 9:
                return str + "九";
            default:
                return str;
        }
    }

    public static String today() {
        Calendar calendar = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
        int i = calendar.get(1);
        long[] calElement = calElement(i, calendar.get(2) + 1, calendar.get(5));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sdf.format(calendar.getTime()));
        stringBuffer.append(" 农历");
        stringBuffer.append(cyclical(i));
        stringBuffer.append('(');
        stringBuffer.append(AnimalsYear(i));
        stringBuffer.append(")年");
        stringBuffer.append(nStr1[(int) calElement[1]]);
        stringBuffer.append("月");
        stringBuffer.append(getChinaDate((int) calElement[2]));
        return stringBuffer.toString();
    }

    public static String oneDay(int i, int i2, int i3) {
        long[] calElement = calElement(i, i2, i3);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" 农历");
        stringBuffer.append(cyclical(i));
        stringBuffer.append('(');
        stringBuffer.append(AnimalsYear(i));
        stringBuffer.append(")年");
        stringBuffer.append(nStr1[(int) calElement[1]]);
        stringBuffer.append("月");
        stringBuffer.append(getChinaDate((int) calElement[2]));
        return stringBuffer.toString();
    }

    public static String getLunarYearText(int i) {
        StringBuilder sb = new StringBuilder();
        int i2 = i - 4;
        sb.append(Gan[i2 % 10]);
        sb.append(Zhi[i2 % 12]);
        sb.append("年");
        return sb.toString();
    }

    public static ArrayList<String> getYears(int i, int i2) {
        ArrayList<String> arrayList = new ArrayList<>();
        while (i < i2) {
            arrayList.add(String.format("%s(%d)", getLunarYearText(i), Integer.valueOf(i)));
            i++;
        }
        return arrayList;
    }

    public static ArrayList<String> getMonths(int i) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i2 = 1; i2 < nStr1.length; i2++) {
            arrayList.add(nStr1[i2] + "月");
        }
        if (leapMonth(i) != 0) {
            int leapMonth = leapMonth(i);
            arrayList.add(leapMonth, "闰" + nStr1[leapMonth(i)] + "月");
        }
        return arrayList;
    }

    public static ArrayList<String> getLunarDays(int i) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i2 = 1; i2 <= i; i2++) {
            arrayList.add(getChinaDate(i2));
        }
        return arrayList;
    }
}