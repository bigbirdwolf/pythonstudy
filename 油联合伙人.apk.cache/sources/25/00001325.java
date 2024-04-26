package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.os.EnvironmentCompat;
import android.text.TextUtils;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONObject;

/* compiled from: UMProbe.java */
/* loaded from: classes.dex */
public class l {
    public static final String a = "UM_PROBE_DATA";
    public static final String b = "_dsk_s";
    public static final String c = "_thm_z";
    public static final String d = "_gdf_r";
    private static Object e = new Object();

    public static String a(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(a, 0);
            if (sharedPreferences != null) {
                JSONObject jSONObject = new JSONObject();
                synchronized (e) {
                    jSONObject.put(b, sharedPreferences.getString(b, ""));
                    jSONObject.put(c, sharedPreferences.getString(c, ""));
                    jSONObject.put(d, sharedPreferences.getString(d, ""));
                }
                return jSONObject.toString();
            }
            return null;
        } catch (Exception e2) {
            UMCrashManager.reportCrash(context, e2);
            return null;
        }
    }

    public static void b(final Context context) {
        if (c(context)) {
            return;
        }
        final String[] strArr = {EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN};
        new Thread() { // from class: com.umeng.commonsdk.internal.utils.l.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    strArr[0] = l.c();
                    strArr[1] = l.a();
                    strArr[2] = l.b();
                    ULog.i("diskType = " + strArr[0] + "; ThremalZone = " + strArr[1] + "; GoldFishRc = " + strArr[2]);
                    l.b(context, strArr);
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(context, th);
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, String[] strArr) {
        SharedPreferences sharedPreferences;
        if (context == null || (sharedPreferences = context.getApplicationContext().getSharedPreferences(a, 0)) == null) {
            return;
        }
        synchronized (e) {
            sharedPreferences.edit().putString(b, strArr[0]).putString(c, strArr[1]).putString(d, strArr[2]).commit();
        }
    }

    public static boolean c(Context context) {
        SharedPreferences sharedPreferences;
        return (context == null || (sharedPreferences = context.getApplicationContext().getSharedPreferences(a, 0)) == null || TextUtils.isEmpty(sharedPreferences.getString(b, ""))) ? false : true;
    }

    public static int a(String str, String str2) throws IOException {
        int i;
        Process exec = Runtime.getRuntime().exec(str);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                i = -1;
                break;
            } else if (readLine.contains(str2)) {
                i = 1;
                break;
            }
        }
        try {
            if (exec.waitFor() != 0) {
                return -1;
            }
            return i;
        } catch (InterruptedException unused) {
            return -1;
        }
    }

    public static String a() {
        int i;
        try {
            i = a("ls /sys/class/thermal", "thermal_zone");
        } catch (Throwable unused) {
            i = -1;
        }
        return i > 0 ? "thermal_zone" : i < 0 ? "noper" : EnvironmentCompat.MEDIA_UNKNOWN;
    }

    public static String b() {
        int i;
        try {
            i = a("ls /", "goldfish");
        } catch (Throwable unused) {
            i = -1;
        }
        return i > 0 ? "goldfish" : i < 0 ? "noper" : EnvironmentCompat.MEDIA_UNKNOWN;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x003b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String c() {
        /*
            java.lang.String r0 = "unknown"
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L36
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L36
            java.lang.String r4 = "/proc/diskstats"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L36
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L36
            java.lang.String r1 = "mmcblk"
            java.lang.String r3 = "sda"
            java.lang.String r4 = "mtd"
        L15:
            java.lang.String r5 = r2.readLine()     // Catch: java.lang.Throwable -> L37
            if (r5 == 0) goto L39
            boolean r6 = r5.contains(r1)     // Catch: java.lang.Throwable -> L37
            if (r6 == 0) goto L24
            java.lang.String r0 = "mmcblk"
            goto L39
        L24:
            boolean r6 = r5.contains(r3)     // Catch: java.lang.Throwable -> L37
            if (r6 == 0) goto L2d
            java.lang.String r0 = "sda"
            goto L39
        L2d:
            boolean r5 = r5.contains(r4)     // Catch: java.lang.Throwable -> L37
            if (r5 == 0) goto L15
            java.lang.String r0 = "mtd"
            goto L39
        L36:
            r2 = r1
        L37:
            java.lang.String r0 = "noper"
        L39:
            if (r2 == 0) goto L3e
            r2.close()     // Catch: java.lang.Throwable -> L3e
        L3e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.utils.l.c():java.lang.String");
    }
}