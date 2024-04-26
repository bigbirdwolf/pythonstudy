package com.yltx.oil.partner.modules.profit.presenter;

import com.yltx.oil.partner.modules.profit.domain.AllordersUseCase;
import com.yltx.oil.partner.mvp.controller.PageLimitPresenter;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class AllordersPresenter extends PageLimitPresenter {
    private AllordersUseCase allordersUseCase;
    private String status;
    private String type;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    @Inject
    public AllordersPresenter(AllordersUseCase allordersUseCase) {
        this.allordersUseCase = allordersUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.PageLimitPresenter
    protected UseCase buildPageUseCase(int i, int i2) {
        this.allordersUseCase.setPageOffset(i);
        this.allordersUseCase.setType(this.type);
        this.allordersUseCase.setStatus(this.status);
        return this.allordersUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.allordersUseCase.unSubscribe();
    }
}