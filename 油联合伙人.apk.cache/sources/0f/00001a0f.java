package com.yltx.oil.partner.modules.profit.presenter;

import com.yltx.oil.partner.modules.profit.domain.FragmentProfit_yjjsjl_UseCase;
import com.yltx.oil.partner.mvp.controller.PageLimitPresenter;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class FragmentProfit_yjjsjl_Presenter extends PageLimitPresenter {
    private FragmentProfit_yjjsjl_UseCase useCase;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public FragmentProfit_yjjsjl_Presenter(FragmentProfit_yjjsjl_UseCase fragmentProfit_yjjsjl_UseCase) {
        this.useCase = fragmentProfit_yjjsjl_UseCase;
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