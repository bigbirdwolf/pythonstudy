package com.yltx.oil.partner.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/* loaded from: classes.dex */
public class VpScrollView extends ScrollView {
    private boolean mIsVpDragger;
    private final int mTouchSlop;
    private float startX;
    private float startY;

    public VpScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.startY = motionEvent.getY();
                this.startX = motionEvent.getX();
                this.mIsVpDragger = false;
                break;
            case 1:
            case 3:
                this.mIsVpDragger = false;
                break;
            case 2:
                if (this.mIsVpDragger) {
                    return false;
                }
                float y = motionEvent.getY();
                float abs = Math.abs(motionEvent.getX() - this.startX);
                float abs2 = Math.abs(y - this.startY);
                if (abs > this.mTouchSlop && abs > abs2) {
                    this.mIsVpDragger = true;
                    return false;
                }
                break;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}