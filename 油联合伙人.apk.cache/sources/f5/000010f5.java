package com.jakewharton.rxbinding.support.v7.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;

/* loaded from: classes.dex */
public final class RxRecyclerView {
    @CheckResult
    @NonNull
    public static Observable<RecyclerViewChildAttachStateChangeEvent> childAttachStateChangeEvents(@NonNull RecyclerView recyclerView) {
        Preconditions.checkNotNull(recyclerView, "view == null");
        return Observable.create(new RecyclerViewChildAttachStateChangeEventOnSubscribe(recyclerView));
    }

    @CheckResult
    @NonNull
    public static Observable<RecyclerViewScrollEvent> scrollEvents(@NonNull RecyclerView recyclerView) {
        Preconditions.checkNotNull(recyclerView, "view == null");
        return Observable.create(new RecyclerViewScrollEventOnSubscribe(recyclerView));
    }

    @CheckResult
    @NonNull
    public static Observable<Integer> scrollStateChanges(@NonNull RecyclerView recyclerView) {
        Preconditions.checkNotNull(recyclerView, "view == null");
        return Observable.create(new RecyclerViewScrollStateChangeOnSubscribe(recyclerView));
    }

    private RxRecyclerView() {
        throw new AssertionError("No instances.");
    }
}