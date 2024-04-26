package com.yltx.oil.partner.modules.profit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.BaseFragment;
import com.yltx.oil.partner.modules.profit.adapter.ManageRecyclerAdapter;
import com.yltx.oil.partner.modules.profit.presenter.SettlementRecordsPresenter;
import com.yltx.oil.partner.modules.profit.response.ManageResponse;
import com.yltx.oil.partner.mvp.views.PageLimitView;
import com.yltx.oil.partner.utils.ToastUtil;
import java.util.List;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class AllFragment extends BaseFragment implements PageLimitView<ManageResponse>, BaseQuickAdapter.RequestLoadMoreListener {
    ManageRecyclerAdapter adapter;
    @Inject
    SettlementRecordsPresenter settlementRecordsPresenter;
    @BindView(R.id.skjl_rv_list)
    RecyclerView skjlRvList;
    @BindView(R.id.skjl_srl_layout)
    SwipeRefreshLayout skjlSrlLayout;
    Unbinder unbinder;

    @Override // com.yltx.oil.partner.base.BaseFragment
    protected int setupContentView() {
        return R.layout.fragment_all;
    }

    public static AllFragment newInstance() {
        Bundle bundle = new Bundle();
        AllFragment allFragment = new AllFragment();
        allFragment.setArguments(bundle);
        return allFragment;
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.unbinder = ButterKnife.bind(this, onCreateView);
        return onCreateView;
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.settlementRecordsPresenter.attachView(this);
        setupUI();
        bindListener();
    }

    protected void setupUI() {
        initRView();
        this.settlementRecordsPresenter.fetchFirst();
    }

    private void initRView() {
        this.adapter = new ManageRecyclerAdapter(null);
        this.skjlRvList.setLayoutManager(new LinearLayoutManager(getContext()));
        this.skjlRvList.setAdapter(this.adapter);
        this.adapter.setOnLoadMoreListener(this, this.skjlRvList);
    }

    private void bindListener() {
        this.skjlSrlLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.yltx.oil.partner.modules.profit.fragment.-$$Lambda$AllFragment$Cpu62LSyh24i6bIBnMyfDB1pGEw
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                AllFragment.lambda$bindListener$0(AllFragment.this);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(AllFragment allFragment) {
        allFragment.settlementRecordsPresenter.fetchTop();
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    private void setData(ManageResponse manageResponse) {
        if (manageResponse == null || manageResponse.getStationRecordData().size() == 0) {
            this.adapter.loadMoreEnd();
        } else if (manageResponse.getStationRecordData().size() < 10) {
            this.adapter.setEnableLoadMore(false);
            this.adapter.loadMoreEnd();
        } else {
            this.adapter.setEnableLoadMore(true);
            this.adapter.loadMoreComplete();
        }
        this.adapter.setNewData(manageResponse.getStationRecordData());
        this.adapter.disableLoadMoreIfNotFullPage();
    }

    private void setMoreData(ManageResponse manageResponse) {
        if (manageResponse.getStationRecordData().size() < 10) {
            this.adapter.setEnableLoadMore(false);
            this.adapter.loadMoreEnd();
        } else {
            this.adapter.setEnableLoadMore(true);
            this.adapter.loadMoreComplete();
        }
        this.adapter.addData((List) manageResponse.getStationRecordData());
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void render(ManageResponse manageResponse) {
        setData(manageResponse);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onRefreshComplete(ManageResponse manageResponse) {
        setData(manageResponse);
        this.skjlSrlLayout.setRefreshing(false);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onLoadMoreComplete(ManageResponse manageResponse) {
        this.skjlSrlLayout.setRefreshing(false);
        setMoreData(manageResponse);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onRefreshError(String str) {
        this.skjlSrlLayout.setRefreshing(false);
        ToastUtil.showMiddleScreenToast(str);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onLoadMoreError(String str) {
        this.adapter.loadMoreFail();
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener
    public void onLoadMoreRequested() {
        this.settlementRecordsPresenter.fetchMore();
    }
}