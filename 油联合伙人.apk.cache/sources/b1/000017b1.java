package com.yltx.oil.partner.modules.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.BaseFragment;
import com.yltx.oil.partner.base.PartnerApplication;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.data.network.Config;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.BannerResponse;
import com.yltx.oil.partner.data.response.HomeBannerBean;
import com.yltx.oil.partner.data.response.Homebuttonconfiguration_Bean;
import com.yltx.oil.partner.data.response.ShopResp;
import com.yltx.oil.partner.modules.home.activity.CommoditySharingInforActivity;
import com.yltx.oil.partner.modules.home.adapter.BannerHolder;
import com.yltx.oil.partner.modules.home.adapter.HomeHotProdRecyclerAdapter;
import com.yltx.oil.partner.modules.home.presenter.BannerPresenter;
import com.yltx.oil.partner.modules.home.presenter.HomeButtonconfigurationPresenter;
import com.yltx.oil.partner.modules.home.presenter.ShopRecommendPresenter;
import com.yltx.oil.partner.modules.home.view.BannerView;
import com.yltx.oil.partner.modules.home.view.HomeButtonconfigurationView;
import com.yltx.oil.partner.modules.login.activity.LoginActivity;
import com.yltx.oil.partner.modules.main.MainActivity;
import com.yltx.oil.partner.mvp.views.PageLimitView;
import com.yltx.oil.partner.utils.CommonUtils;
import com.yltx.oil.partner.utils.ToastUtil;
import com.yltx.oil.partner.widget.CommonDialog;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class FragmentHome extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener, BannerView, HomeButtonconfigurationView, PageLimitView<List<ShopResp>> {
    ImageView ClassificationImg;
    @Inject
    BannerPresenter bannerPresenter;
    ImageView czk;
    ConvenientBanner fragmennntHoneHeadBanner;
    @BindView(R.id.fragment_home_shangp)
    RecyclerView fragmentHomeShangp;
    @Inject
    HomeButtonconfigurationPresenter homeButtonconfigurationPresenter;
    ImageView homeMessage;
    TextView homeSearch;
    Homebuttonconfiguration_Bean homebuttonhhr;
    Homebuttonconfiguration_Bean homebuttonyzjm;
    Homebuttonconfiguration_Bean homebuttonzrhhr;
    boolean isVisibleToUser;
    ImageView ivApplyPartner;
    ImageView ivQgjyk;
    ImageView ivYpmy;
    ImageView ivYzjm;
    ImageView ivZrhhr;
    private HomeHotProdRecyclerAdapter mAdapter;
    @Inject
    ShopRecommendPresenter mPresenter;
    @BindView(R.id.sl_home)
    SwipeRefreshLayout slHome;
    TextView tvDixian;
    Unbinder unbinder;
    List<HomeBannerBean> bannerBeans = new ArrayList();
    List<Homebuttonconfiguration_Bean> buttons = new ArrayList();
    private String TAG = FragmentHome.class.getName();
    private List<HomeBannerBean> mList = new ArrayList();

    @Override // android.support.v4.app.Fragment, com.yltx.oil.partner.mvp.views.InterfaceView
    public Context getContext() {
        return null;
    }

    @Override // com.yltx.oil.partner.mvp.views.ProgressView
    public void hideProgress() {
    }

    @Override // com.yltx.oil.partner.modules.home.view.BannerView, com.yltx.oil.partner.modules.home.view.HomeButtonconfigurationView
    public void onError(Throwable th) {
    }

    @Override // com.yltx.oil.partner.base.BaseFragment
    protected int setupContentView() {
        return R.layout.fragment_home;
    }

    @Override // com.yltx.oil.partner.mvp.views.ProgressView
    public void showProgress() {
    }

    public static FragmentHome newInstance() {
        Bundle bundle = new Bundle();
        FragmentHome fragmentHome = new FragmentHome();
        fragmentHome.setArguments(bundle);
        return fragmentHome;
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.unbinder = ButterKnife.bind(this, onCreateView);
        this.bannerPresenter.attachView(this);
        this.mPresenter.attachView(this);
        this.homeButtonconfigurationPresenter.attachView(this);
        return onCreateView;
    }

    @Override // android.support.v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.isVisibleToUser = z;
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.bannerPresenter.submitBanner();
        this.homeButtonconfigurationPresenter.submitHomeButton();
        this.mPresenter.fetchFirst();
        initView();
        bindListener();
    }

    private void bindListener() {
        Rx.click(this.ivApplyPartner, new Action1() { // from class: com.yltx.oil.partner.modules.home.fragment.-$$Lambda$FragmentHome$JfY9r5v4a1B_zAaBVaAm9Z0H_Ss
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentHome.lambda$bindListener$0(FragmentHome.this, (Void) obj);
            }
        });
        Rx.click(this.ivZrhhr, new Action1() { // from class: com.yltx.oil.partner.modules.home.fragment.-$$Lambda$FragmentHome$lnuluucx5nYXyquj14O153ZinDw
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentHome.lambda$bindListener$1(FragmentHome.this, (Void) obj);
            }
        });
        Rx.click(this.ivYzjm, new Action1() { // from class: com.yltx.oil.partner.modules.home.fragment.-$$Lambda$FragmentHome$jm5KNXUO9VLMi3SFWfpkUNJvYps
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentHome.lambda$bindListener$2(FragmentHome.this, (Void) obj);
            }
        });
        Rx.click(this.ClassificationImg, new Action1() { // from class: com.yltx.oil.partner.modules.home.fragment.-$$Lambda$FragmentHome$x1YVGGAe5X-5PSXokblFxOmdJQI
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentHome.lambda$bindListener$3(FragmentHome.this, (Void) obj);
            }
        });
        Rx.click(this.homeSearch, new Action1() { // from class: com.yltx.oil.partner.modules.home.fragment.-$$Lambda$FragmentHome$kkauX49O1yW42wrTS8yD2xYk47Q
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentHome.lambda$bindListener$4(FragmentHome.this, (Void) obj);
            }
        });
        Rx.click(this.homeMessage, new Action1() { // from class: com.yltx.oil.partner.modules.home.fragment.-$$Lambda$FragmentHome$Okwd4WLLfwmKCC2AK4U_bMuadm8
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentHome.lambda$bindListener$5(FragmentHome.this, (Void) obj);
            }
        });
        Rx.click(this.ivQgjyk, new Action1() { // from class: com.yltx.oil.partner.modules.home.fragment.-$$Lambda$FragmentHome$9kUPzxaE63qHlv8j5QfH_1iH1M0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentHome.lambda$bindListener$6(FragmentHome.this, (Void) obj);
            }
        });
        Rx.click(this.czk, new Action1() { // from class: com.yltx.oil.partner.modules.home.fragment.-$$Lambda$FragmentHome$eJosKjOgTh8tZhyNiqJw4GsxV3Y
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentHome.lambda$bindListener$7(FragmentHome.this, (Void) obj);
            }
        });
        Rx.click(this.ivYpmy, new Action1() { // from class: com.yltx.oil.partner.modules.home.fragment.-$$Lambda$FragmentHome$lxXsY6hVOEhZtXZdTfSBmB3XTTY
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentHome.lambda$bindListener$8(FragmentHome.this, (Void) obj);
            }
        });
        this.slHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.yltx.oil.partner.modules.home.fragment.FragmentHome.1
            {
                FragmentHome.this = this;
            }

            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                FragmentHome.this.bannerPresenter.submitBanner();
                FragmentHome.this.homeButtonconfigurationPresenter.submitHomeButton();
                FragmentHome.this.mPresenter.fetchTop();
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(FragmentHome fragmentHome, Void r5) {
        if (fragmentHome.homebuttonhhr == null || TextUtils.isEmpty(fragmentHome.homebuttonhhr.getUrl())) {
            return;
        }
        if (PartnerApplication.getInstance().isLoading) {
            if (!PartnerApplication.getInstance().getUserInfos().getPartnerInfo().getIsPartner().equals("1")) {
                fragmentHome.getNavigator().navigateToJsBridgeWebActivity(fragmentHome.getActivity(), "油站合伙人", Config.API_WeiXin_URL.concat(fragmentHome.homebuttonhhr.getUrl()));
                return;
            } else {
                fragmentHome.getNavigator().navigateToJsBridgeWebActivity(fragmentHome.getActivity(), "油站合伙人", Config.API_WeiXin_URL.concat(fragmentHome.homebuttonhhr.getUrl()));
                return;
            }
        }
        fragmentHome.startActivity(LoginActivity.getCallingIntent(fragmentHome.getActivity()));
    }

    public static /* synthetic */ void lambda$bindListener$1(FragmentHome fragmentHome, Void r5) {
        if (fragmentHome.homebuttonzrhhr == null || TextUtils.isEmpty(fragmentHome.homebuttonzrhhr.getUrl())) {
            return;
        }
        if (PartnerApplication.getInstance().isLoading) {
            if (!PartnerApplication.getInstance().getUserInfos().getPartnerInfo().getIsPartner().equals("1")) {
                fragmentHome.getNavigator().navigateToJsBridgeWebActivity(fragmentHome.getActivity(), "自然合伙人", Config.API_WeiXin_URL.concat(fragmentHome.homebuttonzrhhr.getUrl()));
                return;
            } else {
                fragmentHome.getNavigator().navigateToJsBridgeWebActivity(fragmentHome.getActivity(), "自然合伙人", Config.API_WeiXin_URL.concat(fragmentHome.homebuttonzrhhr.getUrl()));
                return;
            }
        }
        fragmentHome.startActivity(LoginActivity.getCallingIntent(fragmentHome.getActivity()));
    }

    public static /* synthetic */ void lambda$bindListener$2(FragmentHome fragmentHome, Void r5) {
        if (fragmentHome.homebuttonyzjm == null || TextUtils.isEmpty(fragmentHome.homebuttonyzjm.getUrl())) {
            return;
        }
        if (PartnerApplication.getInstance().isLoading) {
            if (!PartnerApplication.getInstance().getUserInfos().getPartnerInfo().getIsPartner().equals("1")) {
                fragmentHome.getNavigator().navigateToJsBridgeWebActivity(fragmentHome.getActivity(), "油站加盟", Config.API_WeiXin_URL.concat(fragmentHome.homebuttonyzjm.getUrl()));
                return;
            } else {
                fragmentHome.getNavigator().navigateToJsBridgeWebActivity(fragmentHome.getActivity(), "油站加盟", Config.API_WeiXin_URL.concat(fragmentHome.homebuttonyzjm.getUrl()));
                return;
            }
        }
        fragmentHome.startActivity(LoginActivity.getCallingIntent(fragmentHome.getActivity()));
    }

    public static /* synthetic */ void lambda$bindListener$3(FragmentHome fragmentHome, Void r3) {
        ((MainActivity) fragmentHome.getActivity()).performClick(1, "0");
    }

    public static /* synthetic */ void lambda$bindListener$4(FragmentHome fragmentHome, Void r2) {
        fragmentHome.getNavigator().navigateToSearchHome(fragmentHome.getActivity());
    }

    public static /* synthetic */ void lambda$bindListener$5(FragmentHome fragmentHome, Void r2) {
        if (PartnerApplication.instance.isLoading) {
            fragmentHome.getNavigator().navigateToMessageHome(fragmentHome.getActivity());
        } else {
            fragmentHome.startActivity(LoginActivity.getCallingIntent(fragmentHome.getActivity()));
        }
    }

    public static /* synthetic */ void lambda$bindListener$6(FragmentHome fragmentHome, Void r3) {
        ((MainActivity) fragmentHome.getActivity()).performClick(1, "1");
    }

    public static /* synthetic */ void lambda$bindListener$7(FragmentHome fragmentHome, Void r3) {
        ((MainActivity) fragmentHome.getActivity()).performClick(1, "2");
    }

    public static /* synthetic */ void lambda$bindListener$8(FragmentHome fragmentHome, Void r3) {
        ((MainActivity) fragmentHome.getActivity()).performClick(1, "0");
    }

    private void initView() {
        this.mAdapter = new HomeHotProdRecyclerAdapter(null);
        View inflate = getLayoutInflater().inflate(R.layout.layout_home_head, (ViewGroup) null);
        View inflate2 = getLayoutInflater().inflate(R.layout.layout_foot, (ViewGroup) null);
        this.ivApplyPartner = (ImageView) inflate.findViewById(R.id.iv_apply_partner);
        this.ClassificationImg = (ImageView) inflate.findViewById(R.id.Classification_img);
        this.homeSearch = (TextView) inflate.findViewById(R.id.fragment_home_search);
        this.homeMessage = (ImageView) inflate.findViewById(R.id.fragment_home_message);
        this.fragmennntHoneHeadBanner = (ConvenientBanner) inflate.findViewById(R.id.fragmennnt_hone_head_banner);
        this.ivZrhhr = (ImageView) inflate.findViewById(R.id.iv_zrhhr);
        this.ivYzjm = (ImageView) inflate.findViewById(R.id.iv_yzjm);
        this.ivQgjyk = (ImageView) inflate.findViewById(R.id.iv_qgjyk);
        this.ivYpmy = (ImageView) inflate.findViewById(R.id.iv_ypmy);
        this.czk = (ImageView) inflate.findViewById(R.id.czk);
        this.tvDixian = (TextView) inflate2.findViewById(R.id.tv_dixian);
        this.mAdapter.addHeaderView(inflate);
        this.mAdapter.addFooterView(inflate2);
        this.fragmentHomeShangp.setLayoutManager(new LinearLayoutManager(getContext()));
        this.fragmentHomeShangp.setAdapter(this.mAdapter);
        this.mAdapter.setOnLoadMoreListener(this, this.fragmentHomeShangp);
        this.mAdapter.setOnItemClickListener(this);
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (PartnerApplication.getInstance().isLoading) {
            if (PartnerApplication.getInstance().getUserInfos().getPartnerInfo().getIsPartner().equals("1")) {
                getActivity().startActivity(CommoditySharingInforActivity.getCallingIntent(getActivity(), 3, String.valueOf(((ShopResp) baseQuickAdapter.getData().get(i)).getSpecsId())));
                return;
            }
            final CommonDialog showPartnerDialog = CommonUtils.showPartnerDialog(getActivity());
            CommonUtils.setOnSureClickListener(new CommonUtils.OnSureClickListener() { // from class: com.yltx.oil.partner.modules.home.fragment.FragmentHome.2
                {
                    FragmentHome.this = this;
                }

                @Override // com.yltx.oil.partner.utils.CommonUtils.OnSureClickListener
                public void onSureClick(View view2) {
                    FragmentHome.this.getNavigator().navigateToJsBridgeWebActivity(FragmentHome.this.getActivity(), "油站合伙人", Config.API_WeiXin_URL.concat("#/partner/stationpartner"));
                    showPartnerDialog.dismiss();
                }
            });
            return;
        }
        startActivity(LoginActivity.getCallingIntent(getActivity()));
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.fragmennntHoneHeadBanner == null || this.mList.size() <= 0) {
            return;
        }
        this.fragmennntHoneHeadBanner.startTurning(Config.BINNER_DURATION);
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.fragmennntHoneHeadBanner != null) {
            this.fragmennntHoneHeadBanner.stopTurning();
        }
    }

    private void initBinner(final List<BannerResponse> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                BannerResponse bannerResponse = new BannerResponse();
                bannerResponse.setFileName(list.get(i).getFileName());
                arrayList.add(bannerResponse);
            }
        }
        if (this.fragmennntHoneHeadBanner != null) {
            this.fragmennntHoneHeadBanner.setPages(new CBViewHolderCreator() { // from class: com.yltx.oil.partner.modules.home.fragment.-$$Lambda$xGJ2lVz6z3xZ3j1x2rWSwhoGaG4
                @Override // com.bigkoo.convenientbanner.holder.CBViewHolderCreator
                public final Object createHolder() {
                    return new BannerHolder();
                }
            }, arrayList).setPageIndicator(new int[]{R.drawable.shape_lunbo_kong_color, R.drawable.shape_lunbo_main_color}).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL).setOnItemClickListener(new OnItemClickListener() { // from class: com.yltx.oil.partner.modules.home.fragment.-$$Lambda$FragmentHome$mubha9swvbxpDz8mLjFzROAurfs
                @Override // com.bigkoo.convenientbanner.listener.OnItemClickListener
                public final void onItemClick(int i2) {
                    FragmentHome.lambda$initBinner$9(FragmentHome.this, list, i2);
                }
            });
            this.fragmennntHoneHeadBanner.startTurning(Config.BINNER_DURATION);
        }
    }

    public static /* synthetic */ void lambda$initBinner$9(FragmentHome fragmentHome, List list, int i) {
        if (TextUtils.isEmpty(((BannerResponse) list.get(i)).getDrillValue())) {
            return;
        }
        fragmentHome.getNavigator().navigateToJsBridgeWebActivity(fragmentHome.getActivity(), "", ((BannerResponse) list.get(i)).getDrillValue());
    }

    @Override // com.yltx.oil.partner.modules.home.view.BannerView
    public void onBanner(HttpResult<List<BannerResponse>> httpResult) {
        initBinner(httpResult.getData());
    }

    @Override // com.yltx.oil.partner.modules.home.view.HomeButtonconfigurationView
    public void onHomeButtonconfiguration(HttpResult<List<Homebuttonconfiguration_Bean>> httpResult) {
        this.buttons = httpResult.getData();
        for (int i = 0; i < httpResult.getData().size(); i++) {
            if (httpResult.getData().get(i).getType().equals("1")) {
                this.homebuttonhhr = httpResult.getData().get(i);
            } else if (httpResult.getData().get(i).getType().equals("2")) {
                this.homebuttonzrhhr = httpResult.getData().get(i);
            } else if (httpResult.getData().get(i).getType().equals("3")) {
                this.homebuttonyzjm = httpResult.getData().get(i);
            }
        }
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void render(List<ShopResp> list) {
        setData(list);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onRefreshComplete(List<ShopResp> list) {
        setData(list);
        this.slHome.setRefreshing(false);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onLoadMoreComplete(List<ShopResp> list) {
        this.slHome.setRefreshing(false);
        setMoreData(list);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onRefreshError(String str) {
        this.slHome.setRefreshing(false);
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

    private void setData(List<ShopResp> list) {
        if (list == null || list.size() == 0) {
            this.mAdapter.loadMoreEnd();
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
        if (list.size() == 0) {
            this.tvDixian.setVisibility(0);
        } else {
            this.tvDixian.setVisibility(8);
        }
        if (list.size() < 10) {
            this.mAdapter.setEnableLoadMore(false);
            this.mAdapter.loadMoreEnd();
        } else {
            this.mAdapter.setEnableLoadMore(true);
            this.mAdapter.loadMoreComplete();
        }
        this.mAdapter.addData((List) list);
    }
}