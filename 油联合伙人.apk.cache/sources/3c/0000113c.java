package com.jakewharton.rxbinding.view;

import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;
import rx.functions.Func1;

/* loaded from: classes.dex */
final class ViewHoverOnSubscribe implements Observable.OnSubscribe<MotionEvent> {
    final Func1<? super MotionEvent, Boolean> handled;
    final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewHoverOnSubscribe(View view, Func1<? super MotionEvent, Boolean> func1) {
        this.view = view;
        this.handled = func1;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super MotionEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        View.OnHoverListener onHoverListener = new View.OnHoverListener() { // from class: com.jakewharton.rxbinding.view.ViewHoverOnSubscribe.1
            @Override // android.view.View.OnHoverListener
            public boolean onHover(View view, @NonNull MotionEvent motionEvent) {
                if (ViewHoverOnSubscribe.this.handled.call(motionEvent).booleanValue()) {
                    if (subscriber.isUnsubscribed()) {
                        return true;
                    }
                    subscriber.onNext(motionEvent);
                    return true;
                }
                return false;
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.view.ViewHoverOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                ViewHoverOnSubscribe.this.view.setOnHoverListener(null);
            }
        });
        this.view.setOnHoverListener(onHoverListener);
    }
}