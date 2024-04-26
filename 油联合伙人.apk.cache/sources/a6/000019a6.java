package com.yltx.oil.partner.modules.profit.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.BaseListToolBarActivity;
import com.yltx.oil.partner.base.RxBus;
import com.yltx.oil.partner.base.RxCanceOrderlEvent;
import com.yltx.oil.partner.data.response.TxHistoryResp;
import com.yltx.oil.partner.modules.home.adapter.TxHistoryAdapter;
import com.yltx.oil.partner.modules.profit.presenter.TxHistoryPresenter;
import com.yltx.oil.partner.mvp.views.PageLimitView;
import java.util.List;
import javax.inject.Inject;
import rx.Subscription;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class TxHistoryActivity extends BaseListToolBarActivity implements PageLimitView<TxHistoryResp>, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.RequestLoadMoreListener {
    private TxHistoryAdapter mAdapter;
    @Inject
    TxHistoryPresenter mPresenter;
    public TextView mTextContent;
    public TextView mTotalMoney;
    public SwipeRefreshLayout refreshLayout;
    public Subscription subscribe;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, TxHistoryActivity.class);
    }

    @Override // com.yltx.oil.partner.base.BaseListToolBarActivity, com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setupUI();
        bindListener();
        this.mPresenter.attachView(this);
        this.mPresenter.fetchFirst();
    }

    @Override // com.yltx.oil.partner.base.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mPresenter.onDestroy();
        this.subscribe.unsubscribe();
    }

    @Override // com.yltx.oil.partner.base.BaseListToolBarActivity
    protected void setupRecyclerView(RecyclerView recyclerView) {
        this.mAdapter = new TxHistoryAdapter(null);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.header_reimburse_histroy, (ViewGroup) null);
        this.mTotalMoney = (TextView) inflate.findViewById(R.id.tv_reimbursable_money);
        this.mTextContent = (TextView) inflate.findViewById(R.id.tv_content);
        this.mAdapter.addHeaderView(inflate);
        recyclerView.setAdapter(this.mAdapter);
        this.mAdapter.setOnLoadMoreListener(this, recyclerView);
        this.mAdapter.setOnItemChildClickListener(this);
    }

    private void setupUI() {
        setToolbarTitle("提现记录");
        this.mTextContent.setText("累计历史提现金额(元)");
        getToolbar().setBackground(getResources().getDrawable(R.color.titleBarColor));
        this.refreshLayout = getSwipeRefreshLayout();
        setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.yltx.oil.partner.modules.profit.activity.-$$Lambda$TxHistoryActivity$bTLDb5UP029PrG_MeRFZwg5HQz4
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                TxHistoryActivity.lambda$setupUI$0(TxHistoryActivity.this);
            }
        });
    }

    public static /* synthetic */ void lambda$setupUI$0(TxHistoryActivity txHistoryActivity) {
        txHistoryActivity.mPresenter.fetchTop();
    }

    private void bindListener() {
        this.subscribe = RxBus.getDefault().toObserverable(RxCanceOrderlEvent.class).subscribe(new Action1() { // from class: com.yltx.oil.partner.modules.profit.activity.-$$Lambda$TxHistoryActivity$IxOB07TAxEJ1JsrNigrzOTaf7QE
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                TxHistoryActivity.lambda$bindListener$1(TxHistoryActivity.this, (RxCanceOrderlEvent) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$1(TxHistoryActivity txHistoryActivity, RxCanceOrderlEvent rxCanceOrderlEvent) {
        txHistoryActivity.mPresenter.fetchTop();
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        ((TxHistoryResp.TxListBean) baseQuickAdapter.getData().get(i)).getRowId();
        view.getId();
    }

    private void setData(TxHistoryResp txHistoryResp) {
        if (txHistoryResp == null || txHistoryResp.getTxList().size() == 0) {
            this.mAdapter.loadMoreEnd();
            this.mAdapter.setEmptyView(R.layout.empty_layout);
        } else if (txHistoryResp.getTxList().size() < 10) {
            this.mAdapter.setEnableLoadMore(false);
            this.mAdapter.loadMoreEnd();
        } else {
            this.mAdapter.setEnableLoadMore(true);
            this.mAdapter.loadMoreComplete();
        }
        this.mAdapter.setNewData(txHistoryResp == null ? null : txHistoryResp.getTxList());
        this.mAdapter.disableLoadMoreIfNotFullPage();
    }

    private void setMoreData(TxHistoryResp txHistoryResp) {
        if (txHistoryResp.getTxList().size() < 10) {
            this.mAdapter.setEnableLoadMore(false);
            this.mAdapter.loadMoreEnd();
        } else {
            this.mAdapter.setEnableLoadMore(true);
            this.mAdapter.loadMoreComplete();
        }
        this.mAdapter.addData((List) txHistoryResp.getTxList());
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void render(TxHistoryResp txHistoryResp) {
        this.mTotalMoney.setText(txHistoryResp.getTotalMoney());
        setData(txHistoryResp);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onRefreshComplete(TxHistoryResp txHistoryResp) {
        this.mTotalMoney.setText(txHistoryResp.getTotalMoney());
        setData(txHistoryResp);
        setRefreshing(false);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onLoadMoreComplete(TxHistoryResp txHistoryResp) {
        setMoreData(txHistoryResp);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onRefreshError(String str) {
        setRefreshing(false);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onLoadMoreError(String str) {
        this.mAdapter.loadMoreFail();
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener
    public void onLoadMoreRequested() {
        this.mPresenter.fetchMore();
    }
}