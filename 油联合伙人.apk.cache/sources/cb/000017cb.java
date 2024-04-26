package com.yltx.oil.partner.modules.home.presenter;

import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.home.domain.ShortUseCase;
import com.yltx.oil.partner.modules.home.view.ShareDetailView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import javax.inject.Inject;

@ActivityScope
/* loaded from: classes.dex */
public class ShareDetailPresenter implements Presenter {
    private ShortUseCase bannerUseCase;
    private ShareDetailView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public ShareDetailPresenter(ShortUseCase shortUseCase) {
        this.bannerUseCase = shortUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.bannerUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (ShareDetailView) interfaceView;
    }
}