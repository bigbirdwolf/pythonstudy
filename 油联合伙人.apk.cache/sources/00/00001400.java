package com.umeng.social.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

/* loaded from: classes.dex */
class UMWaterMark {
    static final int RELATIVE_POSITION_HORIZONTAL_LEFT = 4;
    static final int RELATIVE_POSITION_HORIZONTAL_RIGHT = 3;
    static final int RELATIVE_POSITION_VERTICAL_BOTTOM = 1;
    static final int RELATIVE_POSITION_VERTICAL_TOP = 2;
    private static final String TAG = "UMWaterMark";
    private int mBottomMargin;
    private Context mContext;
    private int mLeftMargin;
    private int mRightMargin;
    private int mTopMargin;
    private float mScale = 0.3f;
    private Rect mAnchorMarkRect = new Rect();
    private int mVerticalRelativePosition = -1;
    private int mHorizontalRelativePosition = -1;
    private int mGravity = 51;
    private boolean mIsTransparent = false;
    private boolean mIsBringToFront = false;
    private float mAlpha = -1.0f;
    private int mDegree = -1;
    private Rect mMeasureRect = new Rect();

    Bitmap getMarkBitmap() {
        return null;
    }

    void releaseResource() {
    }

    public void setMargins(int i, int i2, int i3, int i4) {
        this.mLeftMargin = i;
        this.mTopMargin = i2;
        this.mRightMargin = i3;
        this.mBottomMargin = i4;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public void setGravity(int i) {
        if (i > 0 && this.mGravity != i) {
            this.mGravity = i;
        }
    }

    public void setScale(float f) {
        if (f < 0.0f || f > 1.0f) {
            return;
        }
        this.mScale = f;
    }

    public void setRotate(int i) {
        if (i < 0 || i > 360) {
            return;
        }
        this.mDegree = i;
    }

    public void bringToFront() {
        this.mIsBringToFront = true;
    }

    public void setAlpha(float f) {
        if (f < 0.0f || f > 1.0f) {
            return;
        }
        this.mAlpha = f;
    }

    public void setTransparent() {
        this.mIsTransparent = true;
    }

    public Bitmap compound(Bitmap bitmap) {
        Bitmap createBitmap;
        Canvas canvas;
        try {
            if (bitmap == null) {
                Log.e(TAG, "scr bitmap is null");
                return null;
            }
            Bitmap markBitmap = getMarkBitmap();
            if (markBitmap == null) {
                Log.e(TAG, "mark bitmap is null");
                return bitmap;
            }
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width > 0 && height > 0) {
                int markWidth = getMarkWidth();
                int markHeight = getMarkHeight();
                if (markWidth > 0 && markHeight > 0) {
                    if (this.mIsTransparent) {
                        createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                        canvas = new Canvas(createBitmap);
                        canvas.drawColor(0);
                    } else {
                        createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                        canvas = new Canvas(createBitmap);
                    }
                    canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
                    Matrix matrix = new Matrix();
                    float min = (this.mScale * Math.min(bitmap.getWidth(), bitmap.getHeight())) / Math.max(markWidth, markHeight);
                    matrix.postScale(min, min, getScaleAnchorX(markWidth), getScaleAnchorY(markHeight));
                    if (this.mDegree != -1) {
                        matrix.postRotate(this.mDegree, markWidth / 2, markHeight / 2);
                    }
                    matrix.postTranslate(isHorizontalRelativePosition() ? getRelativeDx(width) : getDx(width), isVerticalRelativePosition() ? getRelativeDy(height) : getDy(height));
                    if (this.mAlpha != -1.0f) {
                        Paint paint = new Paint();
                        paint.setAlpha((int) (this.mAlpha * 255.0f));
                        canvas.drawBitmap(markBitmap, matrix, paint);
                    } else {
                        canvas.drawBitmap(markBitmap, matrix, null);
                    }
                    canvas.save(31);
                    canvas.restore();
                    safelyRecycleBitmap(bitmap);
                    safelyRecycleBitmap(markBitmap);
                    releaseResource();
                    return createBitmap;
                }
                String str = TAG;
                Log.e(str, "mark bitmap is error, markWidth:" + markWidth + ", markHeight:" + markHeight);
                return bitmap;
            }
            String str2 = TAG;
            Log.e(str2, "mark bitmap is error, markWidth:" + width + ", markHeight:" + height);
            return bitmap;
        } catch (Exception unused) {
            return null;
        }
    }

