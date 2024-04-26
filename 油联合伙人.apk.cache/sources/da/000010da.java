package com.jakewharton.rxbinding.support.v7.widget;

import android.support.v7.widget.ActionMenuView;
import android.view.MenuItem;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class ActionMenuViewItemClickOnSubscribe implements Observable.OnSubscribe<MenuItem> {
    final ActionMenuView view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ActionMenuViewItemClickOnSubscribe(ActionMenuView actionMenuView) {
        this.view = actionMenuView;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super MenuItem> subscriber) {
        MainThreadSubscription.verifyMainThread();
        ActionMenuView.OnMenuItemClickListener onMenuItemClickListener = new ActionMenuView.OnMenuItemClickListener() { // from class: com.jakewharton.rxbinding.support.v7.widget.ActionMenuViewItemClickOnSubscribe.1
            @Override // android.support.v7.widget.ActionMenuView.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (subscriber.isUnsubscribed()) {
                    return true;
                }
                subscriber.onNext(menuItem);
                return true;
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.v7.widget.ActionMenuViewItemClickOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                ActionMenuViewItemClickOnSubscribe.this.view.setOnMenuItemClickListener(null);
            }
        });
        this.view.setOnMenuItemClickListener(onMenuItemClickListener);
    }
}