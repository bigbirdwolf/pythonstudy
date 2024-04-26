package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.CompoundButton;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes.dex */
public final class RxCompoundButton {
    @CheckResult
    @NonNull
    public static Observable<Boolean> checkedChanges(@NonNull CompoundButton compoundButton) {
        Preconditions.checkNotNull(compoundButton, "view == null");
        return Observable.create(new CompoundButtonCheckedChangeOnSubscribe(compoundButton));
    }

    @CheckResult
    @NonNull
    public static Action1<? super Boolean> checked(@NonNull final CompoundButton compoundButton) {
        Preconditions.checkNotNull(compoundButton, "view == null");
        return new Action1<Boolean>() { // from class: com.jakewharton.rxbinding.widget.RxCompoundButton.1
            @Override // rx.functions.Action1
            public void call(Boolean bool) {
                compoundButton.setChecked(bool.booleanValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Object> toggle(@NonNull final CompoundButton compoundButton) {
        Preconditions.checkNotNull(compoundButton, "view == null");
        return new Action1<Object>() { // from class: com.jakewharton.rxbinding.widget.RxCompoundButton.2
            @Override // rx.functions.Action1
            public void call(Object obj) {
                compoundButton.toggle();
            }
        };
    }

    private RxCompoundButton() {
        throw new AssertionError("No instances.");
    }
}