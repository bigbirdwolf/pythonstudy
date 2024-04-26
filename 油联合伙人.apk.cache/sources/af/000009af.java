package com.alipay.sdk.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.yltx.oil.partner.data.network.Config;

/* loaded from: classes.dex */
public class a {
    private static final String a = "00:00:00:00:00:00";
    private static a e;
    private String b;
    private String c;
    private String d;

    public static a a(Context context) {
        if (e == null) {
            e = new a(context);
        }
        return e;
    }

    private a(Context context) {
        try {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService(Config.PHONE);
                b(telephonyManager.getDeviceId());
                a(telephonyManager.getSubscriberId());
                this.d = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
                if (!TextUtils.isEmpty(this.d)) {
                    return;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (!TextUtils.isEmpty(this.d)) {
                    return;
                }
            }
            this.d = a;
        } catch (Throwable th) {
            if (TextUtils.isEmpty(this.d)) {
                this.d = a;
            }
            throw th;
        }
    }

    public String a() {
        if (TextUtils.isEmpty(this.b)) {
            this.b = "000000000000000";
        }
        return this.b;
    }

    public String b() {
        if (TextUtils.isEmpty(this.c)) {
            this.c = "000000000000000";
        }
        return this.c;
    }

    public void a(String str) {
        if (str != null) {
            str = (str + "000000000000000").substring(0, 15);
        }
        this.b = str;
    }

    public void b(String str) {
        if (str != null) {
            byte[] bytes = str.getBytes();
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] < 48 || bytes[i] > 57) {
                    bytes[i] = 48;
                }
            }
            String str2 = new String(bytes);
            str = (str2 + "000000000000000").substring(0, 15);
        }
        this.c = str;
    }

    public String c() {
        String str = b() + "|";
        String a2 = a();
        if (TextUtils.isEmpty(a2)) {
            return str + "000000000000000";
        }
        return str + a2;
    }

    public String d() {
        return this.d;
    }

    public static d b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 0) {
                return d.a(activeNetworkInfo.getSubtype());
            }
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 1) {
                return d.WIFI;
            }
            return d.NONE;
        } catch (Exception unused) {
            return d.NONE;
        }
    }

    public static String c(Context context) {
        return a(context).c().substring(0, 8);
    }

    public static String d(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getResources().getConfiguration().locale.toString();
        } catch (Throwable unused) {
            return "";
        }
    }
}