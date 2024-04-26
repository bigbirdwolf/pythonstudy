package com.yltx.oil.partner.modules.profit.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class DataanalysisActivity_ViewBinding implements Unbinder {
    private DataanalysisActivity target;

    @UiThread
    public DataanalysisActivity_ViewBinding(DataanalysisActivity dataanalysisActivity) {
        this(dataanalysisActivity, dataanalysisActivity.getWindow().getDecorView());
    }

    @UiThread
    public DataanalysisActivity_ViewBinding(DataanalysisActivity dataanalysisActivity, View view) {
        this.target = dataanalysisActivity;
        dataanalysisActivity.syTgsjXzrqWx = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.sy_tgsj_xzrq_wx, "field 'syTgsjXzrqWx'", RelativeLayout.class);
        dataanalysisActivity.syTgsjXzrqXw = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.sy_tgsj_xzrq_xw, "field 'syTgsjXzrqXw'", RelativeLayout.class);
        dataanalysisActivity.syTgsjQbsjRecyclerview = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.sy_tgsj_qbsj_recyclerview, "field 'syTgsjQbsjRecyclerview'", RecyclerView.class);
        dataanalysisActivity.syTgsjQbsjQbWx = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.sy_tgsj_qbsj_qb_wx, "field 'syTgsjQbsjQbWx'", RelativeLayout.class);
        dataanalysisActivity.syTgsjQbsjQbXw = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.sy_tgsj_qbsj_qb_xw, "field 'syTgsjQbsjQbXw'", RelativeLayout.class);
        dataanalysisActivity.syTgsjXzrqTv = (TextView) Utils.findRequiredViewAsType(view, R.id.sy_tgsj_xzrq_tv, "field 'syTgsjXzrqTv'", TextView.class);
        dataanalysisActivity.SRListTgsj = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.SR_list_tgsj, "field 'SRListTgsj'", SwipeRefreshLayout.class);
        dataanalysisActivity.syTgsjDjcsShu = (TextView) Utils.findRequiredViewAsType(view, R.id.sy_tgsj_djcs_shu, "field 'syTgsjDjcsShu'", TextView.class);
        dataanalysisActivity.syTgsjXseShu = (TextView) Utils.findRequiredViewAsType(view, R.id.sy_tgsj_xse_shu, "field 'syTgsjXseShu'", TextView.class);
        dataanalysisActivity.syTgsjYxddsShu = (TextView) Utils.findRequiredViewAsType(view, R.id.sy_tgsj_yxdds_shu, "field 'syTgsjYxddsShu'", TextView.class);
        dataanalysisActivity.syTgsjYjsrShu = (TextView) Utils.findRequiredViewAsType(view, R.id.sy_tgsj_yjsr_shu, "field 'syTgsjYjsrShu'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        DataanalysisActivity dataanalysisActivity = this.target;
        if (dataanalysisActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dataanalysisActivity.syTgsjXzrqWx = null;
        dataanalysisActivity.syTgsjXzrqXw = null;
        dataanalysisActivity.syTgsjQbsjRecyclerview = null;
        dataanalysisActivity.syTgsjQbsjQbWx = null;
        dataanalysisActivity.syTgsjQbsjQbXw = null;
        dataanalysisActivity.syTgsjXzrqTv = null;
        dataanalysisActivity.SRListTgsj = null;
        dataanalysisActivity.syTgsjDjcsShu = null;
        dataanalysisActivity.syTgsjXseShu = null;
        dataanalysisActivity.syTgsjYxddsShu = null;
        dataanalysisActivity.syTgsjYjsrShu = null;
    }
}