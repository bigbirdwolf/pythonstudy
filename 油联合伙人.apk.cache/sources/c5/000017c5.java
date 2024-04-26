package com.yltx.oil.partner.modules.home.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.home.domain.SSLSUseCase;
import com.yltx.oil.partner.modules.home.response.SSLSResponse;
import com.yltx.oil.partner.modules.home.view.SSLSView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import com.yltx.oil.partner.mvp.views.LoadDataView;
import java.util.List;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class SSLSPresenter implements Presenter {
    private SSLSUseCase sslsUseCase;
    private SSLSView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public SSLSPresenter(SSLSUseCase sSLSUseCase) {
        this.sslsUseCase = sSLSUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.sslsUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (SSLSView) interfaceView;
    }

    public void messagesubmit() {
        this.sslsUseCase.execute(new SSLSSubscriber(this.view));
    }

    /* loaded from: classes.dex */
    private class SSLSSubscriber extends ProgressSubscriber<HttpResult<List<SSLSResponse>>> {
        public SSLSSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            SSLSPresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<List<SSLSResponse>> httpResult) {
            super.onNext((SSLSSubscriber) httpResult);
            SSLSPresenter.this.view.onSSLS(httpResult);
        }
    }
}