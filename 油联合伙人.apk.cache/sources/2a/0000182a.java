package com.yltx.oil.partner.modules.login.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.LoginInfo;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.login.domain.AutoUseCase;
import com.yltx.oil.partner.modules.login.domain.LoginSmUseCase;
import com.yltx.oil.partner.modules.login.domain.LoginUseCase;
import com.yltx.oil.partner.modules.login.view.LoginView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import com.yltx.oil.partner.mvp.views.LoadDataView;
import com.yltx.oil.partner.utils.Md5;
import javax.inject.Inject;

@ActivityScope
/* loaded from: classes.dex */
public class LoginPresenter implements Presenter {
    private AutoUseCase autoUseCase;
    private LoginSmUseCase loginSmUseCase;
    private LoginUseCase mLoginUseCase;
    private LoginView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public LoginPresenter(LoginUseCase loginUseCase, LoginSmUseCase loginSmUseCase, AutoUseCase autoUseCase) {
        this.mLoginUseCase = loginUseCase;
        this.loginSmUseCase = loginSmUseCase;
        this.autoUseCase = autoUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.mLoginUseCase.unSubscribe();
        this.loginSmUseCase.unSubscribe();
        this.autoUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (LoginView) interfaceView;
    }

    public void Login(String str, String str2) {
        this.autoUseCase.setToken(str);
        this.autoUseCase.setUserid(str2);
        this.autoUseCase.execute(new LoginSubscriber(this.view));
    }

    public void submitLogin(String str, String str2) {
        this.mLoginUseCase.setName(str);
        this.mLoginUseCase.setPwd(Md5.md5(str2));
        this.mLoginUseCase.execute(new LoginSubscriber(this.view));
    }

    public void submitLoginsm(String str, String str2) {
        this.loginSmUseCase.setName(str);
        this.loginSmUseCase.setValidCode(str2);
        this.loginSmUseCase.execute(new LoginSubscriber(this.view));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class LoginSubscriber extends ProgressSubscriber<HttpResult<LoginInfo>> {
        public LoginSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            LoginPresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<LoginInfo> httpResult) {
            super.onNext((LoginSubscriber) httpResult);
            LoginPresenter.this.view.onLoginSuccess(httpResult);
        }
    }
}