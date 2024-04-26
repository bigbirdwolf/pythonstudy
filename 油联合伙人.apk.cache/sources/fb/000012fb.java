package com.umeng.commonsdk.framework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.FileObserver;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback;
import java.io.File;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UMNetWorkSender.java */
/* loaded from: classes.dex */
public class b implements UMImprintChangeCallback {
    private static HandlerThread a = null;
    private static Handler b = null;
    private static Handler c = null;
    private static final int d = 200;
    private static final int e = 273;
    private static final int f = 274;
    private static final int g = 512;
    private static final int h = 769;
    private static a i = null;
    private static ConnectivityManager j = null;
    private static NetworkInfo k = null;
    private static IntentFilter l = null;
    private static boolean m = false;
    private static ArrayList<UMSenderStateNotify> n = null;
    private static final String p = "report_policy";
    private static final String q = "report_interval";
    private static Object o = new Object();
    private static boolean r = false;
    private static final int s = 15000;
    private static int t = s;
    private static Object u = new Object();
    private static BroadcastReceiver v = new BroadcastReceiver() { // from class: com.umeng.commonsdk.framework.b.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int size;
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                Context appContext = UMModuleRegister.getAppContext();
                ConnectivityManager unused = b.j = (ConnectivityManager) appContext.getSystemService("connectivity");
                try {
                    if (b.j != null) {
                        NetworkInfo unused2 = b.k = b.j.getActiveNetworkInfo();
                        if (b.k != null && b.k.isAvailable()) {
                            ULog.i("--->>> network isAvailable, check if there are any files to send.");
                            boolean unused3 = b.m = true;
                            synchronized (b.o) {
                                if (b.n != null && (size = b.n.size()) > 0) {
                                    for (int i2 = 0; i2 < size; i2++) {
                                        ((UMSenderStateNotify) b.n.get(i2)).onConnectionAvailable();
                                    }
                                }
                            }
                            b.c(273);
                            if (b.k.getType() != 1 || context == null) {
                                return;
                            }
                            try {
                                if (UMWorkDispatch.eventHasExist(com.umeng.commonsdk.internal.a.j)) {
                                    return;
                                }
                                UMWorkDispatch.sendEvent(context, com.umeng.commonsdk.internal.a.j, com.umeng.commonsdk.internal.b.a(context).a(), null);
                                return;
                            } catch (Throwable unused4) {
                                return;
                            }
                        }
                        ULog.i("--->>> network disconnected.");
                        boolean unused5 = b.m = false;
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(appContext, th);
                }
            }
        }
    };

    public static void a(UMSenderStateNotify uMSenderStateNotify) {
        synchronized (o) {
            if (n == null) {
                n = new ArrayList<>();
            }
            if (uMSenderStateNotify != null) {
                for (int i2 = 0; i2 < n.size(); i2++) {
                    if (uMSenderStateNotify == n.get(i2)) {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> addConnStateObserver: input item has exist.");
                        return;
                    }
                }
                n.add(uMSenderStateNotify);
            }
        }
    }

    public static boolean a() {
        boolean z;
        synchronized (u) {
            z = r;
        }
        return z;
    }

    public static int b() {
        int i2;
        synchronized (u) {
            i2 = t;
        }
        return i2;
    }

    private void m() {
        synchronized (u) {
            if ("11".equals(UMEnvelopeBuild.imprintProperty(UMModuleRegister.getAppContext(), p, ""))) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> switch to report_policy 11");
                r = true;
                t = s;
                int intValue = Integer.valueOf(UMEnvelopeBuild.imprintProperty(UMModuleRegister.getAppContext(), q, "15")).intValue();
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> set report_interval value to: " + intValue);
                if (intValue >= 15 && intValue <= 90) {
                    t = intValue * 1000;
                }
                t = s;
            } else {
                r = false;
            }
        }
    }

    @Override // com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback
    public void onImprintValueChanged(String str, String str2) {
        synchronized (u) {
            if (p.equals(str)) {
                if ("11".equals(str2)) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> switch to report_policy 11");
                    r = true;
                    t = s;
                } else {
                    r = false;
                }
            }
            if (q.equals(str)) {
                int intValue = Integer.valueOf(str2).intValue();
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> set report_interval value to: " + intValue);
                if (intValue >= 15 && intValue <= 90) {
                    t = intValue * 1000;
                }
                t = s;
            }
        }
    }

    public b(Context context, Handler handler) {
        c = handler;
        try {
            if (a == null) {
                a = new HandlerThread("NetWorkSender");
                a.start();
                if (i == null) {
                    i = new a(UMFrUtils.getEnvelopeDirPath(context));
                    i.startWatching();
                    ULog.d("--->>> FileMonitor has already started!");
                }
                Context appContext = UMModuleRegister.getAppContext();
                if (DeviceConfig.checkPermission(appContext, "android.permission.ACCESS_NETWORK_STATE") && l == null) {
                    l = new IntentFilter();
                    l.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                    if (v != null) {
                        appContext.registerReceiver(v, l);
                    }
                }
                m();
                if (b == null) {
                    b = new Handler(a.getLooper()) { // from class: com.umeng.commonsdk.framework.b.2
                        @Override // android.os.Handler
                        public void handleMessage(Message message) {
                            int i2 = message.what;
                            if (i2 == 512) {
                                b.p();
                                return;
                            }
                            switch (i2) {
                                case 273:
                                    ULog.d("--->>> handleMessage: recv MSG_PROCESS_NEXT msg.");
                                    b.q();
                                    return;
                                case b.f /* 274 */:
                                    b.o();
                                    return;
                                default:
                                    return;
                            }
                        }
                    };
                }
                ImprintHandler.getImprintService(context).registImprintCallback(p, this);
                ImprintHandler.getImprintService(context).registImprintCallback(q, this);
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
        }
    }

    private static void n() {
        if (a != null) {
            a = null;
        }
        if (b != null) {
            b = null;
        }
        if (c != null) {
            c = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void o() {
        int size;
        synchronized (o) {
            if (n != null && (size = n.size()) > 0) {
                for (int i2 = 0; i2 < size; i2++) {
                    n.get(i2).onSenderIdle();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void p() {
        if (i != null) {
            i.stopWatching();
            i = null;
        }
        if (l != null) {
            if (v != null) {
                UMModuleRegister.getAppContext().unregisterReceiver(v);
                v = null;
            }
            l = null;
        }
        ULog.d("--->>> handleQuit: Quit sender thread.");
        if (a != null) {
            a.quit();
            n();
        }
    }

    public static void c() {
        c(512);
    }

    private static void b(int i2) {
        if (!m || b == null || b.hasMessages(i2)) {
            return;
        }
        Message obtainMessage = b.obtainMessage();
        obtainMessage.what = i2;
        b.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(int i2) {
        if (!m || b == null) {
            return;
        }
        Message obtainMessage = b.obtainMessage();
        obtainMessage.what = i2;
        b.sendMessage(obtainMessage);
    }

    private static void a(int i2, long j2) {
        if (!m || b == null) {
            return;
        }
        Message obtainMessage = b.obtainMessage();
        obtainMessage.what = i2;
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> sendMsgDelayed: " + j2);
        b.sendMessageDelayed(obtainMessage, j2);
    }

    public static void d() {
        b(273);
    }

    private static void a(int i2, int i3) {
        if (!m || b == null) {
            return;
        }
        b.removeMessages(i2);
        Message obtainMessage = b.obtainMessage();
        obtainMessage.what = i2;
        b.sendMessageDelayed(obtainMessage, i3);
    }

    public static void e() {
        a((int) f, 3000);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: UMNetWorkSender.java */
    /* loaded from: classes.dex */
    public static class a extends FileObserver {
        public a(String str) {
            super(str);
        }

        @Override // android.os.FileObserver
        public void onEvent(int i, String str) {
            if ((i & 8) != 8) {
                return;
            }
            ULog.d("--->>> envelope file created >>> " + str);
            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> envelope file created >>> " + str);
            b.c(273);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void q() {
        ULog.d("--->>> handleProcessNext: Enter...");
        if (m) {
            Context appContext = UMModuleRegister.getAppContext();
            try {
                if (UMFrUtils.envelopeFileNumber(appContext) > 0) {
                    ULog.d("--->>> The envelope file exists.");
                    if (UMFrUtils.envelopeFileNumber(appContext) > 200) {
                        ULog.d("--->>> Number of envelope files is greater than 200, remove old files first.");
                        UMFrUtils.removeRedundantEnvelopeFiles(appContext, 200);
                    }
                    File envelopeFile = UMFrUtils.getEnvelopeFile(appContext);
                    if (envelopeFile != null) {
                        String path = envelopeFile.getPath();
                        ULog.d("--->>> Ready to send envelope file [" + path + "].");
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> send envelope file [ " + path + "].");
                        if (new com.umeng.commonsdk.statistics.c(appContext).a(envelopeFile)) {
                            ULog.d("--->>> Send envelope file success, delete it.");
                            if (!UMFrUtils.removeEnvelopeFile(envelopeFile)) {
                                ULog.d("--->>> Failed to delete already processed file. We try again after delete failed.");
                                UMFrUtils.removeEnvelopeFile(envelopeFile);
                            }
                            c(273);
                            return;
                        }
                        ULog.d("--->>> Send envelope file failed, abandon and wait next trigger!");
                        return;
                    }
                }
                e();
            } catch (Throwable th) {
                UMCrashManager.reportCrash(appContext, th);
            }
        }
    }
}