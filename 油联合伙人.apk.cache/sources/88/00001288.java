package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.h;
import com.umeng.commonsdk.debug.UMLog;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MobclickAgent {

    /* loaded from: classes.dex */
    public enum PageMode {
        AUTO,
        MANUAL,
        LEGACY_AUTO,
        LEGACY_MANUAL
    }

    public static void enableEncrypt(boolean z) {
    }

    public static void setCheckDevice(boolean z) {
    }

    public static void setDebugMode(boolean z) {
    }

    public static void setLatencyWindow(long j) {
    }

    public static void setScenarioType(Context context, EScenarioType eScenarioType) {
    }

    private static void init(Context context) {
        b.a().a(context);
    }

    public static void setLocation(double d, double d2) {
        b.a().a(d, d2);
    }

    public static void setCatchUncaughtExceptions(boolean z) {
        b.a().a(z);
    }

    public static void setSecret(Context context, String str) {
        b.a().c(context, str);
    }

    public static void setSessionContinueMillis(long j) {
        if (j <= com.umeng.commonsdk.proguard.c.d) {
            j = 30000;
        }
        b.a().a(j);
    }

    public static b getAgent() {
        return b.a();
    }

    public static void setOpenGLContext(GL10 gl10) {
        b.a().a(gl10);
    }

    public static void setPageCollectionMode(PageMode pageMode) {
        b.a().a(pageMode);
    }

    public static void onPageStart(String str) {
        if (!TextUtils.isEmpty(str)) {
            b.a().a(str);
        } else {
            UMLog.aq(h.C, 0, "\\|");
        }
    }

    public static void onPageEnd(String str) {
        if (!TextUtils.isEmpty(str)) {
            b.a().b(str);
        } else {
            UMLog.aq(h.D, 0, "\\|");
        }
    }

    public static void onPause(Context context) {
        b.a().c(context);
    }

    public static void onResume(Context context) {
        if (context == null) {
            UMLog.aq(h.n, 0, "\\|");
        } else {
            b.a().b(context);
        }
    }

    public static void reportError(Context context, String str) {
        b.a().a(context, str);
    }

    public static void reportError(Context context, Throwable th) {
        b.a().a(context, th);
    }

    public static void onEvent(Context context, String str) {
        b.a().a(context, str, null, -1L, 1);
    }

    public static void onEvent(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            UMLog.aq(h.k, 0, "\\|");
        } else {
            b.a().a(context, str, str2, -1L, 1);
        }
    }

    public static void onEvent(Context context, String str, Map<String, String> map) {
        if (map == null) {
            UMLog.aq(h.a, 0, "\\|");
        } else {
            b.a().a(context, str, new HashMap(map), -1L);
        }
    }

    public static void onEventObject(Context context, String str, Map<String, Object> map) {
        if (map == null) {
            UMLog.aq(h.a, 0, "\\|");
        } else {
            b.a().a(context, str, map, -1L);
        }
    }

    public static void onEventValue(Context context, String str, Map<String, String> map, int i) {
        HashMap hashMap;
        if (map == null) {
            hashMap = new HashMap();
        } else {
            hashMap = new HashMap(map);
        }
        hashMap.put("__ct__", Integer.valueOf(i));
        b.a().a(context, str, hashMap, -1L);
    }

    public static void onKillProcess(Context context) {
        b.a().d(context);
    }

    public static void onDeepLinkReceived(Context context, String str) {
        b.a().b(context, str);
    }

    public static void onProfileSignIn(String str) {
        onProfileSignIn("_adhoc", str);
    }

    public static void onProfileSignIn(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            UMLog.aq(h.t, 0, "\\|");
        } else if (str2.length() > 64) {
            UMLog.aq(h.u, 0, "\\|");
        } else if (TextUtils.isEmpty(str)) {
            b.a().a("_adhoc", str2);
        } else if (str.length() > 32) {
            UMLog.aq(h.v, 0, "\\|");
        } else {
            b.a().a(str, str2);
        }
    }

    public static void onProfileSignOff() {
        b.a().j();
    }

    /* loaded from: classes.dex */
    public enum EScenarioType {
        E_UM_NORMAL(0),
        E_UM_GAME(1);
        
        private int a;

        EScenarioType(int i) {
            this.a = i;
        }

        public int toValue() {
            return this.a;
        }
    }

    public static void setFirstLaunchEvent(Context context, List<String> list) {
        getAgent().a(context, list);
    }

    public static void registerPreProperties(Context context, JSONObject jSONObject) {
        getAgent().a(context, jSONObject);
    }

    public static void unregisterPreProperty(Context context, String str) {
        getAgent().f(context, str);
    }

    public static void clearPreProperties(Context context) {
        getAgent().g(context);
    }

    public static JSONObject getPreProperties(Context context) {
        return getAgent().h(context);
    }

    private static void disableExceptionCatch() {
        b.a().a(false);
        AnalyticsConfig.CHANGE_CATCH_EXCEPTION_NOTALLOW = true;
    }

    private static void setGameScenarioType(Context context) {
        b.a().a(context, EScenarioType.E_UM_GAME);
    }
}