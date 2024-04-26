package com.jakewharton.rxbinding.widget;

import android.support.annotation.Nullable;
import android.widget.SeekBar;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class SeekBarChangeOnSubscribe implements Observable.OnSubscribe<Integer> {
    @Nullable
    final Boolean shouldBeFromUser;
    final SeekBar view;

    public SeekBarChangeOnSubscribe(SeekBar seekBar, @Nullable Boolean bool) {
        this.view = seekBar;
        this.shouldBeFromUser = bool;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Integer> subscriber) {
        MainThreadSubscription.verifyMainThread();
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.jakewharton.rxbinding.widget.SeekBarChangeOnSubscribe.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                if (SeekBarChangeOnSubscribe.this.shouldBeFromUser == null || SeekBarChangeOnSubscribe.this.shouldBeFromUser.booleanValue() == z) {
                    subscriber.onNext(Integer.valueOf(i));
                }
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.SeekBarChangeOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                SeekBarChangeOnSubscribe.this.view.setOnSeekBarChangeListener(null);
            }
        });
        this.view.setOnSeekBarChangeListener(onSeekBarChangeListener);
        subscriber.onNext(Integer.valueOf(this.view.getProgress()));
    }
}