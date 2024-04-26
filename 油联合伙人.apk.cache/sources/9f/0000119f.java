package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.PopupMenu;
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