package com.alipay.sdk.auth;

import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;

/* loaded from: classes.dex */
class a implements DownloadListener {
    final /* synthetic */ AuthActivity a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(AuthActivity authActivity) {
        this.a = authActivity;
    }

    @Override // android.webkit.DownloadListener
    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Throwable unused) {
        }
    }
}