package com.jakewharton.rxbinding.support.v7.widget;

import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class PopupMenuItemClickOnSubscribe implements Observable.OnSubscribe<MenuItem> {
    final PopupMenu view;

    public PopupMenuItemClickOnSubscribe(PopupMenu popupMenu) {
        this.view = popupMenu;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super MenuItem> subscriber) {
        MainThreadSubscription.verifyMainThread();
        PopupMenu.OnMenuItemClickListener onMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() { // from class: com.jakewharton.rxbinding.support.v7.widget.PopupMenuItemClickOnSubscribe.1
            @Override // android.support.v7.widget.PopupMenu.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (subscriber.isUnsubscribed()) {
                    return true;
                }
                subscriber.onNext(menuItem);
                return true;
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.v7.widget.PopupMenuItemClickOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                PopupMenuItemClickOnSubscribe.this.view.setOnMenuItemClickListener(null);
            }
        });
        this.view.setOnMenuItemClickListener(onMenuItemClickListener);
    }
}