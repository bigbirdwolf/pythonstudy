package com.yltx.oil.partner.modules.home.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class FragmentHome_ViewBinding implements Unbinder {
    private FragmentHome target;

    @UiThread
    public FragmentHome_ViewBinding(FragmentHome fragmentHome, View view) {
        this.target = fragmentHome;
        fragmentHome.fragmentHomeShangp = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.fragment_home_shangp, "field 'fragmentHomeShangp'", RecyclerView.class);
        fragmentHome.slHome = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.sl_home, "field 'slHome'", SwipeRefreshLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        FragmentHome fragmentHome = this.target;
        if (fragmentHome == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        fragmentHome.fragmentHomeShangp = null;
        fragmentHome.slHome = null;
    }
}