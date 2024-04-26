package com.jakewharton.rxbinding.support.design.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;

/* loaded from: classes.dex */
public final class RxSwipeDismissBehavior {
    @CheckResult
    @NonNull
    public static Observable<View> dismisses(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return Observable.create(new SwipeDismissBehaviorOnSubscribe(view));
    }

    private RxSwipeDismissBehavior() {
        throw new AssertionError("No instances.");
    }
}