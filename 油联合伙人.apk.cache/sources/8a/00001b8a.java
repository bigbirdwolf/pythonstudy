package jp.wasabeef.glide.transformations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;

/* loaded from: classes.dex */
public class ColorFilterTransformation implements Transformation<Bitmap> {
    private BitmapPool mBitmapPool;
    private int mColor;

    public ColorFilterTransformation(Context context, int i) {
        this(Glide.get(context).getBitmapPool(), i);
    }

    public ColorFilterTransformation(BitmapPool bitmapPool, int i) {
        this.mBitmapPool = bitmapPool;
        this.mColor = i;
    }

    @Override // com.bumptech.glide.load.Transformation
    public Resource<Bitmap> transform(Resource<Bitmap> resource, int i, int i2) {
        Bitmap bitmap = resource.get();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap.Config config = bitmap.getConfig() != null ? bitmap.getConfig() : Bitmap.Config.ARGB_8888;
        Bitmap bitmap2 = this.mBitmapPool.get(width, height, config);
        if (bitmap2 == null) {
            bitmap2 = Bitmap.createBitmap(width, height, config);
        }
        Canvas canvas = new Canvas(bitmap2);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColorFilter(new PorterDuffColorFilter(this.mColor, PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return BitmapResource.obtain(bitmap2, this.mBitmapPool);
    }

    @Override // com.bumptech.glide.load.Transformation
    public String getId() {
        return "ColorFilterTransformation(color=" + this.mColor + ")";
    }
}