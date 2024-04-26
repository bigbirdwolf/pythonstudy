package com.yltx.oil.partner.modules.oiltrade.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

/* loaded from: classes.dex */
public class OiltradeTitleAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] titlesList;

    public OiltradeTitleAdapter(FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager);
        this.titlesList = new String[]{"油品贸易", "加油卡", "礼品卡", "商品"};
        this.fragments = list;
    }

    @Override // android.support.v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        return this.fragments.get(i);
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.fragments.size();
    }

    @Override // android.support.v4.view.PagerAdapter
    public CharSequence getPageTitle(int i) {
        return this.titlesList[i];
    }
}