package com.yltx.oil.partner.base;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class BaseListToolBarActivity_ViewBinding implements Unbinder {
    private BaseListToolBarActivity target;

    @UiThread
    public BaseListToolBarActivity_ViewBinding(BaseListToolBarActivity baseListToolBarActivity) {
        this(baseListToolBarActivity, baseListToolBarActivity.getWindow().getDecorView());
    }

    @UiThread
    public BaseListToolBarActivity_ViewBinding(BaseListToolBarActivity baseListToolBarActivity, View view) {
        this.target = baseListToolBarActivity;
        baseListToolBarActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
        baseListToolBarActivity.mSwipeRefreshLayout = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipe_refresh_layout, "field 'mSwipeRefreshLayout'", SwipeRefreshLayout.class);
        baseListToolBarActivity.empty_layout = Utils.findRequiredView(view, R.id.empty_layout, "field 'empty_layout'");
        baseListToolBarActivity.ll_no_use_ticket = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_no_use_ticket, "field 'll_no_use_ticket'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        BaseListToolBarActivity baseListToolBarActivity = this.target;
        if (baseListToolBarActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        baseListToolBarActivity.mRecyclerView = null;
        baseListToolBarActivity.mSwipeRefreshLayout = null;
        baseListToolBarActivity.empty_layout = null;
        baseListToolBarActivity.ll_no_use_ticket = null;
    }
}