package com.yltx.oil.partner.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class DialogUtils {
    private Dialog mProgressDialog;

    public void showProgress(Context context) {
        if (this.mProgressDialog == null) {
            this.mProgressDialog = new Dialog(context, R.style.AppTheme_Dialogstyle);
            this.mProgressDialog.setCancelable(false);
            this.mProgressDialog.setCanceledOnTouchOutside(false);
        }
        this.mProgressDialog.show();
        View inflate = LayoutInflater.from(context).inflate(R.layout.custom_progressbar, (ViewGroup) null);
        Glide.with(context).load(Integer.valueOf((int) R.mipmap.loading)).asGif().into((ImageView) inflate.findViewById(R.id.loading_view));
        this.mProgressDialog.setContentView(inflate);
    }

    public void hideProgress() {
        if (this.mProgressDialog == null || !this.mProgressDialog.isShowing()) {
            return;
        }
        this.mProgressDialog.dismiss();
    }
}