package com.jakewharton.rxbinding.view;

import android.view.View;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;
import rx.functions.Func0;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ViewLongClickOnSubscribe implements Observable.OnSubscribe<Void> {
    final Func0<Boolean> handled;
    final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewLongClickOnSubscribe(View view, Func0<Boolean> func0) {
        this.view = view;
        this.handled = func0;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Void> subscriber) {
        MainThreadSubscription.verifyMainThread();
        View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() { // from class: com.jakewharton.rxbinding.view.ViewLongClickOnSubscribe.1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                if (ViewLongClickOnSubscribe.this.handled.call().booleanValue()) {
                    if (subscriber.isUnsubscribed()) {
                        return true;
                    }
                    subscriber.onNext(null);
                    return true;
                }
                return false;
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.view.ViewLongClickOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                ViewLongClickOnSubscribe.this.view.setOnLongClickListener(null);
            }
        });
        this.view.setOnLongClickListener(onLongClickListener);
    }
}