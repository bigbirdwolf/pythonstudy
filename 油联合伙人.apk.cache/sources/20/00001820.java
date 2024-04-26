package com.yltx.oil.partner.modules.login.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.AppLoginStatusResp;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.login.domain.AppLoginStatusUseCase;
import com.yltx.oil.partner.modules.login.view.AppLoginStatusView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import com.yltx.oil.partner.mvp.views.LoadDataView;
import javax.inject.Inject;

@ActivityScope
/* loaded from: classes.dex */
public class AppLoginStatusPresenter implements Presenter {
    private AppLoginStatusUseCase mLoginUseCase;
    private AppLoginStatusView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public AppLoginStatusPresenter(AppLoginStatusUseCase appLoginStatusUseCase) {
        this.mLoginUseCase = appLoginStatusUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.mLoginUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (AppLoginStatusView) interfaceView;
    }

    public void submitLogin() {
        this.mLoginUseCase.execute(new LoginSubscriber(this.view));
    }

    /* loaded from: classes.dex */
    private class LoginSubscriber extends ProgressSubscriber<HttpResult<AppLoginStatusResp>> {
        public LoginSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            AppLoginStatusPresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<AppLoginStatusResp> httpResult) {
            super.onNext((LoginSubscriber) httpResult);
            AppLoginStatusPresenter.this.view.onStatusLoginSuccess(httpResult);
        }
    }
}