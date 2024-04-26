package com.yltx.oil.partner.modules.oiltrade.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/* loaded from: classes.dex */
public class WrapContentHeightViewPager extends ViewPager {
    private int current;
    private int height;
    private boolean scrollble;

    public WrapContentHeightViewPager(Context context) {
        super(context);
        this.height = 0;
        this.scrollble = true;
    }

    public WrapContentHeightViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.height = 0;
        this.scrollble = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.view.ViewPager, android.view.View
    public void onMeasure(int i, int i2) {
        if (getChildCount() > this.current) {
            View childAt = getChildAt(this.current);
            childAt.measure(i, View.MeasureSpec.makeMeasureSpec(0, 0));
            this.height = childAt.getMeasuredHeight();
        }
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(this.height, 1073741824));
    }

    public void resetHeight(int i) {
        this.current = i;
        if (getChildCount() > i) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LinearLayout.LayoutParams(-1, this.height);
            } else {
                layoutParams.height = this.height;
            }
            setLayoutParams(layoutParams);
        }
    }
}