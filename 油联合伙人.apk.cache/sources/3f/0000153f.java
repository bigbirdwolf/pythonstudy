package com.yltx.oil.partner.base;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.widget.ViewAnimator;

@CoordinatorLayout.DefaultBehavior(AppBarLayout.ScrollingViewBehavior.class)
/* loaded from: classes.dex */
public class BetterViewAnimator extends ViewAnimator {
    public BetterViewAnimator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setDisplayedChildId(int i) {
        if (getDisplayedChildId() != i) {
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                if (getChildAt(i2).getId() == i) {
                    setDisplayedChild(i2);
                    return;
                }
            }
            String resourceEntryName = getResources().getResourceEntryName(i);
            throw new IllegalArgumentException("No view with ID " + resourceEntryName);
        }
    }

    public int getDisplayedChildId() {
        return getChildAt(getDisplayedChild()).getId();
    }
}