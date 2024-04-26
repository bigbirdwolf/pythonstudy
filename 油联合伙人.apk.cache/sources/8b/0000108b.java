package com.jakewharton.rxbinding.support.design.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes.dex */
public final class RxTabLayout {
    @CheckResult
    @NonNull
    public static Observable<TabLayout.Tab> selections(@NonNull TabLayout tabLayout) {
        Preconditions.checkNotNull(tabLayout, "view == null");
        return Observable.create(new TabLayoutSelectionsOnSubscribe(tabLayout));
    }

    @CheckResult
    @NonNull
    public static Observable<TabLayoutSelectionEvent> selectionEvents(@NonNull TabLayout tabLayout) {
        Preconditions.checkNotNull(tabLayout, "view == null");
        return Observable.create(new TabLayoutSelectionEventOnSubscribe(tabLayout));
    }

    @CheckResult
    @NonNull
    public static Action1<? super Integer> select(@NonNull final TabLayout tabLayout) {
        Preconditions.checkNotNull(tabLayout, "view == null");
        return new Action1<Integer>() { // from class: com.jakewharton.rxbinding.support.design.widget.RxTabLayout.1
            @Override // rx.functions.Action1
            public void call(Integer num) {
                if (num.intValue() < 0 || num.intValue() >= TabLayout.this.getTabCount()) {
                    throw new IllegalArgumentException("No tab for index " + num);
                }
                TabLayout.this.getTabAt(num.intValue()).select();
            }
        };
    }

    private RxTabLayout() {
        throw new AssertionError("No instances.");
    }
}