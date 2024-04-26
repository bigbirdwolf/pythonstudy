package com.jakewharton.rxbinding.view;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public final class ViewGroupHierarchyChildViewAddEvent extends ViewGroupHierarchyChangeEvent {
    @CheckResult
    @NonNull
    public static ViewGroupHierarchyChildViewAddEvent create(@NonNull ViewGroup viewGroup, View view) {
        return new ViewGroupHierarchyChildViewAddEvent(viewGroup, view);
    }

    private ViewGroupHierarchyChildViewAddEvent(@NonNull ViewGroup viewGroup, View view) {
        super(viewGroup, view);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ViewGroupHierarchyChildViewAddEvent) {
            ViewGroupHierarchyChildViewAddEvent viewGroupHierarchyChildViewAddEvent = (ViewGroupHierarchyChildViewAddEvent) obj;
            return viewGroupHierarchyChildViewAddEvent.view() == view() && viewGroupHierarchyChildViewAddEvent.child() == child();
        }
        return false;
    }

    public int hashCode() {
        return ((629 + view().hashCode()) * 37) + child().hashCode();
    }

    public String toString() {
        return "ViewGroupHierarchyChildViewAddEvent{view=" + view() + ", child=" + child() + '}';
    }
}