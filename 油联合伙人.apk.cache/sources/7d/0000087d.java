package com.alibaba.sdk.android.httpdns;

import android.content.Context;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class HttpDns implements HttpDnsService {
    private boolean isExpiredIPEnabled = false;
    private static ExecutorService pool = Executors.newFixedThreadPool(3, new g());
    private static a hostManager = a.a();
    private static DegradationFilter degradationFilter = null;
    static HttpDns instance = null;

    private HttpDns() {
    }

    public static HttpDnsService getService(Context context, String str) {
        if (instance == null) {
            synchronized (HttpDns.class) {
                if (instance == null) {
                    if (!f.b()) {
                        Thread thread = new Thread(new c(context));
                        thread.setUncaughtExceptionHandler(new h());
                        thread.start();
                    }
                    j.setContext(context);
                    l.setContext(context);
                    d.c(str);
                    instance = new HttpDns();
                }
            }
        }
        return instance;
    }

    @Override // com.alibaba.sdk.android.httpdns.HttpDnsService
    public String getIpByHost(String str) {
        String[] ipsByHost = getIpsByHost(str);
        if (ipsByHost.length > 0) {
            return ipsByHost[0];
        }
        return null;
    }

    @Override // com.alibaba.sdk.android.httpdns.HttpDnsService
    public String getIpByHostAsync(String str) {
        String[] ipsByHostAsync = getIpsByHostAsync(str);
        if (ipsByHostAsync.length > 0) {
            return ipsByHostAsync[0];
        }
        return null;
    }

    @Override // com.alibaba.sdk.android.httpdns.HttpDnsService
    public String[] getIpsByHost(String str) {
        if (i.m13b(str)) {
            if (i.c(str)) {
                return new String[]{str};
            }
            if (degradationFilter == null || !degradationFilter.shouldDegradeHttpDNS(str)) {
                b a = hostManager.a(str);
                if (a != null && a.m11a() && this.isExpiredIPEnabled) {
                    if (!hostManager.m10a(str)) {
                        e.d("refresh host async: " + str);
                        pool.submit(new l(str));
                    }
                    return a.m12a();
                } else if (a == null || a.m11a()) {
                    e.d("refresh host sync: " + str);
                    try {
                        return (String[]) pool.submit(new l(str)).get();
                    } catch (Exception e) {
                        e.a(e);
                        return d.f4b;
                    }
                } else {
                    return a.m12a();
                }
            }
            return d.f4b;
        }
        return d.f4b;
    }

    @Override // com.alibaba.sdk.android.httpdns.HttpDnsService
    public String[] getIpsByHostAsync(String str) {
        if (i.m13b(str)) {
            if (i.c(str)) {
                return new String[]{str};
            }
            if (degradationFilter == null || !degradationFilter.shouldDegradeHttpDNS(str)) {
                b a = hostManager.a(str);
                if ((a == null || a.m11a()) && !hostManager.m10a(str)) {
                    e.d("refresh host async: " + str);
                    pool.submit(new l(str));
                }
                return (a == null || (a.m11a() && !(a.m11a() && this.isExpiredIPEnabled))) ? d.f4b : a.m12a();
            }
            return d.f4b;
        }
        return d.f4b;
    }

    @Override // com.alibaba.sdk.android.httpdns.HttpDnsService
    public void setDegradationFilter(DegradationFilter degradationFilter2) {
        degradationFilter = degradationFilter2;
    }

    @Override // com.alibaba.sdk.android.httpdns.HttpDnsService
    public void setExpiredIPEnabled(boolean z) {
        this.isExpiredIPEnabled = z;
    }

    @Override // com.alibaba.sdk.android.httpdns.HttpDnsService
    public void setLogEnabled(boolean z) {
        e.setLogEnabled(z);
    }

    @Override // com.alibaba.sdk.android.httpdns.HttpDnsService
    public void setPreResolveAfterNetworkChanged(boolean z) {
        j.f6c = z;
    }

    @Override // com.alibaba.sdk.android.httpdns.HttpDnsService
    public void setPreResolveHosts(ArrayList arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            String str = (String) arrayList.get(i);
            if (!hostManager.m10a(str)) {
                pool.submit(new l(str));
            }
        }
    }
}