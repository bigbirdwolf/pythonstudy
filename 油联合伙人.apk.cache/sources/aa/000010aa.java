package com.jakewharton.rxbinding.support.v4.view;

import android.support.v4.view.ViewPager;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class ViewPagerPageScrollStateChangedOnSubscribe implements Observable.OnSubscribe<Integer> {
    final ViewPager view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewPagerPageScrollStateChangedOnSubscribe(ViewPager viewPager) {
        this.view = viewPager;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Integer> subscriber) {
        MainThreadSubscription.verifyMainThread();
        final ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() { // from class: com.jakewharton.rxbinding.support.v4.view.ViewPagerPageScrollStateChangedOnSubscribe.1
            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(Integer.valueOf(i));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.v4.view.ViewPagerPageScrollStateChangedOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                ViewPagerPageScrollStateChangedOnSubscribe.this.view.removeOnPageChangeListener(onPageChangeListener);
            }
        });
        this.view.addOnPageChangeListener(onPageChangeListener);
    }
}