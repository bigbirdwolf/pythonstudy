package com.jakewharton.rxbinding.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class RecyclerViewScrollStateChangeOnSubscribe implements Observable.OnSubscribe<Integer> {
    final RecyclerView recyclerView;

    public RecyclerViewScrollStateChangeOnSubscribe(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Integer> subscriber) {
        MainThreadSubscription.verifyMainThread();
        final RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() { // from class: com.jakewharton.rxbinding.support.v7.widget.RecyclerViewScrollStateChangeOnSubscribe.1
            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(Integer.valueOf(i));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.v7.widget.RecyclerViewScrollStateChangeOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                RecyclerViewScrollStateChangeOnSubscribe.this.recyclerView.removeOnScrollListener(onScrollListener);
            }
        });
        this.recyclerView.addOnScrollListener(onScrollListener);
    }
}