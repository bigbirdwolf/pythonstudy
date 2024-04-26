package com.jakewharton.rxbinding.view;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public final class ViewGroupHierarchyChildViewRemoveEvent extends ViewGroupHierarchyChangeEvent {
    @CheckResult
    @NonNull
    public static ViewGroupHierarchyChildViewRemoveEvent create(@NonNull ViewGroup viewGroup, View view) {
        return new ViewGroupHierarchyChildViewRemoveEvent(viewGroup, view);
    }

    private ViewGroupHierarchyChildViewRemoveEvent(@NonNull ViewGroup viewGroup, View view) {
        super(viewGroup, view);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ViewGroupHierarchyChildViewRemoveEvent) {
            ViewGroupHierarchyChildViewRemoveEvent viewGroupHierarchyChildViewRemoveEvent = (ViewGroupHierarchyChildViewRemoveEvent) obj;
            return viewGroupHierarchyChildViewRemoveEvent.view() == view() && viewGroupHierarchyChildViewRemoveEvent.child() == child();
        }
        return false;
    }

    public int hashCode() {
        return ((629 + view().hashCode()) * 37) + child().hashCode();
    }

    public String toString() {
        return "ViewGroupHierarchyChildViewRemoveEvent{view=" + view() + ", child=" + child() + '}';
    }
}