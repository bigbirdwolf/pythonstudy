package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.AdapterView;

/* loaded from: classes.dex */
public final class AdapterViewNothingSelectionEvent extends AdapterViewSelectionEvent {
    @CheckResult
    @NonNull
    public static AdapterViewSelectionEvent create(@NonNull AdapterView<?> adapterView) {
        return new AdapterViewNothingSelectionEvent(adapterView);
    }

    private AdapterViewNothingSelectionEvent(@NonNull AdapterView<?> adapterView) {
        super(adapterView);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof AdapterViewNothingSelectionEvent) && ((AdapterViewNothingSelectionEvent) obj).view() == view();
    }

    public int hashCode() {
        return view().hashCode();
    }

    public String toString() {
        return "AdapterViewNothingSelectionEvent{view=" + view() + '}';
    }
}