package com.yltx.oil.partner.modules.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.data.network.Config;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.mine.presenter.MinePhonePresenter;
import com.yltx.oil.partner.modules.mine.view.MineInfoPhoneView;
import com.yltx.oil.partner.utils.CommonUtils;
import com.yltx.oil.partner.utils.StringUtils;
import com.yltx.oil.partner.utils.ToastUtil;
import com.yltx.oil.partner.widget.ZoomButton;
import javax.inject.Inject;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class PhoneActivity extends ToolBarActivity implements MineInfoPhoneView {
    @BindView(R.id.code)
    ZoomButton code;
    @BindView(R.id.ll_sms)
    LinearLayout llSms;
    @Inject
    MinePhonePresenter mPresenter;
    String phone;
    @BindView(R.id.phone_sms_code)
    EditText phoneSmsCode;
    private int time;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    boolean isNext = false;
    private Handler handler = new Handler();
    private Runnable timer = new Runnable() { // from class: com.yltx.oil.partner.modules.mine.activity.PhoneActivity.1
        {
            PhoneActivity.this = this;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (PhoneActivity.this.time <= 0) {
                PhoneActivity.this.handler.removeCallbacksAndMessages(null);
                PhoneActivity.this.code.setEnabled(true);
                PhoneActivity.this.code.setText(PhoneActivity.this.getString(R.string.get_sms_code));
            } else {
                PhoneActivity.this.code.setEnabled(false);
                ZoomButton zoomButton = PhoneActivity.this.code;
                zoomButton.setText(PhoneActivity.this.time + PhoneActivity.this.getString(R.string.send_again));
                PhoneActivity.this.handler.postDelayed(this, 1000L);
            }
            PhoneActivity.access$010(PhoneActivity.this);
        }
    };

    static /* synthetic */ int access$010(PhoneActivity phoneActivity) {
        int i = phoneActivity.time;
        phoneActivity.time = i - 1;
        return i;
    }

    public static Intent getCallingIntent(Context context, String str) {
        Intent intent = new Intent(context, PhoneActivity.class);
        intent.putExtra(Config.PHONE, str);
        return intent;
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_phone);
        ButterKnife.bind(this);
        this.mPresenter.attachView(this);
        setupUI();
        bindListener();
    }

    private void bindListener() {
        Rx.click(this.code, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$PhoneActivity$8Ga-AFeVhSnPrzK9YJ5julvMqo8
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PhoneActivity.lambda$bindListener$0(PhoneActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvNext, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$PhoneActivity$d8ETx3gecsFimf9fCw14qLzfJqs
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PhoneActivity.lambda$bindListener$1(PhoneActivity.this, (Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(PhoneActivity phoneActivity, Void r3) {
        if (CommonUtils.isFastClick(300L)) {
            return;
        }
        phoneActivity.getSmsCode();
    }

    public static /* synthetic */ void lambda$bindListener$1(PhoneActivity phoneActivity, Void r3) {
        if (phoneActivity.isNext) {
            if (StringUtils.isMobileNO(phoneActivity.tvPhone.getText().toString()) && !TextUtils.isEmpty(phoneActivity.phoneSmsCode.getText().toString())) {
                phoneActivity.mPresenter.updatePhone(phoneActivity.tvPhone.getText().toString(), phoneActivity.phoneSmsCode.getText().toString());
            } else {
                ToastUtil.showMiddleScreenToast("手机号码或验证码错误");
            }
        } else if (StringUtils.isMobileNO(phoneActivity.tvPhone.getText().toString()) && !TextUtils.isEmpty(phoneActivity.phoneSmsCode.getText().toString())) {
            phoneActivity.mPresenter.checkValidCode(phoneActivity.tvPhone.getText().toString(), phoneActivity.phoneSmsCode.getText().toString());
        } else {
            ToastUtil.showMiddleScreenToast("手机号码或验证码错误");
        }
    }

    private void setupUI() {
        setToolbarTitle("修改手机号");
        this.phone = getIntent().getStringExtra(Config.PHONE);
        this.tvPhone.setText(this.phone);
        this.tvPhone.setFocusable(false);
        this.tvPhone.setFocusableInTouchMode(false);
    }

    private void getSmsCode() {
        String trim = this.tvPhone.getText().toString().trim();
        if (trim.equals("")) {
            ToastUtil.showMiddleScreenToast(getString(R.string.pls_enter_phone_num));
        } else if (!StringUtils.isMobile(trim)) {
            ToastUtil.showMiddleScreenToast(getString(R.string.pls_enter_right_phonenum));
        } else if (!this.isNext) {
            this.mPresenter.getVCode(trim, "checkphone");
        } else {
            this.mPresenter.getVCode(trim, "modifyphone");
        }
    }

    @Override // com.yltx.oil.partner.base.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacksAndMessages(null);
    }

    @Override // com.yltx.oil.partner.modules.mine.view.MineInfoPhoneView
    public void onSuccess(HttpResult<String> httpResult) {
        this.tvNext.setText("完成");
        this.tvPhone.setText("");
        this.tvPhone.setFocusableInTouchMode(true);
        this.tvPhone.setFocusable(true);
        this.phoneSmsCode.setText("");
        this.code.setEnabled(true);
        this.code.setText(getString(R.string.get_sms_code));
        this.handler.removeCallbacksAndMessages(null);
        this.isNext = true;
    }

    @Override // com.yltx.oil.partner.modules.mine.view.MineInfoPhoneView
    public void onSmsSuccess(HttpResult<String> httpResult) {
        ToastUtil.showMiddleScreenToast("验证码发送成功");
        this.handler.removeCallbacksAndMessages(null);
        this.time = 60;
        this.handler.post(this.timer);
    }

    @Override // com.yltx.oil.partner.modules.mine.view.MineInfoPhoneView
    public void onUpSuccess(HttpResult<String> httpResult) {
        ToastUtil.showMiddleScreenToast("修改成功");
        finish();
    }

    @Override // com.yltx.oil.partner.modules.mine.view.MineInfoPhoneView
    public void onError(Throwable th) {
        ToastUtil.showMiddleScreenToast(th.getMessage());
    }
}