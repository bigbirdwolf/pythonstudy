package com.yltx.oil.partner.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/* loaded from: classes.dex */
public class ClipZoomImageView extends AppCompatImageView implements ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener {
    public static float SCALE_MAX = 4.0f;
    private static float SCALE_MID = 2.0f;
    private static final String TAG = "ClipZoomImageView";
    private float initScale;
    private boolean isAutoScale;
    private boolean isCanDrag;
    private int lastPointerCount;
    private GestureDetector mGestureDetector;
    private int mHorizontalPadding;
    private float mLastX;
    private float mLastY;
    private ScaleGestureDetector mScaleGestureDetector;
    private final Matrix mScaleMatrix;
    private int mTouchSlop;
    private int mVerticalPadding;
    private final float[] matrixValues;
    private boolean once;

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        return true;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
    }

    public ClipZoomImageView(Context context) {
        this(context, null);
    }

    @SuppressLint({"ResourceType"})
    public ClipZoomImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.initScale = 1.0f;
        this.once = true;
        this.matrixValues = new float[9];
        this.mScaleGestureDetector = null;
        this.mScaleMatrix = new Matrix();
        setScaleType(ImageView.ScaleType.MATRIX);
        this.mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() { // from class: com.yltx.oil.partner.widget.ClipZoomImageView.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (ClipZoomImageView.this.isAutoScale) {
                    return true;
                }
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (ClipZoomImageView.this.getScale() < ClipZoomImageView.SCALE_MID) {
                    ClipZoomImageView clipZoomImageView = ClipZoomImageView.this;
                    ClipZoomImageView clipZoomImageView2 = ClipZoomImageView.this;
                    clipZoomImageView2.getClass();
                    clipZoomImageView.postDelayed(new AutoScaleRunnable(ClipZoomImageView.SCALE_MID, x, y), 16L);
                    ClipZoomImageView.this.isAutoScale = true;
                } else {
                    ClipZoomImageView clipZoomImageView3 = ClipZoomImageView.this;
                    ClipZoomImageView clipZoomImageView4 = ClipZoomImageView.this;
                    clipZoomImageView4.getClass();
                    clipZoomImageView3.postDelayed(new AutoScaleRunnable(ClipZoomImageView.this.initScale, x, y), 16L);
                    ClipZoomImageView.this.isAutoScale = true;
                }
                return true;
            }
        });
        this.mScaleGestureDetector = new ScaleGestureDetector(context, this);
        setOnTouchListener(this);
        setBackgroundColor(getResources().getColor(17170444));
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        float scale = getScale();
        float scaleFactor = scaleGestureDetector.getScaleFactor();
        if (getDrawable() == null) {
            return true;
        }
        if ((scale < SCALE_MAX && scaleFactor > 1.0f) || (scale > this.initScale && scaleFactor < 1.0f)) {
            if (scaleFactor * scale < this.initScale) {
                scaleFactor = this.initScale / scale;
            }
            if (scaleFactor * scale > SCALE_MAX) {
                scaleFactor = SCALE_MAX / scale;
            }
            this.mScaleMatrix.postScale(scaleFactor, scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
            checkBorder();
            setImageMatrix(this.mScaleMatrix);
        }
        return true;
    }

    private RectF getMatrixRectF() {
        Matrix matrix = this.mScaleMatrix;
        RectF rectF = new RectF();
        Drawable drawable = getDrawable();
        if (drawable != null) {
            rectF.set(0.0f, 0.0f, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            matrix.mapRect(rectF);
        }
        return rectF;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.mGestureDetector.onTouchEvent(motionEvent)) {
            return true;
        }
        this.mScaleGestureDetector.onTouchEvent(motionEvent);
        int pointerCount = motionEvent.getPointerCount();
        float f = 0.0f;
        float f2 = 0.0f;
        for (int i = 0; i < pointerCount; i++) {
            f += motionEvent.getX(i);
            f2 += motionEvent.getY(i);
        }
        float f3 = pointerCount;
        float f4 = f / f3;
        float f5 = f2 / f3;
        if (pointerCount != this.lastPointerCount) {
            this.isCanDrag = false;
            this.mLastX = f4;
            this.mLastY = f5;
        }
        this.lastPointerCount = pointerCount;
        switch (motionEvent.getAction()) {
            case 1:
            case 3:
                this.lastPointerCount = 0;
                break;
            case 2:
                float f6 = f4 - this.mLastX;
                float f7 = f5 - this.mLastY;
                if (!this.isCanDrag) {
                    this.isCanDrag = isCanDrag(f6, f7);
                }
                if (this.isCanDrag && getDrawable() != null) {
                    RectF matrixRectF = getMatrixRectF();
                    if (matrixRectF.width() <= getWidth() - (this.mHorizontalPadding * 2)) {
                        f6 = 0.0f;
                    }
                    if (matrixRectF.height() <= getHeight() - (this.mVerticalPadding * 2)) {
                        f7 = 0.0f;
                    }
                    this.mScaleMatrix.postTranslate(f6, f7);
                    checkBorder();
                    setImageMatrix(this.mScaleMatrix);
                }
                this.mLastX = f4;
                this.mLastY = f5;
                break;
        }
        return true;
    }

    public final float getScale() {
        this.mScaleMatrix.getValues(this.matrixValues);
        return this.matrixValues[0];
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        Drawable drawable;
        if (!this.once || (drawable = getDrawable()) == null) {
            return;
        }
        this.mVerticalPadding = (getHeight() - (getWidth() - (this.mHorizontalPadding * 2))) / 2;
        int width = getWidth();
        int height = getHeight();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        float width2 = (intrinsicWidth >= getWidth() - (this.mHorizontalPadding * 2) || intrinsicHeight <= getHeight() - (this.mVerticalPadding * 2)) ? 1.0f : ((getWidth() * 1.0f) - (this.mHorizontalPadding * 2)) / intrinsicWidth;
        if (intrinsicHeight < getHeight() - (this.mVerticalPadding * 2) && intrinsicWidth > getWidth() - (this.mHorizontalPadding * 2)) {
            width2 = ((getHeight() * 1.0f) - (this.mVerticalPadding * 2)) / intrinsicHeight;
        }
        if (intrinsicWidth < getWidth() - (this.mHorizontalPadding * 2) && intrinsicHeight < getHeight() - (this.mVerticalPadding * 2)) {
            width2 = Math.max(((getWidth() * 1.0f) - (this.mHorizontalPadding * 2)) / intrinsicWidth, ((getHeight() * 1.0f) - (this.mVerticalPadding * 2)) / intrinsicHeight);
        }
        this.initScale = width2;
        SCALE_MID = this.initScale * 2.0f;
        SCALE_MAX = this.initScale * 4.0f;
        this.mScaleMatrix.postTranslate((width - intrinsicWidth) / 2, (height - intrinsicHeight) / 2);
        this.mScaleMatrix.postScale(width2, width2, getWidth() / 2, getHeight() / 2);
        setImageMatrix(this.mScaleMatrix);
        this.once = false;
    }

    public Bitmap clip() {
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        draw(new Canvas(createBitmap));
        return Bitmap.createBitmap(createBitmap, this.mHorizontalPadding, this.mVerticalPadding, getWidth() - (this.mHorizontalPadding * 2), getWidth() - (this.mHorizontalPadding * 2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkBorder() {
        float f;
        RectF matrixRectF = getMatrixRectF();
        int width = getWidth();
        int height = getHeight();
        double width2 = matrixRectF.width();
        Double.isNaN(width2);
        int i = ((width2 + 0.01d) > (width - (this.mHorizontalPadding * 2)) ? 1 : ((width2 + 0.01d) == (width - (this.mHorizontalPadding * 2)) ? 0 : -1));
        if (i >= 0) {
            f = matrixRectF.right < ((float) (width - this.mHorizontalPadding)) ? (width - this.mHorizontalPadding) - matrixRectF.right : matrixRectF.left > ((float) this.mHorizontalPadding) ? (-matrixRectF.left) + this.mHorizontalPadding : 0.0f;
        } else {
            f = 0.0f;
        }
        double height2 = matrixRectF.height();
        Double.isNaN(height2);
        if (height2 + 0.01d >= height - (this.mVerticalPadding * 2)) {
            r3 = matrixRectF.top > ((float) this.mVerticalPadding) ? (-matrixRectF.top) + this.mVerticalPadding : 0.0f;
            if (matrixRectF.bottom < height - this.mVerticalPadding) {
                r3 = (height - this.mVerticalPadding) - matrixRectF.bottom;
            }
        }
        this.mScaleMatrix.postTranslate(f, r3);
    }

    private boolean isCanDrag(float f, float f2) {
        return Math.sqrt((double) ((f * f) + (f2 * f2))) >= ((double) this.mTouchSlop);
    }

    public void setHorizontalPadding(int i) {
        this.mHorizontalPadding = i;
    }

    /* loaded from: classes.dex */
    private class AutoScaleRunnable implements Runnable {
        static final float BIGGER = 1.07f;
        static final float SMALLER = 0.93f;
        private float mTargetScale;
        private float tmpScale;
        private float x;
        private float y;

        public AutoScaleRunnable(float f, float f2, float f3) {
            this.mTargetScale = f;
            this.x = f2;
            this.y = f3;
            if (ClipZoomImageView.this.getScale() < this.mTargetScale) {
                this.tmpScale = BIGGER;
            } else {
                this.tmpScale = SMALLER;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            ClipZoomImageView.this.mScaleMatrix.postScale(this.tmpScale, this.tmpScale, this.x, this.y);
            ClipZoomImageView.this.checkBorder();
            ClipZoomImageView.this.setImageMatrix(ClipZoomImageView.this.mScaleMatrix);
            float scale = ClipZoomImageView.this.getScale();
            if ((this.tmpScale <= 1.0f || scale >= this.mTargetScale) && (this.tmpScale >= 1.0f || this.mTargetScale >= scale)) {
                float f = this.mTargetScale / scale;
                ClipZoomImageView.this.mScaleMatrix.postScale(f, f, this.x, this.y);
                ClipZoomImageView.this.checkBorder();
                ClipZoomImageView.this.setImageMatrix(ClipZoomImageView.this.mScaleMatrix);
                ClipZoomImageView.this.isAutoScale = false;
                return;
            }
            ClipZoomImageView.this.postDelayed(this, 16L);
        }
    }
}