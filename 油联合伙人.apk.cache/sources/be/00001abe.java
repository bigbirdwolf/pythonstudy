package com.yltx.oil.partner.utils;

import android.app.ProgressDialog;
import android.content.Context;

/* loaded from: classes.dex */
public class ProgressDialogUtils {
    public static ProgressDialog loadDialog(Context context, String str) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(str);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
}