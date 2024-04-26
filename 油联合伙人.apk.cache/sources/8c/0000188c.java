package com.yltx.oil.partner.modules.mine.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class ComplaintActivity_ViewBinding implements Unbinder {
    private ComplaintActivity target;

    @UiThread
    public ComplaintActivity_ViewBinding(ComplaintActivity complaintActivity) {
        this(complaintActivity, complaintActivity.getWindow().getDecorView());
    }

    @UiThread
    public ComplaintActivity_ViewBinding(ComplaintActivity complaintActivity, View view) {
        this.target = complaintActivity;
        complaintActivity.etVoucherCode = (EditText) Utils.findRequiredViewAsType(view, R.id.et_voucherCode, "field 'etVoucherCode'", EditText.class);
        complaintActivity.tvQuxiao = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_quxiao, "field 'tvQuxiao'", TextView.class);
        complaintActivity.tvDaifaqi = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_daifaqi, "field 'tvDaifaqi'", TextView.class);
        complaintActivity.tvChulizhong = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_chulizhong, "field 'tvChulizhong'", TextView.class);
        complaintActivity.tvWancheng = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_wancheng, "field 'tvWancheng'", TextView.class);
        complaintActivity.rvList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_list, "field 'rvList'", RecyclerView.class);
        complaintActivity.tvJyk = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_jyk, "field 'tvJyk'", TextView.class);
        complaintActivity.tvYpmy = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ypmy, "field 'tvYpmy'", TextView.class);
        complaintActivity.tvCzk = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_czk, "field 'tvCzk'", TextView.class);
        complaintActivity.tvSp = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sp, "field 'tvSp'", TextView.class);
        complaintActivity.srlComplain = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.srl_complain, "field 'srlComplain'", SwipeRefreshLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ComplaintActivity complaintActivity = this.target;
        if (complaintActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        complaintActivity.etVoucherCode = null;
        complaintActivity.tvQuxiao = null;
        complaintActivity.tvDaifaqi = null;
        complaintActivity.tvChulizhong = null;
        complaintActivity.tvWancheng = null;
        complaintActivity.rvList = null;
        complaintActivity.tvJyk = null;
        complaintActivity.tvYpmy = null;
        complaintActivity.tvCzk = null;
        complaintActivity.tvSp = null;
        complaintActivity.srlComplain = null;
    }
}