package me.zhanghai.android.materialprogressbar.internal;

import android.content.Context;
import android.content.res.TypedArray;

/* loaded from: classes.dex */
public class ThemeUtils {
    private ThemeUtils() {
    }

    public static int getColorFromAttrRes(int i, Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        try {
            return obtainStyledAttributes.getColor(0, 0);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static float getFloatFromAttrRes(int i, Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        try {
            return obtainStyledAttributes.getFloat(0, 0.0f);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }
}