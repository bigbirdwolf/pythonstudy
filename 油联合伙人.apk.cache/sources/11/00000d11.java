package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.Util;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public abstract class AbstractChainedDescriptor<E> extends Descriptor implements ChainedDescriptor {
    private Descriptor mSuper;

    protected void onGetAccessibilityStyles(E e, StyleAccumulator styleAccumulator) {
    }

    protected void onGetAttributes(E e, AttributeAccumulator attributeAccumulator) {
    }

    protected void onGetChildren(E e, Accumulator<Object> accumulator) {
    }

    protected void onGetStyles(E e, StyleAccumulator styleAccumulator) {
    }

    protected void onHook(E e) {
    }

    protected void onUnhook(E e) {
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

    final Descriptor getSuper() {
        return this.mSuper;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public final void hook(Object obj) {
        verifyThreadAccess();
        this.mSuper.hook(obj);
        onHook(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public final void unhook(Object obj) {
        verifyThreadAccess();
        onUnhook(obj);
        this.mSuper.unhook(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public final NodeType getNodeType(Object obj) {
        return onGetNodeType(obj);
    }

    protected NodeType onGetNodeType(E e) {
        return this.mSuper.getNodeType(e);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public final String getNodeName(Object obj) {
        return onGetNodeName(obj);
    }

    protected String onGetNodeName(E e) {
        return this.mSuper.getNodeName(e);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public final String getLocalName(Object obj) {
        return onGetLocalName(obj);
    }

    protected String onGetLocalName(E e) {
        return this.mSuper.getLocalName(e);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public final String getNodeValue(Object obj) {
        return onGetNodeValue(obj);
    }

    @Nullable
    public String onGetNodeValue(E e) {
        return this.mSuper.getNodeValue(e);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public final void getChildren(Object obj, Accumulator<Object> accumulator) {
        this.mSuper.getChildren(obj, accumulator);
        onGetChildren(obj, accumulator);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public final void getAttributes(Object obj, AttributeAccumulator attributeAccumulator) {
        this.mSuper.getAttributes(obj, attributeAccumulator);
        onGetAttributes(obj, attributeAccumulator);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public final void setAttributesAsText(Object obj, String str) {
        onSetAttributesAsText(obj, str);
    }

    protected void onSetAttributesAsText(E e, String str) {
        this.mSuper.setAttributesAsText(e, str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public final void getStyles(Object obj, StyleAccumulator styleAccumulator) {
        this.mSuper.getStyles(obj, styleAccumulator);
        onGetStyles(obj, styleAccumulator);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public final void getAccessibilityStyles(Object obj, StyleAccumulator styleAccumulator) {
        this.mSuper.getAccessibilityStyles(obj, styleAccumulator);
        onGetAccessibilityStyles(obj, styleAccumulator);
    }
}