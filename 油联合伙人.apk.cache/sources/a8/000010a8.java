package com.jakewharton.rxbinding.support.v4.view;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes.dex */
public final class RxViewPager {
    @CheckResult
    @NonNull
    public static Observable<Integer> pageScrollStateChanges(@NonNull ViewPager viewPager) {
        Preconditions.checkNotNull(viewPager, "view == null");
        return Observable.create(new ViewPagerPageScrollStateChangedOnSubscribe(viewPager));
    }

    @CheckResult
    @NonNull
    public static Observable<Integer> pageSelections(@NonNull ViewPager viewPager) {
        Preconditions.checkNotNull(viewPager, "view == null");
        return Observable.create(new ViewPagerPageSelectedOnSubscribe(viewPager));
    }

    @CheckResult
    @NonNull
    public static Action1<? super Integer> currentItem(@NonNull final ViewPager viewPager) {
        Preconditions.checkNotNull(viewPager, "view == null");
        return new Action1<Integer>() { // from class: com.jakewharton.rxbinding.support.v4.view.RxViewPager.1
            @Override // rx.functions.Action1
            public void call(Integer num) {
                ViewPager.this.setCurrentItem(num.intValue());
            }
        };
    }

    private RxViewPager() {
        throw new AssertionError("No instances.");
    }
}