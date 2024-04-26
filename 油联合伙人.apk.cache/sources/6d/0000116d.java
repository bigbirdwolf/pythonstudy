package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import com.jakewharton.rxbinding.view.ViewEvent;

/* loaded from: classes.dex */
public final class AdapterViewItemLongClickEvent extends ViewEvent<AdapterView<?>> {
    private final View clickedView;
    private final long id;
    private final int position;

    @CheckResult
    @NonNull
    public static AdapterViewItemLongClickEvent create(@NonNull AdapterView<?> adapterView, @NonNull View view, int i, long j) {
        return new AdapterViewItemLongClickEvent(adapterView, view, i, j);
    }

    private AdapterViewItemLongClickEvent(@NonNull AdapterView<?> adapterView, @NonNull View view, int i, long j) {
        super(adapterView);
        this.clickedView = view;
        this.position = i;
        this.id = j;
    }

    @NonNull
    public View clickedView() {
        return this.clickedView;
    }

    public int position() {
        return this.position;
    }

    public long id() {
        return this.id;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AdapterViewItemLongClickEvent) {
            AdapterViewItemLongClickEvent adapterViewItemLongClickEvent = (AdapterViewItemLongClickEvent) obj;
            return adapterViewItemLongClickEvent.view() == view() && adapterViewItemLongClickEvent.clickedView == this.clickedView && adapterViewItemLongClickEvent.position == this.position && adapterViewItemLongClickEvent.id == this.id;
        }
        return false;
    }

    public int hashCode() {
        return ((((((629 + view().hashCode()) * 37) + this.clickedView.hashCode()) * 37) + this.position) * 37) + ((int) (this.id ^ (this.id >>> 32)));
    }

    public String toString() {
        return "AdapterViewItemLongClickEvent{view=" + view() + ", clickedView=" + this.clickedView + ", position=" + this.position + ", id=" + this.id + '}';
    }
}