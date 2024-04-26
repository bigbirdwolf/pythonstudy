package com.yltx.oil.partner.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.ScaleAnimation;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class ZoomButton extends AppCompatButton {
    private static final float SCALE = 0.95f;
    private float scale;

    public ZoomButton(Context context) {
        this(context, null);
        this.scale = getScale(context, null, 0);
    }

    public ZoomButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.scale = getScale(context, attributeSet, 0);
    }

    public ZoomButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.scale = getScale(context, attributeSet, i);
    }

    private float getScale(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ZoomButton, i, 0);
        float f = obtainStyledAttributes.getFloat(0, SCALE);
        obtainStyledAttributes.recycle();
        return f;
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled()) {
            switch (motionEvent.getAction()) {
                case 0:
                    ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, this.scale, 1.0f, this.scale, 1, 0.5f, 1, 0.5f);
                    scaleAnimation.setDuration(100L);
                    scaleAnimation.setFillAfter(true);
                    setAnimation(scaleAnimation);
                    startAnimation(scaleAnimation);
                    break;
                case 1:
                    ScaleAnimation scaleAnimation2 = new ScaleAnimation(this.scale, 1.0f, this.scale, 1.0f, 1, 0.5f, 1, 0.5f);
                    scaleAnimation2.setDuration(100L);
                    scaleAnimation2.setFillAfter(true);
                    setAnimation(scaleAnimation2);
                    startAnimation(scaleAnimation2);
                    break;
            }
            return super.onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }
}