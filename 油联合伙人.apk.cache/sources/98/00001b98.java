package jp.wasabeef.glide.transformations.gpu;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;

/* loaded from: classes.dex */
public class GPUFilterTransformation implements Transformation<Bitmap> {
    private BitmapPool mBitmapPool;
    private Context mContext;
    private GPUImageFilter mFilter;

    public GPUFilterTransformation(Context context, GPUImageFilter gPUImageFilter) {
        this(context, Glide.get(context).getBitmapPool(), gPUImageFilter);
    }

    public GPUFilterTransformation(Context context, BitmapPool bitmapPool, GPUImageFilter gPUImageFilter) {
        this.mContext = context.getApplicationContext();
        this.mBitmapPool = bitmapPool;
        this.mFilter = gPUImageFilter;
    }

    @Override // com.bumptech.glide.load.Transformation
    public Resource<Bitmap> transform(Resource<Bitmap> resource, int i, int i2) {
        GPUImage gPUImage = new GPUImage(this.mContext);
        gPUImage.setImage(resource.get());
        gPUImage.setFilter(this.mFilter);
        return BitmapResource.obtain(gPUImage.getBitmapWithFilterApplied(), this.mBitmapPool);
    }

    @Override // com.bumptech.glide.load.Transformation
    public String getId() {
        return getClass().getSimpleName();
    }

    public <T> T getFilter() {
        return (T) this.mFilter;
    }
}