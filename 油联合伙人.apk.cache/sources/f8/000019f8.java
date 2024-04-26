package com.yltx.oil.partner.modules.profit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.BaseFragment;
import com.yltx.oil.partner.base.PartnerApplication;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.data.network.Config;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.login.activity.LoginActivity;
import com.yltx.oil.partner.modules.profit.activity.CollectionRecordActivity;
import com.yltx.oil.partner.modules.profit.activity.DataanalysisActivity;
import com.yltx.oil.partner.modules.profit.activity.OrderdetailsActivity;
import com.yltx.oil.partner.modules.profit.activity.SettlementrecordsActivity;
import com.yltx.oil.partner.modules.profit.presenter.AccountBalancePresenter;
import com.yltx.oil.partner.modules.profit.presenter.CommissionPresenter;
import com.yltx.oil.partner.modules.profit.presenter.FragmentProfit_yjjsjl_Presenter;
import com.yltx.oil.partner.modules.profit.presenter.JoinPresenter;
import com.yltx.oil.partner.modules.profit.response.CommissionResponse;
import com.yltx.oil.partner.modules.profit.response.FragmentProfit_yjjsjl_Response;
import com.yltx.oil.partner.modules.profit.response.JoinResponse;
import com.yltx.oil.partner.modules.profit.response.ManageResponse;
import com.yltx.oil.partner.modules.profit.view.AccountBalanceView;
import com.yltx.oil.partner.modules.profit.view.CommissionView;
import com.yltx.oil.partner.modules.profit.view.JoinView;
import com.yltx.oil.partner.mvp.views.PageLimitView;
import com.yltx.oil.partner.utils.CommonUtils;
import com.yltx.oil.partner.widget.CommonDialog;
import javax.inject.Inject;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class FragmentProfit extends BaseFragment implements PageLimitView<FragmentProfit_yjjsjl_Response>, CommissionView, JoinView, AccountBalanceView {
    @Inject
    CommissionPresenter commissionPresenter;
    @Inject
    JoinPresenter joinPresenter;
    @BindView(R.id.ll_tixian)
    RelativeLayout llTixian;
    @Inject
    AccountBalancePresenter mPresenter;
    @Inject
    FragmentProfit_yjjsjl_Presenter profit_yjjsjl_presenter;
    @BindView(R.id.sy_jrzsr)
    TextView syJrzsr;
    @BindView(R.id.sy_linear)
    LinearLayout syLinear;
    @BindView(R.id.sy_radio_jqt)
    RadioButton syRadioJqt;
    @BindView(R.id.sy_radio_jr)
    RadioButton syRadioJr;
    @BindView(R.id.sy_radio_jsst)
    RadioButton syRadioJsst;
    @BindView(R.id.sy_radio_zr)
    RadioButton syRadioZr;
    @BindView(R.id.sy_yjddmx_click)
    RelativeLayout syYjddmxClick;
    @BindView(R.id.sy_yjjsjl_click)
    RelativeLayout syYjjsjlClick;
    @BindView(R.id.sy_yjsjfx_click)
    RelativeLayout syYjsjfxClick;
    @BindView(R.id.sy_yzjyfx_click)
    RelativeLayout syYzjyfxClick;
    @BindView(R.id.tv_sybb2_skbs)
    TextView tvSybb2Skbs;
    @BindView(R.id.tv_sybb2_tkbs)
    TextView tvSybb2Tkbs;
    @BindView(R.id.tv_sybb_djcs)
    TextView tvSybbDjcs;
    @BindView(R.id.tv_sybb_xse)
    TextView tvSybbXse;
    @BindView(R.id.tv_sybb_yjsr)
    TextView tvSybbYjsr;
    @BindView(R.id.tv_sybb_yxdds)
    TextView tvSybbYxdds;
    @BindView(R.id.tv_zhanghuyue)
    TextView tvZhanghuyue;
    Unbinder unbinder;
    @BindView(R.id.yjjsjl_byyjsr)
    TextView yjjsjlByyjsr;
    @BindView(R.id.yjjsjl_fhjgsm)
    TextView yjjsjlFhjgsm;
    @BindView(R.id.yjjsjl_syjssr)
    TextView yjjsjlSyjssr;
    private String JR = "0";
    private String ZR = "1";
    private String JQT = "2";
    private String JSST = "3";
    private String type = "0";

    @Override // com.yltx.oil.partner.mvp.views.ProgressView
    public void hideProgress() {
    }

    @Override // com.yltx.oil.partner.modules.profit.view.CommissionView, com.yltx.oil.partner.modules.profit.view.JoinView, com.yltx.oil.partner.modules.profit.view.AccountBalanceView
    public void onError(Throwable th) {
    }

    @Override // com.yltx.oil.partner.modules.profit.view.AccountBalanceView
    public void onIsAuth(HttpResult<String> httpResult) {
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onLoadMoreComplete(FragmentProfit_yjjsjl_Response fragmentProfit_yjjsjl_Response) {
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onLoadMoreError(String str) {
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onRefreshComplete(FragmentProfit_yjjsjl_Response fragmentProfit_yjjsjl_Response) {
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onRefreshError(String str) {
    }

    @Override // com.yltx.oil.partner.base.BaseFragment
    protected int setupContentView() {
        return R.layout.activity_sybb2;
    }

    @Override // com.yltx.oil.partner.mvp.views.ProgressView
    public void showProgress() {
    }

    public static FragmentProfit newInstance() {
        Bundle bundle = new Bundle();
        FragmentProfit fragmentProfit = new FragmentProfit();
        fragmentProfit.setArguments(bundle);
        return fragmentProfit;
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
        this.profit_yjjsjl_presenter.attachView(this);
        this.commissionPresenter.attachView(this);
        this.joinPresenter.attachView(this);
        this.mPresenter.attachView(this);
        this.syRadioJr.setChecked(true);
        this.joinPresenter.JoinMember();
        this.mPresenter.getYZJYSJFX();
        initView();
        bindListener();
    }

    private void bindListener() {
        Rx.click(this.syYzjyfxClick, new Action1() { // from class: com.yltx.oil.partner.modules.profit.fragment.-$$Lambda$FragmentProfit$adjHc7KK4q3JNRK7cd0NHHaM3bs
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentProfit.lambda$bindListener$0(FragmentProfit.this, (Void) obj);
            }
        });
        Rx.click(this.syYjjsjlClick, new Action1() { // from class: com.yltx.oil.partner.modules.profit.fragment.-$$Lambda$FragmentProfit$hCIu6O0WVMs3Lbh0i0ASv0gxWTQ
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentProfit.lambda$bindListener$1(FragmentProfit.this, (Void) obj);
            }
        });
        Rx.click(this.syYjsjfxClick, new Action1() { // from class: com.yltx.oil.partner.modules.profit.fragment.-$$Lambda$FragmentProfit$3tDkBsjKiLmm8LIRV5itL6pexWQ
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentProfit.lambda$bindListener$2(FragmentProfit.this, (Void) obj);
            }
        });
        Rx.click(this.syYjddmxClick, new Action1() { // from class: com.yltx.oil.partner.modules.profit.fragment.-$$Lambda$FragmentProfit$Q3QjNsXdJI4yzhPrILl0d96fo7I
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentProfit.lambda$bindListener$3(FragmentProfit.this, (Void) obj);
            }
        });
        Rx.click(this.llTixian, new Action1() { // from class: com.yltx.oil.partner.modules.profit.fragment.-$$Lambda$FragmentProfit$9MqLWDryfAHpKF2xLKOqCXG7D78
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentProfit.lambda$bindListener$4(FragmentProfit.this, (Void) obj);
            }
        });
        Rx.click(this.syRadioJr, new Action1() { // from class: com.yltx.oil.partner.modules.profit.fragment.-$$Lambda$FragmentProfit$xDZjB0PQsbjmtrqnt4Bh-5BKPmM
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentProfit.lambda$bindListener$5(FragmentProfit.this, (Void) obj);
            }
        });
        Rx.click(this.syRadioZr, new Action1() { // from class: com.yltx.oil.partner.modules.profit.fragment.-$$Lambda$FragmentProfit$WQcYhTGleKsyBjI4h8_aAJH6WXc
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentProfit.lambda$bindListener$6(FragmentProfit.this, (Void) obj);
            }
        });
        Rx.click(this.syRadioJqt, new Action1() { // from class: com.yltx.oil.partner.modules.profit.fragment.-$$Lambda$FragmentProfit$386P5ZQBG1cHN0H9T0HYSkxCqp4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentProfit.lambda$bindListener$7(FragmentProfit.this, (Void) obj);
            }
        });
        Rx.click(this.syRadioJsst, new Action1() { // from class: com.yltx.oil.partner.modules.profit.fragment.-$$Lambda$FragmentProfit$Bdjo-LY6DwxTYXSzgqTRBHNavrY
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentProfit.lambda$bindListener$8(FragmentProfit.this, (Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(FragmentProfit fragmentProfit, Void r2) {
        if (PartnerApplication.getInstance().isLoading) {
            fragmentProfit.getActivity().startActivity(CollectionRecordActivity.getCallingIntent(fragmentProfit.getActivity()));
        } else {
            fragmentProfit.startActivity(LoginActivity.getCallingIntent(fragmentProfit.getActivity()));
        }
    }

    public static /* synthetic */ void lambda$bindListener$1(FragmentProfit fragmentProfit, Void r2) {
        if (PartnerApplication.getInstance().isLoading) {
            if (PartnerApplication.getInstance().getUserInfos().getPartnerInfo().getIsPartner().equals("1")) {
                fragmentProfit.getActivity().startActivity(SettlementrecordsActivity.getCallingIntent(fragmentProfit.getActivity()));
                return;
            }
            final CommonDialog showPartnerDialog = CommonUtils.showPartnerDialog(fragmentProfit.getContext());
            CommonUtils.setOnSureClickListener(new CommonUtils.OnSureClickListener() { // from class: com.yltx.oil.partner.modules.profit.fragment.FragmentProfit.1
                {
                    FragmentProfit.this = fragmentProfit;
                }

                @Override // com.yltx.oil.partner.utils.CommonUtils.OnSureClickListener
                public void onSureClick(View view) {
                    showPartnerDialog.dismiss();
                    FragmentProfit.this.getNavigator().navigateToJsBridgeWebActivity(FragmentProfit.this.getActivity(), "油站合伙人", Config.API_WeiXin_URL.concat("#/partner/stationpartner"));
                }
            });
            return;
        }
        fragmentProfit.startActivity(LoginActivity.getCallingIntent(fragmentProfit.getActivity()));
    }

    public static /* synthetic */ void lambda$bindListener$2(FragmentProfit fragmentProfit, Void r2) {
        if (PartnerApplication.getInstance().isLoading) {
            if (PartnerApplication.getInstance().getUserInfos().getPartnerInfo().getIsPartner().equals("1")) {
                fragmentProfit.getActivity().startActivity(DataanalysisActivity.getCallingIntent(fragmentProfit.getActivity()));
                return;
            }
            final CommonDialog showPartnerDialog = CommonUtils.showPartnerDialog(fragmentProfit.getContext());
            CommonUtils.setOnSureClickListener(new CommonUtils.OnSureClickListener() { // from class: com.yltx.oil.partner.modules.profit.fragment.FragmentProfit.2
                {
                    FragmentProfit.this = fragmentProfit;
                }

                @Override // com.yltx.oil.partner.utils.CommonUtils.OnSureClickListener
                public void onSureClick(View view) {
                    showPartnerDialog.dismiss();
                    FragmentProfit.this.getNavigator().navigateToJsBridgeWebActivity(FragmentProfit.this.getActivity(), "油站合伙人", Config.API_WeiXin_URL.concat("#/partner/stationpartner"));
                }
            });
            return;
        }
        fragmentProfit.startActivity(LoginActivity.getCallingIntent(fragmentProfit.getActivity()));
    }

    public static /* synthetic */ void lambda$bindListener$3(FragmentProfit fragmentProfit, Void r2) {
        if (PartnerApplication.getInstance().isLoading) {
            if (PartnerApplication.getInstance().getUserInfos().getPartnerInfo().getIsPartner().equals("1")) {
                fragmentProfit.getActivity().startActivity(OrderdetailsActivity.getCallingIntent(fragmentProfit.getActivity()));
                return;
            }
            final CommonDialog showPartnerDialog = CommonUtils.showPartnerDialog(fragmentProfit.getContext());
            CommonUtils.setOnSureClickListener(new CommonUtils.OnSureClickListener() { // from class: com.yltx.oil.partner.modules.profit.fragment.FragmentProfit.3
                {
                    FragmentProfit.this = fragmentProfit;
                }

                @Override // com.yltx.oil.partner.utils.CommonUtils.OnSureClickListener
                public void onSureClick(View view) {
                    showPartnerDialog.dismiss();
                    FragmentProfit.this.getNavigator().navigateToJsBridgeWebActivity(FragmentProfit.this.getActivity(), "油站合伙人", Config.API_WeiXin_URL.concat("#/partner/stationpartner"));
                }
            });
            return;
        }
        fragmentProfit.startActivity(LoginActivity.getCallingIntent(fragmentProfit.getActivity()));
    }

    public static /* synthetic */ void lambda$bindListener$4(FragmentProfit fragmentProfit, Void r2) {
        if (PartnerApplication.instance.isLoading) {
            fragmentProfit.getNavigator().navigateToAccountdetails(fragmentProfit.getActivity());
        } else {
            fragmentProfit.startActivity(LoginActivity.getCallingIntent(fragmentProfit.getActivity()));
        }
    }

    public static /* synthetic */ void lambda$bindListener$5(FragmentProfit fragmentProfit, Void r1) {
        fragmentProfit.type = fragmentProfit.JR;
        fragmentProfit.initView();
    }

    public static /* synthetic */ void lambda$bindListener$6(FragmentProfit fragmentProfit, Void r1) {
        fragmentProfit.type = fragmentProfit.ZR;
        fragmentProfit.initView();
    }

    public static /* synthetic */ void lambda$bindListener$7(FragmentProfit fragmentProfit, Void r1) {
        fragmentProfit.type = fragmentProfit.JQT;
        fragmentProfit.initView();
    }

    public static /* synthetic */ void lambda$bindListener$8(FragmentProfit fragmentProfit, Void r1) {
        fragmentProfit.type = fragmentProfit.JSST;
        fragmentProfit.initView();
    }

    private void initView() {
        this.commissionPresenter.CommissionMember(this.type);
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void render(FragmentProfit_yjjsjl_Response fragmentProfit_yjjsjl_Response) {
        TextView textView = this.yjjsjlByyjsr;
        textView.setText(fragmentProfit_yjjsjl_Response.getCurrentRebateAmount() + "");
        TextView textView2 = this.yjjsjlSyjssr;
        textView2.setText(fragmentProfit_yjjsjl_Response.getLastMonthRebateAmount() + "");
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        if (PartnerApplication.getInstance().isLoading) {
            this.profit_yjjsjl_presenter.fetchFirst();
        }
        this.commissionPresenter.CommissionMember(this.type);
        this.joinPresenter.JoinMember();
        this.mPresenter.getAccountBalance();
        this.mPresenter.getYZJYSJFX();
    }

    @Override // com.yltx.oil.partner.modules.profit.view.CommissionView
    public void onMember(HttpResult<CommissionResponse> httpResult) {
        TextView textView = this.tvSybbDjcs;
        textView.setText(httpResult.getData().getWatchCount() + "");
        TextView textView2 = this.tvSybbXse;
        textView2.setText(httpResult.getData().getOrderAmount() + "");
        TextView textView3 = this.tvSybbYxdds;
        textView3.setText(httpResult.getData().getBuyCount() + "");
        TextView textView4 = this.tvSybbYjsr;
        textView4.setText(httpResult.getData().getRebateAmount() + "");
    }

    @Override // com.yltx.oil.partner.modules.profit.view.JoinView
    public void onJoin(HttpResult<JoinResponse> httpResult) {
        if (httpResult.getData().getIsStationManager().equals("1")) {
            this.syLinear.setVisibility(0);
        } else if (httpResult.getData().getIsStationManager().equals("0")) {
            this.syLinear.setVisibility(8);
        }
    }

    @Override // com.yltx.oil.partner.modules.profit.view.AccountBalanceView
    public void onAccountBalance(HttpResult<String> httpResult) {
        TextView textView = this.tvZhanghuyue;
        textView.setText(httpResult.getData() + "");
    }

    @Override // com.yltx.oil.partner.modules.profit.view.AccountBalanceView
    public void onyzjxsjfx(HttpResult<ManageResponse> httpResult) {
        TextView textView = this.syJrzsr;
        textView.setText(httpResult.getData().getStationData().getRealpayAmount() + "");
        TextView textView2 = this.tvSybb2Tkbs;
        textView2.setText(httpResult.getData().getStationData().getCancelCount() + "");
        TextView textView3 = this.tvSybb2Skbs;
        textView3.setText(httpResult.getData().getStationData().getGetCount() + "");
    }
}