package com.afollestad.materialdialogs;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import com.afollestad.materialdialogs.internal.MDTintHelper;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class DefaultAdapter extends BaseAdapter {
    private final MaterialDialog dialog;
    private final GravityEnum itemGravity;
    @LayoutRes
    private final int layout;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    public DefaultAdapter(MaterialDialog materialDialog, @LayoutRes int i) {
        this.dialog = materialDialog;
        this.layout = i;
        this.itemGravity = materialDialog.mBuilder.itemsGravity;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.dialog.mBuilder.items != null) {
            return this.dialog.mBuilder.items.length;
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.dialog.mBuilder.items[i];
    }

    @Override // android.widget.Adapter
    @SuppressLint({"WrongViewCast"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.dialog.getContext()).inflate(this.layout, viewGroup, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.title);
        switch (this.dialog.listType) {
            case SINGLE:
                RadioButton radioButton = (RadioButton) view.findViewById(R.id.control);
                boolean z = this.dialog.mBuilder.selectedIndex == i;
                MDTintHelper.setTint(radioButton, this.dialog.mBuilder.widgetColor);
                radioButton.setChecked(z);
                break;
            case MULTI:
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.control);
                boolean contains = this.dialog.selectedIndicesList.contains(Integer.valueOf(i));
                MDTintHelper.setTint(checkBox, this.dialog.mBuilder.widgetColor);
                checkBox.setChecked(contains);
                break;
        }
        textView.setText(this.dialog.mBuilder.items[i]);
        textView.setTextColor(this.dialog.mBuilder.itemColor);
        this.dialog.setTypeface(textView, this.dialog.mBuilder.regularFont);
        view.setTag(i + ":" + ((Object) this.dialog.mBuilder.items[i]));
        ViewGroup viewGroup2 = (ViewGroup) view;
        setupGravity(viewGroup2);
        if (this.dialog.mBuilder.itemIds != null) {
            if (i < this.dialog.mBuilder.itemIds.length) {
                view.setId(this.dialog.mBuilder.itemIds[i]);
            } else {
                view.setId(-1);
            }
        }
        if (Build.VERSION.SDK_INT >= 21 && viewGroup2.getChildCount() == 2) {
            if (viewGroup2.getChildAt(0) instanceof CompoundButton) {
                viewGroup2.getChildAt(0).setBackground(null);
            } else if (viewGroup2.getChildAt(1) instanceof CompoundButton) {
                viewGroup2.getChildAt(1).setBackground(null);
            }
        }
        return view;
    }

    @TargetApi(17)
    private void setupGravity(ViewGroup viewGroup) {
        ((LinearLayout) viewGroup).setGravity(this.itemGravity.getGravityInt() | 16);
        if (viewGroup.getChildCount() == 2) {
            if (this.itemGravity == GravityEnum.END && !isRTL() && (viewGroup.getChildAt(0) instanceof CompoundButton)) {
                View view = (CompoundButton) viewGroup.getChildAt(0);
                viewGroup.removeView(view);
                TextView textView = (TextView) viewGroup.getChildAt(0);
                viewGroup.removeView(textView);
                textView.setPadding(textView.getPaddingRight(), textView.getPaddingTop(), textView.getPaddingLeft(), textView.getPaddingBottom());
                viewGroup.addView(textView);
                viewGroup.addView(view);
            } else if (this.itemGravity == GravityEnum.START && isRTL() && (viewGroup.getChildAt(1) instanceof CompoundButton)) {
                View view2 = (CompoundButton) viewGroup.getChildAt(1);
                viewGroup.removeView(view2);
                TextView textView2 = (TextView) viewGroup.getChildAt(0);
                viewGroup.removeView(textView2);
                textView2.setPadding(textView2.getPaddingRight(), textView2.getPaddingTop(), textView2.getPaddingRight(), textView2.getPaddingBottom());
                viewGroup.addView(view2);
                viewGroup.addView(textView2);
            }
        }
    }

    @TargetApi(17)
    private boolean isRTL() {
        return Build.VERSION.SDK_INT >= 17 && this.dialog.getBuilder().getContext().getResources().getConfiguration().getLayoutDirection() == 1;
    }
}