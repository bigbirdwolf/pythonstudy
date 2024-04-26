package com.yltx.oil.partner.modules.profit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.BaseFragment;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.modules.profit.adapter.Sy_Ddmx_ELV_Adapter;
import com.yltx.oil.partner.modules.profit.presenter.AllordersPresenter;
import com.yltx.oil.partner.modules.profit.response.AllordersResponse;
import com.yltx.oil.partner.mvp.views.PageLimitView;
import com.yltx.oil.partner.utils.ToastUtil;
import java.util.List;
import javax.inject.Inject;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class EffectiveorderFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener, PageLimitView<AllordersResponse>, BaseQuickAdapter.RequestLoadMoreListener {
    @Inject
    AllordersPresenter allordersPresenter;
    @BindView(R.id.ddmx_cyc_eff)
    RecyclerView ddmxCycEff;
    @BindView(R.id.ddmx_listlayout_eff)
    SwipeRefreshLayout ddmxListlayoutEff;
    Sy_Ddmx_ELV_Adapter mAdapter;
    @BindView(R.id.sy_ddmx_sydd_czk)
    RadioButton syDdmxSyddCzk;
    @BindView(R.id.sy_ddmx_sydd_czkk)
    RadioButton syDdmxSyddCzkk;
    @BindView(R.id.sy_ddmx_sydd_jyk)
    RadioButton syDdmxSyddJyk;
    @BindView(R.id.sy_ddmx_sydd_sp)
    RadioButton syDdmxSyddSp;
    @BindView(R.id.sy_ddmx_sydd_ypmy)
    RadioButton syDdmxSyddYpmy;
    Unbinder unbinder;
    private String YPMY = "1";
    private String JYK = "2";
    private String CZK = "3";
    private String SP = "0";
    private String CZKK = "5";
    private String Status = "0";
    private String type = this.YPMY;

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
    }

    @Override // com.yltx.oil.partner.base.BaseFragment
    protected int setupContentView() {
        return R.layout.fragment_effectiveorder;
    }

    public static EffectiveorderFragment newInstance() {
        Bundle bundle = new Bundle();
        EffectiveorderFragment effectiveorderFragment = new EffectiveorderFragment();
        effectiveorderFragment.setArguments(bundle);
        return effectiveorderFragment;
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
        this.allordersPresenter.attachView(this);
        initView();
        bindListener();
    }

    private void bindListener() {
        Rx.click(this.syDdmxSyddJyk, new Action1() { // from class: com.yltx.oil.partner.modules.profit.fragment.-$$Lambda$EffectiveorderFragment$sun4Q2iKQIwAi2FQcq3UWnvXKcc
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                EffectiveorderFragment.lambda$bindListener$0(EffectiveorderFragment.this, (Void) obj);
            }
        });
        Rx.click(this.syDdmxSyddYpmy, new Action1() { // from class: com.yltx.oil.partner.modules.profit.fragment.-$$Lambda$EffectiveorderFragment$-xIGm0jP9f5wa37etw3NtBXbICc
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                EffectiveorderFragment.lambda$bindListener$1(EffectiveorderFragment.this, (Void) obj);
            }
        });
        Rx.click(this.syDdmxSyddCzk, new Action1() { // from class: com.yltx.oil.partner.modules.profit.fragment.-$$Lambda$EffectiveorderFragment$sVI9ZRlresFey9mQbCWiJy5OwCM
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                EffectiveorderFragment.lambda$bindListener$2(EffectiveorderFragment.this, (Void) obj);
            }
        });
        Rx.click(this.syDdmxSyddSp, new Action1() { // from class: com.yltx.oil.partner.modules.profit.fragment.-$$Lambda$EffectiveorderFragment$SCw7lk-m82ncCK19gpFsrDNbZwY
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                EffectiveorderFragment.lambda$bindListener$3(EffectiveorderFragment.this, (Void) obj);
            }
        });
        Rx.click(this.syDdmxSyddCzkk, new Action1() { // from class: com.yltx.oil.partner.modules.profit.fragment.-$$Lambda$EffectiveorderFragment$ukbsmRYlrukD6vUeupK3wW9puI4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                EffectiveorderFragment.lambda$bindListener$4(EffectiveorderFragment.this, (Void) obj);
            }
        });
        this.ddmxListlayoutEff.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.yltx.oil.partner.modules.profit.fragment.-$$Lambda$EffectiveorderFragment$VdB_7fIFTbrf_p-Qo9_NTWkyeHY
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                EffectiveorderFragment.lambda$bindListener$5(EffectiveorderFragment.this);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(EffectiveorderFragment effectiveorderFragment, Void r3) {
        effectiveorderFragment.mAdapter = new Sy_Ddmx_ELV_Adapter(null, null, "加油卡");
        effectiveorderFragment.ddmxCycEff.setLayoutManager(new LinearLayoutManager(effectiveorderFragment.getContext()));
        effectiveorderFragment.ddmxCycEff.setAdapter(effectiveorderFragment.mAdapter);
        effectiveorderFragment.mAdapter.setOnItemClickListener(effectiveorderFragment);
        effectiveorderFragment.mAdapter.setOnLoadMoreListener(effectiveorderFragment, effectiveorderFragment.ddmxCycEff);
        effectiveorderFragment.type = effectiveorderFragment.JYK;
        effectiveorderFragment.getComplain();
    }

    public static /* synthetic */ void lambda$bindListener$1(EffectiveorderFragment effectiveorderFragment, Void r3) {
        effectiveorderFragment.mAdapter = new Sy_Ddmx_ELV_Adapter(null, null, "油品贸易");
        effectiveorderFragment.ddmxCycEff.setLayoutManager(new LinearLayoutManager(effectiveorderFragment.getContext()));
        effectiveorderFragment.ddmxCycEff.setAdapter(effectiveorderFragment.mAdapter);
        effectiveorderFragment.mAdapter.setOnItemClickListener(effectiveorderFragment);
        effectiveorderFragment.mAdapter.setOnLoadMoreListener(effectiveorderFragment, effectiveorderFragment.ddmxCycEff);
        effectiveorderFragment.type = effectiveorderFragment.YPMY;
        effectiveorderFragment.getComplain();
    }

    public static /* synthetic */ void lambda$bindListener$2(EffectiveorderFragment effectiveorderFragment, Void r3) {
        effectiveorderFragment.mAdapter = new Sy_Ddmx_ELV_Adapter(null, null, "储值卡");
        effectiveorderFragment.ddmxCycEff.setLayoutManager(new LinearLayoutManager(effectiveorderFragment.getContext()));
        effectiveorderFragment.ddmxCycEff.setAdapter(effectiveorderFragment.mAdapter);
        effectiveorderFragment.mAdapter.setOnItemClickListener(effectiveorderFragment);
        effectiveorderFragment.mAdapter.setOnLoadMoreListener(effectiveorderFragment, effectiveorderFragment.ddmxCycEff);
        effectiveorderFragment.type = effectiveorderFragment.CZK;
        effectiveorderFragment.getComplain();
    }

    public static /* synthetic */ void lambda$bindListener$3(EffectiveorderFragment effectiveorderFragment, Void r4) {
        effectiveorderFragment.mAdapter = new Sy_Ddmx_ELV_Adapter(null, "商品", "商品");
        effectiveorderFragment.ddmxCycEff.setLayoutManager(new LinearLayoutManager(effectiveorderFragment.getContext()));
        effectiveorderFragment.ddmxCycEff.setAdapter(effectiveorderFragment.mAdapter);
        effectiveorderFragment.mAdapter.setOnItemClickListener(effectiveorderFragment);
        effectiveorderFragment.mAdapter.setOnLoadMoreListener(effectiveorderFragment, effectiveorderFragment.ddmxCycEff);
        effectiveorderFragment.type = effectiveorderFragment.SP;
        effectiveorderFragment.getComplain();
    }

    public static /* synthetic */ void lambda$bindListener$4(EffectiveorderFragment effectiveorderFragment, Void r3) {
        effectiveorderFragment.mAdapter = new Sy_Ddmx_ELV_Adapter(null, null, "礼品卡");
        effectiveorderFragment.ddmxCycEff.setLayoutManager(new LinearLayoutManager(effectiveorderFragment.getContext()));
        effectiveorderFragment.ddmxCycEff.setAdapter(effectiveorderFragment.mAdapter);
        effectiveorderFragment.mAdapter.setOnItemClickListener(effectiveorderFragment);
        effectiveorderFragment.mAdapter.setOnLoadMoreListener(effectiveorderFragment, effectiveorderFragment.ddmxCycEff);
        effectiveorderFragment.type = effectiveorderFragment.CZKK;
        effectiveorderFragment.getComplain();
    }

    public static /* synthetic */ void lambda$bindListener$5(EffectiveorderFragment effectiveorderFragment) {
        effectiveorderFragment.allordersPresenter.fetchTop();
    }

    private void getComplain() {
        this.Status = "0";
        this.allordersPresenter.setStatus(this.Status);
        this.allordersPresenter.setType(this.type);
        this.allordersPresenter.fetchFirst();
    }

    private void initView() {
        initRView();
        this.syDdmxSyddYpmy.setChecked(true);
        this.type = this.YPMY;
        this.allordersPresenter.setStatus(this.Status);
        this.allordersPresenter.setType(this.type);
        this.allordersPresenter.fetchFirst();
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        initView();
    }

    private void initRView() {
        this.mAdapter = new Sy_Ddmx_ELV_Adapter(null, null, "油品贸易");
        this.ddmxCycEff.setLayoutManager(new LinearLayoutManager(getContext()));
        this.ddmxCycEff.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(this);
        this.mAdapter.setOnLoadMoreListener(this, this.ddmxCycEff);
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    private void setData(AllordersResponse allordersResponse) {
        if (allordersResponse == null || allordersResponse.getData().size() == 0) {
            this.mAdapter.loadMoreEnd();
        } else if (allordersResponse.getData().size() < 10) {
            this.mAdapter.setEnableLoadMore(false);
            this.mAdapter.loadMoreEnd();
        } else {
            this.mAdapter.setEnableLoadMore(true);
            this.mAdapter.loadMoreComplete();
        }
        this.mAdapter.setNewData(allordersResponse.getData());
        this.mAdapter.disableLoadMoreIfNotFullPage();
    }

    private void setMoreData(AllordersResponse allordersResponse) {
        if (allordersResponse.getData().size() < 10) {
            this.mAdapter.setEnableLoadMore(false);
            this.mAdapter.loadMoreEnd();
        } else {
            this.mAdapter.setEnableLoadMore(true);
            this.mAdapter.loadMoreComplete();
        }
        this.mAdapter.addData((List) allordersResponse.getData());
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void render(AllordersResponse allordersResponse) {
        setData(allordersResponse);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onRefreshComplete(AllordersResponse allordersResponse) {
        setData(allordersResponse);
        this.ddmxListlayoutEff.setRefreshing(false);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onLoadMoreComplete(AllordersResponse allordersResponse) {
        this.ddmxListlayoutEff.setRefreshing(false);
        setMoreData(allordersResponse);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onRefreshError(String str) {
        this.ddmxListlayoutEff.setRefreshing(false);
        ToastUtil.showMiddleScreenToast(str);
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener
    public void onLoadMoreRequested() {
        this.allordersPresenter.fetchMore();
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onLoadMoreError(String str) {
        this.mAdapter.loadMoreFail();
    }
}