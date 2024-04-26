package com.jakewharton.rxbinding.widget;

import android.widget.SeekBar;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class SeekBarChangeEventOnSubscribe implements Observable.OnSubscribe<SeekBarChangeEvent> {
    final SeekBar view;

    public SeekBarChangeEventOnSubscribe(SeekBar seekBar) {
        this.view = seekBar;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super SeekBarChangeEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.jakewharton.rxbinding.widget.SeekBarChangeEventOnSubscribe.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(SeekBarProgressChangeEvent.create(seekBar, i, z));
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(SeekBarStartChangeEvent.create(seekBar));
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(SeekBarStopChangeEvent.create(seekBar));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.SeekBarChangeEventOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                SeekBarChangeEventOnSubscribe.this.view.setOnSeekBarChangeListener(null);
            }
        });
        this.view.setOnSeekBarChangeListener(onSeekBarChangeListener);
        subscriber.onNext(SeekBarProgressChangeEvent.create(this.view, this.view.getProgress(), false));
    }
}