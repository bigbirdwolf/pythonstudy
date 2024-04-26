package com.yltx.oil.partner.widget;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public abstract class CommonDialog {
    private DialogViewHolder dilaogVh;
    public Dialog mDialog;
    private Window mDialogWindow;
    private View mRootView;

    public abstract void convert(DialogViewHolder dialogViewHolder);

    public CommonDialog(Context context, int i) {
        this.dilaogVh = DialogViewHolder.get(context, i);
        this.mRootView = this.dilaogVh.getConvertView();
        this.mDialog = new Dialog(context, R.style.commondialog);
        this.mDialog.setContentView(this.mRootView);
        this.mDialogWindow = this.mDialog.getWindow();
        convert(this.dilaogVh);
    }

    public static AlertDialog.Builder creatNormalDialogBuilder(Context context, String str, String str2) {
        return new AlertDialog.Builder(context).setTitle(str).setMessage(str2);
    }

    public CommonDialog showDialog() {
        if (this.mDialog != null && !this.mDialog.isShowing()) {
            this.mDialog.show();
        }
        return this;
    }

    public CommonDialog backgroundLight(double d) {
        if (d < 0.0d || d > 1.0d) {
            return this;
        }
        WindowManager.LayoutParams attributes = this.mDialogWindow.getAttributes();
        attributes.dimAmount = (float) d;
        this.mDialogWindow.setAttributes(attributes);
        return this;
    }

    @SuppressLint({"NewApi"})
    public CommonDialog fromBottomToMiddle() {
        this.mDialogWindow.setWindowAnimations(R.style.window_bottom_in_bottom_out);
        return this;
    }

    public CommonDialog fromBottom() {
        fromBottomToMiddle();
        this.mDialogWindow.setGravity(81);
        return this;
    }

    public CommonDialog fromLeftToMiddle() {
        this.mDialogWindow.setWindowAnimations(R.style.window_left_in_left_out);
        this.mDialogWindow.addFlags(2);
        this.mDialogWindow.setGravity(19);
        return this;
    }

    public CommonDialog fromRightToMiddle() {
        this.mDialogWindow.setWindowAnimations(R.style.window_right_in_right_out);
        this.mDialogWindow.addFlags(2);
        this.mDialogWindow.setGravity(5);
        return this;
    }

    public CommonDialog fromTop() {
        fromTopToMiddle();
        this.mDialogWindow.setGravity(49);
        return this;
    }

    public CommonDialog fromTopToMiddle() {
        this.mDialogWindow.setWindowAnimations(R.style.window_top_in_top_out);
        this.mDialogWindow.addFlags(2);
        return this;
    }

    public CommonDialog showDialog(@StyleRes int i) {
        this.mDialogWindow.setWindowAnimations(i);
        this.mDialog.show();
        return this;
    }

    public CommonDialog showPyqDialog() {
        this.mDialogWindow.setWindowAnimations(R.style.window_bottom_in_bottom_out);
        this.mDialogWindow.addFlags(2);
        WindowManager.LayoutParams attributes = this.mDialogWindow.getAttributes();
        attributes.width = this.mDialogWindow.getWindowManager().getDefaultDisplay().getWidth();
        this.mDialog.getWindow().setAttributes(attributes);
        this.mDialogWindow.setGravity(81);
        this.mDialog.show();
        return this;
    }

    public CommonDialog showDialog(boolean z) {
        this.mDialogWindow.setWindowAnimations(R.style.dialog_scale_animstyle);
        this.mDialog.show();
        return this;
    }

    public CommonDialog fullScreen() {
        WindowManager.LayoutParams attributes = this.mDialogWindow.getAttributes();
        attributes.height = -1;
        attributes.width = -1;
        this.mDialog.onWindowAttributesChanged(attributes);
        return this;
    }

    public CommonDialog setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
        this.mDialog.setOnKeyListener(onKeyListener);
        return this;
    }

    public CommonDialog fullWidth() {
        WindowManager.LayoutParams attributes = this.mDialogWindow.getAttributes();
        attributes.width = -1;
        this.mDialog.onWindowAttributesChanged(attributes);
        return this;
    }

    public CommonDialog fullHeight() {
        WindowManager.LayoutParams attributes = this.mDialogWindow.getAttributes();
        attributes.height = -1;
        this.mDialog.onWindowAttributesChanged(attributes);
        return this;
    }

    public CommonDialog setWidthAndHeight(int i, int i2) {
        WindowManager.LayoutParams attributes = this.mDialogWindow.getAttributes();
        attributes.width = i;
        attributes.height = i2;
        this.mDialog.onWindowAttributesChanged(attributes);
        return this;
    }

    public void cancelDialog() {
        if (this.mDialog == null || !this.mDialog.isShowing()) {
            return;
        }
        dismiss();
    }

    public void dismiss() {
        if (this.mDialog == null || !this.mDialog.isShowing()) {
            return;
        }
        this.mDialog.dismiss();
    }

    public CommonDialog setDialogDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.mDialog.setOnDismissListener(onDismissListener);
        return this;
    }

    public CommonDialog setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        this.mDialog.setOnCancelListener(onCancelListener);
        return this;
    }

    public CommonDialog setCancelAble(boolean z) {
        this.mDialog.setCancelable(z);
        return this;
    }

    public CommonDialog setCanceledOnTouchOutside(boolean z) {
        this.mDialog.setCanceledOnTouchOutside(z);
        return this;
    }

    public DialogViewHolder getHolder() {
        return this.dilaogVh;
    }
}