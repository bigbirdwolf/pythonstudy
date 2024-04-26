package com.yltx.oil.partner.modules.profit.activity;

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
public class WithdrawActivity_ViewBinding implements Unbinder {
    private WithdrawActivity target;

    @UiThread
    public WithdrawActivity_ViewBinding(WithdrawActivity withdrawActivity) {
        this(withdrawActivity, withdrawActivity.getWindow().getDecorView());
    }

    @UiThread
    public WithdrawActivity_ViewBinding(WithdrawActivity withdrawActivity, View view) {
        this.target = withdrawActivity;
        withdrawActivity.tvXgyhk = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_xgyhk, "field 'tvXgyhk'", TextView.class);
        withdrawActivity.tvBankNo = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_bank_no, "field 'tvBankNo'", TextView.class);
        withdrawActivity.etInputMoney = (EditText) Utils.findRequiredViewAsType(view, R.id.et_input_money, "field 'etInputMoney'", EditText.class);
        withdrawActivity.tvAccountMoney = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_account_money, "field 'tvAccountMoney'", TextView.class);
        withdrawActivity.btnSubmit = (Button) Utils.findRequiredViewAsType(view, R.id.btn_submit, "field 'btnSubmit'", Button.class);
        withdrawActivity.tvTxjl = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_txjl, "field 'tvTxjl'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        WithdrawActivity withdrawActivity = this.target;
        if (withdrawActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        withdrawActivity.tvXgyhk = null;
        withdrawActivity.tvBankNo = null;
        withdrawActivity.etInputMoney = null;
        withdrawActivity.tvAccountMoney = null;
        withdrawActivity.btnSubmit = null;
        withdrawActivity.tvTxjl = null;
    }
}