package com.yltx.oil.partner.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public abstract class BaseListToolBarActivity extends ToolBarActivity {
    @BindView(R.id.empty_layout)
    View empty_layout;
    @BindView(R.id.ll_no_use_ticket)
    LinearLayout ll_no_use_ticket;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    protected abstract void setupRecyclerView(RecyclerView recyclerView);

    public LinearLayout getLl_no_use_ticket() {
        return this.ll_no_use_ticket;
    }

    public void setLl_no_use_ticket(LinearLayout linearLayout) {
        this.ll_no_use_ticket = linearLayout;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.fragment_common_recycler_view);
        ButterKnife.bind(this);
        this.mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimary, R.color.colorPrimary, R.color.colorPrimary);
        setupRecyclerView(this.mRecyclerView);
    }

    protected int getScrolledDistance(RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        View childAt = recyclerView.getChildAt(0);
        int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        return ((findFirstVisibleItemPosition + 1) * childAt.getHeight()) - linearLayoutManager.getDecoratedBottom(childAt);
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return this.mSwipeRefreshLayout;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        this.mSwipeRefreshLayout.setOnRefreshListener(onRefreshListener);
    }

    public View getEmptyLayout() {
        return this.empty_layout;
    }

    public void showEmptyLayout() {
        this.mRecyclerView.setVisibility(8);
        this.empty_layout.setVisibility(0);
    }

    public void hideEmptyLayout() {
        this.mRecyclerView.setVisibility(0);
        this.empty_layout.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setRefreshing(boolean z) {
        this.mSwipeRefreshLayout.setRefreshing(z);
    }

    protected void setEnableLoadMore(boolean z) {
        if (this.mRecyclerView.getAdapter() == null || !(this.mRecyclerView.getAdapter() instanceof BaseQuickAdapter)) {
            return;
        }
        ((BaseQuickAdapter) this.mRecyclerView.getAdapter()).setEnableLoadMore(z);
    }

    protected void showNoMoreData() {
        if (this.mRecyclerView.getAdapter() == null || !(this.mRecyclerView.getAdapter() instanceof BaseQuickAdapter)) {
            return;
        }
        ((BaseQuickAdapter) this.mRecyclerView.getAdapter()).loadMoreEnd();
    }

    protected void showLoadMoreFail() {
        if (this.mRecyclerView.getAdapter() == null || !(this.mRecyclerView.getAdapter() instanceof BaseQuickAdapter)) {
            return;
        }
        ((BaseQuickAdapter) this.mRecyclerView.getAdapter()).loadMoreFail();
    }

    protected void loadMoreComplete() {
        if (this.mRecyclerView.getAdapter() == null || !(this.mRecyclerView.getAdapter() instanceof BaseQuickAdapter)) {
            return;
        }
        ((BaseQuickAdapter) this.mRecyclerView.getAdapter()).loadMoreComplete();
    }
}