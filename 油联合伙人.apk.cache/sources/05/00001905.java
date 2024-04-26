package com.yltx.oil.partner.modules.mine.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.mine.domain.MyfeedbackSubmintUseCase;
import com.yltx.oil.partner.modules.mine.domain.MyfeedbackUseCase;
import com.yltx.oil.partner.modules.mine.response.MyfeedbackResponse;
import com.yltx.oil.partner.modules.mine.view.MyfeedbackView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import com.yltx.oil.partner.mvp.views.LoadDataView;
import java.util.List;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class MyfeedbackPresenter implements Presenter {
    private MyfeedbackSubmintUseCase myfeedbackSubmintUseCase;
    private MyfeedbackUseCase myfeedbackUseCase;
    private MyfeedbackView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public MyfeedbackPresenter(MyfeedbackUseCase myfeedbackUseCase, MyfeedbackSubmintUseCase myfeedbackSubmintUseCase) {
        this.myfeedbackUseCase = myfeedbackUseCase;
        this.myfeedbackSubmintUseCase = myfeedbackSubmintUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.myfeedbackUseCase.unSubscribe();
        this.myfeedbackSubmintUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (MyfeedbackView) interfaceView;
    }

    public void feedbackMember() {
        this.myfeedbackUseCase.execute(new MemberSubscriber(this.view));
    }

    public void feedbacksubmit(String str, String str2, String str3) {
        this.myfeedbackSubmintUseCase.setContent(str);
        this.myfeedbackSubmintUseCase.setPhone(str2);
        this.myfeedbackSubmintUseCase.setName(str3);
        this.myfeedbackSubmintUseCase.execute(new FeedbacksubmitSubscriber(this.view));
    }

    /* loaded from: classes.dex */
    private class MemberSubscriber extends ProgressSubscriber<HttpResult<List<MyfeedbackResponse>>> {
        public MemberSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            MyfeedbackPresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<List<MyfeedbackResponse>> httpResult) {
            super.onNext((MemberSubscriber) httpResult);
            MyfeedbackPresenter.this.view.onMember(httpResult);
        }
    }

    /* loaded from: classes.dex */
    private class FeedbacksubmitSubscriber extends ProgressSubscriber<HttpResult<String>> {
        public FeedbacksubmitSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            MyfeedbackPresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<String> httpResult) {
            super.onNext((FeedbacksubmitSubscriber) httpResult);
            MyfeedbackPresenter.this.view.onfeedbacksubmint(httpResult);
        }
    }
}