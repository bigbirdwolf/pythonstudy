package com.yltx.oil.partner.utils;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class ImageUtils {
    private static final String TAG = "ImageUtils";

    public static Bitmap rotateBitmap(int i, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static int readBitmapDegree(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
            if (attributeInt != 3) {
                if (attributeInt != 6) {
                    return attributeInt != 8 ? 0 : 270;
                }
                return 90;
            }
            return 180;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            return 0;
        }
    }

    public static int[] readBitmapSize(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return new int[]{options.outWidth, options.outHeight};
    }

    public static byte[] bitmap2ByteArray(Bitmap bitmap) {
        ByteBuffer allocate = ByteBuffer.allocate(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(allocate);
        return allocate.array();
    }

    public static byte[] inputStream2ByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                inputStream.close();
                byteArrayOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public static Bitmap decodeFileByScale(String str, int i) {
        if (i <= 0) {
            i = 720;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int calculateInSampleSize = calculateInSampleSize(options, i);
        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateInSampleSize;
        return BitmapFactory.decodeFile(str, options);
    }

    public static Bitmap decodeStreamByScale(ContentResolver contentResolver, Uri uri, int i) {
        InputStream inputStream;
        InputStream inputStream2;
        if (i <= 0) {
            i = 720;
        }
        try {
            inputStream = contentResolver.openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            inputStream = null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        int calculateInSampleSize = calculateInSampleSize(options, i);
        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateInSampleSize;
        try {
            inputStream2 = contentResolver.openInputStream(uri);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            inputStream2 = inputStream;
        }
        return BitmapFactory.decodeStream(inputStream2, null, options);
    }

    public static Bitmap compBitmapByQuality(Bitmap bitmap, int i) {
        int i2;
        if (bitmap == null || bitmap.isRecycled()) {
            throw new IllegalArgumentException("Bitmap can not be null or recycled.");
        }
        int round = Math.round(bitmap.getByteCount() / i);
        if (round > 1 && round < 10) {
            i2 = 100 - (round * 10);
        } else if (round >= 10) {
            Log.w(TAG, "size ratio too large");
            i2 = 50;
        } else {
            i2 = 100;
        }
        if (i2 == 100) {
            return bitmap;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, i2, byteArrayOutputStream);
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
    }

    public static Bitmap compBitmapByQuality(String str, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        if (i2 <= i3) {
            i2 = i3;
        }
        int round = i2 >= 1280 ? Math.round(i2 / 1280.0f) : 1;
        options.inJustDecodeBounds = false;
        options.inSampleSize = round;
        return compBitmapByQuality(BitmapFactory.decodeFile(str, options), i);
    }

    public static byte[] compBitmap2bytesByQuality(Bitmap bitmap, int i) {
        int i2;
        if (bitmap == null || bitmap.isRecycled()) {
            throw new IllegalArgumentException("Bitmap can not be null or recycled.");
        }
        int round = Math.round(bitmap.getByteCount() / i);
        if (round > 1 && round < 10) {
            i2 = 100 - (round * 10);
        } else if (round >= 10) {
            Log.w(TAG, "size ratio too large");
            i2 = 50;
        } else {
            i2 = 100;
        }
        if (i2 == 100) {
            return bitmap2ByteArray(bitmap);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, i2, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] compBitmap2bytesByQuality(String str, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        if (i2 <= i3) {
            i2 = i3;
        }
        int round = i2 >= 1280 ? Math.round(i2 / 1280.0f) : 1;
        options.inJustDecodeBounds = false;
        options.inSampleSize = round;
        return compBitmap2bytesByQuality(BitmapFactory.decodeFile(str, options), i);
    }

    public static void writeBitmapToFile(Bitmap bitmap, File file) {
        if (!CommonUtils.isSDCardMounted()) {
            throw new RuntimeException("sdcard is not available.");
        }
        if (bitmap != null) {
            try {
                if (!bitmap.isRecycled()) {
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)) {
                            fileOutputStream.flush();
                        }
                    } catch (IOException e) {
                        file.delete();
                        Log.e(TAG, e.getMessage(), e);
                    }
                    return;
                }
            } finally {
                bitmap.recycle();
            }
        }
        throw new IllegalArgumentException("bitmap can not be null or recycled.");
    }

    public static void writeByteArrayToFile(byte[] bArr, File file) {
        if (!CommonUtils.isSDCardMounted()) {
            throw new RuntimeException("sdcard is not available.");
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                    try {
                        fileOutputStream2.write(bArr);
                        fileOutputStream2.close();
                    } catch (IOException e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    e = e3;
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int i) {
        return Math.round(options.outWidth / i);
    }
}