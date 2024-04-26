package com.yltx.oil.partner.modules.profit.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class AllordersFragment_ViewBinding implements Unbinder {
    private AllordersFragment target;

    @UiThread
    public AllordersFragment_ViewBinding(AllordersFragment allordersFragment, View view) {
        this.target = allordersFragment;
        allordersFragment.syDdmxSyddJyk = (RadioButton) Utils.findRequiredViewAsType(view, R.id.sy_ddmx_sydd_jyk, "field 'syDdmxSyddJyk'", RadioButton.class);
        allordersFragment.syDdmxSyddYpmy = (RadioButton) Utils.findRequiredViewAsType(view, R.id.sy_ddmx_sydd_ypmy, "field 'syDdmxSyddYpmy'", RadioButton.class);
        allordersFragment.syDdmxSyddCzk = (RadioButton) Utils.findRequiredViewAsType(view, R.id.sy_ddmx_sydd_czk, "field 'syDdmxSyddCzk'", RadioButton.class);
        allordersFragment.syDdmxSyddSp = (RadioButton) Utils.findRequiredViewAsType(view, R.id.sy_ddmx_sydd_sp, "field 'syDdmxSyddSp'", RadioButton.class);
        allordersFragment.syDdmxSyddCzkk = (RadioButton) Utils.findRequiredViewAsType(view, R.id.sy_ddmx_sydd_czkk, "field 'syDdmxSyddCzkk'", RadioButton.class);
        allordersFragment.ddmxRvList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.ddmx_rv_list, "field 'ddmxRvList'", RecyclerView.class);
        allordersFragment.ddmxSrlComplain = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.ddmx_srl_complain, "field 'ddmxSrlComplain'", SwipeRefreshLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        AllordersFragment allordersFragment = this.target;
        if (allordersFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        allordersFragment.syDdmxSyddJyk = null;
        allordersFragment.syDdmxSyddYpmy = null;
        allordersFragment.syDdmxSyddCzk = null;
        allordersFragment.syDdmxSyddSp = null;
        allordersFragment.syDdmxSyddCzkk = null;
        allordersFragment.ddmxRvList = null;
        allordersFragment.ddmxSrlComplain = null;
    }
}