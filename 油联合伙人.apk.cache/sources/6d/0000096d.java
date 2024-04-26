package com.alipay.sdk.app;

import android.app.Activity;

/* loaded from: classes.dex */
final class f implements Runnable {
    final /* synthetic */ Activity a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(Activity activity) {
        this.a = activity;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.finish();
    }
}