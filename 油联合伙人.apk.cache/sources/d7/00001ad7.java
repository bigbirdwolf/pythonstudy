package com.yltx.oil.partner.widget;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/* loaded from: classes.dex */
public class DialogViewHolder {
    private View mDialogView;
    private final SparseArray<View> mViews = new SparseArray<>();

    private DialogViewHolder(Context context, int i) {
        this.mDialogView = View.inflate(context, i, null);
    }

    public static DialogViewHolder get(Context context, int i) {
        return new DialogViewHolder(context, i);
    }

    public View getConvertView() {
        return this.mDialogView;
    }

    public DialogViewHolder setText(int i, CharSequence charSequence) {
        ((TextView) getView(i)).setText(charSequence);
        return this;
    }

    public DialogViewHolder setViewInViSible(int i) {
        ((TextView) getView(i)).setVisibility(4);
        return this;
    }

    public DialogViewHolder setViewViSible(int i) {
        ((TextView) getView(i)).setVisibility(0);
        return this;
    }

    public DialogViewHolder setViewGone(int i) {
        ((TextView) getView(i)).setVisibility(8);
        return this;
    }

    public DialogViewHolder setOnClick(int i, View.OnClickListener onClickListener) {
        getView(i).setOnClickListener(onClickListener);
        return this;
    }

    public <T extends View> T getView(int i) {
        T t = (T) this.mViews.get(i);
        if (t == null) {
            T t2 = (T) this.mDialogView.findViewById(i);
            this.mViews.put(i, t2);
            return t2;
        }
        return t;
    }
}