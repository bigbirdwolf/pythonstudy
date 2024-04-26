package com.jakewharton.rxbinding.support.design.widget;

import android.support.design.widget.Snackbar;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class SnackbarDismissesOnSubscribe implements Observable.OnSubscribe<Integer> {
    final Snackbar view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SnackbarDismissesOnSubscribe(Snackbar snackbar) {
        this.view = snackbar;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Integer> subscriber) {
        MainThreadSubscription.verifyMainThread();
        Snackbar.Callback callback = new Snackbar.Callback() { // from class: com.jakewharton.rxbinding.support.design.widget.SnackbarDismissesOnSubscribe.1
            @Override // android.support.design.widget.Snackbar.Callback
            public void onDismissed(Snackbar snackbar, int i) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(Integer.valueOf(i));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.design.widget.SnackbarDismissesOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                SnackbarDismissesOnSubscribe.this.view.setCallback(null);
            }
        });
        this.view.setCallback(callback);
    }
}