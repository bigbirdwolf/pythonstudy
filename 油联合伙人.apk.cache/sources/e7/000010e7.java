package com.jakewharton.rxbinding.support.v7.widget;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.jakewharton.rxbinding.view.ViewEvent;

/* loaded from: classes.dex */
public abstract class RecyclerViewChildAttachStateChangeEvent extends ViewEvent<RecyclerView> {
    private final View child;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecyclerViewChildAttachStateChangeEvent(@NonNull RecyclerView recyclerView, @NonNull View view) {
        super(recyclerView);
        this.child = view;
    }

    @NonNull
    public final View child() {
        return this.child;
    }
}