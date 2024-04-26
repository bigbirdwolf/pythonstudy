package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.TextView;
import com.jakewharton.rxbinding.internal.Functions;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/* loaded from: classes.dex */
public final class RxTextView {
    @CheckResult
    @NonNull
    public static Observable<Integer> editorActions(@NonNull TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return editorActions(textView, Functions.FUNC1_ALWAYS_TRUE);
    }

    @CheckResult
    @NonNull
    public static Observable<Integer> editorActions(@NonNull TextView textView, @NonNull Func1<? super Integer, Boolean> func1) {
        Preconditions.checkNotNull(textView, "view == null");
        Preconditions.checkNotNull(func1, "handled == null");
        return Observable.create(new TextViewEditorActionOnSubscribe(textView, func1));
    }

    @CheckResult
    @NonNull
    public static Observable<TextViewEditorActionEvent> editorActionEvents(@NonNull TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return editorActionEvents(textView, Functions.FUNC1_ALWAYS_TRUE);
    }

    @CheckResult
    @NonNull
    public static Observable<TextViewEditorActionEvent> editorActionEvents(@NonNull TextView textView, @NonNull Func1<? super TextViewEditorActionEvent, Boolean> func1) {
        Preconditions.checkNotNull(textView, "view == null");
        Preconditions.checkNotNull(func1, "handled == null");
        return Observable.create(new TextViewEditorActionEventOnSubscribe(textView, func1));
    }

    @CheckResult
    @NonNull
    public static Observable<CharSequence> textChanges(@NonNull TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return Observable.create(new TextViewTextOnSubscribe(textView));
    }

    @CheckResult
    @NonNull
    public static Observable<TextViewTextChangeEvent> textChangeEvents(@NonNull TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return Observable.create(new TextViewTextChangeEventOnSubscribe(textView));
    }

    @CheckResult
    @NonNull
    public static Observable<TextViewBeforeTextChangeEvent> beforeTextChangeEvents(@NonNull TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return Observable.create(new TextViewBeforeTextChangeEventOnSubscribe(textView));
    }

    @CheckResult
    @NonNull
    public static Observable<TextViewAfterTextChangeEvent> afterTextChangeEvents(@NonNull TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return Observable.create(new TextViewAfterTextChangeEventOnSubscribe(textView));
    }

    @CheckResult
    @NonNull
    public static Action1<? super CharSequence> text(@NonNull final TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return new Action1<CharSequence>() { // from class: com.jakewharton.rxbinding.widget.RxTextView.1
            @Override // rx.functions.Action1
            public void call(CharSequence charSequence) {
                textView.setText(charSequence);
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Integer> textRes(@NonNull final TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return new Action1<Integer>() { // from class: com.jakewharton.rxbinding.widget.RxTextView.2
            @Override // rx.functions.Action1
            public void call(Integer num) {
                textView.setText(num.intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super CharSequence> error(@NonNull final TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return new Action1<CharSequence>() { // from class: com.jakewharton.rxbinding.widget.RxTextView.3
            @Override // rx.functions.Action1
            public void call(CharSequence charSequence) {
                textView.setError(charSequence);
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Integer> errorRes(@NonNull final TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return new Action1<Integer>() { // from class: com.jakewharton.rxbinding.widget.RxTextView.4
            @Override // rx.functions.Action1
            public void call(Integer num) {
                textView.setError(textView.getContext().getResources().getText(num.intValue()));
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super CharSequence> hint(@NonNull final TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return new Action1<CharSequence>() { // from class: com.jakewharton.rxbinding.widget.RxTextView.5
            @Override // rx.functions.Action1
            public void call(CharSequence charSequence) {
                textView.setHint(charSequence);
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Integer> hintRes(@NonNull final TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return new Action1<Integer>() { // from class: com.jakewharton.rxbinding.widget.RxTextView.6
            @Override // rx.functions.Action1
            public void call(Integer num) {
                textView.setHint(num.intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Integer> color(@NonNull final TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return new Action1<Integer>() { // from class: com.jakewharton.rxbinding.widget.RxTextView.7
            @Override // rx.functions.Action1
            public void call(Integer num) {
                textView.setTextColor(num.intValue());
            }
        };
    }

    private RxTextView() {
        throw new AssertionError("No instances.");
    }
}