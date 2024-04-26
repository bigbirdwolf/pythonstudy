package com.jakewharton.rxbinding.widget;

import android.view.View;
import android.widget.AdapterView;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class AdapterViewItemClickEventOnSubscribe implements Observable.OnSubscribe<AdapterViewItemClickEvent> {
    final AdapterView<?> view;

    public AdapterViewItemClickEventOnSubscribe(AdapterView<?> adapterView) {
        this.view = adapterView;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super AdapterViewItemClickEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.jakewharton.rxbinding.widget.AdapterViewItemClickEventOnSubscribe.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(AdapterViewItemClickEvent.create(adapterView, view, i, j));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.AdapterViewItemClickEventOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                AdapterViewItemClickEventOnSubscribe.this.view.setOnItemClickListener(null);
            }
        });
        this.view.setOnItemClickListener(onItemClickListener);
    }
}