package com.jakewharton.rxbinding.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class AutoCompleteTextViewItemClickEventOnSubscribe implements Observable.OnSubscribe<AdapterViewItemClickEvent> {
    final AutoCompleteTextView view;

    public AutoCompleteTextViewItemClickEventOnSubscribe(AutoCompleteTextView autoCompleteTextView) {
        this.view = autoCompleteTextView;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super AdapterViewItemClickEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.jakewharton.rxbinding.widget.AutoCompleteTextViewItemClickEventOnSubscribe.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(AdapterViewItemClickEvent.create(adapterView, view, i, j));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.AutoCompleteTextViewItemClickEventOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                AutoCompleteTextViewItemClickEventOnSubscribe.this.view.setOnItemClickListener(null);
            }
        });
        this.view.setOnItemClickListener(onItemClickListener);
    }
}