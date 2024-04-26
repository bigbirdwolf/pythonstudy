package com.jakewharton.rxbinding.support.design.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;

/* loaded from: classes.dex */
public final class RxNavigationView {
    @CheckResult
    @NonNull
    public static Observable<MenuItem> itemSelections(@NonNull NavigationView navigationView) {
        Preconditions.checkNotNull(navigationView, "view == null");
        return Observable.create(new NavigationViewItemSelectionsOnSubscribe(navigationView));
    }

    private RxNavigationView() {
        throw new AssertionError("No instances.");
    }
}