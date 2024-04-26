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
public class ApplyingPartnerActivity_ViewBinding implements Unbinder {
    private ApplyingPartnerActivity target;

    @UiThread
    public ApplyingPartnerActivity_ViewBinding(ApplyingPartnerActivity applyingPartnerActivity) {
        this(applyingPartnerActivity, applyingPartnerActivity.getWindow().getDecorView());
    }

    @UiThread
    public ApplyingPartnerActivity_ViewBinding(ApplyingPartnerActivity applyingPartnerActivity, View view) {
        this.target = applyingPartnerActivity;
        applyingPartnerActivity.headLeftImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.head_left_image, "field 'headLeftImage'", ImageView.class);
        applyingPartnerActivity.headTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.head_title, "field 'headTitle'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ApplyingPartnerActivity applyingPartnerActivity = this.target;
        if (applyingPartnerActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        applyingPartnerActivity.headLeftImage = null;
        applyingPartnerActivity.headTitle = null;
    }
}