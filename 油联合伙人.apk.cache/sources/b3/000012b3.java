package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.alipay.sdk.app.AlipayResultActivity;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.CoreProtocol;
import com.umeng.analytics.process.UMProcessDBHelper;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ReportPolicy;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.statistics.internal.StatTracer;
import com.umeng.commonsdk.statistics.noise.ABTest;
import com.umeng.commonsdk.statistics.noise.Defcon;
import com.umeng.commonsdk.utils.JSONArraySortUtil;
import com.umeng.commonsdk.utils.UMUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: CoreProtocolImpl.java */
/* loaded from: classes.dex */
public class k {
    private static Context a = null;
    private static final String l = "first_activate_time";
    private static final String m = "ana_is_f";
    private static final String n = "thtstart";
    private static final String o = "dstk_last_time";
    private static final String p = "dstk_cnt";
    private static final String q = "gkvc";
    private static final String r = "ekvc";
    private static final String t = "-1";
    private c b;
    private SharedPreferences c;
    private String d;
    private String e;
    private int f;
    private JSONArray g;
    private final int h;
    private int i;
    private int j;
    private long k;
    private final long s;
    private boolean u;
    private boolean v;
    private Object w;

    /* compiled from: CoreProtocolImpl.java */
    /* loaded from: classes.dex */
    public static class a {
        public static final int a = 4097;
        public static final int b = 4098;
        public static final int c = 4099;
        public static final int d = 4100;
        public static final int e = 4101;
        public static final int f = 4102;
        public static final int g = 4103;
        public static final int h = 4104;
        public static final int i = 4105;
        public static final int j = 4106;
        public static final int k = 4352;
        public static final int l = 4353;
        public static final int m = 4354;
        public static final int n = 8193;
        public static final int o = 8194;
        public static final int p = 8195;
        public static final int q = 8196;
        public static final int r = 8197;
        public static final int s = 8198;
        public static final int t = 8199;
        public static final int u = 8200;
        public static final int v = 8201;
        public static final int w = 8202;
    }

    public void b() {
    }

    private k() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = 10;
        this.g = new JSONArray();
        this.h = AlipayResultActivity.b;
        this.i = 0;
        this.j = 0;
        this.k = 0L;
        this.s = 28800000L;
        this.u = false;
        this.v = false;
        this.w = new Object();
        try {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(a);
            this.k = sharedPreferences.getLong(n, 0L);
            this.i = sharedPreferences.getInt(q, 0);
            this.j = sharedPreferences.getInt(r, 0);
            this.b = new c();
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CoreProtocolImpl.java */
    /* loaded from: classes.dex */
    public static class b {
        private static final k a = new k();

        private b() {
        }
    }

    public static k a(Context context) {
        if (a == null && context != null) {
            a = context.getApplicationContext();
        }
        return b.a;
    }

    public void a() {
        if (a != null) {
            synchronized (this.w) {
                if (this.u) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> network is now available, rebuild instant session data packet.");
                    UMWorkDispatch.sendEvent(a, a.l, CoreProtocol.getInstance(a), null);
                }
            }
            synchronized (this.w) {
                if (this.v) {
                    UMWorkDispatch.sendEvent(a, a.m, CoreProtocol.getInstance(a), null);
                }
            }
        }
    }

    public void c() {
        b(a);
        d();
        a(true);
    }

    public void a(Object obj, int i) {
        try {
            switch (i) {
                case 4097:
                    if (UMGlobalContext.getInstance().isMainProcess(a)) {
                        if (obj != null) {
                            e(obj);
                        }
                        if (t.equals(((JSONObject) obj).optString("__i"))) {
                            return;
                        }
                        a(false);
                        return;
                    }
                    UMProcessDBHelper.getInstance(a).insertEventsInSubProcess(UMFrUtils.getSubProcessName(a), new JSONArray().put(obj));
                    return;
                case 4098:
                    if (obj != null) {
                        e(obj);
                    }
                    if (t.equals(((JSONObject) obj).optString("__i"))) {
                        return;
                    }
                    a(false);
                    return;
                case 4099:
                    r.a(a);
                    return;
                case a.d /* 4100 */:
                    j.a(a);
                    return;
                case a.e /* 4101 */:
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> PROFILE_SIGNIN");
                    g(obj);
                    return;
                case a.f /* 4102 */:
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> PROFILE_SIGNOFF");
                    f(obj);
                    return;
                case a.g /* 4103 */:
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> START_SESSION");
                    q.a().a(a, obj);
                    synchronized (this.w) {
                        this.v = true;
                    }
                    return;
                case a.h /* 4104 */:
                    q.a().c(a, obj);
                    return;
                case a.i /* 4105 */:
                    d();
                    return;
                case a.j /* 4106 */:
                    h(obj);
                    return;
                default:
                    switch (i) {
                        case a.k /* 4352 */:
                            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> INSTANT_SESSION_START");
                            q.a().b(a, obj);
                            synchronized (this.w) {
                                this.u = true;
                            }
                            return;
                        case a.l /* 4353 */:
                            a(obj, true);
                            return;
                        case a.m /* 4354 */:
                            c();
                            return;
                        default:
                            switch (i) {
                                case a.p /* 8195 */:
                                    com.umeng.analytics.b.a().a(obj);
                                    return;
                                case a.q /* 8196 */:
                                    com.umeng.analytics.b.a().m();
                                    return;
                                case a.r /* 8197 */:
                                    com.umeng.analytics.b.a().k();
                                    return;
                                case a.s /* 8198 */:
                                    if (TextUtils.isEmpty(q.a().b())) {
                                        return;
                                    }
                                    i();
                                    return;
                                case a.t /* 8199 */:
                                case a.u /* 8200 */:
                                    com.umeng.analytics.b.a().b(obj);
                                    return;
                                case a.v /* 8201 */:
                                    com.umeng.analytics.b.a().b((Object) null);
                                    return;
                                case a.w /* 8202 */:
                                    h();
                                    return;
                                default:
                                    return;
                            }
                    }
            }
        } catch (Throwable unused) {
        }
    }

