package com.yltx.oil.partner.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/* loaded from: classes.dex */
public class ClipImageBorderView extends View {
    private int mBorderColor;
    private int mBorderWidth;
    private int mHorizontalPadding;
    private Paint mPaint;
    private int mVerticalPadding;
    private int mWidth;

    public ClipImageBorderView(Context context) {
        this(context, null);
    }

    public ClipImageBorderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ClipImageBorderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBorderColor = Color.parseColor("#FFFFFF");
        this.mBorderWidth = 1;
        this.mBorderWidth = (int) TypedValue.applyDimension(1, this.mBorderWidth, getResources().getDisplayMetrics());
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mWidth = getWidth() - (this.mHorizontalPadding * 2);
        this.mVerticalPadding = (getHeight() - this.mWidth) / 2;
        this.mPaint.setColor(Color.parseColor("#aa000000"));
        this.mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0.0f, 0.0f, this.mHorizontalPadding, getHeight(), this.mPaint);
        canvas.drawRect(getWidth() - this.mHorizontalPadding, 0.0f, getWidth(), getHeight(), this.mPaint);
        canvas.drawRect(this.mHorizontalPadding, 0.0f, getWidth() - this.mHorizontalPadding, this.mVerticalPadding, this.mPaint);
        canvas.drawRect(this.mHorizontalPadding, getHeight() - this.mVerticalPadding, getWidth() - this.mHorizontalPadding, getHeight(), this.mPaint);
        this.mPaint.setColor(this.mBorderColor);
        this.mPaint.setStrokeWidth(this.mBorderWidth);
        this.mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(this.mHorizontalPadding, this.mVerticalPadding, getWidth() - this.mHorizontalPadding, getHeight() - this.mVerticalPadding, this.mPaint);
    }

    public void setHorizontalPadding(int i) {
        this.mHorizontalPadding = i;
    }
}