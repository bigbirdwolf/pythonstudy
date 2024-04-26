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
public class RefuelingCardFragment_ViewBinding implements Unbinder {
    private RefuelingCardFragment target;

    @UiThread
    public RefuelingCardFragment_ViewBinding(RefuelingCardFragment refuelingCardFragment, View view) {
        this.target = refuelingCardFragment;
        refuelingCardFragment.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.fragment_oilcard_list, "field 'recyclerView'", RecyclerView.class);
        refuelingCardFragment.mRefreshLayout = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.SR_list, "field 'mRefreshLayout'", SwipeRefreshLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        RefuelingCardFragment refuelingCardFragment = this.target;
        if (refuelingCardFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        refuelingCardFragment.recyclerView = null;
        refuelingCardFragment.mRefreshLayout = null;
    }
}