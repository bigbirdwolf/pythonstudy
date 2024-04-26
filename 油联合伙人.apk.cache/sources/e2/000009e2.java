package com.alipay.security.mobile.module.b;

import android.app.KeyguardManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.os.EnvironmentCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.umeng.commonsdk.proguard.e;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.yltx.oil.partner.data.network.Config;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class b {
    private static b a = new b();

    private b() {
    }

    private static String A() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                        return nextElement.getHostAddress().toString();
                    }
                }
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static b a() {
        return a;
    }

    private static String a(BluetoothAdapter bluetoothAdapter) {
        Object obj;
        try {
            Field declaredField = BluetoothAdapter.class.getDeclaredField("mService");
            declaredField.setAccessible(true);
            obj = declaredField.get(bluetoothAdapter);
        } catch (Throwable unused) {
        }
        if (obj == null) {
            return null;
        }
        Method declaredMethod = obj.getClass().getDeclaredMethod("getAddress", new Class[0]);
        declaredMethod.setAccessible(true);
        Object invoke = declaredMethod.invoke(obj, new Object[0]);
        if (invoke != null && (invoke instanceof String)) {
            return (String) invoke;
        }
        return null;
    }

    public static String a(Context context) {
        if (a(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        String str = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Config.PHONE);
                if (telephonyManager != null) {
                    str = telephonyManager.getDeviceId();
                }
            } catch (Throwable unused) {
            }
        }
        return str == null ? "" : str;
    }

    private static boolean a(Context context, String str) {
        return !(context.getPackageManager().checkPermission(str, context.getPackageName()) == 0);
    }

    public static String b() {
        long j;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (Throwable unused) {
            j = 0;
        }
        return String.valueOf(j);
    }

    public static String b(Context context) {
        String str = "";
        if (a(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Config.PHONE);
                if (telephonyManager != null) {
                    str = telephonyManager.getSubscriberId();
                }
            } catch (Throwable unused) {
            }
        }
        return str == null ? "" : str;
    }

    public static String c() {
        long j = 0;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(com.alipay.security.mobile.module.a.a.a().getPath());
                j = statFs.getBlockSize() * statFs.getAvailableBlocks();
            }
        } catch (Throwable unused) {
        }
        return String.valueOf(j);
    }

    public static String c(Context context) {
        int i;
        try {
            i = Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0);
        } catch (Throwable unused) {
            i = 0;
        }
        return i == 1 ? "1" : "0";
    }

    public static String d() {
        return "";
    }

    public static String d(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            int i = audioManager.getRingerMode() == 0 ? 1 : 0;
            int streamVolume = audioManager.getStreamVolume(0);
            int streamVolume2 = audioManager.getStreamVolume(1);
            int streamVolume3 = audioManager.getStreamVolume(2);
            int streamVolume4 = audioManager.getStreamVolume(3);
            int streamVolume5 = audioManager.getStreamVolume(4);
            jSONObject.put("ringermode", String.valueOf(i));
            jSONObject.put("call", String.valueOf(streamVolume));
            jSONObject.put("system", String.valueOf(streamVolume2));
            jSONObject.put("ring", String.valueOf(streamVolume3));
            jSONObject.put("music", String.valueOf(streamVolume4));
            jSONObject.put(NotificationCompat.CATEGORY_ALARM, String.valueOf(streamVolume5));
        } catch (Throwable unused) {
        }
        return jSONObject.toString();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public static java.lang.String e() {
        /*
            java.lang.String r0 = "0000000000000000"
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L6e
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L6e
            java.lang.String r4 = "/proc/cpuinfo"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L6e
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L6e
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L59
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L59
            java.io.LineNumberReader r4 = new java.io.LineNumberReader     // Catch: java.lang.Throwable -> L70
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L70
            r1 = 1
            r5 = 1
        L1b:
            r6 = 100
            if (r5 >= r6) goto L4a
            java.lang.String r6 = r4.readLine()     // Catch: java.lang.Throwable -> L48
            if (r6 == 0) goto L4a
            java.lang.String r7 = "Serial"
            int r7 = r6.indexOf(r7)     // Catch: java.lang.Throwable -> L48
            if (r7 < 0) goto L42
            java.lang.String r5 = ":"
            int r5 = r6.indexOf(r5)     // Catch: java.lang.Throwable -> L48
            int r5 = r5 + r1
            int r1 = r6.length()     // Catch: java.lang.Throwable -> L48
            java.lang.String r1 = r6.substring(r5, r1)     // Catch: java.lang.Throwable -> L48
            java.lang.String r1 = r1.trim()     // Catch: java.lang.Throwable -> L48
            r0 = r1
            goto L4a
        L42:
            int r5 = r5 + 1
            goto L1b
        L45:
            r0 = move-exception
            r1 = r4
            goto L5e
        L48:
            r1 = r4
            goto L70
        L4a:
            r4.close()     // Catch: java.lang.Throwable -> L4d
        L4d:
            r3.close()     // Catch: java.lang.Throwable -> L50
        L50:
            r2.close()     // Catch: java.lang.Throwable -> L7d
            goto L7d
        L54:
            r0 = move-exception
            goto L5e
        L56:
            r0 = move-exception
            r3 = r1
            goto L5e
        L59:
            r3 = r1
            goto L70
        L5b:
            r0 = move-exception
            r2 = r1
            r3 = r2
        L5e:
            if (r1 == 0) goto L63
            r1.close()     // Catch: java.lang.Throwable -> L63
        L63:
            if (r3 == 0) goto L68
            r3.close()     // Catch: java.lang.Throwable -> L68
        L68:
            if (r2 == 0) goto L6d
            r2.close()     // Catch: java.lang.Throwable -> L6d
        L6d:
            throw r0
        L6e:
            r2 = r1
            r3 = r2
        L70:
            if (r1 == 0) goto L75
            r1.close()     // Catch: java.lang.Throwable -> L75
        L75:
            if (r3 == 0) goto L7a
            r3.close()     // Catch: java.lang.Throwable -> L7a
        L7a:
            if (r2 == 0) goto L7d
            goto L50
        L7d:
            if (r0 != 0) goto L81
            java.lang.String r0 = ""
        L81:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.b.b.e():java.lang.String");
    }

    public static String e(Context context) {
        String str = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Config.PHONE);
                if (telephonyManager != null) {
                    str = telephonyManager.getNetworkOperatorName();
                }
            } catch (Throwable unused) {
            }
        }
        return (str == null || "null".equals(str)) ? "" : str;
    }

    public static String f(Context context) {
        List<Sensor> sensorList;
        String str = null;
        if (context != null) {
            try {
                SensorManager sensorManager = (SensorManager) context.getSystemService(e.aa);
                if (sensorManager != null && (sensorList = sensorManager.getSensorList(-1)) != null && sensorList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (Sensor sensor : sensorList) {
                        sb.append(sensor.getName());
                        sb.append(sensor.getVersion());
                        sb.append(sensor.getVendor());
                    }
                    str = com.alipay.security.mobile.module.a.a.e(sb.toString());
                }
            } catch (Throwable unused) {
            }
        }
        return str == null ? "" : str;
    }

    public static String g() {
        String x = x();
        return !com.alipay.security.mobile.module.a.a.a(x) ? x : y();
    }

    public static String g(Context context) {
        List<Sensor> sensorList;
        JSONArray jSONArray = new JSONArray();
        if (context != null) {
            try {
                SensorManager sensorManager = (SensorManager) context.getSystemService(e.aa);
                if (sensorManager != null && (sensorList = sensorManager.getSensorList(-1)) != null && sensorList.size() > 0) {
                    for (Sensor sensor : sensorList) {
                        if (sensor != null) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("name", sensor.getName());
                            jSONObject.put("version", sensor.getVersion());
                            jSONObject.put("vendor", sensor.getVendor());
                            jSONArray.put(jSONObject);
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return jSONArray.toString();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public static java.lang.String h() {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch: java.lang.Throwable -> L46
            java.lang.String r2 = "/proc/cpuinfo"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L46
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L47
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L47
            java.lang.String r0 = r2.readLine()     // Catch: java.lang.Throwable -> L30
            java.lang.String r3 = ":\\s+"
            r4 = 2
            java.lang.String[] r0 = r0.split(r3, r4)     // Catch: java.lang.Throwable -> L30
            if (r0 == 0) goto L27
            int r3 = r0.length     // Catch: java.lang.Throwable -> L30
            r4 = 1
            if (r3 <= r4) goto L27
            r0 = r0[r4]     // Catch: java.lang.Throwable -> L30
            r1.close()     // Catch: java.lang.Throwable -> L23
        L23:
            r2.close()     // Catch: java.lang.Throwable -> L26
        L26:
            return r0
        L27:
            r1.close()     // Catch: java.lang.Throwable -> L2a
        L2a:
            r2.close()     // Catch: java.lang.Throwable -> L51
            goto L51
        L2e:
            r0 = move-exception
            goto L3b
        L30:
            r0 = r2
            goto L47
        L32:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
            goto L3b
        L37:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
        L3b:
            if (r1 == 0) goto L40
            r1.close()     // Catch: java.lang.Throwable -> L40
        L40:
            if (r2 == 0) goto L45
            r2.close()     // Catch: java.lang.Throwable -> L45
        L45:
            throw r0
        L46:
            r1 = r0
        L47:
            if (r1 == 0) goto L4c
            r1.close()     // Catch: java.lang.Throwable -> L4c
        L4c:
            if (r0 == 0) goto L51
            r0.close()     // Catch: java.lang.Throwable -> L51
        L51:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.b.b.h():java.lang.String");
    }

    public static String h(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return Integer.toString(displayMetrics.widthPixels) + "*" + Integer.toString(displayMetrics.heightPixels);
        } catch (Throwable unused) {
            return "";
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public static java.lang.String i() {
        /*
            java.lang.String r0 = "/proc/meminfo"
            r1 = 0
            r2 = 0
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.lang.Throwable -> L45
            r4.<init>(r0)     // Catch: java.lang.Throwable -> L45
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L46
            r5 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r4, r5)     // Catch: java.lang.Throwable -> L46
            java.lang.String r1 = r0.readLine()     // Catch: java.lang.Throwable -> L2f
            if (r1 == 0) goto L26
            java.lang.String r5 = "\\s+"
            java.lang.String[] r1 = r1.split(r5)     // Catch: java.lang.Throwable -> L2f
            r5 = 1
            r1 = r1[r5]     // Catch: java.lang.Throwable -> L2f
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.Throwable -> L2f
            long r1 = (long) r1
            r2 = r1
        L26:
            r4.close()     // Catch: java.lang.Throwable -> L29
        L29:
            r0.close()     // Catch: java.lang.Throwable -> L50
            goto L50
        L2d:
            r1 = move-exception
            goto L3a
        L2f:
            r1 = r0
            goto L46
        L31:
            r0 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L3a
        L36:
            r0 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L3a:
            if (r4 == 0) goto L3f
            r4.close()     // Catch: java.lang.Throwable -> L3f
        L3f:
            if (r0 == 0) goto L44
            r0.close()     // Catch: java.lang.Throwable -> L44
        L44:
            throw r1
        L45:
            r4 = r1
        L46:
            if (r4 == 0) goto L4b
            r4.close()     // Catch: java.lang.Throwable -> L4b
        L4b:
            if (r1 == 0) goto L50
            r1.close()     // Catch: java.lang.Throwable -> L50
        L50:
            java.lang.String r0 = java.lang.String.valueOf(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.b.b.i():java.lang.String");
    }

    public static String i(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            StringBuilder sb = new StringBuilder();
            sb.append(displayMetrics.widthPixels);
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String j() {
        long j;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = statFs.getBlockCount() * statFs.getBlockSize();
        } catch (Throwable unused) {
            j = 0;
        }
        return String.valueOf(j);
    }

    public static String j(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            StringBuilder sb = new StringBuilder();
            sb.append(displayMetrics.heightPixels);
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String k() {
        long j = 0;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                j = statFs.getBlockSize() * statFs.getBlockCount();
            }
        } catch (Throwable unused) {
        }
        return String.valueOf(j);
    }

    public static String k(Context context) {
        if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
            return "";
        }
        try {
            String macAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (macAddress != null) {
                try {
                    if (macAddress.length() != 0 && !"02:00:00:00:00:00".equals(macAddress)) {
                        return macAddress;
                    }
                } catch (Throwable unused) {
                    return macAddress;
                }
            }
            return w();
        } catch (Throwable unused2) {
            return "";
        }
    }

    public static String l() {
        String str = "";
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str = (String) cls.getMethod("get", String.class, String.class).invoke(cls.newInstance(), "gsm.version.baseband", "no message");
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    public static String l(Context context) {
        if (a(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        String str = "";
        try {
            String simSerialNumber = ((TelephonyManager) context.getSystemService(Config.PHONE)).getSimSerialNumber();
            if (simSerialNumber != null) {
                if (simSerialNumber == null) {
                    return simSerialNumber;
                }
                try {
                    if (simSerialNumber.length() != 0) {
                        return simSerialNumber;
                    }
                } catch (Throwable unused) {
                    return simSerialNumber;
                }
            }
            str = "";
        } catch (Throwable unused2) {
        }
        return str;
    }

    public static String m() {
        String str = "";
        try {
            str = Build.SERIAL;
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    public static String m(Context context) {
        String str;
        try {
            str = Settings.Secure.getString(context.getContentResolver(), SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID);
        } catch (Throwable unused) {
            str = "";
        }
        return str == null ? "" : str;
    }

    public static String n() {
        String str = "";
        try {
            str = Locale.getDefault().toString();
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x001d, code lost:
        if ("02:00:00:00:00:00".equals(r0) == false) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String n(android.content.Context r2) {
        /*
            java.lang.String r0 = "android.permission.BLUETOOTH"
            boolean r0 = a(r2, r0)
            if (r0 == 0) goto Lb
            java.lang.String r2 = ""
            return r2
        Lb:
            java.lang.String r0 = z()
            if (r0 == 0) goto L1f
            int r1 = r0.length()     // Catch: java.lang.Throwable -> L2f
            if (r1 == 0) goto L1f
            java.lang.String r1 = "02:00:00:00:00:00"
            boolean r1 = r1.equals(r0)     // Catch: java.lang.Throwable -> L2f
            if (r1 == 0) goto L2a
        L1f:
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch: java.lang.Throwable -> L2f
            java.lang.String r1 = "bluetooth_address"
            java.lang.String r2 = android.provider.Settings.Secure.getString(r2, r1)     // Catch: java.lang.Throwable -> L2f
            r0 = r2
        L2a:
            if (r0 != 0) goto L2f
            java.lang.String r2 = ""
            goto L30
        L2f:
            r2 = r0
        L30:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.b.b.n(android.content.Context):java.lang.String");
    }

    public static String o() {
        String str = "";
        try {
            str = TimeZone.getDefault().getDisplayName(false, 0);
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    public static String o(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Config.PHONE);
            return telephonyManager != null ? String.valueOf(telephonyManager.getNetworkType()) : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String p() {
        try {
            long currentTimeMillis = System.currentTimeMillis() - SystemClock.elapsedRealtime();
            StringBuilder sb = new StringBuilder();
            sb.append(currentTimeMillis - (currentTimeMillis % 1000));
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String p(Context context) {
        String str = "";
        if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
            return "";
        }
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager.isWifiEnabled()) {
                str = wifiManager.getConnectionInfo().getBSSID();
            }
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    public static String q() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(SystemClock.elapsedRealtime());
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String q(Context context) {
        try {
            String t = t(context);
            String A = A();
            if (com.alipay.security.mobile.module.a.a.b(t) && com.alipay.security.mobile.module.a.a.b(A)) {
                return t + ":" + A();
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String r() {
        try {
            StringBuilder sb = new StringBuilder();
            String[] strArr = {"/dev/qemu_pipe", "/dev/socket/qemud", "/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace", "/system/bin/qemu-props", "/dev/socket/genyd", "/dev/socket/baseband_genyd"};
            sb.append("00:");
            for (int i = 0; i < 7; i++) {
                sb.append(new File(strArr[i]).exists() ? "1" : "0");
            }
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String r(Context context) {
        try {
            long j = 0;
            if (((KeyguardManager) context.getSystemService("keyguard")).isKeyguardSecure()) {
                String[] strArr = {"/data/system/password.key", "/data/system/gesture.key", "/data/system/gatekeeper.password.key", "/data/system/gatekeeper.gesture.key", "/data/system/gatekeeper.pattern.key"};
                for (int i = 0; i < 5; i++) {
                    long j2 = -1;
                    try {
                        j2 = new File(strArr[i]).lastModified();
                    } catch (Throwable unused) {
                    }
                    j = Math.max(j2, j);
                }
                return "1:" + j;
            }
            return "0:0";
        } catch (Throwable unused2) {
            return "";
        }
    }

    public static String s() {
        String[] strArr = {"dalvik.system.Taint"};
        StringBuilder sb = new StringBuilder();
        sb.append("00");
        sb.append(":");
        for (int i = 0; i <= 0; i++) {
            try {
                Class.forName(strArr[0]);
                sb.append("1");
            } catch (Throwable unused) {
                sb.append("0");
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String s(android.content.Context r3) {
        /*
            android.content.IntentFilter r0 = new android.content.IntentFilter     // Catch: java.lang.Throwable -> L3f
            java.lang.String r1 = "android.intent.action.BATTERY_CHANGED"
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L3f
            r1 = 0
            android.content.Intent r3 = r3.registerReceiver(r1, r0)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r0 = "level"
            r1 = -1
            int r0 = r3.getIntExtra(r0, r1)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r2 = "status"
            int r3 = r3.getIntExtra(r2, r1)     // Catch: java.lang.Throwable -> L3f
            r1 = 2
            if (r3 == r1) goto L22
            r1 = 5
            if (r3 != r1) goto L20
            goto L22
        L20:
            r3 = 0
            goto L23
        L22:
            r3 = 1
        L23:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3f
            r1.<init>()     // Catch: java.lang.Throwable -> L3f
            if (r3 == 0) goto L2d
            java.lang.String r3 = "1"
            goto L2f
        L2d:
            java.lang.String r3 = "0"
        L2f:
            r1.append(r3)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r3 = ":"
            r1.append(r3)     // Catch: java.lang.Throwable -> L3f
            r1.append(r0)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r3 = r1.toString()     // Catch: java.lang.Throwable -> L3f
            return r3
        L3f:
            java.lang.String r3 = ""
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.b.b.s(android.content.Context):java.lang.String");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public static java.lang.String t() {
        /*
            java.lang.String r0 = "00"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.util.LinkedHashMap r2 = new java.util.LinkedHashMap
            r2.<init>()
            java.lang.String r3 = "/system/build.prop"
            java.lang.String r4 = "ro.product.name=sdk"
            r2.put(r3, r4)
            java.lang.String r3 = "/proc/tty/drivers"
            java.lang.String r4 = "goldfish"
            r2.put(r3, r4)
            java.lang.String r3 = "/proc/cpuinfo"
            java.lang.String r4 = "goldfish"
            r2.put(r3, r4)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r0 = ":"
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.append(r0)
            java.util.Set r0 = r2.keySet()
            java.util.Iterator r0 = r0.iterator()
        L3d:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L92
            java.lang.Object r3 = r0.next()
            java.lang.String r3 = (java.lang.String) r3
            r4 = 0
            r5 = 48
            java.io.LineNumberReader r6 = new java.io.LineNumberReader     // Catch: java.lang.Throwable -> L89
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L89
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L89
            r8.<init>(r3)     // Catch: java.lang.Throwable -> L89
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L89
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L89
        L5b:
            java.lang.String r4 = r6.readLine()     // Catch: java.lang.Throwable -> L7d
            if (r4 == 0) goto L73
            java.lang.String r4 = r4.toLowerCase()     // Catch: java.lang.Throwable -> L7d
            java.lang.Object r7 = r2.get(r3)     // Catch: java.lang.Throwable -> L7d
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7     // Catch: java.lang.Throwable -> L7d
            boolean r4 = r4.contains(r7)     // Catch: java.lang.Throwable -> L7d
            if (r4 == 0) goto L5b
            r5 = 49
        L73:
            r1.append(r5)
            r6.close()     // Catch: java.lang.Throwable -> L3d
            goto L3d
        L7a:
            r0 = move-exception
            r4 = r6
            goto L80
        L7d:
            r4 = r6
            goto L89
        L7f:
            r0 = move-exception
        L80:
            r1.append(r5)
            if (r4 == 0) goto L88
            r4.close()     // Catch: java.lang.Throwable -> L88
        L88:
            throw r0
        L89:
            r1.append(r5)
            if (r4 == 0) goto L3d
            r4.close()     // Catch: java.lang.Throwable -> L3d
            goto L3d
        L92:
            java.lang.String r0 = r1.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.b.b.t():java.lang.String");
    }

    private static String t(Context context) {
        if (a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return "";
        }
        String str = null;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == 1) {
                    return "WIFI";
                }
                if (activeNetworkInfo.getType() == 0) {
                    int subtype = activeNetworkInfo.getSubtype();
                    if (subtype != 4 && subtype != 1 && subtype != 2 && subtype != 7 && subtype != 11) {
                        if (subtype != 3 && subtype != 5 && subtype != 6 && subtype != 8 && subtype != 9 && subtype != 10 && subtype != 12 && subtype != 14 && subtype != 15) {
                            if (subtype == 13) {
                                return "4G";
                            }
                            str = "UNKNOW";
                        }
                        return "3G";
                    }
                    return "2G";
                }
            }
        } catch (Throwable unused) {
        }
        return str;
    }

    public static String u() {
        StringBuilder sb = new StringBuilder();
        sb.append("00:");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("BRAND", "generic");
        linkedHashMap.put("BOARD", EnvironmentCompat.MEDIA_UNKNOWN);
        linkedHashMap.put("DEVICE", "generic");
        linkedHashMap.put("HARDWARE", "goldfish");
        linkedHashMap.put("PRODUCT", "sdk");
        linkedHashMap.put("MODEL", "sdk");
        for (String str : linkedHashMap.keySet()) {
            char c = '0';
            try {
                String str2 = (String) Build.class.getField(str).get(null);
                String str3 = (String) linkedHashMap.get(str);
                String lowerCase = str2 != null ? str2.toLowerCase() : null;
                if (lowerCase != null && lowerCase.contains(str3)) {
                    c = '1';
                }
            } catch (Throwable unused) {
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static String v() {
        StringBuilder sb = new StringBuilder();
        sb.append("00:");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("ro.hardware", "goldfish");
        linkedHashMap.put("ro.kernel.qemu", "1");
        linkedHashMap.put("ro.product.device", "generic");
        linkedHashMap.put("ro.product.model", "sdk");
        linkedHashMap.put("ro.product.brand", "generic");
        linkedHashMap.put("ro.product.name", "sdk");
        linkedHashMap.put("ro.build.fingerprint", "test-keys");
        linkedHashMap.put("ro.product.manufacturer", "unknow");
        for (String str : linkedHashMap.keySet()) {
            char c = '0';
            String str2 = (String) linkedHashMap.get(str);
            String b = com.alipay.security.mobile.module.a.a.b(str, "");
            if (b != null && b.contains(str2)) {
                c = '1';
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private static String w() {
        try {
            ArrayList<NetworkInterface> list = Collections.list(NetworkInterface.getNetworkInterfaces());
            if (list != null) {
                for (NetworkInterface networkInterface : list) {
                    if (networkInterface != null && networkInterface.getName() != null && networkInterface.getName().equalsIgnoreCase("wlan0")) {
                        byte[] hardwareAddress = networkInterface.getHardwareAddress();
                        if (hardwareAddress == null) {
                            return "02:00:00:00:00:00";
                        }
                        StringBuilder sb = new StringBuilder();
                        int length = hardwareAddress.length;
                        for (int i = 0; i < length; i++) {
                            sb.append(String.format("%02X:", Integer.valueOf(hardwareAddress[i] & 255)));
                        }
                        if (sb.length() > 0) {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        return sb.toString();
                    }
                }
                return "02:00:00:00:00:00";
            }
            return "02:00:00:00:00:00";
        } catch (Throwable unused) {
            return "02:00:00:00:00:00";
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    private static java.lang.String x() {
        /*
            java.lang.String r0 = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L41
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L41
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L42
            r3 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r2, r3)     // Catch: java.lang.Throwable -> L42
            java.lang.String r1 = r0.readLine()     // Catch: java.lang.Throwable -> L30
            boolean r3 = com.alipay.security.mobile.module.a.a.a(r1)     // Catch: java.lang.Throwable -> L30
            if (r3 != 0) goto L24
            java.lang.String r1 = r1.trim()     // Catch: java.lang.Throwable -> L30
            r0.close()     // Catch: java.lang.Throwable -> L20
        L20:
            r2.close()     // Catch: java.lang.Throwable -> L23
        L23:
            return r1
        L24:
            r0.close()     // Catch: java.lang.Throwable -> L27
        L27:
            r2.close()     // Catch: java.lang.Throwable -> L4a
            goto L4a
        L2b:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L36
        L30:
            r1 = r0
            goto L42
        L32:
            r0 = move-exception
            goto L36
        L34:
            r0 = move-exception
            r2 = r1
        L36:
            if (r1 == 0) goto L3b
            r1.close()     // Catch: java.lang.Throwable -> L3b
        L3b:
            if (r2 == 0) goto L40
            r2.close()     // Catch: java.lang.Throwable -> L40
        L40:
            throw r0
        L41:
            r2 = r1
        L42:
            if (r1 == 0) goto L47
            r1.close()     // Catch: java.lang.Throwable -> L47
        L47:
            if (r2 == 0) goto L4a
            goto L27
        L4a:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.b.b.x():java.lang.String");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    private static java.lang.String y() {
        /*
            java.lang.String r0 = "/proc/cpuinfo"
            java.lang.String r1 = ""
            r2 = 0
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L57
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L57
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L58
            r4 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r3, r4)     // Catch: java.lang.Throwable -> L58
        L11:
            java.lang.String r2 = r0.readLine()     // Catch: java.lang.Throwable -> L44
            if (r2 == 0) goto L3b
            boolean r4 = com.alipay.security.mobile.module.a.a.a(r2)     // Catch: java.lang.Throwable -> L44
            if (r4 != 0) goto L11
            java.lang.String r4 = ":"
            java.lang.String[] r2 = r2.split(r4)     // Catch: java.lang.Throwable -> L44
            if (r2 == 0) goto L11
            int r4 = r2.length     // Catch: java.lang.Throwable -> L44
            r5 = 1
            if (r4 <= r5) goto L11
            r4 = 0
            r4 = r2[r4]     // Catch: java.lang.Throwable -> L44
            java.lang.String r6 = "BogoMIPS"
            boolean r4 = r4.contains(r6)     // Catch: java.lang.Throwable -> L44
            if (r4 == 0) goto L11
            r2 = r2[r5]     // Catch: java.lang.Throwable -> L44
            java.lang.String r2 = r2.trim()     // Catch: java.lang.Throwable -> L44
            r1 = r2
        L3b:
            r3.close()     // Catch: java.lang.Throwable -> L3e
        L3e:
            r0.close()     // Catch: java.lang.Throwable -> L62
            goto L62
        L42:
            r1 = move-exception
            goto L4c
        L44:
            r2 = r0
            goto L58
        L46:
            r1 = move-exception
            r0 = r2
            goto L4c
        L49:
            r1 = move-exception
            r0 = r2
            r3 = r0
        L4c:
            if (r3 == 0) goto L51
            r3.close()     // Catch: java.lang.Throwable -> L51
        L51:
            if (r0 == 0) goto L56
            r0.close()     // Catch: java.lang.Throwable -> L56
        L56:
            throw r1
        L57:
            r3 = r2
        L58:
            if (r3 == 0) goto L5d
            r3.close()     // Catch: java.lang.Throwable -> L5d
        L5d:
            if (r2 == 0) goto L62
            r2.close()     // Catch: java.lang.Throwable -> L62
        L62:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.b.b.y():java.lang.String");
    }

    private static String z() {
        BluetoothAdapter bluetoothAdapter;
        String str = "";
        try {
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (bluetoothAdapter != null) {
                try {
                    if (!bluetoothAdapter.isEnabled()) {
                        return "";
                    }
                } catch (Throwable unused) {
                }
            }
            str = bluetoothAdapter.getAddress();
        } catch (Throwable unused2) {
            bluetoothAdapter = null;
        }
        if (str == null || str.endsWith("00:00:00:00:00")) {
            try {
                str = a(bluetoothAdapter);
            } catch (Throwable unused3) {
            }
        }
        return str == null ? "" : str;
    }

    public final String f() {
        try {
            return String.valueOf(new File("/sys/devices/system/cpu/").listFiles(new c(this)).length);
        } catch (Throwable unused) {
            return "1";
        }
    }
}