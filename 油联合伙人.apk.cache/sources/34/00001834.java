package com.yltx.oil.partner.modules.login.view;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.LoginInfo;
import com.yltx.oil.partner.mvp.views.ProgressView;

/* loaded from: classes.dex */
public interface LoginView extends ProgressView {
    void onError(Throwable th);

    void onLoginSuccess(HttpResult<LoginInfo> httpResult);
}