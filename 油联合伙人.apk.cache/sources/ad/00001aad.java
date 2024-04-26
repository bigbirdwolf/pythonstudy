package com.yltx.oil.partner.utils;

import android.content.Context;

/* loaded from: classes.dex */
public class ContextHolder {
    private static Context appContext;

    public static void initial(Context context) {
        appContext = context;
    }

    public static Context getContext() {
        return appContext;
    }
}