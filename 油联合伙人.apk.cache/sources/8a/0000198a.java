package com.yltx.oil.partner.modules.profit.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.login.presenter.GetValidCodePresenter;
import com.yltx.oil.partner.modules.login.view.GetValidCodeView;
import com.yltx.oil.partner.modules.profit.presenter.BindingBankPresenter;
import com.yltx.oil.partner.modules.profit.view.BindingBankView;
import com.yltx.oil.partner.utils.CommonUtils;
import com.yltx.oil.partner.utils.StringUtils;
import com.yltx.oil.partner.utils.ToastUtil;
import com.yltx.oil.partner.widget.ZoomButton;
import javax.inject.Inject;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class BindingbankcardsActivity extends ToolBarActivity implements BindingBankView, GetValidCodeView {
    @Inject
    BindingBankPresenter bindingBankPresenter;
    @BindView(R.id.btn_complete)
    TextView btnComplete;
    @BindView(R.id.et_cardno)
    EditText etCardno;
    @BindView(R.id.et_idcard)
    EditText etIdcard;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @Inject
    GetValidCodePresenter getValidCodePresenter;
    String id;
    String name;
    private int time;
    @BindView(R.id.tv_get_validCode)
    ZoomButton tvGetValidCode;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    private int type;
    private Handler handler = new Handler();
    private Runnable timer = new Runnable() { // from class: com.yltx.oil.partner.modules.profit.activity.BindingbankcardsActivity.1
        {
            BindingbankcardsActivity.this = this;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (BindingbankcardsActivity.this.time <= 0) {
                BindingbankcardsActivity.this.handler.removeCallbacksAndMessages(null);
                BindingbankcardsActivity.this.tvGetValidCode.setEnabled(true);
                BindingbankcardsActivity.this.tvGetValidCode.setText(BindingbankcardsActivity.this.getString(R.string.get_sms_code));
            } else {
                BindingbankcardsActivity.this.tvGetValidCode.setEnabled(false);
                ZoomButton zoomButton = BindingbankcardsActivity.this.tvGetValidCode;
                zoomButton.setText(BindingbankcardsActivity.this.time + BindingbankcardsActivity.this.getString(R.string.send_again));
                BindingbankcardsActivity.this.handler.postDelayed(this, 1000L);
            }
            BindingbankcardsActivity.access$010(BindingbankcardsActivity.this);
        }
    };

    @Override // com.yltx.oil.partner.modules.profit.view.BindingBankView
    public void onError(Throwable th) {
    }

    static /* synthetic */ int access$010(BindingbankcardsActivity bindingbankcardsActivity) {
        int i = bindingbankcardsActivity.time;
        bindingbankcardsActivity.time = i - 1;
        return i;
    }

    public static Intent getCallingIntent(Context context, int i, String str, String str2) {
        Intent intent = new Intent(context, BindingbankcardsActivity.class);
        intent.putExtra("type", i);
        intent.putExtra("name", str);
        intent.putExtra("id", str2);
        return intent;
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_bindingbankcards);
        ButterKnife.bind(this);
        this.bindingBankPresenter.attachView(this);
        this.getValidCodePresenter.attachView(this);
        this.type = getIntent().getIntExtra("type", 0);
        setupUI();
        bindListener();
    }

    private void setupUI() {
        if (this.type == 0) {
            setToolbarTitle("绑定银行卡");
            this.btnComplete.setText("提交");
            return;
        }
        setToolbarTitle("修改银行卡");
        this.name = getIntent().getStringExtra("name");
        this.id = getIntent().getStringExtra("id");
        this.etName.setText(this.name);
        this.etIdcard.setText(this.id);
        this.btnComplete.setText("修改");
    }

    private void bindListener() {
        Rx.click(this.tvGetValidCode, new Action1() { // from class: com.yltx.oil.partner.modules.profit.activity.-$$Lambda$BindingbankcardsActivity$BvWTR2G3hoNB-ZQT8ZjWNsSJjxg
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BindingbankcardsActivity.lambda$bindListener$0(BindingbankcardsActivity.this, (Void) obj);
            }
        });
        Rx.click(this.btnComplete, new Action1() { // from class: com.yltx.oil.partner.modules.profit.activity.-$$Lambda$BindingbankcardsActivity$KSMTLpDWaXXs4NTknVvabYwX3VQ
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BindingbankcardsActivity.lambda$bindListener$1(BindingbankcardsActivity.this, (Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(BindingbankcardsActivity bindingbankcardsActivity, Void r3) {
        if (CommonUtils.isFastClick(300L)) {
            return;
        }
        bindingbankcardsActivity.getSmsCode();
    }

    public static /* synthetic */ void lambda$bindListener$1(BindingbankcardsActivity bindingbankcardsActivity, Void r13) {
        if (TextUtils.isEmpty(bindingbankcardsActivity.etName.getText().toString())) {
            ToastUtil.showMiddleScreenToast("请输入姓名");
        } else if (TextUtils.isEmpty(bindingbankcardsActivity.etIdcard.getText().toString())) {
            ToastUtil.showMiddleScreenToast("请输入身份证号");
        } else if (TextUtils.isEmpty(bindingbankcardsActivity.etCardno.getText().toString())) {
            ToastUtil.showMiddleScreenToast("请输入银行卡号");
        } else if (TextUtils.isEmpty(bindingbankcardsActivity.tvPhone.getText().toString())) {
            ToastUtil.showMiddleScreenToast("请输入验证码");
        } else if (bindingbankcardsActivity.type == 0) {
            bindingbankcardsActivity.bindingBankPresenter.getBindingBank(bindingbankcardsActivity.etName.getText().toString(), bindingbankcardsActivity.etIdcard.getText().toString(), bindingbankcardsActivity.etCardno.getText().toString(), bindingbankcardsActivity.etPhone.getText().toString(), bindingbankcardsActivity.tvPhone.getText().toString());
        } else {
            bindingbankcardsActivity.bindingBankPresenter.uPBankcards(bindingbankcardsActivity.etName.getText().toString(), bindingbankcardsActivity.etIdcard.getText().toString(), bindingbankcardsActivity.etCardno.getText().toString(), bindingbankcardsActivity.etPhone.getText().toString(), bindingbankcardsActivity.tvPhone.getText().toString());
        }
    }

    private void getSmsCode() {
        String trim = this.etPhone.getText().toString().trim();
        if (trim.equals("")) {
            ToastUtil.showMiddleScreenToast(getString(R.string.pls_enter_phone_num));
        } else if (!StringUtils.isMobile(trim)) {
            ToastUtil.showMiddleScreenToast(getString(R.string.pls_enter_right_phonenum));
        } else {
            this.getValidCodePresenter.submitLoginsm(trim, "bandbankcard", "", "");
        }
    }

    @Override // com.yltx.oil.partner.base.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacksAndMessages(null);
    }

    @Override // com.yltx.oil.partner.modules.profit.view.BindingBankView
    public void onBindingBank(HttpResult<String> httpResult) {
        ToastUtil.showMiddleScreenToast("绑定成功");
    }

    @Override // com.yltx.oil.partner.modules.profit.view.BindingBankView
    public void onUpBindingBank(HttpResult<String> httpResult) {
        ToastUtil.showMiddleScreenToast("修改成功");
        finish();
    }

    @Override // com.yltx.oil.partner.modules.login.view.GetValidCodeView
    public void onsmsSuccess(HttpResult<String> httpResult) {
        ToastUtil.showMiddleScreenToast("已发送");
        this.handler.removeCallbacksAndMessages(null);
        this.time = 60;
        this.handler.post(this.timer);
    }
}