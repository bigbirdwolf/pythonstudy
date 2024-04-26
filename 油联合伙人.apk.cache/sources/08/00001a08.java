package com.yltx.oil.partner.modules.profit.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.profit.domain.BindingbankcardsUseCase;
import com.yltx.oil.partner.modules.profit.domain.UpBankcardsUseCase;
import com.yltx.oil.partner.modules.profit.view.BindingBankView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import com.yltx.oil.partner.mvp.views.LoadDataView;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class BindingBankPresenter implements Presenter {
    private BindingbankcardsUseCase bindingbankcardsUseCase;
    private UpBankcardsUseCase mUpBankcardsUseCase;
    private BindingBankView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public BindingBankPresenter(BindingbankcardsUseCase bindingbankcardsUseCase, UpBankcardsUseCase upBankcardsUseCase) {
        this.bindingbankcardsUseCase = bindingbankcardsUseCase;
        this.mUpBankcardsUseCase = upBankcardsUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.bindingbankcardsUseCase.unSubscribe();
        this.mUpBankcardsUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (BindingBankView) interfaceView;
    }

    public void uPBankcards(String str, String str2, String str3, String str4, String str5) {
        this.mUpBankcardsUseCase.setRealname(str);
        this.mUpBankcardsUseCase.setIdcard(str2);
        this.mUpBankcardsUseCase.setBankNo(str3);
        this.mUpBankcardsUseCase.setBankPhone(str4);
        this.mUpBankcardsUseCase.setValidCode(str5);
        this.mUpBankcardsUseCase.execute(new ProgressSubscriber<HttpResult<String>>(this.view) { // from class: com.yltx.oil.partner.modules.profit.presenter.BindingBankPresenter.1
            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
            public void onError(Throwable th) {
                super.onError(th);
                BindingBankPresenter.this.view.onError(th);
            }

            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
            public void onNext(HttpResult<String> httpResult) {
                super.onNext((AnonymousClass1) httpResult);
                BindingBankPresenter.this.view.onUpBindingBank(httpResult);
            }
        });
    }

    public void getBindingBank(String str, String str2, String str3, String str4, String str5) {
        this.bindingbankcardsUseCase.setRealname(str);
        this.bindingbankcardsUseCase.setIdcard(str2);
        this.bindingbankcardsUseCase.setBankNo(str3);
        this.bindingbankcardsUseCase.setBankPhone(str4);
        this.bindingbankcardsUseCase.setValidCode(str5);
        this.bindingbankcardsUseCase.execute(new BindingBankSubscriber(this.view));
    }

    /* loaded from: classes.dex */
    private class BindingBankSubscriber extends ProgressSubscriber<HttpResult<String>> {
        public BindingBankSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            BindingBankPresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<String> httpResult) {
            super.onNext((BindingBankSubscriber) httpResult);
            BindingBankPresenter.this.view.onBindingBank(httpResult);
        }
    }
}