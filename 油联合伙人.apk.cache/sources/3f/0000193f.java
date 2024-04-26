package com.yltx.oil.partner.modules.oiltrade.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.BaseFragment;
import com.yltx.oil.partner.modules.oiltrade.adapter.OiltradeTitleAdapter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class FragmentOilTrade extends BaseFragment {
    private String TAG = FragmentOilTrade.class.getName();
    private List<Fragment> fragmentList;
    private int index;
    boolean isVisibleToUser;
    OiltradeTitleAdapter mAdapter;
    @BindView(R.id.oiltrabe_layout_title)
    TabLayout oiltrabeLayoutTitle;
    Unbinder unbinder;
    @BindView(R.id.vp_oiltrade)
    ViewPager vpOiltrade;

    private void bindListener() {
    }

    @Override // com.yltx.oil.partner.base.BaseFragment
    protected int setupContentView() {
        return R.layout.fragment_oiltrade;
    }

    public static FragmentOilTrade newInstance() {
        Bundle bundle = new Bundle();
        FragmentOilTrade fragmentOilTrade = new FragmentOilTrade();
        fragmentOilTrade.setArguments(bundle);
        return fragmentOilTrade;
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.unbinder = ButterKnife.bind(this, onCreateView);
        setupUI();
        bindListener();
        return onCreateView;
    }

    @Override // android.support.v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.isVisibleToUser = z;
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
    }

    private void setupUI() {
        this.fragmentList = getFragments();
        this.mAdapter = new OiltradeTitleAdapter(getChildFragmentManager(), this.fragmentList);
        this.vpOiltrade.setAdapter(this.mAdapter);
        this.oiltrabeLayoutTitle.setupWithViewPager(this.vpOiltrade);
        this.vpOiltrade.setOffscreenPageLimit(this.fragmentList.size() - 1);
    }

    public void setindext(int i) {
        this.vpOiltrade.setCurrentItem(i);
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> arrayList = new ArrayList<>(3);
        arrayList.add(OilCardFragment.newInstance());
        arrayList.add(RefuelingCardFragment.newInstance());
        arrayList.add(GiftCardFragment.newInstance());
        arrayList.add(ShopFragment.newInstance());
        return arrayList;
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public void onStop() {
        super.onStop();
    }
}