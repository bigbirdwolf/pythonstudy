package com.yltx.oil.partner.modules.mine.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.InviteDetailResp;
import com.yltx.oil.partner.modules.mine.domain.DetailUseCase;
import com.yltx.oil.partner.modules.mine.view.InviteDetailView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class InviteDetailPresenter implements Presenter {
    private DetailUseCase mDetailUseCase;
    private InviteDetailView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public InviteDetailPresenter(DetailUseCase detailUseCase) {
        this.mDetailUseCase = detailUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.mDetailUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (InviteDetailView) interfaceView;
    }

    public void getDetail(String str) {
        this.mDetailUseCase.setUserId(str);
        this.mDetailUseCase.execute(new ProgressSubscriber<HttpResult<InviteDetailResp>>(this.view) { // from class: com.yltx.oil.partner.modules.mine.presenter.InviteDetailPresenter.1
            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
            public void onError(Throwable th) {
                super.onError(th);
                InviteDetailPresenter.this.view.onError(th);
            }

            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
            public void onNext(HttpResult<InviteDetailResp> httpResult) {
                super.onNext((AnonymousClass1) httpResult);
                InviteDetailPresenter.this.view.onSuccess(httpResult);
            }
        });
    }
}