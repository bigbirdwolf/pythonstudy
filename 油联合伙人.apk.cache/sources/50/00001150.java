package com.jakewharton.rxbinding.view;

import android.view.View;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class ViewSystemUiVisibilityChangeOnSubscribe implements Observable.OnSubscribe<Integer> {
    final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewSystemUiVisibilityChangeOnSubscribe(View view) {
        this.view = view;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Integer> subscriber) {
        MainThreadSubscription.verifyMainThread();
        View.OnSystemUiVisibilityChangeListener onSystemUiVisibilityChangeListener = new View.OnSystemUiVisibilityChangeListener() { // from class: com.jakewharton.rxbinding.view.ViewSystemUiVisibilityChangeOnSubscribe.1
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public void onSystemUiVisibilityChange(int i) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(Integer.valueOf(i));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.view.ViewSystemUiVisibilityChangeOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                ViewSystemUiVisibilityChangeOnSubscribe.this.view.setOnSystemUiVisibilityChangeListener(null);
            }
        });
        this.view.setOnSystemUiVisibilityChangeListener(onSystemUiVisibilityChangeListener);
    }
}