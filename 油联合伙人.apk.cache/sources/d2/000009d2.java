package com.alipay.sdk.widget;

import android.view.animation.Animation;
import com.alipay.sdk.widget.j;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class l extends j.a {
    final /* synthetic */ WebViewWindow a;
    final /* synthetic */ j b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(j jVar, WebViewWindow webViewWindow) {
        super(jVar, null);
        this.b = jVar;
        this.a = webViewWindow;
    }

    @Override // com.alipay.sdk.widget.j.a, android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        this.a.a();
        this.b.v = false;
    }
}