package com.jakewharton.rxbinding.support.design.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;

/* loaded from: classes.dex */
public final class RxSnackbar {
    @CheckResult
    @NonNull
    public static Observable<Integer> dismisses(@NonNull Snackbar snackbar) {
        Preconditions.checkNotNull(snackbar, "view == null");
        return Observable.create(new SnackbarDismissesOnSubscribe(snackbar));
    }

    private RxSnackbar() {
        throw new AssertionError("No instances.");
    }
}