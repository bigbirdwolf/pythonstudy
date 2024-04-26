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
public class EffectiveorderFragment_ViewBinding implements Unbinder {
    private EffectiveorderFragment target;

    @UiThread
    public EffectiveorderFragment_ViewBinding(EffectiveorderFragment effectiveorderFragment, View view) {
        this.target = effectiveorderFragment;
        effectiveorderFragment.syDdmxSyddCzkk = (RadioButton) Utils.findRequiredViewAsType(view, R.id.sy_ddmx_sydd_czkk, "field 'syDdmxSyddCzkk'", RadioButton.class);
        effectiveorderFragment.syDdmxSyddJyk = (RadioButton) Utils.findRequiredViewAsType(view, R.id.sy_ddmx_sydd_jyk, "field 'syDdmxSyddJyk'", RadioButton.class);
        effectiveorderFragment.syDdmxSyddYpmy = (RadioButton) Utils.findRequiredViewAsType(view, R.id.sy_ddmx_sydd_ypmy, "field 'syDdmxSyddYpmy'", RadioButton.class);
        effectiveorderFragment.syDdmxSyddCzk = (RadioButton) Utils.findRequiredViewAsType(view, R.id.sy_ddmx_sydd_czk, "field 'syDdmxSyddCzk'", RadioButton.class);
        effectiveorderFragment.syDdmxSyddSp = (RadioButton) Utils.findRequiredViewAsType(view, R.id.sy_ddmx_sydd_sp, "field 'syDdmxSyddSp'", RadioButton.class);
        effectiveorderFragment.ddmxCycEff = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.ddmx_cyc_eff, "field 'ddmxCycEff'", RecyclerView.class);
        effectiveorderFragment.ddmxListlayoutEff = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.ddmx_listlayout_eff, "field 'ddmxListlayoutEff'", SwipeRefreshLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        EffectiveorderFragment effectiveorderFragment = this.target;
        if (effectiveorderFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        effectiveorderFragment.syDdmxSyddCzkk = null;
        effectiveorderFragment.syDdmxSyddJyk = null;
        effectiveorderFragment.syDdmxSyddYpmy = null;
        effectiveorderFragment.syDdmxSyddCzk = null;
        effectiveorderFragment.syDdmxSyddSp = null;
        effectiveorderFragment.ddmxCycEff = null;
        effectiveorderFragment.ddmxListlayoutEff = null;
    }
}