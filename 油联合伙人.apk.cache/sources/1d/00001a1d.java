package com.yltx.oil.partner.modules.profit.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.BankInfoResp;
import com.yltx.oil.partner.modules.profit.domain.TxApplyUseCase;
import com.yltx.oil.partner.modules.profit.domain.TxUseCase;
import com.yltx.oil.partner.modules.profit.view.TxView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class TxPresenter implements Presenter {
    private TxApplyUseCase mApplyUseCase;
    private TxUseCase mUseCase;
    private TxView mView;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public TxPresenter(TxUseCase txUseCase, TxApplyUseCase txApplyUseCase) {
        this.mUseCase = txUseCase;
        this.mApplyUseCase = txApplyUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.mUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.mView = (TxView) interfaceView;
    }

    public void TxApply(String str, String str2) {
        this.mApplyUseCase.setTxMoney(str);
        this.mApplyUseCase.setAccountPwd(str2);
        this.mApplyUseCase.execute(new ProgressSubscriber<HttpResult<String>>(this.mView) { // from class: com.yltx.oil.partner.modules.profit.presenter.TxPresenter.1
            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
            public void onError(Throwable th) {
                super.onError(th);
            }

            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
            public void onNext(HttpResult<String> httpResult) {
                super.onNext((AnonymousClass1) httpResult);
                TxPresenter.this.mView.onApplySuccess();
            }
        });
    }

    public void getBankCard() {
        this.mUseCase.execute(new ProgressSubscriber<HttpResult<BankInfoResp>>(this.mView) { // from class: com.yltx.oil.partner.modules.profit.presenter.TxPresenter.2
            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
            public void onError(Throwable th) {
                super.onError(th);
                TxPresenter.this.mView.applyFailed();
            }

            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
            public void onNext(HttpResult<BankInfoResp> httpResult) {
                super.onNext((AnonymousClass2) httpResult);
                TxPresenter.this.mView.applySuccess(httpResult);
            }
        });
    }
}