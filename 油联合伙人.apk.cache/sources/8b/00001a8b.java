package com.yltx.oil.partner.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.util.Base64;
import android.util.Log;
import com.yltx.oil.partner.utils.CommonUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes.dex */
public class BitmapUtils {
    private static final String TAG = "BitmapUtils";

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

    public static String readBitmapSize(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i = options.outWidth;
        int i2 = options.outHeight;
        return i + "*" + i2;
    }

    private static BitmapFactory.Options bitmapManage(int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inSampleSize = i;
        return options;
    }

    public static String saveBitmapByUri(Context context, int i, String str, float f, float f2) throws Exception {
        Bitmap rotateBitmap = rotateBitmap(i, compressBitmap(str, f, f2));
        File appCacheFile = CommonUtils.getAppCacheFile(CommonUtils.generateFileName(CommonUtils.FileType.FILE_TYPE_IMAGE), context);
        if (appCacheFile == null) {
            throw new RuntimeException("photo cannot be created.");
        }
        writeToSdcard(rotateBitmap, appCacheFile);
        return appCacheFile.getAbsolutePath();
    }

    public static void saveBitmap(Bitmap bitmap, String str, float f, float f2) throws Exception {
        writeToSdcard(compressBitmap(bitmap, f, f2), new File(str));
    }

    public static void saveBitmap(String str, String str2, float f, float f2) {
        writeToSdcard(compressBitmap(str, f, f2), new File(str2));
    }

    public static Bitmap compressBitmap(Bitmap bitmap, float f, float f2) throws Exception {
        return compBitmapByScale(bitmap, f, f2);
    }

    public static Bitmap compressBitmap(String str, float f, float f2) {
        if (f <= 0.0f || f2 <= 0.0f) {
            f = 480.0f;
            f2 = 800.0f;
        }
        return compBitmapByScale(str, f, f2);
    }

    public static Bitmap compBitmapByScale(String str, float f, float f2) {
        int round;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i = options.outWidth;
        int i2 = options.outHeight;
        if (i >= i2 && i > f) {
            round = Math.round(options.outWidth / f);
        } else {
            round = (i > i2 || ((float) i2) <= f2) ? 1 : Math.round(options.outHeight / f2);
        }
        if (round <= 0) {
            round = 1;
        }
        return BitmapFactory.decodeFile(str, bitmapManage(round));
    }

    public static Bitmap compBitmapByScale(Bitmap bitmap, float f, float f2) throws Exception {
        int round;
        if (bitmap == null) {
            throw new IllegalArgumentException("source can not be empty.");
        }
        if (f <= 0.0f || f2 <= 0.0f) {
            f = 480.0f;
            f2 = 800.0f;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Rect rect = null;
        BitmapFactory.decodeStream(byteArrayInputStream, rect, options);
        options.inJustDecodeBounds = false;
        int i = options.outWidth;
        int i2 = options.outHeight;
        if (i >= i2 && i > f) {
            round = Math.round(options.outWidth / f);
        } else {
            round = (i > i2 || ((float) i2) <= f2) ? 1 : Math.round(options.outHeight / f2);
        }
        if (round <= 0) {
            round = 1;
        }
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inSampleSize = round;
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        Bitmap decodeStream = BitmapFactory.decodeStream(byteArrayInputStream2, rect, options);
        closeInputStream(byteArrayInputStream2);
        closeOutputStream(byteArrayOutputStream);
        return decodeStream;
    }

    private static void closeInputStream(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void closeOutputStream(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ByteArrayOutputStream compBitmap(Bitmap bitmap, int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        if (byteArrayOutputStream.toByteArray().length / 1024 > i) {
            while (byteArrayOutputStream.toByteArray().length / 1024 > i) {
                byteArrayOutputStream.reset();
                bitmap.compress(Bitmap.CompressFormat.JPEG, i2, byteArrayOutputStream);
                i2 -= 10;
            }
        }
        return byteArrayOutputStream;
    }

    public static Bitmap compBitmapByQuality(Bitmap bitmap, int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        if (byteArrayOutputStream.toByteArray().length / 1024 > i) {
            while (byteArrayOutputStream.toByteArray().length / 1024 > i) {
                byteArrayOutputStream.reset();
                bitmap.compress(Bitmap.CompressFormat.JPEG, i2, byteArrayOutputStream);
                i2 -= 10;
            }
        }
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, null);
    }

    public static void writeToSdcard(Bitmap bitmap, File file) {
        try {
            if (!CommonUtils.isSDCardMounted()) {
                throw new RuntimeException("sdcard is not available.");
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                if (bitmap != null && bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)) {
                    fileOutputStream.flush();
                }
                if (bitmap == null) {
                    return;
                }
            } catch (IOException e) {
                file.delete();
                Log.e(TAG, e.getMessage(), e);
                if (bitmap == null) {
                    return;
                }
            }
            bitmap.recycle();
        } catch (Throwable th) {
            if (bitmap != null) {
                bitmap.recycle();
            }
            throw th;
        }
    }

    public static Bitmap stringToBitmap(String str) {
        try {
            System.out.println(str.split(",")[1].replaceAll("[\\n\\r]", ""));
            Log.i("======", str.split(",")[1].replaceAll("[\\n\\r]", ""));
            byte[] decode = Base64.decode(str.split(",")[1].replaceAll("[\\n\\r]", ""), 0);
            return BitmapFactory.decodeByteArray(decode, 0, decode.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}