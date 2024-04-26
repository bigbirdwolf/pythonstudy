package com.yltx.oil.partner.modules.home.presenter;

import com.yltx.oil.partner.modules.home.domain.ShopRecommendUseCase;
import com.yltx.oil.partner.mvp.controller.PageLimitPresenter;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class ShopRecommendPresenter extends PageLimitPresenter {
    private ShopRecommendUseCase mStoredValueCardUseCase;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public ShopRecommendPresenter(ShopRecommendUseCase shopRecommendUseCase) {
        this.mStoredValueCardUseCase = shopRecommendUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.PageLimitPresenter
    protected UseCase buildPageUseCase(int i, int i2) {
        this.mStoredValueCardUseCase.setPageOffset(i);
        return this.mStoredValueCardUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.mStoredValueCardUseCase.unSubscribe();
    }
}