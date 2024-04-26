package com.yltx.oil.partner.modules.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;
import java.util.List;

/* loaded from: classes.dex */
public class AdapterFragmentViewPager extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;
    private int mChildCount;

    public AdapterFragmentViewPager(FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager);
        this.mChildCount = 0;
        this.fragments = list;
    }

    @Override // android.support.v4.app.FragmentStatePagerAdapter
    public Fragment getItem(int i) {
        return this.fragments.get(i);
    }

    @Override // android.support.v4.view.PagerAdapter
    public void notifyDataSetChanged() {
        this.mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    public void addItems(List<Fragment> list) {
        this.fragments.addAll(list);
        notifyDataSetChanged();
    }

    @Override // android.support.v4.app.FragmentStatePagerAdapter, android.support.v4.view.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        return super.instantiateItem(viewGroup, i);
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getItemPosition(Object obj) {
        if (this.mChildCount > 0) {
            this.mChildCount--;
            return -2;
        }
        return super.getItemPosition(obj);
    }

    public List<Fragment> getFragments() {
        return this.fragments;
    }

    public void removeAll() {
        this.fragments.clear();
        notifyDataSetChanged();
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.fragments.size();
    }
}