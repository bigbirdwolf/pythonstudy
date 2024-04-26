package com.bigkoo.convenientbanner.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.bigkoo.convenientbanner.R;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.view.CBLoopViewPager;
import java.util.List;

/* loaded from: classes.dex */
public class CBPageAdapter<T> extends PagerAdapter {
    protected CBViewHolderCreator holderCreator;
    protected List<T> mDatas;
    private CBLoopViewPager viewPager;
    private boolean canLoop = true;
    private final int MULTIPLE_COUNT = 300;

    @Override // android.support.v4.view.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public int toRealPosition(int i) {
        int realCount = getRealCount();
        if (realCount == 0) {
            return 0;
        }
        return i % realCount;
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.canLoop ? getRealCount() * 300 : getRealCount();
    }

    public int getRealCount() {
        if (this.mDatas == null) {
            return 0;
        }
        return this.mDatas.size();
    }

    @Override // android.support.v4.view.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = getView(toRealPosition(i), null, viewGroup);
        viewGroup.addView(view);
        return view;
    }

    @Override // android.support.v4.view.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    @Override // android.support.v4.view.PagerAdapter
    public void finishUpdate(ViewGroup viewGroup) {
        int currentItem = this.viewPager.getCurrentItem();
        if (currentItem == 0) {
            currentItem = this.viewPager.getFristItem();
        } else if (currentItem == getCount() - 1) {
            currentItem = this.viewPager.getLastItem();
        }
        try {
            this.viewPager.setCurrentItem(currentItem, false);
        } catch (IllegalStateException unused) {
        }
    }

    public void setCanLoop(boolean z) {
        this.canLoop = z;
    }

    public void setViewPager(CBLoopViewPager cBLoopViewPager) {
        this.viewPager = cBLoopViewPager;
    }

    public CBPageAdapter(CBViewHolderCreator cBViewHolderCreator, List<T> list) {
        this.holderCreator = cBViewHolderCreator;
        this.mDatas = list;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        Holder holder;
        if (view == null) {
            holder = (Holder) this.holderCreator.createHolder();
            view2 = holder.createView(viewGroup.getContext());
            view2.setTag(R.id.cb_item_tag, holder);
        } else {
            view2 = view;
            holder = (Holder) view.getTag(R.id.cb_item_tag);
        }
        if (this.mDatas != null && !this.mDatas.isEmpty()) {
            holder.UpdateUI(viewGroup.getContext(), i, this.mDatas.get(i));
        }
        return view2;
    }
}