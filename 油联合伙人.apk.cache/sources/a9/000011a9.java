package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.RatingBar;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes.dex */
public final class RxRatingBar {
    @CheckResult
    @NonNull
    public static Observable<Float> ratingChanges(@NonNull RatingBar ratingBar) {
        Preconditions.checkNotNull(ratingBar, "view == null");
        return Observable.create(new RatingBarRatingChangeOnSubscribe(ratingBar));
    }

    @CheckResult
    @NonNull
    public static Observable<RatingBarChangeEvent> ratingChangeEvents(@NonNull RatingBar ratingBar) {
        Preconditions.checkNotNull(ratingBar, "view == null");
        return Observable.create(new RatingBarRatingChangeEventOnSubscribe(ratingBar));
    }

    @CheckResult
    @NonNull
    public static Action1<? super Float> rating(@NonNull final RatingBar ratingBar) {
        Preconditions.checkNotNull(ratingBar, "view == null");
        return new Action1<Float>() { // from class: com.jakewharton.rxbinding.widget.RxRatingBar.1
            @Override // rx.functions.Action1
            public void call(Float f) {
                ratingBar.setRating(f.floatValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Boolean> isIndicator(@NonNull final RatingBar ratingBar) {
        Preconditions.checkNotNull(ratingBar, "view == null");
        return new Action1<Boolean>() { // from class: com.jakewharton.rxbinding.widget.RxRatingBar.2
            @Override // rx.functions.Action1
            public void call(Boolean bool) {
                ratingBar.setIsIndicator(bool.booleanValue());
            }
        };
    }

    private RxRatingBar() {
        throw new AssertionError("No instances.");
    }
}