package com.umeng.socialize.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import com.umeng.social.tool.UMImageMark;
import com.umeng.socialize.b.a.a;
import com.umeng.socialize.media.UMediaObject;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.ContextUtil;
import com.umeng.socialize.utils.SLog;
import com.umeng.socialize.utils.SocializeUtils;
import com.umeng.socialize.utils.UmengText;
import java.io.Closeable;
import java.io.File;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class UMImage extends BaseMediaObject {
    public static int BINARY_IMAGE = 5;
    public static int BITMAP_IMAGE = 4;
    public static int FILE_IMAGE = 1;
    public static int MAX_HEIGHT = 1024;
    public static int MAX_WIDTH = 768;
    public static int RES_IMAGE = 3;
    public static int URL_IMAGE = 2;
    public Bitmap.CompressFormat compressFormat;
    public CompressStyle compressStyle;
    private ConfiguredConvertor f;
    private UMImage g;
    private UMImageMark h;
    private int i;
    public boolean isLoadImgByCompress;
    private boolean j;

    /* loaded from: classes.dex */
    public enum CompressStyle {
        SCALE,
        QUALITY
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface IImageConvertor {
        byte[] asBinary();

        Bitmap asBitmap();

        File asFile();

        String asUrl();
    }

    private float a(float f, float f2, float f3, float f4) {
        if (f > f4 || f2 > f4) {
            float f5 = f / f3;
            float f6 = f2 / f4;
            return f5 > f6 ? f5 : f6;
        }
        return -1.0f;
    }

    public UMImage(Context context, File file) {
        this.f = null;
        this.isLoadImgByCompress = true;
        this.compressStyle = CompressStyle.SCALE;
        this.compressFormat = Bitmap.CompressFormat.JPEG;
        this.i = 0;
        a(context, file);
    }

    public UMImage(Context context, String str) {
        super(str);
        this.f = null;
        this.isLoadImgByCompress = true;
        this.compressStyle = CompressStyle.SCALE;
        this.compressFormat = Bitmap.CompressFormat.JPEG;
        this.i = 0;
        a((Context) new WeakReference(context).get(), str);
    }

    public UMImage(Context context, int i) {
        this.f = null;
        this.isLoadImgByCompress = true;
        this.compressStyle = CompressStyle.SCALE;
        this.compressFormat = Bitmap.CompressFormat.JPEG;
        this.i = 0;
        a(context, Integer.valueOf(i));
    }

    public UMImage(Context context, byte[] bArr) {
        this.f = null;
        this.isLoadImgByCompress = true;
        this.compressStyle = CompressStyle.SCALE;
        this.compressFormat = Bitmap.CompressFormat.JPEG;
        this.i = 0;
        a(context, bArr);
    }

    public UMImage(Context context, Bitmap bitmap) {
        this.f = null;
        this.isLoadImgByCompress = true;
        this.compressStyle = CompressStyle.SCALE;
        this.compressFormat = Bitmap.CompressFormat.JPEG;
        this.i = 0;
        a(context, bitmap);
    }

    public UMImage(Context context, Bitmap bitmap, UMImageMark uMImageMark) {
        this.f = null;
        this.isLoadImgByCompress = true;
        this.compressStyle = CompressStyle.SCALE;
        this.compressFormat = Bitmap.CompressFormat.JPEG;
        this.i = 0;
        a(context, bitmap, uMImageMark);
    }

    public UMImage(Context context, int i, UMImageMark uMImageMark) {
        this.f = null;
        this.isLoadImgByCompress = true;
        this.compressStyle = CompressStyle.SCALE;
        this.compressFormat = Bitmap.CompressFormat.JPEG;
        this.i = 0;
        a(context, Integer.valueOf(i), uMImageMark);
    }

    public UMImage(Context context, byte[] bArr, UMImageMark uMImageMark) {
        this.f = null;
        this.isLoadImgByCompress = true;
        this.compressStyle = CompressStyle.SCALE;
        this.compressFormat = Bitmap.CompressFormat.JPEG;
        this.i = 0;
        a(context, bArr, uMImageMark);
    }

    private void a(Context context, Object obj) {
        a(context, obj, null);
    }

    private void a(Context context, Object obj, UMImageMark uMImageMark) {
        Bitmap a;
        if (uMImageMark != null) {
            this.j = true;
            this.h = uMImageMark;
            this.h.setContext(context);
        }
        if (ContextUtil.getContext() == null) {
            ContextUtil.setContext(context.getApplicationContext());
        }
        if (obj instanceof File) {
            this.i = FILE_IMAGE;
            this.f = new FileConvertor((File) obj);
        } else if (obj instanceof String) {
            this.i = URL_IMAGE;
            this.f = new UrlConvertor((String) obj);
        } else {
            if (obj instanceof Integer) {
                this.i = RES_IMAGE;
                a = isHasWaterMark() ? a(context, ((Integer) obj).intValue()) : null;
                if (a != null) {
                    this.f = new BitmapConvertor(a);
                } else {
                    this.f = new ResConvertor(context.getApplicationContext(), ((Integer) obj).intValue());
                }
            } else if (obj instanceof byte[]) {
                this.i = BINARY_IMAGE;
                a = isHasWaterMark() ? a((byte[]) obj) : null;
                if (a != null) {
                    this.f = new BitmapConvertor(a);
                } else {
                    this.f = new BinaryConvertor((byte[]) obj);
                }
            } else if (obj instanceof Bitmap) {
                this.i = BITMAP_IMAGE;
                a = isHasWaterMark() ? a((Bitmap) obj, true) : null;
                if (a == null) {
                    a = (Bitmap) obj;
                }
                this.f = new BitmapConvertor(a);
            } else if (obj != null) {
                SLog.E(UmengText.IMAGE.UNKNOW_UMIMAGE + obj.getClass().getSimpleName());
            } else {
                SLog.E(UmengText.IMAGE.UNKNOW_UMIMAGE + "null");
            }
        }
    }

    @Override // com.umeng.socialize.media.UMediaObject
    public byte[] toByte() {
        return asBinImage();
    }

    @Override // com.umeng.socialize.media.BaseMediaObject
    public void setThumb(UMImage uMImage) {
        this.g = uMImage;
    }

    @Override // com.umeng.socialize.media.BaseMediaObject
    public UMImage getThumbImage() {
        return this.g;
    }

    @Override // com.umeng.socialize.media.UMediaObject
    public final Map<String, Object> toUrlExtraParams() {
        HashMap hashMap = new HashMap();
        if (isUrlMedia()) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FURL, this.a);
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FTYPE, getMediaType());
        }
        return hashMap;
    }

    @Override // com.umeng.socialize.media.UMediaObject
    public UMediaObject.MediaType getMediaType() {
        return UMediaObject.MediaType.IMAGE;
    }

    public int getImageStyle() {
        return this.i;
    }

    public File asFileImage() {
        if (this.f == null) {
            return null;
        }
        return this.f.asFile();
    }

    public String asUrlImage() {
        if (this.f == null) {
            return null;
        }
        return this.f.asUrl();
    }

    public byte[] asBinImage() {
        if (this.f == null) {
            return null;
        }
        return this.f.asBinary();
    }

    public Bitmap asBitmap() {
        if (this.f == null) {
            return null;
        }
        return this.f.asBitmap();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class BitmapConvertor extends ConfiguredConvertor {
        private Bitmap b;

        @Override // com.umeng.socialize.media.UMImage.IImageConvertor
        public String asUrl() {
            return null;
        }

        public BitmapConvertor(Bitmap bitmap) {
            this.b = bitmap;
        }

        @Override // com.umeng.socialize.media.UMImage.IImageConvertor
        public File asFile() {
            byte[] a = a.a(this.b, UMImage.this.compressFormat);
            if (SocializeUtils.assertBinaryInvalid(asBinary())) {
                return a.b(a);
            }
            return null;
        }

        @Override // com.umeng.socialize.media.UMImage.IImageConvertor
        public byte[] asBinary() {
            return a.a(this.b, UMImage.this.compressFormat);
        }

        @Override // com.umeng.socialize.media.UMImage.IImageConvertor
        public Bitmap asBitmap() {
            return this.b;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class FileConvertor extends ConfiguredConvertor {
        private File b;

        @Override // com.umeng.socialize.media.UMImage.IImageConvertor
        public String asUrl() {
            return null;
        }

        public FileConvertor(File file) {
            this.b = file;
        }

        @Override // com.umeng.socialize.media.UMImage.IImageConvertor
        public File asFile() {
            return this.b;
        }

        @Override // com.umeng.socialize.media.UMImage.IImageConvertor
        public byte[] asBinary() {
            return a.a(this.b, UMImage.this.compressFormat);
        }

        @Override // com.umeng.socialize.media.UMImage.IImageConvertor
        public Bitmap asBitmap() {
            if (SocializeUtils.assertBinaryInvalid(asBinary())) {
                return a.a(UMImage.this.asBinImage());
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class UrlConvertor extends ConfiguredConvertor {
        private String b;

        public UrlConvertor(String str) {
            this.b = null;
            this.b = str;
        }

        @Override // com.umeng.socialize.media.UMImage.IImageConvertor
        public File asFile() {
            if (SocializeUtils.assertBinaryInvalid(asBinary())) {
                return a.b(asBinary());
            }
            return null;
        }

        @Override // com.umeng.socialize.media.UMImage.IImageConvertor
        public String asUrl() {
            return this.b;
        }

        @Override // com.umeng.socialize.media.UMImage.IImageConvertor
        public byte[] asBinary() {
            return a.a(this.b);
        }

        @Override // com.umeng.socialize.media.UMImage.IImageConvertor
        public Bitmap asBitmap() {
            if (SocializeUtils.assertBinaryInvalid(asBinary())) {
                return a.a(asBinary());
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class BinaryConvertor extends ConfiguredConvertor {
        private byte[] b;

        @Override // com.umeng.socialize.media.UMImage.IImageConvertor
        public String asUrl() {
            return null;
        }

        public BinaryConvertor(byte[] bArr) {
            this.b = bArr;
        }

        @Override // com.umeng.socialize.media.UMImage.IImageConvertor
        public File asFile() {
            if (SocializeUtils.assertBinaryInvalid(asBinary())) {
                return a.b(asBinary());
            }
            return null;
        }

        @Override // com.umeng.socialize.media.UMImage.IImageConvertor
        public byte[] asBinary() {
            return this.b;
        }

        @Override // com.umeng.socialize.media.UMImage.IImageConvertor
        public Bitmap asBitmap() {
            if (SocializeUtils.assertBinaryInvalid(asBinary())) {
                return a.a(asBinary());
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ResConvertor extends ConfiguredConvertor {
        private Context b;
        private int c;

        @Override // com.umeng.socialize.media.UMImage.IImageConvertor
        public String asUrl() {
            return null;
        }

        public ResConvertor(Context context, int i) {
            this.c = 0;
            this.b = context;
            this.c = i;
        }

        @Override // com.umeng.socialize.media.UMImage.IImageConvertor
        public File asFile() {
            if (SocializeUtils.assertBinaryInvalid(asBinary())) {
                return a.b(asBinary());
            }
            return null;
        }

        @Override // com.umeng.socialize.media.UMImage.IImageConvertor
        public byte[] asBinary() {
            return a.a(this.b, this.c, UMImage.this.isLoadImgByCompress, UMImage.this.compressFormat);
        }

        @Override // com.umeng.socialize.media.UMImage.IImageConvertor
        public Bitmap asBitmap() {
            if (SocializeUtils.assertBinaryInvalid(asBinary())) {
                return a.a(asBinary());
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class ConfiguredConvertor implements IImageConvertor {
        ConfiguredConvertor() {
        }
    }

    private Bitmap a(Bitmap bitmap, boolean z) {
        if (this.h == null) {
            return bitmap;
        }
        if (bitmap == null) {
            return null;
        }
        if (z) {
            try {
                bitmap = a(bitmap);
            } catch (Exception e) {
                SLog.error(e);
                return null;
            }
        }
        return this.h.compound(bitmap);
    }

    private Bitmap a(Context context, int i) {
        InputStream inputStream;
        InputStream inputStream2;
        if (i != 0 && context != null) {
            try {
                if (this.h != null) {
                    try {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        inputStream2 = context.getResources().openRawResource(i);
                        try {
                            BitmapFactory.decodeStream(inputStream2, null, options);
                            a(inputStream2);
                            int a = (int) a(options.outWidth, options.outHeight, MAX_WIDTH, MAX_HEIGHT);
                            if (a > 0) {
                                options.inSampleSize = a;
                            }
                            options.inJustDecodeBounds = false;
                            InputStream openRawResource = context.getResources().openRawResource(i);
                            try {
                                Bitmap a2 = a(BitmapFactory.decodeStream(openRawResource, null, options), false);
                                a(openRawResource);
                                return a2;
                            } catch (Exception e) {
                                inputStream2 = openRawResource;
                                e = e;
                                SLog.error(e);
                                a(inputStream2);
                                return null;
                            } catch (Throwable th) {
                                inputStream = openRawResource;
                                th = th;
                                a(inputStream);
                                throw th;
                            }
                        } catch (Exception e2) {
                            e = e2;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        inputStream2 = null;
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream = null;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        return null;
    }

    private void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                SLog.error(e);
            }
        }
    }

    private Bitmap a(byte[] bArr) {
        if (bArr == null || this.h == null) {
            return null;
        }
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            int a = (int) a(options.outWidth, options.outHeight, MAX_WIDTH, MAX_HEIGHT);
            if (a > 0) {
                options.inSampleSize = a;
            }
            options.inJustDecodeBounds = false;
            return a(BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options), false);
        } catch (Exception e) {
            SLog.error(e);
            return null;
        }
    }

    private Bitmap a(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float a = a(width, height, MAX_WIDTH, MAX_HEIGHT);
        if (a < 0.0f) {
            return bitmap;
        }
        float f = 1.0f / a;
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        b(bitmap);
        return createBitmap;
    }

    private void b(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                if (bitmap.isRecycled()) {
                    return;
                }
                bitmap.recycle();
            } catch (Exception e) {
                SLog.error(e);
            }
        }
    }

    public boolean isHasWaterMark() {
        return this.j;
    }
}