package com.yltx.oil.partner.modules.home.view;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.home.response.MessageNotificationResponse;
import com.yltx.oil.partner.mvp.views.ProgressView;
import java.util.List;

/* loaded from: classes.dex */
public interface MessageNotificationView extends ProgressView {
    void onError(Throwable th);

    void onMember(HttpResult<List<MessageNotificationResponse>> httpResult);
}