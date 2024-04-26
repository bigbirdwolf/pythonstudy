package com.jakewharton.rxbinding.support.v4.widget;

import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class SlidingPaneLayoutSlideOnSubscribe implements Observable.OnSubscribe<Float> {
    final SlidingPaneLayout view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SlidingPaneLayoutSlideOnSubscribe(SlidingPaneLayout slidingPaneLayout) {
        this.view = slidingPaneLayout;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Float> subscriber) {
        MainThreadSubscription.verifyMainThread();
        SlidingPaneLayout.SimplePanelSlideListener simplePanelSlideListener = new SlidingPaneLayout.SimplePanelSlideListener() { // from class: com.jakewharton.rxbinding.support.v4.widget.SlidingPaneLayoutSlideOnSubscribe.1
            @Override // android.support.v4.widget.SlidingPaneLayout.SimplePanelSlideListener, android.support.v4.widget.SlidingPaneLayout.PanelSlideListener
            public void onPanelSlide(View view, float f) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(Float.valueOf(f));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.v4.widget.SlidingPaneLayoutSlideOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                SlidingPaneLayoutSlideOnSubscribe.this.view.setPanelSlideListener(null);
            }
        });
        this.view.setPanelSlideListener(simplePanelSlideListener);
    }
}