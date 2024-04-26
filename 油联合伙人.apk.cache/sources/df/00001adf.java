package com.yltx.oil.partner.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/* loaded from: classes.dex */
public class ScrollViewPager extends ViewPager {
    private boolean isEnableScroll;

    public ScrollViewPager(Context context) {
        super(context);
        this.isEnableScroll = true;
    }

    public ScrollViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isEnableScroll = true;
    }

    public void setEnableScroll(boolean z) {
        this.isEnableScroll = z;
    }

    @Override // android.support.v4.view.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.isEnableScroll) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.support.v4.view.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.isEnableScroll) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }
}