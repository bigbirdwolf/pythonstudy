package jp.wasabeef.glide.transformations.gpu;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import jp.co.cyberagent.android.gpuimage.GPUImageContrastFilter;

/* loaded from: classes.dex */
public class ContrastFilterTransformation extends GPUFilterTransformation {
    private float mContrast;

    public ContrastFilterTransformation(Context context) {
        this(context, Glide.get(context).getBitmapPool());
    }

    public ContrastFilterTransformation(Context context, BitmapPool bitmapPool) {
        this(context, bitmapPool, 1.0f);
    }

    public ContrastFilterTransformation(Context context, float f) {
        this(context, Glide.get(context).getBitmapPool(), f);
    }

    public ContrastFilterTransformation(Context context, BitmapPool bitmapPool, float f) {
        super(context, bitmapPool, new GPUImageContrastFilter());
        this.mContrast = f;
        ((GPUImageContrastFilter) getFilter()).setContrast(this.mContrast);
    }

    @Override // jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation, com.bumptech.glide.load.Transformation
    public String getId() {
        return "ContrastFilterTransformation(contrast=" + this.mContrast + ")";
    }
}