package com.jakewharton.rxbinding.support.v4.view;

import android.support.v4.view.MenuItemCompat;
import android.view.MenuItem;
import com.jakewharton.rxbinding.view.MenuItemActionViewEvent;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;
import rx.functions.Func1;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class MenuItemActionViewEventOnSubscribe implements Observable.OnSubscribe<MenuItemActionViewEvent> {
    final Func1<? super MenuItemActionViewEvent, Boolean> handled;
    final MenuItem menuItem;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MenuItemActionViewEventOnSubscribe(MenuItem menuItem, Func1<? super MenuItemActionViewEvent, Boolean> func1) {
        this.menuItem = menuItem;
        this.handled = func1;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super MenuItemActionViewEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        MenuItemCompat.OnActionExpandListener onActionExpandListener = new MenuItemCompat.OnActionExpandListener() { // from class: com.jakewharton.rxbinding.support.v4.view.MenuItemActionViewEventOnSubscribe.1
            @Override // android.support.v4.view.MenuItemCompat.OnActionExpandListener
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return onEvent(MenuItemActionViewEvent.create(MenuItemActionViewEventOnSubscribe.this.menuItem, MenuItemActionViewEvent.Kind.EXPAND));
            }

            @Override // android.support.v4.view.MenuItemCompat.OnActionExpandListener
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                return onEvent(MenuItemActionViewEvent.create(MenuItemActionViewEventOnSubscribe.this.menuItem, MenuItemActionViewEvent.Kind.COLLAPSE));
            }

            private boolean onEvent(MenuItemActionViewEvent menuItemActionViewEvent) {
                if (MenuItemActionViewEventOnSubscribe.this.handled.call(menuItemActionViewEvent).booleanValue()) {
                    if (subscriber.isUnsubscribed()) {
                        return true;
                    }
                    subscriber.onNext(menuItemActionViewEvent);
                    return true;
                }
                return false;
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.v4.view.MenuItemActionViewEventOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                MenuItemCompat.setOnActionExpandListener(MenuItemActionViewEventOnSubscribe.this.menuItem, null);
            }
        });
        MenuItemCompat.setOnActionExpandListener(this.menuItem, onActionExpandListener);
    }
}