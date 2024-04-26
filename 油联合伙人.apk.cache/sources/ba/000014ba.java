package com.umeng.socialize.shareboard;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: classes.dex */
public class IndicatorView extends View {
    private int mIndicatorMargin;
    private int mIndicatorWidth;
    private float mLeftPosition;
    private Paint mNormalPaint;
    private int mPageCount;
    private Paint mSelectPaint;
    private int mSelectPosition;

    public IndicatorView(Context context) {
        super(context);
    }

    public IndicatorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public IndicatorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public IndicatorView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(measureWidth(i), measureHeight(i2));
    }

    private int measureWidth(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int paddingLeft = getPaddingLeft() + getPaddingRight() + (this.mIndicatorWidth * this.mPageCount * 2) + (this.mIndicatorMargin * (this.mPageCount - 1));
        this.mLeftPosition = ((getMeasuredWidth() - paddingLeft) / 2.0f) + getPaddingLeft();
        if (mode == 1073741824) {
            return Math.max(paddingLeft, size);
        }
        return mode == Integer.MIN_VALUE ? Math.min(paddingLeft, size) : paddingLeft;
    }

    private int measureHeight(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int paddingTop = getPaddingTop() + getPaddingBottom() + (this.mIndicatorWidth * 2);
        return mode == Integer.MIN_VALUE ? Math.min(paddingTop, size) : paddingTop;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mSelectPaint == null || this.mNormalPaint == null) {
            return;
        }
        float f = this.mLeftPosition + this.mIndicatorWidth;
        int i = 0;
        while (i < this.mPageCount) {
            canvas.drawCircle(f, this.mIndicatorWidth, this.mIndicatorWidth, i == this.mSelectPosition ? this.mSelectPaint : this.mNormalPaint);
            f += this.mIndicatorMargin + (this.mIndicatorWidth * 2);
            i++;
        }
    }

    public void setSelectedPosition(int i) {
        this.mSelectPosition = i;
        invalidate();
    }

    public void setPageCount(int i) {
        this.mPageCount = i;
        invalidate();
    }

    public void setIndicator(int i, int i2) {
        this.mIndicatorMargin = dip2px(i2);
        this.mIndicatorWidth = dip2px(i);
    }

    public void setIndicatorColor(int i, int i2) {
        this.mSelectPaint = new Paint();
        this.mSelectPaint.setStyle(Paint.Style.FILL);
        this.mSelectPaint.setAntiAlias(true);
        this.mSelectPaint.setColor(i2);
        this.mNormalPaint = new Paint();
        this.mNormalPaint.setStyle(Paint.Style.FILL);
        this.mNormalPaint.setAntiAlias(true);
        this.mNormalPaint.setColor(i);
    }

    protected int dip2px(float f) {
        return (int) ((f * getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }
}