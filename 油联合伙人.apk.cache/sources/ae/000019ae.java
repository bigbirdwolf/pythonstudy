package com.yltx.oil.partner.modules.profit.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

/* loaded from: classes.dex */
public class Order_FragmentPager_Adapter extends FragmentPagerAdapter {
    List<Fragment> list;

    public Order_FragmentPager_Adapter(FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager);
        this.list = list;
    }

    @Override // android.support.v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        return this.list.get(i);
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.list.size();
    }
}