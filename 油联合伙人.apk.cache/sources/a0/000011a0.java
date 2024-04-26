package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.ProgressBar;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.functions.Action1;

/* loaded from: classes.dex */
public final class RxProgressBar {
    @CheckResult
    @NonNull
    public static Action1<? super Integer> incrementProgressBy(@NonNull final ProgressBar progressBar) {
        Preconditions.checkNotNull(progressBar, "view == null");
        return new Action1<Integer>() { // from class: com.jakewharton.rxbinding.widget.RxProgressBar.1
            @Override // rx.functions.Action1
            public void call(Integer num) {
                progressBar.incrementProgressBy(num.intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Integer> incrementSecondaryProgressBy(@NonNull final ProgressBar progressBar) {
        Preconditions.checkNotNull(progressBar, "view == null");
        return new Action1<Integer>() { // from class: com.jakewharton.rxbinding.widget.RxProgressBar.2
            @Override // rx.functions.Action1
            public void call(Integer num) {
                progressBar.incrementSecondaryProgressBy(num.intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Boolean> indeterminate(@NonNull final ProgressBar progressBar) {
        Preconditions.checkNotNull(progressBar, "view == null");
        return new Action1<Boolean>() { // from class: com.jakewharton.rxbinding.widget.RxProgressBar.3
            @Override // rx.functions.Action1
            public void call(Boolean bool) {
                progressBar.setIndeterminate(bool.booleanValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Integer> max(@NonNull final ProgressBar progressBar) {
        Preconditions.checkNotNull(progressBar, "view == null");
        return new Action1<Integer>() { // from class: com.jakewharton.rxbinding.widget.RxProgressBar.4
            @Override // rx.functions.Action1
            public void call(Integer num) {
                progressBar.setMax(num.intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Integer> progress(@NonNull final ProgressBar progressBar) {
        Preconditions.checkNotNull(progressBar, "view == null");
        return new Action1<Integer>() { // from class: com.jakewharton.rxbinding.widget.RxProgressBar.5
            @Override // rx.functions.Action1
            public void call(Integer num) {
                progressBar.setProgress(num.intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Integer> secondaryProgress(@NonNull final ProgressBar progressBar) {
        Preconditions.checkNotNull(progressBar, "view == null");
        return new Action1<Integer>() { // from class: com.jakewharton.rxbinding.widget.RxProgressBar.6
            @Override // rx.functions.Action1
            public void call(Integer num) {
                progressBar.setSecondaryProgress(num.intValue());
            }
        };
    }

    private RxProgressBar() {
        throw new AssertionError("No instances.");
    }
}