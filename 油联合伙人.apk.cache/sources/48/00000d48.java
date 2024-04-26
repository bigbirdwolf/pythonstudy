package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.common.android.DialogFragmentAccessor;
import com.facebook.stetho.common.android.FragmentCompat;
import com.facebook.stetho.inspector.elements.AttributeAccumulator;
import com.facebook.stetho.inspector.elements.ChainedDescriptor;
import com.facebook.stetho.inspector.elements.Descriptor;
import com.facebook.stetho.inspector.elements.DescriptorMap;
import com.facebook.stetho.inspector.elements.NodeType;
import com.facebook.stetho.inspector.elements.StyleAccumulator;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
final class DialogFragmentDescriptor extends Descriptor implements ChainedDescriptor, HighlightableDescriptor {
    private final DialogFragmentAccessor mAccessor;
    private Descriptor mSuper;

    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public void getAccessibilityStyles(Object obj, StyleAccumulator styleAccumulator) {
    }

    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public void getStyles(Object obj, StyleAccumulator styleAccumulator) {
    }

    public static DescriptorMap register(DescriptorMap descriptorMap) {
        maybeRegister(descriptorMap, FragmentCompat.getSupportLibInstance());
        maybeRegister(descriptorMap, FragmentCompat.getFrameworkInstance());
        return descriptorMap;
    }

    private static void maybeRegister(DescriptorMap descriptorMap, @Nullable FragmentCompat fragmentCompat) {
        if (fragmentCompat != null) {
            Class<?> dialogFragmentClass = fragmentCompat.getDialogFragmentClass();
            LogUtil.d("Adding support for %s", dialogFragmentClass);
            descriptorMap.register(dialogFragmentClass, new DialogFragmentDescriptor(fragmentCompat));
        }
    }

    private DialogFragmentDescriptor(FragmentCompat fragmentCompat) {
        this.mAccessor = fragmentCompat.forDialogFragment();
    }

    @Override // com.facebook.stetho.inspector.elements.ChainedDescriptor
    public void setSuper(Descriptor descriptor) {
        Util.throwIfNull(descriptor);
        if (descriptor != this.mSuper) {
            if (this.mSuper != null) {
                throw new IllegalStateException();
            }
            this.mSuper = descriptor;
        }
    }

    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public void hook(Object obj) {
        this.mSuper.hook(obj);
    }

    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public void unhook(Object obj) {
        this.mSuper.unhook(obj);
    }

    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public NodeType getNodeType(Object obj) {
        return this.mSuper.getNodeType(obj);
    }

    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public String getNodeName(Object obj) {
        return this.mSuper.getNodeName(obj);
    }

    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public String getLocalName(Object obj) {
        return this.mSuper.getLocalName(obj);
    }

    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    @Nullable
    public String getNodeValue(Object obj) {
        return this.mSuper.getNodeValue(obj);
    }

    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public void getChildren(Object obj, Accumulator<Object> accumulator) {
        accumulator.store(this.mAccessor.getDialog(obj));
    }

    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public void getAttributes(Object obj, AttributeAccumulator attributeAccumulator) {
        this.mSuper.getAttributes(obj, attributeAccumulator);
    }

    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public void setAttributesAsText(Object obj, String str) {
        this.mSuper.setAttributesAsText(obj, str);
    }

    @Override // com.facebook.stetho.inspector.elements.android.HighlightableDescriptor
    @Nullable
    public View getViewForHighlighting(Object obj) {
        Descriptor.Host host = getHost();
        if (host instanceof AndroidDescriptorHost) {
            return ((AndroidDescriptorHost) host).getHighlightingView(this.mAccessor.getDialog(obj));
        }
        return null;
    }
}