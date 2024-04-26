package com.jakewharton.rxbinding.widget;

import android.widget.AbsListView;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class AbsListViewScrollEventOnSubscribe implements Observable.OnSubscribe<AbsListViewScrollEvent> {
    final AbsListView view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbsListViewScrollEventOnSubscribe(AbsListView absListView) {
        this.view = absListView;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super AbsListViewScrollEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() { // from class: com.jakewharton.rxbinding.widget.AbsListViewScrollEventOnSubscribe.1
            int currentScrollState = 0;

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
                this.currentScrollState = i;
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(AbsListViewScrollEvent.create(absListView, i, absListView.getFirstVisiblePosition(), absListView.getChildCount(), absListView.getCount()));
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(AbsListViewScrollEvent.create(absListView, this.currentScrollState, i, i2, i3));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.AbsListViewScrollEventOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                AbsListViewScrollEventOnSubscribe.this.view.setOnScrollListener(null);
            }
        });
        this.view.setOnScrollListener(onScrollListener);
    }
}