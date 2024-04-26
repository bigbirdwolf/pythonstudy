package jp.wasabeef.glide.transformations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import jp.wasabeef.glide.transformations.internal.Utils;

/* loaded from: classes.dex */
public class MaskTransformation implements Transformation<Bitmap> {
    private static Paint sMaskingPaint = new Paint();
    private BitmapPool mBitmapPool;
    private Context mContext;
    private int mMaskId;

    static {
        sMaskingPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    }

    public MaskTransformation(Context context, int i) {
        this(context, Glide.get(context).getBitmapPool(), i);
    }

    public MaskTransformation(Context context, BitmapPool bitmapPool, int i) {
        this.mBitmapPool = bitmapPool;
        this.mContext = context.getApplicationContext();
        this.mMaskId = i;
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
        Drawable maskDrawable = Utils.getMaskDrawable(this.mContext, this.mMaskId);
        Canvas canvas = new Canvas(bitmap2);
        maskDrawable.setBounds(0, 0, width, height);
        maskDrawable.draw(canvas);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, sMaskingPaint);
        return BitmapResource.obtain(bitmap2, this.mBitmapPool);
    }

    @Override // com.bumptech.glide.load.Transformation
    public String getId() {
        return "MaskTransformation(maskId=" + this.mContext.getResources().getResourceEntryName(this.mMaskId) + ")";
    }
}