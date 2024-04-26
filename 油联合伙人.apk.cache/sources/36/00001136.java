package com.jakewharton.rxbinding.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public abstract class ViewGroupHierarchyChangeEvent extends ViewEvent<ViewGroup> {
    private final View child;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewGroupHierarchyChangeEvent(@NonNull ViewGroup viewGroup, View view) {
        super(viewGroup);
        this.child = view;
    }

    @NonNull
    public final View child() {
        return this.child;
    }
}