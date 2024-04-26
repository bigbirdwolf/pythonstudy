package com.makeramen.roundedimageview;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ImageView;
import com.squareup.picasso.Transformation;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class RoundedTransformationBuilder {
    private float[] mCornerRadii = {0.0f, 0.0f, 0.0f, 0.0f};
    private boolean mOval = false;
    private float mBorderWidth = 0.0f;
    private ColorStateList mBorderColor = ColorStateList.valueOf(-16777216);
    private ImageView.ScaleType mScaleType = ImageView.ScaleType.FIT_CENTER;
    private final DisplayMetrics mDisplayMetrics = Resources.getSystem().getDisplayMetrics();

    public RoundedTransformationBuilder scaleType(ImageView.ScaleType scaleType) {
        this.mScaleType = scaleType;
        return this;
    }

    public RoundedTransformationBuilder cornerRadius(float f) {
        this.mCornerRadii[0] = f;
        this.mCornerRadii[1] = f;
        this.mCornerRadii[2] = f;
        this.mCornerRadii[3] = f;
        return this;
    }

    public RoundedTransformationBuilder cornerRadius(int i, float f) {
        this.mCornerRadii[i] = f;
        return this;
    }

    public RoundedTransformationBuilder cornerRadiusDp(float f) {
        return cornerRadius(TypedValue.applyDimension(1, f, this.mDisplayMetrics));
    }

    public RoundedTransformationBuilder cornerRadiusDp(int i, float f) {
        return cornerRadius(i, TypedValue.applyDimension(1, f, this.mDisplayMetrics));
    }

    public RoundedTransformationBuilder borderWidth(float f) {
        this.mBorderWidth = f;
        return this;
    }

    public RoundedTransformationBuilder borderWidthDp(float f) {
        this.mBorderWidth = TypedValue.applyDimension(1, f, this.mDisplayMetrics);
        return this;
    }

    public RoundedTransformationBuilder borderColor(int i) {
        this.mBorderColor = ColorStateList.valueOf(i);
        return this;
    }

    public RoundedTransformationBuilder borderColor(ColorStateList colorStateList) {
        this.mBorderColor = colorStateList;
        return this;
    }

    public RoundedTransformationBuilder oval(boolean z) {
        this.mOval = z;
        return this;
    }

    public Transformation build() {
        return new Transformation() { // from class: com.makeramen.roundedimageview.RoundedTransformationBuilder.1
            public Bitmap transform(Bitmap bitmap) {
                Bitmap bitmap2 = RoundedDrawable.fromBitmap(bitmap).setScaleType(RoundedTransformationBuilder.this.mScaleType).setCornerRadius(RoundedTransformationBuilder.this.mCornerRadii[0], RoundedTransformationBuilder.this.mCornerRadii[1], RoundedTransformationBuilder.this.mCornerRadii[2], RoundedTransformationBuilder.this.mCornerRadii[3]).setBorderWidth(RoundedTransformationBuilder.this.mBorderWidth).setBorderColor(RoundedTransformationBuilder.this.mBorderColor).setOval(RoundedTransformationBuilder.this.mOval).toBitmap();
                if (!bitmap.equals(bitmap2)) {
                    bitmap.recycle();
                }
                return bitmap2;
            }

            public String key() {
                return "r:" + Arrays.toString(RoundedTransformationBuilder.this.mCornerRadii) + "b:" + RoundedTransformationBuilder.this.mBorderWidth + "c:" + RoundedTransformationBuilder.this.mBorderColor + "o:" + RoundedTransformationBuilder.this.mOval;
            }
        };
    }
}