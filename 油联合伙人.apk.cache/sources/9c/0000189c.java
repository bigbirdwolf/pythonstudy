package com.yltx.oil.partner.modules.mine.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class InviteCourtesyActivity_ViewBinding implements Unbinder {
    private InviteCourtesyActivity target;

    @UiThread
    public InviteCourtesyActivity_ViewBinding(InviteCourtesyActivity inviteCourtesyActivity) {
        this(inviteCourtesyActivity, inviteCourtesyActivity.getWindow().getDecorView());
    }

    @UiThread
    public InviteCourtesyActivity_ViewBinding(InviteCourtesyActivity inviteCourtesyActivity, View view) {
        this.target = inviteCourtesyActivity;
        inviteCourtesyActivity.btnYq = (Button) Utils.findRequiredViewAsType(view, R.id.btn_yq, "field 'btnYq'", Button.class);
        inviteCourtesyActivity.tvYqry = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_yqry, "field 'tvYqry'", TextView.class);
        inviteCourtesyActivity.tvYaoqmoney = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_yaoqmoney, "field 'tvYaoqmoney'", TextView.class);
        inviteCourtesyActivity.tvMx = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_mx, "field 'tvMx'", TextView.class);
        inviteCourtesyActivity.gz = (TextView) Utils.findRequiredViewAsType(view, R.id.gz, "field 'gz'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        InviteCourtesyActivity inviteCourtesyActivity = this.target;
        if (inviteCourtesyActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        inviteCourtesyActivity.btnYq = null;
        inviteCourtesyActivity.tvYqry = null;
        inviteCourtesyActivity.tvYaoqmoney = null;
        inviteCourtesyActivity.tvMx = null;
        inviteCourtesyActivity.gz = null;
    }
}