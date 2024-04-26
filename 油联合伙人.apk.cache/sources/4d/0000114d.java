package com.jakewharton.rxbinding.view;

import android.annotation.TargetApi;
import android.view.View;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

@TargetApi(23)
/* loaded from: classes.dex */
final class ViewScrollChangeEventOnSubscribe implements Observable.OnSubscribe<ViewScrollChangeEvent> {
    final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewScrollChangeEventOnSubscribe(View view) {
        this.view = view;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super ViewScrollChangeEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        View.OnScrollChangeListener onScrollChangeListener = new View.OnScrollChangeListener() { // from class: com.jakewharton.rxbinding.view.ViewScrollChangeEventOnSubscribe.1
            @Override // android.view.View.OnScrollChangeListener
            public void onScrollChange(View view, int i, int i2, int i3, int i4) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(ViewScrollChangeEvent.create(ViewScrollChangeEventOnSubscribe.this.view, i, i2, i3, i4));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.view.ViewScrollChangeEventOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                ViewScrollChangeEventOnSubscribe.this.view.setOnScrollChangeListener(null);
            }
        });
        this.view.setOnScrollChangeListener(onScrollChangeListener);
    }
}