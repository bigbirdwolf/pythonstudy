package com.bumptech.glide.load.model;

import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.data.DataFetcher;
import java.io.InputStream;

/* loaded from: classes.dex */
public class ImageVideoModelLoader<A> implements ModelLoader<A, ImageVideoWrapper> {
    private static final String TAG = "IVML";
    private final ModelLoader<A, ParcelFileDescriptor> fileDescriptorLoader;
    private final ModelLoader<A, InputStream> streamLoader;

    public ImageVideoModelLoader(ModelLoader<A, InputStream> modelLoader, ModelLoader<A, ParcelFileDescriptor> modelLoader2) {
        if (modelLoader == null && modelLoader2 == null) {
            throw new NullPointerException("At least one of streamLoader and fileDescriptorLoader must be non null");
        }
        this.streamLoader = modelLoader;
        this.fileDescriptorLoader = modelLoader2;
    }

    @Override // com.bumptech.glide.load.model.ModelLoader
    public DataFetcher<ImageVideoWrapper> getResourceFetcher(A a, int i, int i2) {
        DataFetcher<InputStream> resourceFetcher = this.streamLoader != null ? this.streamLoader.getResourceFetcher(a, i, i2) : null;
        DataFetcher<ParcelFileDescriptor> resourceFetcher2 = this.fileDescriptorLoader != null ? this.fileDescriptorLoader.getResourceFetcher(a, i, i2) : null;
        if (resourceFetcher == null && resourceFetcher2 == null) {
            return null;
        }
        return new ImageVideoFetcher(resourceFetcher, resourceFetcher2);
    }

    /* loaded from: classes.dex */
    static class ImageVideoFetcher implements DataFetcher<ImageVideoWrapper> {
        private final DataFetcher<ParcelFileDescriptor> fileDescriptorFetcher;
        private final DataFetcher<InputStream> streamFetcher;

        public ImageVideoFetcher(DataFetcher<InputStream> dataFetcher, DataFetcher<ParcelFileDescriptor> dataFetcher2) {
            this.streamFetcher = dataFetcher;
            this.fileDescriptorFetcher = dataFetcher2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Removed duplicated region for block: B:31:0x002a A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // com.bumptech.glide.load.data.DataFetcher
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.bumptech.glide.load.model.ImageVideoWrapper loadData(com.bumptech.glide.Priority r6) throws java.lang.Exception {
            /*
                r5 = this;
                com.bumptech.glide.load.data.DataFetcher<java.io.InputStream> r0 = r5.streamFetcher
                r1 = 2
                r2 = 0
                if (r0 == 0) goto L25
                com.bumptech.glide.load.data.DataFetcher<java.io.InputStream> r0 = r5.streamFetcher     // Catch: java.lang.Exception -> Lf
                java.lang.Object r0 = r0.loadData(r6)     // Catch: java.lang.Exception -> Lf
                java.io.InputStream r0 = (java.io.InputStream) r0     // Catch: java.lang.Exception -> Lf
                goto L26
            Lf:
                r0 = move-exception
                java.lang.String r3 = "IVML"
                boolean r3 = android.util.Log.isLoggable(r3, r1)
                if (r3 == 0) goto L1f
                java.lang.String r3 = "IVML"
                java.lang.String r4 = "Exception fetching input stream, trying ParcelFileDescriptor"
                android.util.Log.v(r3, r4, r0)
            L1f:
                com.bumptech.glide.load.data.DataFetcher<android.os.ParcelFileDescriptor> r3 = r5.fileDescriptorFetcher
                if (r3 == 0) goto L24
                goto L25
            L24:
                throw r0
            L25:
                r0 = r2
            L26:
                com.bumptech.glide.load.data.DataFetcher<android.os.ParcelFileDescriptor> r3 = r5.fileDescriptorFetcher
                if (r3 == 0) goto L47
                com.bumptech.glide.load.data.DataFetcher<android.os.ParcelFileDescriptor> r3 = r5.fileDescriptorFetcher     // Catch: java.lang.Exception -> L33
                java.lang.Object r6 = r3.loadData(r6)     // Catch: java.lang.Exception -> L33
                android.os.ParcelFileDescriptor r6 = (android.os.ParcelFileDescriptor) r6     // Catch: java.lang.Exception -> L33
                goto L48
            L33:
                r6 = move-exception
                java.lang.String r3 = "IVML"
                boolean r1 = android.util.Log.isLoggable(r3, r1)
                if (r1 == 0) goto L43
                java.lang.String r1 = "IVML"
                java.lang.String r3 = "Exception fetching ParcelFileDescriptor"
                android.util.Log.v(r1, r3, r6)
            L43:
                if (r0 == 0) goto L46
                goto L47
            L46:
                throw r6
            L47:
                r6 = r2
            L48:
                com.bumptech.glide.load.model.ImageVideoWrapper r1 = new com.bumptech.glide.load.model.ImageVideoWrapper
                r1.<init>(r0, r6)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.model.ImageVideoModelLoader.ImageVideoFetcher.loadData(com.bumptech.glide.Priority):com.bumptech.glide.load.model.ImageVideoWrapper");
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public void cleanup() {
            if (this.streamFetcher != null) {
                this.streamFetcher.cleanup();
            }
            if (this.fileDescriptorFetcher != null) {
                this.fileDescriptorFetcher.cleanup();
            }
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public String getId() {
            if (this.streamFetcher != null) {
                return this.streamFetcher.getId();
            }
            return this.fileDescriptorFetcher.getId();
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public void cancel() {
            if (this.streamFetcher != null) {
                this.streamFetcher.cancel();
            }
            if (this.fileDescriptorFetcher != null) {
                this.fileDescriptorFetcher.cancel();
            }
        }
    }
}