package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
interface HighlightableDescriptor {
    @Nullable
    View getViewForHighlighting(Object obj);
}