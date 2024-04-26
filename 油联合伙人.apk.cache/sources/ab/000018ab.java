package com.yltx.oil.partner.modules.mine.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.internal.view.SupportMenu;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.data.response.LnvoicePayResp;
import com.yltx.oil.partner.data.response.PayResult;
import com.yltx.oil.partner.data.response.RechargePayTypeResp;
import com.yltx.oil.partner.modules.mine.adapter.RecharhePayTypeAdapter;
import com.yltx.oil.partner.modules.mine.presenter.RechargePresenter;
import com.yltx.oil.partner.modules.mine.view.RechargeView;
import com.yltx.oil.partner.utils.AliPay;
import com.yltx.oil.partner.utils.ToastUtil;
import com.yltx.oil.partner.utils.XTViewUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class RechargeActivity extends ToolBarActivity implements RechargeView {
    public static final String ORDER_RECHARGE_OIL = "13";
    public static Activity RechargeActivity;
    @BindView(R.id.btn_my_submit)
    Button btn_my_submit;
    Button btn_zf_submit;
    ImageView closed;
    public Dialog dialog;
    @BindView(R.id.et_my_input_money)
    EditText et_my_input_money;
    RecharhePayTypeAdapter mAdapter;
    @Inject
    RechargePresenter mPresenter;
    private Dialog mProgressDialog;
    RecyclerView mRecyclerView;
    private List<RechargePayTypeResp.OutPayBean.OutPayListBean> rechargePayTypeRespList;
    @BindView(R.id.tv_cjgz)
    TextView tv_cjgz;
    private String recharge = "充值规则\n\n  1.用户在使用平台加油或购买其它产品时若出现银行或第三方支付限额的情况，可多次充值到油联账户后再进行加油或购买其它产品。\n\n  2.输入充值金额，跳转至银行或者第三方支付页面，按照页面的提示输入银行账户和密码等信息即可完成充值。\n\n  3.请注意您的银行卡或第三方支付的充值限制，大额资金请选择个人或企业网银进行支付。\n\n  4.充值成功后，若无消费，30天内不可提现。\n\n  5.禁止洗钱、信用卡套现、虚假交易等行为，一经发现将予以处罚，包括但不限于：限制收款、提现、冻结账户等，并追究相关法律责任。\n\n  如有疑问，请联系油联客服4001077728\n\n";
    String type = "";
    @SuppressLint({"HandlerLeak"})
    public Handler mHandler = new Handler() { // from class: com.yltx.oil.partner.modules.mine.activity.RechargeActivity.2
        {
            RechargeActivity.this = this;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            PayResult payResult = new PayResult((Map) message.obj);
            payResult.getResult();
            String resultStatus = payResult.getResultStatus();
            if (TextUtils.equals(resultStatus, "9000")) {
                ToastUtil.showMiddleScreenToast("支付成功");
                RechargeActivity.this.finish();
            } else if (TextUtils.equals(resultStatus, "6001")) {
                ToastUtil.showMiddleScreenToast("支付宝支付已取消");
            } else if (TextUtils.equals(resultStatus, "8000")) {
                ToastUtil.showMiddleScreenToast("支付结果确认中");
            } else if (TextUtils.equals(resultStatus, "4000")) {
                ToastUtil.showMiddleScreenToast("支付失败");
            } else if (TextUtils.equals(resultStatus, "6002")) {
                ToastUtil.showMiddleScreenToast("网络异常");
            } else {
                ToastUtil.showMiddleScreenToast("支付失败");
            }
        }
    };

    public static /* synthetic */ void lambda$bindListener$1(Void r0) {
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, RechargeActivity.class);
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);
        this.mPresenter.attachView(this);
        RechargeActivity = this;
        setupUI();
        setupDialog();
        bindListener();
    }

    private void setupUI() {
        setToolbarTitle("账户充值");
        this.mBxHistory.setText("充值明细");
        this.mBxHistory.setVisibility(0);
        this.rechargePayTypeRespList = new ArrayList();
        SpannableString spannableString = new SpannableString(this.recharge);
        Matcher matcher = Pattern.compile("4.充值成功后，若无消费，30天内不可提现。").matcher(spannableString);
        while (matcher.find()) {
            spannableString.setSpan(new ForegroundColorSpan((int) SupportMenu.CATEGORY_MASK), matcher.start(), matcher.end(), 33);
        }
        this.tv_cjgz.setText(spannableString);
    }

    private void bindListener() {
        Rx.click(this.btn_my_submit, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$RechargeActivity$HjeH0LTRDLQoeE6iuHGR6p99Fq8
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                RechargeActivity.lambda$bindListener$0(RechargeActivity.this, (Void) obj);
            }
        });
        Rx.click(this.mBxHistory, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$RechargeActivity$Aj_cQTepO06twq8cRC1_s2l6PPI
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                RechargeActivity.lambda$bindListener$1((Void) obj);
            }
        });
        this.mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.yltx.oil.partner.modules.mine.activity.RechargeActivity.1
            {
                RechargeActivity.this = this;
            }

            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                for (int i2 = 0; i2 < RechargeActivity.this.rechargePayTypeRespList.size(); i2++) {
                    if (i2 == i) {
                        ((RechargePayTypeResp.OutPayBean.OutPayListBean) RechargeActivity.this.rechargePayTypeRespList.get(i)).setChecked(true);
                        RechargeActivity.this.type = ((RechargePayTypeResp.OutPayBean.OutPayListBean) RechargeActivity.this.rechargePayTypeRespList.get(i)).getType();
                    } else {
                        ((RechargePayTypeResp.OutPayBean.OutPayListBean) RechargeActivity.this.rechargePayTypeRespList.get(i2)).setChecked(false);
                    }
                }
                Log.d(">>>>>", ">>>>");
                RechargeActivity.this.mAdapter.notifyDataSetChanged();
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(RechargeActivity rechargeActivity, Void r5) {
        if (TextUtils.isEmpty(rechargeActivity.et_my_input_money.getText().toString())) {
            ToastUtil.showMiddleScreenLongToast("请输入充值金额");
        } else if (Double.parseDouble(rechargeActivity.et_my_input_money.getText().toString()) <= 0.0d) {
            ToastUtil.showMiddleScreenLongToast("请输入正确的金额");
        } else {
            rechargeActivity.mPresenter.getOutPayTypeList();
        }
    }

    private void setupDialog() {
        this.dialog = new Dialog(this, R.style.Theme_Light_Dialog);
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_zf_detail, (ViewGroup) null);
        this.mRecyclerView = (RecyclerView) inflate.findViewById(R.id.rv_zf_list);
        this.closed = (ImageView) inflate.findViewById(R.id.iv_zf_detailed_close);
        this.btn_zf_submit = (Button) inflate.findViewById(R.id.btn_zf_submit);
        Rx.click(this.btn_zf_submit, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$RechargeActivity$kDc5uObh_2Z6Wyf3MOe5eXDExdY
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                RechargeActivity.lambda$setupDialog$2(RechargeActivity.this, (Void) obj);
            }
        });
        Rx.click(this.closed, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$RechargeActivity$6qV34HpMgCrFls1bFPpmLY0IV_Y
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                RechargeActivity.lambda$setupDialog$3(RechargeActivity.this, (Void) obj);
            }
        });
        this.mAdapter = new RecharhePayTypeAdapter(this.rechargePayTypeRespList);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mRecyclerView.setAdapter(this.mAdapter);
        Window window = this.dialog.getWindow();
        window.setGravity(17);
        window.setWindowAnimations(R.style.dialogStyle);
        window.getDecorView().setPadding(XTViewUtils.dp2pix(this, 15), 0, XTViewUtils.dp2pix(this, 15), 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        window.setAttributes(attributes);
        this.dialog.setContentView(inflate);
        this.dialog.setCancelable(true);
        this.dialog.setCanceledOnTouchOutside(true);
    }

    public static /* synthetic */ void lambda$setupDialog$2(RechargeActivity rechargeActivity, Void r7) {
        if (TextUtils.isEmpty(rechargeActivity.type)) {
            ToastUtil.showMiddleScreenToast("请选择支付方式");
            return;
        }
        rechargeActivity.dialog.dismiss();
        rechargeActivity.mPresenter.generateRecord(rechargeActivity.et_my_input_money.getText().toString(), rechargeActivity.et_my_input_money.getText().toString(), rechargeActivity.type, "", rechargeActivity.et_my_input_money.getText().toString());
        rechargeActivity.showProgress();
    }

    public static /* synthetic */ void lambda$setupDialog$3(RechargeActivity rechargeActivity, Void r1) {
        rechargeActivity.dialog.dismiss();
    }

    @Override // com.yltx.oil.partner.modules.mine.view.RechargeView
    public void onPayTypeSuccess(RechargePayTypeResp rechargePayTypeResp) {
        if (rechargePayTypeResp.getOutPay() == null || rechargePayTypeResp.getOutPay().getOutPayList().size() <= 0) {
            return;
        }
        setData(rechargePayTypeResp.getOutPay().getOutPayList());
    }

    @Override // com.yltx.oil.partner.modules.mine.view.RechargeView
    public void onPaySuccess(LnvoicePayResp lnvoicePayResp) {
        hideProgress();
        if (TextUtils.isEmpty(this.type)) {
            return;
        }
        String str = this.type;
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != -385220034) {
            if (hashCode == 377480526 && str.equals("ptalipay")) {
                c = 0;
            }
        } else if (str.equals("ptwechatpay")) {
            c = 1;
        }
        switch (c) {
            case 0:
                AliPay.payV2(this, lnvoicePayResp.getAliPayStr(), this.mHandler);
                return;
            case 1:
                Bundle bundle = new Bundle();
                bundle.putString("orderId", lnvoicePayResp.getOrderId());
                bundle.putString("appId", lnvoicePayResp.getAppid());
                bundle.putString("partnerId", lnvoicePayResp.getPartnerid());
                bundle.putString("prepayId", lnvoicePayResp.getPrepayid());
                bundle.putString("nonceStr", lnvoicePayResp.getNoncestr());
                bundle.putString("timeStamp", lnvoicePayResp.getTimestamp());
                bundle.putString("sign", lnvoicePayResp.getSign());
                bundle.putString("orderType", ORDER_RECHARGE_OIL);
                getNavigator().navigateToWechatPay(getContext(), bundle);
                return;
            default:
                return;
        }
    }

    @Override // com.yltx.oil.partner.modules.mine.view.RechargeView
    public void onPayTypeError(Throwable th) {
        Log.d(">>>>>支付出错", ">>>>>" + th.getMessage());
        hideProgress();
        ToastUtil.showMiddleScreenLongToast(th.getMessage());
    }

    public void setData(List<RechargePayTypeResp.OutPayBean.OutPayListBean> list) {
        this.rechargePayTypeRespList.clear();
        for (int i = 0; i < list.size(); i++) {
            RechargePayTypeResp.OutPayBean.OutPayListBean outPayListBean = new RechargePayTypeResp.OutPayBean.OutPayListBean();
            outPayListBean.setName(list.get(i).getName());
            outPayListBean.setType(list.get(i).getType());
            outPayListBean.setChecked(false);
            this.rechargePayTypeRespList.add(outPayListBean);
        }
        this.mAdapter.notifyDataSetChanged();
        this.dialog.show();
    }

    @Override // com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.mvp.views.ProgressView
    public void showProgress() {
        if (this.mProgressDialog == null) {
            this.mProgressDialog = new Dialog(this, R.style.AppTheme_Dialogstyle);
            this.mProgressDialog.setCancelable(false);
            this.mProgressDialog.setCanceledOnTouchOutside(false);
        }
        this.mProgressDialog.show();
        View inflate = LayoutInflater.from(this).inflate(R.layout.custom_progressbar, (ViewGroup) null);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.mipmap.loading)).asGif().into((ImageView) inflate.findViewById(R.id.loading_view));
        this.mProgressDialog.setContentView(inflate);
    }

    @Override // com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.mvp.views.ProgressView
    public void hideProgress() {
        if (this.mProgressDialog == null || !this.mProgressDialog.isShowing()) {
            return;
        }
        this.mProgressDialog.dismiss();
    }
}