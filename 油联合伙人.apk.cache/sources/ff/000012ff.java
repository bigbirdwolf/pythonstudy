package com.umeng.commonsdk.framework;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.ULog;
import org.json.JSONObject;

/* compiled from: UMWorkDispatchImpl.java */
/* loaded from: classes.dex */
public class c {
    public static final String a = "content";
    public static final String b = "header";
    public static final String c = "exception";
    private static HandlerThread d = null;
    private static Handler e = null;
    private static b f = null;
    private static Object g = new Object();
    private static final int h = 768;
    private static final int i = 769;
    private static final int j = 770;
    private static final int k = 784;

    public static void a(UMSenderStateNotify uMSenderStateNotify) {
        if (f != null) {
            b.a(uMSenderStateNotify);
        }
    }

    public static void a(Context context, int i2, UMLogDataProtocol uMLogDataProtocol, Object obj) {
        if (context == null || uMLogDataProtocol == null) {
            ULog.d("--->>> Context or UMLogDataProtocol parameter cannot be null!");
            return;
        }
        UMModuleRegister.registerAppContext(context.getApplicationContext());
        if (UMModuleRegister.registerCallback(i2, uMLogDataProtocol)) {
            if (d == null || e == null) {
                e();
            }
            try {
                if (e != null) {
                    if (UMGlobalContext.getInstance().isMainProcess(context) && f == null) {
                        synchronized (g) {
                            UMFrUtils.syncLegacyEnvelopeIfNeeded(context);
                            f = new b(context, e);
                        }
                    }
                    Message obtainMessage = e.obtainMessage();
                    obtainMessage.what = h;
                    obtainMessage.arg1 = i2;
                    obtainMessage.obj = obj;
                    e.sendMessage(obtainMessage);
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(UMModuleRegister.getAppContext(), th);
            }
        }
    }

    private c() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d() {
        JSONObject buildEnvelopeWithExtHeader;
        ULog.d("--->>> delayProcess Enter...");
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> delayProcess Enter...");
        Context appContext = UMModuleRegister.getAppContext();
        if (appContext == null || !UMFrUtils.isOnline(appContext)) {
            return;
        }
        long maxDataSpace = UMEnvelopeBuild.maxDataSpace(appContext);
        UMLogDataProtocol callbackFromModuleName = UMModuleRegister.getCallbackFromModuleName("analytics");
        JSONObject jSONObject = null;
        if (callbackFromModuleName != null) {
            try {
                jSONObject = callbackFromModuleName.setupReportData(maxDataSpace);
                if (jSONObject == null) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> analyticsCB.setupReportData() return null");
                    return;
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(appContext, th);
                return;
            }
        }
        if (jSONObject == null || jSONObject.length() <= 0) {
            return;
        }
        JSONObject jSONObject2 = (JSONObject) jSONObject.opt("header");
        JSONObject jSONObject3 = (JSONObject) jSONObject.opt("content");
        if (appContext == null || jSONObject2 == null || jSONObject3 == null || (buildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(appContext, jSONObject2, jSONObject3)) == null) {
            return;
        }
        try {
            if (buildEnvelopeWithExtHeader.has("exception")) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> autoProcess: Build envelope error code: " + buildEnvelopeWithExtHeader.getInt("exception"));
            }
        } catch (Throwable unused) {
        }
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> autoProcess: removeCacheData ... ");
        callbackFromModuleName.removeCacheData(buildEnvelopeWithExtHeader);
    }

    public static void a(long j2) {
        if (e != null) {
            if (e.hasMessages(j)) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> MSG_DELAY_PROCESS has exist. do nothing.");
                return;
            }
            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> MSG_DELAY_PROCESS not exist. send it.");
            Message obtainMessage = e.obtainMessage();
            obtainMessage.what = j;
            e.sendMessageDelayed(obtainMessage, j2);
        }
    }

    private static synchronized void e() {
        synchronized (c.class) {
            ULog.d("--->>> Dispatch: init Enter...");
            if (d == null) {
                d = new HandlerThread("work_thread");
                d.start();
                if (e == null) {
                    e = new Handler(d.getLooper()) { // from class: com.umeng.commonsdk.framework.c.1
                        @Override // android.os.Handler
                        public void handleMessage(Message message) {
                            int i2 = message.what;
                            if (i2 != c.k) {
                                switch (i2) {
                                    case c.h /* 768 */:
                                        c.b(message);
                                        return;
                                    case c.i /* 769 */:
                                    default:
                                        return;
                                    case c.j /* 770 */:
                                        c.d();
                                        return;
                                }
                            }
                            c.g();
                        }
                    };
                }
            }
            ULog.d("--->>> Dispatch: init Exit...");
        }
    }

    public static synchronized boolean a(int i2) {
        synchronized (c.class) {
            if (e == null) {
                return false;
            }
            return e.hasMessages(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Message message) {
        int i2 = message.arg1;
        Object obj = message.obj;
        UMLogDataProtocol callbackFromModuleName = UMModuleRegister.getCallbackFromModuleName(UMModuleRegister.eventType2ModuleName(i2));
        if (callbackFromModuleName != null) {
            ULog.d("--->>> dispatch:handleEvent: call back workEvent with msg type [ 0x" + Integer.toHexString(i2) + "]");
            callbackFromModuleName.workEvent(obj, i2);
        }
    }

    private static void f() {
        if (d != null) {
            d = null;
        }
        if (e != null) {
            e = null;
        }
        if (f != null) {
            f = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void g() {
        if (f == null || d == null) {
            return;
        }
        b.c();
        ULog.d("--->>> handleQuit: Quit dispatch thread.");
        d.quit();
        f();
    }

    public static void a() {
        if (e != null) {
            Message obtainMessage = e.obtainMessage();
            obtainMessage.what = k;
            e.sendMessage(obtainMessage);
        }
    }
}