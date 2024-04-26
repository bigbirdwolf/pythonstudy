package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.AutoCompleteTextView;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes.dex */
public final class RxAutoCompleteTextView {
    @CheckResult
    @NonNull
    public static Observable<AdapterViewItemClickEvent> itemClickEvents(@NonNull AutoCompleteTextView autoCompleteTextView) {
        Preconditions.checkNotNull(autoCompleteTextView, "view == null");
        return Observable.create(new AutoCompleteTextViewItemClickEventOnSubscribe(autoCompleteTextView));
    }

    @CheckResult
    @NonNull
    public static Action1<? super CharSequence> completionHint(@NonNull final AutoCompleteTextView autoCompleteTextView) {
        Preconditions.checkNotNull(autoCompleteTextView, "view == null");
        return new Action1<CharSequence>() { // from class: com.jakewharton.rxbinding.widget.RxAutoCompleteTextView.1
            @Override // rx.functions.Action1
            public void call(CharSequence charSequence) {
                autoCompleteTextView.setCompletionHint(charSequence);
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Integer> threshold(@NonNull final AutoCompleteTextView autoCompleteTextView) {
        Preconditions.checkNotNull(autoCompleteTextView, "view == null");
        return new Action1<Integer>() { // from class: com.jakewharton.rxbinding.widget.RxAutoCompleteTextView.2
            @Override // rx.functions.Action1
            public void call(Integer num) {
                autoCompleteTextView.setThreshold(num.intValue());
            }
        };
    }

    private RxAutoCompleteTextView() {
        throw new AssertionError("No instances.");
    }
}