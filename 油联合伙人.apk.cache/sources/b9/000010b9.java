package com.jakewharton.rxbinding.support.v4.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v4.widget.SlidingPaneLayout;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes.dex */
public final class RxSlidingPaneLayout {
    @CheckResult
    @NonNull
    public static Observable<Boolean> panelOpens(@NonNull SlidingPaneLayout slidingPaneLayout) {
        Preconditions.checkNotNull(slidingPaneLayout, "view == null");
        return Observable.create(new SlidingPaneLayoutPaneOpenedOnSubscribe(slidingPaneLayout));
    }

    @CheckResult
    @NonNull
    public static Observable<Float> panelSlides(@NonNull SlidingPaneLayout slidingPaneLayout) {
        Preconditions.checkNotNull(slidingPaneLayout, "view == null");
        return Observable.create(new SlidingPaneLayoutSlideOnSubscribe(slidingPaneLayout));
    }

    @CheckResult
    @NonNull
    public static Action1<? super Boolean> open(@NonNull final SlidingPaneLayout slidingPaneLayout) {
        Preconditions.checkNotNull(slidingPaneLayout, "view == null");
        return new Action1<Boolean>() { // from class: com.jakewharton.rxbinding.support.v4.widget.RxSlidingPaneLayout.1
            @Override // rx.functions.Action1
            public void call(Boolean bool) {
                if (bool.booleanValue()) {
                    SlidingPaneLayout.this.openPane();
                } else {
                    SlidingPaneLayout.this.closePane();
                }
            }
        };
    }

    private RxSlidingPaneLayout() {
        throw new AssertionError("No instances.");
    }
}