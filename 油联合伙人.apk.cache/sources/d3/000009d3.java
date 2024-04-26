package com.alipay.sdk.widget;

import android.view.animation.Animation;
import com.alipay.sdk.widget.j;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class m extends j.a {
    final /* synthetic */ WebViewWindow a;
    final /* synthetic */ String b;
    final /* synthetic */ j c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(j jVar, WebViewWindow webViewWindow, String str) {
        super(jVar, null);
        this.c = jVar;
        this.a = webViewWindow;
        this.b = str;
    }

    @Override // com.alipay.sdk.widget.j.a, android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        WebViewWindow webViewWindow;
        this.c.removeView(this.a);
        webViewWindow = this.c.x;
        webViewWindow.a(this.b);
        this.c.v = false;
    }
}