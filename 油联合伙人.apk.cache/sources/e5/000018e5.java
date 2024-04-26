package com.yltx.oil.partner.modules.mine.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.BaseFragment;
import com.yltx.oil.partner.base.PartnerApplication;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.data.network.Config;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.LoginInfo;
import com.yltx.oil.partner.data.response.PhoneResp;
import com.yltx.oil.partner.modules.login.activity.LoginActivity;
import com.yltx.oil.partner.modules.mine.activity.ComplaintActivity;
import com.yltx.oil.partner.modules.mine.activity.FeedbackActivity;
import com.yltx.oil.partner.modules.mine.activity.InviteCourtesyActivity;
import com.yltx.oil.partner.modules.mine.activity.MineInfoActivity;
import com.yltx.oil.partner.modules.mine.presenter.MemberPresenter;
import com.yltx.oil.partner.modules.mine.response.MemberResponse;
import com.yltx.oil.partner.modules.mine.view.MemberView;
import com.yltx.oil.partner.utils.AlbumDisplayUtils;
import com.yltx.oil.partner.utils.CommonUtils;
import com.yltx.oil.partner.utils.DataCache;
import com.yltx.oil.partner.utils.XTSwipeRefreshLayout;
import com.yltx.oil.partner.widget.CommonDialog;
import com.yltx.oil.partner.widget.SelectableRoundedImageView;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class FragmentMine extends BaseFragment implements MemberView {
    @BindView(R.id.fragment_mine_head_iv)
    SelectableRoundedImageView fragmentMineHeadIv;
    @BindView(R.id.im_dengji)
    ImageView imDengji;
    @BindView(R.id.im_dji)
    ImageView imDji;
    private LayoutInflater inflater;
    boolean isVisibleToUser;
    @BindView(R.id.ll_dengji)
    LinearLayout llDengji;
    @BindView(R.id.ll_kefu)
    LinearLayout llKefu;
    @BindView(R.id.ll_minecomplaint)
    LinearLayout llMinecomplaint;
    @BindView(R.id.ll_minefas)
    LinearLayout llMinefas;
    @BindView(R.id.ll_minefeedback)
    LinearLayout llMinefeedback;
    @BindView(R.id.ll_minefriends)
    LinearLayout llMinefriends;
    @BindView(R.id.ll_mineguide)
    LinearLayout llMineguide;
    @BindView(R.id.ll_minehelp)
    LinearLayout llMinehelp;
    @BindView(R.id.ll_mineinfo)
    LinearLayout llMineinfo;
    @BindView(R.id.layout_refresh)
    XTSwipeRefreshLayout mRefresh;
    @Inject
    MemberPresenter memberPresenter;
    private PopupWindow popupWindow;
    private PopupWindow popupWindow1;
    @BindView(R.id.tv_dengji)
    TextView tvDengji;
    @BindView(R.id.tv_go)
    TextView tvGo;
    @BindView(R.id.ll_login)
    LinearLayout tvLogin;
    @BindView(R.id.tv_mine_id)
    TextView tvMineId;
    @BindView(R.id.tv_mine_phone)
    TextView tvMinePhone;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_xiaoshoue)
    TextView tvXiaoshoue;
    @BindView(R.id.tv_yongj)
    TextView tvYongj;
    @BindView(R.id.tv_dl)
    TextView tv_dl;
    Unbinder unbinder;
    private View view;
    List<MemberResponse> memberResponses = new ArrayList();
    private String TAG = "FragmentMine";
    String headpath = null;

    @Override // com.yltx.oil.partner.mvp.views.ProgressView
    public void hideProgress() {
    }

    @Override // com.yltx.oil.partner.base.BaseFragment
    protected int setupContentView() {
        return R.layout.fragment_mine;
    }

    @Override // com.yltx.oil.partner.mvp.views.ProgressView
    public void showProgress() {
    }

    public static FragmentMine newInstance() {
        Bundle bundle = new Bundle();
        FragmentMine fragmentMine = new FragmentMine();
        fragmentMine.setArguments(bundle);
        return fragmentMine;
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.unbinder = ButterKnife.bind(this, onCreateView);
        this.memberPresenter.attachView(this);
        this.view = onCreateView;
        return onCreateView;
    }

    @Override // android.support.v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.isVisibleToUser = z;
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.memberPresenter.submitMember();
        initView();
        bindListener();
    }

    private void bindListener() {
        Rx.click(this.tvLogin, new Action1() { // from class: com.yltx.oil.partner.modules.mine.fragment.-$$Lambda$FragmentMine$N304hoVjaUmROEnPLA4qSoQ2PK8
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentMine.lambda$bindListener$0(FragmentMine.this, (Void) obj);
            }
        });
        Rx.click(this.llKefu, new Action1() { // from class: com.yltx.oil.partner.modules.mine.fragment.-$$Lambda$FragmentMine$x3q1BDDGEfRwjo0AI8f1QXiLo1o
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentMine.lambda$bindListener$1(FragmentMine.this, (Void) obj);
            }
        });
        Rx.click(this.tvXiaoshoue, new Action1() { // from class: com.yltx.oil.partner.modules.mine.fragment.-$$Lambda$FragmentMine$t3UviS6wQDwlqLAEI8ACTF8ElYU
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentMine.lambda$bindListener$2(FragmentMine.this, (Void) obj);
            }
        });
        Rx.click(this.llMineinfo, new Action1() { // from class: com.yltx.oil.partner.modules.mine.fragment.-$$Lambda$FragmentMine$5jV-sJp-soW72Lkucby3sY3c-y8
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentMine.lambda$bindListener$3(FragmentMine.this, (Void) obj);
            }
        });
        Rx.click(this.llMinefeedback, new Action1() { // from class: com.yltx.oil.partner.modules.mine.fragment.-$$Lambda$FragmentMine$XBHH4DtHzd-xo0LsdFT_lKqQ30M
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentMine.lambda$bindListener$4(FragmentMine.this, (Void) obj);
            }
        });
        Rx.click(this.llMinecomplaint, new Action1() { // from class: com.yltx.oil.partner.modules.mine.fragment.-$$Lambda$FragmentMine$u1xulGcaGmkW3HTfaq1sEGvcop4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentMine.lambda$bindListener$5(FragmentMine.this, (Void) obj);
            }
        });
        Rx.click(this.llMinehelp, new Action1() { // from class: com.yltx.oil.partner.modules.mine.fragment.-$$Lambda$FragmentMine$uPrguLGJOnuvvwiwkZdl0Kp6o4s
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentMine.lambda$bindListener$6(FragmentMine.this, (Void) obj);
            }
        });
        Rx.click(this.llMineguide, new Action1() { // from class: com.yltx.oil.partner.modules.mine.fragment.-$$Lambda$FragmentMine$4UqSt7U3kIJw0YFRQ9bQxD5sPYo
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentMine.lambda$bindListener$7(FragmentMine.this, (Void) obj);
            }
        });
        Rx.click(this.tvGo, new Action1() { // from class: com.yltx.oil.partner.modules.mine.fragment.-$$Lambda$FragmentMine$5XhFXejSZ7NwzMQkzuSeBUDJrHo
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentMine.lambda$bindListener$8(FragmentMine.this, (Void) obj);
            }
        });
        Rx.click(this.llMinefriends, new Action1() { // from class: com.yltx.oil.partner.modules.mine.fragment.-$$Lambda$FragmentMine$SWbcPMuzQKhtR7o8evf-HL5Z3Mk
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentMine.lambda$bindListener$9(FragmentMine.this, (Void) obj);
            }
        });
        Rx.click(this.tvYongj, new Action1() { // from class: com.yltx.oil.partner.modules.mine.fragment.-$$Lambda$FragmentMine$4_Pv-tKcwg0vexFv68fvbVM7FtM
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FragmentMine.lambda$bindListener$10(FragmentMine.this, (Void) obj);
            }
        });
        this.mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.yltx.oil.partner.modules.mine.fragment.-$$Lambda$FragmentMine$NeLpbw5e5qMELEtPssTnfnsPT9w
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                FragmentMine.lambda$bindListener$11(FragmentMine.this);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(FragmentMine fragmentMine, Void r2) {
        if (PartnerApplication.instance.isLoading) {
            fragmentMine.startActivity(MineInfoActivity.getCallingIntent(fragmentMine.getActivity(), fragmentMine.headpath));
        } else {
            fragmentMine.startActivity(LoginActivity.getCallingIntent(fragmentMine.getActivity()));
        }
    }

    public static /* synthetic */ void lambda$bindListener$1(FragmentMine fragmentMine, Void r2) {
        if (TextUtils.isEmpty(fragmentMine.tvPhone.getText().toString())) {
            return;
        }
        final CommonDialog showPhoneDialog = CommonUtils.showPhoneDialog(fragmentMine.getActivity(), fragmentMine.tvPhone.getText().toString());
        CommonUtils.setOnSureClickListener(new CommonUtils.OnSureClickListener() { // from class: com.yltx.oil.partner.modules.mine.fragment.FragmentMine.1
            {
                FragmentMine.this = fragmentMine;
            }

            @Override // com.yltx.oil.partner.utils.CommonUtils.OnSureClickListener
            public void onSureClick(View view) {
                showPhoneDialog.dismiss();
                CommonUtils.goToPhone(FragmentMine.this.getActivity(), FragmentMine.this.tvPhone.getText().toString());
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$2(FragmentMine fragmentMine, Void r30) {
        TextView textView;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        TextView textView7;
        TextView textView8;
        TextView textView9;
        TextView textView10;
        View inflate = LayoutInflater.from(fragmentMine.view.getContext()).inflate(R.layout.layout_popup_view, (ViewGroup) null);
        TextView textView11 = (TextView) inflate.findViewById(R.id.tv_1);
        TextView textView12 = (TextView) inflate.findViewById(R.id.tv_1name);
        TextView textView13 = (TextView) inflate.findViewById(R.id.tv_1money);
        TextView textView14 = (TextView) inflate.findViewById(R.id.tv_2);
        TextView textView15 = (TextView) inflate.findViewById(R.id.tv_2name);
        TextView textView16 = (TextView) inflate.findViewById(R.id.tv_2money);
        TextView textView17 = (TextView) inflate.findViewById(R.id.tv_3);
        TextView textView18 = (TextView) inflate.findViewById(R.id.tv_3name);
        TextView textView19 = (TextView) inflate.findViewById(R.id.tv_3money);
        TextView textView20 = (TextView) inflate.findViewById(R.id.tv_4);
        TextView textView21 = (TextView) inflate.findViewById(R.id.tv_4mame);
        TextView textView22 = (TextView) inflate.findViewById(R.id.tv_4money);
        TextView textView23 = (TextView) inflate.findViewById(R.id.tv_5);
        TextView textView24 = (TextView) inflate.findViewById(R.id.tv_5mame);
        TextView textView25 = (TextView) inflate.findViewById(R.id.tv_5money);
        TextView textView26 = (TextView) inflate.findViewById(R.id.tv_6);
        TextView textView27 = (TextView) inflate.findViewById(R.id.tv_6mame);
        List<MemberResponse> list = fragmentMine.memberResponses;
        TextView textView28 = (TextView) inflate.findViewById(R.id.tv_6money);
        if (list != null && fragmentMine.memberResponses.size() > 0) {
            int i = 0;
            while (i < fragmentMine.memberResponses.size()) {
                if (fragmentMine.memberResponses.get(i) != null) {
                    if (i == 0) {
                        StringBuilder sb = new StringBuilder();
                        textView9 = textView23;
                        sb.append(fragmentMine.memberResponses.get(i).getLevel());
                        sb.append("");
                        textView11.setText(sb.toString());
                        textView12.setText(fragmentMine.memberResponses.get(i).getLevelName());
                        textView = textView11;
                        textView2 = textView12;
                        textView13.setText(new BigDecimal(Double.parseDouble(fragmentMine.memberResponses.get(i).getSale())).setScale(0, 4).toString());
                    } else {
                        textView = textView11;
                        textView2 = textView12;
                        textView9 = textView23;
                        if (i == 1) {
                            textView14.setText(fragmentMine.memberResponses.get(i).getLevel() + "");
                            textView15.setText(fragmentMine.memberResponses.get(i).getLevelName());
                            textView16.setText(new BigDecimal(Double.parseDouble(fragmentMine.memberResponses.get(i).getSale())).setScale(0, 4).toString());
                        } else if (i == 2) {
                            textView17.setText(fragmentMine.memberResponses.get(i).getLevel() + "");
                            textView18.setText(fragmentMine.memberResponses.get(i).getLevelName());
                            textView19.setText(new BigDecimal(Double.parseDouble(fragmentMine.memberResponses.get(i).getSale())).setScale(0, 4).toString());
                        } else if (i == 3) {
                            textView20.setText(fragmentMine.memberResponses.get(i).getLevel() + "");
                            textView21.setText(fragmentMine.memberResponses.get(i).getLevelName());
                            textView22.setText(new BigDecimal(Double.parseDouble(fragmentMine.memberResponses.get(i).getSale())).setScale(0, 4).toString());
                        } else {
                            if (i == 4) {
                                textView23 = textView9;
                                textView23.setText(fragmentMine.memberResponses.get(i).getLevel() + "");
                                textView4 = textView24;
                                textView4.setText(fragmentMine.memberResponses.get(i).getLevelName());
                                textView3 = textView13;
                                textView10 = textView25;
                                textView10.setText(new BigDecimal(Double.parseDouble(fragmentMine.memberResponses.get(i).getSale())).setScale(0, 4).toString());
                            } else {
                                textView3 = textView13;
                                textView4 = textView24;
                                textView10 = textView25;
                                textView23 = textView9;
                                if (i == 5) {
                                    TextView textView29 = textView26;
                                    textView29.setText(fragmentMine.memberResponses.get(i).getLevel() + "");
                                    textView5 = textView10;
                                    TextView textView30 = textView27;
                                    textView30.setText(fragmentMine.memberResponses.get(i).getLevelName());
                                    textView7 = textView30;
                                    textView6 = textView29;
                                    textView8 = textView28;
                                    textView8.setText(new BigDecimal(Double.parseDouble(fragmentMine.memberResponses.get(i).getSale())).setScale(0, 4).toString());
                                    i++;
                                    textView28 = textView8;
                                    textView24 = textView4;
                                    textView11 = textView;
                                    textView12 = textView2;
                                    textView13 = textView3;
                                    textView25 = textView5;
                                    textView27 = textView7;
                                    textView26 = textView6;
                                }
                            }
                            textView5 = textView10;
                        }
                    }
                    textView3 = textView13;
                    textView4 = textView24;
                    textView5 = textView25;
                    textView6 = textView26;
                    textView7 = textView27;
                    textView8 = textView28;
                    textView23 = textView9;
                    i++;
                    textView28 = textView8;
                    textView24 = textView4;
                    textView11 = textView;
                    textView12 = textView2;
                    textView13 = textView3;
                    textView25 = textView5;
                    textView27 = textView7;
                    textView26 = textView6;
                } else {
                    textView = textView11;
                    textView2 = textView12;
                    textView3 = textView13;
                    textView4 = textView24;
                    textView5 = textView25;
                }
                textView6 = textView26;
                textView7 = textView27;
                textView8 = textView28;
                i++;
                textView28 = textView8;
                textView24 = textView4;
                textView11 = textView;
                textView12 = textView2;
                textView13 = textView3;
                textView25 = textView5;
                textView27 = textView7;
                textView26 = textView6;
            }
        }
        fragmentMine.popupWindow = new PopupWindow(inflate, -2, -2);
        fragmentMine.popupWindow.setFocusable(true);
        fragmentMine.popupWindow.setOutsideTouchable(false);
        fragmentMine.popupWindow.setBackgroundDrawable(new BitmapDrawable());
        if (Build.VERSION.SDK_INT >= 22) {
            fragmentMine.popupWindow.showAsDropDown(fragmentMine.tvXiaoshoue);
        } else {
            fragmentMine.popupWindow.showAsDropDown(fragmentMine.tvXiaoshoue);
        }
    }

    public static /* synthetic */ void lambda$bindListener$3(FragmentMine fragmentMine, Void r2) {
        if (PartnerApplication.instance.isLoading) {
            fragmentMine.startActivity(MineInfoActivity.getCallingIntent(fragmentMine.getActivity(), fragmentMine.headpath));
        } else {
            fragmentMine.startActivity(LoginActivity.getCallingIntent(fragmentMine.getActivity()));
        }
    }

    public static /* synthetic */ void lambda$bindListener$4(FragmentMine fragmentMine, Void r1) {
        if (PartnerApplication.instance.isLoading) {
            fragmentMine.startActivity(FeedbackActivity.getCallingIntent(fragmentMine.getActivity()));
        } else {
            fragmentMine.startActivity(LoginActivity.getCallingIntent(fragmentMine.getActivity()));
        }
    }

    public static /* synthetic */ void lambda$bindListener$5(FragmentMine fragmentMine, Void r1) {
        if (PartnerApplication.instance.isLoading) {
            fragmentMine.startActivity(ComplaintActivity.getCallingIntent(fragmentMine.getActivity()));
        } else {
            fragmentMine.startActivity(LoginActivity.getCallingIntent(fragmentMine.getActivity()));
        }
    }

    public static /* synthetic */ void lambda$bindListener$6(FragmentMine fragmentMine, Void r5) {
        fragmentMine.getNavigator().navigateToJsBridgeWebActivity(fragmentMine.getContext(), "帮助中心", Config.API_WeiXin_URL.concat("#/partner/helpcenter"));
    }

    public static /* synthetic */ void lambda$bindListener$7(FragmentMine fragmentMine, Void r5) {
        fragmentMine.getNavigator().navigateToJsBridgeWebActivity(fragmentMine.getContext(), "新手指引", Config.API_WeiXin_URL.concat("#/partner/newGuidelines"));
    }

    public static /* synthetic */ void lambda$bindListener$8(FragmentMine fragmentMine, Void r2) {
        if (PartnerApplication.instance.isLoading) {
            fragmentMine.startActivity(MineInfoActivity.getCallingIntent(fragmentMine.getActivity(), fragmentMine.headpath));
        } else {
            fragmentMine.startActivity(LoginActivity.getCallingIntent(fragmentMine.getActivity()));
        }
    }

    public static /* synthetic */ void lambda$bindListener$9(FragmentMine fragmentMine, Void r1) {
        if (PartnerApplication.instance.isLoading) {
            fragmentMine.startActivity(InviteCourtesyActivity.getCallingIntent(fragmentMine.getActivity()));
        } else {
            fragmentMine.startActivity(LoginActivity.getCallingIntent(fragmentMine.getActivity()));
        }
    }

    public static /* synthetic */ void lambda$bindListener$10(FragmentMine fragmentMine, Void r4) {
        fragmentMine.popupWindow1 = new PopupWindow(LayoutInflater.from(fragmentMine.view.getContext()).inflate(R.layout.layout_pop_text, (ViewGroup) null), -2, -2);
        fragmentMine.popupWindow1.setFocusable(true);
        fragmentMine.popupWindow1.setOutsideTouchable(false);
        fragmentMine.popupWindow1.setBackgroundDrawable(new BitmapDrawable());
        if (Build.VERSION.SDK_INT >= 22) {
            fragmentMine.popupWindow1.showAsDropDown(fragmentMine.tvYongj, -70, 0);
        } else {
            fragmentMine.popupWindow1.showAsDropDown(fragmentMine.tvYongj, -70, 0);
        }
    }

    public static /* synthetic */ void lambda$bindListener$11(FragmentMine fragmentMine) {
        fragmentMine.memberPresenter.PersonalCenter();
    }

    private void initView() {
        Log.v("httpl==", PartnerApplication.instance.isLoading + "");
        this.mRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimary, R.color.colorPrimary, R.color.colorPrimary);
        if (PartnerApplication.instance.isLoading) {
            this.tvGo.setVisibility(8);
            this.tv_dl.setVisibility(8);
            LoginInfo loginInfo = (LoginInfo) new Gson().fromJson(DataCache.getUserToken(getActivity()), new TypeToken<LoginInfo>() { // from class: com.yltx.oil.partner.modules.mine.fragment.FragmentMine.2
                {
                    FragmentMine.this = this;
                }
            }.getType());
            String phone = loginInfo.getUserInfo().getPhone();
            this.tvMinePhone.setText(phone.substring(0, 3) + "****" + phone.substring(7, phone.length()));
            TextView textView = this.tvMineId;
            textView.setText("ID:" + loginInfo.getUserInfo().getNickname());
            if (!TextUtils.isEmpty(loginInfo.getUserInfo().getAvatar())) {
                this.headpath = loginInfo.getUserInfo().getAvatar();
                if (!TextUtils.isEmpty(loginInfo.getHeadPic())) {
                    AlbumDisplayUtils.displayHeaderImg(getActivity(), this.fragmentMineHeadIv, loginInfo.getUserInfo().getAvatar());
                }
            } else {
                this.fragmentMineHeadIv.setImageResource(R.drawable.head);
            }
            if (loginInfo.getPartnerInfo().getIsPartner().equals("1")) {
                this.llDengji.setVisibility(0);
            } else {
                this.llDengji.setVisibility(8);
            }
            this.imDengji.setVisibility(8);
            this.tvDengji.setVisibility(0);
            this.tvYongj.setVisibility(0);
            this.tvXiaoshoue.setVisibility(0);
            return;
        }
        this.fragmentMineHeadIv.setImageResource(R.drawable.head);
        this.tv_dl.setVisibility(0);
        this.imDengji.setVisibility(8);
        this.tvDengji.setVisibility(8);
        this.tvYongj.setVisibility(8);
        this.tvXiaoshoue.setVisibility(8);
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        Log.v("httpl==onResume", PartnerApplication.instance.isLoading + "");
        if (!TextUtils.isEmpty(DataCache.getToken(getActivity()))) {
            this.memberPresenter.PersonalCenter();
        }
        initView();
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    @Override // com.yltx.oil.partner.modules.mine.view.MemberView
    public void onMember(HttpResult<List<MemberResponse>> httpResult) {
        this.memberResponses = httpResult.getData();
    }

    @Override // com.yltx.oil.partner.modules.mine.view.MemberView
    public void onError(Throwable th) {
        if (this.mRefresh != null) {
            this.mRefresh.setRefreshing(false);
        }
    }

    @Override // com.yltx.oil.partner.modules.mine.view.MemberView
    public void onphone(HttpResult<PhoneResp> httpResult) {
        this.tvPhone.setText(httpResult.getData().getPhone());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.yltx.oil.partner.modules.mine.view.MemberView
    public void onPersonalCenter(HttpResult<LoginInfo> httpResult) {
        char c;
        if (this.mRefresh != null) {
            this.mRefresh.setRefreshing(false);
        }
        if (httpResult.getData().getIsPartner().equals("1")) {
            this.llDengji.setVisibility(0);
            this.tvDengji.setText(httpResult.getData().getLevelName());
            this.imDji.setVisibility(8);
            String partnerLevel = httpResult.getData().getPartnerLevel();
            switch (partnerLevel.hashCode()) {
                case 2715:
                    if (partnerLevel.equals("V1")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 2716:
                    if (partnerLevel.equals("V2")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 2717:
                    if (partnerLevel.equals("V3")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    this.imDji.setImageResource(R.mipmap.icon_v1);
                    break;
                case 1:
                    this.imDji.setImageResource(R.mipmap.icon_v2);
                    break;
                case 2:
                    this.imDji.setImageResource(R.mipmap.icon_v3);
                    break;
                default:
                    this.imDji.setVisibility(8);
                    break;
            }
        } else {
            this.llDengji.setVisibility(8);
            this.imDji.setVisibility(8);
        }
        this.tv_dl.setVisibility(8);
        this.tvGo.setVisibility(8);
        PartnerApplication.getInstance().getUserInfos().getPartnerInfo().setIsPartner(httpResult.getData().getIsPartner());
        String phone = httpResult.getData().getPhone();
        this.tvMinePhone.setText(phone.substring(0, 3) + "****" + phone.substring(7, phone.length()));
        TextView textView = this.tvMineId;
        textView.setText("ID:" + httpResult.getData().getNickname());
        this.tvXiaoshoue.setText(new BigDecimal(httpResult.getData().getSaleNeed()).setScale(2, 4).toString());
        BigDecimal scale = new BigDecimal(httpResult.getData().getGoodProportion()).setScale(0, 4);
        TextView textView2 = this.tvYongj;
        textView2.setText(scale.toString() + "%");
        if (!TextUtils.isEmpty(httpResult.getData().getHeadPic())) {
            this.headpath = httpResult.getData().getHeadPic();
            AlbumDisplayUtils.displayHeaderImg(getActivity(), this.fragmentMineHeadIv, httpResult.getData().getHeadPic());
            return;
        }
        this.fragmentMineHeadIv.setImageResource(R.drawable.head);
    }
}