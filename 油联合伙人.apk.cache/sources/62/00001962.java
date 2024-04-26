package com.yltx.oil.partner.modules.oiltrade.presenter;

import com.yltx.oil.partner.modules.oiltrade.domain.FuelCardListUseCase;
import com.yltx.oil.partner.mvp.controller.PageLimitPresenter;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class FuelCardListPresenter extends PageLimitPresenter {
    private FuelCardListUseCase fuelCardListUseCase;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public FuelCardListPresenter(FuelCardListUseCase fuelCardListUseCase) {
        this.fuelCardListUseCase = fuelCardListUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.PageLimitPresenter
    protected UseCase buildPageUseCase(int i, int i2) {
        this.fuelCardListUseCase.setPageOffset(i);
        return this.fuelCardListUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.fuelCardListUseCase.unSubscribe();
    }
}