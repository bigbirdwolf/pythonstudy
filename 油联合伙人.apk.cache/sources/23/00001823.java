package com.yltx.oil.partner.modules.login.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.login.domain.FindpwUseCase;
import com.yltx.oil.partner.modules.login.domain.GetvalidCodeUseCase;
import com.yltx.oil.partner.modules.login.view.ForgetPwdView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import com.yltx.oil.partner.utils.Md5;
import javax.inject.Inject;

@ActivityScope
/* loaded from: classes.dex */
public class ForgetPwdPresenter implements Presenter {
    private FindpwUseCase mFindpwUseCase;
    private GetvalidCodeUseCase mGetvalidCodeUseCase;
    private ForgetPwdView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public ForgetPwdPresenter(FindpwUseCase findpwUseCase, GetvalidCodeUseCase getvalidCodeUseCase) {
        this.mFindpwUseCase = findpwUseCase;
        this.mGetvalidCodeUseCase = getvalidCodeUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.mFindpwUseCase.unSubscribe();
        this.mGetvalidCodeUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (ForgetPwdView) interfaceView;
    }

    public void forgetPwd(String str, String str2, String str3) {
        this.mFindpwUseCase.setName(str);
        this.mFindpwUseCase.setPwd(Md5.md5(str2));
        this.mFindpwUseCase.setValidCode(str3);
        this.mFindpwUseCase.execute(new ProgressSubscriber<HttpResult<String>>(this.view) { // from class: com.yltx.oil.partner.modules.login.presenter.ForgetPwdPresenter.1
            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
            public void onError(Throwable th) {
                super.onError(th);
                ForgetPwdPresenter.this.view.onError(th);
            }

            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
            public void onNext(HttpResult<String> httpResult) {
                super.onNext((AnonymousClass1) httpResult);
                ForgetPwdPresenter.this.view.onSuccess();
            }
        });
    }

    public void getVcode(String str, String str2) {
        this.mGetvalidCodeUseCase.setName(str);
        this.mGetvalidCodeUseCase.setLoginOrRegister(str2);
        this.mGetvalidCodeUseCase.execute(new ProgressSubscriber<HttpResult<String>>(this.view) { // from class: com.yltx.oil.partner.modules.login.presenter.ForgetPwdPresenter.2
            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
            public void onError(Throwable th) {
                super.onError(th);
                ForgetPwdPresenter.this.view.onError(th);
            }

            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
            public void onNext(HttpResult<String> httpResult) {
                super.onNext((AnonymousClass2) httpResult);
                ForgetPwdPresenter.this.view.onVcodeSuccess();
            }
        });
    }
}