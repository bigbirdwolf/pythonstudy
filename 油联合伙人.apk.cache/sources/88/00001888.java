package com.yltx.oil.partner.modules.mine.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class ComplainValetActivity_ViewBinding implements Unbinder {
    private ComplainValetActivity target;

    @UiThread
    public ComplainValetActivity_ViewBinding(ComplainValetActivity complainValetActivity) {
        this(complainValetActivity, complainValetActivity.getWindow().getDecorView());
    }

    @UiThread
    public ComplainValetActivity_ViewBinding(ComplainValetActivity complainValetActivity, View view) {
        this.target = complainValetActivity;
        complainValetActivity.bxImg = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.bx_img, "field 'bxImg'", RecyclerView.class);
        complainValetActivity.ivComplain = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.iv_complain, "field 'ivComplain'", LinearLayout.class);
        complainValetActivity.tvSub = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sub, "field 'tvSub'", TextView.class);
        complainValetActivity.tvReson = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_reson, "field 'tvReson'", TextView.class);
        complainValetActivity.etName = (EditText) Utils.findRequiredViewAsType(view, R.id.et_name, "field 'etName'", EditText.class);
        complainValetActivity.etPhone = (EditText) Utils.findRequiredViewAsType(view, R.id.et_phone, "field 'etPhone'", EditText.class);
        complainValetActivity.etContext = (EditText) Utils.findRequiredViewAsType(view, R.id.et_context, "field 'etContext'", EditText.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ComplainValetActivity complainValetActivity = this.target;
        if (complainValetActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        complainValetActivity.bxImg = null;
        complainValetActivity.ivComplain = null;
        complainValetActivity.tvSub = null;
        complainValetActivity.tvReson = null;
        complainValetActivity.etName = null;
        complainValetActivity.etPhone = null;
        complainValetActivity.etContext = null;
    }
}