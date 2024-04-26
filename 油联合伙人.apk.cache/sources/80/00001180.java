package com.jakewharton.rxbinding.widget;

import android.widget.CompoundButton;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class CompoundButtonCheckedChangeOnSubscribe implements Observable.OnSubscribe<Boolean> {
    final CompoundButton view;

    public CompoundButtonCheckedChangeOnSubscribe(CompoundButton compoundButton) {
        this.view = compoundButton;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Boolean> subscriber) {
        MainThreadSubscription.verifyMainThread();
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.jakewharton.rxbinding.widget.CompoundButtonCheckedChangeOnSubscribe.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(Boolean.valueOf(z));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.CompoundButtonCheckedChangeOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                CompoundButtonCheckedChangeOnSubscribe.this.view.setOnCheckedChangeListener(null);
            }
        });
        this.view.setOnCheckedChangeListener(onCheckedChangeListener);
        subscriber.onNext(Boolean.valueOf(this.view.isChecked()));
    }
}