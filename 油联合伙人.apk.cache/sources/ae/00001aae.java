package com.yltx.oil.partner.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;

/* loaded from: classes.dex */
public class CustomRadioButton extends AppCompatRadioButton {
    private static final String TAG = "CustomRadioButton";

    public CustomRadioButton(Context context) {
        super(context);
    }

    public CustomRadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable drawable = getCompoundDrawables()[0];
        int width = getGravity() == 17 ? ((int) ((getWidth() - drawable.getIntrinsicWidth()) - getPaint().measureText(getText().toString()))) / 2 : 0;
        drawable.setBounds(width, 0, drawable.getIntrinsicWidth() + width, drawable.getIntrinsicHeight());
    }
}