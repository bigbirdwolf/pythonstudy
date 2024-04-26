package com.facebook.stetho;

import com.facebook.stetho.dumpapp.DumperPlugin;

/* loaded from: classes.dex */
public interface DumperPluginsProvider {
    Iterable<DumperPlugin> get();
}