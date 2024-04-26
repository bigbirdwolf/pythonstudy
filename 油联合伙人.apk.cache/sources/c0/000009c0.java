package com.alipay.sdk.util;

import android.app.Activity;

/* loaded from: classes.dex */
final class p implements Runnable {
    final /* synthetic */ Activity a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public p(Activity activity) {
        this.a = activity;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.finish();
    }
}