package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.AbsListView;
import com.jakewharton.rxbinding.view.ViewEvent;

/* loaded from: classes.dex */
public final class AbsListViewScrollEvent extends ViewEvent<AbsListView> {
    private final int firstVisibleItem;
    private final int scrollState;
    private final int totalItemCount;
    private final int visibleItemCount;

    @CheckResult
    @NonNull
    public static AbsListViewScrollEvent create(AbsListView absListView, int i, int i2, int i3, int i4) {
        return new AbsListViewScrollEvent(absListView, i, i2, i3, i4);
    }

    private AbsListViewScrollEvent(@NonNull AbsListView absListView, int i, int i2, int i3, int i4) {
        super(absListView);
        this.scrollState = i;
        this.firstVisibleItem = i2;
        this.visibleItemCount = i3;
        this.totalItemCount = i4;
    }

    public int scrollState() {
        return this.scrollState;
    }

    public int firstVisibleItem() {
        return this.firstVisibleItem;
    }

    public int visibleItemCount() {
        return this.visibleItemCount;
    }

    public int totalItemCount() {
        return this.totalItemCount;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AbsListViewScrollEvent absListViewScrollEvent = (AbsListViewScrollEvent) obj;
        return this.scrollState == absListViewScrollEvent.scrollState && this.firstVisibleItem == absListViewScrollEvent.firstVisibleItem && this.visibleItemCount == absListViewScrollEvent.visibleItemCount && this.totalItemCount == absListViewScrollEvent.totalItemCount;
    }

    public int hashCode() {
        return (((((this.scrollState * 31) + this.firstVisibleItem) * 31) + this.visibleItemCount) * 31) + this.totalItemCount;
    }

    public String toString() {
        return "AbsListViewScrollEvent{scrollState=" + this.scrollState + ", firstVisibleItem=" + this.firstVisibleItem + ", visibleItemCount=" + this.visibleItemCount + ", totalItemCount=" + this.totalItemCount + '}';
    }
}