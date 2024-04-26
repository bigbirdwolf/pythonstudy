package com.yltx.oil.partner.mvp.subscribers;

import com.yltx.oil.partner.mvp.views.LoadDataView;
import com.yltx.oil.partner.mvp.views.ProgressView;

/* loaded from: classes.dex */
public abstract class ProgressSubscriber<T> extends CommonErrorHandlerSubscriber<T> {
    @Override // rx.Observer
    public void onCompleted() {
    }

    public ProgressSubscriber(LoadDataView loadDataView) {
        super(loadDataView);
    }

    @Override // rx.Subscriber
    public void onStart() {
        super.onStart();
        if (this.mLoadDataView instanceof ProgressView) {
            ((ProgressView) this.mLoadDataView).showProgress();
        }
    }

    @Override // com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
    public void onError(Throwable th) {
        super.onError(th);
        if (this.mLoadDataView instanceof ProgressView) {
            ((ProgressView) this.mLoadDataView).hideProgress();
        }
    }

    @Override // rx.Observer
    public void onNext(T t) {
        if (this.mLoadDataView instanceof ProgressView) {
            ((ProgressView) this.mLoadDataView).hideProgress();
        }
    }
}