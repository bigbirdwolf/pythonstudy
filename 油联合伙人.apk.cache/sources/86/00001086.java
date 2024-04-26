package com.jakewharton.rxbinding.support.design.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;

/* loaded from: classes.dex */
public final class RxAppBarLayout {
    @CheckResult
    @NonNull
    public static Observable<Integer> offsetChanges(@NonNull AppBarLayout appBarLayout) {
        Preconditions.checkNotNull(appBarLayout, "view == null");
        return Observable.create(new AppBarLayoutOffsetChangeOnSubscribe(appBarLayout));
    }

    private RxAppBarLayout() {
        throw new AssertionError("No instances.");
    }
}