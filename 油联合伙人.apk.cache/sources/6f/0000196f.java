package com.yltx.oil.partner.modules.oiltrade.view;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.FinanceCardlResp;
import com.yltx.oil.partner.data.response.GiftCardResp;
import com.yltx.oil.partner.data.response.SfResp;
import com.yltx.oil.partner.data.response.ShopDetailsResp;
import com.yltx.oil.partner.data.response.StoredValueCardResp;
import com.yltx.oil.partner.modules.oiltrade.response.FuelCardDetailsResponse;
import com.yltx.oil.partner.mvp.views.ProgressView;

/* loaded from: classes.dex */
public interface FinanCarddetailView extends ProgressView {
    void onDetails(SfResp sfResp);

    void onError(Throwable th);

    void onGiftCardDetails(HttpResult<GiftCardResp> httpResult);

    void onStoredValueDetail(HttpResult<StoredValueCardResp> httpResult);

    void onfinanceCarddetail(HttpResult<FinanceCardlResp> httpResult);

    void onfuelDetails(HttpResult<FuelCardDetailsResponse> httpResult);

    void onshopDetails(HttpResult<ShopDetailsResp> httpResult);
}