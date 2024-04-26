package com.jakewharton.rxbinding.support.design.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;

/* loaded from: classes.dex */
public final class RxBottomNavigationView {
    @CheckResult
    @NonNull
    public static Observable<MenuItem> itemSelections(@NonNull BottomNavigationView bottomNavigationView) {
        Preconditions.checkNotNull(bottomNavigationView, "view == null");
        return Observable.create(new BottomNavigationViewItemSelectionsOnSubscribe(bottomNavigationView));
    }

    private RxBottomNavigationView() {
        throw new AssertionError("No instances.");
    }
}