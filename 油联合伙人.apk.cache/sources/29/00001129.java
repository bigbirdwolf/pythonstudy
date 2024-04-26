package com.jakewharton.rxbinding.view;

import android.support.annotation.NonNull;
import android.view.View;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class ViewAttachesOnSubscribe implements Observable.OnSubscribe<Void> {
    final boolean callOnAttach;
    final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewAttachesOnSubscribe(View view, boolean z) {
        this.view = view;
        this.callOnAttach = z;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Void> subscriber) {
        MainThreadSubscription.verifyMainThread();
        final View.OnAttachStateChangeListener onAttachStateChangeListener = new View.OnAttachStateChangeListener() { // from class: com.jakewharton.rxbinding.view.ViewAttachesOnSubscribe.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(@NonNull View view) {
                if (!ViewAttachesOnSubscribe.this.callOnAttach || subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(null);
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(@NonNull View view) {
                if (ViewAttachesOnSubscribe.this.callOnAttach || subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(null);
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.view.ViewAttachesOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                ViewAttachesOnSubscribe.this.view.removeOnAttachStateChangeListener(onAttachStateChangeListener);
            }
        });
        this.view.addOnAttachStateChangeListener(onAttachStateChangeListener);
    }
}