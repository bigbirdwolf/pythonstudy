package com.afollestad.materialdialogs.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ArrayRes;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;

/* loaded from: classes.dex */
public class DialogUtils {
    public static int adjustAlpha(int i, float f) {
        return Color.argb(Math.round(Color.alpha(i) * f), Color.red(i), Color.green(i), Color.blue(i));
    }

    public static int resolveColor(Context context, @AttrRes int i) {
        return resolveColor(context, i, 0);
    }

    public static int resolveColor(Context context, @AttrRes int i, int i2) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i});
        try {
            return obtainStyledAttributes.getColor(0, i2);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static ColorStateList resolveActionTextColorStateList(Context context, @AttrRes int i, ColorStateList colorStateList) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i});
        try {
            TypedValue peekValue = obtainStyledAttributes.peekValue(0);
            if (peekValue == null) {
                return colorStateList;
            }
            if (peekValue.type >= 28 && peekValue.type <= 31) {
                return getActionTextStateList(context, peekValue.data);
            }
            ColorStateList colorStateList2 = obtainStyledAttributes.getColorStateList(0);
            return colorStateList2 != null ? colorStateList2 : colorStateList;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static ColorStateList getActionTextColorStateList(Context context, @ColorRes int i) {
        TypedValue typedValue = new TypedValue();
        context.getResources().getValue(i, typedValue, true);
        if (typedValue.type >= 28 && typedValue.type <= 31) {
            return getActionTextStateList(context, typedValue.data);
        }
        if (Build.VERSION.SDK_INT <= 22) {
            return context.getResources().getColorStateList(i);
        }
        return context.getColorStateList(i);
    }

    public static int getColor(Context context, @ColorRes int i) {
        if (Build.VERSION.SDK_INT <= 22) {
            return context.getResources().getColor(i);
        }
        return context.getColor(i);
    }

    public static String resolveString(Context context, @AttrRes int i) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return (String) typedValue.string;
    }

    private static int gravityEnumToAttrInt(GravityEnum gravityEnum) {
        switch (gravityEnum) {
            case CENTER:
                return 1;
            case END:
                return 2;
            default:
                return 0;
        }
    }

    public static GravityEnum resolveGravityEnum(Context context, @AttrRes int i, GravityEnum gravityEnum) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i});
        try {
            switch (obtainStyledAttributes.getInt(0, gravityEnumToAttrInt(gravityEnum))) {
                case 1:
                    return GravityEnum.CENTER;
                case 2:
                    return GravityEnum.END;
                default:
                    return GravityEnum.START;
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static Drawable resolveDrawable(Context context, @AttrRes int i) {
        return resolveDrawable(context, i, null);
    }

    private static Drawable resolveDrawable(Context context, @AttrRes int i, Drawable drawable) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i});
        try {
            Drawable drawable2 = obtainStyledAttributes.getDrawable(0);
            if (drawable2 == null && drawable != null) {
                drawable2 = drawable;
            }
            return drawable2;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static int resolveDimension(Context context, @AttrRes int i) {
        return resolveDimension(context, i, -1);
    }

    private static int resolveDimension(Context context, @AttrRes int i, int i2) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i});
        try {
            return obtainStyledAttributes.getDimensionPixelSize(0, i2);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static boolean resolveBoolean(Context context, @AttrRes int i, boolean z) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i});
        try {
            return obtainStyledAttributes.getBoolean(0, z);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static boolean resolveBoolean(Context context, @AttrRes int i) {
        return resolveBoolean(context, i, false);
    }

    public static boolean isColorDark(int i) {
        double red = Color.red(i);
        Double.isNaN(red);
        double green = Color.green(i);
        Double.isNaN(green);
        double d = (red * 0.299d) + (green * 0.587d);
        double blue = Color.blue(i);
        Double.isNaN(blue);
        return 1.0d - ((d + (blue * 0.114d)) / 255.0d) >= 0.5d;
    }

    public static void setBackgroundCompat(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT < 16) {
            view.setBackgroundDrawable(drawable);
        } else {
            view.setBackground(drawable);
        }
    }

    public static void showKeyboard(@NonNull DialogInterface dialogInterface, @NonNull final MaterialDialog.Builder builder) {
        final MaterialDialog materialDialog = (MaterialDialog) dialogInterface;
        if (materialDialog.getInputEditText() == null) {
            return;
        }
        materialDialog.getInputEditText().post(new Runnable() { // from class: com.afollestad.materialdialogs.util.DialogUtils.1
            @Override // java.lang.Runnable
            public void run() {
                MaterialDialog.this.getInputEditText().requestFocus();
                InputMethodManager inputMethodManager = (InputMethodManager) builder.getContext().getSystemService("input_method");
                if (inputMethodManager != null) {
                    inputMethodManager.showSoftInput(MaterialDialog.this.getInputEditText(), 2);
                }
            }
        });
    }

    public static void hideKeyboard(@NonNull DialogInterface dialogInterface, @NonNull MaterialDialog.Builder builder) {
        InputMethodManager inputMethodManager;
        MaterialDialog materialDialog = (MaterialDialog) dialogInterface;
        if (materialDialog.getInputEditText() == null || (inputMethodManager = (InputMethodManager) builder.getContext().getSystemService("input_method")) == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(materialDialog.getCurrentFocus().getWindowToken(), 0);
    }

    public static ColorStateList getActionTextStateList(Context context, int i) {
        int resolveColor = resolveColor(context, 16842806);
        if (i != 0) {
            resolveColor = i;
        }
        return new ColorStateList(new int[][]{new int[]{-16842910}, new int[0]}, new int[]{adjustAlpha(resolveColor, 0.4f), resolveColor});
    }

    public static int[] getColorArray(@NonNull Context context, @ArrayRes int i) {
        if (i == 0) {
            return null;
        }
        TypedArray obtainTypedArray = context.getResources().obtainTypedArray(i);
        int[] iArr = new int[obtainTypedArray.length()];
        for (int i2 = 0; i2 < obtainTypedArray.length(); i2++) {
            iArr[i2] = obtainTypedArray.getColor(i2, 0);
        }
        obtainTypedArray.recycle();
        return iArr;
    }
}