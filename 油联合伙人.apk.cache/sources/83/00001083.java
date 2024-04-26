package com.jakewharton.rxbinding.support.design.widget;

import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.MenuItem;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class NavigationViewItemSelectionsOnSubscribe implements Observable.OnSubscribe<MenuItem> {
    final NavigationView view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NavigationViewItemSelectionsOnSubscribe(NavigationView navigationView) {
        this.view = navigationView;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super MenuItem> subscriber) {
        MainThreadSubscription.verifyMainThread();
        NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() { // from class: com.jakewharton.rxbinding.support.design.widget.NavigationViewItemSelectionsOnSubscribe.1
            @Override // android.support.design.widget.NavigationView.OnNavigationItemSelectedListener
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (subscriber.isUnsubscribed()) {
                    return true;
                }
                subscriber.onNext(menuItem);
                return true;
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.design.widget.NavigationViewItemSelectionsOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                NavigationViewItemSelectionsOnSubscribe.this.view.setNavigationItemSelectedListener(null);
            }
        });
        this.view.setNavigationItemSelectedListener(onNavigationItemSelectedListener);
        Menu menu = this.view.getMenu();
        int size = menu.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = menu.getItem(i);
            if (item.isChecked()) {
                subscriber.onNext(item);
                return;
            }
        }
    }
}