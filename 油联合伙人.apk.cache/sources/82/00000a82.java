package com.bigkoo.convenientbanner.listener;

import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class CBPageChangeListener implements ViewPager.OnPageChangeListener {
    private ViewPager.OnPageChangeListener onPageChangeListener;
    private int[] page_indicatorId;
    private ArrayList<ImageView> pointViews;

    public CBPageChangeListener(ArrayList<ImageView> arrayList, int[] iArr) {
        this.pointViews = arrayList;
        this.page_indicatorId = iArr;
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        if (this.onPageChangeListener != null) {
            this.onPageChangeListener.onPageScrollStateChanged(i);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        if (this.onPageChangeListener != null) {
            this.onPageChangeListener.onPageScrolled(i, f, i2);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        for (int i2 = 0; i2 < this.pointViews.size(); i2++) {
            this.pointViews.get(i).setImageResource(this.page_indicatorId[1]);
            if (i != i2) {
                this.pointViews.get(i2).setImageResource(this.page_indicatorId[0]);
            }
        }
        if (this.onPageChangeListener != null) {
            this.onPageChangeListener.onPageSelected(i);
        }
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }
}