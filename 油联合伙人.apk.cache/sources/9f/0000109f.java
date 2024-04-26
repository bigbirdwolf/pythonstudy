package com.jakewharton.rxbinding.support.design.widget;

import android.support.design.widget.TabLayout;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class TabLayoutSelectionsOnSubscribe implements Observable.OnSubscribe<TabLayout.Tab> {
    final TabLayout view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TabLayoutSelectionsOnSubscribe(TabLayout tabLayout) {
        this.view = tabLayout;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super TabLayout.Tab> subscriber) {
        MainThreadSubscription.verifyMainThread();
        TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() { // from class: com.jakewharton.rxbinding.support.design.widget.TabLayoutSelectionsOnSubscribe.1
            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }

            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(tab);
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.design.widget.TabLayoutSelectionsOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                TabLayoutSelectionsOnSubscribe.this.view.setOnTabSelectedListener(null);
            }
        });
        this.view.setOnTabSelectedListener(onTabSelectedListener);
        int selectedTabPosition = this.view.getSelectedTabPosition();
        if (selectedTabPosition != -1) {
            subscriber.onNext(this.view.getTabAt(selectedTabPosition));
        }
    }
}