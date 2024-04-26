package com.yltx.oil.partner.modules.profit.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class AllFragment_ViewBinding implements Unbinder {
    private AllFragment target;

    @UiThread
    public AllFragment_ViewBinding(AllFragment allFragment, View view) {
        this.target = allFragment;
        allFragment.skjlRvList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.skjl_rv_list, "field 'skjlRvList'", RecyclerView.class);
        allFragment.skjlSrlLayout = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.skjl_srl_layout, "field 'skjlSrlLayout'", SwipeRefreshLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        AllFragment allFragment = this.target;
        if (allFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        allFragment.skjlRvList = null;
        allFragment.skjlSrlLayout = null;
    }
}