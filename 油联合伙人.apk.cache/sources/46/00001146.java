package com.jakewharton.rxbinding.view;

import android.view.View;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class ViewLayoutChangeOnSubscribe implements Observable.OnSubscribe<Void> {
    final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewLayoutChangeOnSubscribe(View view) {
        this.view = view;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Void> subscriber) {
        MainThreadSubscription.verifyMainThread();
        final View.OnLayoutChangeListener onLayoutChangeListener = new View.OnLayoutChangeListener() { // from class: com.jakewharton.rxbinding.view.ViewLayoutChangeOnSubscribe.1
            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(null);
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.view.ViewLayoutChangeOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                ViewLayoutChangeOnSubscribe.this.view.removeOnLayoutChangeListener(onLayoutChangeListener);
            }
        });
        this.view.addOnLayoutChangeListener(onLayoutChangeListener);
    }
}