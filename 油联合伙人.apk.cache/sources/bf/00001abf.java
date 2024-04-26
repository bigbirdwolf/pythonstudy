package com.yltx.oil.partner.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class RodHoriztalProgressBar extends ProgressBar {
    private static final int DEFAULT_REACH_COLOR = -14355095;
    private static final int DEFAULT_REACH_HEIGHT = 2;
    private static final int DEFAULT_TEXT_COLOR = -14355095;
    private static final int DEFAULT_TEXT_OFFSET = 5;
    private static final int DEFAULT_TEXT_SIZE = 12;
    private static final int DEFAULT_UNREACH_COLOR = -4144960;
    private static final int DEFAULT_UNREACH_HEIGHT = 2;
    private final Drawable mKedu;
    protected Paint mPaint;
    private final Drawable mProgress;
    private int mProgressHeight;
    private int mProgressWidght;
    protected int mReachColor;
    protected int mReachHeight;
    protected int mTextColor;
    protected int mTextOffset;
    private final int mTextOffsetLine;
    protected int mTextSize;
    protected int mUnReachColor;
    protected int mUnReachHeight;
    private int mkeduHeight;
    private final int mkeduWidget;

    public RodHoriztalProgressBar(Context context) {
        this(context, null);
    }

    public RodHoriztalProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RodHoriztalProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mReachColor = -14355095;
        this.mUnReachColor = DEFAULT_UNREACH_COLOR;
        this.mReachHeight = dp2px(2);
        this.mUnReachHeight = dp2px(2);
        this.mTextColor = -14355095;
        this.mTextSize = sp2px(12);
        this.mTextOffset = dp2px(5);
        this.mPaint = new Paint();
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.RodHoriztalProgressBar);
        this.mReachColor = obtainStyledAttributes.getColor(0, this.mReachColor);
        this.mUnReachColor = obtainStyledAttributes.getColor(8, this.mUnReachColor);
        this.mReachHeight = (int) obtainStyledAttributes.getDimension(1, this.mReachHeight);
        this.mUnReachHeight = (int) obtainStyledAttributes.getDimension(9, this.mUnReachHeight);
        this.mTextColor = obtainStyledAttributes.getColor(4, this.mTextColor);
        this.mTextSize = (int) obtainStyledAttributes.getDimension(7, this.mTextSize);
        this.mTextOffset = (int) obtainStyledAttributes.getDimension(5, this.mTextOffset);
        this.mTextOffsetLine = (int) obtainStyledAttributes.getDimension(5, this.mTextOffset);
        this.mKedu = obtainStyledAttributes.getDrawable(2);
        this.mProgress = obtainStyledAttributes.getDrawable(3);
        obtainStyledAttributes.recycle();
        if (this.mProgress != null) {
            this.mProgressWidght = this.mProgress.getIntrinsicWidth();
            this.mProgressHeight = this.mProgress.getIntrinsicHeight();
        }
        if (this.mKedu != null) {
            this.mkeduHeight = this.mKedu.getIntrinsicHeight();
        }
        this.mkeduWidget = this.mKedu.getIntrinsicWidth();
        this.mPaint.setTextSize(this.mTextSize);
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected synchronized void onMeasure(int i, int i2) {
        setMeasuredDimension(this.mkeduWidget, this.mProgressHeight + this.mTextOffsetLine + this.mReachHeight + this.mkeduHeight);
    }

    public int getTextWidth(String str) {
        if (str == null || str.length() <= 0) {
            return 0;
        }
        int length = str.length();
        float[] fArr = new float[length];
        this.mPaint.getTextWidths(str, fArr);
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i += (int) Math.ceil(fArr[i2]);
        }
        return i;
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        float f;
        boolean z;
        String str = getProgress() + "%";
        float progress = this.mkeduWidget * ((getProgress() * 1.0f) / getMax());
        if (progress > this.mkeduWidget) {
            f = this.mkeduWidget;
            z = true;
        } else {
            f = progress;
            z = false;
        }
        if (this.mProgress != null) {
            float f2 = ((int) f) - (this.mProgressWidght / 2);
            float f3 = (this.mProgressWidght / 2) + f;
            if (f2 >= this.mkeduWidget - this.mProgressWidght) {
                f2 = this.mkeduWidget - this.mProgressWidght;
            }
            if (f3 >= this.mkeduWidget) {
                f3 = this.mkeduWidget;
            }
            if (f3 < this.mProgressWidght) {
                f3 = this.mProgressWidght;
            }
            if (f2 <= 0.0f) {
                f2 = 0.0f;
            }
            this.mProgress.setBounds((int) f2, 0, (int) f3, this.mProgressHeight);
            this.mProgress.draw(canvas);
            int textWidth = getTextWidth(str) / 2;
            int i = this.mProgressWidght / 2;
            float f4 = i - textWidth;
            float f5 = f2 + f4;
            float f6 = (this.mProgressHeight / 2) + 5;
            if (f5 >= this.mkeduWidget - i) {
                f5 = (this.mkeduWidget - i) - textWidth;
            }
            if (f5 <= f4) {
                f5 = f4;
            }
            this.mPaint.setColor(this.mTextColor);
            canvas.drawText(str, f5, f6, this.mPaint);
        }
        canvas.save();
        canvas.translate(getPaddingLeft(), this.mProgressHeight + this.mTextOffsetLine);
        this.mPaint.setColor(this.mReachColor);
        this.mPaint.setStrokeWidth(this.mReachHeight);
        canvas.drawLine(0.0f, this.mReachHeight + 0, f, this.mReachHeight + 0, this.mPaint);
        if (this.mKedu != null) {
            this.mKedu.setBounds(0, 0, this.mkeduWidget, this.mkeduHeight);
            this.mKedu.draw(canvas);
        }
        if (!z) {
            this.mPaint.setColor(this.mUnReachColor);
            this.mPaint.setStrokeWidth(this.mUnReachHeight);
            canvas.drawLine(f, this.mProgressHeight, this.mkeduWidget, this.mProgressHeight, this.mPaint);
        }
        canvas.restore();
    }

    protected int dp2px(int i) {
        return (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }

    protected int sp2px(int i) {
        return (int) TypedValue.applyDimension(2, i, getResources().getDisplayMetrics());
    }
}