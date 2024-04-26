package com.jakewharton.rxbinding.widget;

import android.view.View;
import android.widget.AdapterView;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class AdapterViewItemClickOnSubscribe implements Observable.OnSubscribe<Integer> {
    final AdapterView<?> view;

    public AdapterViewItemClickOnSubscribe(AdapterView<?> adapterView) {
        this.view = adapterView;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Integer> subscriber) {
        MainThreadSubscription.verifyMainThread();
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.jakewharton.rxbinding.widget.AdapterViewItemClickOnSubscribe.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(Integer.valueOf(i));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.AdapterViewItemClickOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                AdapterViewItemClickOnSubscribe.this.view.setOnItemClickListener(null);
            }
        });
        this.view.setOnItemClickListener(onItemClickListener);
    }
}