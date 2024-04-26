package com.yltx.oil.partner.modules.home.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.Homebuttonconfiguration_Bean;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.home.domain.HomeButtonconfigurationUseCase;
import com.yltx.oil.partner.modules.home.view.HomeButtonconfigurationView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import com.yltx.oil.partner.mvp.views.LoadDataView;
import java.util.List;
import javax.inject.Inject;

@ActivityScope
/* loaded from: classes.dex */
public class HomeButtonconfigurationPresenter implements Presenter {
    private HomeButtonconfigurationUseCase homeButtonconfigurationUseCase;
    private HomeButtonconfigurationView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public HomeButtonconfigurationPresenter(HomeButtonconfigurationUseCase homeButtonconfigurationUseCase) {
        this.homeButtonconfigurationUseCase = homeButtonconfigurationUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.homeButtonconfigurationUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (HomeButtonconfigurationView) interfaceView;
    }

    public void submitHomeButton() {
        this.homeButtonconfigurationUseCase.execute(new HomeButtonSubscriber(this.view));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class HomeButtonSubscriber extends ProgressSubscriber<HttpResult<List<Homebuttonconfiguration_Bean>>> {
        public HomeButtonSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            HomeButtonconfigurationPresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<List<Homebuttonconfiguration_Bean>> httpResult) {
            super.onNext((HomeButtonSubscriber) httpResult);
            HomeButtonconfigurationPresenter.this.view.onHomeButtonconfiguration(httpResult);
        }
    }
}