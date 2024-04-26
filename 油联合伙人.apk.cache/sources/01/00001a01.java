package com.yltx.oil.partner.modules.profit.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.profit.domain.AccountBalanceUseCase;
import com.yltx.oil.partner.modules.profit.domain.IsAuthUseCase;
import com.yltx.oil.partner.modules.profit.domain.ManagerYZJYFXUseCase;
import com.yltx.oil.partner.modules.profit.response.ManageResponse;
import com.yltx.oil.partner.modules.profit.view.AccountBalanceView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import com.yltx.oil.partner.mvp.views.LoadDataView;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class AccountBalancePresenter implements Presenter {
    private AccountBalanceUseCase accountBalanceUseCase;
    private IsAuthUseCase isAuthUseCase;
    private ManagerYZJYFXUseCase managerYZJYFXUseCase;
    private AccountBalanceView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public AccountBalancePresenter(AccountBalanceUseCase accountBalanceUseCase, IsAuthUseCase isAuthUseCase, ManagerYZJYFXUseCase managerYZJYFXUseCase) {
        this.accountBalanceUseCase = accountBalanceUseCase;
        this.isAuthUseCase = isAuthUseCase;
        this.managerYZJYFXUseCase = managerYZJYFXUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.accountBalanceUseCase.unSubscribe();
        this.isAuthUseCase.unSubscribe();
        this.managerYZJYFXUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (AccountBalanceView) interfaceView;
    }

    public void getAccountBalance() {
        this.accountBalanceUseCase.execute(new AccountBalanceSubscriber(this.view));
    }

    public void getIsAuth() {
        this.isAuthUseCase.execute(new IsAuthSubscriber(this.view));
    }

    public void getYZJYSJFX() {
        this.managerYZJYFXUseCase.setPageNo("1");
        this.managerYZJYFXUseCase.execute(new YZJYSJFXSubscriber(this.view));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class YZJYSJFXSubscriber extends ProgressSubscriber<HttpResult<ManageResponse>> {
        public YZJYSJFXSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            AccountBalancePresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<ManageResponse> httpResult) {
            super.onNext((YZJYSJFXSubscriber) httpResult);
            AccountBalancePresenter.this.view.onyzjxsjfx(httpResult);
        }
    }

    /* loaded from: classes.dex */
    private class IsAuthSubscriber extends ProgressSubscriber<HttpResult<String>> {
        public IsAuthSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            AccountBalancePresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<String> httpResult) {
            super.onNext((IsAuthSubscriber) httpResult);
            AccountBalancePresenter.this.view.onIsAuth(httpResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class AccountBalanceSubscriber extends ProgressSubscriber<HttpResult<String>> {
        public AccountBalanceSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            AccountBalancePresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<String> httpResult) {
            super.onNext((AccountBalanceSubscriber) httpResult);
            AccountBalancePresenter.this.view.onAccountBalance(httpResult);
        }
    }
}