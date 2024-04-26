package com.jakewharton.rxbinding.support.design.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import com.jakewharton.rxbinding.view.ViewEvent;

/* loaded from: classes.dex */
public final class TabLayoutSelectionEvent extends ViewEvent<TabLayout> {
    private final Kind kind;
    private final TabLayout.Tab tab;

    /* loaded from: classes.dex */
    public enum Kind {
        SELECTED,
        RESELECTED,
        UNSELECTED
    }

    @CheckResult
    @NonNull
    public static TabLayoutSelectionEvent create(@NonNull TabLayout tabLayout, @NonNull Kind kind, @NonNull TabLayout.Tab tab) {
        return new TabLayoutSelectionEvent(tabLayout, kind, tab);
    }

    private TabLayoutSelectionEvent(@NonNull TabLayout tabLayout, @NonNull Kind kind, @NonNull TabLayout.Tab tab) {
        super(tabLayout);
        this.tab = tab;
        this.kind = kind;
    }

    @NonNull
    public Kind kind() {
        return this.kind;
    }

    @NonNull
    public TabLayout.Tab tab() {
        return this.tab;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TabLayoutSelectionEvent) {
            TabLayoutSelectionEvent tabLayoutSelectionEvent = (TabLayoutSelectionEvent) obj;
            return view() == tabLayoutSelectionEvent.view() && this.kind == tabLayoutSelectionEvent.kind && this.tab == tabLayoutSelectionEvent.tab;
        }
        return false;
    }

    public int hashCode() {
        return ((((629 + view().hashCode()) * 37) + this.kind.hashCode()) * 37) + this.tab.hashCode();
    }

    public String toString() {
        return "TabLayoutSelectionEvent{view=" + view() + ", kind=" + this.kind + ", tab=" + this.tab + '}';
    }
}