package com.jakewharton.rxbinding.support.design.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.functions.Action1;

/* loaded from: classes.dex */
public final class RxTextInputLayout {
    @CheckResult
    @NonNull
    public static Action1<? super Boolean> counterEnabled(@NonNull final TextInputLayout textInputLayout) {
        Preconditions.checkNotNull(textInputLayout, "view == null");
        return new Action1<Boolean>() { // from class: com.jakewharton.rxbinding.support.design.widget.RxTextInputLayout.1
            @Override // rx.functions.Action1
            public void call(Boolean bool) {
                TextInputLayout.this.setCounterEnabled(bool.booleanValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Integer> counterMaxLength(@NonNull final TextInputLayout textInputLayout) {
        Preconditions.checkNotNull(textInputLayout, "view == null");
        return new Action1<Integer>() { // from class: com.jakewharton.rxbinding.support.design.widget.RxTextInputLayout.2
            @Override // rx.functions.Action1
            public void call(Integer num) {
                TextInputLayout.this.setCounterMaxLength(num.intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super CharSequence> error(@NonNull final TextInputLayout textInputLayout) {
        Preconditions.checkNotNull(textInputLayout, "view == null");
        return new Action1<CharSequence>() { // from class: com.jakewharton.rxbinding.support.design.widget.RxTextInputLayout.3
            @Override // rx.functions.Action1
            public void call(CharSequence charSequence) {
                TextInputLayout.this.setError(charSequence);
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Integer> errorRes(@NonNull final TextInputLayout textInputLayout) {
        Preconditions.checkNotNull(textInputLayout, "view == null");
        return new Action1<Integer>() { // from class: com.jakewharton.rxbinding.support.design.widget.RxTextInputLayout.4
            @Override // rx.functions.Action1
            public void call(Integer num) {
                TextInputLayout.this.setError(TextInputLayout.this.getContext().getResources().getText(num.intValue()));
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super CharSequence> hint(@NonNull final TextInputLayout textInputLayout) {
        Preconditions.checkNotNull(textInputLayout, "view == null");
        return new Action1<CharSequence>() { // from class: com.jakewharton.rxbinding.support.design.widget.RxTextInputLayout.5
            @Override // rx.functions.Action1
            public void call(CharSequence charSequence) {
                TextInputLayout.this.setHint(charSequence);
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Integer> hintRes(@NonNull final TextInputLayout textInputLayout) {
        Preconditions.checkNotNull(textInputLayout, "view == null");
        return new Action1<Integer>() { // from class: com.jakewharton.rxbinding.support.design.widget.RxTextInputLayout.6
            @Override // rx.functions.Action1
            public void call(Integer num) {
                TextInputLayout.this.setHint(TextInputLayout.this.getContext().getResources().getText(num.intValue()));
            }
        };
    }

    private RxTextInputLayout() {
        throw new AssertionError("No instances.");
    }
}