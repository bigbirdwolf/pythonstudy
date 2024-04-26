package com.yltx.oil.partner.modules.profit.presenter;

import com.yltx.oil.partner.modules.profit.domain.GeneralizeUseCase;
import com.yltx.oil.partner.mvp.controller.PageLimitPresenter;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class GeneralizePresenter extends PageLimitPresenter {
    private String beginTime;
    private String endTime;
    private GeneralizeUseCase generalizeUseCase;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    public String getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(String str) {
        this.beginTime = str;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String str) {
        this.endTime = str;
    }

    @Inject
    public GeneralizePresenter(GeneralizeUseCase generalizeUseCase) {
        this.generalizeUseCase = generalizeUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.generalizeUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.PageLimitPresenter
    protected UseCase buildPageUseCase(int i, int i2) {
        this.generalizeUseCase.setPageOffset(i);
        this.generalizeUseCase.setBeginTime(this.beginTime);
        this.generalizeUseCase.setEndTime(this.endTime);
        return this.generalizeUseCase;
    }
}