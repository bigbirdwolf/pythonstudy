package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.android.FragmentAccessor;
import com.facebook.stetho.common.android.FragmentCompat;
import com.facebook.stetho.common.android.ResourcesUtil;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.AttributeAccumulator;
import com.facebook.stetho.inspector.elements.DescriptorMap;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
final class FragmentDescriptor extends AbstractChainedDescriptor<Object> implements HighlightableDescriptor {
    private static final String ID_ATTRIBUTE_NAME = "id";
    private static final String TAG_ATTRIBUTE_NAME = "tag";
    private final FragmentAccessor mAccessor;

    public static DescriptorMap register(DescriptorMap descriptorMap) {
        maybeRegister(descriptorMap, FragmentCompat.getSupportLibInstance());
        maybeRegister(descriptorMap, FragmentCompat.getFrameworkInstance());
        return descriptorMap;
    }

    private static void maybeRegister(DescriptorMap descriptorMap, @Nullable FragmentCompat fragmentCompat) {
        if (fragmentCompat != null) {
            Class<?> fragmentClass = fragmentCompat.getFragmentClass();
            LogUtil.d("Adding support for %s", fragmentClass.getName());
            descriptorMap.register(fragmentClass, new FragmentDescriptor(fragmentCompat));
        }
    }

    private FragmentDescriptor(FragmentCompat fragmentCompat) {
        this.mAccessor = fragmentCompat.forFragment();
    }

    @Override // com.facebook.stetho.inspector.elements.AbstractChainedDescriptor
    protected void onGetAttributes(Object obj, AttributeAccumulator attributeAccumulator) {
        int id = this.mAccessor.getId(obj);
        if (id != 0) {
            attributeAccumulator.store("id", ResourcesUtil.getIdStringQuietly(obj, this.mAccessor.getResources(obj), id));
        }
        String tag = this.mAccessor.getTag(obj);
        if (tag == null || tag.length() <= 0) {
            return;
        }
        attributeAccumulator.store("tag", tag);
    }

    @Override // com.facebook.stetho.inspector.elements.AbstractChainedDescriptor
    protected void onGetChildren(Object obj, Accumulator<Object> accumulator) {
        View view = this.mAccessor.getView(obj);
        if (view != null) {
            accumulator.store(view);
        }
    }

    @Override // com.facebook.stetho.inspector.elements.android.HighlightableDescriptor
    public View getViewForHighlighting(Object obj) {
        return this.mAccessor.getView(obj);
    }
}