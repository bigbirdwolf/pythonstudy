package com.jakewharton.rxbinding.widget;

import android.widget.RatingBar;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class RatingBarRatingChangeOnSubscribe implements Observable.OnSubscribe<Float> {
    final RatingBar view;

    public RatingBarRatingChangeOnSubscribe(RatingBar ratingBar) {
        this.view = ratingBar;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Float> subscriber) {
        MainThreadSubscription.verifyMainThread();
        RatingBar.OnRatingBarChangeListener onRatingBarChangeListener = new RatingBar.OnRatingBarChangeListener() { // from class: com.jakewharton.rxbinding.widget.RatingBarRatingChangeOnSubscribe.1
            @Override // android.widget.RatingBar.OnRatingBarChangeListener
            public void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(Float.valueOf(f));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.RatingBarRatingChangeOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                RatingBarRatingChangeOnSubscribe.this.view.setOnRatingBarChangeListener(null);
            }
        });
        this.view.setOnRatingBarChangeListener(onRatingBarChangeListener);
        subscriber.onNext(Float.valueOf(this.view.getRating()));
    }
}