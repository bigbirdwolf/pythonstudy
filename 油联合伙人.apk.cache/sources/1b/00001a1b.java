package com.yltx.oil.partner.modules.profit.presenter;

import com.yltx.oil.partner.data.response.TxHistoryResp;
import com.yltx.oil.partner.modules.profit.domain.GetTxHistoryListUseCase;
import com.yltx.oil.partner.mvp.controller.PageLimitPresenter;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class TxHistoryPresenter extends PageLimitPresenter<TxHistoryResp> {
    private GetTxHistoryListUseCase mUseCase;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public TxHistoryPresenter(GetTxHistoryListUseCase getTxHistoryListUseCase) {
        this.mUseCase = getTxHistoryListUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.mUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.PageLimitPresenter
    protected UseCase<TxHistoryResp> buildPageUseCase(int i, int i2) {
        this.mUseCase.setPageOffset(i);
        return this.mUseCase;
    }
}