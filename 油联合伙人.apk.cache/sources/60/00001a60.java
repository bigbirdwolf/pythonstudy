package com.yltx.oil.partner.mvp.domain;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/* loaded from: classes.dex */
public abstract class UseCase<T> {
    protected Subscription subscription = Subscriptions.empty();

    protected abstract Observable<T> buildObservable();

    /* JADX WARN: Multi-variable type inference failed */
    public void execute(Subscriber<T> subscriber) {
        this.subscription = buildObservable().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber) subscriber);
    }

    public void unSubscribe() {
        if (this.subscription.isUnsubscribed()) {
            return;
        }
        this.subscription.unsubscribe();
    }
}