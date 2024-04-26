package com.jakewharton.rxbinding.support.v7.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;

/* loaded from: classes.dex */
public final class RxRecyclerViewAdapter {
    @CheckResult
    @NonNull
    public static <T extends RecyclerView.Adapter<? extends RecyclerView.ViewHolder>> Observable<T> dataChanges(@NonNull T t) {
        Preconditions.checkNotNull(t, "adapter == null");
        return Observable.create(new RecyclerAdapterDataChangeOnSubscribe(t));
    }

    private RxRecyclerViewAdapter() {
        throw new AssertionError("No instances.");
    }
}