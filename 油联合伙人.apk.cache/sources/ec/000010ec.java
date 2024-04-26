package com.jakewharton.rxbinding.support.v7.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import com.jakewharton.rxbinding.view.ViewEvent;

/* loaded from: classes.dex */
public final class RecyclerViewScrollEvent extends ViewEvent<RecyclerView> {
    private final int dx;
    private final int dy;

    @CheckResult
    @NonNull
    public static RecyclerViewScrollEvent create(@NonNull RecyclerView recyclerView, int i, int i2) {
        return new RecyclerViewScrollEvent(recyclerView, i, i2);
    }

    private RecyclerViewScrollEvent(@NonNull RecyclerView recyclerView, int i, int i2) {
        super(recyclerView);
        this.dx = i;
        this.dy = i2;
    }

    public int dx() {
        return this.dx;
    }

    public int dy() {
        return this.dy;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RecyclerViewScrollEvent) {
            RecyclerViewScrollEvent recyclerViewScrollEvent = (RecyclerViewScrollEvent) obj;
            return recyclerViewScrollEvent.view() == view() && this.dx == recyclerViewScrollEvent.dx && this.dy == recyclerViewScrollEvent.dy;
        }
        return false;
    }

    public int hashCode() {
        return ((((629 + view().hashCode()) * 37) + this.dx) * 37) + this.dy;
    }

    public String toString() {
        return "RecyclerViewScrollEvent{view=" + view() + ", dx=" + this.dx + ", dy=" + this.dy + '}';
    }
}