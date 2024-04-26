package com.jakewharton.rxbinding.widget;

import android.view.View;
import android.widget.AdapterView;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;
import rx.functions.Func1;

/* loaded from: classes.dex */
final class AdapterViewItemLongClickEventOnSubscribe implements Observable.OnSubscribe<AdapterViewItemLongClickEvent> {
    final Func1<? super AdapterViewItemLongClickEvent, Boolean> handled;
    final AdapterView<?> view;

    public AdapterViewItemLongClickEventOnSubscribe(AdapterView<?> adapterView, Func1<? super AdapterViewItemLongClickEvent, Boolean> func1) {
        this.view = adapterView;
        this.handled = func1;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super AdapterViewItemLongClickEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() { // from class: com.jakewharton.rxbinding.widget.AdapterViewItemLongClickEventOnSubscribe.1
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                AdapterViewItemLongClickEvent create = AdapterViewItemLongClickEvent.create(adapterView, view, i, j);
                if (AdapterViewItemLongClickEventOnSubscribe.this.handled.call(create).booleanValue()) {
                    if (subscriber.isUnsubscribed()) {
                        return true;
                    }
                    subscriber.onNext(create);
                    return true;
                }
                return false;
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.AdapterViewItemLongClickEventOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                AdapterViewItemLongClickEventOnSubscribe.this.view.setOnItemLongClickListener(null);
            }
        });
        this.view.setOnItemLongClickListener(onItemLongClickListener);
    }
}