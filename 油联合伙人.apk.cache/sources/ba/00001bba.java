package me.zhanghai.android.materialprogressbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import me.zhanghai.android.materialprogressbar.internal.ThemeUtils;

/* loaded from: classes.dex */
abstract class ProgressDrawableBase extends Drawable implements IntrinsicPaddingDrawable, TintableDrawable {
    protected ColorFilter mColorFilter;
    private Paint mPaint;
    protected PorterDuffColorFilter mTintFilter;
    protected ColorStateList mTintList;
    protected boolean mUseIntrinsicPadding = true;
    protected int mAlpha = 255;
    protected PorterDuff.Mode mTintMode = PorterDuff.Mode.SRC_IN;

    protected abstract void onDraw(Canvas canvas, int i, int i2, Paint paint);

    protected abstract void onPreparePaint(Paint paint);

    public ProgressDrawableBase(Context context) {
        setTint(ThemeUtils.getColorFromAttrRes(R.attr.colorControlActivated, context));
    }

    public boolean getUseIntrinsicPadding() {
        return this.mUseIntrinsicPadding;
    }

    public void setUseIntrinsicPadding(boolean z) {
        if (this.mUseIntrinsicPadding != z) {
            this.mUseIntrinsicPadding = z;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (this.mAlpha != i) {
            this.mAlpha = i;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.mColorFilter;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.mColorFilter = colorFilter;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable, me.zhanghai.android.materialprogressbar.TintableDrawable
    public void setTint(@ColorInt int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable, me.zhanghai.android.materialprogressbar.TintableDrawable
    public void setTintList(@Nullable ColorStateList colorStateList) {
        this.mTintList = colorStateList;
        this.mTintFilter = makeTintFilter(this.mTintList, this.mTintMode);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable, me.zhanghai.android.materialprogressbar.TintableDrawable
    public void setTintMode(@NonNull PorterDuff.Mode mode) {
        this.mTintMode = mode;
        this.mTintFilter = makeTintFilter(this.mTintList, this.mTintMode);
        invalidateSelf();
    }

    private PorterDuffColorFilter makeTintFilter(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    private boolean needMirroring() {
        return DrawableCompat.isAutoMirrored(this) && DrawableCompat.getLayoutDirection(this) == 1;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (bounds.width() == 0 || bounds.height() == 0) {
            return;
        }
        if (this.mPaint == null) {
            this.mPaint = new Paint();
            this.mPaint.setAntiAlias(true);
            this.mPaint.setColor(-16777216);
            onPreparePaint(this.mPaint);
        }
        this.mPaint.setAlpha(this.mAlpha);
        this.mPaint.setColorFilter(this.mColorFilter != null ? this.mColorFilter : this.mTintFilter);
        int save = canvas.save();
        canvas.translate(bounds.left, bounds.top);
        if (needMirroring()) {
            canvas.translate(bounds.width(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        onDraw(canvas, bounds.width(), bounds.height(), this.mPaint);
        canvas.restoreToCount(save);
    }
}