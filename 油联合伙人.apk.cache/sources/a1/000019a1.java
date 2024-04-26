package com.yltx.oil.partner.modules.profit.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class OrderdetailsActivity_ViewBinding implements Unbinder {
    private OrderdetailsActivity target;

    @UiThread
    public OrderdetailsActivity_ViewBinding(OrderdetailsActivity orderdetailsActivity) {
        this(orderdetailsActivity, orderdetailsActivity.getWindow().getDecorView());
    }

    @UiThread
    public OrderdetailsActivity_ViewBinding(OrderdetailsActivity orderdetailsActivity, View view) {
        this.target = orderdetailsActivity;
        orderdetailsActivity.ddmxRadiobuttonSydd = (RadioButton) Utils.findRequiredViewAsType(view, R.id.ddmx_radiobutton_sydd, "field 'ddmxRadiobuttonSydd'", RadioButton.class);
        orderdetailsActivity.ddmxRadiobuttonYxdd = (RadioButton) Utils.findRequiredViewAsType(view, R.id.ddmx_radiobutton_yxdd, "field 'ddmxRadiobuttonYxdd'", RadioButton.class);
        orderdetailsActivity.ddmxRadiobuttonSxdd = (RadioButton) Utils.findRequiredViewAsType(view, R.id.ddmx_radiobutton_sxdd, "field 'ddmxRadiobuttonSxdd'", RadioButton.class);
        orderdetailsActivity.ddmxRadiogroup = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.ddmx_radiogroup, "field 'ddmxRadiogroup'", RadioGroup.class);
        orderdetailsActivity.ddmxViewpager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.ddmx_viewpager, "field 'ddmxViewpager'", ViewPager.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        OrderdetailsActivity orderdetailsActivity = this.target;
        if (orderdetailsActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        orderdetailsActivity.ddmxRadiobuttonSydd = null;
        orderdetailsActivity.ddmxRadiobuttonYxdd = null;
        orderdetailsActivity.ddmxRadiobuttonSxdd = null;
        orderdetailsActivity.ddmxRadiogroup = null;
        orderdetailsActivity.ddmxViewpager = null;
    }
}