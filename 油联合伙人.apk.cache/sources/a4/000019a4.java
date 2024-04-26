package com.yltx.oil.partner.modules.profit.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class SettlementrecordsActivity_ViewBinding implements Unbinder {
    private SettlementrecordsActivity target;

    @UiThread
    public SettlementrecordsActivity_ViewBinding(SettlementrecordsActivity settlementrecordsActivity) {
        this(settlementrecordsActivity, settlementrecordsActivity.getWindow().getDecorView());
    }

    @UiThread
    public SettlementrecordsActivity_ViewBinding(SettlementrecordsActivity settlementrecordsActivity, View view) {
        this.target = settlementrecordsActivity;
        settlementrecordsActivity.syJsjlRecyclerview = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.sy_jsjl_recyclerview, "field 'syJsjlRecyclerview'", RecyclerView.class);
        settlementrecordsActivity.jsjlListlayoutFai = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.jsjl_listlayout_fai, "field 'jsjlListlayoutFai'", SwipeRefreshLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        SettlementrecordsActivity settlementrecordsActivity = this.target;
        if (settlementrecordsActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        settlementrecordsActivity.syJsjlRecyclerview = null;
        settlementrecordsActivity.jsjlListlayoutFai = null;
    }
}