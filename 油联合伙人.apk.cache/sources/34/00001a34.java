package com.yltx.oil.partner.modules.profit.view;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.profit.response.JoinResponse;
import com.yltx.oil.partner.mvp.views.ProgressView;

/* loaded from: classes.dex */
public interface JoinView extends ProgressView {
    void onError(Throwable th);

    void onJoin(HttpResult<JoinResponse> httpResult);
}