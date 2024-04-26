package com.jakewharton.rxbinding.view;

import android.view.MenuItem;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;
import rx.functions.Func1;

/* loaded from: classes.dex */
final class MenuItemClickOnSubscribe implements Observable.OnSubscribe<Void> {
    final Func1<? super MenuItem, Boolean> handled;
    final MenuItem menuItem;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MenuItemClickOnSubscribe(MenuItem menuItem, Func1<? super MenuItem, Boolean> func1) {
        this.menuItem = menuItem;
        this.handled = func1;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Void> subscriber) {
        MainThreadSubscription.verifyMainThread();
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = new MenuItem.OnMenuItemClickListener() { // from class: com.jakewharton.rxbinding.view.MenuItemClickOnSubscribe.1
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (MenuItemClickOnSubscribe.this.handled.call(MenuItemClickOnSubscribe.this.menuItem).booleanValue()) {
                    if (subscriber.isUnsubscribed()) {
                        return true;
                    }
                    subscriber.onNext(null);
                    return true;
                }
                return false;
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.view.MenuItemClickOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                MenuItemClickOnSubscribe.this.menuItem.setOnMenuItemClickListener(null);
            }
        });
        this.menuItem.setOnMenuItemClickListener(onMenuItemClickListener);
    }
}