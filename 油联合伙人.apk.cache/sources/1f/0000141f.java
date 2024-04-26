package com.umeng.socialize.b.a;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import com.umeng.socialize.b.b.b;
import com.umeng.socialize.b.b.c;
import com.umeng.socialize.b.b.d;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.net.utils.SocializeNetUtils;
import com.umeng.socialize.utils.DefaultClass;
import com.umeng.socialize.utils.SLog;
import com.umeng.socialize.utils.SocializeUtils;
import com.umeng.socialize.utils.UmengText;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: ImageImpl.java */
/* loaded from: classes.dex */
public class a {
    static {
        com.umeng.socialize.b.b.a.a();
    }

    private static byte[] b(Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        if (bitmap != null) {
            try {
                if (!bitmap.isRecycled()) {
                    try {
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            float rowBytes = (bitmap.getRowBytes() * bitmap.getHeight()) / 1024;
                            int i = rowBytes > c.g ? (int) ((c.g / rowBytes) * 100) : 100;
                            if (bitmap != null) {
                                bitmap.compress(compressFormat, i, byteArrayOutputStream);
                            }
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e) {
                                SLog.error(UmengText.IMAGE.CLOSE, e);
                            }
                            return byteArray;
                        } catch (Exception e2) {
                            e = e2;
                            byteArrayOutputStream2 = byteArrayOutputStream;
                            SLog.error(UmengText.IMAGE.BITMAOTOBINARY, e);
                            if (byteArrayOutputStream2 != null) {
                                try {
                                    byteArrayOutputStream2.close();
                                } catch (IOException e3) {
                                    SLog.error(UmengText.IMAGE.CLOSE, e3);
                                }
                            }
                            return DefaultClass.getBytes();
                        } catch (Throwable th) {
                            th = th;
                            if (byteArrayOutputStream != null) {
                                try {
                                    byteArrayOutputStream.close();
                                } catch (IOException e4) {
                                    SLog.error(UmengText.IMAGE.CLOSE, e4);
                                }
                            }
                            throw th;
                        }
                    } catch (Exception e5) {
                        e = e5;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream = null;
            }
        }
        return null;
    }

