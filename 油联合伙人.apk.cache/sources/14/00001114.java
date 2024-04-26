package com.jakewharton.rxbinding.view;

import android.graphics.drawable.Drawable;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import com.jakewharton.rxbinding.internal.Functions;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/* loaded from: classes.dex */
public final class RxMenuItem {
    @CheckResult
    @NonNull
    public static Observable<Void> clicks(@NonNull MenuItem menuItem) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        return Observable.create(new MenuItemClickOnSubscribe(menuItem, Functions.FUNC1_ALWAYS_TRUE));
    }

    @CheckResult
    @NonNull
    public static Observable<Void> clicks(@NonNull MenuItem menuItem, @NonNull Func1<? super MenuItem, Boolean> func1) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        Preconditions.checkNotNull(func1, "handled == null");
        return Observable.create(new MenuItemClickOnSubscribe(menuItem, func1));
    }

    @CheckResult
    @NonNull
    public static Observable<MenuItemActionViewEvent> actionViewEvents(@NonNull MenuItem menuItem) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        return Observable.create(new MenuItemActionViewEventOnSubscribe(menuItem, Functions.FUNC1_ALWAYS_TRUE));
    }

    @CheckResult
    @NonNull
    public static Observable<MenuItemActionViewEvent> actionViewEvents(@NonNull MenuItem menuItem, @NonNull Func1<? super MenuItemActionViewEvent, Boolean> func1) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        Preconditions.checkNotNull(func1, "handled == null");
        return Observable.create(new MenuItemActionViewEventOnSubscribe(menuItem, func1));
    }

    @CheckResult
    @NonNull
    public static Action1<? super Boolean> checked(@NonNull final MenuItem menuItem) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        return new Action1<Boolean>() { // from class: com.jakewharton.rxbinding.view.RxMenuItem.1
            @Override // rx.functions.Action1
            public void call(Boolean bool) {
                menuItem.setChecked(bool.booleanValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Boolean> enabled(@NonNull final MenuItem menuItem) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        return new Action1<Boolean>() { // from class: com.jakewharton.rxbinding.view.RxMenuItem.2
            @Override // rx.functions.Action1
            public void call(Boolean bool) {
                menuItem.setEnabled(bool.booleanValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Drawable> icon(@NonNull final MenuItem menuItem) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        return new Action1<Drawable>() { // from class: com.jakewharton.rxbinding.view.RxMenuItem.3
            @Override // rx.functions.Action1
            public void call(Drawable drawable) {
                menuItem.setIcon(drawable);
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Integer> iconRes(@NonNull final MenuItem menuItem) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        return new Action1<Integer>() { // from class: com.jakewharton.rxbinding.view.RxMenuItem.4
            @Override // rx.functions.Action1
            public void call(Integer num) {
                menuItem.setIcon(num.intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super CharSequence> title(@NonNull final MenuItem menuItem) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        return new Action1<CharSequence>() { // from class: com.jakewharton.rxbinding.view.RxMenuItem.5
            @Override // rx.functions.Action1
            public void call(CharSequence charSequence) {
                menuItem.setTitle(charSequence);
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Integer> titleRes(@NonNull final MenuItem menuItem) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        return new Action1<Integer>() { // from class: com.jakewharton.rxbinding.view.RxMenuItem.6
            @Override // rx.functions.Action1
            public void call(Integer num) {
                menuItem.setTitle(num.intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    public static Action1<? super Boolean> visible(@NonNull final MenuItem menuItem) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        return new Action1<Boolean>() { // from class: com.jakewharton.rxbinding.view.RxMenuItem.7
            @Override // rx.functions.Action1
            public void call(Boolean bool) {
                menuItem.setVisible(bool.booleanValue());
            }
        };
    }

    private RxMenuItem() {
        throw new AssertionError("No instances.");
    }
}