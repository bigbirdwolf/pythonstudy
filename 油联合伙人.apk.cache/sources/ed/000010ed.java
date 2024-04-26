package com.jakewharton.rxbinding.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class RecyclerViewScrollEventOnSubscribe implements Observable.OnSubscribe<RecyclerViewScrollEvent> {
    final RecyclerView recyclerView;

    public RecyclerViewScrollEventOnSubscribe(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super RecyclerViewScrollEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        final RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() { // from class: com.jakewharton.rxbinding.support.v7.widget.RecyclerViewScrollEventOnSubscribe.1
            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(RecyclerViewScrollEvent.create(recyclerView, i, i2));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.v7.widget.RecyclerViewScrollEventOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                RecyclerViewScrollEventOnSubscribe.this.recyclerView.removeOnScrollListener(onScrollListener);
            }
        });
        this.recyclerView.addOnScrollListener(onScrollListener);
    }
}