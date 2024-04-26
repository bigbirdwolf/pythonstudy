package com.makeramen.roundedimageview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/* loaded from: classes.dex */
public class RoundedImageView extends ImageView {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final float DEFAULT_BORDER_WIDTH = 0.0f;
    public static final float DEFAULT_RADIUS = 0.0f;
    public static final Shader.TileMode DEFAULT_TILE_MODE = Shader.TileMode.CLAMP;
    private static final ImageView.ScaleType[] SCALE_TYPES = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    public static final String TAG = "RoundedImageView";
    private static final int TILE_MODE_CLAMP = 0;
    private static final int TILE_MODE_MIRROR = 2;
    private static final int TILE_MODE_REPEAT = 1;
    private static final int TILE_MODE_UNDEFINED = -2;
    private Drawable mBackgroundDrawable;
    private int mBackgroundResource;
    private ColorStateList mBorderColor;
    private float mBorderWidth;
    private ColorFilter mColorFilter;
    private boolean mColorMod;
    private final float[] mCornerRadii;
    private Drawable mDrawable;
    private boolean mHasColorFilter;
    private boolean mIsOval;
    private boolean mMutateBackground;
    private int mResource;
    private ImageView.ScaleType mScaleType;
    private Shader.TileMode mTileModeX;
    private Shader.TileMode mTileModeY;

    public RoundedImageView(Context context) {
        super(context);
        this.mCornerRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
        this.mBorderColor = ColorStateList.valueOf(-16777216);
        this.mBorderWidth = 0.0f;
        this.mColorFilter = null;
        this.mColorMod = false;
        this.mHasColorFilter = false;
        this.mIsOval = false;
        this.mMutateBackground = false;
        this.mTileModeX = DEFAULT_TILE_MODE;
        this.mTileModeY = DEFAULT_TILE_MODE;
    }

