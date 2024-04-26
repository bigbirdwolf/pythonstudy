package com.yltx.oil.partner.modules.oiltrade.presenter;

import com.yltx.oil.partner.modules.oiltrade.domain.StoredValueCardUseCase;
import com.yltx.oil.partner.mvp.controller.PageLimitPresenter;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class StoredValueCardPresenter extends PageLimitPresenter {
    private StoredValueCardUseCase mStoredValueCardUseCase;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public StoredValueCardPresenter(StoredValueCardUseCase storedValueCardUseCase) {
        this.mStoredValueCardUseCase = storedValueCardUseCase;
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