package com.yltx.oil.partner.modules.profit.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.profit.domain.CommissionUseCase;
import com.yltx.oil.partner.modules.profit.response.CommissionResponse;
import com.yltx.oil.partner.modules.profit.view.CommissionView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import com.yltx.oil.partner.mvp.views.LoadDataView;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class CommissionPresenter implements Presenter {
    private CommissionUseCase commissionUseCase;
    private CommissionView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public CommissionPresenter(CommissionUseCase commissionUseCase) {
        this.commissionUseCase = commissionUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.commissionUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (CommissionView) interfaceView;
    }

    public void CommissionMember(String str) {
        this.commissionUseCase.setButton(str);
        this.commissionUseCase.execute(new CommissionSubscriber(this.view));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CommissionSubscriber extends ProgressSubscriber<HttpResult<CommissionResponse>> {
        public CommissionSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            CommissionPresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<CommissionResponse> httpResult) {
            super.onNext((CommissionSubscriber) httpResult);
            CommissionPresenter.this.view.onMember(httpResult);
        }
    }
}