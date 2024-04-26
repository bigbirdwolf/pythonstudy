package com.yltx.oil.partner.modules.login.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.widget.ZoomButton;

/* loaded from: classes.dex */
public class ForgetPasswordActivity_ViewBinding implements Unbinder {
    private ForgetPasswordActivity target;

    @UiThread
    public ForgetPasswordActivity_ViewBinding(ForgetPasswordActivity forgetPasswordActivity) {
        this(forgetPasswordActivity, forgetPasswordActivity.getWindow().getDecorView());
    }

    @UiThread
    public ForgetPasswordActivity_ViewBinding(ForgetPasswordActivity forgetPasswordActivity, View view) {
        this.target = forgetPasswordActivity;
        forgetPasswordActivity.tvPhone = (EditText) Utils.findRequiredViewAsType(view, R.id.tv_phone, "field 'tvPhone'", EditText.class);
        forgetPasswordActivity.tvForgetValidCode = (ZoomButton) Utils.findRequiredViewAsType(view, R.id.tv_forget_validCode, "field 'tvForgetValidCode'", ZoomButton.class);
        forgetPasswordActivity.etValidCode = (EditText) Utils.findRequiredViewAsType(view, R.id.et_valid_code, "field 'etValidCode'", EditText.class);
        forgetPasswordActivity.etNewPwd = (EditText) Utils.findRequiredViewAsType(view, R.id.et_new_pwd, "field 'etNewPwd'", EditText.class);
        forgetPasswordActivity.ivPw = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_pw, "field 'ivPw'", ImageView.class);
        forgetPasswordActivity.etConfirmPwd = (EditText) Utils.findRequiredViewAsType(view, R.id.et_confirm_pwd, "field 'etConfirmPwd'", EditText.class);
        forgetPasswordActivity.ivAgpw = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_agpw, "field 'ivAgpw'", ImageView.class);
        forgetPasswordActivity.btnComplete = (TextView) Utils.findRequiredViewAsType(view, R.id.btn_complete, "field 'btnComplete'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ForgetPasswordActivity forgetPasswordActivity = this.target;
        if (forgetPasswordActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        forgetPasswordActivity.tvPhone = null;
        forgetPasswordActivity.tvForgetValidCode = null;
        forgetPasswordActivity.etValidCode = null;
        forgetPasswordActivity.etNewPwd = null;
        forgetPasswordActivity.ivPw = null;
        forgetPasswordActivity.etConfirmPwd = null;
        forgetPasswordActivity.ivAgpw = null;
        forgetPasswordActivity.btnComplete = null;
    }
}