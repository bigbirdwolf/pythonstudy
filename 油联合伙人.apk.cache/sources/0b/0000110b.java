package com.jakewharton.rxbinding.view;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.MenuItem;

/* loaded from: classes.dex */
public final class MenuItemActionViewEvent extends MenuItemEvent<MenuItem> {
    private final Kind kind;

    /* loaded from: classes.dex */
    public enum Kind {
        EXPAND,
        COLLAPSE
    }

    @CheckResult
    @NonNull
    public static MenuItemActionViewEvent create(@NonNull MenuItem menuItem, @NonNull Kind kind) {
        return new MenuItemActionViewEvent(menuItem, kind);
    }

    private MenuItemActionViewEvent(@NonNull MenuItem menuItem, @NonNull Kind kind) {
        super(menuItem);
        this.kind = kind;
    }

    @NonNull
    public Kind kind() {
        return this.kind;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MenuItemActionViewEvent menuItemActionViewEvent = (MenuItemActionViewEvent) obj;
        return menuItem().equals(menuItemActionViewEvent.menuItem()) && this.kind == menuItemActionViewEvent.kind;
    }

    public int hashCode() {
        return (menuItem().hashCode() * 31) + this.kind.hashCode();
    }

    public String toString() {
        return "MenuItemActionViewEvent{menuItem=" + menuItem() + ", kind=" + this.kind + '}';
    }
}