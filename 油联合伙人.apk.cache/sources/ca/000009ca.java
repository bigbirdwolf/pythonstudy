package com.alipay.sdk.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.text.TextUtils;

/* loaded from: classes.dex */
public class e {
    private static boolean a;

    static {
        a = Build.VERSION.SDK_INT >= 11;
    }

    public static Dialog a(Context context, String str, String str2, String str3, DialogInterface.OnClickListener onClickListener, String str4, DialogInterface.OnClickListener onClickListener2) {
        AlertDialog.Builder a2 = a(context, str, str3, onClickListener, str4, onClickListener2);
        a2.setTitle(str);
        a2.setMessage(str2);
        AlertDialog create = a2.create();
        create.setCanceledOnTouchOutside(false);
        create.setOnKeyListener(new f());
        try {
            create.show();
        } catch (Throwable th) {
            com.alipay.sdk.util.c.a("msp", th);
        }
        return create;
    }

    private static AlertDialog.Builder a(Context context, String str, String str2, DialogInterface.OnClickListener onClickListener, String str3, DialogInterface.OnClickListener onClickListener2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (a) {
            if (!TextUtils.isEmpty(str3) && onClickListener2 != null) {
                builder.setPositiveButton(str3, onClickListener2);
            }
            if (!TextUtils.isEmpty(str2) && onClickListener != null) {
                builder.setNegativeButton(str2, onClickListener);
            }
        } else {
            if (!TextUtils.isEmpty(str2) && onClickListener != null) {
                builder.setPositiveButton(str2, onClickListener);
            }
            if (!TextUtils.isEmpty(str3) && onClickListener2 != null) {
                builder.setNegativeButton(str3, onClickListener2);
            }
        }
        return builder;
    }
}