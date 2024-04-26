package com.jakewharton.rxbinding.widget;

import android.view.View;
import android.widget.AdapterView;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;
import rx.functions.Func0;

/* loaded from: classes.dex */
final class AdapterViewItemLongClickOnSubscribe implements Observable.OnSubscribe<Integer> {
    final Func0<Boolean> handled;
    final AdapterView<?> view;

    public AdapterViewItemLongClickOnSubscribe(AdapterView<?> adapterView, Func0<Boolean> func0) {
        this.view = adapterView;
        this.handled = func0;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Integer> subscriber) {
        MainThreadSubscription.verifyMainThread();
        AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() { // from class: com.jakewharton.rxbinding.widget.AdapterViewItemLongClickOnSubscribe.1
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (AdapterViewItemLongClickOnSubscribe.this.handled.call().booleanValue()) {
                    if (subscriber.isUnsubscribed()) {
                        return true;
                    }
                    subscriber.onNext(Integer.valueOf(i));
                    return true;
                }
                return false;
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.AdapterViewItemLongClickOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                AdapterViewItemLongClickOnSubscribe.this.view.setOnItemLongClickListener(null);
            }
        });
        this.view.setOnItemLongClickListener(onItemLongClickListener);
    }
}