package com.yltx.oil.partner.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.Hashtable;

/* loaded from: classes.dex */
public class EncodeUtils {
    private static final int IMAGE_HALFWIDTH = 40;

    public static Bitmap createCode(Context context, String str, Bitmap bitmap) throws WriterException {
        Matrix matrix = new Matrix();
        matrix.setScale(80.0f / bitmap.getWidth(), 80.0f / bitmap.getHeight());
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Hashtable hashtable = new Hashtable();
        hashtable.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        BitMatrix encode = multiFormatWriter.encode(str, BarcodeFormat.QR_CODE, DensityUtils.dp2px(context, 300.0f), DensityUtils.dp2px(context, 300.0f), hashtable);
        int width = encode.getWidth();
        int height = encode.getHeight();
        int i = width / 2;
        int i2 = height / 2;
        int[] iArr = new int[width * height];
        for (int i3 = 0; i3 < height; i3++) {
            for (int i4 = 0; i4 < width; i4++) {
                if (i4 > i - 40 && i4 < i + 40 && i3 > i2 - 40 && i3 < i2 + 40) {
                    iArr[(i3 * width) + i4] = createBitmap.getPixel((i4 - i) + 40, (i3 - i2) + 40);
                } else if (encode.get(i4, i3)) {
                    iArr[(i3 * width) + i4] = -16777216;
                } else {
                    iArr[(i3 * width) + i4] = -1;
                }
            }
        }
        Bitmap createBitmap2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        createBitmap2.setPixels(iArr, 0, width, 0, 0, width, height);
        return createBitmap2;
    }

    public static Bitmap createCode(Context context, String str) throws WriterException {
        BitMatrix encode = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, DensityUtils.dp2px(context, 250.0f), DensityUtils.dp2px(context, 250.0f));
        int width = encode.getWidth();
        int height = encode.getHeight();
        int[] iArr = new int[width * height];
        for (int i = 0; i < height; i++) {
            for (int i2 = 0; i2 < width; i2++) {
                if (encode.get(i2, i)) {
                    iArr[(i * width) + i2] = -16777216;
                } else {
                    iArr[(i * width) + i2] = -1;
                }
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return createBitmap;
    }
}