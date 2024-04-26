package com.jakewharton.rxbinding.support.v7.widget;

import android.support.v7.widget.Toolbar;
import android.view.View;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class ToolbarNavigationClickOnSubscribe implements Observable.OnSubscribe<Void> {
    final Toolbar view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ToolbarNavigationClickOnSubscribe(Toolbar toolbar) {
        this.view = toolbar;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Void> subscriber) {
        MainThreadSubscription.verifyMainThread();
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.jakewharton.rxbinding.support.v7.widget.ToolbarNavigationClickOnSubscribe.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(null);
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.v7.widget.ToolbarNavigationClickOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                ToolbarNavigationClickOnSubscribe.this.view.setNavigationOnClickListener(null);
            }
        });
        this.view.setNavigationOnClickListener(onClickListener);
    }
}