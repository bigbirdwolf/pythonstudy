package com.yltx.oil.partner.modules.oiltrade.fragment;

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
import com.yltx.oil.partner.base.PartnerApplication;
import com.yltx.oil.partner.data.network.Config;
import com.yltx.oil.partner.data.response.FinanceCardlResp;
import com.yltx.oil.partner.modules.home.activity.CommoditySharingInforActivity;
import com.yltx.oil.partner.modules.login.activity.LoginActivity;
import com.yltx.oil.partner.modules.oiltrade.adapter.YPMYAdapter;
import com.yltx.oil.partner.modules.oiltrade.presenter.FinanceCardPresenter;
import com.yltx.oil.partner.mvp.views.PageLimitView;
import com.yltx.oil.partner.utils.CommonUtils;
import com.yltx.oil.partner.utils.ToastUtil;
import com.yltx.oil.partner.widget.CommonDialog;
import java.util.List;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class OilCardFragment extends BaseFragment implements PageLimitView<List<FinanceCardlResp>>, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {
    private YPMYAdapter mAdapter;
    @Inject
    FinanceCardPresenter mPresenter;
    @BindView(R.id.SR_list)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.fragment_oilcard_list)
    RecyclerView recyclerView;
    Unbinder unbinder;

    @Override // com.yltx.oil.partner.base.BaseFragment
    protected int setupContentView() {
        return R.layout.fragment_oilcard;
    }

    public static OilCardFragment newInstance() {
        Bundle bundle = new Bundle();
        OilCardFragment oilCardFragment = new OilCardFragment();
        oilCardFragment.setArguments(bundle);
        return oilCardFragment;
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
        this.mPresenter.attachView(this);
        initView();
        bindListener();
        this.mPresenter.fetchFirst();
    }

    private void bindListener() {
        this.mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.yltx.oil.partner.modules.oiltrade.fragment.-$$Lambda$OilCardFragment$phzTgixK7fmDbdimXhqEvDkfCZ4
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                OilCardFragment.lambda$bindListener$0(OilCardFragment.this);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(OilCardFragment oilCardFragment) {
        oilCardFragment.mPresenter.fetchTop();
    }

    private void initView() {
        this.mAdapter = new YPMYAdapter(null);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.recyclerView.setAdapter(this.mAdapter);
        this.mAdapter.setOnLoadMoreListener(this, this.recyclerView);
        this.mAdapter.setOnItemClickListener(this);
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    private void setData(List<FinanceCardlResp> list) {
        if (list == null || list.size() == 0) {
            this.mAdapter.loadMoreEnd();
            this.mAdapter.setEmptyView(R.layout.empty_layout);
        } else if (list.size() < 10) {
            this.mAdapter.setEnableLoadMore(false);
            this.mAdapter.loadMoreEnd();
        } else {
            this.mAdapter.setEnableLoadMore(true);
            this.mAdapter.loadMoreComplete();
        }
        this.mAdapter.setNewData(list);
        this.mAdapter.disableLoadMoreIfNotFullPage();
    }

    private void setMoreData(List<FinanceCardlResp> list) {
        if (list.size() < 10) {
            this.mAdapter.setEnableLoadMore(false);
            this.mAdapter.loadMoreEnd();
        } else {
            this.mAdapter.setEnableLoadMore(true);
            this.mAdapter.loadMoreComplete();
        }
        this.mAdapter.addData((List) list);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void render(List<FinanceCardlResp> list) {
        setData(list);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onRefreshComplete(List<FinanceCardlResp> list) {
        setData(list);
        this.mRefreshLayout.setRefreshing(false);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onLoadMoreComplete(List<FinanceCardlResp> list) {
        this.mRefreshLayout.setRefreshing(false);
        setMoreData(list);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onRefreshError(String str) {
        this.mRefreshLayout.setRefreshing(false);
        ToastUtil.showMiddleScreenToast(str);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onLoadMoreError(String str) {
        this.mAdapter.loadMoreFail();
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener
    public void onLoadMoreRequested() {
        this.mPresenter.fetchMore();
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (PartnerApplication.getInstance().isLoading) {
            if (PartnerApplication.getInstance().getUserInfos().getPartnerInfo().getIsPartner().equals("1")) {
                getActivity().startActivity(CommoditySharingInforActivity.getCallingIntent(getActivity(), 1, String.valueOf(((FinanceCardlResp) baseQuickAdapter.getData().get(i)).getRowId())));
                return;
            }
            final CommonDialog showPartnerDialog = CommonUtils.showPartnerDialog(getContext());
            CommonUtils.setOnSureClickListener(new CommonUtils.OnSureClickListener() { // from class: com.yltx.oil.partner.modules.oiltrade.fragment.OilCardFragment.1
                {
                    OilCardFragment.this = this;
                }

                @Override // com.yltx.oil.partner.utils.CommonUtils.OnSureClickListener
                public void onSureClick(View view2) {
                    OilCardFragment.this.getNavigator().navigateToJsBridgeWebActivity(OilCardFragment.this.getActivity(), "油站合伙人", Config.API_WeiXin_URL.concat("#/partner/stationpartner"));
                    showPartnerDialog.dismiss();
                }
            });
            return;
        }
        startActivity(LoginActivity.getCallingIntent(getActivity()));
    }
}