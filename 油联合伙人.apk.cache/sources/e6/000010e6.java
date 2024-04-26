package com.jakewharton.rxbinding.support.v7.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/* loaded from: classes.dex */
public final class RecyclerViewChildAttachEvent extends RecyclerViewChildAttachStateChangeEvent {
    @CheckResult
    @NonNull
    public static RecyclerViewChildAttachEvent create(@NonNull RecyclerView recyclerView, @NonNull View view) {
        return new RecyclerViewChildAttachEvent(recyclerView, view);
    }

    private RecyclerViewChildAttachEvent(@NonNull RecyclerView recyclerView, @NonNull View view) {
        super(recyclerView, view);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RecyclerViewChildAttachEvent) {
            RecyclerViewChildAttachEvent recyclerViewChildAttachEvent = (RecyclerViewChildAttachEvent) obj;
            return recyclerViewChildAttachEvent.view() == view() && recyclerViewChildAttachEvent.child() == child();
        }
        return false;
    }

    public int hashCode() {
        return ((629 + view().hashCode()) * 37) + child().hashCode();
    }

    public String toString() {
        return "RecyclerViewChildAttachEvent{view=" + view() + ", child=" + child() + '}';
    }
}