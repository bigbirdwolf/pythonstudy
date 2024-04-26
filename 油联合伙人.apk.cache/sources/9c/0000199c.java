package com.yltx.oil.partner.modules.profit.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class OptiondateActivity_ViewBinding implements Unbinder {
    private OptiondateActivity target;

    @UiThread
    public OptiondateActivity_ViewBinding(OptiondateActivity optiondateActivity) {
        this(optiondateActivity, optiondateActivity.getWindow().getDecorView());
    }

    @UiThread
    public OptiondateActivity_ViewBinding(OptiondateActivity optiondateActivity, View view) {
        this.target = optiondateActivity;
        optiondateActivity.headLeftImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.head_left_image, "field 'headLeftImage'", ImageView.class);
        optiondateActivity.headTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.head_title, "field 'headTitle'", TextView.class);
        optiondateActivity.syXzsjBt = (Button) Utils.findRequiredViewAsType(view, R.id.sy_xzsj_bt, "field 'syXzsjBt'", Button.class);
        optiondateActivity.tvXzsjKssjNyr = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_xzsj_kssj_nyr, "field 'tvXzsjKssjNyr'", TextView.class);
        optiondateActivity.syXzsjKssjWu = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.sy_xzsj_kssj_wu, "field 'syXzsjKssjWu'", RelativeLayout.class);
        optiondateActivity.tvXzsjJssjNyr = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_xzsj_jssj_nyr, "field 'tvXzsjJssjNyr'", TextView.class);
        optiondateActivity.syXzsjJssjWu = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.sy_xzsj_jssj_wu, "field 'syXzsjJssjWu'", RelativeLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        OptiondateActivity optiondateActivity = this.target;
        if (optiondateActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        optiondateActivity.headLeftImage = null;
        optiondateActivity.headTitle = null;
        optiondateActivity.syXzsjBt = null;
        optiondateActivity.tvXzsjKssjNyr = null;
        optiondateActivity.syXzsjKssjWu = null;
        optiondateActivity.tvXzsjJssjNyr = null;
        optiondateActivity.syXzsjJssjWu = null;
    }
}