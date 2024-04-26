package com.jakewharton.rxbinding.support.v4.widget;

import android.support.v4.widget.DrawerLayout;
import android.view.View;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class DrawerLayoutDrawerOpenedOnSubscribe implements Observable.OnSubscribe<Boolean> {
    final int gravity;
    final DrawerLayout view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DrawerLayoutDrawerOpenedOnSubscribe(DrawerLayout drawerLayout, int i) {
        this.view = drawerLayout;
        this.gravity = i;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Boolean> subscriber) {
        MainThreadSubscription.verifyMainThread();
        DrawerLayout.SimpleDrawerListener simpleDrawerListener = new DrawerLayout.SimpleDrawerListener() { // from class: com.jakewharton.rxbinding.support.v4.widget.DrawerLayoutDrawerOpenedOnSubscribe.1
            @Override // android.support.v4.widget.DrawerLayout.SimpleDrawerListener, android.support.v4.widget.DrawerLayout.DrawerListener
            public void onDrawerSlide(View view, float f) {
            }

            @Override // android.support.v4.widget.DrawerLayout.SimpleDrawerListener, android.support.v4.widget.DrawerLayout.DrawerListener
            public void onDrawerStateChanged(int i) {
            }

            @Override // android.support.v4.widget.DrawerLayout.SimpleDrawerListener, android.support.v4.widget.DrawerLayout.DrawerListener
            public void onDrawerOpened(View view) {
                if (subscriber.isUnsubscribed() || ((DrawerLayout.LayoutParams) view.getLayoutParams()).gravity != DrawerLayoutDrawerOpenedOnSubscribe.this.gravity) {
                    return;
                }
                subscriber.onNext(true);
            }

            @Override // android.support.v4.widget.DrawerLayout.SimpleDrawerListener, android.support.v4.widget.DrawerLayout.DrawerListener
            public void onDrawerClosed(View view) {
                if (subscriber.isUnsubscribed() || ((DrawerLayout.LayoutParams) view.getLayoutParams()).gravity != DrawerLayoutDrawerOpenedOnSubscribe.this.gravity) {
                    return;
                }
                subscriber.onNext(false);
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.v4.widget.DrawerLayoutDrawerOpenedOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                DrawerLayoutDrawerOpenedOnSubscribe.this.view.setDrawerListener(null);
            }
        });
        this.view.setDrawerListener(simpleDrawerListener);
        subscriber.onNext(Boolean.valueOf(this.view.isDrawerOpen(this.gravity)));
    }
}