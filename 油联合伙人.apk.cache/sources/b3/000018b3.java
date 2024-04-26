package com.yltx.oil.partner.modules.mine.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.widget.ZoomButton;

/* loaded from: classes.dex */
public class UpdatePwdActivity_ViewBinding implements Unbinder {
    private UpdatePwdActivity target;

    @UiThread
    public UpdatePwdActivity_ViewBinding(UpdatePwdActivity updatePwdActivity) {
        this(updatePwdActivity, updatePwdActivity.getWindow().getDecorView());
    }

    @UiThread
    public UpdatePwdActivity_ViewBinding(UpdatePwdActivity updatePwdActivity, View view) {
        this.target = updatePwdActivity;
        updatePwdActivity.tvPhone = (EditText) Utils.findRequiredViewAsType(view, R.id.tv_phone, "field 'tvPhone'", EditText.class);
        updatePwdActivity.code = (ZoomButton) Utils.findRequiredViewAsType(view, R.id.code, "field 'code'", ZoomButton.class);
        updatePwdActivity.phoneSmsCode = (EditText) Utils.findRequiredViewAsType(view, R.id.phone_sms_code, "field 'phoneSmsCode'", EditText.class);
        updatePwdActivity.llSms = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sms, "field 'llSms'", LinearLayout.class);
        updatePwdActivity.tvNext = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_next, "field 'tvNext'", TextView.class);
        updatePwdActivity.activityChangePsdEt = (EditText) Utils.findRequiredViewAsType(view, R.id.activity_change_psd_et, "field 'activityChangePsdEt'", EditText.class);
        updatePwdActivity.activityChangePsdEtTwice = (EditText) Utils.findRequiredViewAsType(view, R.id.activity_change_psd_et_twice, "field 'activityChangePsdEtTwice'", EditText.class);
        updatePwdActivity.activityChangePsdLl = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.activity_change_psd_ll, "field 'activityChangePsdLl'", LinearLayout.class);
        updatePwdActivity.ivPw = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_pw, "field 'ivPw'", ImageView.class);
        updatePwdActivity.ivTwicepw = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_twicepw, "field 'ivTwicepw'", ImageView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        UpdatePwdActivity updatePwdActivity = this.target;
        if (updatePwdActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        updatePwdActivity.tvPhone = null;
        updatePwdActivity.code = null;
        updatePwdActivity.phoneSmsCode = null;
        updatePwdActivity.llSms = null;
        updatePwdActivity.tvNext = null;
        updatePwdActivity.activityChangePsdEt = null;
        updatePwdActivity.activityChangePsdEtTwice = null;
        updatePwdActivity.activityChangePsdLl = null;
        updatePwdActivity.ivPw = null;
        updatePwdActivity.ivTwicepw = null;
    }
}