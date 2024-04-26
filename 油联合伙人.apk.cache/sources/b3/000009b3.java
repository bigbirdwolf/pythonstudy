package com.alipay.sdk.util;

import android.app.Activity;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import com.alipay.android.app.IAlixPay;
import com.alipay.android.app.IRemoteServiceCallback;
import com.alipay.sdk.util.n;

/* loaded from: classes.dex */
public class e {
    public static final String a = "failed";
    public static final String b = "scheme_failed";
    private Activity c;
    private IAlixPay d;
    private boolean f;
    private a g;
    private final Object e = IAlixPay.class;
    private ServiceConnection h = new f(this);
    private String i = null;
    private IRemoteServiceCallback j = new h(this);

    /* loaded from: classes.dex */
    public interface a {
        void a();

        void b();
    }

    public e(Activity activity, a aVar) {
        this.c = activity;
        this.g = aVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0056 A[Catch: Throwable -> 0x0062, TryCatch #0 {Throwable -> 0x0062, blocks: (B:3:0x0003, B:7:0x0017, B:9:0x001f, B:11:0x0025, B:14:0x002c, B:18:0x0037, B:20:0x003b, B:23:0x0048, B:25:0x0051, B:27:0x0056, B:28:0x005b, B:24:0x004d, B:6:0x0015), top: B:35:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String a(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.String r0 = ""
            r1 = 0
            com.alipay.sdk.data.a r2 = com.alipay.sdk.data.a.g()     // Catch: java.lang.Throwable -> L62
            java.util.List r2 = r2.f()     // Catch: java.lang.Throwable -> L62
            com.alipay.sdk.data.a r3 = com.alipay.sdk.data.a.g()     // Catch: java.lang.Throwable -> L62
            boolean r3 = r3.p     // Catch: java.lang.Throwable -> L62
            if (r3 == 0) goto L15
            if (r2 != 0) goto L17
        L15:
            java.util.List<com.alipay.sdk.data.a$a> r2 = com.alipay.sdk.app.i.a     // Catch: java.lang.Throwable -> L62
        L17:
            android.app.Activity r3 = r5.c     // Catch: java.lang.Throwable -> L62
            com.alipay.sdk.util.n$a r2 = com.alipay.sdk.util.n.a(r3, r2)     // Catch: java.lang.Throwable -> L62
            if (r2 == 0) goto L5f
            boolean r3 = r2.a()     // Catch: java.lang.Throwable -> L62
            if (r3 != 0) goto L5f
            boolean r3 = r2.b()     // Catch: java.lang.Throwable -> L62
            if (r3 == 0) goto L2c
            goto L5f
        L2c:
            android.content.pm.PackageInfo r3 = r2.a     // Catch: java.lang.Throwable -> L62
            boolean r3 = com.alipay.sdk.util.n.a(r3)     // Catch: java.lang.Throwable -> L62
            if (r3 == 0) goto L37
            java.lang.String r2 = "failed"
            return r2
        L37:
            android.content.pm.PackageInfo r3 = r2.a     // Catch: java.lang.Throwable -> L62
            if (r3 == 0) goto L4d
            java.lang.String r3 = "com.eg.android.AlipayGphone"
            android.content.pm.PackageInfo r4 = r2.a     // Catch: java.lang.Throwable -> L62
            java.lang.String r4 = r4.packageName     // Catch: java.lang.Throwable -> L62
            boolean r3 = r3.equals(r4)     // Catch: java.lang.Throwable -> L62
            if (r3 == 0) goto L48
            goto L4d
        L48:
            android.content.pm.PackageInfo r3 = r2.a     // Catch: java.lang.Throwable -> L62
            java.lang.String r3 = r3.packageName     // Catch: java.lang.Throwable -> L62
            goto L51
        L4d:
            java.lang.String r3 = com.alipay.sdk.util.n.a()     // Catch: java.lang.Throwable -> L62
        L51:
            r0 = r3
            android.content.pm.PackageInfo r3 = r2.a     // Catch: java.lang.Throwable -> L62
            if (r3 == 0) goto L5b
            android.content.pm.PackageInfo r3 = r2.a     // Catch: java.lang.Throwable -> L62
            int r3 = r3.versionCode     // Catch: java.lang.Throwable -> L62
            r1 = r3
        L5b:
            r5.a(r2)     // Catch: java.lang.Throwable -> L62
            goto L6a
        L5f:
            java.lang.String r2 = "failed"
            return r2
        L62:
            r2 = move-exception
            java.lang.String r3 = "biz"
            java.lang.String r4 = "CheckClientSignEx"
            com.alipay.sdk.app.statistic.a.a(r3, r4, r2)
        L6a:
            java.lang.String r6 = r5.a(r6, r0, r1)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.util.e.a(java.lang.String):java.lang.String");
    }

    private void a(n.a aVar) throws InterruptedException {
        PackageInfo packageInfo;
        if (aVar == null || (packageInfo = aVar.a) == null) {
            return;
        }
        String str = packageInfo.packageName;
        Intent intent = new Intent();
        intent.setClassName(str, "com.alipay.android.app.TransProcessPayActivity");
        try {
            this.c.startActivity(intent);
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.H, th);
        }
        Thread.sleep(200L);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:16:0x00c9
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    private java.lang.String a(java.lang.String r6, java.lang.String r7, int r8) {
        /*
            Method dump skipped, instructions count: 297
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.util.e.a(java.lang.String, java.lang.String, int):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00ef, code lost:
        if (r7.c != null) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00f1, code lost:
        r7.c.setRequestedOrientation(0);
        r7.f = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0131, code lost:
        if (r7.c != null) goto L48;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String a(java.lang.String r8, java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 383
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.util.e.a(java.lang.String, java.lang.String):java.lang.String");
    }

    public void a() {
        this.c = null;
    }
}