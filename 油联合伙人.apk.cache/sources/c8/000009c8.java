package com.alipay.sdk.widget;

import android.os.Handler;
import com.alipay.sdk.widget.a;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class c implements Runnable {
    final /* synthetic */ a a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(a aVar) {
        this.a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        a.AlertDialogC0008a alertDialogC0008a;
        a.AlertDialogC0008a alertDialogC0008a2;
        Handler handler;
        a.AlertDialogC0008a alertDialogC0008a3;
        alertDialogC0008a = this.a.e;
        if (alertDialogC0008a != null) {
            alertDialogC0008a2 = this.a.e;
            if (alertDialogC0008a2.isShowing()) {
                try {
                    handler = this.a.l;
                    handler.removeMessages(1);
                    alertDialogC0008a3 = this.a.e;
                    alertDialogC0008a3.dismiss();
                } catch (Exception e) {
                    com.alipay.sdk.util.c.a(e);
                }
            }
        }
    }
}