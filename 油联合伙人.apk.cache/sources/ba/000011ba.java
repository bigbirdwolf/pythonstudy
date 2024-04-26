package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.MenuItem;
import android.widget.Toolbar;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;
import rx.functions.Action1;

@RequiresApi(21)
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
        return new Action1<CharSequence>() { // from class: com.jakewharton.rxbinding.widget.RxToolbar.1
            @Override // rx.functions.Action1
            public void call(CharSequence charSequence) {
                toolbar.setTitle(charSequence);
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Integer> titleRes(@NonNull final Toolbar toolbar) {
        Preconditions.checkNotNull(toolbar, "view == null");
        return new Action1<Integer>() { // from class: com.jakewharton.rxbinding.widget.RxToolbar.2
            @Override // rx.functions.Action1
            public void call(Integer num) {
                toolbar.setTitle(num.intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super CharSequence> subtitle(@NonNull final Toolbar toolbar) {
        Preconditions.checkNotNull(toolbar, "view == null");
        return new Action1<CharSequence>() { // from class: com.jakewharton.rxbinding.widget.RxToolbar.3
            @Override // rx.functions.Action1
            public void call(CharSequence charSequence) {
                toolbar.setSubtitle(charSequence);
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Integer> subtitleRes(@NonNull final Toolbar toolbar) {
        Preconditions.checkNotNull(toolbar, "view == null");
        return new Action1<Integer>() { // from class: com.jakewharton.rxbinding.widget.RxToolbar.4
            @Override // rx.functions.Action1
            public void call(Integer num) {
                toolbar.setSubtitle(num.intValue());
            }
        };
    }

    private RxToolbar() {
        throw new AssertionError("No instances.");
    }
}