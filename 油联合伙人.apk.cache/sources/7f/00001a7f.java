package com.yltx.oil.partner.oss.glide;

import android.content.Context;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;
import java.io.InputStream;

/* loaded from: classes.dex */
public class OSSUrlLoader extends BaseGlideUrlLoader<OSSFile> {
    public OSSUrlLoader(ModelLoader<GlideUrl, InputStream> modelLoader, ModelCache<OSSFile, GlideUrl> modelCache) {
        super(modelLoader, modelCache);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bumptech.glide.load.model.stream.BaseGlideUrlLoader
    public String getUrl(OSSFile oSSFile, int i, int i2) {
        return oSSFile.buildUrl(i, i2);
    }

    /* loaded from: classes.dex */
    static class Factory implements ModelLoaderFactory<OSSFile, InputStream> {
        private final ModelCache<OSSFile, GlideUrl> modelCache = new ModelCache<>(500);

        @Override // com.bumptech.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }

        @Override // com.bumptech.glide.load.model.ModelLoaderFactory
        public ModelLoader<OSSFile, InputStream> build(Context context, GenericLoaderFactory genericLoaderFactory) {
            return new OSSUrlLoader(genericLoaderFactory.buildModelLoader(GlideUrl.class, InputStream.class), this.modelCache);
        }
    }
}