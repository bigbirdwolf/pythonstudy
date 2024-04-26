package com.yltx.oil.partner.modules.mine.view;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.InviteResp;
import com.yltx.oil.partner.mvp.views.ProgressView;

/* loaded from: classes.dex */
public interface InviteView extends ProgressView {
    void onError(Throwable th);

    void onSuccess(HttpResult<InviteResp> httpResult);
}