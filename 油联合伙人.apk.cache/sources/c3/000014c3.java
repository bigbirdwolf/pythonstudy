package com.umeng.socialize.shareboard;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ImageView;

/* loaded from: classes.dex */
public class SocializeImageView extends ImageButton {
    public static int BG_SHAPE_CIRCULAR = 1;
    public static int BG_SHAPE_NONE = 0;
    public static int BG_SHAPE_ROUNDED_SQUARE = 2;
    private int mAngle;
    private int mBgShape;
    private int mIconPressedColor;
    private boolean mIsPressEffect;
    private boolean mIsSelected;
    private int mNormalColor;
    protected Paint mNormalPaint;
    private int mPressedColor;
    protected Paint mPressedPaint;
    private RectF mSquareRect;

    public SocializeImageView(Context context) {
        super(context);
        init();
    }

    public SocializeImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public SocializeImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    @TargetApi(21)
    public SocializeImageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    private void init() {
        if (Build.VERSION.SDK_INT < 16) {
            setBackgroundDrawable(null);
        } else {
            setBackground(null);
        }
        setClickable(false);
        setScaleType(ImageView.ScaleType.CENTER_INSIDE);
    }

    public void setBackgroundShape(int i) {
        setBackgroundShape(i, 0);
    }

    public void setBackgroundShape(int i, int i2) {
        this.mBgShape = i;
        if (i != BG_SHAPE_ROUNDED_SQUARE) {
            this.mAngle = 0;
            return;
        }
        this.mAngle = (int) ((i2 * getResources().getDisplayMetrics().density) + 0.5f);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        setBackgroundColor(i, 0);
    }

    public void setBackgroundColor(int i, int i2) {
        this.mNormalColor = i;
        this.mPressedColor = i2;
        setPressEffectEnable(i2 != 0);
        if (this.mNormalColor != 0) {
            this.mNormalPaint = new Paint();
            this.mNormalPaint.setStyle(Paint.Style.FILL);
            this.mNormalPaint.setAntiAlias(true);
            this.mNormalPaint.setColor(i);
        }
        if (this.mPressedColor != 0) {
            this.mPressedPaint = new Paint();
            this.mPressedPaint.setStyle(Paint.Style.FILL);
            this.mPressedPaint.setAntiAlias(true);
            this.mPressedPaint.setColor(i2);
        }
    }

    public void setPressedColor(int i) {
        setPressEffectEnable(i != 0);
        this.mIconPressedColor = i;
    }

    public void setPressEffectEnable(boolean z) {
        this.mIsPressEffect = z;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mIsPressEffect) {
            if (isPressed()) {
                if (BG_SHAPE_NONE == this.mBgShape) {
                    if (this.mIconPressedColor != 0) {
                        setColorFilter(this.mIconPressedColor, PorterDuff.Mode.SRC_ATOP);
                        return;
                    }
                    return;
                }
                this.mIsSelected = true;
                invalidate();
            } else if (BG_SHAPE_NONE == this.mBgShape) {
                clearColorFilter();
            } else {
                this.mIsSelected = false;
                invalidate();
            }
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mBgShape == BG_SHAPE_NONE) {
            super.onDraw(canvas);
            return;
        }
        if (this.mIsSelected) {
            if (this.mIsPressEffect && this.mPressedPaint != null) {
                if (this.mBgShape == BG_SHAPE_CIRCULAR) {
                    drawCircle(canvas, this.mPressedPaint);
                } else if (this.mBgShape == BG_SHAPE_ROUNDED_SQUARE) {
                    drawRect(canvas, this.mPressedPaint);
                }
            }
        } else if (this.mBgShape == BG_SHAPE_CIRCULAR) {
            drawCircle(canvas, this.mNormalPaint);
        } else if (this.mBgShape == BG_SHAPE_ROUNDED_SQUARE) {
            drawRect(canvas, this.mNormalPaint);
        }
        super.onDraw(canvas);
    }

    private void drawCircle(Canvas canvas, Paint paint) {
        float measuredWidth = getMeasuredWidth() / 2;
        canvas.drawCircle(measuredWidth, measuredWidth, measuredWidth, paint);
    }

    private void drawRect(Canvas canvas, Paint paint) {
        if (this.mSquareRect == null) {
            this.mSquareRect = new RectF();
            this.mSquareRect.left = 0.0f;
            this.mSquareRect.top = 0.0f;
            this.mSquareRect.right = getMeasuredWidth();
            this.mSquareRect.bottom = getMeasuredWidth();
        }
        canvas.drawRoundRect(this.mSquareRect, this.mAngle, this.mAngle, paint);
    }

    protected int dip2px(float f) {
        return (int) ((f * getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }
}