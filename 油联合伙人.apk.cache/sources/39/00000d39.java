package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import com.facebook.stetho.inspector.elements.Descriptor;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
interface AndroidDescriptorHost extends Descriptor.Host {
    @Nullable
    View getHighlightingView(@Nullable Object obj);
}