package com.yltx.oil.partner.modules.mine.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class RechargeActivity_ViewBinding implements Unbinder {
    private RechargeActivity target;

    @UiThread
    public RechargeActivity_ViewBinding(RechargeActivity rechargeActivity) {
        this(rechargeActivity, rechargeActivity.getWindow().getDecorView());
    }

    @UiThread
    public RechargeActivity_ViewBinding(RechargeActivity rechargeActivity, View view) {
        this.target = rechargeActivity;
        rechargeActivity.et_my_input_money = (EditText) Utils.findRequiredViewAsType(view, R.id.et_my_input_money, "field 'et_my_input_money'", EditText.class);
        rechargeActivity.btn_my_submit = (Button) Utils.findRequiredViewAsType(view, R.id.btn_my_submit, "field 'btn_my_submit'", Button.class);
        rechargeActivity.tv_cjgz = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cjgz, "field 'tv_cjgz'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        RechargeActivity rechargeActivity = this.target;
        if (rechargeActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        rechargeActivity.et_my_input_money = null;
        rechargeActivity.btn_my_submit = null;
        rechargeActivity.tv_cjgz = null;
    }
}