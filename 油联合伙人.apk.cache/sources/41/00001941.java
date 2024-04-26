package com.yltx.oil.partner.modules.oiltrade.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class FragmentOilTrade_ViewBinding implements Unbinder {
    private FragmentOilTrade target;

    @UiThread
    public FragmentOilTrade_ViewBinding(FragmentOilTrade fragmentOilTrade, View view) {
        this.target = fragmentOilTrade;
        fragmentOilTrade.oiltrabeLayoutTitle = (TabLayout) Utils.findRequiredViewAsType(view, R.id.oiltrabe_layout_title, "field 'oiltrabeLayoutTitle'", TabLayout.class);
        fragmentOilTrade.vpOiltrade = (ViewPager) Utils.findRequiredViewAsType(view, R.id.vp_oiltrade, "field 'vpOiltrade'", ViewPager.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        FragmentOilTrade fragmentOilTrade = this.target;
        if (fragmentOilTrade == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        fragmentOilTrade.oiltrabeLayoutTitle = null;
        fragmentOilTrade.vpOiltrade = null;
    }
}