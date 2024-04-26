package com.yltx.oil.partner.injections.instrumentation;

import android.support.annotation.NonNull;
import com.yltx.oil.partner.injections.Preconditions;

/* loaded from: classes.dex */
public class DebugApplicationInstrumentation implements ApplicationInstrumentation {
    @NonNull
    private final StethoInstrumentation instrumentation;
    @NonNull
    private final HttpLoggingInstrumentation instrumentation1;

    public DebugApplicationInstrumentation(@NonNull StethoInstrumentation stethoInstrumentation, @NonNull HttpLoggingInstrumentation httpLoggingInstrumentation) {
        Preconditions.checkNotNull(stethoInstrumentation, "Instrumentation cannot be null.");
        Preconditions.checkNotNull(httpLoggingInstrumentation, "instrumentation1 cannot be null.");
        this.instrumentation = stethoInstrumentation;
        this.instrumentation1 = httpLoggingInstrumentation;
    }

    @Override // com.yltx.oil.partner.injections.instrumentation.Instrumentation
    public void init() {
        this.instrumentation.init();
        this.instrumentation1.init();
    }
}