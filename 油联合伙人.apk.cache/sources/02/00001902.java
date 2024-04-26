package com.yltx.oil.partner.modules.mine.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.mine.domain.ModifUseCase;
import com.yltx.oil.partner.modules.mine.view.ModifyusernicknamesView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import com.yltx.oil.partner.mvp.views.LoadDataView;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class ModifPresenter implements Presenter {
    private ModifUseCase modifUseCase;
    private ModifyusernicknamesView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public ModifPresenter(ModifUseCase modifUseCase) {
        this.modifUseCase = modifUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.modifUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (ModifyusernicknamesView) interfaceView;
    }

    public void submitMember(String str) {
        this.modifUseCase.setNickName(str);
        this.modifUseCase.execute(new MemberSubscriber(this.view));
    }

    /* loaded from: classes.dex */
    private class MemberSubscriber extends ProgressSubscriber<HttpResult<String>> {
        public MemberSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            ModifPresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<String> httpResult) {
            super.onNext((MemberSubscriber) httpResult);
            ModifPresenter.this.view.onModif(httpResult);
        }
    }
}