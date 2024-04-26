package com.jakewharton.rxbinding.view;

import android.view.KeyEvent;
import android.view.View;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;
import rx.functions.Func1;

/* loaded from: classes.dex */
final class ViewKeyOnSubscribe implements Observable.OnSubscribe<KeyEvent> {
    private final Func1<? super KeyEvent, Boolean> handled;
    private final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewKeyOnSubscribe(View view, Func1<? super KeyEvent, Boolean> func1) {
        this.view = view;
        this.handled = func1;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super KeyEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        View.OnKeyListener onKeyListener = new View.OnKeyListener() { // from class: com.jakewharton.rxbinding.view.ViewKeyOnSubscribe.1
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (!subscriber.isUnsubscribed() && ((Boolean) ViewKeyOnSubscribe.this.handled.call(keyEvent)).booleanValue()) {
                    subscriber.onNext(keyEvent);
                    return true;
                }
                return false;
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.view.ViewKeyOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                ViewKeyOnSubscribe.this.view.setOnKeyListener(null);
            }
        });
        this.view.setOnKeyListener(onKeyListener);
    }
}