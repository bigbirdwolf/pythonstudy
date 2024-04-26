package org.jsoup.nodes;

/* loaded from: classes.dex */
public class BooleanAttribute extends Attribute {
    @Override // org.jsoup.nodes.Attribute
    protected boolean isBooleanAttribute() {
        return true;
    }

    public BooleanAttribute(String str) {
        super(str, "");
    }
}