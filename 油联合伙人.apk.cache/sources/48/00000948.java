package com.alipay.apmobilesecuritysdk.f;

import java.util.LinkedList;

/* loaded from: classes.dex */
public final class b {
    private static b a = new b();
    private Thread b = null;
    private LinkedList<Runnable> c = new LinkedList<>();

    public static b a() {
        return a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Thread b(b bVar) {
        bVar.b = null;
        return null;
    }

    public final synchronized void a(Runnable runnable) {
        this.c.add(runnable);
        if (this.b == null) {
            this.b = new Thread(new c(this));
            this.b.start();
        }
    }
}