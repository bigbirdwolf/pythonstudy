package com.alipay.sdk.widget;

import android.view.View;

/* loaded from: classes.dex */
class r implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ q b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public r(q qVar, View view) {
        this.b = qVar;
        this.a = view;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.setEnabled(true);
    }
}