package com.yltx.oil.partner.modules.profit.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.BankInfoResp;
import com.yltx.oil.partner.modules.profit.domain.TxUseCase;
import com.yltx.oil.partner.modules.profit.view.TxModificationView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class ModificationPresenter implements Presenter {
    private TxUseCase mUseCase;
    private TxModificationView mView;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public ModificationPresenter(TxUseCase txUseCase) {
        this.mUseCase = txUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.mUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.mView = (TxModificationView) interfaceView;
    }

    public void getBankCard() {
        this.mUseCase.execute(new ProgressSubscriber<HttpResult<BankInfoResp>>(this.mView) { // from class: com.yltx.oil.partner.modules.profit.presenter.ModificationPresenter.1
            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
            public void onError(Throwable th) {
                super.onError(th);
                ModificationPresenter.this.mView.applyFailed();
            }

            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
            public void onNext(HttpResult<BankInfoResp> httpResult) {
                super.onNext((AnonymousClass1) httpResult);
                ModificationPresenter.this.mView.applySuccess(httpResult);
            }
        });
    }
}