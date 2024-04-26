package com.yltx.oil.partner.modules.home.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.home.domain.MessageForDetailsUseCase;
import com.yltx.oil.partner.modules.home.response.MessageForDetailsResponse;
import com.yltx.oil.partner.modules.home.view.MessageForDetailsView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import com.yltx.oil.partner.mvp.views.LoadDataView;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class MessageForDetailsPresenter implements Presenter {
    private MessageForDetailsUseCase messageForDetailsUseCase;
    private MessageForDetailsView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public MessageForDetailsPresenter(MessageForDetailsUseCase messageForDetailsUseCase) {
        this.messageForDetailsUseCase = messageForDetailsUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.messageForDetailsUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (MessageForDetailsView) interfaceView;
    }

    public void messagesubmit(int i) {
        this.messageForDetailsUseCase.setRowid(i);
        this.messageForDetailsUseCase.execute(new MessageForDetailsSubscriber(this.view));
    }

    /* loaded from: classes.dex */
    private class MessageForDetailsSubscriber extends ProgressSubscriber<HttpResult<MessageForDetailsResponse>> {
        public MessageForDetailsSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            MessageForDetailsPresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<MessageForDetailsResponse> httpResult) {
            super.onNext((MessageForDetailsSubscriber) httpResult);
            MessageForDetailsPresenter.this.view.onMember(httpResult);
        }
    }
}