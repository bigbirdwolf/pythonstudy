package com.facebook.stetho;

import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;

/* loaded from: classes.dex */
public interface InspectorModulesProvider {
    Iterable<ChromeDevtoolsDomain> get();
}