package com.jakewharton.rxbinding.widget;

import android.widget.RadioGroup;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class RadioGroupCheckedChangeOnSubscribe implements Observable.OnSubscribe<Integer> {
    final RadioGroup view;

    public RadioGroupCheckedChangeOnSubscribe(RadioGroup radioGroup) {
        this.view = radioGroup;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Integer> subscriber) {
        MainThreadSubscription.verifyMainThread();
        RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() { // from class: com.jakewharton.rxbinding.widget.RadioGroupCheckedChangeOnSubscribe.1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(Integer.valueOf(i));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.RadioGroupCheckedChangeOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                RadioGroupCheckedChangeOnSubscribe.this.view.setOnCheckedChangeListener(null);
            }
        });
        this.view.setOnCheckedChangeListener(onCheckedChangeListener);
        subscriber.onNext(Integer.valueOf(this.view.getCheckedRadioButtonId()));
    }
}