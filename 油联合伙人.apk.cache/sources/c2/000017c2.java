package com.yltx.oil.partner.modules.home.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.home.domain.SCUseCase;
import com.yltx.oil.partner.modules.home.response.SCResponse;
import com.yltx.oil.partner.modules.home.view.SCView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import com.yltx.oil.partner.mvp.views.LoadDataView;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class SCPresenter implements Presenter {
    private SCUseCase scUseCase;
    private SCView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public SCPresenter(SCUseCase sCUseCase) {
        this.scUseCase = sCUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.scUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (SCView) interfaceView;
    }

    public void messagesubmit() {
        this.scUseCase.execute(new SSLSSubscriber(this.view));
    }

    /* loaded from: classes.dex */
    private class SSLSSubscriber extends ProgressSubscriber<HttpResult<SCResponse>> {
        public SSLSSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            SCPresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<SCResponse> httpResult) {
            super.onNext((SSLSSubscriber) httpResult);
            SCPresenter.this.view.onSC(httpResult);
        }
    }
}