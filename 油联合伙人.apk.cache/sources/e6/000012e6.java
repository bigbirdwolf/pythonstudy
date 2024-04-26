package com.umeng.commonsdk;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.b;

/* compiled from: UMConfigureInternation.java */
/* loaded from: classes.dex */
public class a {
    private static boolean a = false;

    public static synchronized void a(final Context context) {
        synchronized (a.class) {
            if (context != null) {
                try {
                    if (!a) {
                        new Thread(new Runnable() { // from class: com.umeng.commonsdk.a.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    String currentProcessName = UMFrUtils.getCurrentProcessName(context);
                                    String packageName = context.getPackageName();
                                    if (TextUtils.isEmpty(currentProcessName) || TextUtils.isEmpty(packageName) || !currentProcessName.equals(packageName) || !UMEnvelopeBuild.isReadyBuild(context, UMLogDataProtocol.UMBusinessType.U_INTERNAL)) {
                                        return;
                                    }
                                    UMWorkDispatch.sendEvent(context, com.umeng.commonsdk.internal.a.m, b.a(context).a(), null);
                                } catch (Throwable unused) {
                                }
                            }
                        }).start();
                        a = true;
                    }
                } finally {
                }
            }
        }
    }
}