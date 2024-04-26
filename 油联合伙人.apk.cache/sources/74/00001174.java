package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;

/* loaded from: classes.dex */
public final class AdapterViewItemSelectionEvent extends AdapterViewSelectionEvent {
    private final long id;
    private final int position;
    private final View selectedView;

    @CheckResult
    @NonNull
    public static AdapterViewSelectionEvent create(@NonNull AdapterView<?> adapterView, @NonNull View view, int i, long j) {
        return new AdapterViewItemSelectionEvent(adapterView, view, i, j);
    }

    private AdapterViewItemSelectionEvent(@NonNull AdapterView<?> adapterView, @NonNull View view, int i, long j) {
        super(adapterView);
        this.selectedView = view;
        this.position = i;
        this.id = j;
    }

    @NonNull
    public View selectedView() {
        return this.selectedView;
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
        if (obj instanceof AdapterViewItemSelectionEvent) {
            AdapterViewItemSelectionEvent adapterViewItemSelectionEvent = (AdapterViewItemSelectionEvent) obj;
            return adapterViewItemSelectionEvent.view() == view() && adapterViewItemSelectionEvent.selectedView == this.selectedView && adapterViewItemSelectionEvent.position == this.position && adapterViewItemSelectionEvent.id == this.id;
        }
        return false;
    }

    public int hashCode() {
        return ((((((629 + view().hashCode()) * 37) + this.selectedView.hashCode()) * 37) + this.position) * 37) + ((int) (this.id ^ (this.id >>> 32)));
    }

    public String toString() {
        return "AdapterViewItemSelectionEvent{view=" + view() + ", selectedView=" + this.selectedView + ", position=" + this.position + ", id=" + this.id + '}';
    }
}