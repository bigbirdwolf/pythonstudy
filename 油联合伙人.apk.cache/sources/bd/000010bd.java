package com.jakewharton.rxbinding.support.v4.widget;

import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class SlidingPaneLayoutPaneOpenedOnSubscribe implements Observable.OnSubscribe<Boolean> {
    final SlidingPaneLayout view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SlidingPaneLayoutPaneOpenedOnSubscribe(SlidingPaneLayout slidingPaneLayout) {
        this.view = slidingPaneLayout;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Boolean> subscriber) {
        MainThreadSubscription.verifyMainThread();
        SlidingPaneLayout.SimplePanelSlideListener simplePanelSlideListener = new SlidingPaneLayout.SimplePanelSlideListener() { // from class: com.jakewharton.rxbinding.support.v4.widget.SlidingPaneLayoutPaneOpenedOnSubscribe.1
            @Override // android.support.v4.widget.SlidingPaneLayout.SimplePanelSlideListener, android.support.v4.widget.SlidingPaneLayout.PanelSlideListener
            public void onPanelOpened(View view) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(true);
            }

            @Override // android.support.v4.widget.SlidingPaneLayout.SimplePanelSlideListener, android.support.v4.widget.SlidingPaneLayout.PanelSlideListener
            public void onPanelClosed(View view) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(false);
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.v4.widget.SlidingPaneLayoutPaneOpenedOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                SlidingPaneLayoutPaneOpenedOnSubscribe.this.view.setPanelSlideListener(null);
            }
        });
        this.view.setPanelSlideListener(simplePanelSlideListener);
        subscriber.onNext(Boolean.valueOf(this.view.isOpen()));
    }
}