package com.jakewharton.rxbinding.support.v4.view;

import android.support.v4.view.ViewPager;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class ViewPagerPageSelectedOnSubscribe implements Observable.OnSubscribe<Integer> {
    final ViewPager view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewPagerPageSelectedOnSubscribe(ViewPager viewPager) {
        this.view = viewPager;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Integer> subscriber) {
        MainThreadSubscription.verifyMainThread();
        final ViewPager.SimpleOnPageChangeListener simpleOnPageChangeListener = new ViewPager.SimpleOnPageChangeListener() { // from class: com.jakewharton.rxbinding.support.v4.view.ViewPagerPageSelectedOnSubscribe.1
            @Override // android.support.v4.view.ViewPager.SimpleOnPageChangeListener, android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(Integer.valueOf(i));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.v4.view.ViewPagerPageSelectedOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                ViewPagerPageSelectedOnSubscribe.this.view.removeOnPageChangeListener(simpleOnPageChangeListener);
            }
        });
        this.view.addOnPageChangeListener(simpleOnPageChangeListener);
        subscriber.onNext(Integer.valueOf(this.view.getCurrentItem()));
    }
}