package com.yltx.oil.partner.modules.mine.view;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.mvp.views.ProgressView;

/* loaded from: classes.dex */
public interface ModifyusernicknamesView extends ProgressView {
    void onError(Throwable th);

    void onModif(HttpResult<String> httpResult);
}