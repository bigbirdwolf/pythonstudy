package com.jakewharton.rxbinding.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class RecyclerAdapterDataChangeOnSubscribe<T extends RecyclerView.Adapter<? extends RecyclerView.ViewHolder>> implements Observable.OnSubscribe<T> {
    final T adapter;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecyclerAdapterDataChangeOnSubscribe(T t) {
        this.adapter = t;
    }

    public void call(final Subscriber<? super T> subscriber) {
        MainThreadSubscription.verifyMainThread();
        final RecyclerView.AdapterDataObserver adapterDataObserver = new RecyclerView.AdapterDataObserver() { // from class: com.jakewharton.rxbinding.support.v7.widget.RecyclerAdapterDataChangeOnSubscribe.1
            @Override // android.support.v7.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(RecyclerAdapterDataChangeOnSubscribe.this.adapter);
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.v7.widget.RecyclerAdapterDataChangeOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                RecyclerAdapterDataChangeOnSubscribe.this.adapter.unregisterAdapterDataObserver(adapterDataObserver);
            }
        });
        this.adapter.registerAdapterDataObserver(adapterDataObserver);
        subscriber.onNext(this.adapter);
    }
}