package com.yltx.oil.partner.modules.profit.view;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.profit.response.ManageResponse;
import com.yltx.oil.partner.mvp.views.ProgressView;

/* loaded from: classes.dex */
public interface AccountBalanceView extends ProgressView {
    void onAccountBalance(HttpResult<String> httpResult);

    void onError(Throwable th);

    void onIsAuth(HttpResult<String> httpResult);

    void onyzjxsjfx(HttpResult<ManageResponse> httpResult);
}