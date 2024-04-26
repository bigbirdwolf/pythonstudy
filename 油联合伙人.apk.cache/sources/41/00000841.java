package com.afollestad.materialdialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ArrayRes;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListAdapter;
import com.afollestad.materialdialogs.MaterialDialog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class AlertDialogWrapper {

    /* loaded from: classes.dex */
    public static class Builder {
        private final MaterialDialog.Builder builder;
        private DialogInterface.OnClickListener negativeDialogListener;
        private DialogInterface.OnClickListener neutralDialogListener;
        private DialogInterface.OnClickListener onClickListener;
        private DialogInterface.OnClickListener positiveDialogListener;

        public Builder(@NonNull Context context) {
            this.builder = new MaterialDialog.Builder(context);
        }

        public Builder autoDismiss(boolean z) {
            this.builder.autoDismiss(z);
            return this;
        }

        public Builder setMessage(@StringRes int i) {
            this.builder.content(i);
            return this;
        }

        public Builder setMessage(@NonNull CharSequence charSequence) {
            this.builder.content(charSequence);
            return this;
        }

        public Builder setTitle(@StringRes int i) {
            this.builder.title(i);
            return this;
        }

        public Builder setTitle(@NonNull CharSequence charSequence) {
            this.builder.title(charSequence);
            return this;
        }

        public Builder setIcon(@DrawableRes int i) {
            this.builder.iconRes(i);
            return this;
        }

        public Builder setIcon(Drawable drawable) {
            this.builder.icon(drawable);
            return this;
        }

        public Builder setIconAttribute(@AttrRes int i) {
            this.builder.iconAttr(i);
            return this;
        }

        public Builder setNegativeButton(@StringRes int i, DialogInterface.OnClickListener onClickListener) {
            this.builder.negativeText(i);
            this.negativeDialogListener = onClickListener;
            return this;
        }

        public Builder setNegativeButton(@NonNull CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            this.builder.negativeText(charSequence);
            this.negativeDialogListener = onClickListener;
            return this;
        }

        public Builder setPositiveButton(@StringRes int i, DialogInterface.OnClickListener onClickListener) {
            this.builder.positiveText(i);
            this.positiveDialogListener = onClickListener;
            return this;
        }

        public Builder setPositiveButton(@NonNull CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            this.builder.positiveText(charSequence);
            this.positiveDialogListener = onClickListener;
            return this;
        }

        public Builder setNeutralButton(@StringRes int i, DialogInterface.OnClickListener onClickListener) {
            this.builder.neutralText(i);
            this.neutralDialogListener = onClickListener;
            return this;
        }

        public Builder setNeutralButton(@NonNull CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            this.builder.neutralText(charSequence);
            this.neutralDialogListener = onClickListener;
            return this;
        }

        public Builder setCancelable(boolean z) {
            this.builder.cancelable(z);
            return this;
        }

        public Builder setItems(@ArrayRes int i, DialogInterface.OnClickListener onClickListener) {
            this.builder.items(i);
            this.onClickListener = onClickListener;
            return this;
        }

        public Builder setItems(CharSequence[] charSequenceArr, DialogInterface.OnClickListener onClickListener) {
            this.builder.items(charSequenceArr);
            this.onClickListener = onClickListener;
            return this;
        }

        @Deprecated
        public Builder setAdapter(ListAdapter listAdapter) {
            return setAdapter(listAdapter, null);
        }

        public Builder setAdapter(ListAdapter listAdapter, final DialogInterface.OnClickListener onClickListener) {
            this.builder.adapter = listAdapter;
            this.builder.listCallbackCustom = new MaterialDialog.ListCallback() { // from class: com.afollestad.materialdialogs.AlertDialogWrapper.Builder.1
                @Override // com.afollestad.materialdialogs.MaterialDialog.ListCallback
                public void onSelection(MaterialDialog materialDialog, View view, int i, CharSequence charSequence) {
                    onClickListener.onClick(materialDialog, i);
                }
            };
            return this;
        }

        @UiThread
        public Dialog create() {
            addButtonsCallback();
            addListCallbacks();
            return this.builder.build();
        }

        @UiThread
        public Dialog show() {
            Dialog create = create();
            create.show();
            return create;
        }

        private void addListCallbacks() {
            if (this.onClickListener != null) {
                this.builder.itemsCallback(new MaterialDialog.ListCallback() { // from class: com.afollestad.materialdialogs.AlertDialogWrapper.Builder.2
                    @Override // com.afollestad.materialdialogs.MaterialDialog.ListCallback
                    public void onSelection(MaterialDialog materialDialog, View view, int i, CharSequence charSequence) {
                        Builder.this.onClickListener.onClick(materialDialog, i);
                    }
                });
            }
        }

        private void addButtonsCallback() {
            if (this.positiveDialogListener == null && this.negativeDialogListener == null) {
                return;
            }
            this.builder.callback(new MaterialDialog.ButtonCallback() { // from class: com.afollestad.materialdialogs.AlertDialogWrapper.Builder.3
                @Override // com.afollestad.materialdialogs.MaterialDialog.ButtonCallback
                public void onNeutral(MaterialDialog materialDialog) {
                    if (Builder.this.neutralDialogListener != null) {
                        Builder.this.neutralDialogListener.onClick(materialDialog, -3);
                    }
                }

                @Override // com.afollestad.materialdialogs.MaterialDialog.ButtonCallback
                public void onPositive(MaterialDialog materialDialog) {
                    if (Builder.this.positiveDialogListener != null) {
                        Builder.this.positiveDialogListener.onClick(materialDialog, -1);
                    }
                }

                @Override // com.afollestad.materialdialogs.MaterialDialog.ButtonCallback
                public void onNegative(MaterialDialog materialDialog) {
                    if (Builder.this.negativeDialogListener != null) {
                        Builder.this.negativeDialogListener.onClick(materialDialog, -2);
                    }
                }
            });
        }

        public Builder setView(@NonNull View view) {
            this.builder.customView(view, false);
            return this;
        }

        public Builder setMultiChoiceItems(@ArrayRes int i, @Nullable boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            this.builder.items(i);
            setUpMultiChoiceCallback(zArr, onMultiChoiceClickListener);
            return this;
        }

        public Builder setMultiChoiceItems(@NonNull String[] strArr, @Nullable boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            this.builder.items(strArr);
            setUpMultiChoiceCallback(zArr, onMultiChoiceClickListener);
            return this;
        }

        public Builder alwaysCallSingleChoiceCallback() {
            this.builder.alwaysCallSingleChoiceCallback();
            return this;
        }

        public Builder alwaysCallMultiChoiceCallback() {
            this.builder.alwaysCallMultiChoiceCallback();
            return this;
        }

        private void setUpMultiChoiceCallback(@Nullable final boolean[] zArr, final DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            Integer[] numArr;
            if (zArr != null) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < zArr.length; i++) {
                    if (zArr[i]) {
                        arrayList.add(Integer.valueOf(i));
                    }
                }
                numArr = (Integer[]) arrayList.toArray(new Integer[arrayList.size()]);
            } else {
                numArr = null;
            }
            this.builder.itemsCallbackMultiChoice(numArr, new MaterialDialog.ListCallbackMultiChoice() { // from class: com.afollestad.materialdialogs.AlertDialogWrapper.Builder.4
                @Override // com.afollestad.materialdialogs.MaterialDialog.ListCallbackMultiChoice
                public boolean onSelection(MaterialDialog materialDialog, Integer[] numArr2, CharSequence[] charSequenceArr) {
                    List asList = Arrays.asList(numArr2);
                    if (zArr != null) {
                        for (int i2 = 0; i2 < zArr.length; i2++) {
                            boolean z = zArr[i2];
                            zArr[i2] = asList.contains(Integer.valueOf(i2));
                            if (z != zArr[i2]) {
                                onMultiChoiceClickListener.onClick(materialDialog, i2, zArr[i2]);
                            }
                        }
                        return true;
                    }
                    return true;
                }
            });
        }

        public Builder setSingleChoiceItems(@NonNull String[] strArr, int i, final DialogInterface.OnClickListener onClickListener) {
            this.builder.items(strArr);
            this.builder.itemsCallbackSingleChoice(i, new MaterialDialog.ListCallbackSingleChoice() { // from class: com.afollestad.materialdialogs.AlertDialogWrapper.Builder.5
                @Override // com.afollestad.materialdialogs.MaterialDialog.ListCallbackSingleChoice
                public boolean onSelection(MaterialDialog materialDialog, View view, int i2, CharSequence charSequence) {
                    onClickListener.onClick(materialDialog, i2);
                    return true;
                }
            });
            return this;
        }

        public Builder setSingleChoiceItems(@ArrayRes int i, int i2, final DialogInterface.OnClickListener onClickListener) {
            this.builder.items(i);
            this.builder.itemsCallbackSingleChoice(i2, new MaterialDialog.ListCallbackSingleChoice() { // from class: com.afollestad.materialdialogs.AlertDialogWrapper.Builder.6
                @Override // com.afollestad.materialdialogs.MaterialDialog.ListCallbackSingleChoice
                public boolean onSelection(MaterialDialog materialDialog, View view, int i3, CharSequence charSequence) {
                    onClickListener.onClick(materialDialog, i3);
                    return true;
                }
            });
            return this;
        }

        public Builder setOnCancelListener(@NonNull DialogInterface.OnCancelListener onCancelListener) {
            this.builder.cancelListener(onCancelListener);
            return this;
        }

        public Builder setOnDismissListener(@NonNull DialogInterface.OnDismissListener onDismissListener) {
            this.builder.dismissListener(onDismissListener);
            return this;
        }

        public Builder setOnShowListener(@NonNull DialogInterface.OnShowListener onShowListener) {
            this.builder.showListener(onShowListener);
            return this;
        }

        public Builder setOnKeyListener(@NonNull DialogInterface.OnKeyListener onKeyListener) {
            this.builder.keyListener(onKeyListener);
            return this;
        }
    }
}