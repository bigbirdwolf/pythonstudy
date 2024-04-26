package com.yltx.oil.partner.modules.home.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class CommoditySharingInforActivity_ViewBinding implements Unbinder {
    private CommoditySharingInforActivity target;

    @UiThread
    public CommoditySharingInforActivity_ViewBinding(CommoditySharingInforActivity commoditySharingInforActivity) {
        this(commoditySharingInforActivity, commoditySharingInforActivity.getWindow().getDecorView());
    }

    @UiThread
    public CommoditySharingInforActivity_ViewBinding(CommoditySharingInforActivity commoditySharingInforActivity, View view) {
        this.target = commoditySharingInforActivity;
        commoditySharingInforActivity.tvShare = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_share, "field 'tvShare'", TextView.class);
        commoditySharingInforActivity.ivIco = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_ico, "field 'ivIco'", ImageView.class);
        commoditySharingInforActivity.tvYuanjia = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_yuanjia, "field 'tvYuanjia'", TextView.class);
        commoditySharingInforActivity.tvLirun = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_lirun, "field 'tvLirun'", TextView.class);
        commoditySharingInforActivity.tvZhouqi = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_zhouqi, "field 'tvZhouqi'", TextView.class);
        commoditySharingInforActivity.tvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'tvTitle'", TextView.class);
        commoditySharingInforActivity.tvCzksy = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_czksy, "field 'tvCzksy'", TextView.class);
        commoditySharingInforActivity.tvFxz = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_fxz, "field 'tvFxz'", TextView.class);
        commoditySharingInforActivity.tvYjbl = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_yjbl, "field 'tvYjbl'", TextView.class);
        commoditySharingInforActivity.tvLr = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_lr, "field 'tvLr'", TextView.class);
        commoditySharingInforActivity.tvFxxq = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_fxxq, "field 'tvFxxq'", TextView.class);
        commoditySharingInforActivity.tv_hy = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_hy, "field 'tv_hy'", TextView.class);
        commoditySharingInforActivity.tvBuycount = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_buycount, "field 'tvBuycount'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        CommoditySharingInforActivity commoditySharingInforActivity = this.target;
        if (commoditySharingInforActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        commoditySharingInforActivity.tvShare = null;
        commoditySharingInforActivity.ivIco = null;
        commoditySharingInforActivity.tvYuanjia = null;
        commoditySharingInforActivity.tvLirun = null;
        commoditySharingInforActivity.tvZhouqi = null;
        commoditySharingInforActivity.tvTitle = null;
        commoditySharingInforActivity.tvCzksy = null;
        commoditySharingInforActivity.tvFxz = null;
        commoditySharingInforActivity.tvYjbl = null;
        commoditySharingInforActivity.tvLr = null;
        commoditySharingInforActivity.tvFxxq = null;
        commoditySharingInforActivity.tv_hy = null;
        commoditySharingInforActivity.tvBuycount = null;
    }
}