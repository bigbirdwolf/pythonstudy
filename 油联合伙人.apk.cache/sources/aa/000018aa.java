package com.yltx.oil.partner.modules.mine.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.widget.ZoomButton;

/* loaded from: classes.dex */
public class PhoneActivity_ViewBinding implements Unbinder {
    private PhoneActivity target;

    @UiThread
    public PhoneActivity_ViewBinding(PhoneActivity phoneActivity) {
        this(phoneActivity, phoneActivity.getWindow().getDecorView());
    }

    @UiThread
    public PhoneActivity_ViewBinding(PhoneActivity phoneActivity, View view) {
        this.target = phoneActivity;
        phoneActivity.tvPhone = (EditText) Utils.findRequiredViewAsType(view, R.id.tv_phone, "field 'tvPhone'", EditText.class);
        phoneActivity.code = (ZoomButton) Utils.findRequiredViewAsType(view, R.id.code, "field 'code'", ZoomButton.class);
        phoneActivity.phoneSmsCode = (EditText) Utils.findRequiredViewAsType(view, R.id.phone_sms_code, "field 'phoneSmsCode'", EditText.class);
        phoneActivity.llSms = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sms, "field 'llSms'", LinearLayout.class);
        phoneActivity.tvNext = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_next, "field 'tvNext'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        PhoneActivity phoneActivity = this.target;
        if (phoneActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        phoneActivity.tvPhone = null;
        phoneActivity.code = null;
        phoneActivity.phoneSmsCode = null;
        phoneActivity.llSms = null;
        phoneActivity.tvNext = null;
    }
}