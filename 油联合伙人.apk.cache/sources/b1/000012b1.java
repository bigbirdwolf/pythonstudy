package com.umeng.analytics.pro;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.pro.c;
import com.umeng.analytics.pro.g;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: AutoViewPageTracker.java */
/* loaded from: classes.dex */
public class j {
    public static String a;
    private static JSONArray e = new JSONArray();
    private static Object f = new Object();
    private Application g;
    private final Map<String, Long> d = new HashMap();
    private boolean h = false;
    boolean b = false;
    Application.ActivityLifecycleCallbacks c = new Application.ActivityLifecycleCallbacks() { // from class: com.umeng.analytics.pro.j.1
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            if (AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION != MobclickAgent.PageMode.AUTO) {
                return;
            }
            try {
                if (UMConfigure.isDebugLog()) {
                    String name = activity.getClass().getName();
                    if (name.equals(com.umeng.analytics.b.a().g())) {
                        return;
                    }
                    UMLog.aq(h.s, 0, "\\|", new String[]{"@"}, new String[]{name.substring(0, name.length() - 1)}, null, null);
                }
            } catch (Throwable unused) {
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            if (AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION == MobclickAgent.PageMode.AUTO && activity != null) {
                if (!j.this.b) {
                    j.this.a(activity);
                    com.umeng.analytics.b.a().h();
                    return;
                }
                j.this.b = false;
                if (!TextUtils.isEmpty(j.a)) {
                    String str = j.a;
                    if (str.equals(activity.getPackageName() + "." + activity.getLocalClassName())) {
                        return;
                    }
                    j.this.a(activity);
                    com.umeng.analytics.b.a().h();
                    return;
                }
                j.a = activity.getPackageName() + "." + activity.getLocalClassName();
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            if (AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION != MobclickAgent.PageMode.AUTO) {
                return;
            }
            j.this.b(activity);
            com.umeng.analytics.b.a().i();
            j.this.b = false;
            try {
                if (UMConfigure.isDebugLog()) {
                    String name = activity.getClass().getName();
                    if (name.equals(com.umeng.analytics.b.a().f())) {
                        return;
                    }
                    UMLog.aq(h.r, 0, "\\|", new String[]{"@"}, new String[]{name}, null, null);
                }
            } catch (Throwable unused) {
            }
        }
    };

    public boolean a() {
        return this.h;
    }

    public j(Context context) {
        this.g = null;
        synchronized (this) {
            if (this.g == null && context != null) {
                if (context instanceof Activity) {
                    this.g = ((Activity) context).getApplication();
                } else if (context instanceof Application) {
                    this.g = (Application) context;
                }
                if (this.g != null) {
                    b(context);
                }
            }
        }
    }

    private void b(Context context) {
        if (this.h) {
            return;
        }
        this.h = true;
        if (this.g == null || Build.VERSION.SDK_INT < 14) {
            return;
        }
        this.g.registerActivityLifecycleCallbacks(this.c);
    }

    public void b() {
        this.h = false;
        if (this.g != null) {
            if (Build.VERSION.SDK_INT >= 14) {
                this.g.unregisterActivityLifecycleCallbacks(this.c);
            }
            this.g = null;
        }
    }

    public void c() {
        b((Activity) null);
        b();
    }

    public static void a(Context context) {
        String jSONArray;
        if (context != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                synchronized (f) {
                    jSONArray = e.toString();
                    e = new JSONArray();
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put(c.d.a.c, new JSONArray(jSONArray));
                    g.a(context).a(q.a().c(), jSONObject, g.a.AUTOPAGE);
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Activity activity) {
        a = activity.getPackageName() + "." + activity.getLocalClassName();
        synchronized (this.d) {
            this.d.put(a, Long.valueOf(System.currentTimeMillis()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Activity activity) {
        long j;
        long j2;
        try {
            synchronized (this.d) {
                if (a == null && activity != null) {
                    a = activity.getPackageName() + "." + activity.getLocalClassName();
                }
                j = 0;
                if (TextUtils.isEmpty(a) || !this.d.containsKey(a)) {
                    j2 = 0;
                } else {
                    long longValue = this.d.get(a).longValue();
                    this.d.remove(a);
                    j = System.currentTimeMillis() - longValue;
                    j2 = longValue;
                }
            }
            synchronized (f) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(b.u, a);
                    jSONObject.put("duration", j);
                    jSONObject.put(b.w, j2);
                    jSONObject.put("type", 0);
                    e.put(jSONObject);
                } catch (Throwable unused) {
                }
            }
        } catch (Throwable unused2) {
        }
    }
}