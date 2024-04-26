package com.alipay.sdk.widget;

import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;
import com.umeng.socialize.net.dplus.CommonNetImpl;

/* loaded from: classes.dex */
class i implements DownloadListener {
    final /* synthetic */ h a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(h hVar) {
        this.a = hVar;
    }

    @Override // android.webkit.DownloadListener
    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.setFlags(CommonNetImpl.FLAG_AUTH);
            this.a.a.startActivity(intent);
        } catch (Throwable unused) {
        }
    }
}