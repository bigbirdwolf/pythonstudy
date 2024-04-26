package com.jakewharton.rxbinding.support.v4.view;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import com.jakewharton.rxbinding.internal.Functions;
import com.jakewharton.rxbinding.internal.Preconditions;
import com.jakewharton.rxbinding.view.MenuItemActionViewEvent;
import rx.Observable;
import rx.functions.Func1;

/* loaded from: classes.dex */
public final class RxMenuItemCompat {
    @CheckResult
    @NonNull
    public static Observable<MenuItemActionViewEvent> actionViewEvents(@NonNull MenuItem menuItem) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        return Observable.create(new MenuItemActionViewEventOnSubscribe(menuItem, Functions.FUNC1_ALWAYS_TRUE));
    }

    @CheckResult
    @NonNull
    public static Observable<MenuItemActionViewEvent> actionViewEvents(@NonNull MenuItem menuItem, @NonNull Func1<? super MenuItemActionViewEvent, Boolean> func1) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        Preconditions.checkNotNull(func1, "handled == null");
        return Observable.create(new MenuItemActionViewEventOnSubscribe(menuItem, func1));
    }

    private RxMenuItemCompat() {
        throw new AssertionError("No instances.");
    }
}