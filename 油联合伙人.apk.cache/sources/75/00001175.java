package com.jakewharton.rxbinding.widget;

import android.view.View;
import android.widget.AdapterView;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class AdapterViewItemSelectionOnSubscribe implements Observable.OnSubscribe<Integer> {
    final AdapterView<?> view;

    public AdapterViewItemSelectionOnSubscribe(AdapterView<?> adapterView) {
        this.view = adapterView;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Integer> subscriber) {
        MainThreadSubscription.verifyMainThread();
        AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() { // from class: com.jakewharton.rxbinding.widget.AdapterViewItemSelectionOnSubscribe.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(Integer.valueOf(i));
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(-1);
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.AdapterViewItemSelectionOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                AdapterViewItemSelectionOnSubscribe.this.view.setOnItemSelectedListener(null);
            }
        });
        this.view.setOnItemSelectedListener(onItemSelectedListener);
        subscriber.onNext(Integer.valueOf(this.view.getSelectedItemPosition()));
    }
}