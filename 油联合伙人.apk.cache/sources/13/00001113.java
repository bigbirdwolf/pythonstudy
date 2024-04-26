package com.jakewharton.rxbinding.view;

import android.support.annotation.NonNull;
import android.view.MenuItem;

/* loaded from: classes.dex */
public abstract class MenuItemEvent<T extends MenuItem> {
    private final T menuItem;

    /* JADX INFO: Access modifiers changed from: protected */
    public MenuItemEvent(@NonNull T t) {
        this.menuItem = t;
    }

    @NonNull
    public T menuItem() {
        return this.menuItem;
    }
}