package com.yltx.oil.partner.modules.login.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.PartnerApplication;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.LoginInfo;
import com.yltx.oil.partner.modules.login.presenter.GetValidCodePresenter;
import com.yltx.oil.partner.modules.login.presenter.LoginPresenter;
import com.yltx.oil.partner.modules.login.presenter.RegisterPresenter;
import com.yltx.oil.partner.modules.login.view.GetValidCodeView;
import com.yltx.oil.partner.modules.login.view.LoginView;
import com.yltx.oil.partner.modules.login.view.RegisterView;
import com.yltx.oil.partner.utils.BitmapUtils;
import com.yltx.oil.partner.utils.CommonUtils;
import com.yltx.oil.partner.utils.StringUtils;
import com.yltx.oil.partner.utils.ToastUtil;
import com.yltx.oil.partner.widget.ZoomButton;
import javax.inject.Inject;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class RegisterActivity extends ToolBarActivity implements GetValidCodeView, RegisterView, LoginView {
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.et_pw)
    EditText etPw;
    @BindView(R.id.et_surepw)
    EditText etSurepw;
    @BindView(R.id.et_vercode_verify)
    EditText etVercodeVerify;
    @BindView(R.id.get_sms_code_btn_verify)
    Button getSmsCodeBtnVerify;
    @Inject
    GetValidCodePresenter getValidCodePresenter;
    @BindView(R.id.image_verify)
    ImageView imageVerify;
    @BindView(R.id.iv_pw)
    ImageView ivPw;
    @BindView(R.id.iv_surepw)
    ImageView ivSurepw;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.ll_pw)
    LinearLayout llPw;
    @BindView(R.id.ll_xieyi)
    LinearLayout llXieyi;
    @Inject
    LoginPresenter mPresenter;
    @BindView(R.id.rb_agree)
    CheckBox rbAgree;
    @BindView(R.id.rb_yes)
    CheckBox rbYes;
    @Inject
    RegisterPresenter registerPresenter;
    @BindView(R.id.textView_image_verify)
    TextView textViewImageVerify;
    private int time;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.tv_regist_validCode)
    ZoomButton tvRegistValidCode;
    @BindView(R.id.tv_shoujih)
    TextView tvShoujih;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    private Handler handler = new Handler();
    private boolean ispwkan = false;
    private boolean isagpwkan = false;
    private Runnable timer = new Runnable() { // from class: com.yltx.oil.partner.modules.login.activity.RegisterActivity.1
        {
            RegisterActivity.this = this;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (RegisterActivity.this.time <= 0) {
                RegisterActivity.this.handler.removeCallbacksAndMessages(null);
                RegisterActivity.this.tvRegistValidCode.setEnabled(true);
                RegisterActivity.this.tvRegistValidCode.setText(RegisterActivity.this.getString(R.string.get_sms_code));
            } else {
                RegisterActivity.this.tvRegistValidCode.setEnabled(false);
                ZoomButton zoomButton = RegisterActivity.this.tvRegistValidCode;
                zoomButton.setText(RegisterActivity.this.time + RegisterActivity.this.getString(R.string.send_again));
                RegisterActivity.this.handler.postDelayed(this, 1000L);
            }
            RegisterActivity.access$010(RegisterActivity.this);
        }
    };

    @Override // com.yltx.oil.partner.modules.login.view.LoginView, com.yltx.oil.partner.modules.main.view.SplashView
    public void onError(Throwable th) {
    }

    static /* synthetic */ int access$010(RegisterActivity registerActivity) {
        int i = registerActivity.time;
        registerActivity.time = i - 1;
        return i;
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, RegisterActivity.class);
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        this.getValidCodePresenter.attachView(this);
        this.registerPresenter.attachView(this);
        this.mPresenter.attachView(this);
        setupUI();
        bindListener();
    }

    protected void setupUI() {
        setToolbarTitle("注册");
    }

    protected void bindListener() {
        Rx.click(this.getSmsCodeBtnVerify, new Action1() { // from class: com.yltx.oil.partner.modules.login.activity.-$$Lambda$RegisterActivity$cGzDRyzOC3j4GihCIUFuFxynpic
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                RegisterActivity.lambda$bindListener$0(RegisterActivity.this, (Void) obj);
            }
        });
        Rx.click(getToolbar(), new Action1() { // from class: com.yltx.oil.partner.modules.login.activity.-$$Lambda$RegisterActivity$hjM_mfrWVj8CBOaVdaNdRMFc8Uo
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                RegisterActivity.lambda$bindListener$1(RegisterActivity.this, (Void) obj);
            }
        });
        Rx.click(this.llXieyi, new Action1() { // from class: com.yltx.oil.partner.modules.login.activity.-$$Lambda$RegisterActivity$-f7xzGG4QAZ0Wft1BKK_SZ5RCjc
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                RegisterActivity.lambda$bindListener$2(RegisterActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvNext, new Action1() { // from class: com.yltx.oil.partner.modules.login.activity.-$$Lambda$RegisterActivity$yehneAgH_BrkjBm39QkJyBwSUKE
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                RegisterActivity.lambda$bindListener$3(RegisterActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvRegistValidCode, new Action1() { // from class: com.yltx.oil.partner.modules.login.activity.-$$Lambda$RegisterActivity$LXuNCknXSH5Et_wJBgEStl7wxX0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                RegisterActivity.lambda$bindListener$4(RegisterActivity.this, (Void) obj);
            }
        });
        Rx.click(this.ivPw, new Action1() { // from class: com.yltx.oil.partner.modules.login.activity.-$$Lambda$RegisterActivity$eO0HWAigU6WXX2DcXxfHFPieVyU
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                RegisterActivity.lambda$bindListener$5(RegisterActivity.this, (Void) obj);
            }
        });
        Rx.click(this.ivSurepw, new Action1() { // from class: com.yltx.oil.partner.modules.login.activity.-$$Lambda$RegisterActivity$3D8EDJXKUhW_tgEDblHfPWu0H38
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                RegisterActivity.lambda$bindListener$6(RegisterActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvSubmit, new Action1() { // from class: com.yltx.oil.partner.modules.login.activity.-$$Lambda$RegisterActivity$ow0yWZ3VhXPDnSYn5jx3kRjf7EY
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                RegisterActivity.lambda$bindListener$7(RegisterActivity.this, (Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(RegisterActivity registerActivity, Void r2) {
        registerActivity.etVercodeVerify.setText("");
        registerActivity.registerPresenter.getImgCode();
    }

    public static /* synthetic */ void lambda$bindListener$1(RegisterActivity registerActivity, Void r2) {
        if (registerActivity.llPw.getVisibility() == 0) {
            registerActivity.llPw.setVisibility(8);
            registerActivity.llPhone.setVisibility(0);
            return;
        }
        registerActivity.finish();
    }

    public static /* synthetic */ void lambda$bindListener$2(RegisterActivity registerActivity, Void r4) {
        registerActivity.getNavigator().navigateToJsBridgeWebActivity(registerActivity.getContext(), "", "http://weixin.ylsp188.com/#/agreement");
    }

    public static /* synthetic */ void lambda$bindListener$3(RegisterActivity registerActivity, Void r3) {
        if (!StringUtils.isMobile(registerActivity.tvPhone.getText().toString())) {
            ToastUtil.showMiddleScreenToast(registerActivity.getString(R.string.pls_enter_right_phonenum));
        } else if (registerActivity.rbAgree.isChecked()) {
            registerActivity.llPhone.setVisibility(8);
            registerActivity.llPw.setVisibility(0);
            registerActivity.tvShoujih.setText(registerActivity.tvPhone.getText().toString());
            if (CommonUtils.isFastClick(300L)) {
                return;
            }
            registerActivity.registerPresenter.getImgCode();
        } else {
            ToastUtil.showMiddleScreenToast("您需要同意油联尚品协议才能注册");
        }
    }

    public static /* synthetic */ void lambda$bindListener$4(RegisterActivity registerActivity, Void r3) {
        if (CommonUtils.isFastClick(300L)) {
            return;
        }
        registerActivity.getSmsCode();
    }

    public static /* synthetic */ void lambda$bindListener$5(RegisterActivity registerActivity, Void r3) {
        if (registerActivity.ispwkan) {
            registerActivity.ispwkan = false;
        } else {
            registerActivity.ispwkan = true;
        }
        registerActivity.iskanjian(registerActivity.ispwkan, registerActivity.etPw, registerActivity.ivPw);
    }

    public static /* synthetic */ void lambda$bindListener$6(RegisterActivity registerActivity, Void r3) {
        if (registerActivity.isagpwkan) {
            registerActivity.isagpwkan = false;
        } else {
            registerActivity.isagpwkan = true;
        }
        registerActivity.iskanjian(registerActivity.isagpwkan, registerActivity.etSurepw, registerActivity.ivSurepw);
    }

    public static /* synthetic */ void lambda$bindListener$7(RegisterActivity registerActivity, Void r4) {
        if (TextUtils.isEmpty(registerActivity.etVercodeVerify.getText().toString().trim())) {
            ToastUtil.showMiddleScreenToast(registerActivity.getString(R.string.pls_enter_image_code));
        } else if (!TextUtils.isEmpty(registerActivity.etCode.getText().toString()) && registerActivity.etCode.getText().toString().length() == 4) {
            if (!TextUtils.isEmpty(registerActivity.etPw.getText().toString())) {
                if (registerActivity.etPw.getText().toString().length() >= 6 && registerActivity.etPw.getText().toString().length() < 13) {
                    if (registerActivity.etPw.getText().toString().equals(registerActivity.etSurepw.getText().toString())) {
                        if (registerActivity.rbYes.isChecked()) {
                            registerActivity.registerPresenter.submitRegist(registerActivity.tvShoujih.getText().toString(), registerActivity.etCode.getText().toString(), registerActivity.etSurepw.getText().toString());
                            return;
                        } else {
                            ToastUtil.showMiddleScreenToast("您需要同意油联尚品协议才能注册");
                            return;
                        }
                    }
                    ToastUtil.showMiddleScreenToast("两次密码不一致！");
                    return;
                }
                ToastUtil.showMiddleScreenToast("密码长度有误！");
                return;
            }
            ToastUtil.showMiddleScreenToast("请输入密码！");
        } else {
            ToastUtil.showMiddleScreenToast("请输入正确验证码！");
        }
    }

    private void iskanjian(boolean z, EditText editText, ImageView imageView) {
        if (!z) {
            imageView.setImageResource(R.mipmap.kan);
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            imageView.setImageResource(R.mipmap.bukan);
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        editText.postInvalidate();
        Editable text = editText.getText();
        if (text instanceof Spannable) {
            Selection.setSelection(text, text.length());
        }
    }

    @Override // com.yltx.oil.partner.base.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacksAndMessages(null);
    }

    private void getSmsCode() {
        String trim = this.tvPhone.getText().toString().trim();
        if (trim.equals("")) {
            ToastUtil.showMiddleScreenToast(getString(R.string.pls_enter_phone_num));
        } else if (!StringUtils.isMobile(trim)) {
            ToastUtil.showMiddleScreenToast(getString(R.string.pls_enter_right_phonenum));
        } else {
            String trim2 = this.etVercodeVerify.getText().toString().trim();
            if (TextUtils.isEmpty(trim2)) {
                ToastUtil.showMiddleScreenToast(getString(R.string.pls_enter_image_code));
            } else {
                this.getValidCodePresenter.submitLoginsm(trim, "loginOrRegister", trim2, "Register");
            }
        }
    }

    @Override // com.yltx.oil.partner.modules.login.view.GetValidCodeView
    public void onsmsSuccess(HttpResult<String> httpResult) {
        ToastUtil.showMiddleScreenToast("验证码已发送");
        this.handler.removeCallbacksAndMessages(null);
        this.time = 60;
        this.handler.post(this.timer);
    }

    @Override // com.yltx.oil.partner.modules.login.view.RegisterView
    public void onRegisterSuccess(HttpResult<LoginInfo> httpResult) {
        this.mPresenter.submitLogin(this.tvShoujih.getText().toString(), this.etSurepw.getText().toString());
    }

    @Override // com.yltx.oil.partner.modules.login.view.RegisterView
    public void getImgCode(String str) {
        this.imageVerify.setImageBitmap(BitmapUtils.stringToBitmap(str));
    }

    @Override // com.yltx.oil.partner.modules.login.view.LoginView, com.yltx.oil.partner.modules.main.view.SplashView
    public void onLoginSuccess(HttpResult<LoginInfo> httpResult) {
        PartnerApplication.getInstance().initLogin(httpResult.getData());
        setResult(-1, new Intent());
        finish();
    }
}