package jp.wasabeef.glide.transformations.gpu;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import jp.co.cyberagent.android.gpuimage.GPUImageSepiaFilter;

/* loaded from: classes.dex */
public class SepiaFilterTransformation extends GPUFilterTransformation {
    private float mIntensity;

    public SepiaFilterTransformation(Context context) {
        this(context, Glide.get(context).getBitmapPool());
    }

    public SepiaFilterTransformation(Context context, BitmapPool bitmapPool) {
        this(context, bitmapPool, 1.0f);
    }

    public SepiaFilterTransformation(Context context, float f) {
        this(context, Glide.get(context).getBitmapPool(), f);
    }

    public SepiaFilterTransformation(Context context, BitmapPool bitmapPool, float f) {
        super(context, bitmapPool, new GPUImageSepiaFilter());
        this.mIntensity = f;
        ((GPUImageSepiaFilter) getFilter()).setIntensity(this.mIntensity);
    }

    @Override // jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation, com.bumptech.glide.load.Transformation
    public String getId() {
        return "SepiaFilterTransformation(intensity=" + this.mIntensity + ")";
    }
}