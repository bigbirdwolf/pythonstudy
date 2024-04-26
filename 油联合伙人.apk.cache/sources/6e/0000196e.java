package com.yltx.oil.partner.modules.oiltrade.view;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.FinanceCardlResp;
import com.yltx.oil.partner.mvp.views.ProgressView;
import java.util.List;

/* loaded from: classes.dex */
public interface FinanCardView extends ProgressView {
    void onError(Throwable th);

    void onfinanceCard(HttpResult<List<FinanceCardlResp>> httpResult);
}