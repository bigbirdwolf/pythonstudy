package com.alipay.sdk.widget;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import com.alipay.sdk.widget.WebViewWindow;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class q implements View.OnClickListener {
    final /* synthetic */ WebViewWindow a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public q(WebViewWindow webViewWindow) {
        this.a = webViewWindow;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        WebViewWindow.c cVar;
        Handler handler;
        ImageView imageView;
        ImageView imageView2;
        cVar = this.a.i;
        if (cVar != null) {
            view.setEnabled(false);
            handler = WebViewWindow.f;
            handler.postDelayed(new r(this, view), 256L);
            imageView = this.a.a;
            if (view != imageView) {
                imageView2 = this.a.c;
                if (view == imageView2) {
                    cVar.b(this.a);
                    return;
                }
                return;
            }
            cVar.a(this.a);
        }
    }
}