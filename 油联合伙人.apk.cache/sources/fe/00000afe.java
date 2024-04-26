package com.bumptech.glide.load.data;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.umeng.socialize.common.SocializeConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class MediaStoreThumbFetcher implements DataFetcher<InputStream> {
    private static final ThumbnailStreamOpenerFactory DEFAULT_FACTORY = new ThumbnailStreamOpenerFactory();
    private static final int MINI_HEIGHT = 384;
    private static final int MINI_WIDTH = 512;
    private static final String TAG = "MediaStoreThumbFetcher";
    private final Context context;
    private final DataFetcher<InputStream> defaultFetcher;
    private final ThumbnailStreamOpenerFactory factory;
    private final int height;
    private InputStream inputStream;
    private final Uri mediaStoreUri;
    private final int width;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface ThumbnailQuery {
        Cursor queryPath(Context context, Uri uri);
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public void cancel() {
    }

    public MediaStoreThumbFetcher(Context context, Uri uri, DataFetcher<InputStream> dataFetcher, int i, int i2) {
        this(context, uri, dataFetcher, i, i2, DEFAULT_FACTORY);
    }

    MediaStoreThumbFetcher(Context context, Uri uri, DataFetcher<InputStream> dataFetcher, int i, int i2, ThumbnailStreamOpenerFactory thumbnailStreamOpenerFactory) {
        this.context = context;
        this.mediaStoreUri = uri;
        this.defaultFetcher = dataFetcher;
        this.width = i;
        this.height = i2;
        this.factory = thumbnailStreamOpenerFactory;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.bumptech.glide.load.data.DataFetcher
    public InputStream loadData(Priority priority) throws Exception {
        ThumbnailStreamOpener build = this.factory.build(this.mediaStoreUri, this.width, this.height);
        if (build != null) {
            this.inputStream = openThumbInputStream(build);
        }
        if (this.inputStream == null) {
            this.inputStream = this.defaultFetcher.loadData(priority);
        }
        return this.inputStream;
    }

    private InputStream openThumbInputStream(ThumbnailStreamOpener thumbnailStreamOpener) {
        InputStream inputStream;
        try {
            inputStream = thumbnailStreamOpener.open(this.context, this.mediaStoreUri);
        } catch (FileNotFoundException e) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Failed to find thumbnail file", e);
            }
            inputStream = null;
        }
        int orientation = inputStream != null ? thumbnailStreamOpener.getOrientation(this.context, this.mediaStoreUri) : -1;
        return orientation != -1 ? new ExifOrientationStream(inputStream, orientation) : inputStream;
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public void cleanup() {
        if (this.inputStream != null) {
            try {
                this.inputStream.close();
            } catch (IOException unused) {
            }
        }
        this.defaultFetcher.cleanup();
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public String getId() {
        return this.mediaStoreUri.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isMediaStoreUri(Uri uri) {
        return uri != null && "content".equals(uri.getScheme()) && SocializeConstants.KEY_PLATFORM.equals(uri.getAuthority());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isMediaStoreVideo(Uri uri) {
        return isMediaStoreUri(uri) && uri.getPathSegments().contains("video");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class FileService {
        FileService() {
        }

        public boolean exists(File file) {
            return file.exists();
        }

        public long length(File file) {
            return file.length();
        }

        public File get(String str) {
            return new File(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class ThumbnailStreamOpener {
        private static final FileService DEFAULT_SERVICE = new FileService();
        private ThumbnailQuery query;
        private final FileService service;

        public ThumbnailStreamOpener(ThumbnailQuery thumbnailQuery) {
            this(DEFAULT_SERVICE, thumbnailQuery);
        }

        public ThumbnailStreamOpener(FileService fileService, ThumbnailQuery thumbnailQuery) {
            this.service = fileService;
            this.query = thumbnailQuery;
        }

        /* JADX WARN: Removed duplicated region for block: B:35:0x004a A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int getOrientation(android.content.Context r6, android.net.Uri r7) {
            /*
                r5 = this;
                r0 = 0
                android.content.ContentResolver r6 = r6.getContentResolver()     // Catch: java.lang.Throwable -> L1a java.io.IOException -> L1d
                java.io.InputStream r6 = r6.openInputStream(r7)     // Catch: java.lang.Throwable -> L1a java.io.IOException -> L1d
                com.bumptech.glide.load.resource.bitmap.ImageHeaderParser r0 = new com.bumptech.glide.load.resource.bitmap.ImageHeaderParser     // Catch: java.io.IOException -> L18 java.lang.Throwable -> L47
                r0.<init>(r6)     // Catch: java.io.IOException -> L18 java.lang.Throwable -> L47
                int r0 = r0.getOrientation()     // Catch: java.io.IOException -> L18 java.lang.Throwable -> L47
                if (r6 == 0) goto L46
                r6.close()     // Catch: java.io.IOException -> L46
                goto L46
            L18:
                r0 = move-exception
                goto L21
            L1a:
                r7 = move-exception
                r6 = r0
                goto L48
            L1d:
                r6 = move-exception
                r4 = r0
                r0 = r6
                r6 = r4
            L21:
                java.lang.String r1 = "MediaStoreThumbFetcher"
                r2 = 3
                boolean r1 = android.util.Log.isLoggable(r1, r2)     // Catch: java.lang.Throwable -> L47
                if (r1 == 0) goto L40
                java.lang.String r1 = "MediaStoreThumbFetcher"
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L47
                r2.<init>()     // Catch: java.lang.Throwable -> L47
                java.lang.String r3 = "Failed to open uri: "
                r2.append(r3)     // Catch: java.lang.Throwable -> L47
                r2.append(r7)     // Catch: java.lang.Throwable -> L47
                java.lang.String r7 = r2.toString()     // Catch: java.lang.Throwable -> L47
                android.util.Log.d(r1, r7, r0)     // Catch: java.lang.Throwable -> L47
            L40:
                if (r6 == 0) goto L45
                r6.close()     // Catch: java.io.IOException -> L45
            L45:
                r0 = -1
            L46:
                return r0
            L47:
                r7 = move-exception
            L48:
                if (r6 == 0) goto L4d
                r6.close()     // Catch: java.io.IOException -> L4d
            L4d:
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.data.MediaStoreThumbFetcher.ThumbnailStreamOpener.getOrientation(android.content.Context, android.net.Uri):int");
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x001e A[DONT_GENERATE] */
        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.io.InputStream open(android.content.Context r3, android.net.Uri r4) throws java.io.FileNotFoundException {
            /*
                r2 = this;
                com.bumptech.glide.load.data.MediaStoreThumbFetcher$ThumbnailQuery r0 = r2.query
                android.database.Cursor r4 = r0.queryPath(r3, r4)
                r0 = 0
                if (r4 == 0) goto L1b
                boolean r1 = r4.moveToFirst()     // Catch: java.lang.Throwable -> L14
                if (r1 == 0) goto L1b
                android.net.Uri r1 = r2.parseThumbUri(r4)     // Catch: java.lang.Throwable -> L14
                goto L1c
            L14:
                r3 = move-exception
                if (r4 == 0) goto L1a
                r4.close()
            L1a:
                throw r3
            L1b:
                r1 = r0
            L1c:
                if (r4 == 0) goto L21
                r4.close()
            L21:
                if (r1 == 0) goto L2b
                android.content.ContentResolver r3 = r3.getContentResolver()
                java.io.InputStream r0 = r3.openInputStream(r1)
            L2b:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.data.MediaStoreThumbFetcher.ThumbnailStreamOpener.open(android.content.Context, android.net.Uri):java.io.InputStream");
        }

        private Uri parseThumbUri(Cursor cursor) {
            String string = cursor.getString(0);
            if (!TextUtils.isEmpty(string)) {
                File file = this.service.get(string);
                if (this.service.exists(file) && this.service.length(file) > 0) {
                    return Uri.fromFile(file);
                }
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class ImageThumbnailQuery implements ThumbnailQuery {
        private static final String[] PATH_PROJECTION = {"_data"};
        private static final String PATH_SELECTION = "kind = 1 AND image_id = ?";

        ImageThumbnailQuery() {
        }

        @Override // com.bumptech.glide.load.data.MediaStoreThumbFetcher.ThumbnailQuery
        public Cursor queryPath(Context context, Uri uri) {
            return context.getContentResolver().query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, PATH_PROJECTION, PATH_SELECTION, new String[]{uri.getLastPathSegment()}, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class VideoThumbnailQuery implements ThumbnailQuery {
        private static final String[] PATH_PROJECTION = {"_data"};
        private static final String PATH_SELECTION = "kind = 1 AND video_id = ?";

        VideoThumbnailQuery() {
        }

        @Override // com.bumptech.glide.load.data.MediaStoreThumbFetcher.ThumbnailQuery
        public Cursor queryPath(Context context, Uri uri) {
            return context.getContentResolver().query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, PATH_PROJECTION, PATH_SELECTION, new String[]{uri.getLastPathSegment()}, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class ThumbnailStreamOpenerFactory {
        ThumbnailStreamOpenerFactory() {
        }

        public ThumbnailStreamOpener build(Uri uri, int i, int i2) {
            if (!MediaStoreThumbFetcher.isMediaStoreUri(uri) || i > 512 || i2 > MediaStoreThumbFetcher.MINI_HEIGHT) {
                return null;
            }
            if (MediaStoreThumbFetcher.isMediaStoreVideo(uri)) {
                return new ThumbnailStreamOpener(new VideoThumbnailQuery());
            }
            return new ThumbnailStreamOpener(new ImageThumbnailQuery());
        }
    }
}