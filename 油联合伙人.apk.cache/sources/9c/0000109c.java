package com.jakewharton.rxbinding.support.design.widget;

import android.support.design.widget.TabLayout;
import com.jakewharton.rxbinding.support.design.widget.TabLayoutSelectionEvent;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class TabLayoutSelectionEventOnSubscribe implements Observable.OnSubscribe<TabLayoutSelectionEvent> {
    final TabLayout view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TabLayoutSelectionEventOnSubscribe(TabLayout tabLayout) {
        this.view = tabLayout;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super TabLayoutSelectionEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() { // from class: com.jakewharton.rxbinding.support.design.widget.TabLayoutSelectionEventOnSubscribe.1
            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(TabLayoutSelectionEvent.create(TabLayoutSelectionEventOnSubscribe.this.view, TabLayoutSelectionEvent.Kind.SELECTED, tab));
            }

            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(TabLayoutSelectionEvent.create(TabLayoutSelectionEventOnSubscribe.this.view, TabLayoutSelectionEvent.Kind.UNSELECTED, tab));
            }

            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(TabLayoutSelectionEvent.create(TabLayoutSelectionEventOnSubscribe.this.view, TabLayoutSelectionEvent.Kind.RESELECTED, tab));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.design.widget.TabLayoutSelectionEventOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                TabLayoutSelectionEventOnSubscribe.this.view.setOnTabSelectedListener(null);
            }
        });
        this.view.setOnTabSelectedListener(onTabSelectedListener);
        int selectedTabPosition = this.view.getSelectedTabPosition();
        if (selectedTabPosition != -1) {
            subscriber.onNext(TabLayoutSelectionEvent.create(this.view, TabLayoutSelectionEvent.Kind.SELECTED, this.view.getTabAt(selectedTabPosition)));
        }
    }
}