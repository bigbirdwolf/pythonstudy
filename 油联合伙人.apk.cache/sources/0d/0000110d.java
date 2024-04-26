package com.jakewharton.rxbinding.view;

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
        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() { // from class: com.jakewharton.rxbinding.view.MenuItemActionViewEventOnSubscribe.1
            @Override // android.view.MenuItem.OnActionExpandListener
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return onEvent(MenuItemActionViewEvent.create(MenuItemActionViewEventOnSubscribe.this.menuItem, MenuItemActionViewEvent.Kind.EXPAND));
            }

            @Override // android.view.MenuItem.OnActionExpandListener
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
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.view.MenuItemActionViewEventOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                MenuItemActionViewEventOnSubscribe.this.menuItem.setOnActionExpandListener(null);
            }
        });
        this.menuItem.setOnActionExpandListener(onActionExpandListener);
    }
}