    private float getScaleAnchorY(int i) {
        int i2 = this.mGravity & 112;
        if (i2 != 16) {
            if (i2 != 80) {
                return 0.0f;
            }
            return i;
        }
        return i / 2;
    }

    private float getScaleAnchorX(int i) {
        int i2 = this.mGravity & 7;
        if (i2 != 1) {
            if (i2 != 5) {
                return 0.0f;
            }
            return i;
        }
        return i / 2;
    }

    private void safelyRecycleBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                if (bitmap.isRecycled()) {
                    return;
                }
                bitmap.recycle();
            } catch (Exception unused) {
            }
        }
    }

    private float getRelativeDy(int i) {
        float f = this.mAnchorMarkRect.top;
        float f2 = this.mAnchorMarkRect.bottom;
        switch (this.mVerticalRelativePosition) {
            case 1:
                return f2 + dip2px(this.mTopMargin);
            case 2:
                return (f - getMarkHeight()) + (-dip2px(this.mBottomMargin));
            default:
                return getDy(i);
        }
    }

    private float getRelativeDx(int i) {
        float f = this.mAnchorMarkRect.left;
        float f2 = this.mAnchorMarkRect.right;
        switch (this.mHorizontalRelativePosition) {
            case 3:
                return f2 + dip2px(this.mLeftMargin);
            case 4:
                return (f - getMarkWidth()) + (-dip2px(this.mRightMargin));
            default:
                return getDx(i);
        }
    }

    private float getDy(int i) {
        int i2 = -dip2px(this.mBottomMargin);
        int dip2px = dip2px(this.mTopMargin);
        int i3 = this.mGravity & 112;
        if (i3 != 16) {
            return i3 != 80 ? dip2px : (i - getMarkHeight()) + i2;
        }
        if (dip2px != 0) {
            i2 = dip2px;
        }
        return (((i - getMarkHeight()) * 1.0f) / 2.0f) + i2;
    }

    private float getDx(int i) {
        int dip2px = dip2px(this.mLeftMargin);
        int i2 = -dip2px(this.mRightMargin);
        int i3 = this.mGravity & 7;
        if (i3 != 1) {
            return i3 != 5 ? dip2px : (i - getMarkWidth()) + i2;
        }
        if (dip2px == 0) {
            dip2px = i2;
        }
        return (((i - getMarkWidth()) * 1.0f) / 2.0f) + dip2px;
    }

    private int getMarkWidth() {
        if (getMarkBitmap() == null) {
            return -1;
        }
        return getMarkBitmap().getWidth();
    }

    private int getMarkHeight() {
        if (getMarkBitmap() == null) {
            return -1;
        }
        return getMarkBitmap().getHeight();
    }

    void setAnchorMarkHorizontalRect(Rect rect) {
        this.mAnchorMarkRect.set(rect.left, this.mAnchorMarkRect.top, rect.right, this.mAnchorMarkRect.bottom);
    }

    void setAnchorMarkVerticalRect(Rect rect) {
        this.mAnchorMarkRect = rect;
        this.mAnchorMarkRect.set(this.mAnchorMarkRect.left, rect.top, this.mAnchorMarkRect.right, rect.bottom);
    }

    Rect onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6 = 0;
        if (isHorizontalRelativePosition()) {
            i3 = 0;
            i4 = 0;
        } else {
            i3 = (int) getDx(i);
            i4 = getMarkWidth() + i3;
        }
        if (isVerticalRelativePosition()) {
            i5 = 0;
        } else {
            i6 = (int) getDy(i2);
            i5 = getMarkHeight() + i6;
        }
        this.mMeasureRect.set(i3, i6, i4, i5);
        return this.mMeasureRect;
    }

    void setHorizontalRelativePosition(int i) {
        this.mHorizontalRelativePosition = i;
    }

    void setVerticalRelativePosition(int i) {
        this.mVerticalRelativePosition = i;
    }

    void clearRelativePosition() {
        this.mHorizontalRelativePosition = -1;
        this.mVerticalRelativePosition = -1;
    }

    boolean isVerticalRelativePosition() {
        return this.mVerticalRelativePosition != -1;
    }

    boolean isHorizontalRelativePosition() {
        return this.mHorizontalRelativePosition != -1;
    }

    boolean isBringToFront() {
        return this.mIsBringToFront;
    }

    int dip2px(float f) {
        return (int) ((f * this.mContext.getResources().getDisplayMetrics().density) + 0.5f);
    }
}