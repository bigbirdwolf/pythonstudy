package com.yltx.oil.partner.modules.home.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.BannerResponse;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.home.domain.BannerUseCase;
import com.yltx.oil.partner.modules.home.view.BannerView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import com.yltx.oil.partner.mvp.views.LoadDataView;
import java.util.List;
import javax.inject.Inject;

@ActivityScope
/* loaded from: classes.dex */
public class BannerPresenter implements Presenter {
    private BannerUseCase bannerUseCase;
    private BannerView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public BannerPresenter(BannerUseCase bannerUseCase) {
        this.bannerUseCase = bannerUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.bannerUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (BannerView) interfaceView;
    }

    public void submitBanner() {
        this.bannerUseCase.execute(new BannerSubscriber(this.view));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class BannerSubscriber extends ProgressSubscriber<HttpResult<List<BannerResponse>>> {
        public BannerSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            BannerPresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<List<BannerResponse>> httpResult) {
            super.onNext((BannerSubscriber) httpResult);
            BannerPresenter.this.view.onBanner(httpResult);
        }
    }
}