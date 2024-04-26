package com.jakewharton.rxbinding.support.v7.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/* loaded from: classes.dex */
public final class RecyclerViewChildDetachEvent extends RecyclerViewChildAttachStateChangeEvent {
    @CheckResult
    @NonNull
    public static RecyclerViewChildDetachEvent create(@NonNull RecyclerView recyclerView, @NonNull View view) {
        return new RecyclerViewChildDetachEvent(recyclerView, view);
    }

    private RecyclerViewChildDetachEvent(@NonNull RecyclerView recyclerView, @NonNull View view) {
        super(recyclerView, view);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RecyclerViewChildDetachEvent) {
            RecyclerViewChildDetachEvent recyclerViewChildDetachEvent = (RecyclerViewChildDetachEvent) obj;
            return recyclerViewChildDetachEvent.view() == view() && recyclerViewChildDetachEvent.child() == child();
        }
        return false;
    }

    public int hashCode() {
        return ((629 + view().hashCode()) * 37) + child().hashCode();
    }

    public String toString() {
        return "RecyclerViewChildDetachEvent{view=" + view() + ", child=" + child() + '}';
    }
}