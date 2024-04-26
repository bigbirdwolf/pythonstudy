package com.yltx.oil.partner.modules.login.view;

import com.yltx.oil.partner.mvp.views.ProgressView;

/* loaded from: classes.dex */
public interface ForgetPwdView extends ProgressView {
    void onError(Throwable th);

    void onSuccess();

    void onVcodeSuccess();
}