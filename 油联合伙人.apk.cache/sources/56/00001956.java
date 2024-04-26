package com.yltx.oil.partner.modules.oiltrade.presenter;

import com.yltx.oil.partner.modules.oiltrade.domain.FinanceCardUseCase;
import com.yltx.oil.partner.mvp.controller.PageLimitPresenter;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class FinanceCardPresenter extends PageLimitPresenter {
    private FinanceCardUseCase financeCardUseCase;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public FinanceCardPresenter(FinanceCardUseCase financeCardUseCase) {
        this.financeCardUseCase = financeCardUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.PageLimitPresenter
    protected UseCase buildPageUseCase(int i, int i2) {
        this.financeCardUseCase.setPageOffset(i);
        return this.financeCardUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.financeCardUseCase.unSubscribe();
    }
}