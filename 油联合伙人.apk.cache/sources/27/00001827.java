package com.yltx.oil.partner.modules.login.presenter;

import android.text.TextUtils;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.login.domain.GetvalidCodeUseCase;
import com.yltx.oil.partner.modules.login.view.GetValidCodeView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import com.yltx.oil.partner.mvp.views.LoadDataView;
import javax.inject.Inject;

@ActivityScope
/* loaded from: classes.dex */
public class GetValidCodePresenter implements Presenter {
    private GetvalidCodeUseCase getvalidCodeUseCase;
    private GetValidCodeView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public GetValidCodePresenter(GetvalidCodeUseCase getvalidCodeUseCase) {
        this.getvalidCodeUseCase = getvalidCodeUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.getvalidCodeUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (GetValidCodeView) interfaceView;
    }

    public void submitLoginsm(String str, String str2, String str3, String str4) {
        this.getvalidCodeUseCase.setName(str);
        this.getvalidCodeUseCase.setLoginOrRegister(str2);
        if (!TextUtils.isEmpty(str4)) {
            this.getvalidCodeUseCase.setImage(str3);
        } else {
            this.getvalidCodeUseCase.setImage("");
        }
        this.getvalidCodeUseCase.execute(new LoginSubscriber(this.view));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class LoginSubscriber extends ProgressSubscriber<HttpResult<String>> {
        public LoginSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<String> httpResult) {
            super.onNext((LoginSubscriber) httpResult);
            GetValidCodePresenter.this.view.onsmsSuccess(httpResult);
        }
    }
}