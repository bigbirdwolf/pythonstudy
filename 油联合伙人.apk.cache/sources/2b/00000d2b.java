package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;

/* loaded from: classes.dex */
public final class ObjectDescriptor extends Descriptor {
    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public void getAccessibilityStyles(Object obj, StyleAccumulator styleAccumulator) {
    }

    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public void getAttributes(Object obj, AttributeAccumulator attributeAccumulator) {
    }

    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public void getChildren(Object obj, Accumulator<Object> accumulator) {
    }

    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public String getNodeValue(Object obj) {
        return null;
    }

    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public void getStyles(Object obj, StyleAccumulator styleAccumulator) {
    }

    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public void hook(Object obj) {
    }

    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public void setAttributesAsText(Object obj, String str) {
    }

    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public void unhook(Object obj) {
    }

    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public NodeType getNodeType(Object obj) {
        return NodeType.ELEMENT_NODE;
    }

    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public String getNodeName(Object obj) {
        return obj.getClass().getName();
    }

    @Override // com.facebook.stetho.inspector.elements.NodeDescriptor
    public String getLocalName(Object obj) {
        return getNodeName(obj);
    }
}