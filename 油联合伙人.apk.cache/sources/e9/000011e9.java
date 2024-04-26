package com.jakewharton.rxbinding.widget;

import android.annotation.TargetApi;
import android.view.View;
import android.widget.Toolbar;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

@TargetApi(21)
/* loaded from: classes.dex */
final class ToolbarNavigationClickOnSubscribe implements Observable.OnSubscribe<Void> {
    final Toolbar view;

    public ToolbarNavigationClickOnSubscribe(Toolbar toolbar) {
        this.view = toolbar;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Void> subscriber) {
        MainThreadSubscription.verifyMainThread();
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.jakewharton.rxbinding.widget.ToolbarNavigationClickOnSubscribe.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(null);
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.ToolbarNavigationClickOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                ToolbarNavigationClickOnSubscribe.this.view.setNavigationOnClickListener(null);
            }
        });
        this.view.setNavigationOnClickListener(onClickListener);
    }
}