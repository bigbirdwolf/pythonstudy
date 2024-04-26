package com.alibaba.sdk.android.httpdns;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class m implements Runnable {
    final /* synthetic */ l a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(l lVar) {
        this.a = lVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context;
        context = l.b;
        f.a(context);
    }
}