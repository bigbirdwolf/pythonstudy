package com.jakewharton.rxbinding.support.v4.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import com.jakewharton.rxbinding.internal.Preconditions;
import com.jakewharton.rxbinding.view.ViewScrollChangeEvent;
import rx.Observable;

/* loaded from: classes.dex */
public final class RxNestedScrollView {
    @CheckResult
    @NonNull
    public static Observable<ViewScrollChangeEvent> scrollChangeEvents(@NonNull NestedScrollView nestedScrollView) {
        Preconditions.checkNotNull(nestedScrollView, "view == null");
        return Observable.create(new NestedScrollViewScrollChangeEventOnSubscribe(nestedScrollView));
    }

    private RxNestedScrollView() {
        throw new AssertionError("No instances.");
    }
}