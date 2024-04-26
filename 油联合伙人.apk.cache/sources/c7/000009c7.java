package com.alipay.sdk.widget;

import android.os.Handler;
import com.alipay.sdk.widget.a;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class b implements Runnable {
    final /* synthetic */ a a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(a aVar) {
        this.a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        a.AlertDialogC0008a alertDialogC0008a;
        a.AlertDialogC0008a alertDialogC0008a2;
        a.AlertDialogC0008a alertDialogC0008a3;
        Handler handler;
        a.AlertDialogC0008a alertDialogC0008a4;
        boolean z;
        alertDialogC0008a = this.a.e;
        if (alertDialogC0008a == null) {
            this.a.e = new a.AlertDialogC0008a(this.a.f);
            alertDialogC0008a4 = this.a.e;
            z = this.a.k;
            alertDialogC0008a4.setCancelable(z);
        }
        try {
            alertDialogC0008a2 = this.a.e;
            if (alertDialogC0008a2.isShowing()) {
                return;
            }
            alertDialogC0008a3 = this.a.e;
            alertDialogC0008a3.show();
            handler = this.a.l;
            handler.sendEmptyMessageDelayed(1, 15000L);
        } catch (Exception e) {
            com.alipay.sdk.util.c.a(e);
        }
    }
}