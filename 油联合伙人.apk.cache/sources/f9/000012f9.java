package com.umeng.commonsdk.framework;

import android.content.Context;

/* loaded from: classes.dex */
public class UMWorkDispatch {
    public static void sendEvent(Context context, int i, UMLogDataProtocol uMLogDataProtocol, Object obj) {
        c.a(context, i, uMLogDataProtocol, obj);
    }

    public static boolean eventHasExist(int i) {
        return c.a(i);
    }

    public static void Quit() {
        c.a();
    }

    public static void registerConnStateObserver(UMSenderStateNotify uMSenderStateNotify) {
        c.a(uMSenderStateNotify);
    }
}