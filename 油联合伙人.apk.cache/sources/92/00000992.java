package com.alipay.sdk.data;

import android.content.Context;
import java.util.HashMap;
import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class d implements Callable<String> {
    final /* synthetic */ Context a;
    final /* synthetic */ HashMap b;
    final /* synthetic */ c c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(c cVar, Context context, HashMap hashMap) {
        this.c = cVar;
        this.a = context;
        this.b = hashMap;
    }

    @Override // java.util.concurrent.Callable
    /* renamed from: a */
    public String call() throws Exception {
        String a;
        a = this.c.a(this.a, this.b);
        return a;
    }
}