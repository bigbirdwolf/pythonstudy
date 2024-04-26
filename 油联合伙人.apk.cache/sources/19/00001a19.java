package com.yltx.oil.partner.modules.profit.presenter;

import com.yltx.oil.partner.modules.profit.domain.SettlementRecordsUseCase;
import com.yltx.oil.partner.mvp.controller.PageLimitPresenter;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class SettlementRecordsPresenter extends PageLimitPresenter {
    private SettlementRecordsUseCase useCase;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public SettlementRecordsPresenter(SettlementRecordsUseCase settlementRecordsUseCase) {
        this.useCase = settlementRecordsUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.PageLimitPresenter
    protected UseCase buildPageUseCase(int i, int i2) {
        this.useCase.setPageOffset(i);
        return this.useCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.useCase.unSubscribe();
    }
}