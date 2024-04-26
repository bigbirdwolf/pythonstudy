package com.jakewharton.rxbinding.widget;

import android.annotation.TargetApi;
import android.view.MenuItem;
import android.widget.Toolbar;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

@TargetApi(21)
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
        Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() { // from class: com.jakewharton.rxbinding.widget.ToolbarItemClickOnSubscribe.1
            @Override // android.widget.Toolbar.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (subscriber.isUnsubscribed()) {
                    return true;
                }
                subscriber.onNext(menuItem);
                return true;
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.ToolbarItemClickOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                ToolbarItemClickOnSubscribe.this.view.setOnMenuItemClickListener(null);
            }
        });
        this.view.setOnMenuItemClickListener(onMenuItemClickListener);
    }
}