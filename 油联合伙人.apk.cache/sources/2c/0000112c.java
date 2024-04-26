package com.jakewharton.rxbinding.view;

import android.view.View;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ViewClickOnSubscribe implements Observable.OnSubscribe<Void> {
    final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewClickOnSubscribe(View view) {
        this.view = view;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Void> subscriber) {
        MainThreadSubscription.verifyMainThread();
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.jakewharton.rxbinding.view.ViewClickOnSubscribe.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(null);
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.view.ViewClickOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                ViewClickOnSubscribe.this.view.setOnClickListener(null);
            }
        });
        this.view.setOnClickListener(onClickListener);
    }
}