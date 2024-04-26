package com.jakewharton.rxbinding.support.v7.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.ActionMenuView;
import android.view.MenuItem;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;

/* loaded from: classes.dex */
public final class RxActionMenuView {
    @CheckResult
    @NonNull
    public static Observable<MenuItem> itemClicks(@NonNull ActionMenuView actionMenuView) {
        Preconditions.checkNotNull(actionMenuView, "view == null");
        return Observable.create(new ActionMenuViewItemClickOnSubscribe(actionMenuView));
    }

    private RxActionMenuView() {
        throw new AssertionError("No instances.");
    }
}