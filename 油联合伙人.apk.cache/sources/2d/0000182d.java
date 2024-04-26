package com.yltx.oil.partner.modules.login.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.LoginInfo;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.login.domain.GetImgCodeUseCase;
import com.yltx.oil.partner.modules.login.domain.RegisterUseCase;
import com.yltx.oil.partner.modules.login.view.RegisterView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import com.yltx.oil.partner.mvp.views.LoadDataView;
import com.yltx.oil.partner.utils.Md5;
import javax.inject.Inject;

@ActivityScope
/* loaded from: classes.dex */
public class RegisterPresenter implements Presenter {
    private GetImgCodeUseCase getImgCodeUseCase;
    private RegisterUseCase registerUseCase;
    private RegisterView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public RegisterPresenter(RegisterUseCase registerUseCase, GetImgCodeUseCase getImgCodeUseCase) {
        this.registerUseCase = registerUseCase;
        this.getImgCodeUseCase = getImgCodeUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.registerUseCase.unSubscribe();
        this.getImgCodeUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (RegisterView) interfaceView;
    }

    public void submitRegist(String str, String str2, String str3) {
        this.registerUseCase.setPhone(str);
        this.registerUseCase.setValidCode(str2);
        this.registerUseCase.setPassword(Md5.md5(str3));
        this.registerUseCase.execute(new RegistSubscriber(this.view));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class RegistSubscriber extends ProgressSubscriber<HttpResult<LoginInfo>> {
        public RegistSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<LoginInfo> httpResult) {
            super.onNext((RegistSubscriber) httpResult);
            RegisterPresenter.this.view.onRegisterSuccess(httpResult);
        }
    }

    public void getImgCode() {
        this.getImgCodeUseCase.execute(new GetImgCodeSubscriber(this.view));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class GetImgCodeSubscriber extends ProgressSubscriber<String> {
        public GetImgCodeSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(String str) {
            super.onNext((GetImgCodeSubscriber) str);
            RegisterPresenter.this.view.getImgCode(str);
        }
    }
}