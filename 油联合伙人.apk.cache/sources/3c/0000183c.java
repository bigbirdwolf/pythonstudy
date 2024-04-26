package com.yltx.oil.partner.modules.main.presenter;

import com.yltx.oil.partner.base.PartnerApplication;
import com.yltx.oil.partner.data.response.VersionResponse;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.main.domian.CheckVersionUseCase;
import com.yltx.oil.partner.modules.main.view.SplashView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import com.yltx.oil.partner.utils.AndroidUtil;
import javax.inject.Inject;

@ActivityScope
/* loaded from: classes.dex */
public class SplashPresenter implements Presenter {
    private CheckVersionUseCase mCheckVersionUseCase;
    private SplashView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public SplashPresenter(CheckVersionUseCase checkVersionUseCase) {
        this.mCheckVersionUseCase = checkVersionUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.mCheckVersionUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (SplashView) interfaceView;
    }

    public void CheckVersionUseCase() {
        AndroidUtil.getAppVersionCode(PartnerApplication.mContext);
        this.mCheckVersionUseCase.setPlatform("android");
        this.mCheckVersionUseCase.execute(new ProgressSubscriber<VersionResponse>(this.view) { // from class: com.yltx.oil.partner.modules.main.presenter.SplashPresenter.1
            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
            public void onError(Throwable th) {
                super.onError(th);
                SplashPresenter.this.view.onCheckVersiononError(th);
            }

            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
            public void onNext(VersionResponse versionResponse) {
                super.onNext((AnonymousClass1) versionResponse);
                SplashPresenter.this.view.onCheckVersionSuccess(versionResponse);
            }
        });
    }
}