package com.umeng.commonsdk.stateless;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.File;

/* compiled from: UMSLNetWorkSender.java */
/* loaded from: classes.dex */
public class d {
    public static final int a = 273;
    private static Context b = null;
    private static HandlerThread c = null;
    private static Handler d = null;
    private static final int f = 512;
    private static IntentFilter g;
    private static Object e = new Object();
    private static boolean h = false;
    private static BroadcastReceiver i = new BroadcastReceiver() { // from class: com.umeng.commonsdk.stateless.d.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager;
            if (context == null || intent == null) {
                return;
            }
            try {
                if (intent.getAction() != null && intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    Context unused = d.b = context.getApplicationContext();
                    if (d.b != null && (connectivityManager = (ConnectivityManager) d.b.getSystemService("connectivity")) != null) {
                        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                            ULog.i("walle", "[stateless] net reveiver disconnected --->>>");
                            boolean unused2 = d.h = false;
                        } else {
                            boolean unused3 = d.h = true;
                            ULog.i("walle", "[stateless] net reveiver ok --->>>");
                            d.b(273);
                        }
                    }
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context, th);
            }
        }
    };

    public d(Context context) {
        synchronized (e) {
            if (context != null) {
                try {
                    b = context.getApplicationContext();
                    if (b != null && c == null) {
                        c = new HandlerThread("SL-NetWorkSender");
                        c.start();
                        if (d == null) {
                            d = new Handler(c.getLooper()) { // from class: com.umeng.commonsdk.stateless.d.2
                                @Override // android.os.Handler
                                public void handleMessage(Message message) {
                                    int i2 = message.what;
                                    if (i2 == 273) {
                                        d.e();
                                    } else if (i2 != 512) {
                                    } else {
                                        d.f();
                                    }
                                }
                            };
                        }
                        if (DeviceConfig.checkPermission(b, "android.permission.ACCESS_NETWORK_STATE")) {
                            ULog.i("walle", "[stateless] begin register receiver");
                            if (g == null) {
                                g = new IntentFilter();
                                g.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                                if (i != null) {
                                    ULog.i("walle", "[stateless] register receiver ok");
                                    b.registerReceiver(i, g);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void a(int i2) {
        if (!h || d == null) {
            return;
        }
        Message obtainMessage = d.obtainMessage();
        obtainMessage.what = i2;
        d.sendMessage(obtainMessage);
    }

    public static void b(int i2) {
        try {
            if (!h || d == null || d.hasMessages(i2)) {
                return;
            }
            ULog.i("walle", "[stateless] sendMsgOnce !!!!");
            Message obtainMessage = d.obtainMessage();
            obtainMessage.what = i2;
            d.sendMessage(obtainMessage);
        } catch (Throwable th) {
            UMCrashManager.reportCrash(b, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e() {
        if (!h || b == null) {
            return;
        }
        try {
            File a2 = f.a(b);
            if (a2 == null || a2.getParentFile() == null || TextUtils.isEmpty(a2.getParentFile().getName())) {
                return;
            }
            e eVar = new e(b);
            String str = new String(Base64.decode(a2.getParentFile().getName(), 0));
            ULog.i("walle", "[stateless] handleProcessNext, pathUrl is " + str);
            byte[] bArr = null;
            try {
                bArr = f.a(a2.getAbsolutePath());
            } catch (Exception unused) {
            }
            if (!eVar.a(bArr, str)) {
                ULog.i("walle", "[stateless] Send envelope file failed, abandon and wait next trigger!");
                return;
            }
            ULog.i("walle", "[stateless] Send envelope file success, delete it.");
            File file = new File(a2.getAbsolutePath());
            if (!file.delete()) {
                ULog.i("walle", "[stateless] Failed to delete already processed file. We try again after delete failed.");
                file.delete();
            }
            b(273);
        } catch (Throwable th) {
            UMCrashManager.reportCrash(b, th);
        }
    }

    public static void a() {
        b(512);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f() {
        if (g != null) {
            if (i != null) {
                if (b != null) {
                    b.unregisterReceiver(i);
                }
                i = null;
            }
            g = null;
        }
        if (c != null) {
            c.quit();
            if (c != null) {
                c = null;
            }
            if (d != null) {
                d = null;
            }
        }
    }
}