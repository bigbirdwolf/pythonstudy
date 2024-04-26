package com.jakewharton.rxbinding.view;

import android.view.View;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class ViewFocusChangeOnSubscribe implements Observable.OnSubscribe<Boolean> {
    final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewFocusChangeOnSubscribe(View view) {
        this.view = view;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Boolean> subscriber) {
        MainThreadSubscription.verifyMainThread();
        View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.jakewharton.rxbinding.view.ViewFocusChangeOnSubscribe.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(Boolean.valueOf(z));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.view.ViewFocusChangeOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                ViewFocusChangeOnSubscribe.this.view.setOnFocusChangeListener(null);
            }
        });
        this.view.setOnFocusChangeListener(onFocusChangeListener);
        subscriber.onNext(Boolean.valueOf(this.view.hasFocus()));
    }
}