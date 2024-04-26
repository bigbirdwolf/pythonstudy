package com.alipay.sdk.util;

import com.alipay.sdk.app.AlipayResultActivity;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class g implements AlipayResultActivity.b {
    final /* synthetic */ CountDownLatch a;
    final /* synthetic */ e b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(e eVar, CountDownLatch countDownLatch) {
        this.b = eVar;
        this.a = countDownLatch;
    }

    @Override // com.alipay.sdk.app.AlipayResultActivity.b
    public void a(int i, String str, String str2) {
        this.b.i = com.alipay.sdk.app.j.a(i, str, str2);
        this.a.countDown();
    }
}