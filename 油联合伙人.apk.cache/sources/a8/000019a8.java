package com.yltx.oil.partner.modules.profit.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.BankInfoResp;
import com.yltx.oil.partner.modules.profit.fragment.PayFragment;
import com.yltx.oil.partner.modules.profit.presenter.TxPresenter;
import com.yltx.oil.partner.modules.profit.view.TxView;
import com.yltx.oil.partner.utils.Md5;
import com.yltx.oil.partner.utils.ToastUtil;
import com.yltx.oil.partner.widget.PayPwdView;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.inject.Inject;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class WithdrawActivity extends ToolBarActivity implements TxView, PayPwdView.InputCallBack {
    BigDecimal bigDecimal;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.et_input_money)
    EditText etInputMoney;
    String money;
    private PayFragment payFragment;
    private String payPassword;
    @Inject
    TxPresenter presenter;
    @BindView(R.id.tv_account_money)
    TextView tvAccountMoney;
    @BindView(R.id.tv_bank_no)
    TextView tvBankNo;
    @BindView(R.id.tv_txjl)
    TextView tvTxjl;
    @BindView(R.id.tv_xgyhk)
    TextView tvXgyhk;
    BigDecimal bigDmoney = null;
    DecimalFormat df = new DecimalFormat("######0.00");

    @Override // com.yltx.oil.partner.modules.profit.view.TxView
    public void applyFailed() {
    }

    public static Intent getCallingIntent(Context context, String str) {
        Intent intent = new Intent(context, WithdrawActivity.class);
        intent.putExtra("money", str);
        return intent;
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_withdraw_home);
        ButterKnife.bind(this);
        this.presenter.attachView(this);
        setupUI();
        bindListener();
        this.presenter.getBankCard();
    }

    protected void setupUI() {
        setToolbarTitle("提现");
        this.money = getIntent().getStringExtra("money");
        this.bigDmoney = new BigDecimal(this.money).setScale(2, 4);
        TextView textView = this.tvAccountMoney;
        textView.setText("您可提现额度：" + this.bigDmoney.toString());
    }

    protected void bindListener() {
        Rx.click(this.tvXgyhk, new Action1() { // from class: com.yltx.oil.partner.modules.profit.activity.-$$Lambda$WithdrawActivity$5kmOhYJlF2iHEyrgo-KYr1a9nWs
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                WithdrawActivity.lambda$bindListener$0(WithdrawActivity.this, (Void) obj);
            }
        });
        Rx.click(this.btnSubmit, new Action1() { // from class: com.yltx.oil.partner.modules.profit.activity.-$$Lambda$WithdrawActivity$-72UE1vS_vT8J6IgJ_bj_3OVU-g
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                WithdrawActivity.lambda$bindListener$1(WithdrawActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvTxjl, new Action1() { // from class: com.yltx.oil.partner.modules.profit.activity.-$$Lambda$WithdrawActivity$Wb0PVPwJ5XO1cdEAUojalJXjh0Y
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                WithdrawActivity.lambda$bindListener$2(WithdrawActivity.this, (Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(WithdrawActivity withdrawActivity, Void r2) {
        withdrawActivity.getNavigator().navigateToModificationBankCards(withdrawActivity.getContext());
    }

    public static /* synthetic */ void lambda$bindListener$1(WithdrawActivity withdrawActivity, Void r5) {
        if (!TextUtils.isEmpty(withdrawActivity.etInputMoney.getText().toString())) {
            withdrawActivity.bigDecimal = new BigDecimal(withdrawActivity.etInputMoney.getText().toString()).setScale(2, 4);
            if (withdrawActivity.bigDecimal.doubleValue() > withdrawActivity.bigDmoney.doubleValue()) {
                ToastUtil.showMiddleScreenToast("提现金额不能大于余额");
                return;
            } else if (withdrawActivity.bigDecimal.doubleValue() >= 100.0d) {
                withdrawActivity.getTixian(Math.min(withdrawActivity.bigDecimal.doubleValue(), withdrawActivity.bigDmoney.doubleValue()));
                return;
            } else {
                ToastUtil.showMiddleScreenToast("提现金额不能小于100");
                return;
            }
        }
        ToastUtil.showMiddleScreenToast("请输入提现金额");
    }

    public static /* synthetic */ void lambda$bindListener$2(WithdrawActivity withdrawActivity, Void r2) {
        withdrawActivity.getNavigator().navigateToTxHistoryActivity(withdrawActivity.getContext());
    }

    private void getTixian(double d) {
        Bundle bundle = new Bundle();
        bundle.putString(PayFragment.EXTRA_CONTENT, "提现：¥" + this.df.format(d).toString());
        this.payFragment = new PayFragment();
        this.payFragment.setArguments(bundle);
        this.payFragment.setPaySuccessCallBack(this);
        this.payFragment.show(getSupportFragmentManager(), "提现");
    }

    @Override // com.yltx.oil.partner.modules.profit.view.TxView
    public void applySuccess(HttpResult<BankInfoResp> httpResult) {
        TextView textView = this.tvBankNo;
        textView.setText("银行卡号：" + httpResult.getData().getBankNo());
    }

    @Override // com.yltx.oil.partner.modules.profit.view.TxView
    public void onApplySuccess() {
        getNavigator().navigateToTxHistoryActivity(this);
        finish();
    }

    @Override // com.yltx.oil.partner.widget.PayPwdView.InputCallBack
    public void onInputFinish(String str) {
        if (str == null || str.equals("")) {
            ToastUtil.showMiddleScreenToast("请输入支付密码");
            return;
        }
        this.payPassword = Md5.md5(str);
        this.presenter.TxApply(this.bigDecimal.toString(), this.payPassword);
    }
}