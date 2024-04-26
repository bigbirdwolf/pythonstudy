package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.TextSwitcher;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.functions.Action1;

/* loaded from: classes.dex */
public final class RxTextSwitcher {
    @CheckResult
    @NonNull
    public static Action1<? super CharSequence> text(@NonNull final TextSwitcher textSwitcher) {
        Preconditions.checkNotNull(textSwitcher, "view == null");
        return new Action1<CharSequence>() { // from class: com.jakewharton.rxbinding.widget.RxTextSwitcher.1
            @Override // rx.functions.Action1
            public void call(CharSequence charSequence) {
                textSwitcher.setText(charSequence);
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super CharSequence> currentText(@NonNull final TextSwitcher textSwitcher) {
        Preconditions.checkNotNull(textSwitcher, "view == null");
        return new Action1<CharSequence>() { // from class: com.jakewharton.rxbinding.widget.RxTextSwitcher.2
            @Override // rx.functions.Action1
            public void call(CharSequence charSequence) {
                textSwitcher.setCurrentText(charSequence);
            }
        };
    }

    private RxTextSwitcher() {
        throw new AssertionError("No instances.");
    }
}