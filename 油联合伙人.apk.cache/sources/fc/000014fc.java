package com.umeng.socialize.utils;

import android.os.Bundle;
import android.util.Log;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.debug.UMRTLog;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SLog {
    private static boolean DEBUG = true;
    private static final int E = 0;
    private static final int I = 2;
    private static final String PRIVATE = "priviteSocial";
    private static final String TAG = "Social";

    public static void AQ(String str, String str2) {
        UMLog uMLog = UMConfigure.umDebugLog;
        UMLog.aq(TAG, 0, str, str2);
    }

    public static void mutlE(String... strArr) {
        UMLog uMLog = UMConfigure.umDebugLog;
        UMLog.mutlInfo(TAG, 0, strArr);
    }

    public static void mutlI(String... strArr) {
        if (UMConfigure.umDebugLog != null) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(TAG, 2, strArr);
        }
    }

    public static void E(String str) {
        if (UMConfigure.umDebugLog != null) {
            String[] split = str.split("\n");
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(TAG, 0, split);
        }
    }

    public static void I(String str) {
        if (UMConfigure.umDebugLog != null) {
            String[] split = str.split("\n");
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(TAG, 2, split);
        }
    }

    public static void TE(String str, String str2) {
        if (UMConfigure.umDebugLog != null) {
            String[] split = str2.split("\n");
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo("Social_" + str, 0, split);
        }
    }

    public static void TI(String str, String str2) {
        if (UMConfigure.umDebugLog != null) {
            String[] split = str2.split("\n");
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo("Social_" + str, 2, split);
        }
    }

    public static void debug(String str) {
        if (UMConfigure.umDebugLog == null || !DEBUG) {
            return;
        }
        UMLog uMLog = UMConfigure.umDebugLog;
        UMLog.mutlInfo(PRIVATE, 2, "[private log]  " + str);
    }

    public static void selfLog(String str) {
        Log.e(PRIVATE, str);
    }

    public static boolean isDebug() {
        if (UMConfigure.umDebugLog != null) {
            return UMConfigure.isDebugLog();
        }
        return false;
    }

    public static void JSON(JSONObject jSONObject) {
        if (UMConfigure.umDebugLog != null) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.jsonObject(TAG, jSONObject);
        }
    }

    public static void JARRY(JSONArray jSONArray) {
        if (UMConfigure.umDebugLog != null) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.jsonArry(TAG, jSONArray);
        }
    }

    public static void BUNDLE(Bundle bundle) {
        if (UMConfigure.umDebugLog != null) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.bundle(TAG, 2, bundle);
        }
    }

    public static void error(Throwable th) {
        if (UMConfigure.umDebugLog != null) {
            StackTraceElement[] stackTrace = th.getStackTrace();
            String[] strArr = new String[stackTrace.length + 2];
            strArr[0] = "错误信息如下:";
            strArr[1] = "错误类型:" + th.getMessage();
            int i = 2;
            for (StackTraceElement stackTraceElement : stackTrace) {
                strArr[i] = "        at\t " + stackTraceElement.toString();
                i++;
            }
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(TAG, 0, strArr);
        }
    }

    public static void welcome() {
        if (UMConfigure.isDebugLog()) {
            Log.e(PRIVATE, "欢迎使用友盟社会化分享业务，您已添加debug包，可以使用UM Debug模式");
        } else {
            Log.e(PRIVATE, "欢迎使用友盟社会化分享业务，您没有添加debug库，如需看log，请根据文档提示添加：https://developer.umeng.com/docs/66632/detail/66890#h2-u67E5u770Bu65E5u5FD74");
        }
    }

    public static void error(String str, Throwable th) {
        if (UMConfigure.umDebugLog == null || th == null) {
            return;
        }
        StackTraceElement[] stackTrace = th.getStackTrace();
        String[] strArr = new String[stackTrace.length + 2];
        strArr[0] = str;
        strArr[1] = "错误类型:" + th.getMessage();
        int i = 2;
        for (StackTraceElement stackTraceElement : stackTrace) {
            strArr[i] = "        at\t " + stackTraceElement.toString();
            i++;
        }
        UMLog uMLog = UMConfigure.umDebugLog;
        UMLog.mutlInfo(TAG, 0, strArr);
    }

    public static void runtimePrint(String str) {
        UMRTLog.e(PRIVATE, str);
    }
}