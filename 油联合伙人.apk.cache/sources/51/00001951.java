package com.yltx.oil.partner.modules.oiltrade.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class ShopFragment_ViewBinding implements Unbinder {
    private ShopFragment target;

    @UiThread
    public ShopFragment_ViewBinding(ShopFragment shopFragment, View view) {
        this.target = shopFragment;
        shopFragment.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.fragment_oilcard_list, "field 'recyclerView'", RecyclerView.class);
        shopFragment.mRefreshLayout = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.SR_list, "field 'mRefreshLayout'", SwipeRefreshLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ShopFragment shopFragment = this.target;
        if (shopFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        shopFragment.recyclerView = null;
        shopFragment.mRefreshLayout = null;
    }
}