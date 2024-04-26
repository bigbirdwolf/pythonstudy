package com.jakewharton.rxbinding.support.v7.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;

/* loaded from: classes.dex */
public final class RxPopupMenu {
    @CheckResult
    @NonNull
    public static Observable<MenuItem> itemClicks(@NonNull PopupMenu popupMenu) {
        Preconditions.checkNotNull(popupMenu, "view == null");
        return Observable.create(new PopupMenuItemClickOnSubscribe(popupMenu));
    }

    @CheckResult
    @NonNull
    public static Observable<Void> dismisses(@NonNull PopupMenu popupMenu) {
        Preconditions.checkNotNull(popupMenu, "view == null");
        return Observable.create(new PopupMenuDismissOnSubscribe(popupMenu));
    }

    private RxPopupMenu() {
        throw new AssertionError("No instances.");
    }
}