package com.yltx.oil.partner.modules.profit.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.widget.ZoomButton;

/* loaded from: classes.dex */
public class BindingbankcardsActivity_ViewBinding implements Unbinder {
    private BindingbankcardsActivity target;

    @UiThread
    public BindingbankcardsActivity_ViewBinding(BindingbankcardsActivity bindingbankcardsActivity) {
        this(bindingbankcardsActivity, bindingbankcardsActivity.getWindow().getDecorView());
    }

    @UiThread
    public BindingbankcardsActivity_ViewBinding(BindingbankcardsActivity bindingbankcardsActivity, View view) {
        this.target = bindingbankcardsActivity;
        bindingbankcardsActivity.tvPhone = (EditText) Utils.findRequiredViewAsType(view, R.id.tv_phone, "field 'tvPhone'", EditText.class);
        bindingbankcardsActivity.tvGetValidCode = (ZoomButton) Utils.findRequiredViewAsType(view, R.id.tv_get_validCode, "field 'tvGetValidCode'", ZoomButton.class);
        bindingbankcardsActivity.btnComplete = (TextView) Utils.findRequiredViewAsType(view, R.id.btn_complete, "field 'btnComplete'", TextView.class);
        bindingbankcardsActivity.etPhone = (EditText) Utils.findRequiredViewAsType(view, R.id.et_phone, "field 'etPhone'", EditText.class);
        bindingbankcardsActivity.etName = (EditText) Utils.findRequiredViewAsType(view, R.id.et_name, "field 'etName'", EditText.class);
        bindingbankcardsActivity.etIdcard = (EditText) Utils.findRequiredViewAsType(view, R.id.et_idcard, "field 'etIdcard'", EditText.class);
        bindingbankcardsActivity.etCardno = (EditText) Utils.findRequiredViewAsType(view, R.id.et_cardno, "field 'etCardno'", EditText.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        BindingbankcardsActivity bindingbankcardsActivity = this.target;
        if (bindingbankcardsActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        bindingbankcardsActivity.tvPhone = null;
        bindingbankcardsActivity.tvGetValidCode = null;
        bindingbankcardsActivity.btnComplete = null;
        bindingbankcardsActivity.etPhone = null;
        bindingbankcardsActivity.etName = null;
        bindingbankcardsActivity.etIdcard = null;
        bindingbankcardsActivity.etCardno = null;
    }
}