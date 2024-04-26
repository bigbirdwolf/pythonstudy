package com.yltx.oil.partner.modules.profit.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class AccountdetailsActivity_ViewBinding implements Unbinder {
    private AccountdetailsActivity target;

    @UiThread
    public AccountdetailsActivity_ViewBinding(AccountdetailsActivity accountdetailsActivity) {
        this(accountdetailsActivity, accountdetailsActivity.getWindow().getDecorView());
    }

    @UiThread
    public AccountdetailsActivity_ViewBinding(AccountdetailsActivity accountdetailsActivity, View view) {
        this.target = accountdetailsActivity;
        accountdetailsActivity.tvReleaseMoney = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_release_money, "field 'tvReleaseMoney'", TextView.class);
        accountdetailsActivity.tvTixian = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_tixian, "field 'tvTixian'", TextView.class);
        accountdetailsActivity.tvCz = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cz, "field 'tvCz'", TextView.class);
        accountdetailsActivity.List = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recyc_list, "field 'List'", RecyclerView.class);
        accountdetailsActivity.mRefreshLayout = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.sl_layout, "field 'mRefreshLayout'", SwipeRefreshLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        AccountdetailsActivity accountdetailsActivity = this.target;
        if (accountdetailsActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        accountdetailsActivity.tvReleaseMoney = null;
        accountdetailsActivity.tvTixian = null;
        accountdetailsActivity.tvCz = null;
        accountdetailsActivity.List = null;
        accountdetailsActivity.mRefreshLayout = null;
    }
}