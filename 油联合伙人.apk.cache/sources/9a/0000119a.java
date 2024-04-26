package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.CheckedTextView;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.functions.Action1;

/* loaded from: classes.dex */
public final class RxCheckedTextView {
    @CheckResult
    @NonNull
    public static Action1<? super Boolean> check(@NonNull final CheckedTextView checkedTextView) {
        Preconditions.checkNotNull(checkedTextView, "view == null");
        return new Action1<Boolean>() { // from class: com.jakewharton.rxbinding.widget.RxCheckedTextView.1
            @Override // rx.functions.Action1
            public void call(Boolean bool) {
                checkedTextView.setChecked(bool.booleanValue());
            }
        };
    }

    private RxCheckedTextView() {
        throw new AssertionError("No instances.");
    }
}