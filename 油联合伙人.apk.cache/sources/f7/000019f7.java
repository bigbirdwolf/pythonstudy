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
public class FailureoftheorderFragment_ViewBinding implements Unbinder {
    private FailureoftheorderFragment target;

    @UiThread
    public FailureoftheorderFragment_ViewBinding(FailureoftheorderFragment failureoftheorderFragment, View view) {
        this.target = failureoftheorderFragment;
        failureoftheorderFragment.ddmxCycFai = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.ddmx_cyc_fai, "field 'ddmxCycFai'", RecyclerView.class);
        failureoftheorderFragment.ddmxListlayoutFai = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.ddmx_listlayout_fai, "field 'ddmxListlayoutFai'", SwipeRefreshLayout.class);
        failureoftheorderFragment.syDdmxSyddCzkk = (RadioButton) Utils.findRequiredViewAsType(view, R.id.sy_ddmx_sydd_czkk, "field 'syDdmxSyddCzkk'", RadioButton.class);
        failureoftheorderFragment.syDdmxSyddJyk = (RadioButton) Utils.findRequiredViewAsType(view, R.id.sy_ddmx_sydd_jyk, "field 'syDdmxSyddJyk'", RadioButton.class);
        failureoftheorderFragment.syDdmxSyddYpmy = (RadioButton) Utils.findRequiredViewAsType(view, R.id.sy_ddmx_sydd_ypmy, "field 'syDdmxSyddYpmy'", RadioButton.class);
        failureoftheorderFragment.syDdmxSyddCzk = (RadioButton) Utils.findRequiredViewAsType(view, R.id.sy_ddmx_sydd_czk, "field 'syDdmxSyddCzk'", RadioButton.class);
        failureoftheorderFragment.syDdmxSyddSp = (RadioButton) Utils.findRequiredViewAsType(view, R.id.sy_ddmx_sydd_sp, "field 'syDdmxSyddSp'", RadioButton.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        FailureoftheorderFragment failureoftheorderFragment = this.target;
        if (failureoftheorderFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        failureoftheorderFragment.ddmxCycFai = null;
        failureoftheorderFragment.ddmxListlayoutFai = null;
        failureoftheorderFragment.syDdmxSyddCzkk = null;
        failureoftheorderFragment.syDdmxSyddJyk = null;
        failureoftheorderFragment.syDdmxSyddYpmy = null;
        failureoftheorderFragment.syDdmxSyddCzk = null;
        failureoftheorderFragment.syDdmxSyddSp = null;
    }
}