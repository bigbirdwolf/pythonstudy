package com.alibaba.sdk.android.httpdns;

import android.content.Context;

/* loaded from: classes.dex */
final class c implements Runnable {
    final /* synthetic */ Context a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(Context context) {
        this.a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        f.a(this.a);
    }
}