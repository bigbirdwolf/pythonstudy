package com.yltx.oil.partner.modules.mine.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.mine.domain.ComplaintUseCase;
import com.yltx.oil.partner.modules.mine.view.ComplainView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import com.yltx.oil.partner.mvp.views.LoadDataView;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class ComplaintPresenter implements Presenter {
    private ComplaintUseCase complaintUseCase;
    private ComplainView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public ComplaintPresenter(ComplaintUseCase complaintUseCase) {
        this.complaintUseCase = complaintUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.complaintUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (ComplainView) interfaceView;
    }

    public void submitComplaint(String str, String str2, String str3, String str4, String str5, String str6) {
        this.complaintUseCase.setPhone(str);
        this.complaintUseCase.setContext(str2);
        this.complaintUseCase.setReason(str3);
        this.complaintUseCase.setUrl(str4);
        this.complaintUseCase.setVoucherCode(str5);
        this.complaintUseCase.setName(str6);
        this.complaintUseCase.execute(new ComplaintSubscriber(this.view));
    }

    /* loaded from: classes.dex */
    private class ComplaintSubscriber extends ProgressSubscriber<HttpResult<String>> {
        public ComplaintSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            ComplaintPresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<String> httpResult) {
            super.onNext((ComplaintSubscriber) httpResult);
            ComplaintPresenter.this.view.onComplain(httpResult);
        }
    }
}