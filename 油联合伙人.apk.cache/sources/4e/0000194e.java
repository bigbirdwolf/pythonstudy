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
import com.yltx.oil.partner.data.response.ShopResp;
import com.yltx.oil.partner.modules.home.activity.CommoditySharingInforActivity;
import com.yltx.oil.partner.modules.home.adapter.HomeHotProdRecyclerAdapter;
import com.yltx.oil.partner.modules.login.activity.LoginActivity;
import com.yltx.oil.partner.modules.oiltrade.presenter.ShopPresenter;
import com.yltx.oil.partner.mvp.views.PageLimitView;
import com.yltx.oil.partner.utils.CommonUtils;
import com.yltx.oil.partner.utils.ToastUtil;
import com.yltx.oil.partner.widget.CommonDialog;
import java.util.List;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class ShopFragment extends BaseFragment implements PageLimitView<List<ShopResp>>, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {
    private HomeHotProdRecyclerAdapter mAdapter;
    @Inject
    ShopPresenter mPresenter;
    @BindView(R.id.SR_list)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.fragment_oilcard_list)
    RecyclerView recyclerView;
    Unbinder unbinder;

    @Override // com.yltx.oil.partner.base.BaseFragment
    protected int setupContentView() {
        return R.layout.fragment_oilcard;
    }

    public static ShopFragment newInstance() {
        Bundle bundle = new Bundle();
        ShopFragment shopFragment = new ShopFragment();
        shopFragment.setArguments(bundle);
        return shopFragment;
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
        this.mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.yltx.oil.partner.modules.oiltrade.fragment.-$$Lambda$ShopFragment$ikbPV3J8E2ph7KSOCWVswjkWFR8
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                ShopFragment.lambda$bindListener$0(ShopFragment.this);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(ShopFragment shopFragment) {
        shopFragment.mPresenter.fetchTop();
    }

    private void initView() {
        this.mAdapter = new HomeHotProdRecyclerAdapter(null);
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

    private void setData(List<ShopResp> list) {
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

    private void setMoreData(List<ShopResp> list) {
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
    public void render(List<ShopResp> list) {
        setData(list);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onRefreshComplete(List<ShopResp> list) {
        setData(list);
        this.mRefreshLayout.setRefreshing(false);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onLoadMoreComplete(List<ShopResp> list) {
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
                getActivity().startActivity(CommoditySharingInforActivity.getCallingIntent(getActivity(), 3, String.valueOf(((ShopResp) baseQuickAdapter.getData().get(i)).getSpecsId())));
                return;
            }
            final CommonDialog showPartnerDialog = CommonUtils.showPartnerDialog(getContext());
            CommonUtils.setOnSureClickListener(new CommonUtils.OnSureClickListener() { // from class: com.yltx.oil.partner.modules.oiltrade.fragment.ShopFragment.1
                {
                    ShopFragment.this = this;
                }

                @Override // com.yltx.oil.partner.utils.CommonUtils.OnSureClickListener
                public void onSureClick(View view2) {
                    showPartnerDialog.dismiss();
                    ShopFragment.this.getNavigator().navigateToJsBridgeWebActivity(ShopFragment.this.getActivity(), "油站合伙人", Config.API_WeiXin_URL.concat("#/partner/stationpartner"));
                }
            });
            return;
        }
        startActivity(LoginActivity.getCallingIntent(getActivity()));
    }
}