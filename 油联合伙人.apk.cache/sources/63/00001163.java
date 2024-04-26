package com.jakewharton.rxbinding.widget;

import android.database.DataSetObserver;
import android.widget.Adapter;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class AdapterDataChangeOnSubscribe<T extends Adapter> implements Observable.OnSubscribe<T> {
    final T adapter;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public AdapterDataChangeOnSubscribe(T t) {
        this.adapter = t;
    }

    public void call(final Subscriber<? super T> subscriber) {
        MainThreadSubscription.verifyMainThread();
        final DataSetObserver dataSetObserver = new DataSetObserver() { // from class: com.jakewharton.rxbinding.widget.AdapterDataChangeOnSubscribe.1
            @Override // android.database.DataSetObserver
            public void onChanged() {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(AdapterDataChangeOnSubscribe.this.adapter);
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.AdapterDataChangeOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                AdapterDataChangeOnSubscribe.this.adapter.unregisterDataSetObserver(dataSetObserver);
            }
        });
        this.adapter.registerDataSetObserver(dataSetObserver);
        subscriber.onNext(this.adapter);
    }
}