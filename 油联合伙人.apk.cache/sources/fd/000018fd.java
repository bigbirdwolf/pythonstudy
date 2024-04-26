package com.yltx.oil.partner.modules.mine.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.login.domain.GetvalidCodeUseCase;
import com.yltx.oil.partner.modules.mine.domain.MineinfoOldPhoneUseCase;
import com.yltx.oil.partner.modules.mine.domain.MineinfoUpdatePhoneUseCase;
import com.yltx.oil.partner.modules.mine.view.MineInfoPhoneView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class MinePhonePresenter implements Presenter {
    private GetvalidCodeUseCase mGetvalidCodeUseCase;
    private MineinfoOldPhoneUseCase mMineinfoOldPhoneUseCase;
    private MineinfoUpdatePhoneUseCase mMineinfoUpdatePhoneUseCase;
    private MineInfoPhoneView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public MinePhonePresenter(MineinfoOldPhoneUseCase mineinfoOldPhoneUseCase, MineinfoUpdatePhoneUseCase mineinfoUpdatePhoneUseCase, GetvalidCodeUseCase getvalidCodeUseCase) {
        this.mMineinfoOldPhoneUseCase = mineinfoOldPhoneUseCase;
        this.mMineinfoUpdatePhoneUseCase = mineinfoUpdatePhoneUseCase;
        this.mGetvalidCodeUseCase = getvalidCodeUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.mMineinfoOldPhoneUseCase.unSubscribe();
        this.mMineinfoUpdatePhoneUseCase.unSubscribe();
        this.mGetvalidCodeUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (MineInfoPhoneView) interfaceView;
    }

    public void checkValidCode(String str, String str2) {
        this.mMineinfoOldPhoneUseCase.setPhone(str);
        this.mMineinfoOldPhoneUseCase.setValidCode(str2);
        this.mMineinfoOldPhoneUseCase.execute(new ProgressSubscriber<HttpResult<String>>(this.view) { // from class: com.yltx.oil.partner.modules.mine.presenter.MinePhonePresenter.1
            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
            public void onError(Throwable th) {
                super.onError(th);
                MinePhonePresenter.this.view.onError(th);
            }

            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
            public void onNext(HttpResult<String> httpResult) {
                super.onNext((AnonymousClass1) httpResult);
                MinePhonePresenter.this.view.onSuccess(httpResult);
            }
        });
    }

    public void updatePhone(String str, String str2) {
        this.mMineinfoUpdatePhoneUseCase.setPhone(str);
        this.mMineinfoUpdatePhoneUseCase.setValidCode(str2);
        this.mMineinfoUpdatePhoneUseCase.execute(new ProgressSubscriber<HttpResult<String>>(this.view) { // from class: com.yltx.oil.partner.modules.mine.presenter.MinePhonePresenter.2
            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
            public void onError(Throwable th) {
                super.onError(th);
                MinePhonePresenter.this.view.onError(th);
            }

            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
            public void onNext(HttpResult<String> httpResult) {
                super.onNext((AnonymousClass2) httpResult);
                MinePhonePresenter.this.view.onUpSuccess(httpResult);
            }
        });
    }

    public void getVCode(String str, String str2) {
        this.mGetvalidCodeUseCase.setName(str);
        this.mGetvalidCodeUseCase.setLoginOrRegister(str2);
        this.mGetvalidCodeUseCase.execute(new ProgressSubscriber<HttpResult<String>>(this.view) { // from class: com.yltx.oil.partner.modules.mine.presenter.MinePhonePresenter.3
            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
            public void onError(Throwable th) {
                super.onError(th);
                MinePhonePresenter.this.view.onError(th);
            }

            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
            public void onNext(HttpResult<String> httpResult) {
                super.onNext((AnonymousClass3) httpResult);
                MinePhonePresenter.this.view.onSmsSuccess(httpResult);
            }
        });
    }
}