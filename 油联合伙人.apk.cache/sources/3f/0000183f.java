package com.yltx.oil.partner.modules.main.view;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.LoginInfo;
import com.yltx.oil.partner.data.response.VersionResponse;
import com.yltx.oil.partner.mvp.views.ProgressView;

/* loaded from: classes.dex */
public interface SplashView extends ProgressView {
    void onCheckVersionSuccess(VersionResponse versionResponse);

    void onCheckVersiononError(Throwable th);

    void onError(Throwable th);

    void onLoginSuccess(HttpResult<LoginInfo> httpResult);
}