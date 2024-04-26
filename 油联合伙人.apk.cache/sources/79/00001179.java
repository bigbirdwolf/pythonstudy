package com.jakewharton.rxbinding.widget;

import android.support.annotation.NonNull;
import android.widget.AdapterView;
import com.jakewharton.rxbinding.view.ViewEvent;

/* loaded from: classes.dex */
public abstract class AdapterViewSelectionEvent extends ViewEvent<AdapterView<?>> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public AdapterViewSelectionEvent(@NonNull AdapterView<?> adapterView) {
        super(adapterView);
    }
}