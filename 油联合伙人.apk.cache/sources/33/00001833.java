package com.yltx.oil.partner.modules.login.view;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.mvp.views.ProgressView;

/* loaded from: classes.dex */
public interface GetValidCodeView extends ProgressView {
    void onsmsSuccess(HttpResult<String> httpResult);
}