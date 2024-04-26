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
public class OilCardFragment_ViewBinding implements Unbinder {
    private OilCardFragment target;

    @UiThread
    public OilCardFragment_ViewBinding(OilCardFragment oilCardFragment, View view) {
        this.target = oilCardFragment;
        oilCardFragment.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.fragment_oilcard_list, "field 'recyclerView'", RecyclerView.class);
        oilCardFragment.mRefreshLayout = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.SR_list, "field 'mRefreshLayout'", SwipeRefreshLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        OilCardFragment oilCardFragment = this.target;
        if (oilCardFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        oilCardFragment.recyclerView = null;
        oilCardFragment.mRefreshLayout = null;
    }
}