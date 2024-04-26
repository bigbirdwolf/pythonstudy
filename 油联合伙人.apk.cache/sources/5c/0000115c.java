package com.jakewharton.rxbinding.view;

import android.view.View;
import android.view.ViewTreeObserver;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;
import rx.functions.Func0;

/* loaded from: classes.dex */
final class ViewTreeObserverPreDrawOnSubscribe implements Observable.OnSubscribe<Void> {
    final Func0<Boolean> proceedDrawingPass;
    final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewTreeObserverPreDrawOnSubscribe(View view, Func0<Boolean> func0) {
        this.view = view;
        this.proceedDrawingPass = func0;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Void> subscriber) {
        MainThreadSubscription.verifyMainThread();
        final ViewTreeObserver.OnPreDrawListener onPreDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: com.jakewharton.rxbinding.view.ViewTreeObserverPreDrawOnSubscribe.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (subscriber.isUnsubscribed()) {
                    return true;
                }
                subscriber.onNext(null);
                return ViewTreeObserverPreDrawOnSubscribe.this.proceedDrawingPass.call().booleanValue();
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.view.ViewTreeObserverPreDrawOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                ViewTreeObserverPreDrawOnSubscribe.this.view.getViewTreeObserver().removeOnPreDrawListener(onPreDrawListener);
            }
        });
        this.view.getViewTreeObserver().addOnPreDrawListener(onPreDrawListener);
    }
}