package me.zhanghai.android.materialprogressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import me.zhanghai.android.materialprogressbar.internal.ThemeUtils;

/* loaded from: classes.dex */
class SingleHorizontalProgressDrawable extends ProgressDrawableBase {
    private static final int LEVEL_MAX = 10000;
    private static final float PADDED_INTRINSIC_HEIGHT_DP = 16.0f;
    private static final float PROGRESS_INTRINSIC_HEIGHT_DP = 3.2f;
    private static final RectF RECT_BOUND = new RectF(-180.0f, -1.0f, 180.0f, 1.0f);
    private static final RectF RECT_PADDED_BOUND = new RectF(-180.0f, -5.0f, 180.0f, 5.0f);
    private int mPaddedIntrinsicHeight;
    private int mProgressIntrinsicHeight;
    private boolean mShowTrack;
    private float mTrackAlpha;

    public SingleHorizontalProgressDrawable(Context context) {
        super(context);
        this.mShowTrack = true;
        float f = context.getResources().getDisplayMetrics().density;
        this.mProgressIntrinsicHeight = Math.round(PROGRESS_INTRINSIC_HEIGHT_DP * f);
        this.mPaddedIntrinsicHeight = Math.round(f * PADDED_INTRINSIC_HEIGHT_DP);
        this.mTrackAlpha = ThemeUtils.getFloatFromAttrRes(16842803, context);
    }

    public boolean getShowTrack() {
        return this.mShowTrack;
    }

    public void setShowTrack(boolean z) {
        if (this.mShowTrack != z) {
            this.mShowTrack = z;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mUseIntrinsicPadding ? this.mPaddedIntrinsicHeight : this.mProgressIntrinsicHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        if (this.mAlpha == 0) {
            return -2;
        }
        if (this.mAlpha == 255) {
            return (!this.mShowTrack || this.mTrackAlpha == 1.0f) ? -1 : -3;
        }
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i) {
        invalidateSelf();
        return true;
    }

    @Override // me.zhanghai.android.materialprogressbar.ProgressDrawableBase
    protected void onPreparePaint(Paint paint) {
        paint.setStyle(Paint.Style.FILL);
    }

    @Override // me.zhanghai.android.materialprogressbar.ProgressDrawableBase
    protected void onDraw(Canvas canvas, int i, int i2, Paint paint) {
        if (this.mUseIntrinsicPadding) {
            canvas.scale(i / RECT_PADDED_BOUND.width(), i2 / RECT_PADDED_BOUND.height());
            canvas.translate(RECT_PADDED_BOUND.width() / 2.0f, RECT_PADDED_BOUND.height() / 2.0f);
        } else {
            canvas.scale(i / RECT_BOUND.width(), i2 / RECT_BOUND.height());
            canvas.translate(RECT_BOUND.width() / 2.0f, RECT_BOUND.height() / 2.0f);
        }
        if (this.mShowTrack) {
            paint.setAlpha(Math.round(this.mAlpha * this.mTrackAlpha));
            drawTrackRect(canvas, paint);
            paint.setAlpha(this.mAlpha);
        }
        drawProgressRect(canvas, paint);
    }

    private static void drawTrackRect(Canvas canvas, Paint paint) {
        canvas.drawRect(RECT_BOUND, paint);
    }

    private void drawProgressRect(Canvas canvas, Paint paint) {
        int level = getLevel();
        if (level == 0) {
            return;
        }
        int save = canvas.save();
        canvas.scale(level / 10000.0f, 1.0f, RECT_BOUND.left, 0.0f);
        canvas.drawRect(RECT_BOUND, paint);
        canvas.restoreToCount(save);
    }
}