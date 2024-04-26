package com.jakewharton.rxbinding.widget;

import android.widget.RatingBar;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class RatingBarRatingChangeEventOnSubscribe implements Observable.OnSubscribe<RatingBarChangeEvent> {
    final RatingBar view;

    public RatingBarRatingChangeEventOnSubscribe(RatingBar ratingBar) {
        this.view = ratingBar;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super RatingBarChangeEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        RatingBar.OnRatingBarChangeListener onRatingBarChangeListener = new RatingBar.OnRatingBarChangeListener() { // from class: com.jakewharton.rxbinding.widget.RatingBarRatingChangeEventOnSubscribe.1
            @Override // android.widget.RatingBar.OnRatingBarChangeListener
            public void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(RatingBarChangeEvent.create(ratingBar, f, z));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.RatingBarRatingChangeEventOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                RatingBarRatingChangeEventOnSubscribe.this.view.setOnRatingBarChangeListener(null);
            }
        });
        this.view.setOnRatingBarChangeListener(onRatingBarChangeListener);
        subscriber.onNext(RatingBarChangeEvent.create(this.view, this.view.getRating(), false));
    }
}