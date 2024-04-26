package com.afollestad.materialdialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import com.afollestad.materialdialogs.internal.MDRootLayout;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class DialogBase extends Dialog implements DialogInterface.OnShowListener {
    private DialogInterface.OnShowListener mShowListener;
    protected MDRootLayout view;

    /* JADX INFO: Access modifiers changed from: protected */
    public DialogBase(Context context, int i) {
        super(context, i);
    }

    @Override // android.app.Dialog
    public View findViewById(int i) {
        return this.view.findViewById(i);
    }

    @Override // android.app.Dialog
    public final void setOnShowListener(DialogInterface.OnShowListener onShowListener) {
        this.mShowListener = onShowListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setOnShowListenerInternal() {
        super.setOnShowListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setViewInternal(View view) {
        super.setContentView(view);
    }

    @Override // android.content.DialogInterface.OnShowListener
    public void onShow(DialogInterface dialogInterface) {
        if (this.mShowListener != null) {
            this.mShowListener.onShow(dialogInterface);
        }
    }

    @Override // android.app.Dialog
    @Deprecated
    public void setContentView(int i) throws IllegalAccessError {
        throw new IllegalAccessError("setContentView() is not supported in MaterialDialog. Specify a custom view in the Builder instead.");
    }

    @Override // android.app.Dialog
    @Deprecated
    public void setContentView(View view) throws IllegalAccessError {
        throw new IllegalAccessError("setContentView() is not supported in MaterialDialog. Specify a custom view in the Builder instead.");
    }

    @Override // android.app.Dialog
    @Deprecated
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) throws IllegalAccessError {
        throw new IllegalAccessError("setContentView() is not supported in MaterialDialog. Specify a custom view in the Builder instead.");
    }
}