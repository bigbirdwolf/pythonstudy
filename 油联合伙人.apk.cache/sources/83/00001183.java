package com.jakewharton.rxbinding.widget;

import android.widget.PopupMenu;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class PopupMenuDismissOnSubscribe implements Observable.OnSubscribe<Void> {
    final PopupMenu view;

    public PopupMenuDismissOnSubscribe(PopupMenu popupMenu) {
        this.view = popupMenu;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Void> subscriber) {
        MainThreadSubscription.verifyMainThread();
        PopupMenu.OnDismissListener onDismissListener = new PopupMenu.OnDismissListener() { // from class: com.jakewharton.rxbinding.widget.PopupMenuDismissOnSubscribe.1
            @Override // android.widget.PopupMenu.OnDismissListener
            public void onDismiss(PopupMenu popupMenu) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(null);
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.PopupMenuDismissOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                PopupMenuDismissOnSubscribe.this.view.setOnDismissListener(null);
            }
        });
        this.view.setOnDismissListener(onDismissListener);
    }
}