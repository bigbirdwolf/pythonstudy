package com.yltx.oil.partner.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;
import com.yltx.oil.partner.base.PartnerApplication;

/* loaded from: classes.dex */
public class DensityUtils {
    private DensityUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static int dp2px(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public static int dp2px(float f) {
        return (int) TypedValue.applyDimension(1, f, PartnerApplication.getInstance().getResources().getDisplayMetrics());
    }

    public static float dp2pxFloat(Context context, float f) {
        return TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public static int sp2px(Context context, float f) {
        return (int) TypedValue.applyDimension(2, f, context.getResources().getDisplayMetrics());
    }

    public static float px2dp(Context context, float f) {
        return f / context.getResources().getDisplayMetrics().density;
    }

    public static float px2sp(Context context, float f) {
        return f / context.getResources().getDisplayMetrics().scaledDensity;
    }

    public static int getChineseNums(String str) {
        return (str.getBytes().length - str.length()) / 2;
    }

    public static int getNoChineseNums(String str) {
        int length = str.getBytes().length;
        int length2 = str.length();
        return length2 - ((length - length2) / 2);
    }

    public static int[] getScreenSize(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels};
    }
}