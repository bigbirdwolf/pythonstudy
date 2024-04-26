package com.yltx.oil.partner.modules.mine.view;

import com.yltx.oil.partner.data.response.LnvoicePayResp;
import com.yltx.oil.partner.data.response.RechargePayTypeResp;
import com.yltx.oil.partner.mvp.views.LoadDataView;

/* loaded from: classes.dex */
public interface RechargeView extends LoadDataView {
    void onPaySuccess(LnvoicePayResp lnvoicePayResp);

    void onPayTypeError(Throwable th);

    void onPayTypeSuccess(RechargePayTypeResp rechargePayTypeResp);
}