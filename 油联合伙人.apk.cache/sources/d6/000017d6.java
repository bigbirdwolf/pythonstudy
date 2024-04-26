package com.yltx.oil.partner.modules.home.view;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.home.response.MessageForDetailsResponse;
import com.yltx.oil.partner.mvp.views.ProgressView;

/* loaded from: classes.dex */
public interface MessageForDetailsView extends ProgressView {
    void onError(Throwable th);

    void onMember(HttpResult<MessageForDetailsResponse> httpResult);
}