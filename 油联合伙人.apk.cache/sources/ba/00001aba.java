package com.yltx.oil.partner.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/* loaded from: classes.dex */
public class MediaStoreCompat {
    private static final String MEDIA_FILE_EXTENSION = ".jpg";
    private static final String MEDIA_FILE_NAME_FORMAT = "yyyyMMdd_HHmmss";
    private static final String MEDIA_FILE_PREFIX = "IMG_";
    public static final String TAG = "MediaStoreCompat";
    private final String MEDIA_FILE_DIRECTORY;
    private Context mContext;
    private ContentObserver mObserver;
    private ArrayList<PhotoContent> mRecentlyUpdatedPhotos;

    public MediaStoreCompat(Context context, Handler handler) {
        this.mContext = context;
        this.MEDIA_FILE_DIRECTORY = context.getPackageName();
        this.mObserver = new ContentObserver(handler) { // from class: com.yltx.oil.partner.utils.MediaStoreCompat.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                super.onChange(z);
                MediaStoreCompat.this.updateLatestPhotos();
            }
        };
        this.mContext.getContentResolver().registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, this.mObserver);
    }

    public static final boolean hasCameraFeature(Context context) {
        return context.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.camera");
    }

    public String invokeCameraCapture(Activity activity, int i) {
        File outputFileUri;
        if (hasCameraFeature(this.mContext) && (outputFileUri = getOutputFileUri()) != null) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.putExtra("output", Uri.fromFile(outputFileUri));
            intent.putExtra("android.intent.extra.videoQuality", 1);
            activity.startActivityForResult(intent, i);
            return outputFileUri.toString();
        }
        return null;
    }

    public void destroy() {
        this.mContext.getContentResolver().unregisterContentObserver(this.mObserver);
    }

    public Uri getCapturedPhotoUri(Intent intent, String str) {
        Uri uri;
        if (intent != null) {
            uri = intent.getData();
            if (uri == null) {
                intent.getParcelableExtra("android.intent.extra.STREAM");
            }
        } else {
            uri = null;
        }
        File file = new File(str.toString());
        if (uri == null || uri.equals(Uri.fromFile(file))) {
            Uri findPhotoFromRecentlyTaken = findPhotoFromRecentlyTaken(file);
            if (findPhotoFromRecentlyTaken == null) {
                Uri storeImage = storeImage(file);
                file.delete();
                return storeImage;
            }
            String pathFromUri = getPathFromUri(this.mContext.getContentResolver(), findPhotoFromRecentlyTaken);
            if (pathFromUri == null || file.equals(new File(pathFromUri))) {
                return findPhotoFromRecentlyTaken;
            }
            file.delete();
            return findPhotoFromRecentlyTaken;
        }
        return uri;
    }

    public void cleanUp(String str) {
        File file = new File(str.toString());
        if (file.exists()) {
            file.delete();
        }
    }

    public static String getPathFromUri(ContentResolver contentResolver, Uri uri) {
        Cursor cursor;
        try {
            cursor = contentResolver.query(uri, new String[]{"_data"}, null, null, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        String string = cursor.getString(cursor.getColumnIndex("_data"));
                        if (cursor != null) {
                            cursor.close();
                        }
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
    }

    public static long copyFileStream(FileInputStream fileInputStream, FileOutputStream fileOutputStream) throws IOException {
        FileChannel fileChannel;
        Throwable th;
        FileChannel fileChannel2;
        try {
            fileChannel2 = fileInputStream.getChannel();
            try {
                fileChannel = fileOutputStream.getChannel();
            } catch (Throwable th2) {
                th = th2;
                fileChannel = null;
            }
        } catch (Throwable th3) {
            fileChannel = null;
            th = th3;
            fileChannel2 = null;
        }
        try {
            long transferTo = fileChannel2.transferTo(0L, fileChannel2.size(), fileChannel);
            if (fileChannel2 != null) {
                fileChannel2.close();
            }
            if (fileChannel != null) {
                fileChannel.close();
            }
            return transferTo;
        } catch (Throwable th4) {
            th = th4;
            if (fileChannel2 != null) {
                fileChannel2.close();
            }
            if (fileChannel != null) {
                fileChannel.close();
            }
            throw th;
        }
    }

    private Uri findPhotoFromRecentlyTaken(File file) {
        if (this.mRecentlyUpdatedPhotos == null) {
            updateLatestPhotos();
        }
        long length = file.length();
        long exifDateTimeInMillis = ExifInterfaceCompat.getExifDateTimeInMillis(file.getAbsolutePath());
        Iterator<PhotoContent> it = this.mRecentlyUpdatedPhotos.iterator();
        PhotoContent photoContent = null;
        int i = 0;
        while (it.hasNext()) {
            PhotoContent next = it.next();
            int i2 = ((long) next.size) == length ? 1 : 0;
            if (next.taken == exifDateTimeInMillis) {
                i2++;
            }
            if (i2 > i) {
                photoContent = next;
                i = i2;
            }
        }
        if (photoContent != null) {
            generateThumbnails(photoContent.id);
            return ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, photoContent.id);
        }
        return null;
    }

    private Uri storeImage(File file) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("title", file.getName());
            contentValues.put("mime_type", "image/jpeg");
            contentValues.put("description", "mixi Photo");
            contentValues.put("orientation", Integer.valueOf(ExifInterfaceCompat.getExifOrientation(file.getAbsolutePath())));
            long exifDateTimeInMillis = ExifInterfaceCompat.getExifDateTimeInMillis(file.getAbsolutePath());
            if (exifDateTimeInMillis != -1) {
                contentValues.put("datetaken", Long.valueOf(exifDateTimeInMillis));
            }
            Uri insert = this.mContext.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            FileOutputStream fileOutputStream = (FileOutputStream) this.mContext.getContentResolver().openOutputStream(insert);
            FileInputStream fileInputStream = new FileInputStream(file);
            copyFileStream(fileInputStream, fileOutputStream);
            fileOutputStream.close();
            fileInputStream.close();
            generateThumbnails(ContentUris.parseId(insert));
            return insert;
        } catch (Exception e) {
            Log.w(TAG, "cannot insert", e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLatestPhotos() {
        Cursor query = MediaStore.Images.Media.query(this.mContext.getContentResolver(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "datetaken", "_size"}, null, null, "date_added DESC");
        if (query != null) {
            try {
                this.mRecentlyUpdatedPhotos = new ArrayList<>();
                int i = 0;
                while (query.moveToNext()) {
                    PhotoContent photoContent = new PhotoContent();
                    photoContent.id = query.getLong(0);
                    photoContent.taken = query.getLong(1);
                    photoContent.size = query.getInt(2);
                    this.mRecentlyUpdatedPhotos.add(photoContent);
                    i++;
                    if (i > 5) {
                        break;
                    }
                }
            } finally {
                query.close();
            }
        }
    }

    private void generateThumbnails(long j) {
        try {
            MediaStore.Images.Thumbnails.getThumbnail(this.mContext.getContentResolver(), j, 1, null);
        } catch (NullPointerException unused) {
        }
    }

    @TargetApi(8)
    public File getOutputFileUri() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), this.MEDIA_FILE_DIRECTORY);
        if (file.exists() || file.mkdirs()) {
            String format = new SimpleDateFormat(MEDIA_FILE_NAME_FORMAT).format(new Date());
            return new File(file.getPath() + File.separator + MEDIA_FILE_PREFIX + format + MEDIA_FILE_EXTENSION);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class PhotoContent {
        public long id;
        public int size;
        public long taken;

        private PhotoContent() {
        }
    }
}