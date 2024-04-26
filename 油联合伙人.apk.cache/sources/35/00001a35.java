package com.yltx.oil.partner.modules.profit.view;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.BankInfoResp;
import com.yltx.oil.partner.mvp.views.ProgressView;

/* loaded from: classes.dex */
public interface TxModificationView extends ProgressView {
    void applyFailed();

    void applySuccess(HttpResult<BankInfoResp> httpResult);
}