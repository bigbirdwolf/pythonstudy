package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.android.FragmentCompatUtil;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
final class ViewGroupDescriptor extends AbstractChainedDescriptor<ViewGroup> {
    private final Map<View, Object> mViewToElementMap = Collections.synchronizedMap(new WeakHashMap());

    @Override // com.facebook.stetho.inspector.elements.AbstractChainedDescriptor
    protected /* bridge */ /* synthetic */ void onGetChildren(ViewGroup viewGroup, Accumulator accumulator) {
        onGetChildren2(viewGroup, (Accumulator<Object>) accumulator);
    }

    /* renamed from: onGetChildren  reason: avoid collision after fix types in other method */
    protected void onGetChildren2(ViewGroup viewGroup, Accumulator<Object> accumulator) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (isChildVisible(childAt)) {
                accumulator.store(getElementForView(viewGroup, childAt));
            }
        }
    }

    private boolean isChildVisible(View view) {
        return !(view instanceof DocumentHiddenView);
    }

    private Object getElementForView(ViewGroup viewGroup, View view) {
        Object obj = this.mViewToElementMap.get(view);
        if (obj != null) {
            Object element = getElement(view, obj);
            if (element != null && view.getParent() == viewGroup) {
                return element;
            }
            this.mViewToElementMap.remove(view);
        }
        Object findFragmentForView = FragmentCompatUtil.findFragmentForView(view);
        if (findFragmentForView != null && !FragmentCompatUtil.isDialogFragment(findFragmentForView)) {
            this.mViewToElementMap.put(view, new WeakReference(findFragmentForView));
            return findFragmentForView;
        }
        this.mViewToElementMap.put(view, this);
        return view;
    }

    private Object getElement(View view, Object obj) {
        return obj == this ? view : ((WeakReference) obj).get();
    }
}