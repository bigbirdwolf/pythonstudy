package com.yltx.oil.partner.modules.profit.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.modules.profit.adapter.Sett_Recyclerview_Adapter;
import com.yltx.oil.partner.modules.profit.presenter.FragmentProfit_yjjsjl_Presenter;
import com.yltx.oil.partner.modules.profit.response.FragmentProfit_yjjsjl_Response;
import com.yltx.oil.partner.mvp.views.PageLimitView;
import com.yltx.oil.partner.utils.ToastUtil;
import java.util.List;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class SettlementrecordsActivity extends ToolBarActivity implements PageLimitView<FragmentProfit_yjjsjl_Response>, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.jsjl_listlayout_fai)
    SwipeRefreshLayout jsjlListlayoutFai;
    @Inject
    FragmentProfit_yjjsjl_Presenter profit_yjjsjl_presenter;
    Sett_Recyclerview_Adapter sett_recyclerview_adapter;
    @BindView(R.id.sy_jsjl_recyclerview)
    RecyclerView syJsjlRecyclerview;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, SettlementrecordsActivity.class);
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_settlementrecords);
        this.profit_yjjsjl_presenter.attachView(this);
        ButterKnife.bind(this);
        setupUI();
        bindListener();
    }

    private void setData(FragmentProfit_yjjsjl_Response fragmentProfit_yjjsjl_Response) {
        if (fragmentProfit_yjjsjl_Response == null || fragmentProfit_yjjsjl_Response.getRecord().size() == 0) {
            this.sett_recyclerview_adapter.loadMoreEnd();
        } else if (fragmentProfit_yjjsjl_Response.getRecord().size() < 10) {
            this.sett_recyclerview_adapter.setEnableLoadMore(false);
            this.sett_recyclerview_adapter.loadMoreEnd();
        } else {
            this.sett_recyclerview_adapter.setEnableLoadMore(true);
            this.sett_recyclerview_adapter.loadMoreComplete();
        }
        this.sett_recyclerview_adapter.setNewData(fragmentProfit_yjjsjl_Response.getRecord());
        this.sett_recyclerview_adapter.disableLoadMoreIfNotFullPage();
    }

    private void setMoreData(FragmentProfit_yjjsjl_Response fragmentProfit_yjjsjl_Response) {
        if (fragmentProfit_yjjsjl_Response.getRecord().size() < 10) {
            this.sett_recyclerview_adapter.setEnableLoadMore(false);
            this.sett_recyclerview_adapter.loadMoreEnd();
        } else {
            this.sett_recyclerview_adapter.setEnableLoadMore(true);
            this.sett_recyclerview_adapter.loadMoreComplete();
        }
        this.sett_recyclerview_adapter.addData((List) fragmentProfit_yjjsjl_Response.getRecord());
    }

    private void setupUI() {
        initRView();
        setToolbarTitle("结算记录");
        this.profit_yjjsjl_presenter.fetchFirst();
    }

    private void initRView() {
        this.sett_recyclerview_adapter = new Sett_Recyclerview_Adapter(null);
        this.syJsjlRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        this.syJsjlRecyclerview.setAdapter(this.sett_recyclerview_adapter);
        this.sett_recyclerview_adapter.setOnLoadMoreListener(this, this.syJsjlRecyclerview);
    }

    private void bindListener() {
        this.jsjlListlayoutFai.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.yltx.oil.partner.modules.profit.activity.-$$Lambda$SettlementrecordsActivity$1gYaNzCUrKKtSUeLVuhnan0zvl0
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                SettlementrecordsActivity.lambda$bindListener$0(SettlementrecordsActivity.this);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(SettlementrecordsActivity settlementrecordsActivity) {
        settlementrecordsActivity.profit_yjjsjl_presenter.fetchTop();
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void render(FragmentProfit_yjjsjl_Response fragmentProfit_yjjsjl_Response) {
        setData(fragmentProfit_yjjsjl_Response);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onRefreshComplete(FragmentProfit_yjjsjl_Response fragmentProfit_yjjsjl_Response) {
        setData(fragmentProfit_yjjsjl_Response);
        this.jsjlListlayoutFai.setRefreshing(false);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onLoadMoreComplete(FragmentProfit_yjjsjl_Response fragmentProfit_yjjsjl_Response) {
        this.jsjlListlayoutFai.setRefreshing(false);
        setMoreData(fragmentProfit_yjjsjl_Response);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onRefreshError(String str) {
        this.jsjlListlayoutFai.setRefreshing(false);
        ToastUtil.showMiddleScreenToast(str);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onLoadMoreError(String str) {
        this.sett_recyclerview_adapter.loadMoreFail();
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener
    public void onLoadMoreRequested() {
        this.profit_yjjsjl_presenter.fetchMore();
    }
}