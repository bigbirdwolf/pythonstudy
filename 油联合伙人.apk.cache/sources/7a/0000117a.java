package com.jakewharton.rxbinding.widget;

import android.view.View;
import android.widget.AdapterView;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class AdapterViewSelectionOnSubscribe implements Observable.OnSubscribe<AdapterViewSelectionEvent> {
    final AdapterView<?> view;

    public AdapterViewSelectionOnSubscribe(AdapterView<?> adapterView) {
        this.view = adapterView;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super AdapterViewSelectionEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() { // from class: com.jakewharton.rxbinding.widget.AdapterViewSelectionOnSubscribe.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(AdapterViewItemSelectionEvent.create(adapterView, view, i, j));
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(AdapterViewNothingSelectionEvent.create(adapterView));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.AdapterViewSelectionOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                AdapterViewSelectionOnSubscribe.this.view.setOnItemSelectedListener(null);
            }
        });
        this.view.setOnItemSelectedListener(onItemSelectedListener);
        int selectedItemPosition = this.view.getSelectedItemPosition();
        if (selectedItemPosition == -1) {
            subscriber.onNext(AdapterViewNothingSelectionEvent.create(this.view));
            return;
        }
        subscriber.onNext(AdapterViewItemSelectionEvent.create(this.view, this.view.getSelectedView(), selectedItemPosition, this.view.getSelectedItemId()));
    }
}