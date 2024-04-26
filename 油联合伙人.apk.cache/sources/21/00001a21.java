package com.yltx.oil.partner.modules.profit.presenter;

import com.yltx.oil.partner.modules.profit.domain.UserAccountUseCase;
import com.yltx.oil.partner.modules.profit.response.UserAccountConsumeResponse;
import com.yltx.oil.partner.mvp.controller.PageLimitPresenter;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class UserAccountPresenter extends PageLimitPresenter<UserAccountConsumeResponse> {
    private UserAccountUseCase mStoredValueCardUseCase;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public UserAccountPresenter(UserAccountUseCase userAccountUseCase) {
        this.mStoredValueCardUseCase = userAccountUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.PageLimitPresenter
    protected UseCase<UserAccountConsumeResponse> buildPageUseCase(int i, int i2) {
        this.mStoredValueCardUseCase.setPageOffset(i);
        return this.mStoredValueCardUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.mStoredValueCardUseCase.unSubscribe();
    }
}