    private void h() {
        try {
            Class.forName("com.umeng.analytics.vismode.event.VisualHelper").getMethod("loadNativeData", Context.class).invoke(null, a);
        } catch (Exception unused) {
        }
    }

    private void i() {
        try {
            Class.forName("com.umeng.analytics.vismode.event.VisualHelper").getMethod("processCommond", Context.class, String.class).invoke(null, a, AnalyticsConfig.getAppkey(a));
        } catch (Exception unused) {
        }
    }

    public void a(boolean z) {
        if (c(z)) {
            if (!(this.b.c() instanceof ReportPolicy.ReportQuasiRealtime)) {
                if (UMEnvelopeBuild.isReadyBuild(a, UMLogDataProtocol.UMBusinessType.U_APP)) {
                    k();
                }
            } else if (z) {
                if (UMEnvelopeBuild.isOnline(a)) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> send session start in policy ReportQuasiRealtime.");
                    k();
                }
            } else if (UMEnvelopeBuild.isReadyBuild(a, UMLogDataProtocol.UMBusinessType.U_APP)) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> send normal data in policy ReportQuasiRealtime.");
                k();
            }
        }
    }

    private void j() {
        JSONObject b2 = b(UMEnvelopeBuild.maxDataSpace(a));
        if (b2 == null || b2.length() < 1) {
            return;
        }
        JSONObject jSONObject = (JSONObject) b2.opt("header");
        JSONObject jSONObject2 = (JSONObject) b2.opt("content");
        if (a == null || jSONObject == null || jSONObject2 == null) {
            return;
        }
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> constructInstantMessage: request build envelope.");
        JSONObject buildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(a, jSONObject, jSONObject2);
        if (buildEnvelopeWithExtHeader != null) {
            try {
                if (buildEnvelopeWithExtHeader.has("exception")) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "Build envelope error code: " + buildEnvelopeWithExtHeader.getInt("exception"));
                }
            } catch (Throwable unused) {
            }
            b((Object) buildEnvelopeWithExtHeader);
        }
    }

    private void k() {
        JSONObject buildEnvelopeWithExtHeader;
        JSONObject a2 = a(UMEnvelopeBuild.maxDataSpace(a));
        if (a2 == null || a2.length() < 1) {
            return;
        }
        JSONObject jSONObject = (JSONObject) a2.opt("header");
        JSONObject jSONObject2 = (JSONObject) a2.opt("content");
        if (a == null || jSONObject == null || jSONObject2 == null || (buildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(a, jSONObject, jSONObject2)) == null) {
            return;
        }
        try {
            if (buildEnvelopeWithExtHeader.has("exception")) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "Build envelope error code: " + buildEnvelopeWithExtHeader.getInt("exception"));
            }
        } catch (Throwable unused) {
        }
        c(buildEnvelopeWithExtHeader);
        a((Object) buildEnvelopeWithExtHeader);
    }

    public JSONObject a(long j) {
        if (TextUtils.isEmpty(u.a().d(a))) {
            return null;
        }
        JSONObject b2 = b(false);
        int a2 = n.a().a(a);
        if (b2.length() <= 0) {
            if (a2 != 3) {
                return null;
            }
        } else if (b2.length() == 1) {
            if (b2.optJSONObject(com.umeng.analytics.pro.b.K) != null && a2 != 3) {
                return null;
            }
            if (!TextUtils.isEmpty(b2.optString("userlevel")) && a2 != 3) {
                return null;
            }
        } else if (b2.length() == 2 && b2.optJSONObject(com.umeng.analytics.pro.b.K) != null && !TextUtils.isEmpty(b2.optString("userlevel")) && a2 != 3) {
            return null;
        }
        JSONObject m2 = m();
        if (m2 != null) {
            b(m2);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (a2 == 3) {
                jSONObject2.put("analytics", new JSONObject());
            } else if (b2 != null && b2.length() > 0) {
                jSONObject2.put("analytics", b2);
            }
            if (m2 != null && m2.length() > 0) {
                jSONObject.put("header", m2);
            }
            if (jSONObject2.length() > 0) {
                jSONObject.put("content", jSONObject2);
            }
            return a(jSONObject, j);
        } catch (Throwable unused) {
            return jSONObject;
        }
    }

    private void a(JSONObject jSONObject) {
        JSONObject f;
        if (g.a(UMGlobalContext.getAppContext(a)).c() || (f = g.a(UMGlobalContext.getAppContext(a)).f()) == null) {
            return;
        }
        String optString = f.optString("__av");
        String optString2 = f.optString("__vc");
        try {
            if (TextUtils.isEmpty(optString)) {
                jSONObject.put("app_version", UMUtils.getAppVersionName(a));
            } else {
                jSONObject.put("app_version", optString);
            }
            if (TextUtils.isEmpty(optString2)) {
                jSONObject.put("version_code", UMUtils.getAppVersionCode(a));
            } else {
                jSONObject.put("version_code", optString2);
            }
        } catch (Throwable unused) {
        }
    }

    public JSONObject b(long j) {
        if (TextUtils.isEmpty(u.a().d(UMGlobalContext.getAppContext(a)))) {
            return null;
        }
        JSONObject b2 = g.a(UMGlobalContext.getAppContext(a)).b(false);
        String[] a2 = com.umeng.analytics.c.a(a);
        if (a2 != null && !TextUtils.isEmpty(a2[0]) && !TextUtils.isEmpty(a2[1])) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(com.umeng.analytics.pro.b.L, a2[0]);
                jSONObject.put(com.umeng.analytics.pro.b.M, a2[1]);
                if (jSONObject.length() > 0) {
                    b2.put(com.umeng.analytics.pro.b.K, jSONObject);
                }
            } catch (Throwable unused) {
            }
        }
        int a3 = n.a().a(a);
        if (b2.length() != 1 || b2.optJSONObject(com.umeng.analytics.pro.b.K) == null || a3 == 3) {
            n.a().b(b2, a);
            if (b2.length() > 0 || a3 == 3) {
                JSONObject l2 = l();
                if (l2 != null) {
                    a(l2);
                }
                JSONObject jSONObject2 = new JSONObject();
                JSONObject jSONObject3 = new JSONObject();
                try {
                    if (a3 == 3) {
                        jSONObject3.put("analytics", new JSONObject());
                    } else if (b2 != null && b2.length() > 0) {
                        jSONObject3.put("analytics", b2);
                    }
                    if (l2 != null && l2.length() > 0) {
                        jSONObject2.put("header", l2);
                    }
                    if (jSONObject3.length() > 0) {
                        jSONObject2.put("content", jSONObject3);
                    }
                    return b(jSONObject2, j);
                } catch (Throwable unused2) {
                    return jSONObject2;
                }
            }
            return null;
        }
        return null;
    }

    private JSONObject a(JSONObject jSONObject, long j) {
        try {
            if (m.a(jSONObject) > j) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("header");
                jSONObject2.put(com.umeng.analytics.pro.b.ay, m.a(jSONObject));
                jSONObject.put("header", jSONObject2);
                return m.a(a, j, jSONObject);
            }
            return jSONObject;
        } catch (Throwable unused) {
            return jSONObject;
        }
    }

    private JSONObject b(JSONObject jSONObject, long j) {
        try {
            if (m.a(jSONObject) > j) {
                jSONObject = null;
                g.a(a).a(true, false);
                g.a(a).b();
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> Instant session packet overload !!! ");
                return null;
            }
            return jSONObject;
        } catch (Throwable unused) {
            return jSONObject;
        }
    }

    private JSONObject l() {
        JSONObject m2 = m();
        if (m2 != null) {
            try {
                m2.put("st", "1");
            } catch (Throwable unused) {
            }
        }
        return m2;
    }

    private void b(JSONObject jSONObject) {
        try {
            if (!g.a(a).e()) {
                JSONObject g = g.a(a).g();
                if (g != null) {
                    String optString = g.optString("__av");
                    String optString2 = g.optString("__vc");
                    if (TextUtils.isEmpty(optString)) {
                        jSONObject.put("app_version", UMUtils.getAppVersionName(a));
                    } else {
                        jSONObject.put("app_version", optString);
                    }
                    if (TextUtils.isEmpty(optString2)) {
                        jSONObject.put("version_code", UMUtils.getAppVersionCode(a));
                        return;
                    } else {
                        jSONObject.put("version_code", optString2);
                        return;
                    }
                }
                return;
            }
            jSONObject.put("app_version", UMUtils.getAppVersionName(a));
            jSONObject.put("version_code", UMUtils.getAppVersionCode(a));
        } catch (Throwable unused) {
        }
    }

    private JSONObject m() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (AnalyticsConfig.mWrapperType != null && AnalyticsConfig.mWrapperVersion != null) {
                jSONObject.put("wrapper_version", AnalyticsConfig.mWrapperVersion);
                jSONObject.put("wrapper_type", AnalyticsConfig.mWrapperType);
            }
            jSONObject.put(com.umeng.analytics.pro.b.i, AnalyticsConfig.getVerticalType(a));
            jSONObject.put("sdk_version", v.a);
            String MD5 = HelperUtils.MD5(AnalyticsConfig.getSecretKey(a));
            if (!TextUtils.isEmpty(MD5)) {
                jSONObject.put("secret", MD5);
            }
            String imprintProperty = UMEnvelopeBuild.imprintProperty(a, "pr_ve", null);
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(a);
            String imprintProperty2 = UMEnvelopeBuild.imprintProperty(a, com.umeng.analytics.pro.b.ak, "");
            if (!TextUtils.isEmpty(imprintProperty2)) {
                if (AnalyticsConfig.CLEAR_EKV_BL) {
                    jSONObject.put(com.umeng.analytics.pro.b.am, "");
                } else {
                    jSONObject.put(com.umeng.analytics.pro.b.am, imprintProperty2);
                }
            }
            String imprintProperty3 = UMEnvelopeBuild.imprintProperty(a, com.umeng.analytics.pro.b.al, "");
            if (!TextUtils.isEmpty(imprintProperty3)) {
                if (AnalyticsConfig.CLEAR_EKV_WL) {
                    jSONObject.put(com.umeng.analytics.pro.b.an, "");
                } else {
                    jSONObject.put(com.umeng.analytics.pro.b.an, imprintProperty3);
                }
            }
            jSONObject.put(com.umeng.analytics.pro.b.ae, "1.0.0");
            if (t()) {
                jSONObject.put(com.umeng.analytics.pro.b.ag, "1");
                if (sharedPreferences != null) {
                    sharedPreferences.edit().putLong(m, 0L).commit();
                }
            }
            jSONObject.put(com.umeng.analytics.pro.b.l, n());
            jSONObject.put(com.umeng.analytics.pro.b.m, o());
            if (sharedPreferences != null) {
                String string = sharedPreferences.getString("vers_name", "");
                if (!TextUtils.isEmpty(string)) {
                    String format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                    if (TextUtils.isEmpty(imprintProperty)) {
                        jSONObject.put(com.umeng.analytics.pro.b.l, sharedPreferences.getString("vers_pre_version", "0"));
                        jSONObject.put(com.umeng.analytics.pro.b.m, sharedPreferences.getString("vers_date", format));
                    }
                    sharedPreferences.edit().putString("pre_version", string).putString("cur_version", DeviceConfig.getAppVersionName(a)).putString("pre_date", format).remove("vers_name").remove("vers_code").remove("vers_date").remove("vers_pre_version").commit();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    public JSONObject b(boolean z) {
        try {
            JSONObject a2 = g.a(a).a(z);
            try {
                if (a2 == null) {
                    a2 = new JSONObject();
                } else {
                    try {
                        if (a2.has(com.umeng.analytics.pro.b.n)) {
                            JSONArray jSONArray = a2.getJSONArray(com.umeng.analytics.pro.b.n);
                            JSONArray jSONArray2 = new JSONArray();
                            for (int i = 0; i < jSONArray.length(); i++) {
                                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                                JSONArray optJSONArray = jSONObject.optJSONArray(com.umeng.analytics.pro.b.s);
                                JSONArray optJSONArray2 = jSONObject.optJSONArray(com.umeng.analytics.pro.b.t);
                                if (optJSONArray == null && optJSONArray2 != null) {
                                    jSONObject.put(com.umeng.analytics.pro.b.s, optJSONArray2);
                                    jSONObject.remove(com.umeng.analytics.pro.b.t);
                                }
                                if (optJSONArray != null && optJSONArray2 != null) {
                                    ArrayList<JSONObject> arrayList = new ArrayList();
                                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                        arrayList.add((JSONObject) optJSONArray.get(i2));
                                    }
                                    for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                                        arrayList.add((JSONObject) optJSONArray2.get(i3));
                                    }
                                    JSONArraySortUtil jSONArraySortUtil = new JSONArraySortUtil();
                                    jSONArraySortUtil.setCompareKey(com.umeng.analytics.pro.b.w);
                                    Collections.sort(arrayList, jSONArraySortUtil);
                                    JSONArray jSONArray3 = new JSONArray();
                                    for (JSONObject jSONObject2 : arrayList) {
                                        jSONArray3.put(jSONObject2);
                                    }
                                    jSONObject.put(com.umeng.analytics.pro.b.s, jSONArray3);
                                    jSONObject.remove(com.umeng.analytics.pro.b.t);
                                }
                                if (jSONObject.has(com.umeng.analytics.pro.b.s)) {
                                    JSONArray optJSONArray3 = jSONObject.optJSONArray(com.umeng.analytics.pro.b.s);
                                    for (int i4 = 0; i4 < optJSONArray3.length(); i4++) {
                                        JSONObject jSONObject3 = optJSONArray3.getJSONObject(i4);
                                        if (jSONObject3.has(com.umeng.analytics.pro.b.w)) {
                                            jSONObject3.put("ts", jSONObject3.getLong(com.umeng.analytics.pro.b.w));
                                            jSONObject3.remove(com.umeng.analytics.pro.b.w);
                                        }
                                    }
                                    jSONObject.put(com.umeng.analytics.pro.b.s, optJSONArray3);
                                    jSONObject.put(com.umeng.analytics.pro.b.y, optJSONArray3.length());
                                } else {
                                    jSONObject.put(com.umeng.analytics.pro.b.y, 0);
                                }
                                jSONArray2.put(jSONObject);
                            }
                            a2.put(com.umeng.analytics.pro.b.n, jSONArray2);
                        }
                    } catch (Exception e) {
                        MLog.e("merge pages error");
                        e.printStackTrace();
                    }
                }
                SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(a);
                if (sharedPreferences != null) {
                    String string = sharedPreferences.getString("userlevel", "");
                    if (!TextUtils.isEmpty(string)) {
                        a2.put("userlevel", string);
                    }
                }
                String[] a3 = com.umeng.analytics.c.a(a);
                if (a3 != null && !TextUtils.isEmpty(a3[0]) && !TextUtils.isEmpty(a3[1])) {
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put(com.umeng.analytics.pro.b.L, a3[0]);
                    jSONObject4.put(com.umeng.analytics.pro.b.M, a3[1]);
                    if (jSONObject4.length() > 0) {
                        a2.put(com.umeng.analytics.pro.b.K, jSONObject4);
                    }
                }
                if (ABTest.getService(a).isInTest()) {
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put(ABTest.getService(a).getTestName(), ABTest.getService(a).getGroupInfo());
                    a2.put(com.umeng.analytics.pro.b.J, jSONObject5);
                }
                n.a().a(a2, a);
                return a2;
            } catch (Throwable unused) {
                return a2;
            }
        } catch (Throwable unused2) {
            return null;
        }
    }

    private String n() {
        String imprintProperty;
        String str = null;
        try {
            imprintProperty = UMEnvelopeBuild.imprintProperty(a, "pr_ve", null);
        } catch (Throwable unused) {
        }
        if (TextUtils.isEmpty(imprintProperty)) {
            if (!TextUtils.isEmpty(this.d)) {
                return this.d;
            }
            if (this.c == null) {
                this.c = PreferenceWrapper.getDefault(a);
            }
            str = this.c.getString("pre_version", "");
            String appVersionName = DeviceConfig.getAppVersionName(a);
            if (TextUtils.isEmpty(str)) {
                this.c.edit().putString("pre_version", "0").putString("cur_version", appVersionName).commit();
                str = "0";
            } else {
                String string = this.c.getString("cur_version", "");
                if (!appVersionName.equals(string)) {
                    this.c.edit().putString("pre_version", string).putString("cur_version", appVersionName).commit();
                    str = string;
                }
            }
            this.d = str;
            return str;
        }
        str = imprintProperty;
        this.d = str;
        return str;
    }

    private String o() {
        String imprintProperty;
        String str = null;
        try {
            imprintProperty = UMEnvelopeBuild.imprintProperty(a, "ud_da", null);
        } catch (Throwable unused) {
        }
        if (TextUtils.isEmpty(imprintProperty)) {
            if (!TextUtils.isEmpty(this.e)) {
                return this.e;
            }
            if (this.c == null) {
                this.c = PreferenceWrapper.getDefault(a);
            }
            str = this.c.getString("pre_date", "");
            if (TextUtils.isEmpty(str)) {
                str = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                this.c.edit().putString("pre_date", str).commit();
            } else {
                String format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                if (!str.equals(format)) {
                    this.c.edit().putString("pre_date", format).commit();
                    str = format;
                }
            }
            this.e = str;
            return str;
        }
        str = imprintProperty;
        this.e = str;
        return str;
    }

    public void d() {
        try {
            if (this.g.length() > 0) {
                g.a(a).a(this.g);
                this.g = new JSONArray();
            }
            PreferenceWrapper.getDefault(a).edit().putLong(n, this.k).putInt(q, this.i).putInt(r, this.j).commit();
        } catch (Throwable unused) {
        }
    }

    /* compiled from: CoreProtocolImpl.java */
    /* loaded from: classes.dex */
    public static class d {
        private Map<String, Object> a;
        private String b;
        private String c;
        private long d;

        private d() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = 0L;
        }

        public d(String str, Map<String, Object> map, String str2, long j) {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = 0L;
            this.a = map;
            this.b = str;
            this.d = j;
            this.c = str2;
        }

        public Map<String, Object> a() {
            return this.a;
        }

        public String b() {
            return this.c;
        }

        public String c() {
            return this.b;
        }

        public long d() {
            return this.d;
        }
    }

    private void e(Object obj) {
        try {
            JSONObject jSONObject = (JSONObject) obj;
            if (2050 == jSONObject.getInt("__t")) {
                if (!a(this.k, this.i)) {
                    return;
                }
                this.i++;
            } else if (2049 == jSONObject.getInt("__t")) {
                if (!a(this.k, this.j)) {
                    return;
                }
                this.j++;
            }
            if (this.g.length() >= this.f) {
                g.a(a).a(this.g);
                this.g = new JSONArray();
            }
            if (this.k == 0) {
                this.k = System.currentTimeMillis();
            }
            this.g.put(jSONObject);
        } catch (Throwable th) {
            MLog.e(th);
        }
    }

    private boolean a(long j, int i) {
        if (j != 0) {
            if (System.currentTimeMillis() - j <= 28800000) {
                return i < 5000;
            }
            p();
            return true;
        }
        return true;
    }

    private void p() {
        try {
            this.i = 0;
            this.j = 0;
            this.k = System.currentTimeMillis();
            PreferenceWrapper.getDefault(a).edit().putLong(o, System.currentTimeMillis()).putInt(p, 0).commit();
        } catch (Throwable unused) {
        }
    }

    private boolean c(boolean z) {
        if (t()) {
            return true;
        }
        if (this.b == null) {
            this.b = new c();
        }
        this.b.a();
        ReportPolicy.ReportStrategy c2 = this.b.c();
        MLog.d("Report policy : " + c2.getClass().getSimpleName());
        boolean shouldSendMessage = c2.shouldSendMessage(z);
        if (shouldSendMessage) {
            if (((c2 instanceof ReportPolicy.ReportByInterval) || (c2 instanceof ReportPolicy.DebugPolicy) || (c2 instanceof ReportPolicy.ReportQuasiRealtime)) && q()) {
                d();
            }
            if ((c2 instanceof ReportPolicy.DefconPolicy) && q()) {
                d();
            }
        }
        return shouldSendMessage;
    }

    private boolean q() {
        try {
            if (!TextUtils.isEmpty(q.a().b())) {
                b(a);
            }
            if (this.g.length() > 0) {
                for (int i = 0; i < this.g.length(); i++) {
                    JSONObject optJSONObject = this.g.optJSONObject(i);
                    if (optJSONObject != null && optJSONObject.length() > 0) {
                        String optString = optJSONObject.optString("__i");
                        if (TextUtils.isEmpty(optString) || t.equals(optString)) {
                            return false;
                        }
                    }
                }
                return true;
            }
            return false;
        } catch (Throwable unused) {
            return true;
        }
    }

    /* compiled from: CoreProtocolImpl.java */
    /* loaded from: classes.dex */
    public static class c {
        private ReportPolicy.ReportStrategy a = null;
        private int b = -1;
        private int c = -1;
        private int d = -1;
        private int e = -1;
        private ABTest f;

        public c() {
            this.f = null;
            this.f = ABTest.getService(k.a);
        }

        public void a() {
            try {
                int[] a = a(-1, -1);
                this.b = a[0];
                this.c = a[1];
            } catch (Throwable unused) {
            }
        }

        public int[] a(int i, int i2) {
            int intValue = Integer.valueOf(UMEnvelopeBuild.imprintProperty(k.a, "report_policy", k.t)).intValue();
            int intValue2 = Integer.valueOf(UMEnvelopeBuild.imprintProperty(k.a, "report_interval", k.t)).intValue();
            if (intValue == -1 || !ReportPolicy.isValid(intValue)) {
                return new int[]{i, i2};
            }
            if (6 == intValue) {
                int i3 = 90;
                if (intValue2 != -1 && intValue2 >= 90 && intValue2 <= 86400) {
                    i3 = intValue2;
                }
                return new int[]{intValue, i3 * 1000};
            } else if (11 == intValue) {
                int i4 = 15;
                if (intValue2 != -1 && intValue2 >= 15 && intValue2 <= 3600) {
                    i4 = intValue2;
                }
                return new int[]{intValue, i4 * 1000};
            } else {
                return new int[]{i, i2};
            }
        }

        public int a(int i) {
            int intValue = Integer.valueOf(UMEnvelopeBuild.imprintProperty(k.a, "test_report_interval", k.t)).intValue();
            return (intValue == -1 || intValue < 90 || intValue > 86400) ? i : intValue * 1000;
        }

        protected void b() {
            int i;
            ReportPolicy.ReportStrategy defconPolicy;
            Defcon service = Defcon.getService(k.a);
            if (!service.isOpen()) {
                boolean z = Integer.valueOf(UMEnvelopeBuild.imprintProperty(k.a, "integrated_test", k.t)).intValue() == 1;
                if (UMConfigure.isDebugLog() && z && !MLog.DEBUG) {
                    UMLog.mutlInfo(h.J, 3, "\\|", null, null);
                }
                if (MLog.DEBUG && z) {
                    this.a = new ReportPolicy.DebugPolicy(StatTracer.getInstance(k.a));
                } else if (this.f.isInTest() && "RPT".equals(this.f.getTestName())) {
                    if (this.f.getTestPolicy() == 6) {
                        if (Integer.valueOf(UMEnvelopeBuild.imprintProperty(k.a, "test_report_interval", k.t)).intValue() != -1) {
                            i = a(90000);
                        } else if (this.c > 0) {
                            i = this.c;
                        } else {
                            i = this.e;
                        }
                    } else {
                        i = 0;
                    }
                    this.a = b(this.f.getTestPolicy(), i);
                } else {
                    int i2 = this.d;
                    int i3 = this.e;
                    if (this.b != -1) {
                        i2 = this.b;
                        i3 = this.c;
                    }
                    this.a = b(i2, i3);
                }
            } else {
                if (!((this.a instanceof ReportPolicy.DefconPolicy) && this.a.isValid())) {
                    defconPolicy = new ReportPolicy.DefconPolicy(StatTracer.getInstance(k.a), service);
                } else {
                    defconPolicy = this.a;
                }
                this.a = defconPolicy;
            }
            if (UMConfigure.isDebugLog()) {
                try {
                    if (this.a instanceof ReportPolicy.ReportAtLaunch) {
                        UMLog.mutlInfo(h.H, 3, "", null, null);
                    } else if (this.a instanceof ReportPolicy.ReportByInterval) {
                        UMLog.mutlInfo(h.I, 3, "", new String[]{"@"}, new String[]{String.valueOf(((ReportPolicy.ReportByInterval) this.a).getReportInterval() / 1000)});
                    } else if (this.a instanceof ReportPolicy.DebugPolicy) {
                        UMLog.mutlInfo(h.K, 3, "", null, null);
                    } else if (this.a instanceof ReportPolicy.ReportQuasiRealtime) {
                        String[] strArr = {String.valueOf(((ReportPolicy.ReportQuasiRealtime) this.a).getReportInterval() / 1000)};
                        UMLog uMLog = UMConfigure.umDebugLog;
                        UMLog.mutlInfo(h.L, 3, "", new String[]{"@"}, strArr);
                    } else {
                        boolean z2 = this.a instanceof ReportPolicy.DefconPolicy;
                    }
                } catch (Throwable unused) {
                }
            }
        }

        public ReportPolicy.ReportStrategy c() {
            b();
            return this.a;
        }

        private ReportPolicy.ReportStrategy b(int i, int i2) {
            switch (i) {
                case 0:
                    return this.a instanceof ReportPolicy.ReportRealtime ? this.a : new ReportPolicy.ReportRealtime();
                case 1:
                    return this.a instanceof ReportPolicy.ReportAtLaunch ? this.a : new ReportPolicy.ReportAtLaunch();
                case 2:
                case 3:
                case 7:
                case 9:
                case 10:
                default:
                    return this.a instanceof ReportPolicy.ReportAtLaunch ? this.a : new ReportPolicy.ReportAtLaunch();
                case 4:
                    return this.a instanceof ReportPolicy.ReportDaily ? this.a : new ReportPolicy.ReportDaily(StatTracer.getInstance(k.a));
                case 5:
                    return this.a instanceof ReportPolicy.ReportWifiOnly ? this.a : new ReportPolicy.ReportWifiOnly(k.a);
                case 6:
                    if (this.a instanceof ReportPolicy.ReportByInterval) {
                        ReportPolicy.ReportStrategy reportStrategy = this.a;
                        ((ReportPolicy.ReportByInterval) reportStrategy).setReportInterval(i2);
                        return reportStrategy;
                    }
                    return new ReportPolicy.ReportByInterval(StatTracer.getInstance(k.a), i2);
                case 8:
                    return this.a instanceof ReportPolicy.SmartPolicy ? this.a : new ReportPolicy.SmartPolicy(StatTracer.getInstance(k.a));
                case 11:
                    if (this.a instanceof ReportPolicy.ReportQuasiRealtime) {
                        ReportPolicy.ReportStrategy reportStrategy2 = this.a;
                        ((ReportPolicy.ReportQuasiRealtime) reportStrategy2).setReportInterval(i2);
                        return reportStrategy2;
                    }
                    ReportPolicy.ReportQuasiRealtime reportQuasiRealtime = new ReportPolicy.ReportQuasiRealtime();
                    reportQuasiRealtime.setReportInterval(i2);
                    return reportQuasiRealtime;
            }
        }
    }

    private void c(JSONObject jSONObject) {
        JSONObject jSONObject2;
        if (jSONObject == null) {
            return;
        }
        try {
            if (jSONObject.length() <= 0) {
                return;
            }
            JSONObject jSONObject3 = new JSONObject();
            if (jSONObject.has("analytics")) {
                JSONObject jSONObject4 = jSONObject.getJSONObject("analytics");
                if (jSONObject4.has(com.umeng.analytics.pro.b.R)) {
                    jSONObject3.put(com.umeng.analytics.pro.b.R, jSONObject4.getJSONArray(com.umeng.analytics.pro.b.R));
                }
                if (jSONObject4.has(com.umeng.analytics.pro.b.S)) {
                    jSONObject3.put(com.umeng.analytics.pro.b.S, jSONObject4.getJSONArray(com.umeng.analytics.pro.b.S));
                }
                if (jSONObject4.has(com.umeng.analytics.pro.b.N)) {
                    jSONObject3.put(com.umeng.analytics.pro.b.N, jSONObject4.getJSONArray(com.umeng.analytics.pro.b.N));
                }
                if (jSONObject4.has(com.umeng.analytics.pro.b.n)) {
                    JSONArray jSONArray = jSONObject4.getJSONArray(com.umeng.analytics.pro.b.n);
                    JSONArray jSONArray2 = new JSONArray();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject5 = jSONArray.getJSONObject(i);
                        if (jSONObject5 != null && jSONObject5.length() > 0) {
                            if (jSONObject5.has(com.umeng.analytics.pro.b.t)) {
                                jSONObject5.remove(com.umeng.analytics.pro.b.t);
                            }
                            jSONArray2.put(jSONObject5);
                        }
                    }
                    jSONObject3.put(com.umeng.analytics.pro.b.n, jSONArray2);
                }
                if (jSONObject4.has(com.umeng.analytics.pro.b.H)) {
                    jSONObject3.put(com.umeng.analytics.pro.b.H, jSONObject4.getJSONObject(com.umeng.analytics.pro.b.H));
                }
                if (jSONObject4.has(com.umeng.analytics.pro.b.K)) {
                    jSONObject3.put(com.umeng.analytics.pro.b.K, jSONObject4.getJSONObject(com.umeng.analytics.pro.b.K));
                }
            }
            if (jSONObject.has("dplus")) {
                jSONObject3.put("dplus", jSONObject.getJSONObject("dplus"));
            }
            if (jSONObject.has("header") && jSONObject.has("header") && (jSONObject2 = jSONObject.getJSONObject("header")) != null && jSONObject2.length() > 0) {
                if (jSONObject2.has("sdk_version")) {
                    jSONObject3.put("sdk_version", jSONObject2.getString("sdk_version"));
                }
                if (jSONObject2.has("device_id")) {
                    jSONObject3.put("device_id", jSONObject2.getString("device_id"));
                }
                if (jSONObject2.has("device_model")) {
                    jSONObject3.put("device_model", jSONObject2.getString("device_model"));
                }
                if (jSONObject2.has("version_code")) {
                    jSONObject3.put("version", jSONObject2.getInt("version_code"));
                }
                if (jSONObject2.has("appkey")) {
                    jSONObject3.put("appkey", jSONObject2.getString("appkey"));
                }
                if (jSONObject2.has("channel")) {
                    jSONObject3.put("channel", jSONObject2.getString("channel"));
                }
            }
            if (jSONObject3.length() > 0) {
                MLog.d("constructMessage:" + jSONObject3.toString());
                UMRTLog.i(UMRTLog.RTLOG_TAG, "constructMessage: " + jSONObject3.toString());
            }
        } catch (Throwable th) {
            MLog.e(th);
        }
    }

    public void a(Object obj) {
        if (obj != null) {
            try {
                JSONObject jSONObject = (JSONObject) obj;
                if (jSONObject.length() > 0) {
                    if (jSONObject.has("exception")) {
                        if (101 != jSONObject.getInt("exception")) {
                            e(jSONObject);
                        }
                    } else {
                        e(jSONObject);
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    public void b(Object obj) {
        if (obj != null) {
            try {
                JSONObject jSONObject = (JSONObject) obj;
                if (jSONObject.length() > 0) {
                    if (jSONObject.has("exception")) {
                        if (101 != jSONObject.getInt("exception")) {
                            d(jSONObject);
                        }
                    } else {
                        d(jSONObject);
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    private void d(JSONObject jSONObject) {
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        try {
            if (jSONObject.getJSONObject("header").has(com.umeng.analytics.pro.b.ay)) {
                if (jSONObject.has("content")) {
                    jSONObject = jSONObject.getJSONObject("content");
                }
                if (jSONObject.has("analytics")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("analytics");
                    if (jSONObject2.has(com.umeng.analytics.pro.b.n) && (optJSONObject2 = jSONObject2.getJSONArray(com.umeng.analytics.pro.b.n).optJSONObject(0)) != null) {
                        String optString = optJSONObject2.optString("id");
                        if (!TextUtils.isEmpty(optString)) {
                            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> removeAllInstantData: really delete instant session data");
                            g.a(a).b(optString);
                        }
                    }
                }
                g.a(a).b();
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> removeAllInstantData: send INSTANT_SESSION_START_CONTINUE event because OVERSIZE.");
                UMWorkDispatch.sendEvent(a, a.l, CoreProtocol.getInstance(a), null);
                return;
            }
            if (jSONObject.has("content")) {
                jSONObject = jSONObject.getJSONObject("content");
            }
            if (jSONObject.has("analytics") && (optJSONObject = jSONObject.optJSONObject("analytics")) != null && optJSONObject.length() > 0 && optJSONObject.has(com.umeng.analytics.pro.b.n)) {
                g.a(a).a(true, false);
            }
            g.a(a).b();
        } catch (Exception unused) {
        }
    }

    private void e(JSONObject jSONObject) {
        JSONObject optJSONObject;
        try {
            if (jSONObject.getJSONObject("header").has(com.umeng.analytics.pro.b.ay)) {
                if (jSONObject.has("content")) {
                    jSONObject = jSONObject.getJSONObject("content");
                }
                if (jSONObject.has("analytics")) {
                    if (jSONObject.getJSONObject("analytics").has(com.umeng.analytics.pro.b.n)) {
                        g.a(a).i();
                        g.a(a).h();
                        g.a(a).b(true, false);
                        g.a(a).a();
                        return;
                    }
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> Error, Should not go to this branch.");
                    return;
                }
                return;
            }
            if (jSONObject.has("content")) {
                jSONObject = jSONObject.getJSONObject("content");
            }
            if (jSONObject.has("analytics") && (optJSONObject = jSONObject.optJSONObject("analytics")) != null && optJSONObject.length() > 0) {
                if (optJSONObject.has(com.umeng.analytics.pro.b.n)) {
                    g.a(a).b(true, false);
                }
                if (optJSONObject.has(com.umeng.analytics.pro.b.R) || optJSONObject.has(com.umeng.analytics.pro.b.S)) {
                    g.a(a).h();
                }
                if (optJSONObject.has(com.umeng.analytics.pro.b.N)) {
                    g.a(a).i();
                }
            }
            g.a(a).a();
        } catch (Exception unused) {
        }
    }

    public void c(Object obj) {
        b(a);
        d();
        if (d(false)) {
            k();
        }
    }

    public void b(Context context) {
        try {
            g.a(context).d();
            r();
        } catch (Throwable unused) {
        }
    }

    public void e() {
        if (d(false)) {
            k();
        }
    }

    public void d(Object obj) {
        s();
        n();
        o();
        a(true);
    }

    private boolean d(boolean z) {
        if (this.b == null) {
            this.b = new c();
        }
        ReportPolicy.ReportStrategy c2 = this.b.c();
        if (c2 instanceof ReportPolicy.DefconPolicy) {
            if (z) {
                return ((ReportPolicy.DefconPolicy) c2).shouldSendMessageByInstant();
            }
            return c2.shouldSendMessage(false);
        }
        return true;
    }

    public void a(Object obj, boolean z) {
        if (z) {
            if (d(true)) {
                j();
            }
        } else if (UMEnvelopeBuild.isOnline(a) && d(true)) {
            j();
        }
    }

    private void r() {
        if (this.g.length() > 0) {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.g.length(); i++) {
                try {
                    JSONObject jSONObject = this.g.getJSONObject(i);
                    if (jSONObject != null && jSONObject.length() > 0) {
                        String optString = jSONObject.optString("__i");
                        if (TextUtils.isEmpty(optString) || t.equals(optString)) {
                            String b2 = q.a().b();
                            if (TextUtils.isEmpty(b2)) {
                                b2 = t;
                            }
                            jSONObject.put("__i", b2);
                        }
                        jSONArray.put(jSONObject);
                    } else {
                        jSONArray.put(jSONObject);
                    }
                } catch (Throwable unused) {
                }
            }
            this.g = jSONArray;
        }
    }

    private void s() {
        SharedPreferences sharedPreferences;
        try {
            if (!t() || a == null || (sharedPreferences = PreferenceWrapper.getDefault(a)) == null || sharedPreferences.getLong(l, 0L) != 0) {
                return;
            }
            sharedPreferences.edit().putLong(l, System.currentTimeMillis()).commit();
        } catch (Throwable unused) {
        }
    }

    public long f() {
        SharedPreferences sharedPreferences;
        try {
            if (a == null || (sharedPreferences = PreferenceWrapper.getDefault(a)) == null) {
                return 0L;
            }
            long j = sharedPreferences.getLong(l, 0L);
            if (j == 0) {
                try {
                    long currentTimeMillis = System.currentTimeMillis();
                    sharedPreferences.edit().putLong(l, currentTimeMillis).commit();
                    return currentTimeMillis;
                } catch (Throwable unused) {
                }
            }
            return j;
        } catch (Throwable unused2) {
            return 0L;
        }
    }

    private boolean t() {
        SharedPreferences sharedPreferences;
        try {
            if (a == null || (sharedPreferences = PreferenceWrapper.getDefault(a)) == null) {
                return false;
            }
            return sharedPreferences.getLong(m, -1L) != 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    private void f(Object obj) {
        try {
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject != null && jSONObject.length() > 0) {
                long j = jSONObject.getLong("ts");
                b(a);
                d();
                String[] a2 = com.umeng.analytics.c.a(a);
                if (a2 == null || TextUtils.isEmpty(a2[0]) || TextUtils.isEmpty(a2[1])) {
                    return;
                }
                q.a().a(a, j);
                String c2 = u.a().c(a);
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> onProfileSignIn: force generate new session: session id = " + c2);
                boolean b2 = q.a().b(a, j);
                com.umeng.analytics.c.b(a);
                q.a().a(a, j, true);
                if (b2) {
                    q.a().c(a, j);
                }
            }
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(" Excepthon  in  onProfileSignOff", th);
            }
        }
    }

    private void g(Object obj) {
        try {
            b(a);
            d();
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject != null && jSONObject.length() > 0) {
                String string = jSONObject.getString(com.umeng.analytics.pro.b.L);
                String string2 = jSONObject.getString("uid");
                long j = jSONObject.getLong("ts");
                String[] a2 = com.umeng.analytics.c.a(a);
                if (a2 != null && string.equals(a2[0]) && string2.equals(a2[1])) {
                    return;
                }
                q.a().a(a, j);
                String c2 = u.a().c(a);
                boolean b2 = q.a().b(a, j);
                com.umeng.analytics.c.a(a, string, string2);
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> onProfileSignIn: force generate new session: session id = " + c2);
                q.a().a(a, j, true);
                if (b2) {
                    q.a().c(a, j);
                }
            }
        } catch (Throwable unused) {
        }
    }

    private void h(Object obj) {
        try {
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject == null || jSONObject.length() <= 0 || !jSONObject.has("__ii")) {
                return;
            }
            String optString = jSONObject.optString("__ii");
            jSONObject.remove("__ii");
            if (TextUtils.isEmpty(optString)) {
                return;
            }
            g.a(a).a(optString, obj.toString(), 2);
        } catch (Throwable unused) {
        }
    }
}