package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.Adapter;
import android.widget.AdapterView;
import com.jakewharton.rxbinding.internal.Functions;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

/* loaded from: classes.dex */
public final class RxAdapterView {
    @CheckResult
    @NonNull
    public static <T extends Adapter> Observable<Integer> itemSelections(@NonNull AdapterView<T> adapterView) {
        Preconditions.checkNotNull(adapterView, "view == null");
        return Observable.create(new AdapterViewItemSelectionOnSubscribe(adapterView));
    }

    @CheckResult
    @NonNull
    public static <T extends Adapter> Observable<AdapterViewSelectionEvent> selectionEvents(@NonNull AdapterView<T> adapterView) {
        Preconditions.checkNotNull(adapterView, "view == null");
        return Observable.create(new AdapterViewSelectionOnSubscribe(adapterView));
    }

    @CheckResult
    @NonNull
    public static <T extends Adapter> Observable<Integer> itemClicks(@NonNull AdapterView<T> adapterView) {
        Preconditions.checkNotNull(adapterView, "view == null");
        return Observable.create(new AdapterViewItemClickOnSubscribe(adapterView));
    }

    @CheckResult
    @NonNull
    public static <T extends Adapter> Observable<AdapterViewItemClickEvent> itemClickEvents(@NonNull AdapterView<T> adapterView) {
        Preconditions.checkNotNull(adapterView, "view == null");
        return Observable.create(new AdapterViewItemClickEventOnSubscribe(adapterView));
    }

    @CheckResult
    @NonNull
    public static <T extends Adapter> Observable<Integer> itemLongClicks(@NonNull AdapterView<T> adapterView) {
        Preconditions.checkNotNull(adapterView, "view == null");
        return itemLongClicks(adapterView, Functions.FUNC0_ALWAYS_TRUE);
    }

    @CheckResult
    @NonNull
    public static <T extends Adapter> Observable<Integer> itemLongClicks(@NonNull AdapterView<T> adapterView, @NonNull Func0<Boolean> func0) {
        Preconditions.checkNotNull(adapterView, "view == null");
        Preconditions.checkNotNull(func0, "handled == null");
        return Observable.create(new AdapterViewItemLongClickOnSubscribe(adapterView, func0));
    }

    @CheckResult
    @NonNull
    public static <T extends Adapter> Observable<AdapterViewItemLongClickEvent> itemLongClickEvents(@NonNull AdapterView<T> adapterView) {
        Preconditions.checkNotNull(adapterView, "view == null");
        return itemLongClickEvents(adapterView, Functions.FUNC1_ALWAYS_TRUE);
    }

    @CheckResult
    @NonNull
    public static <T extends Adapter> Observable<AdapterViewItemLongClickEvent> itemLongClickEvents(@NonNull AdapterView<T> adapterView, @NonNull Func1<? super AdapterViewItemLongClickEvent, Boolean> func1) {
        Preconditions.checkNotNull(adapterView, "view == null");
        Preconditions.checkNotNull(func1, "handled == null");
        return Observable.create(new AdapterViewItemLongClickEventOnSubscribe(adapterView, func1));
    }

    @CheckResult
    @NonNull
    public static <T extends Adapter> Action1<? super Integer> selection(@NonNull final AdapterView<T> adapterView) {
        Preconditions.checkNotNull(adapterView, "view == null");
        return new Action1<Integer>() { // from class: com.jakewharton.rxbinding.widget.RxAdapterView.1
            @Override // rx.functions.Action1
            public void call(Integer num) {
                adapterView.setSelection(num.intValue());
            }
        };
    }

    private RxAdapterView() {
        throw new AssertionError("No instances.");
    }
}