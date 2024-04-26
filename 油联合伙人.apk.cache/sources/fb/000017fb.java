package com.yltx.oil.partner.modules.login.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.widget.ZoomButton;

/* loaded from: classes.dex */
public class RegisterActivity_ViewBinding implements Unbinder {
    private RegisterActivity target;

    @UiThread
    public RegisterActivity_ViewBinding(RegisterActivity registerActivity) {
        this(registerActivity, registerActivity.getWindow().getDecorView());
    }

    @UiThread
    public RegisterActivity_ViewBinding(RegisterActivity registerActivity, View view) {
        this.target = registerActivity;
        registerActivity.llPhone = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_phone, "field 'llPhone'", LinearLayout.class);
        registerActivity.llXieyi = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_xieyi, "field 'llXieyi'", LinearLayout.class);
        registerActivity.llPw = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_pw, "field 'llPw'", LinearLayout.class);
        registerActivity.tvNext = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_next, "field 'tvNext'", TextView.class);
        registerActivity.tvSubmit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_submit, "field 'tvSubmit'", TextView.class);
        registerActivity.tvPhone = (EditText) Utils.findRequiredViewAsType(view, R.id.tv_phone, "field 'tvPhone'", EditText.class);
        registerActivity.etCode = (EditText) Utils.findRequiredViewAsType(view, R.id.et_code, "field 'etCode'", EditText.class);
        registerActivity.tvRegistValidCode = (ZoomButton) Utils.findRequiredViewAsType(view, R.id.tv_regist_validCode, "field 'tvRegistValidCode'", ZoomButton.class);
        registerActivity.etPw = (EditText) Utils.findRequiredViewAsType(view, R.id.et_pw, "field 'etPw'", EditText.class);
        registerActivity.ivPw = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_pw, "field 'ivPw'", ImageView.class);
        registerActivity.etSurepw = (EditText) Utils.findRequiredViewAsType(view, R.id.et_surepw, "field 'etSurepw'", EditText.class);
        registerActivity.ivSurepw = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_surepw, "field 'ivSurepw'", ImageView.class);
        registerActivity.rbAgree = (CheckBox) Utils.findRequiredViewAsType(view, R.id.rb_agree, "field 'rbAgree'", CheckBox.class);
        registerActivity.tvShoujih = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_shoujih, "field 'tvShoujih'", TextView.class);
        registerActivity.rbYes = (CheckBox) Utils.findRequiredViewAsType(view, R.id.rb_yes, "field 'rbYes'", CheckBox.class);
        registerActivity.textViewImageVerify = (TextView) Utils.findRequiredViewAsType(view, R.id.textView_image_verify, "field 'textViewImageVerify'", TextView.class);
        registerActivity.imageVerify = (ImageView) Utils.findRequiredViewAsType(view, R.id.image_verify, "field 'imageVerify'", ImageView.class);
        registerActivity.etVercodeVerify = (EditText) Utils.findRequiredViewAsType(view, R.id.et_vercode_verify, "field 'etVercodeVerify'", EditText.class);
        registerActivity.getSmsCodeBtnVerify = (Button) Utils.findRequiredViewAsType(view, R.id.get_sms_code_btn_verify, "field 'getSmsCodeBtnVerify'", Button.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        RegisterActivity registerActivity = this.target;
        if (registerActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        registerActivity.llPhone = null;
        registerActivity.llXieyi = null;
        registerActivity.llPw = null;
        registerActivity.tvNext = null;
        registerActivity.tvSubmit = null;
        registerActivity.tvPhone = null;
        registerActivity.etCode = null;
        registerActivity.tvRegistValidCode = null;
        registerActivity.etPw = null;
        registerActivity.ivPw = null;
        registerActivity.etSurepw = null;
        registerActivity.ivSurepw = null;
        registerActivity.rbAgree = null;
        registerActivity.tvShoujih = null;
        registerActivity.rbYes = null;
        registerActivity.textViewImageVerify = null;
        registerActivity.imageVerify = null;
        registerActivity.etVercodeVerify = null;
        registerActivity.getSmsCodeBtnVerify = null;
    }
}