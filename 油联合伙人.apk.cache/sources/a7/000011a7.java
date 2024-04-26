package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.RadioGroup;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes.dex */
public final class RxRadioGroup {
    @CheckResult
    @NonNull
    public static Observable<Integer> checkedChanges(@NonNull RadioGroup radioGroup) {
        Preconditions.checkNotNull(radioGroup, "view == null");
        return Observable.create(new RadioGroupCheckedChangeOnSubscribe(radioGroup)).distinctUntilChanged();
    }

    @CheckResult
    @NonNull
    public static Action1<? super Integer> checked(@NonNull final RadioGroup radioGroup) {
        Preconditions.checkNotNull(radioGroup, "view == null");
        return new Action1<Integer>() { // from class: com.jakewharton.rxbinding.widget.RxRadioGroup.1
            @Override // rx.functions.Action1
            public void call(Integer num) {
                if (num.intValue() == -1) {
                    radioGroup.clearCheck();
                } else {
                    radioGroup.check(num.intValue());
                }
            }
        };
    }

    private RxRadioGroup() {
        throw new AssertionError("No instances.");
    }
}