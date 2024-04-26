package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import android.view.Window;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
final class WindowDescriptor extends AbstractChainedDescriptor<Window> implements HighlightableDescriptor {
    @Override // com.facebook.stetho.inspector.elements.AbstractChainedDescriptor
    protected /* bridge */ /* synthetic */ void onGetChildren(Window window, Accumulator accumulator) {
        onGetChildren2(window, (Accumulator<Object>) accumulator);
    }

    /* renamed from: onGetChildren  reason: avoid collision after fix types in other method */
    protected void onGetChildren2(Window window, Accumulator<Object> accumulator) {
        View peekDecorView = window.peekDecorView();
        if (peekDecorView != null) {
            accumulator.store(peekDecorView);
        }
    }

    @Override // com.facebook.stetho.inspector.elements.android.HighlightableDescriptor
    @Nullable
    public View getViewForHighlighting(Object obj) {
        return ((Window) obj).peekDecorView();
    }
}