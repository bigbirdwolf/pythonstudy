package com.yltx.oil.partner.modules.mine.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.InviteResp;
import com.yltx.oil.partner.modules.mine.domain.TotalUseCase;
import com.yltx.oil.partner.modules.mine.view.InviteView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class InvitePresenter implements Presenter {
    private TotalUseCase totalUseCase;
    private InviteView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public InvitePresenter(TotalUseCase totalUseCase) {
        this.totalUseCase = totalUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.totalUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (InviteView) interfaceView;
    }

    public void getTotal(String str) {
        this.totalUseCase.setUserId(str);
        this.totalUseCase.execute(new ProgressSubscriber<HttpResult<InviteResp>>(this.view) { // from class: com.yltx.oil.partner.modules.mine.presenter.InvitePresenter.1
            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
            public void onError(Throwable th) {
                super.onError(th);
                InvitePresenter.this.view.onError(th);
            }

            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
            public void onNext(HttpResult<InviteResp> httpResult) {
                super.onNext((AnonymousClass1) httpResult);
                InvitePresenter.this.view.onSuccess(httpResult);
            }
        });
    }
}