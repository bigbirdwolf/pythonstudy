package com.yltx.oil.partner.oss.glide;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.module.GlideModule;
import com.yltx.oil.partner.oss.glide.OSSUrlLoader;
import java.io.InputStream;

/* loaded from: classes.dex */
public class OSSGlideModule implements GlideModule {
    @Override // com.bumptech.glide.module.GlideModule
    public void applyOptions(Context context, GlideBuilder glideBuilder) {
    }

    @Override // com.bumptech.glide.module.GlideModule
    public void registerComponents(Context context, Glide glide) {
        glide.register(OSSFile.class, InputStream.class, new OSSUrlLoader.Factory());
    }
}