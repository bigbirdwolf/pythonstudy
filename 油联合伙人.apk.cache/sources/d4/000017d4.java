package com.yltx.oil.partner.modules.home.view;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.BannerResponse;
import com.yltx.oil.partner.mvp.views.ProgressView;
import java.util.List;

/* loaded from: classes.dex */
public interface BannerView extends ProgressView {
    void onBanner(HttpResult<List<BannerResponse>> httpResult);

    void onError(Throwable th);
}