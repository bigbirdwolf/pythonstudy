package com.yltx.oil.partner.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class SelectableRoundedImageView extends AppCompatImageView {
    private static final int DEFAULT_BORDER_COLOR = -16777216;
    private static final String TAG = "RoundedImageView";
    private static final ImageView.ScaleType[] sScaleTypeArray = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    private boolean isOval;
    private ColorStateList mBorderColor;
    private float mBorderWidth;
    private Drawable mDrawable;
    private float mLeftBottomCornerRadius;
    private float mLeftTopCornerRadius;
    private float[] mRadii;
    private int mResource;
    private float mRightBottomCornerRadius;
    private float mRightTopCornerRadius;
    private ImageView.ScaleType mScaleType;

    public SelectableRoundedImageView(Context context) {
        super(context);
        this.mResource = 0;
        this.mScaleType = ImageView.ScaleType.FIT_CENTER;
        this.mLeftTopCornerRadius = 0.0f;
        this.mRightTopCornerRadius = 0.0f;
        this.mLeftBottomCornerRadius = 0.0f;
        this.mRightBottomCornerRadius = 0.0f;
        this.mBorderWidth = 0.0f;
        this.mBorderColor = ColorStateList.valueOf(-16777216);
        this.isOval = false;
        this.mRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    }

    public SelectableRoundedImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SelectableRoundedImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mResource = 0;
        this.mScaleType = ImageView.ScaleType.FIT_CENTER;
        this.mLeftTopCornerRadius = 0.0f;
        this.mRightTopCornerRadius = 0.0f;
        this.mLeftBottomCornerRadius = 0.0f;
        this.mRightBottomCornerRadius = 0.0f;
        this.mBorderWidth = 0.0f;
        this.mBorderColor = ColorStateList.valueOf(-16777216);
        this.isOval = false;
        this.mRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SelectableRoundedImageView, i, 0);
        int i2 = obtainStyledAttributes.getInt(0, -1);
        if (i2 >= 0) {
            setScaleType(sScaleTypeArray[i2]);
        }
        this.mLeftTopCornerRadius = obtainStyledAttributes.getDimensionPixelSize(4, 0);
        this.mRightTopCornerRadius = obtainStyledAttributes.getDimensionPixelSize(7, 0);
        this.mLeftBottomCornerRadius = obtainStyledAttributes.getDimensionPixelSize(3, 0);
        this.mRightBottomCornerRadius = obtainStyledAttributes.getDimensionPixelSize(6, 0);
        if (this.mLeftTopCornerRadius < 0.0f || this.mRightTopCornerRadius < 0.0f || this.mLeftBottomCornerRadius < 0.0f || this.mRightBottomCornerRadius < 0.0f) {
            throw new IllegalArgumentException("radius values cannot be negative.");
        }
        this.mRadii = new float[]{this.mLeftTopCornerRadius, this.mLeftTopCornerRadius, this.mRightTopCornerRadius, this.mRightTopCornerRadius, this.mRightBottomCornerRadius, this.mRightBottomCornerRadius, this.mLeftBottomCornerRadius, this.mLeftBottomCornerRadius};
        this.mBorderWidth = obtainStyledAttributes.getDimensionPixelSize(2, 0);
        if (this.mBorderWidth < 0.0f) {
            throw new IllegalArgumentException("border width cannot be negative.");
        }
        this.mBorderColor = obtainStyledAttributes.getColorStateList(1);
        if (this.mBorderColor == null) {
            this.mBorderColor = ColorStateList.valueOf(-16777216);
        }
        this.isOval = obtainStyledAttributes.getBoolean(5, false);
        obtainStyledAttributes.recycle();
        updateDrawable();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.widget.AppCompatImageView, android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        super.setScaleType(scaleType);
        this.mScaleType = scaleType;
        updateDrawable();
    }

    @Override // android.support.v7.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        this.mResource = 0;
        this.mDrawable = SelectableRoundedCornerDrawable.fromDrawable(drawable, getResources());
        super.setImageDrawable(this.mDrawable);
        updateDrawable();
    }

    @Override // android.support.v7.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        this.mResource = 0;
        this.mDrawable = SelectableRoundedCornerDrawable.fromBitmap(bitmap, getResources());
        super.setImageDrawable(this.mDrawable);
        updateDrawable();
    }

    @Override // android.support.v7.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        if (this.mResource != i) {
            this.mResource = i;
            this.mDrawable = resolveResource();
            super.setImageDrawable(this.mDrawable);
            updateDrawable();
        }
    }

    @Override // android.support.v7.widget.AppCompatImageView, android.widget.ImageView
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
            } catch (Resources.NotFoundException unused) {
                this.mResource = 0;
            }
            return SelectableRoundedCornerDrawable.fromDrawable(drawable, getResources());
        }
        drawable = null;
        return SelectableRoundedCornerDrawable.fromDrawable(drawable, getResources());
    }

    private void updateDrawable() {
        if (this.mDrawable == null) {
            return;
        }
        ((SelectableRoundedCornerDrawable) this.mDrawable).setScaleType(this.mScaleType);
        ((SelectableRoundedCornerDrawable) this.mDrawable).setCornerRadii(this.mRadii);
        ((SelectableRoundedCornerDrawable) this.mDrawable).setBorderWidth(this.mBorderWidth);
        ((SelectableRoundedCornerDrawable) this.mDrawable).setBorderColor(this.mBorderColor);
        ((SelectableRoundedCornerDrawable) this.mDrawable).setOval(this.isOval);
    }

    public float getCornerRadius() {
        return this.mLeftTopCornerRadius;
    }

    public void setCornerRadiiDP(float f, float f2, float f3, float f4) {
        float f5 = getResources().getDisplayMetrics().density;
        float f6 = f * f5;
        float f7 = f2 * f5;
        float f8 = f3 * f5;
        float f9 = f4 * f5;
        this.mRadii = new float[]{f6, f6, f7, f7, f9, f9, f8, f8};
        updateDrawable();
    }

    public float getBorderWidth() {
        return this.mBorderWidth;
    }

    public void setBorderWidthDP(float f) {
        float f2 = getResources().getDisplayMetrics().density * f;
        if (this.mBorderWidth == f2) {
            return;
        }
        this.mBorderWidth = f2;
        updateDrawable();
        invalidate();
    }

    public int getBorderColor() {
        return this.mBorderColor.getDefaultColor();
    }

    public void setBorderColor(int i) {
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
        updateDrawable();
        if (this.mBorderWidth > 0.0f) {
            invalidate();
        }
    }

    public boolean isOval() {
        return this.isOval;
    }

    public void setOval(boolean z) {
        this.isOval = z;
        updateDrawable();
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class SelectableRoundedCornerDrawable extends Drawable {
        private static final int DEFAULT_BORDER_COLOR = -16777216;
        private static final String TAG = "SelectableRoundedCornerDrawable";
        private Bitmap mBitmap;
        private final int mBitmapHeight;
        private final Paint mBitmapPaint;
        private BitmapShader mBitmapShader;
        private final int mBitmapWidth;
        private final Paint mBorderPaint;
        private RectF mBounds = new RectF();
        private RectF mBorderBounds = new RectF();
        private final RectF mBitmapRect = new RectF();
        private float[] mRadii = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        private float[] mBorderRadii = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        private boolean mOval = false;
        private float mBorderWidth = 0.0f;
        private ColorStateList mBorderColor = ColorStateList.valueOf(-16777216);
        private ImageView.ScaleType mScaleType = ImageView.ScaleType.FIT_CENTER;
        private Path mPath = new Path();
        private boolean mBoundsConfigured = false;

        public SelectableRoundedCornerDrawable(Bitmap bitmap, Resources resources) {
            this.mBitmap = bitmap;
            this.mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            if (bitmap != null) {
                this.mBitmapWidth = bitmap.getScaledWidth(resources.getDisplayMetrics());
                this.mBitmapHeight = bitmap.getScaledHeight(resources.getDisplayMetrics());
            } else {
                this.mBitmapHeight = -1;
                this.mBitmapWidth = -1;
            }
            this.mBitmapRect.set(0.0f, 0.0f, this.mBitmapWidth, this.mBitmapHeight);
            this.mBitmapPaint = new Paint(1);
            this.mBitmapPaint.setStyle(Paint.Style.FILL);
            this.mBitmapPaint.setShader(this.mBitmapShader);
            this.mBorderPaint = new Paint(1);
            this.mBorderPaint.setStyle(Paint.Style.STROKE);
            this.mBorderPaint.setColor(this.mBorderColor.getColorForState(getState(), -16777216));
            this.mBorderPaint.setStrokeWidth(this.mBorderWidth);
        }

        public static SelectableRoundedCornerDrawable fromBitmap(Bitmap bitmap, Resources resources) {
            if (bitmap != null) {
                return new SelectableRoundedCornerDrawable(bitmap, resources);
            }
            return null;
        }

        public static Drawable fromDrawable(Drawable drawable, Resources resources) {
            if (drawable == null || (drawable instanceof SelectableRoundedCornerDrawable)) {
                return drawable;
            }
            if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                for (int i = 0; i < numberOfLayers; i++) {
                    layerDrawable.setDrawableByLayerId(layerDrawable.getId(i), fromDrawable(layerDrawable.getDrawable(i), resources));
                }
                return layerDrawable;
            }
            Bitmap drawableToBitmap = drawableToBitmap(drawable);
            if (drawableToBitmap != null) {
                return new SelectableRoundedCornerDrawable(drawableToBitmap, resources);
            }
            return drawable;
        }

        public static Bitmap drawableToBitmap(Drawable drawable) {
            if (drawable == null) {
                return null;
            }
            if (drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            }
            try {
                Bitmap createBitmap = Bitmap.createBitmap(Math.max(drawable.getIntrinsicWidth(), 2), Math.max(drawable.getIntrinsicHeight(), 2), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                drawable.draw(canvas);
                return createBitmap;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override // android.graphics.drawable.Drawable
        public boolean isStateful() {
            return this.mBorderColor.isStateful();
        }

        @Override // android.graphics.drawable.Drawable
        protected boolean onStateChange(int[] iArr) {
            int colorForState = this.mBorderColor.getColorForState(iArr, 0);
            if (this.mBorderPaint.getColor() != colorForState) {
                this.mBorderPaint.setColor(colorForState);
                return true;
            }
            return super.onStateChange(iArr);
        }

        private void configureBounds(Canvas canvas) {
            Rect clipBounds = canvas.getClipBounds();
            Matrix matrix = canvas.getMatrix();
            if (ImageView.ScaleType.CENTER == this.mScaleType) {
                this.mBounds.set(clipBounds);
            } else if (ImageView.ScaleType.CENTER_CROP == this.mScaleType) {
                applyScaleToRadii(matrix);
                this.mBounds.set(clipBounds);
            } else if (ImageView.ScaleType.FIT_XY == this.mScaleType) {
                Matrix matrix2 = new Matrix();
                matrix2.setRectToRect(this.mBitmapRect, new RectF(clipBounds), Matrix.ScaleToFit.FILL);
                this.mBitmapShader.setLocalMatrix(matrix2);
                this.mBounds.set(clipBounds);
            } else if (ImageView.ScaleType.FIT_START == this.mScaleType || ImageView.ScaleType.FIT_END == this.mScaleType || ImageView.ScaleType.FIT_CENTER == this.mScaleType || ImageView.ScaleType.CENTER_INSIDE == this.mScaleType) {
                applyScaleToRadii(matrix);
                this.mBounds.set(this.mBitmapRect);
            } else if (ImageView.ScaleType.MATRIX == this.mScaleType) {
                applyScaleToRadii(matrix);
                this.mBounds.set(this.mBitmapRect);
            }
        }

        private void applyScaleToRadii(Matrix matrix) {
            float[] fArr = new float[9];
            matrix.getValues(fArr);
            for (int i = 0; i < this.mRadii.length; i++) {
                this.mRadii[i] = this.mRadii[i] / fArr[0];
            }
        }

        private void adjustCanvasForBorder(Canvas canvas) {
            float[] fArr = new float[9];
            canvas.getMatrix().getValues(fArr);
            float f = fArr[0];
            float f2 = fArr[4];
            float f3 = fArr[2];
            float f4 = fArr[5];
            float width = this.mBounds.width() / ((this.mBounds.width() + this.mBorderWidth) + this.mBorderWidth);
            float height = this.mBounds.height() / ((this.mBounds.height() + this.mBorderWidth) + this.mBorderWidth);
            canvas.scale(width, height);
            if (ImageView.ScaleType.FIT_START == this.mScaleType || ImageView.ScaleType.FIT_END == this.mScaleType || ImageView.ScaleType.FIT_XY == this.mScaleType || ImageView.ScaleType.FIT_CENTER == this.mScaleType || ImageView.ScaleType.CENTER_INSIDE == this.mScaleType || ImageView.ScaleType.MATRIX == this.mScaleType) {
                canvas.translate(this.mBorderWidth, this.mBorderWidth);
            } else if (ImageView.ScaleType.CENTER == this.mScaleType || ImageView.ScaleType.CENTER_CROP == this.mScaleType) {
                canvas.translate((-f3) / (width * f), (-f4) / (height * f2));
                canvas.translate(-(this.mBounds.left - this.mBorderWidth), -(this.mBounds.top - this.mBorderWidth));
            }
        }

        private void adjustBorderWidthAndBorderBounds(Canvas canvas) {
            float[] fArr = new float[9];
            canvas.getMatrix().getValues(fArr);
            this.mBorderWidth = (this.mBorderWidth * this.mBounds.width()) / ((this.mBounds.width() * fArr[0]) - (this.mBorderWidth * 2.0f));
            this.mBorderPaint.setStrokeWidth(this.mBorderWidth);
            this.mBorderBounds.set(this.mBounds);
            this.mBorderBounds.inset((-this.mBorderWidth) / 2.0f, (-this.mBorderWidth) / 2.0f);
        }

        private void setBorderRadii() {
            for (int i = 0; i < this.mRadii.length; i++) {
                if (this.mRadii[i] > 0.0f) {
                    this.mBorderRadii[i] = this.mRadii[i];
                    this.mRadii[i] = this.mRadii[i] - this.mBorderWidth;
                }
            }
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            canvas.save();
            if (!this.mBoundsConfigured) {
                configureBounds(canvas);
                if (this.mBorderWidth > 0.0f) {
                    adjustBorderWidthAndBorderBounds(canvas);
                    setBorderRadii();
                }
                this.mBoundsConfigured = true;
            }
            if (this.mOval) {
                if (this.mBorderWidth > 0.0f) {
                    adjustCanvasForBorder(canvas);
                    this.mPath.addOval(this.mBounds, Path.Direction.CW);
                    canvas.drawPath(this.mPath, this.mBitmapPaint);
                    this.mPath.reset();
                    this.mPath.addOval(this.mBorderBounds, Path.Direction.CW);
                    canvas.drawPath(this.mPath, this.mBorderPaint);
                } else {
                    this.mPath.addOval(this.mBounds, Path.Direction.CW);
                    canvas.drawPath(this.mPath, this.mBitmapPaint);
                }
            } else if (this.mBorderWidth > 0.0f) {
                adjustCanvasForBorder(canvas);
                this.mPath.addRoundRect(this.mBounds, this.mRadii, Path.Direction.CW);
                canvas.drawPath(this.mPath, this.mBitmapPaint);
                this.mPath.reset();
                this.mPath.addRoundRect(this.mBorderBounds, this.mBorderRadii, Path.Direction.CW);
                canvas.drawPath(this.mPath, this.mBorderPaint);
            } else {
                this.mPath.addRoundRect(this.mBounds, this.mRadii, Path.Direction.CW);
                canvas.drawPath(this.mPath, this.mBitmapPaint);
            }
            canvas.restore();
        }

        public void setCornerRadii(float[] fArr) {
            if (fArr == null) {
                return;
            }
            if (fArr.length != 8) {
                throw new ArrayIndexOutOfBoundsException("radii[] needs 8 values");
            }
            for (int i = 0; i < fArr.length; i++) {
                this.mRadii[i] = fArr[i];
            }
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return (this.mBitmap == null || this.mBitmap.hasAlpha() || this.mBitmapPaint.getAlpha() < 255) ? -3 : -1;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            this.mBitmapPaint.setAlpha(i);
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            this.mBitmapPaint.setColorFilter(colorFilter);
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public void setDither(boolean z) {
            this.mBitmapPaint.setDither(z);
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public void setFilterBitmap(boolean z) {
            this.mBitmapPaint.setFilterBitmap(z);
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return this.mBitmapWidth;
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return this.mBitmapHeight;
        }

        public float getBorderWidth() {
            return this.mBorderWidth;
        }

        public void setBorderWidth(float f) {
            this.mBorderWidth = f;
            this.mBorderPaint.setStrokeWidth(f);
        }

        public int getBorderColor() {
            return this.mBorderColor.getDefaultColor();
        }

        public void setBorderColor(int i) {
            setBorderColor(ColorStateList.valueOf(i));
        }

        public ColorStateList getBorderColors() {
            return this.mBorderColor;
        }

        public void setBorderColor(ColorStateList colorStateList) {
            if (colorStateList == null) {
                this.mBorderWidth = 0.0f;
                this.mBorderColor = ColorStateList.valueOf(0);
                this.mBorderPaint.setColor(0);
                return;
            }
            this.mBorderColor = colorStateList;
            this.mBorderPaint.setColor(this.mBorderColor.getColorForState(getState(), -16777216));
        }

        public boolean isOval() {
            return this.mOval;
        }

        public void setOval(boolean z) {
            this.mOval = z;
        }

        public ImageView.ScaleType getScaleType() {
            return this.mScaleType;
        }

        public void setScaleType(ImageView.ScaleType scaleType) {
            if (scaleType == null) {
                return;
            }
            this.mScaleType = scaleType;
        }
    }
}