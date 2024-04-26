package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.utils.UMUtils;

/* compiled from: EncryptHelper.java */
/* loaded from: classes.dex */
public class w {
    private static String a = null;
    private static final String b = "umeng+";
    private static final String c = "ek__id";
    private static final String d = "ek_key";
    private static w e;

    private w() {
    }

    public static w a() {
        if (e == null) {
            synchronized (w.class) {
                if (e == null) {
                    e = new w();
                }
            }
        }
        return e;
    }

    public void a(Context context) {
        try {
            if (TextUtils.isEmpty(a)) {
                String multiProcessSP = UMUtils.getMultiProcessSP(context, c);
                if (TextUtils.isEmpty(multiProcessSP)) {
                    multiProcessSP = DeviceConfig.getDBencryptID(context);
                    if (!TextUtils.isEmpty(multiProcessSP)) {
                        UMUtils.setMultiProcessSP(context, c, multiProcessSP);
                    }
                }
                if (!TextUtils.isEmpty(multiProcessSP)) {
                    String substring = multiProcessSP.substring(1, 9);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < substring.length(); i++) {
                        char charAt = substring.charAt(i);
                        if (Character.isDigit(charAt)) {
                            if (Integer.parseInt(Character.toString(charAt)) == 0) {
                                sb.append(0);
                            } else {
                                sb.append(10 - Integer.parseInt(Character.toString(charAt)));
                            }
                        } else {
                            sb.append(charAt);
                        }
                    }
                    a = sb.toString();
                }
                if (TextUtils.isEmpty(a)) {
                    return;
                }
                a += new StringBuilder(a).reverse().toString();
                String multiProcessSP2 = UMUtils.getMultiProcessSP(context, d);
                if (TextUtils.isEmpty(multiProcessSP2)) {
                    UMUtils.setMultiProcessSP(context, d, a(b));
                } else {
                    b.equals(b(multiProcessSP2));
                }
            }
        } catch (Throwable unused) {
        }
    }

    public String a(String str) {
        try {
            return TextUtils.isEmpty(a) ? str : Base64.encodeToString(DataHelper.encrypt(str.getBytes(), a.getBytes()), 0);
        } catch (Exception unused) {
            return null;
        }
    }

    public String b(String str) {
        try {
            return TextUtils.isEmpty(a) ? str : new String(DataHelper.decrypt(Base64.decode(str.getBytes(), 0), a.getBytes()));
        } catch (Exception unused) {
            return null;
        }
    }
}