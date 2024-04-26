package jp.wasabeef.glide.transformations;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;

/* loaded from: classes.dex */
public class CropSquareTransformation implements Transformation<Bitmap> {
    private BitmapPool mBitmapPool;
    private int mHeight;
    private int mWidth;

    public CropSquareTransformation(Context context) {
        this(Glide.get(context).getBitmapPool());
    }

    public CropSquareTransformation(BitmapPool bitmapPool) {
        this.mBitmapPool = bitmapPool;
    }

    @Override // com.bumptech.glide.load.Transformation
    public Resource<Bitmap> transform(Resource<Bitmap> resource, int i, int i2) {
        Bitmap bitmap = resource.get();
        int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
        this.mWidth = (bitmap.getWidth() - min) / 2;
        this.mHeight = (bitmap.getHeight() - min) / 2;
        Bitmap bitmap2 = this.mBitmapPool.get(this.mWidth, this.mHeight, bitmap.getConfig() != null ? bitmap.getConfig() : Bitmap.Config.ARGB_8888);
        if (bitmap2 == null) {
            bitmap2 = Bitmap.createBitmap(bitmap, this.mWidth, this.mHeight, min, min);
        }
        return BitmapResource.obtain(bitmap2, this.mBitmapPool);
    }

    @Override // com.bumptech.glide.load.Transformation
    public String getId() {
        return "CropSquareTransformation(width=" + this.mWidth + ", height=" + this.mHeight + ")";
    }
}