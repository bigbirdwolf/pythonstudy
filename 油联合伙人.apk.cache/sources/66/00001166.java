package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import com.jakewharton.rxbinding.view.ViewEvent;

/* loaded from: classes.dex */
public final class AdapterViewItemClickEvent extends ViewEvent<AdapterView<?>> {
    private final View clickedView;
    private final long id;
    private final int position;

    @CheckResult
    @NonNull
    public static AdapterViewItemClickEvent create(@NonNull AdapterView<?> adapterView, @NonNull View view, int i, long j) {
        return new AdapterViewItemClickEvent(adapterView, view, i, j);
    }

    private AdapterViewItemClickEvent(@NonNull AdapterView<?> adapterView, @NonNull View view, int i, long j) {
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
        if (obj instanceof AdapterViewItemClickEvent) {
            AdapterViewItemClickEvent adapterViewItemClickEvent = (AdapterViewItemClickEvent) obj;
            return adapterViewItemClickEvent.view() == view() && adapterViewItemClickEvent.clickedView == this.clickedView && adapterViewItemClickEvent.position == this.position && adapterViewItemClickEvent.id == this.id;
        }
        return false;
    }

    public int hashCode() {
        return ((((((629 + view().hashCode()) * 37) + this.clickedView.hashCode()) * 37) + this.position) * 37) + ((int) (this.id ^ (this.id >>> 32)));
    }

    public String toString() {
        return "AdapterViewItemClickEvent{view=" + view() + ", clickedView=" + this.clickedView + ", position=" + this.position + ", id=" + this.id + '}';
    }
}