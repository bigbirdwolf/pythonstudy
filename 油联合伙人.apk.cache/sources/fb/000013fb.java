package com.umeng.commonsdk.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.yltx.oil.partner.data.network.Config;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes.dex */
public class UMUtils {
    public static final int DEFAULT_TIMEZONE = 8;
    private static final String KEY_APP_KEY = "appkey";
    private static final String KEY_CHANNEL = "channel";
    private static final String KEY_LAST_APP_KEY = "last_appkey";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_SHARED_PREFERENCES_NAME = "umeng_common_config";
    public static final String MOBILE_NETWORK = "2G/3G";
    private static final String SD_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final String TAG = "UMUtils";
    public static final String UNKNOW = "";
    public static final String WIFI = "Wi-Fi";
    private static final Pattern pattern = Pattern.compile("UTDID\">([^<]+)");
    private static Object spLock = new Object();

    public static void setMultiProcessSP(Context context, String str, String str2) {
        SharedPreferences sharedPreferences;
        try {
            synchronized (spLock) {
                if (context != null && !TextUtils.isEmpty(str) && str2 != null) {
                    if (isMainProgress(context)) {
                        sharedPreferences = context.getApplicationContext().getSharedPreferences("umeng_common_config", 0);
                    } else {
                        String subProcessName = UMFrUtils.getSubProcessName(context);
                        Context applicationContext = context.getApplicationContext();
                        sharedPreferences = applicationContext.getSharedPreferences(subProcessName + "_umeng_common_config", 0);
                    }
                    if (sharedPreferences != null) {
                        sharedPreferences.edit().putString(str, str2).commit();
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static String getMultiProcessSP(Context context, String str) {
        SharedPreferences sharedPreferences;
        try {
            synchronized (spLock) {
                if (context != null && !TextUtils.isEmpty(str)) {
                    if (isMainProgress(context)) {
                        sharedPreferences = context.getApplicationContext().getSharedPreferences("umeng_common_config", 0);
                    } else {
                        String subProcessName = UMFrUtils.getSubProcessName(context);
                        Context applicationContext = context.getApplicationContext();
                        sharedPreferences = applicationContext.getSharedPreferences(subProcessName + "_umeng_common_config", 0);
                    }
                    if (sharedPreferences != null) {
                        return sharedPreferences.getString(str, null);
                    }
                    return null;
                }
                return null;
            }
        } catch (Exception unused) {
            return null;
        } catch (Throwable unused2) {
            return null;
        }
    }

    public static void setLastAppkey(Context context, String str) {
        if (context == null || str == null) {
            return;
        }
        try {
            setMultiProcessSP(context, KEY_LAST_APP_KEY, str);
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "set last app key e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "set last app key e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
        }
    }

    public static String getLastAppkey(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return getMultiProcessSP(context, KEY_LAST_APP_KEY);
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get last app key e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get last app key e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static void setAppkey(Context context, String str) {
        if (context == null || str == null) {
            return;
        }
        try {
            setMultiProcessSP(context, "appkey", str);
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "set app key e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "set app key e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
        }
    }

    public static String getAppkey(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return getMultiProcessSP(context, "appkey");
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app key e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app key e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static void setChannel(Context context, String str) {
        if (context == null || str == null) {
            return;
        }
        try {
            setMultiProcessSP(context, "channel", str);
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "set channel e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "set channel e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
        }
    }

    public static String getChannel(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return getMultiProcessSP(context, "channel");
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get channel e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get channel e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static String getUTDID(Context context) {
        if (context == null) {
            return null;
        }
        try {
            try {
                try {
                    return (String) Class.forName("com.ut.device.UTDevice").getMethod("getUtdid", Context.class).invoke(null, context.getApplicationContext());
                } catch (Throwable th) {
                    if (AnalyticsConstants.UM_DEBUG) {
                        Log.e(TAG, "get utiid e is " + th);
                    }
                    return null;
                }
            } catch (Exception unused) {
                return readUTDId(context);
            }
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get utiid e is " + e);
            }
            return null;
        }
    }

    private static String readUTDId(Context context) {
        File file;
        if (context == null || (file = getFile(context)) == null || !file.exists()) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            String parseId = parseId(readStreamToString(fileInputStream));
            safeClose(fileInputStream);
            return parseId;
        } catch (Exception unused) {
            return null;
        }
    }

    private static void safeClose(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    private static String parseId(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private static String readStreamToString(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        char[] cArr = new char[1024];
        StringWriter stringWriter = new StringWriter();
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (-1 != read) {
                stringWriter.write(cArr, 0, read);
            } else {
                return stringWriter.toString();
            }
        }
    }

    private static File getFile(Context context) {
        if (context != null && checkPermission(context, SD_PERMISSION) && Environment.getExternalStorageState().equals("mounted")) {
            try {
                return new File(Environment.getExternalStorageDirectory().getCanonicalPath(), ".UTSystemConfig/Global/Alvin2.xml");
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static String[] getGPU(GL10 gl10) {
        try {
            return new String[]{gl10.glGetString(7936), gl10.glGetString(7937)};
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "Could not read gpu infor, e is " + e);
            }
            return new String[0];
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "Could not read gpu infor, e is " + th);
            }
            return new String[0];
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0060 A[Catch: Throwable -> 0x003f, Exception -> 0x0041, TryCatch #5 {Exception -> 0x0041, Throwable -> 0x003f, blocks: (B:3:0x0001, B:4:0x0008, B:5:0x0013, B:25:0x0060, B:12:0x0024, B:14:0x0028, B:21:0x0044, B:23:0x0048), top: B:41:0x0001, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0071 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getCPU() {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41 java.io.FileNotFoundException -> L43
            java.lang.String r2 = "/proc/cpuinfo"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41 java.io.FileNotFoundException -> L43
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.io.IOException -> L23 java.lang.Throwable -> L3f java.lang.Exception -> L41
            r3 = 1024(0x400, float:1.435E-42)
            r2.<init>(r1, r3)     // Catch: java.io.IOException -> L23 java.lang.Throwable -> L3f java.lang.Exception -> L41
            java.lang.String r3 = r2.readLine()     // Catch: java.io.IOException -> L23 java.lang.Throwable -> L3f java.lang.Exception -> L41
            r2.close()     // Catch: java.io.IOException -> L1f java.lang.Throwable -> L3f java.lang.Exception -> L41
            r1.close()     // Catch: java.io.IOException -> L1f java.lang.Throwable -> L3f java.lang.Exception -> L41
            r0 = r3
            goto L5e
        L1b:
            r0 = move-exception
            r1 = r0
            r0 = r3
            goto L44
        L1f:
            r0 = move-exception
            r1 = r0
            r0 = r3
            goto L24
        L23:
            r1 = move-exception
        L24:
            boolean r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41 java.io.FileNotFoundException -> L43
            if (r2 == 0) goto L5e
            java.lang.String r2 = "UMUtils"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41 java.io.FileNotFoundException -> L43
            r3.<init>()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41 java.io.FileNotFoundException -> L43
            java.lang.String r4 = "Could not read from file /proc/cpuinfo, e is "
            r3.append(r4)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41 java.io.FileNotFoundException -> L43
            r3.append(r1)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41 java.io.FileNotFoundException -> L43
            java.lang.String r1 = r3.toString()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41 java.io.FileNotFoundException -> L43
            android.util.Log.e(r2, r1)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41 java.io.FileNotFoundException -> L43
            goto L5e
        L3f:
            r0 = move-exception
            goto L74
        L41:
            r0 = move-exception
            goto L91
        L43:
            r1 = move-exception
        L44:
            boolean r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            if (r2 == 0) goto L5e
            java.lang.String r2 = "UMUtils"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            r3.<init>()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            java.lang.String r4 = "Could not read from file /proc/cpuinfo, e is "
            r3.append(r4)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            r3.append(r1)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            java.lang.String r1 = r3.toString()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            android.util.Log.e(r2, r1)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
        L5e:
            if (r0 == 0) goto L71
            r1 = 58
            int r1 = r0.indexOf(r1)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            int r1 = r1 + 1
            java.lang.String r0 = r0.substring(r1)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            java.lang.String r0 = r0.trim()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            return r0
        L71:
            java.lang.String r0 = ""
            return r0
        L74:
            boolean r1 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r1 == 0) goto L8e
            java.lang.String r1 = "UMUtils"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "get cpu e is "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            android.util.Log.e(r1, r0)
        L8e:
            java.lang.String r0 = ""
            return r0
        L91:
            boolean r1 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r1 == 0) goto Lab
            java.lang.String r1 = "UMUtils"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "get cpu e is "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            android.util.Log.e(r1, r0)
        Lab:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.getCPU():java.lang.String");
    }

    public static String getImsi(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Config.PHONE);
            if (!checkPermission(context, "android.permission.READ_PHONE_STATE") || telephonyManager == null) {
                return null;
            }
            return telephonyManager.getSubscriberId();
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get imei e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get imei e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static String getRegisteredOperator(Context context) {
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Config.PHONE);
            if (telephonyManager != null && checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                return telephonyManager.getNetworkOperator();
            }
            return null;
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get registered operator e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get registered operator e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static String getNetworkOperatorName(Context context) {
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Config.PHONE);
            return (checkPermission(context, "android.permission.READ_PHONE_STATE") && telephonyManager != null) ? telephonyManager.getNetworkOperatorName() : "";
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get network operator e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get network operator e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return "";
        }
    }

    public static String getDisplayResolution(Context context) {
        if (context == null) {
            return "";
        }
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager == null) {
                return "";
            }
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            return String.valueOf(i2) + "*" + String.valueOf(i);
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get display resolution e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get display resolution e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return "";
        }
    }

    public static String[] getNetworkAccessMode(Context context) {
        String[] strArr = {"", ""};
        if (context == null) {
            return strArr;
        }
        try {
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get network access mode e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get network access mode e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
        }
        if (!checkPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
            strArr[0] = "";
            return strArr;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            strArr[0] = "";
            return strArr;
        }
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
            strArr[0] = "Wi-Fi";
            return strArr;
        }
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
        if (networkInfo2 != null && networkInfo2.getState() == NetworkInfo.State.CONNECTED) {
            strArr[0] = "2G/3G";
            strArr[1] = networkInfo2.getSubtypeName();
            return strArr;
        }
        return strArr;
    }

    public static boolean isSdCardWrittenable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static Locale getLocale(Context context) {
        Locale locale;
        if (context == null) {
            return null;
        }
        try {
            try {
                Configuration configuration = new Configuration();
                configuration.setToDefaults();
                Settings.System.getConfiguration(context.getContentResolver(), configuration);
                locale = configuration.locale;
            } catch (Throwable th) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "get locale e is " + th);
                }
                UMCrashManager.reportCrash(context, th);
                return null;
            }
        } catch (Exception e) {
            try {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "fail to read user config locale, e is " + e);
                }
                locale = null;
            } catch (Exception e2) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "get locale e is " + e2);
                }
                UMCrashManager.reportCrash(context, e2);
                return null;
            }
        }
        return locale == null ? Locale.getDefault() : locale;
    }

    public static String getMac(Context context) {
        if (context == null) {
            return null;
        }
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager == null) {
                return null;
            }
            if (checkPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
                return wifiManager.getConnectionInfo().getMacAddress();
            }
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
                return "";
            }
            return "";
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get mac e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get mac e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static String getOperator(Context context) {
        if (context == null) {
            return "Unknown";
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Config.PHONE);
            return telephonyManager == null ? "Unknown" : telephonyManager.getNetworkOperator();
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get get operator e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return "Unknown";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get get operator e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return "Unknown";
        }
    }

    public static String getSubOSName(Context context) {
        String str;
        if (context == null) {
            return null;
        }
        try {
            try {
                Properties buildProp = getBuildProp();
                try {
                    String property = buildProp.getProperty(KEY_MIUI_VERSION_NAME);
                    if (!TextUtils.isEmpty(property)) {
                        str = "MIUI";
                    } else if (isFlyMe()) {
                        str = "Flyme";
                    } else if (TextUtils.isEmpty(getYunOSVersion(buildProp))) {
                        return property;
                    } else {
                        str = "YunOS";
                    }
                    return str;
                } catch (Exception e) {
                    UMCrashManager.reportCrash(context, e);
                    return null;
                }
            } catch (Exception e2) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "get sub os name e is " + e2);
                }
                UMCrashManager.reportCrash(context, e2);
                return null;
            }
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get sub os name e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static String getSubOSVersion(Context context) {
        String yunOSVersion;
        if (context == null) {
            return null;
        }
        try {
            try {
                Properties buildProp = getBuildProp();
                try {
                    String property = buildProp.getProperty(KEY_MIUI_VERSION_NAME);
                    if (TextUtils.isEmpty(property)) {
                        try {
                            if (isFlyMe()) {
                                yunOSVersion = getFlymeVersion(buildProp);
                            } else {
                                yunOSVersion = getYunOSVersion(buildProp);
                            }
                            return yunOSVersion;
                        } catch (Exception unused) {
                        }
                    }
                    return property;
                } catch (Exception e) {
                    UMCrashManager.reportCrash(context, e);
                    return null;
                }
            } catch (Exception e2) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "get sub os version e is " + e2);
                }
                UMCrashManager.reportCrash(context, e2);
                return null;
            }
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get sub os version e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    private static String getYunOSVersion(Properties properties) {
        String property = properties.getProperty("ro.yunos.version");
        if (TextUtils.isEmpty(property)) {
            return null;
        }
        return property;
    }

    private static String getFlymeVersion(Properties properties) {
        try {
            String lowerCase = properties.getProperty("ro.build.display.id").toLowerCase(Locale.getDefault());
            if (lowerCase.contains("flyme os")) {
                return lowerCase.split(" ")[2];
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static Properties getBuildProp() {
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
                try {
                    properties.load(fileInputStream2);
                    fileInputStream2.close();
                } catch (IOException unused) {
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return properties;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                    throw th;
                }
            } catch (IOException unused3) {
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException unused4) {
        }
        return properties;
    }

    private static boolean isFlyMe() {
        try {
            Build.class.getMethod("hasSmartBar", new Class[0]);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String getDeviceType(Context context) {
        if (context == null) {
            return "Phone";
        }
        try {
            return (context.getResources().getConfiguration().screenLayout & 15) >= 3 ? "Tablet" : "Phone";
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get device type e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get device type e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static String getAppVersionCode(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version code e is " + e);
                return "";
            }
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version code e is " + th);
                return "";
            }
            return "";
        }
    }

    public static String getAppVersinoCode(Context context, String str) {
        if (context == null || str == null) {
            return "";
        }
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(str, 0).versionCode);
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version code e is " + e);
                return "";
            }
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version code e is " + th);
                return "";
            }
            return "";
        }
    }

    public static String getAppVersionName(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version name e is " + e);
                return "";
            }
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version name e is " + th);
                return "";
            }
            return "";
        }
    }

    public static String getAppVersionName(Context context, String str) {
        if (context == null || str == null) {
            return "";
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version name e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version name e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return "";
        }
    }

    public static boolean checkPermission(Context context, String str) {
        if (context == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                if (((Integer) Class.forName("android.content.Context").getMethod("checkSelfPermission", String.class).invoke(context, str)).intValue() != 0) {
                    return false;
                }
            } catch (Exception e) {
                UMCrashManager.reportCrash(context, e);
                return false;
            }
        } else if (context.getPackageManager().checkPermission(str, context.getPackageName()) != 0) {
            return false;
        }
        return true;
    }

    private static String byte2HexFormatted(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase());
            if (i < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    public static boolean isDebug(Context context) {
        if (context == null) {
            return false;
        }
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception e) {
            UMCrashManager.reportCrash(context, e);
            return false;
        }
    }

    public static String getAppName(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.loadLabel(context.getPackageManager()).toString();
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app name e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app name e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static String MD5(String str) {
        if (str == null) {
            return null;
        }
        try {
            try {
                try {
                    byte[] bytes = str.getBytes();
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    messageDigest.reset();
                    messageDigest.update(bytes);
                    byte[] digest = messageDigest.digest();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < digest.length; i++) {
                        stringBuffer.append(String.format("%02X", Byte.valueOf(digest[i])));
                    }
                    return stringBuffer.toString();
                } catch (Exception e) {
                    if (AnalyticsConstants.UM_DEBUG) {
                        Log.e(TAG, "MD5 e is " + e);
                    }
                    return null;
                }
            } catch (Throwable th) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "MD5 e is " + th);
                }
                return null;
            }
        } catch (Exception unused) {
            return str.replaceAll("[^[a-z][A-Z][0-9][.][_]]", "");
        }
    }

    public static String getFileMD5(File file) {
        try {
            try {
                byte[] bArr = new byte[1024];
                try {
                    if (!file.isFile()) {
                        return "";
                    }
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    FileInputStream fileInputStream = new FileInputStream(file);
                    while (true) {
                        int read = fileInputStream.read(bArr, 0, 1024);
                        if (read != -1) {
                            messageDigest.update(bArr, 0, read);
                        } else {
                            fileInputStream.close();
                            return String.format("%1$032x", new BigInteger(1, messageDigest.digest()));
                        }
                    }
                } catch (Exception unused) {
                    return null;
                }
            } catch (Exception e) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "get file MD5 e is " + e);
                }
                return null;
            }
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get file MD5 e is " + th);
            }
            return null;
        }
    }

    public static String encryptBySHA1(String str) {
        try {
            try {
                byte[] bytes = str.getBytes();
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
                    messageDigest.update(bytes);
                    return bytes2Hex(messageDigest.digest());
                } catch (Exception unused) {
                    return null;
                }
            } catch (Throwable th) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "encrypt by SHA1 e is " + th);
                }
                return null;
            }
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "encrypt by SHA1 e is " + e);
            }
            return null;
        }
    }

    private static String bytes2Hex(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                str = str + "0";
            }
            str = str + hexString;
        }
        return str;
    }

    public static String getUMId(Context context) {
        if (context != null) {
            try {
                return UMEnvelopeBuild.imprintProperty(context.getApplicationContext(), "umid", null);
            } catch (Exception e) {
                UMCrashManager.reportCrash(context, e);
                return null;
            }
        }
        return null;
    }

    public static String getDeviceToken(Context context) {
        Method method;
        Object invoke;
        Method method2;
        Object invoke2;
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            try {
                Class<?> cls = Class.forName("com.umeng.message.MessageSharedPrefs");
                if (cls == null || (method = cls.getMethod("getInstance", Context.class)) == null || (invoke = method.invoke(cls, applicationContext)) == null || (method2 = cls.getMethod("getDeviceToken", new Class[0])) == null || (invoke2 = method2.invoke(invoke, new Object[0])) == null || !(invoke2 instanceof String)) {
                    return null;
                }
                return (String) invoke2;
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    public static String getAppkeyByXML(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString("UMENG_APPKEY");
                if (string != null) {
                    return string.trim();
                }
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.i(AnalyticsConstants.LOG_TAG, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.");
                    return null;
                }
                return null;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String getChannelByXML(Context context) {
        Object obj;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null || (obj = applicationInfo.metaData.get("UMENG_CHANNEL")) == null) {
                return null;
            }
            String obj2 = obj.toString();
            if (obj2 != null) {
                return obj2.trim();
            }
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.i(AnalyticsConstants.LOG_TAG, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
                return null;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean checkPath(String str) {
        try {
            return Class.forName(str) != null;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static boolean checkAndroidManifest(Context context, String str) {
        try {
            context.getApplicationContext().getPackageManager().getActivityInfo(new ComponentName(context.getApplicationContext().getPackageName(), str), 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean checkIntentFilterData(Context context, String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setData(Uri.parse(SocializeProtocolConstants.PROTOCOL_KEY_TENCENT + str + ":"));
        List<ResolveInfo> queryIntentActivities = context.getApplicationContext().getPackageManager().queryIntentActivities(intent, 64);
        if (queryIntentActivities.size() > 0) {
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                if (resolveInfo.activityInfo != null && resolveInfo.activityInfo.packageName.equals(context.getApplicationContext().getPackageName())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static boolean checkResource(Context context, String str, String str2) {
        return context.getApplicationContext().getResources().getIdentifier(str, str2, context.getApplicationContext().getPackageName()) > 0;
    }

    public static boolean checkMetaData(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = context.getApplicationContext().getPackageManager().getApplicationInfo(context.getApplicationContext().getPackageName(), 128);
            if (applicationInfo != null) {
                return applicationInfo.metaData.get(str) != null;
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }

    public static String getAppMD5Signature(Context context) {
        String appMD5Signature = DeviceConfig.getAppMD5Signature(context);
        return !TextUtils.isEmpty(appMD5Signature) ? appMD5Signature.replace(":", "").toLowerCase() : appMD5Signature;
    }

    public static String getAppSHA1Key(Context context) {
        return DeviceConfig.getAppSHA1Key(context);
    }

    public static String getAppHashKey(Context context) {
        return DeviceConfig.getAppHashKey(context);
    }

    public static int getTargetSdkVersion(Context context) {
        return context.getApplicationInfo().targetSdkVersion;
    }

    public static boolean isMainProgress(Context context) {
        try {
            String currentProcessName = UMFrUtils.getCurrentProcessName(context);
            String packageName = context.getApplicationContext().getPackageName();
            if (TextUtils.isEmpty(currentProcessName) || TextUtils.isEmpty(packageName)) {
                return false;
            }
            return currentProcessName.equals(packageName);
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isApplication(Context context) {
        try {
            String name = context.getApplicationContext().getClass().getSuperclass().getName();
            if (TextUtils.isEmpty(name)) {
                return false;
            }
            return name.equals("android.app.Application");
        } catch (Exception unused) {
            return false;
        }
    }
}