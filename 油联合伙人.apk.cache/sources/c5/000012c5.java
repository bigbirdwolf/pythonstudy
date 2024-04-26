package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.CoreProtocol;
import com.umeng.analytics.pro.g;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.service.UMGlobalContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ViewPageTracker.java */
/* loaded from: classes.dex */
public class r {
    private static final int b = 5;
    private static JSONArray c = new JSONArray();
    private static Object d = new Object();
    private final Map<String, Long> e = new HashMap();
    Stack<String> a = new Stack<>();

    protected int a() {
        return 2;
    }

    public static void a(Context context) {
        String jSONArray;
        if (context != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                synchronized (d) {
                    jSONArray = c.toString();
                    c = new JSONArray();
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("__a", new JSONArray(jSONArray));
                    if (jSONObject.length() > 0) {
                        g.a(context).a(q.a().c(), jSONObject, g.a.PAGE);
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (UMConfigure.isDebugLog() && this.a.size() != 0) {
            UMLog.aq(h.F, 0, "\\|", new String[]{"@"}, new String[]{this.a.peek()}, null, null);
        }
        synchronized (this.e) {
            this.e.put(str, Long.valueOf(System.currentTimeMillis()));
            if (UMConfigure.isDebugLog()) {
                this.a.push(str);
            }
        }
    }

    public void b(String str) {
        Long l;
        Context appContext;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.e.containsKey(str)) {
            synchronized (this.e) {
                l = this.e.get(str);
            }
            if (l == null) {
                return;
            }
            if (UMConfigure.isDebugLog() && this.a.size() > 0 && str.equals(this.a.peek())) {
                this.a.pop();
            }
            long currentTimeMillis = System.currentTimeMillis() - l.longValue();
            synchronized (d) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(b.u, str);
                    jSONObject.put("duration", currentTimeMillis);
                    jSONObject.put(b.w, l);
                    jSONObject.put("type", a());
                    c.put(jSONObject);
                    if (c.length() >= 5 && (appContext = UMGlobalContext.getAppContext(null)) != null) {
                        UMWorkDispatch.sendEvent(appContext, 4099, CoreProtocol.getInstance(appContext), null);
                    }
                } catch (Throwable unused) {
                }
            }
            if (!UMConfigure.isDebugLog() || this.a.size() == 0) {
                return;
            }
            UMLog.aq(h.E, 0, "\\|", new String[]{"@"}, new String[]{str}, null, null);
        } else if (UMConfigure.isDebugLog() && this.a.size() == 0) {
            UMLog.aq(h.G, 0, "\\|", new String[]{"@"}, new String[]{str}, null, null);
        }
    }

    public void b() {
        String str;
        synchronized (this.e) {
            str = null;
            long j = 0;
            for (Map.Entry<String, Long> entry : this.e.entrySet()) {
                if (entry.getValue().longValue() > j) {
                    long longValue = entry.getValue().longValue();
                    str = entry.getKey();
                    j = longValue;
                }
            }
        }
        if (str != null) {
            b(str);
        }
    }
}