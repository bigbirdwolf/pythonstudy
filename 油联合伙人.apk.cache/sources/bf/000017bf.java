package com.yltx.oil.partner.modules.home.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.home.domain.MessageNotificationUseCase;
import com.yltx.oil.partner.modules.home.response.MessageNotificationResponse;
import com.yltx.oil.partner.modules.home.view.MessageNotificationView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import com.yltx.oil.partner.mvp.views.LoadDataView;
import java.util.List;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class MessageNotificationPresenter implements Presenter {
    private MessageNotificationUseCase messageNotificationUseCase;
    private MessageNotificationView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public MessageNotificationPresenter(MessageNotificationUseCase messageNotificationUseCase) {
        this.messageNotificationUseCase = messageNotificationUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.messageNotificationUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (MessageNotificationView) interfaceView;
    }

    public void messagesubmit(int i) {
        this.messageNotificationUseCase.setPageNo(i);
        this.messageNotificationUseCase.execute(new MessageNotificationSubscriber(this.view));
    }

    /* loaded from: classes.dex */
    private class MessageNotificationSubscriber extends ProgressSubscriber<HttpResult<List<MessageNotificationResponse>>> {
        public MessageNotificationSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            MessageNotificationPresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<List<MessageNotificationResponse>> httpResult) {
            super.onNext((MessageNotificationSubscriber) httpResult);
            MessageNotificationPresenter.this.view.onMember(httpResult);
        }
    }
}