package jp.wasabeef.glide.transformations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;

/* loaded from: classes.dex */
public class GrayscaleTransformation implements Transformation<Bitmap> {
    private BitmapPool mBitmapPool;

    @Override // com.bumptech.glide.load.Transformation
    public String getId() {
        return "GrayscaleTransformation()";
    }

    public GrayscaleTransformation(Context context) {
        this(Glide.get(context).getBitmapPool());
    }

    public GrayscaleTransformation(BitmapPool bitmapPool) {
        this.mBitmapPool = bitmapPool;
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
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return BitmapResource.obtain(bitmap2, this.mBitmapPool);
    }
}