package com.yltx.oil.partner.modules.home.presenter;

import com.yltx.oil.partner.modules.home.domain.SeekUseCase;
import com.yltx.oil.partner.modules.home.response.SeekResponse;
import com.yltx.oil.partner.modules.home.view.SeekView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import com.yltx.oil.partner.mvp.views.LoadDataView;
import java.util.List;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class SeekPresenter implements Presenter {
    private SeekUseCase seekUseCase;
    private SeekView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public SeekPresenter(SeekUseCase seekUseCase) {
        this.seekUseCase = seekUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.seekUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (SeekView) interfaceView;
    }

    public void messagesubmit(String str) {
        this.seekUseCase.setGoodsName(str);
        this.seekUseCase.execute(new SSLSSubscriber(this.view));
    }

    /* loaded from: classes.dex */
    private class SSLSSubscriber extends ProgressSubscriber<List<SeekResponse>> {
        public SSLSSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            SeekPresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(List<SeekResponse> list) {
            super.onNext((SSLSSubscriber) list);
            SeekPresenter.this.view.onSeek(list);
        }
    }
}