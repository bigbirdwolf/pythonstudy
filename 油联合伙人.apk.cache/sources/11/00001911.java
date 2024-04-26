package com.yltx.oil.partner.modules.mine.view;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.mvp.views.ProgressView;

/* loaded from: classes.dex */
public interface ComplainView extends ProgressView {
    void onComplain(HttpResult<String> httpResult);

    void onError(Throwable th);
}