package jp.wasabeef.glide.transformations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;

/* loaded from: classes.dex */
public class RoundedCornersTransformation implements Transformation<Bitmap> {
    private BitmapPool mBitmapPool;
    private CornerType mCornerType;
    private int mDiameter;
    private int mMargin;
    private int mRadius;

    /* loaded from: classes.dex */
    public enum CornerType {
        ALL,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        OTHER_TOP_LEFT,
        OTHER_TOP_RIGHT,
        OTHER_BOTTOM_LEFT,
        OTHER_BOTTOM_RIGHT,
        DIAGONAL_FROM_TOP_LEFT,
        DIAGONAL_FROM_TOP_RIGHT
    }

    public RoundedCornersTransformation(Context context, int i, int i2) {
        this(context, i, i2, CornerType.ALL);
    }

    public RoundedCornersTransformation(BitmapPool bitmapPool, int i, int i2) {
        this(bitmapPool, i, i2, CornerType.ALL);
    }

    public RoundedCornersTransformation(Context context, int i, int i2, CornerType cornerType) {
        this(Glide.get(context).getBitmapPool(), i, i2, cornerType);
    }

    public RoundedCornersTransformation(BitmapPool bitmapPool, int i, int i2, CornerType cornerType) {
        this.mBitmapPool = bitmapPool;
        this.mRadius = i;
        this.mDiameter = this.mRadius * 2;
        this.mMargin = i2;
        this.mCornerType = cornerType;
    }

    @Override // com.bumptech.glide.load.Transformation
    public Resource<Bitmap> transform(Resource<Bitmap> resource, int i, int i2) {
        Bitmap bitmap = resource.get();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap bitmap2 = this.mBitmapPool.get(width, height, Bitmap.Config.ARGB_8888);
        if (bitmap2 == null) {
            bitmap2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap2);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        drawRoundRect(canvas, paint, width, height);
        return BitmapResource.obtain(bitmap2, this.mBitmapPool);
    }

