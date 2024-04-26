package com.yltx.oil.partner.modules.profit.view;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.mvp.views.ProgressView;

/* loaded from: classes.dex */
public interface BindingBankView extends ProgressView {
    void onBindingBank(HttpResult<String> httpResult);

    void onError(Throwable th);

    void onUpBindingBank(HttpResult<String> httpResult);
}