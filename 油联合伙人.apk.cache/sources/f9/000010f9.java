package com.jakewharton.rxbinding.support.v7.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes.dex */
public final class RxToolbar {
    @CheckResult
    @NonNull
    public static Observable<MenuItem> itemClicks(@NonNull Toolbar toolbar) {
        Preconditions.checkNotNull(toolbar, "view == null");
        return Observable.create(new ToolbarItemClickOnSubscribe(toolbar));
    }

    @CheckResult
    @NonNull
    public static Observable<Void> navigationClicks(@NonNull Toolbar toolbar) {
        Preconditions.checkNotNull(toolbar, "view == null");
        return Observable.create(new ToolbarNavigationClickOnSubscribe(toolbar));
    }

    @CheckResult
    @NonNull
    public static Action1<? super CharSequence> title(@NonNull final Toolbar toolbar) {
        Preconditions.checkNotNull(toolbar, "view == null");
        return new Action1<CharSequence>() { // from class: com.jakewharton.rxbinding.support.v7.widget.RxToolbar.1
            @Override // rx.functions.Action1
            public void call(CharSequence charSequence) {
                Toolbar.this.setTitle(charSequence);
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Integer> titleRes(@NonNull final Toolbar toolbar) {
        Preconditions.checkNotNull(toolbar, "view == null");
        return new Action1<Integer>() { // from class: com.jakewharton.rxbinding.support.v7.widget.RxToolbar.2
            @Override // rx.functions.Action1
            public void call(Integer num) {
                Toolbar.this.setTitle(num.intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super CharSequence> subtitle(@NonNull final Toolbar toolbar) {
        Preconditions.checkNotNull(toolbar, "view == null");
        return new Action1<CharSequence>() { // from class: com.jakewharton.rxbinding.support.v7.widget.RxToolbar.3
            @Override // rx.functions.Action1
            public void call(CharSequence charSequence) {
                Toolbar.this.setSubtitle(charSequence);
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Integer> subtitleRes(@NonNull final Toolbar toolbar) {
        Preconditions.checkNotNull(toolbar, "view == null");
        return new Action1<Integer>() { // from class: com.jakewharton.rxbinding.support.v7.widget.RxToolbar.4
            @Override // rx.functions.Action1
            public void call(Integer num) {
                Toolbar.this.setSubtitle(num.intValue());
            }
        };
    }

    private RxToolbar() {
        throw new AssertionError("No instances.");
    }
}