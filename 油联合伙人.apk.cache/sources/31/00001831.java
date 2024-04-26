package com.yltx.oil.partner.modules.login.view;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.AppLoginStatusResp;
import com.yltx.oil.partner.mvp.views.ProgressView;

/* loaded from: classes.dex */
public interface AppLoginStatusView extends ProgressView {
    void onError(Throwable th);

    void onStatusLoginSuccess(HttpResult<AppLoginStatusResp> httpResult);
}