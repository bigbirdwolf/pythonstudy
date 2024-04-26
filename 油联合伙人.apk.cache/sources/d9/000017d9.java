package com.yltx.oil.partner.modules.home.view;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.home.response.SSLSResponse;
import com.yltx.oil.partner.mvp.views.ProgressView;
import java.util.List;

/* loaded from: classes.dex */
public interface SSLSView extends ProgressView {
    void onError(Throwable th);

    void onSSLS(HttpResult<List<SSLSResponse>> httpResult);
}