package com.jakewharton.rxbinding.view;

import android.annotation.TargetApi;
import android.view.View;
import android.view.ViewTreeObserver;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

@TargetApi(16)
/* loaded from: classes.dex */
final class ViewTreeObserverDrawOnSubscribe implements Observable.OnSubscribe<Void> {
    final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewTreeObserverDrawOnSubscribe(View view) {
        this.view = view;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Void> subscriber) {
        MainThreadSubscription.verifyMainThread();
        final ViewTreeObserver.OnDrawListener onDrawListener = new ViewTreeObserver.OnDrawListener() { // from class: com.jakewharton.rxbinding.view.ViewTreeObserverDrawOnSubscribe.1
            @Override // android.view.ViewTreeObserver.OnDrawListener
            public void onDraw() {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(null);
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.view.ViewTreeObserverDrawOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                ViewTreeObserverDrawOnSubscribe.this.view.getViewTreeObserver().removeOnDrawListener(onDrawListener);
            }
        });
        this.view.getViewTreeObserver().addOnDrawListener(onDrawListener);
    }
}