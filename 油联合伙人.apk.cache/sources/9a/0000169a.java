package com.yltx.oil.partner.injections.instrumentation;

import android.content.Context;
import android.support.annotation.NonNull;
import com.yltx.oil.partner.injections.Preconditions;

/* loaded from: classes.dex */
public class PosInstrumentation implements ApplicationInstrumentation {
    @NonNull
    private final Context context;

    @Override // com.yltx.oil.partner.injections.instrumentation.Instrumentation
    public void init() {
    }

    public PosInstrumentation(@NonNull Context context) {
        Preconditions.checkNotNull(context, "Context cannot be null.");
        this.context = context;
    }
}