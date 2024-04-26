package com.jakewharton.rxbinding.support.v4.widget;

import android.support.v4.widget.SwipeRefreshLayout;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class SwipeRefreshLayoutRefreshOnSubscribe implements Observable.OnSubscribe<Void> {
    final SwipeRefreshLayout view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SwipeRefreshLayoutRefreshOnSubscribe(SwipeRefreshLayout swipeRefreshLayout) {
        this.view = swipeRefreshLayout;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Void> subscriber) {
        MainThreadSubscription.verifyMainThread();
        SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() { // from class: com.jakewharton.rxbinding.support.v4.widget.SwipeRefreshLayoutRefreshOnSubscribe.1
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(null);
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.v4.widget.SwipeRefreshLayoutRefreshOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                SwipeRefreshLayoutRefreshOnSubscribe.this.view.setOnRefreshListener(null);
            }
        });
        this.view.setOnRefreshListener(onRefreshListener);
    }
}