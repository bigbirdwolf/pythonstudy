package com.bigkoo.convenientbanner;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.yltx.oil.partner.base.HttpStatusCodes;

/* loaded from: classes.dex */
public class ViewPagerScroller extends Scroller {
    private int mScrollDuration;
    private boolean zero;

    public ViewPagerScroller(Context context) {
        super(context);
        this.mScrollDuration = HttpStatusCodes.CODE_800;
    }

    public ViewPagerScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
        this.mScrollDuration = HttpStatusCodes.CODE_800;
    }

    public ViewPagerScroller(Context context, Interpolator interpolator, boolean z) {
        super(context, interpolator, z);
        this.mScrollDuration = HttpStatusCodes.CODE_800;
    }

    @Override // android.widget.Scroller
    public void startScroll(int i, int i2, int i3, int i4, int i5) {
        super.startScroll(i, i2, i3, i4, this.zero ? 0 : this.mScrollDuration);
    }

    @Override // android.widget.Scroller
    public void startScroll(int i, int i2, int i3, int i4) {
        super.startScroll(i, i2, i3, i4, this.zero ? 0 : this.mScrollDuration);
    }

    public int getScrollDuration() {
        return this.mScrollDuration;
    }

    public void setScrollDuration(int i) {
        this.mScrollDuration = i;
    }

    public boolean isZero() {
        return this.zero;
    }

    public void setZero(boolean z) {
        this.zero = z;
    }
}