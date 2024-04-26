package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.util.ArrayList;
import java.util.List;

/* compiled from: SessionIdManager.java */
/* loaded from: classes.dex */
public class u {
    private static volatile u c;
    private s a = new t();
    private String b;
    private List<a> d;
    private String e;

    /* compiled from: SessionIdManager.java */
    /* loaded from: classes.dex */
    public interface a {
        void a(String str, long j, long j2);

        void a(String str, String str2, long j, long j2);
    }

    private u() {
    }

    public static u a() {
        if (c == null) {
            synchronized (u.class) {
                if (c == null) {
                    c = new u();
                }
            }
        }
        return c;
    }

    public void a(long j) {
        this.a.a(j);
    }

    public long b() {
        return this.a.a();
    }

    public String a(Context context) {
        Context appContext = UMGlobalContext.getAppContext(context);
        if (appContext == null) {
            return "";
        }
        String str = "";
        try {
            synchronized (u.class) {
                try {
                    String string = PreferenceWrapper.getDefault(appContext).getString(q.d, "");
                    try {
                        return string;
                    } catch (Throwable th) {
                        str = string;
                        th = th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            throw th;
        } catch (Exception unused) {
            return str;
        }
    }

    public synchronized String b(Context context) {
        Context appContext = UMGlobalContext.getAppContext(context);
        if (appContext == null) {
            return "";
        }
        this.b = d(appContext);
        if (e(appContext)) {
            try {
                this.b = f(appContext);
            } catch (Exception unused) {
            }
        }
        return this.b;
    }

    public String c(Context context) {
        Context appContext = UMGlobalContext.getAppContext(context);
        if (appContext == null) {
            return "";
        }
        try {
            this.b = f(appContext);
        } catch (Exception unused) {
        }
        return this.b;
    }

    public String d(Context context) {
        if (TextUtils.isEmpty(this.b)) {
            try {
                this.b = PreferenceWrapper.getDefault(context).getString(q.c, null);
            } catch (Exception unused) {
            }
        }
        return this.b;
    }

    public String a(Context context, long j) {
        if (TextUtils.isEmpty(this.e)) {
            String str = "SUB" + j;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(String.format("%0" + (32 - str.length()) + com.umeng.commonsdk.proguard.e.am, 0));
            this.e = sb.toString();
        }
        return this.e;
    }

    public boolean e(Context context) {
        if (TextUtils.isEmpty(this.b)) {
            this.b = d(context);
        }
        return TextUtils.isEmpty(this.b) || j(context) || g(context);
    }

    private String f(Context context) {
        try {
            SharedPreferences.Editor edit = PreferenceWrapper.getDefault(context).edit();
            edit.putString(q.d, d(context));
            edit.commit();
        } catch (Exception unused) {
        }
        long h = h(context);
        long i = i(context);
        String str = this.b;
        a(i, h, str, false);
        this.b = this.a.a(context);
        a(i, h, str, true);
        this.a.a(context, this.b);
        return this.b;
    }

    private boolean g(Context context) {
        return !TextUtils.isEmpty(this.b) && g.a(context).a(this.b) > 0;
    }

    private long a(Context context, String str) {
        long j;
        try {
            j = PreferenceWrapper.getDefault(context).getLong(str, 0L);
        } catch (Exception unused) {
            j = 0;
        }
        return j <= 0 ? System.currentTimeMillis() : j;
    }

    private long h(Context context) {
        return a(context, q.f);
    }

    private long i(Context context) {
        return a(context, q.a);
    }

    private void a(long j, long j2, String str, boolean z) {
        if (this.d != null) {
            for (a aVar : this.d) {
                if (z) {
                    try {
                        aVar.a(str, this.b, j, j2);
                    } catch (Exception unused) {
                    }
                } else {
                    aVar.a(this.b, j, j2);
                }
            }
        }
    }

    private boolean j(Context context) {
        try {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(UMGlobalContext.getAppContext(context));
            long j = sharedPreferences.getLong(q.e, 0L);
            long j2 = sharedPreferences.getLong(q.f, 0L);
            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> interval of last session is: " + (j2 - j));
            return this.a.a(j, j2);
        } catch (Exception unused) {
            return false;
        }
    }

    public void a(a aVar) {
        if (aVar == null) {
            return;
        }
        if (this.d == null) {
            this.d = new ArrayList();
        }
        if (this.d.contains(aVar)) {
            return;
        }
        this.d.add(aVar);
    }

    public void b(a aVar) {
        if (aVar == null || this.d == null || this.d.size() == 0) {
            return;
        }
        this.d.remove(aVar);
    }
}