package com.jakewharton.rxbinding.support.design.widget;

import android.support.design.widget.AppBarLayout;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class AppBarLayoutOffsetChangeOnSubscribe implements Observable.OnSubscribe<Integer> {
    final AppBarLayout view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppBarLayoutOffsetChangeOnSubscribe(AppBarLayout appBarLayout) {
        this.view = appBarLayout;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Integer> subscriber) {
        MainThreadSubscription.verifyMainThread();
        final AppBarLayout.OnOffsetChangedListener onOffsetChangedListener = new AppBarLayout.OnOffsetChangedListener() { // from class: com.jakewharton.rxbinding.support.design.widget.AppBarLayoutOffsetChangeOnSubscribe.1
            @Override // android.support.design.widget.AppBarLayout.OnOffsetChangedListener
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(Integer.valueOf(i));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.design.widget.AppBarLayoutOffsetChangeOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                AppBarLayoutOffsetChangeOnSubscribe.this.view.removeOnOffsetChangedListener(onOffsetChangedListener);
            }
        });
        this.view.addOnOffsetChangedListener(onOffsetChangedListener);
    }
}