package com.jakewharton.rxbinding.support.design.widget;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class BottomNavigationViewItemSelectionsOnSubscribe implements Observable.OnSubscribe<MenuItem> {
    final BottomNavigationView view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BottomNavigationViewItemSelectionsOnSubscribe(BottomNavigationView bottomNavigationView) {
        this.view = bottomNavigationView;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super MenuItem> subscriber) {
        MainThreadSubscription.verifyMainThread();
        BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() { // from class: com.jakewharton.rxbinding.support.design.widget.BottomNavigationViewItemSelectionsOnSubscribe.1
            @Override // android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (subscriber.isUnsubscribed()) {
                    return true;
                }
                subscriber.onNext(menuItem);
                return true;
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.design.widget.BottomNavigationViewItemSelectionsOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                BottomNavigationViewItemSelectionsOnSubscribe.this.view.setOnNavigationItemSelectedListener(null);
            }
        });
        this.view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
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