    private void drawRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        float f3 = f - this.mMargin;
        float f4 = f2 - this.mMargin;
        switch (this.mCornerType) {
            case ALL:
                canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, f3, f4), this.mRadius, this.mRadius, paint);
                return;
            case TOP_LEFT:
                drawTopLeftRoundRect(canvas, paint, f3, f4);
                return;
            case TOP_RIGHT:
                drawTopRightRoundRect(canvas, paint, f3, f4);
                return;
            case BOTTOM_LEFT:
                drawBottomLeftRoundRect(canvas, paint, f3, f4);
                return;
            case BOTTOM_RIGHT:
                drawBottomRightRoundRect(canvas, paint, f3, f4);
                return;
            case TOP:
                drawTopRoundRect(canvas, paint, f3, f4);
                return;
            case BOTTOM:
                drawBottomRoundRect(canvas, paint, f3, f4);
                return;
            case LEFT:
                drawLeftRoundRect(canvas, paint, f3, f4);
                return;
            case RIGHT:
                drawRightRoundRect(canvas, paint, f3, f4);
                return;
            case OTHER_TOP_LEFT:
                drawOtherTopLeftRoundRect(canvas, paint, f3, f4);
                return;
            case OTHER_TOP_RIGHT:
                drawOtherTopRightRoundRect(canvas, paint, f3, f4);
                return;
            case OTHER_BOTTOM_LEFT:
                drawOtherBottomLeftRoundRect(canvas, paint, f3, f4);
                return;
            case OTHER_BOTTOM_RIGHT:
                drawOtherBottomRightRoundRect(canvas, paint, f3, f4);
                return;
            case DIAGONAL_FROM_TOP_LEFT:
                drawDiagonalFromTopLeftRoundRect(canvas, paint, f3, f4);
                return;
            case DIAGONAL_FROM_TOP_RIGHT:
                drawDiagonalFromTopRightRoundRect(canvas, paint, f3, f4);
                return;
            default:
                canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, f3, f4), this.mRadius, this.mRadius, paint);
                return;
        }
    }

    private void drawTopLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, this.mMargin + this.mDiameter, this.mMargin + this.mDiameter), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin + this.mRadius, this.mMargin + this.mRadius, f2), paint);
        canvas.drawRect(new RectF(this.mMargin + this.mRadius, this.mMargin, f, f2), paint);
    }

    private void drawTopRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(f - this.mDiameter, this.mMargin, f, this.mMargin + this.mDiameter), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin, f - this.mRadius, f2), paint);
        canvas.drawRect(new RectF(f - this.mRadius, this.mMargin + this.mRadius, f, f2), paint);
    }

    private void drawBottomLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(this.mMargin, f2 - this.mDiameter, this.mMargin + this.mDiameter, f2), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin, this.mMargin + this.mDiameter, f2 - this.mRadius), paint);
        canvas.drawRect(new RectF(this.mMargin + this.mRadius, this.mMargin, f, f2), paint);
    }

    private void drawBottomRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(f - this.mDiameter, f2 - this.mDiameter, f, f2), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin, f - this.mRadius, f2), paint);
        canvas.drawRect(new RectF(f - this.mRadius, this.mMargin, f, f2 - this.mRadius), paint);
    }

    private void drawTopRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, f, this.mMargin + this.mDiameter), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin + this.mRadius, f, f2), paint);
    }

    private void drawBottomRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(this.mMargin, f2 - this.mDiameter, f, f2), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin, f, f2 - this.mRadius), paint);
    }

    private void drawLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, this.mMargin + this.mDiameter, f2), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin + this.mRadius, this.mMargin, f, f2), paint);
    }

    private void drawRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(f - this.mDiameter, this.mMargin, f, f2), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin, f - this.mRadius, f2), paint);
    }

    private void drawOtherTopLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(this.mMargin, f2 - this.mDiameter, f, f2), this.mRadius, this.mRadius, paint);
        canvas.drawRoundRect(new RectF(f - this.mDiameter, this.mMargin, f, f2), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin, f - this.mRadius, f2 - this.mRadius), paint);
    }

    private void drawOtherTopRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, this.mMargin + this.mDiameter, f2), this.mRadius, this.mRadius, paint);
        canvas.drawRoundRect(new RectF(this.mMargin, f2 - this.mDiameter, f, f2), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin + this.mRadius, this.mMargin, f, f2 - this.mRadius), paint);
    }

    private void drawOtherBottomLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, f, this.mMargin + this.mDiameter), this.mRadius, this.mRadius, paint);
        canvas.drawRoundRect(new RectF(f - this.mDiameter, this.mMargin, f, f2), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin + this.mRadius, f - this.mRadius, f2), paint);
    }

    private void drawOtherBottomRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, f, this.mMargin + this.mDiameter), this.mRadius, this.mRadius, paint);
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, this.mMargin + this.mDiameter, f2), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin + this.mRadius, this.mMargin + this.mRadius, f, f2), paint);
    }

    private void drawDiagonalFromTopLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, this.mMargin + this.mDiameter, this.mMargin + this.mDiameter), this.mRadius, this.mRadius, paint);
        canvas.drawRoundRect(new RectF(f - this.mDiameter, f2 - this.mDiameter, f, f2), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin + this.mRadius, f - this.mDiameter, f2), paint);
        canvas.drawRect(new RectF(this.mMargin + this.mDiameter, this.mMargin, f, f2 - this.mRadius), paint);
    }

    private void drawDiagonalFromTopRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(f - this.mDiameter, this.mMargin, f, this.mMargin + this.mDiameter), this.mRadius, this.mRadius, paint);
        canvas.drawRoundRect(new RectF(this.mMargin, f2 - this.mDiameter, this.mMargin + this.mDiameter, f2), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin, f - this.mRadius, f2 - this.mRadius), paint);
        canvas.drawRect(new RectF(this.mMargin + this.mRadius, this.mMargin + this.mRadius, f, f2), paint);
    }

    @Override // com.bumptech.glide.load.Transformation
    public String getId() {
        return "RoundedTransformation(radius=" + this.mRadius + ", margin=" + this.mMargin + ", diameter=" + this.mDiameter + ", cornerType=" + this.mCornerType.name() + ")";
    }
}