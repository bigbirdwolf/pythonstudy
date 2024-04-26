package com.jakewharton.rxbinding.support.v4.widget;

import android.support.v4.widget.NestedScrollView;
import com.jakewharton.rxbinding.view.ViewScrollChangeEvent;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class NestedScrollViewScrollChangeEventOnSubscribe implements Observable.OnSubscribe<ViewScrollChangeEvent> {
    final NestedScrollView nestedScrollView;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NestedScrollViewScrollChangeEventOnSubscribe(NestedScrollView nestedScrollView) {
        this.nestedScrollView = nestedScrollView;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super ViewScrollChangeEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        NestedScrollView.OnScrollChangeListener onScrollChangeListener = new NestedScrollView.OnScrollChangeListener() { // from class: com.jakewharton.rxbinding.support.v4.widget.NestedScrollViewScrollChangeEventOnSubscribe.1
            @Override // android.support.v4.widget.NestedScrollView.OnScrollChangeListener
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(ViewScrollChangeEvent.create(NestedScrollViewScrollChangeEventOnSubscribe.this.nestedScrollView, i, i2, i3, i4));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.v4.widget.NestedScrollViewScrollChangeEventOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                NestedScrollViewScrollChangeEventOnSubscribe.this.nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) null);
            }
        });
        this.nestedScrollView.setOnScrollChangeListener(onScrollChangeListener);
    }
}