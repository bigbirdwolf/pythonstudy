package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.RatingBar;
import com.jakewharton.rxbinding.view.ViewEvent;

/* loaded from: classes.dex */
public final class RatingBarChangeEvent extends ViewEvent<RatingBar> {
    private final boolean fromUser;
    private final float rating;

    @CheckResult
    @NonNull
    public static RatingBarChangeEvent create(@NonNull RatingBar ratingBar, float f, boolean z) {
        return new RatingBarChangeEvent(ratingBar, f, z);
    }

    private RatingBarChangeEvent(@NonNull RatingBar ratingBar, float f, boolean z) {
        super(ratingBar);
        this.rating = f;
        this.fromUser = z;
    }

    public float rating() {
        return this.rating;
    }

    public boolean fromUser() {
        return this.fromUser;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RatingBarChangeEvent) {
            RatingBarChangeEvent ratingBarChangeEvent = (RatingBarChangeEvent) obj;
            return ratingBarChangeEvent.view() == view() && ratingBarChangeEvent.rating == this.rating && ratingBarChangeEvent.fromUser == this.fromUser;
        }
        return false;
    }

    public int hashCode() {
        return ((((629 + view().hashCode()) * 37) + Float.floatToIntBits(this.rating)) * 37) + (this.fromUser ? 1 : 0);
    }

    public String toString() {
        return "RatingBarChangeEvent{view=" + view() + ", rating=" + this.rating + ", fromUser=" + this.fromUser + '}';
    }
}