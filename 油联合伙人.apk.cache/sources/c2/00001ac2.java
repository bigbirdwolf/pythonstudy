package com.yltx.oil.partner.utils;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

/* loaded from: classes.dex */
public class ScrollGridLayoutManager extends GridLayoutManager {
    private boolean isScrollEnabled;

    public ScrollGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.isScrollEnabled = true;
    }

    public ScrollGridLayoutManager(Context context, int i) {
        super(context, i);
        this.isScrollEnabled = true;
    }

    public ScrollGridLayoutManager(Context context, int i, boolean z) {
        super(context, i);
        this.isScrollEnabled = true;
        this.isScrollEnabled = z;
    }

    public ScrollGridLayoutManager(Context context, int i, int i2, boolean z) {
        super(context, i, i2, z);
        this.isScrollEnabled = true;
    }

    public void setScrollEnabled(boolean z) {
        this.isScrollEnabled = z;
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.isScrollEnabled && super.canScrollVertically();
    }
}