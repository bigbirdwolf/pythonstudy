package com.jakewharton.rxbinding.view;

import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class ViewTreeObserverGlobalLayoutOnSubscribe implements Observable.OnSubscribe<Void> {
    final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewTreeObserverGlobalLayoutOnSubscribe(View view) {
        this.view = view;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Void> subscriber) {
        MainThreadSubscription.verifyMainThread();
        final ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jakewharton.rxbinding.view.ViewTreeObserverGlobalLayoutOnSubscribe.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(null);
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.view.ViewTreeObserverGlobalLayoutOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                if (Build.VERSION.SDK_INT >= 16) {
                    ViewTreeObserverGlobalLayoutOnSubscribe.this.view.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
                } else {
                    ViewTreeObserverGlobalLayoutOnSubscribe.this.view.getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
                }
            }
        });
        this.view.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
    }
}