    private static BitmapFactory.Options d(byte[] bArr) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        int ceil = (int) Math.ceil(options.outWidth / UMImage.MAX_WIDTH);
        int ceil2 = (int) Math.ceil(options.outHeight / UMImage.MAX_HEIGHT);
        if (ceil2 <= 1 || ceil <= 1) {
            if (ceil2 > 2) {
                options.inSampleSize = ceil2;
            } else if (ceil > 2) {
                options.inSampleSize = ceil;
            }
        } else if (ceil2 > ceil) {
            options.inSampleSize = ceil2;
        } else {
            options.inSampleSize = ceil;
        }
        options.inJustDecodeBounds = false;
        return options;
    }

    public static byte[] a(UMImage uMImage, int i) {
        if (uMImage == null) {
            return DefaultClass.getBytes();
        }
        if (uMImage.asBinImage() == null || a(uMImage) < i) {
            return uMImage.asBinImage();
        }
        if (uMImage.compressStyle != UMImage.CompressStyle.QUALITY) {
            try {
                byte[] asBinImage = uMImage.asBinImage();
                if (asBinImage == null) {
                    return new byte[1];
                }
                if (asBinImage.length <= 0) {
                    return uMImage.asBinImage();
                }
                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(asBinImage, 0, asBinImage.length);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byteArrayOutputStream.write(asBinImage, 0, asBinImage.length);
                while (byteArrayOutputStream.toByteArray().length > i) {
                    double length = asBinImage.length;
                    Double.isNaN(length);
                    double d = i;
                    Double.isNaN(d);
                    double sqrt = Math.sqrt((length * 1.0d) / d);
                    double width = decodeByteArray.getWidth();
                    Double.isNaN(width);
                    int i2 = (int) (width / sqrt);
                    double height = decodeByteArray.getHeight();
                    Double.isNaN(height);
                    decodeByteArray = Bitmap.createScaledBitmap(decodeByteArray, i2, (int) (height / sqrt), true);
                    byteArrayOutputStream.reset();
                    if (decodeByteArray != null) {
                        decodeByteArray.compress(uMImage.compressFormat, 100, byteArrayOutputStream);
                        asBinImage = byteArrayOutputStream.toByteArray();
                    }
                }
                if (byteArrayOutputStream.toByteArray().length > i) {
                    return null;
                }
                return asBinImage;
            } catch (Throwable th) {
                SLog.error(th);
                return DefaultClass.getBytes();
            }
        }
        return a(uMImage.asBinImage(), i, uMImage.compressFormat);
    }

    public static byte[] a(String str) {
        return SocializeNetUtils.getNetData(str);
    }

    public static Bitmap a(byte[] bArr) {
        if (bArr != null) {
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }
        return null;
    }

    public static File b(byte[] bArr) {
        try {
            return a(bArr, b.a().b());
        } catch (IOException e) {
            SLog.error(UmengText.IMAGE.BINARYTOFILE, e);
            return null;
        }
    }

    private static File a(byte[] bArr, File file) {
        BufferedOutputStream bufferedOutputStream = null;
        try {
            try {
                try {
                    BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file));
                    try {
                        bufferedOutputStream2.write(bArr);
                        bufferedOutputStream2.close();
                    } catch (Exception e) {
                        e = e;
                        bufferedOutputStream = bufferedOutputStream2;
                        SLog.error(UmengText.IMAGE.GET_FILE_FROM_BINARY, e);
                        if (bufferedOutputStream != null) {
                            bufferedOutputStream.close();
                        }
                        return file;
                    } catch (Throwable th) {
                        th = th;
                        bufferedOutputStream = bufferedOutputStream2;
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e2) {
                                SLog.error(UmengText.IMAGE.CLOSE, e2);
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (IOException e4) {
            SLog.error(UmengText.IMAGE.CLOSE, e4);
        }
        return file;
    }

    public static byte[] a(Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        return b(bitmap, compressFormat);
    }

    private static Bitmap a(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return createBitmap;
    }

    public static byte[] a(Context context, int i, boolean z, Bitmap.CompressFormat compressFormat) {
        Drawable drawable;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (!z) {
            Resources resources = context.getResources();
            if (Build.VERSION.SDK_INT >= 21) {
                drawable = resources.getDrawable(i, null);
            } else {
                drawable = resources.getDrawable(i);
            }
            Bitmap a = a(drawable);
            if (a != null) {
                a.compress(compressFormat, 100, byteArrayOutputStream);
            }
            return byteArrayOutputStream.toByteArray();
        }
        byte[] bArr = new byte[0];
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap decodeStream = BitmapFactory.decodeStream(context.getResources().openRawResource(i), null, options);
            if (decodeStream != null) {
                decodeStream.compress(compressFormat, 100, byteArrayOutputStream);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Error e) {
            SLog.error(UmengText.IMAGE.TOOBIG, e);
            return bArr;
        }
    }

    public static byte[] a(File file, Bitmap.CompressFormat compressFormat) {
        return b(file, compressFormat);
    }

    public static String c(byte[] bArr) {
        return d.a(bArr);
    }

    public static int a(UMImage uMImage) {
        if (uMImage.getImageStyle() == UMImage.FILE_IMAGE) {
            return a(uMImage.asFileImage());
        }
        return e(uMImage.asBinImage());
    }

    private static byte[] b(File file, Bitmap.CompressFormat compressFormat) {
        if (file == null || !file.getAbsoluteFile().exists()) {
            return null;
        }
        byte[] a = b.a().a(file);
        if (SocializeUtils.assertBinaryInvalid(a)) {
            return d.m[1].equals(d.a(a)) ? a : a(a, compressFormat);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x004a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static byte[] a(byte[] r4, android.graphics.Bitmap.CompressFormat r5) {
        /*
            r0 = 0
            android.graphics.BitmapFactory$Options r1 = d(r4)     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L32
            r2 = 0
            int r3 = r4.length     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L32
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeByteArray(r4, r2, r3, r1)     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L32
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L32
            r1.<init>()     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L32
            if (r4 == 0) goto L20
            r2 = 100
            r4.compress(r5, r2, r1)     // Catch: java.lang.Exception -> L1e java.lang.Throwable -> L47
            r4.recycle()     // Catch: java.lang.Exception -> L1e java.lang.Throwable -> L47
            java.lang.System.gc()     // Catch: java.lang.Exception -> L1e java.lang.Throwable -> L47
            goto L20
        L1e:
            r4 = move-exception
            goto L34
        L20:
            byte[] r4 = r1.toByteArray()     // Catch: java.lang.Exception -> L1e java.lang.Throwable -> L47
            r1.close()     // Catch: java.io.IOException -> L28
            goto L46
        L28:
            r5 = move-exception
            java.lang.String r0 = com.umeng.socialize.utils.UmengText.IMAGE.CLOSE
            com.umeng.socialize.utils.SLog.error(r0, r5)
            goto L46
        L2f:
            r4 = move-exception
            r1 = r0
            goto L48
        L32:
            r4 = move-exception
            r1 = r0
        L34:
            java.lang.String r5 = com.umeng.socialize.utils.UmengText.IMAGE.FILE_TO_BINARY_ERROR     // Catch: java.lang.Throwable -> L47
            com.umeng.socialize.utils.SLog.error(r5, r4)     // Catch: java.lang.Throwable -> L47
            if (r1 == 0) goto L45
            r1.close()     // Catch: java.io.IOException -> L3f
            goto L45
        L3f:
            r4 = move-exception
            java.lang.String r5 = com.umeng.socialize.utils.UmengText.IMAGE.CLOSE
            com.umeng.socialize.utils.SLog.error(r5, r4)
        L45:
            r4 = r0
        L46:
            return r4
        L47:
            r4 = move-exception
        L48:
            if (r1 == 0) goto L54
            r1.close()     // Catch: java.io.IOException -> L4e
            goto L54
        L4e:
            r5 = move-exception
            java.lang.String r0 = com.umeng.socialize.utils.UmengText.IMAGE.CLOSE
            com.umeng.socialize.utils.SLog.error(r0, r5)
        L54:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.b.a.a.a(byte[], android.graphics.Bitmap$CompressFormat):byte[]");
    }

    public static byte[] a(byte[] bArr, int i, Bitmap.CompressFormat compressFormat) {
        if (bArr == null || bArr.length < i) {
            return bArr;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        boolean z = false;
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        int i2 = 1;
        while (!z && i2 <= 10) {
            int pow = (int) (Math.pow(0.8d, i2) * 100.0d);
            if (decodeByteArray != null) {
                decodeByteArray.compress(compressFormat, pow, byteArrayOutputStream);
            }
            if (byteArrayOutputStream.size() < i) {
                z = true;
            } else {
                byteArrayOutputStream.reset();
                i2++;
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (!decodeByteArray.isRecycled()) {
            decodeByteArray.recycle();
        }
        if (byteArray != null && byteArray.length <= 0) {
            SLog.E(UmengText.IMAGE.THUMB_ERROR);
        }
        return byteArray;
    }

    private static int e(byte[] bArr) {
        if (bArr != null) {
            return bArr.length;
        }
        return 0;
    }

    private static int a(File file) {
        if (file != null) {
            try {
                return new FileInputStream(file).available();
            } catch (Throwable th) {
                SLog.error(UmengText.IMAGE.GET_IMAGE_SCALE_ERROR, th);
                return 0;
            }
        }
        return 0;
    }
}