package com.jakewharton.rxbinding.view;

import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;
import rx.functions.Func1;

/* loaded from: classes.dex */
final class ViewTouchOnSubscribe implements Observable.OnSubscribe<MotionEvent> {
    final Func1<? super MotionEvent, Boolean> handled;
    final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewTouchOnSubscribe(View view, Func1<? super MotionEvent, Boolean> func1) {
        this.view = view;
        this.handled = func1;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super MotionEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        View.OnTouchListener onTouchListener = new View.OnTouchListener() { // from class: com.jakewharton.rxbinding.view.ViewTouchOnSubscribe.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, @NonNull MotionEvent motionEvent) {
                if (ViewTouchOnSubscribe.this.handled.call(motionEvent).booleanValue()) {
                    if (subscriber.isUnsubscribed()) {
                        return true;
                    }
                    subscriber.onNext(motionEvent);
                    return true;
                }
                return false;
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.view.ViewTouchOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                ViewTouchOnSubscribe.this.view.setOnTouchListener(null);
            }
        });
        this.view.setOnTouchListener(onTouchListener);
    }
}