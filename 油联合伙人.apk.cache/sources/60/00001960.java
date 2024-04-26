package com.yltx.oil.partner.modules.oiltrade.presenter;

import com.yltx.oil.partner.modules.oiltrade.domain.FuelCardDetailsUseCase;
import com.yltx.oil.partner.mvp.controller.PageLimitPresenter;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class FuelCardDetailsPresenter extends PageLimitPresenter {
    private FuelCardDetailsUseCase fuelCardListUseCase;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public FuelCardDetailsPresenter(FuelCardDetailsUseCase fuelCardDetailsUseCase) {
        this.fuelCardListUseCase = fuelCardDetailsUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.PageLimitPresenter
    protected UseCase buildPageUseCase(int i, int i2) {
        return this.fuelCardListUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.fuelCardListUseCase.unSubscribe();
    }
}