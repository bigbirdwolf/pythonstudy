package com.jakewharton.rxbinding.support.v7.widget;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class ToolbarItemClickOnSubscribe implements Observable.OnSubscribe<MenuItem> {
    final Toolbar view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ToolbarItemClickOnSubscribe(Toolbar toolbar) {
        this.view = toolbar;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super MenuItem> subscriber) {
        MainThreadSubscription.verifyMainThread();
        Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() { // from class: com.jakewharton.rxbinding.support.v7.widget.ToolbarItemClickOnSubscribe.1
            @Override // android.support.v7.widget.Toolbar.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (subscriber.isUnsubscribed()) {
                    return true;
                }
                subscriber.onNext(menuItem);
                return true;
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.v7.widget.ToolbarItemClickOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                ToolbarItemClickOnSubscribe.this.view.setOnMenuItemClickListener(null);
            }
        });
        this.view.setOnMenuItemClickListener(onMenuItemClickListener);
    }
}