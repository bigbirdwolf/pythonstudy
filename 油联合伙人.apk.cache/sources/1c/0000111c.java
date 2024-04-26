package com.jakewharton.rxbinding.view;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.jakewharton.rxbinding.internal.Functions;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

/* loaded from: classes.dex */
public final class RxView {
    @CheckResult
    @NonNull
    public static Observable<Void> attaches(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return Observable.create(new ViewAttachesOnSubscribe(view, true));
    }

    @CheckResult
    @NonNull
    public static Observable<ViewAttachEvent> attachEvents(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return Observable.create(new ViewAttachEventOnSubscribe(view));
    }

    @CheckResult
    @NonNull
    public static Observable<Void> detaches(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return Observable.create(new ViewAttachesOnSubscribe(view, false));
    }

    @CheckResult
    @NonNull
    public static Observable<Void> clicks(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return Observable.create(new ViewClickOnSubscribe(view));
    }

    @CheckResult
    @NonNull
    public static Observable<DragEvent> drags(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return Observable.create(new ViewDragOnSubscribe(view, Functions.FUNC1_ALWAYS_TRUE));
    }

    @CheckResult
    @NonNull
    public static Observable<DragEvent> drags(@NonNull View view, @NonNull Func1<? super DragEvent, Boolean> func1) {
        Preconditions.checkNotNull(view, "view == null");
        Preconditions.checkNotNull(func1, "handled == null");
        return Observable.create(new ViewDragOnSubscribe(view, func1));
    }

    @CheckResult
    @NonNull
    public static Observable<Void> draws(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return Observable.create(new ViewTreeObserverDrawOnSubscribe(view));
    }

    @CheckResult
    @NonNull
    public static Observable<Boolean> focusChanges(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return Observable.create(new ViewFocusChangeOnSubscribe(view));
    }

    @CheckResult
    @NonNull
    public static Observable<Void> globalLayouts(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return Observable.create(new ViewTreeObserverGlobalLayoutOnSubscribe(view));
    }

    @CheckResult
    @NonNull
    public static Observable<MotionEvent> hovers(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return hovers(view, Functions.FUNC1_ALWAYS_TRUE);
    }

    @CheckResult
    @NonNull
    public static Observable<MotionEvent> hovers(@NonNull View view, @NonNull Func1<? super MotionEvent, Boolean> func1) {
        Preconditions.checkNotNull(view, "view == null");
        Preconditions.checkNotNull(func1, "handled == null");
        return Observable.create(new ViewHoverOnSubscribe(view, func1));
    }

    @CheckResult
    @NonNull
    public static Observable<Void> layoutChanges(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return Observable.create(new ViewLayoutChangeOnSubscribe(view));
    }

    @CheckResult
    @NonNull
    public static Observable<ViewLayoutChangeEvent> layoutChangeEvents(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return Observable.create(new ViewLayoutChangeEventOnSubscribe(view));
    }

    @CheckResult
    @NonNull
    public static Observable<Void> longClicks(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return Observable.create(new ViewLongClickOnSubscribe(view, Functions.FUNC0_ALWAYS_TRUE));
    }

    @CheckResult
    @NonNull
    public static Observable<Void> longClicks(@NonNull View view, @NonNull Func0<Boolean> func0) {
        Preconditions.checkNotNull(view, "view == null");
        Preconditions.checkNotNull(func0, "handled == null");
        return Observable.create(new ViewLongClickOnSubscribe(view, func0));
    }

    @CheckResult
    @NonNull
    public static Observable<Void> preDraws(@NonNull View view, @NonNull Func0<Boolean> func0) {
        Preconditions.checkNotNull(view, "view == null");
        Preconditions.checkNotNull(func0, "proceedDrawingPass == null");
        return Observable.create(new ViewTreeObserverPreDrawOnSubscribe(view, func0));
    }

    @CheckResult
    @NonNull
    @RequiresApi(23)
    public static Observable<ViewScrollChangeEvent> scrollChangeEvents(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return Observable.create(new ViewScrollChangeEventOnSubscribe(view));
    }

    @CheckResult
    @NonNull
    public static Observable<Integer> systemUiVisibilityChanges(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return Observable.create(new ViewSystemUiVisibilityChangeOnSubscribe(view));
    }

    @CheckResult
    @NonNull
    public static Observable<MotionEvent> touches(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return touches(view, Functions.FUNC1_ALWAYS_TRUE);
    }

    @CheckResult
    @NonNull
    public static Observable<MotionEvent> touches(@NonNull View view, @NonNull Func1<? super MotionEvent, Boolean> func1) {
        Preconditions.checkNotNull(view, "view == null");
        Preconditions.checkNotNull(func1, "handled == null");
        return Observable.create(new ViewTouchOnSubscribe(view, func1));
    }

    @CheckResult
    @NonNull
    public static Observable<KeyEvent> keys(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return keys(view, Functions.FUNC1_ALWAYS_TRUE);
    }

    @CheckResult
    @NonNull
    public static Observable<KeyEvent> keys(@NonNull View view, @NonNull Func1<? super KeyEvent, Boolean> func1) {
        Preconditions.checkNotNull(view, "view == null");
        Preconditions.checkNotNull(func1, "handled == null");
        return Observable.create(new ViewKeyOnSubscribe(view, func1));
    }

    @CheckResult
    @NonNull
    public static Action1<? super Boolean> activated(@NonNull final View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new Action1<Boolean>() { // from class: com.jakewharton.rxbinding.view.RxView.1
            @Override // rx.functions.Action1
            public void call(Boolean bool) {
                view.setActivated(bool.booleanValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Boolean> clickable(@NonNull final View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new Action1<Boolean>() { // from class: com.jakewharton.rxbinding.view.RxView.2
            @Override // rx.functions.Action1
            public void call(Boolean bool) {
                view.setClickable(bool.booleanValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Boolean> enabled(@NonNull final View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new Action1<Boolean>() { // from class: com.jakewharton.rxbinding.view.RxView.3
            @Override // rx.functions.Action1
            public void call(Boolean bool) {
                view.setEnabled(bool.booleanValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Boolean> pressed(@NonNull final View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new Action1<Boolean>() { // from class: com.jakewharton.rxbinding.view.RxView.4
            @Override // rx.functions.Action1
            public void call(Boolean bool) {
                view.setPressed(bool.booleanValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Boolean> selected(@NonNull final View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new Action1<Boolean>() { // from class: com.jakewharton.rxbinding.view.RxView.5
            @Override // rx.functions.Action1
            public void call(Boolean bool) {
                view.setSelected(bool.booleanValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Boolean> visibility(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return visibility(view, 8);
    }

    @CheckResult
    @NonNull
    public static Action1<? super Boolean> visibility(@NonNull final View view, final int i) {
        Preconditions.checkNotNull(view, "view == null");
        boolean z = false;
        Preconditions.checkArgument(i != 0, "Setting visibility to VISIBLE when false would have no effect.");
        Preconditions.checkArgument((i == 4 || i == 8) ? true : true, "Must set visibility to INVISIBLE or GONE when false.");
        return new Action1<Boolean>() { // from class: com.jakewharton.rxbinding.view.RxView.6
            @Override // rx.functions.Action1
            public void call(Boolean bool) {
                view.setVisibility(bool.booleanValue() ? 0 : i);
            }
        };
    }

    private RxView() {
        throw new AssertionError("No instances.");
    }
}