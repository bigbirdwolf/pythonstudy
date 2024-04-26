package com.alibaba.sdk.android.httpdns;

import android.util.Log;

/* loaded from: classes.dex */
class e {
    private static int a = -1;

    /* renamed from: a  reason: collision with other field name */
    private static boolean f5a = false;

    private static String a() {
        if (a == -1) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            int length = stackTrace.length;
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (stackTrace[i].getMethodName().equals("getTraceInfo")) {
                    a = i2 + 1;
                    break;
                } else {
                    i2++;
                    i++;
                }
            }
        }
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[a + 1];
        return stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + " - [" + stackTraceElement.getMethodName() + "]";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Throwable th) {
        if (!f5a || th == null) {
            return;
        }
        th.printStackTrace();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void d(String str) {
        if (!f5a || str == null) {
            return;
        }
        Log.d("HttpDnsSDK", Thread.currentThread().getId() + " - " + a() + " - " + str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void e(String str) {
        if (!f5a || str == null) {
            return;
        }
        Log.e("HttpDnsSDK", Thread.currentThread().getId() + " - " + a() + " - " + str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void setLogEnabled(boolean z) {
        synchronized (e.class) {
            f5a = z;
        }
    }
}