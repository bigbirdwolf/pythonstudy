package com.yltx.oil.partner.oss;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class ImageDisplayer {
    private int height;
    private ImageView imageView;
    private int width;

    public ImageDisplayer(ImageView imageView) {
        this.imageView = imageView;
    }

    public ImageDisplayer(int i, int i2) {
        this.height = i;
        this.width = i2;
    }

    public static byte[] getBytesFromStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 > i2 && i7 / i5 > i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    public Bitmap autoResizeFromLocalFile(String str) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i = this.height;
        int i2 = this.width;
        if (this.imageView != null) {
            i = this.imageView.getHeight();
            i2 = this.imageView.getWidth();
        }
        options.inSampleSize = calculateInSampleSize(options, i2, i);
        Log.d("ImageHeight", String.valueOf(options.outHeight));
        Log.d("ImageWidth", String.valueOf(options.outWidth));
        Log.d("Height", String.valueOf(i));
        Log.d("Width", String.valueOf(i2));
        Log.d("SampleSize", String.valueOf(options.inSampleSize));
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    public Bitmap autoResizeFromBytes(byte[] bArr) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        int i = this.height;
        int i2 = this.width;
        if (this.imageView != null) {
            i = this.imageView.getHeight();
            i2 = this.imageView.getWidth();
        }
        options.inSampleSize = calculateInSampleSize(options, i2, i);
        Log.d("ImageHeight", String.valueOf(options.outHeight));
        Log.d("ImageWidth", String.valueOf(options.outWidth));
        Log.d("Height", String.valueOf(i));
        Log.d("Width", String.valueOf(i2));
        Log.d("SampleSize", String.valueOf(options.inSampleSize));
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
    }

    public Bitmap autoResizeFromStream(InputStream inputStream) throws IOException {
        return autoResizeFromBytes(getBytesFromStream(inputStream));
    }

    public Bitmap autoResizeFromBitmap(Bitmap bitmap) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.outHeight = bitmap.getHeight();
        options.outWidth = bitmap.getWidth();
        int i = this.height;
        int i2 = this.width;
        if (this.imageView != null) {
            i = this.imageView.getHeight();
            i2 = this.imageView.getWidth();
        }
        int calculateInSampleSize = calculateInSampleSize(options, i2, i);
        Log.d("ImageHeight", String.valueOf(options.outHeight));
        Log.d("ImageWidth", String.valueOf(options.outWidth));
        Log.d("Height", String.valueOf(i));
        Log.d("Width", String.valueOf(i2));
        return calculateInSampleSize == 1 ? bitmap : Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / calculateInSampleSize, bitmap.getHeight() / calculateInSampleSize, true);
    }
}