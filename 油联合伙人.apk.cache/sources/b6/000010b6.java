package com.jakewharton.rxbinding.support.v4.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes.dex */
public final class RxDrawerLayout {
    @CheckResult
    @NonNull
    public static Observable<Boolean> drawerOpen(@NonNull DrawerLayout drawerLayout, int i) {
        Preconditions.checkNotNull(drawerLayout, "view == null");
        return Observable.create(new DrawerLayoutDrawerOpenedOnSubscribe(drawerLayout, i));
    }

    @CheckResult
    @NonNull
    public static Action1<? super Boolean> open(@NonNull final DrawerLayout drawerLayout, final int i) {
        Preconditions.checkNotNull(drawerLayout, "view == null");
        return new Action1<Boolean>() { // from class: com.jakewharton.rxbinding.support.v4.widget.RxDrawerLayout.1
            @Override // rx.functions.Action1
            public void call(Boolean bool) {
                if (bool.booleanValue()) {
                    DrawerLayout.this.openDrawer(i);
                } else {
                    DrawerLayout.this.closeDrawer(i);
                }
            }
        };
    }

    private RxDrawerLayout() {
        throw new AssertionError("No instances.");
    }
}