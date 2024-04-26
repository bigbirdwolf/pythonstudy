package com.jakewharton.rxbinding.support.v4.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes.dex */
public final class RxSwipeRefreshLayout {
    @CheckResult
    @NonNull
    public static Observable<Void> refreshes(@NonNull SwipeRefreshLayout swipeRefreshLayout) {
        Preconditions.checkNotNull(swipeRefreshLayout, "view == null");
        return Observable.create(new SwipeRefreshLayoutRefreshOnSubscribe(swipeRefreshLayout));
    }

    @CheckResult
    @NonNull
    public static Action1<? super Boolean> refreshing(@NonNull final SwipeRefreshLayout swipeRefreshLayout) {
        Preconditions.checkNotNull(swipeRefreshLayout, "view == null");
        return new Action1<Boolean>() { // from class: com.jakewharton.rxbinding.support.v4.widget.RxSwipeRefreshLayout.1
            @Override // rx.functions.Action1
            public void call(Boolean bool) {
                SwipeRefreshLayout.this.setRefreshing(bool.booleanValue());
            }
        };
    }

    private RxSwipeRefreshLayout() {
        throw new AssertionError("No instances.");
    }
}