package me.zhanghai.android.materialprogressbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import me.zhanghai.android.materialprogressbar.internal.ThemeUtils;

/* loaded from: classes.dex */
public class HorizontalProgressDrawable extends LayerDrawable implements IntrinsicPaddingDrawable, ShowTrackDrawable, TintableDrawable {
    private SingleHorizontalProgressDrawable mProgressDrawable;
    private int mSecondaryAlpha;
    private SingleHorizontalProgressDrawable mSecondaryProgressDrawable;
    private SingleHorizontalProgressDrawable mTrackDrawable;

    public HorizontalProgressDrawable(Context context) {
        super(new Drawable[]{new SingleHorizontalProgressDrawable(context), new SingleHorizontalProgressDrawable(context), new SingleHorizontalProgressDrawable(context)});
        setId(0, 16908288);
        this.mTrackDrawable = (SingleHorizontalProgressDrawable) getDrawable(0);
        setId(1, 16908303);
        this.mSecondaryProgressDrawable = (SingleHorizontalProgressDrawable) getDrawable(1);
        this.mSecondaryAlpha = Math.round(ThemeUtils.getFloatFromAttrRes(16842803, context) * 255.0f);
        this.mSecondaryProgressDrawable.setAlpha(this.mSecondaryAlpha);
        this.mSecondaryProgressDrawable.setShowTrack(false);
        setId(2, 16908301);
        this.mProgressDrawable = (SingleHorizontalProgressDrawable) getDrawable(2);
        this.mProgressDrawable.setShowTrack(false);
    }

    @Override // me.zhanghai.android.materialprogressbar.ShowTrackDrawable
    public boolean getShowTrack() {
        return this.mTrackDrawable.getShowTrack();
    }

    @Override // me.zhanghai.android.materialprogressbar.ShowTrackDrawable
    public void setShowTrack(boolean z) {
        if (this.mTrackDrawable.getShowTrack() != z) {
            this.mTrackDrawable.setShowTrack(z);
            this.mSecondaryProgressDrawable.setAlpha(z ? this.mSecondaryAlpha : this.mSecondaryAlpha * 2);
        }
    }

    @Override // me.zhanghai.android.materialprogressbar.IntrinsicPaddingDrawable
    public boolean getUseIntrinsicPadding() {
        return this.mTrackDrawable.getUseIntrinsicPadding();
    }

    @Override // me.zhanghai.android.materialprogressbar.IntrinsicPaddingDrawable
    public void setUseIntrinsicPadding(boolean z) {
        this.mTrackDrawable.setUseIntrinsicPadding(z);
        this.mSecondaryProgressDrawable.setUseIntrinsicPadding(z);
        this.mProgressDrawable.setUseIntrinsicPadding(z);
    }

    @Override // android.graphics.drawable.Drawable, me.zhanghai.android.materialprogressbar.TintableDrawable
    @SuppressLint({"NewApi"})
    public void setTint(@ColorInt int i) {
        this.mTrackDrawable.setTint(i);
        this.mSecondaryProgressDrawable.setTint(i);
        this.mProgressDrawable.setTint(i);
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable, me.zhanghai.android.materialprogressbar.TintableDrawable
    @SuppressLint({"NewApi"})
    public void setTintList(@Nullable ColorStateList colorStateList) {
        this.mTrackDrawable.setTintList(colorStateList);
        this.mSecondaryProgressDrawable.setTintList(colorStateList);
        this.mProgressDrawable.setTintList(colorStateList);
    }

    @Override // android.graphics.drawable.Drawable, me.zhanghai.android.materialprogressbar.TintableDrawable
    @SuppressLint({"NewApi"})
    public void setTintMode(@NonNull PorterDuff.Mode mode) {
        this.mTrackDrawable.setTintMode(mode);
        this.mSecondaryProgressDrawable.setTintMode(mode);
        this.mProgressDrawable.setTintMode(mode);
    }
}