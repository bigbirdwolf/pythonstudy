package com.yltx.oil.partner.modules.mine.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.mine.domain.MineinfoHeadPicUseCase;
import com.yltx.oil.partner.modules.mine.view.MineInfoView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class MineInfoPresenter implements Presenter {
    private MineinfoHeadPicUseCase mMineinfoHeadPicUseCase;
    private MineInfoView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public MineInfoPresenter(MineinfoHeadPicUseCase mineinfoHeadPicUseCase) {
        this.mMineinfoHeadPicUseCase = mineinfoHeadPicUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.mMineinfoHeadPicUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (MineInfoView) interfaceView;
    }

    public void submitHeadPic(String str) {
        this.mMineinfoHeadPicUseCase.setPicUrl(str);
        this.mMineinfoHeadPicUseCase.execute(new ProgressSubscriber<HttpResult<String>>(this.view) { // from class: com.yltx.oil.partner.modules.mine.presenter.MineInfoPresenter.1
            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
            public void onError(Throwable th) {
                super.onError(th);
            }

            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
            public void onNext(HttpResult<String> httpResult) {
                super.onNext((AnonymousClass1) httpResult);
                MineInfoPresenter.this.view.onSuccess(httpResult);
            }
        });
    }
}