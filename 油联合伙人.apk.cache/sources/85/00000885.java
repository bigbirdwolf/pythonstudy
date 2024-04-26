package com.alibaba.sdk.android.httpdns;

import android.util.Log;
import java.util.concurrent.ThreadFactory;

/* loaded from: classes.dex */
public class g implements ThreadFactory {
    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setUncaughtExceptionHandler(new h());
        Log.i("HttpDnsSDK", "HttpDnsThreadFactory create a new thread, name: " + thread.getName());
        return thread;
    }
}