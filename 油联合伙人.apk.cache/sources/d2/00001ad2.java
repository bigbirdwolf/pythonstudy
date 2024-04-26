package com.yltx.oil.partner.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;

/* loaded from: classes.dex */
public class ClipImageLayout extends RelativeLayout {
    private ClipImageBorderView mClipImageView;
    private int mHorizontalPadding;
    private ClipZoomImageView mZoomImageView;

    public ClipImageLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHorizontalPadding = 20;
        this.mZoomImageView = new ClipZoomImageView(context);
        this.mClipImageView = new ClipImageBorderView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        addView(this.mZoomImageView, layoutParams);
        addView(this.mClipImageView, layoutParams);
        this.mHorizontalPadding = (int) TypedValue.applyDimension(1, this.mHorizontalPadding, getResources().getDisplayMetrics());
        this.mZoomImageView.setHorizontalPadding(this.mHorizontalPadding);
        this.mClipImageView.setHorizontalPadding(this.mHorizontalPadding);
    }

    public void setHorizontalPadding(int i) {
        this.mHorizontalPadding = i;
    }

    public Bitmap clip() {
        return this.mZoomImageView.clip();
    }

    public ClipZoomImageView getZoomImageView() {
        return this.mZoomImageView;
    }
}