    public RoundedImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundedImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCornerRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
        this.mBorderColor = ColorStateList.valueOf(-16777216);
        this.mBorderWidth = 0.0f;
        this.mColorFilter = null;
        this.mColorMod = false;
        this.mHasColorFilter = false;
        this.mIsOval = false;
        this.mMutateBackground = false;
        this.mTileModeX = DEFAULT_TILE_MODE;
        this.mTileModeY = DEFAULT_TILE_MODE;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundedImageView, i, 0);
        int i2 = obtainStyledAttributes.getInt(R.styleable.RoundedImageView_android_scaleType, -1);
        if (i2 >= 0) {
            setScaleType(SCALE_TYPES[i2]);
        } else {
            setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        float dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius, -1);
        this.mCornerRadii[0] = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius_top_left, -1);
        this.mCornerRadii[1] = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius_top_right, -1);
        this.mCornerRadii[2] = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius_bottom_right, -1);
        this.mCornerRadii[3] = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius_bottom_left, -1);
        int length = this.mCornerRadii.length;
        boolean z = false;
        for (int i3 = 0; i3 < length; i3++) {
            if (this.mCornerRadii[i3] < 0.0f) {
                this.mCornerRadii[i3] = 0.0f;
            } else {
                z = true;
            }
        }
        if (!z) {
            dimensionPixelSize = dimensionPixelSize < 0.0f ? 0.0f : dimensionPixelSize;
            int length2 = this.mCornerRadii.length;
            for (int i4 = 0; i4 < length2; i4++) {
                this.mCornerRadii[i4] = dimensionPixelSize;
            }
        }
        this.mBorderWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_riv_border_width, -1);
        if (this.mBorderWidth < 0.0f) {
            this.mBorderWidth = 0.0f;
        }
        this.mBorderColor = obtainStyledAttributes.getColorStateList(R.styleable.RoundedImageView_riv_border_color);
        if (this.mBorderColor == null) {
            this.mBorderColor = ColorStateList.valueOf(-16777216);
        }
        this.mMutateBackground = obtainStyledAttributes.getBoolean(R.styleable.RoundedImageView_riv_mutate_background, false);
        this.mIsOval = obtainStyledAttributes.getBoolean(R.styleable.RoundedImageView_riv_oval, false);
        int i5 = obtainStyledAttributes.getInt(R.styleable.RoundedImageView_riv_tile_mode, -2);
        if (i5 != -2) {
            setTileModeX(parseTileMode(i5));
            setTileModeY(parseTileMode(i5));
        }
        int i6 = obtainStyledAttributes.getInt(R.styleable.RoundedImageView_riv_tile_mode_x, -2);
        if (i6 != -2) {
            setTileModeX(parseTileMode(i6));
        }
        int i7 = obtainStyledAttributes.getInt(R.styleable.RoundedImageView_riv_tile_mode_y, -2);
        if (i7 != -2) {
            setTileModeY(parseTileMode(i7));
        }
        updateDrawableAttrs();
        updateBackgroundDrawableAttrs(true);
        if (this.mMutateBackground) {
            super.setBackgroundDrawable(this.mBackgroundDrawable);
        }
        obtainStyledAttributes.recycle();
    }

    private static Shader.TileMode parseTileMode(int i) {
        switch (i) {
            case 0:
                return Shader.TileMode.CLAMP;
            case 1:
                return Shader.TileMode.REPEAT;
            case 2:
                return Shader.TileMode.MIRROR;
            default:
                return null;
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (this.mScaleType != scaleType) {
            this.mScaleType = scaleType;
            switch (AnonymousClass1.$SwitchMap$android$widget$ImageView$ScaleType[scaleType.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    super.setScaleType(ImageView.ScaleType.FIT_XY);
                    break;
                default:
                    super.setScaleType(scaleType);
                    break;
            }
            updateDrawableAttrs();
            updateBackgroundDrawableAttrs(false);
            invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.makeramen.roundedimageview.RoundedImageView$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType = new int[ImageView.ScaleType.values().length];

        static {
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_START.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_END.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_XY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        this.mResource = 0;
        this.mDrawable = RoundedDrawable.fromDrawable(drawable);
        updateDrawableAttrs();
        super.setImageDrawable(this.mDrawable);
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        this.mResource = 0;
        this.mDrawable = RoundedDrawable.fromBitmap(bitmap);
        updateDrawableAttrs();
        super.setImageDrawable(this.mDrawable);
    }

    @Override // android.widget.ImageView
    public void setImageResource(@DrawableRes int i) {
        if (this.mResource != i) {
            this.mResource = i;
            this.mDrawable = resolveResource();
            updateDrawableAttrs();
            super.setImageDrawable(this.mDrawable);
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        setImageDrawable(getDrawable());
    }

    private Drawable resolveResource() {
        Drawable drawable;
        Resources resources = getResources();
        if (resources == null) {
            return null;
        }
        if (this.mResource != 0) {
            try {
                drawable = resources.getDrawable(this.mResource);
            } catch (Exception e) {
                Log.w(TAG, "Unable to find resource: " + this.mResource, e);
                this.mResource = 0;
            }
            return RoundedDrawable.fromDrawable(drawable);
        }
        drawable = null;
        return RoundedDrawable.fromDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundResource(@DrawableRes int i) {
        if (this.mBackgroundResource != i) {
            this.mBackgroundResource = i;
            this.mBackgroundDrawable = resolveBackgroundResource();
            setBackgroundDrawable(this.mBackgroundDrawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.mBackgroundDrawable = new ColorDrawable(i);
        setBackgroundDrawable(this.mBackgroundDrawable);
    }

    private Drawable resolveBackgroundResource() {
        Drawable drawable;
        Resources resources = getResources();
        if (resources == null) {
            return null;
        }
        if (this.mBackgroundResource != 0) {
            try {
                drawable = resources.getDrawable(this.mBackgroundResource);
            } catch (Exception e) {
                Log.w(TAG, "Unable to find resource: " + this.mBackgroundResource, e);
                this.mBackgroundResource = 0;
            }
            return RoundedDrawable.fromDrawable(drawable);
        }
        drawable = null;
        return RoundedDrawable.fromDrawable(drawable);
    }

    private void updateDrawableAttrs() {
        updateAttrs(this.mDrawable, this.mScaleType);
    }

    private void updateBackgroundDrawableAttrs(boolean z) {
        if (this.mMutateBackground) {
            if (z) {
                this.mBackgroundDrawable = RoundedDrawable.fromDrawable(this.mBackgroundDrawable);
            }
            updateAttrs(this.mBackgroundDrawable, ImageView.ScaleType.FIT_XY);
        }
    }

    @Override // android.widget.ImageView
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.mColorFilter != colorFilter) {
            this.mColorFilter = colorFilter;
            this.mHasColorFilter = true;
            this.mColorMod = true;
            applyColorMod();
            invalidate();
        }
    }

    private void applyColorMod() {
        if (this.mDrawable == null || !this.mColorMod) {
            return;
        }
        this.mDrawable = this.mDrawable.mutate();
        if (this.mHasColorFilter) {
            this.mDrawable.setColorFilter(this.mColorFilter);
        }
    }

    private void updateAttrs(Drawable drawable, ImageView.ScaleType scaleType) {
        if (drawable == null) {
            return;
        }
        if (drawable instanceof RoundedDrawable) {
            RoundedDrawable roundedDrawable = (RoundedDrawable) drawable;
            roundedDrawable.setScaleType(scaleType).setBorderWidth(this.mBorderWidth).setBorderColor(this.mBorderColor).setOval(this.mIsOval).setTileModeX(this.mTileModeX).setTileModeY(this.mTileModeY);
            if (this.mCornerRadii != null) {
                roundedDrawable.setCornerRadius(this.mCornerRadii[0], this.mCornerRadii[1], this.mCornerRadii[2], this.mCornerRadii[3]);
            }
            applyColorMod();
        } else if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            for (int i = 0; i < numberOfLayers; i++) {
                updateAttrs(layerDrawable.getDrawable(i), scaleType);
            }
        }
    }

    @Override // android.view.View
    @Deprecated
    public void setBackgroundDrawable(Drawable drawable) {
        this.mBackgroundDrawable = drawable;
        updateBackgroundDrawableAttrs(true);
        super.setBackgroundDrawable(this.mBackgroundDrawable);
    }

    public float getCornerRadius() {
        return getMaxCornerRadius();
    }

    public float getMaxCornerRadius() {
        float f = 0.0f;
        for (float f2 : this.mCornerRadii) {
            f = Math.max(f2, f);
        }
        return f;
    }

    public float getCornerRadius(int i) {
        return this.mCornerRadii[i];
    }

    public void setCornerRadiusDimen(@DimenRes int i) {
        float dimension = getResources().getDimension(i);
        setCornerRadius(dimension, dimension, dimension, dimension);
    }

    public void setCornerRadiusDimen(int i, @DimenRes int i2) {
        setCornerRadius(i, getResources().getDimensionPixelSize(i2));
    }

    public void setCornerRadius(float f) {
        setCornerRadius(f, f, f, f);
    }

    public void setCornerRadius(int i, float f) {
        if (this.mCornerRadii[i] == f) {
            return;
        }
        this.mCornerRadii[i] = f;
        updateDrawableAttrs();
        updateBackgroundDrawableAttrs(false);
        invalidate();
    }

    public void setCornerRadius(float f, float f2, float f3, float f4) {
        if (this.mCornerRadii[0] == f && this.mCornerRadii[1] == f2 && this.mCornerRadii[2] == f4 && this.mCornerRadii[3] == f3) {
            return;
        }
        this.mCornerRadii[0] = f;
        this.mCornerRadii[1] = f2;
        this.mCornerRadii[3] = f3;
        this.mCornerRadii[2] = f4;
        updateDrawableAttrs();
        updateBackgroundDrawableAttrs(false);
        invalidate();
    }

    public float getBorderWidth() {
        return this.mBorderWidth;
    }

    public void setBorderWidth(@DimenRes int i) {
        setBorderWidth(getResources().getDimension(i));
    }

    public void setBorderWidth(float f) {
        if (this.mBorderWidth == f) {
            return;
        }
        this.mBorderWidth = f;
        updateDrawableAttrs();
        updateBackgroundDrawableAttrs(false);
        invalidate();
    }

    @ColorInt
    public int getBorderColor() {
        return this.mBorderColor.getDefaultColor();
    }

    public void setBorderColor(@ColorInt int i) {
        setBorderColor(ColorStateList.valueOf(i));
    }

    public ColorStateList getBorderColors() {
        return this.mBorderColor;
    }

    public void setBorderColor(ColorStateList colorStateList) {
        if (this.mBorderColor.equals(colorStateList)) {
            return;
        }
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(-16777216);
        }
        this.mBorderColor = colorStateList;
        updateDrawableAttrs();
        updateBackgroundDrawableAttrs(false);
        if (this.mBorderWidth > 0.0f) {
            invalidate();
        }
    }

    public boolean isOval() {
        return this.mIsOval;
    }

    public void setOval(boolean z) {
        this.mIsOval = z;
        updateDrawableAttrs();
        updateBackgroundDrawableAttrs(false);
        invalidate();
    }

    public Shader.TileMode getTileModeX() {
        return this.mTileModeX;
    }

    public void setTileModeX(Shader.TileMode tileMode) {
        if (this.mTileModeX == tileMode) {
            return;
        }
        this.mTileModeX = tileMode;
        updateDrawableAttrs();
        updateBackgroundDrawableAttrs(false);
        invalidate();
    }

    public Shader.TileMode getTileModeY() {
        return this.mTileModeY;
    }

    public void setTileModeY(Shader.TileMode tileMode) {
        if (this.mTileModeY == tileMode) {
            return;
        }
        this.mTileModeY = tileMode;
        updateDrawableAttrs();
        updateBackgroundDrawableAttrs(false);
        invalidate();
    }

    public boolean mutatesBackground() {
        return this.mMutateBackground;
    }

    public void mutateBackground(boolean z) {
        if (this.mMutateBackground == z) {
            return;
        }
        this.mMutateBackground = z;
        updateBackgroundDrawableAttrs(true);
        invalidate();
    }
}