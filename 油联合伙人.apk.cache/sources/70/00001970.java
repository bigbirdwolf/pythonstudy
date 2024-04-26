package com.yltx.oil.partner.modules.oiltrade.view;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.oiltrade.response.FuelCardListResponse;
import com.yltx.oil.partner.mvp.views.ProgressView;
import java.util.List;

/* loaded from: classes.dex */
public interface FuelCardListView extends ProgressView {
    void OnCard(HttpResult<List<FuelCardListResponse>> httpResult);

    void onError(Throwable th);
}