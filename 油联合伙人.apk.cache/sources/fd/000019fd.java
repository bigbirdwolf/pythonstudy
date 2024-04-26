package com.yltx.oil.partner.modules.profit.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class FragmentProfit_ViewBinding implements Unbinder {
    private FragmentProfit target;

    @UiThread
    public FragmentProfit_ViewBinding(FragmentProfit fragmentProfit, View view) {
        this.target = fragmentProfit;
        fragmentProfit.syRadioJr = (RadioButton) Utils.findRequiredViewAsType(view, R.id.sy_radio_jr, "field 'syRadioJr'", RadioButton.class);
        fragmentProfit.syRadioZr = (RadioButton) Utils.findRequiredViewAsType(view, R.id.sy_radio_zr, "field 'syRadioZr'", RadioButton.class);
        fragmentProfit.syRadioJqt = (RadioButton) Utils.findRequiredViewAsType(view, R.id.sy_radio_jqt, "field 'syRadioJqt'", RadioButton.class);
        fragmentProfit.syRadioJsst = (RadioButton) Utils.findRequiredViewAsType(view, R.id.sy_radio_jsst, "field 'syRadioJsst'", RadioButton.class);
        fragmentProfit.tvSybbDjcs = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sybb_djcs, "field 'tvSybbDjcs'", TextView.class);
        fragmentProfit.tvSybbXse = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sybb_xse, "field 'tvSybbXse'", TextView.class);
        fragmentProfit.tvSybbYxdds = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sybb_yxdds, "field 'tvSybbYxdds'", TextView.class);
        fragmentProfit.tvSybbYjsr = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sybb_yjsr, "field 'tvSybbYjsr'", TextView.class);
        fragmentProfit.syLinear = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.sy_linear, "field 'syLinear'", LinearLayout.class);
        fragmentProfit.syJrzsr = (TextView) Utils.findRequiredViewAsType(view, R.id.sy_jrzsr, "field 'syJrzsr'", TextView.class);
        fragmentProfit.tvSybb2Skbs = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sybb2_skbs, "field 'tvSybb2Skbs'", TextView.class);
        fragmentProfit.tvSybb2Tkbs = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sybb2_tkbs, "field 'tvSybb2Tkbs'", TextView.class);
        fragmentProfit.llTixian = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.ll_tixian, "field 'llTixian'", RelativeLayout.class);
        fragmentProfit.syYzjyfxClick = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.sy_yzjyfx_click, "field 'syYzjyfxClick'", RelativeLayout.class);
        fragmentProfit.syYjjsjlClick = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.sy_yjjsjl_click, "field 'syYjjsjlClick'", RelativeLayout.class);
        fragmentProfit.syYjsjfxClick = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.sy_yjsjfx_click, "field 'syYjsjfxClick'", RelativeLayout.class);
        fragmentProfit.syYjddmxClick = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.sy_yjddmx_click, "field 'syYjddmxClick'", RelativeLayout.class);
        fragmentProfit.yjjsjlFhjgsm = (TextView) Utils.findRequiredViewAsType(view, R.id.yjjsjl_fhjgsm, "field 'yjjsjlFhjgsm'", TextView.class);
        fragmentProfit.yjjsjlByyjsr = (TextView) Utils.findRequiredViewAsType(view, R.id.yjjsjl_byyjsr, "field 'yjjsjlByyjsr'", TextView.class);
        fragmentProfit.yjjsjlSyjssr = (TextView) Utils.findRequiredViewAsType(view, R.id.yjjsjl_syjssr, "field 'yjjsjlSyjssr'", TextView.class);
        fragmentProfit.tvZhanghuyue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_zhanghuyue, "field 'tvZhanghuyue'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        FragmentProfit fragmentProfit = this.target;
        if (fragmentProfit == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        fragmentProfit.syRadioJr = null;
        fragmentProfit.syRadioZr = null;
        fragmentProfit.syRadioJqt = null;
        fragmentProfit.syRadioJsst = null;
        fragmentProfit.tvSybbDjcs = null;
        fragmentProfit.tvSybbXse = null;
        fragmentProfit.tvSybbYxdds = null;
        fragmentProfit.tvSybbYjsr = null;
        fragmentProfit.syLinear = null;
        fragmentProfit.syJrzsr = null;
        fragmentProfit.tvSybb2Skbs = null;
        fragmentProfit.tvSybb2Tkbs = null;
        fragmentProfit.llTixian = null;
        fragmentProfit.syYzjyfxClick = null;
        fragmentProfit.syYjjsjlClick = null;
        fragmentProfit.syYjsjfxClick = null;
        fragmentProfit.syYjddmxClick = null;
        fragmentProfit.yjjsjlFhjgsm = null;
        fragmentProfit.yjjsjlByyjsr = null;
        fragmentProfit.yjjsjlSyjssr = null;
        fragmentProfit.tvZhanghuyue = null;
    }
}