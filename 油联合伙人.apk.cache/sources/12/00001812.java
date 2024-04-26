package com.yltx.oil.partner.modules.login.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.alipay.sdk.app.statistic.c;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.BaseFragment;
import com.yltx.oil.partner.base.PartnerApplication;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.LoginInfo;
import com.yltx.oil.partner.modules.SplashActivity;
import com.yltx.oil.partner.modules.login.activity.ForgetPasswordActivity;
import com.yltx.oil.partner.modules.login.activity.LoginActivity;
import com.yltx.oil.partner.modules.login.activity.RegisterActivity;
import com.yltx.oil.partner.modules.login.presenter.GetValidCodePresenter;
import com.yltx.oil.partner.modules.login.presenter.LoginPresenter;
import com.yltx.oil.partner.modules.login.view.GetValidCodeView;
import com.yltx.oil.partner.modules.login.view.LoginView;
import com.yltx.oil.partner.utils.CommonUtils;
import com.yltx.oil.partner.utils.StringUtils;
import com.yltx.oil.partner.utils.ToastUtil;
import com.yltx.oil.partner.widget.ZoomButton;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class FragmentPwdLogin extends BaseFragment implements LoginView, GetValidCodeView {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.fragment_psd_login)
    TextView fragmentPsdLogin;
    @BindView(R.id.fragment_psd_login_button)
    ZoomButton fragmentPsdLoginButton;
    @BindView(R.id.fragment_psd_login_forgetpwd)
    TextView fragmentPsdLoginForgetpwd;
    @BindView(R.id.fragment_psd_login_register)
    TextView fragmentPsdLoginRegister;
    @BindView(R.id.fragment_psd_login_switch_sms_login)
    TextView fragmentPsdLoginSwitchSmsLogin;
    @BindView(R.id.fragment_sms_et_pwd)
    EditText fragmentSmsEtPwd;
    @BindView(R.id.fragment_sms_get_code)
    ZoomButton fragmentSmsGetCode;
    @Inject
    GetValidCodePresenter getValidCodePresenter;
    private View inflate;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;
    @BindView(R.id.iv_pw)
    ImageView ivPw;
    @BindView(R.id.iv_ylsp)
    ImageView ivYlsp;
    @BindView(R.id.ll_pw)
    LinearLayout llPw;
    @BindView(R.id.ll_sms)
    LinearLayout llSms;
    private SharedPreferences mPreferences;
    @Inject
    LoginPresenter mPresenter;
    private String splash;
    private Context targetAPPContext;
    private int time;
    @BindView(R.id.tool_bar_title)
    TextView toolBarTitle;
    @BindView(R.id.tv_ylsp)
    TextView tvYlsp;
    Unbinder unbinder;
    private Handler handler = new Handler();
    private boolean iskan = false;
    private boolean issm = false;
    private Runnable timer = new Runnable() { // from class: com.yltx.oil.partner.modules.login.fragment.FragmentPwdLogin.1
        @Override // java.lang.Runnable
        public void run() {
            if (FragmentPwdLogin.this.time <= 0) {
                FragmentPwdLogin.this.handler.removeCallbacksAndMessages(null);
                FragmentPwdLogin.this.fragmentSmsGetCode.setEnabled(true);
                FragmentPwdLogin.this.fragmentSmsGetCode.setText(FragmentPwdLogin.this.getString(R.string.get_sms_code));
            } else {
                FragmentPwdLogin.this.fragmentSmsGetCode.setEnabled(false);
                ZoomButton zoomButton = FragmentPwdLogin.this.fragmentSmsGetCode;
                zoomButton.setText(FragmentPwdLogin.this.time + FragmentPwdLogin.this.getString(R.string.send_again));
                FragmentPwdLogin.this.handler.postDelayed(this, 1000L);
            }
            FragmentPwdLogin.access$010(FragmentPwdLogin.this);
        }
    };
    View.OnKeyListener backlistener = new View.OnKeyListener() { // from class: com.yltx.oil.partner.modules.login.fragment.FragmentPwdLogin.2
        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (i == 4) {
                ((LoginActivity) FragmentPwdLogin.this.getActivity()).mFinish();
                if (FragmentPwdLogin.this.splash == "1") {
                    ((SplashActivity) FragmentPwdLogin.this.getActivity()).finish();
                    return true;
                }
                return true;
            }
            return false;
        }
    };

    @Override // com.yltx.oil.partner.mvp.views.ProgressView
    public void hideProgress() {
    }

    @Override // com.yltx.oil.partner.modules.login.view.LoginView, com.yltx.oil.partner.modules.main.view.SplashView
    public void onError(Throwable th) {
    }

    @Override // com.yltx.oil.partner.base.BaseFragment
    protected int setupContentView() {
        return R.layout.fragment_login_pwd;
    }

    @Override // com.yltx.oil.partner.mvp.views.ProgressView
    public void showProgress() {
    }

    static /* synthetic */ int access$010(FragmentPwdLogin fragmentPwdLogin) {
        int i = fragmentPwdLogin.time;
        fragmentPwdLogin.time = i - 1;
        return i;
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.unbinder = ButterKnife.bind(this, onCreateView);
        this.mPresenter.attachView(this);
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            this.splash = extras.getString("splash", "1");
        }
        onCreateView.setFocusableInTouchMode(true);
        onCreateView.requestFocus();
        onCreateView.setOnKeyListener(this.backlistener);
        if (this.splash == "1") {
            this.tvYlsp.setVisibility(8);
            this.ivYlsp.setVisibility(8);
        } else {
            this.tvYlsp.setVisibility(0);
            this.ivYlsp.setVisibility(0);
        }
        this.getValidCodePresenter.attachView(this);
        return onCreateView;
    }

    @OnClick({R.id.iv_delete, R.id.fragment_psd_login_register, R.id.fragment_psd_login_switch_sms_login, R.id.fragment_psd_login_forgetpwd, R.id.fragment_psd_login_button, R.id.fragment_sms_get_code, R.id.iv_ylsp, R.id.iv_pw, R.id.fragment_psd_login})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.fragment_sms_get_code) {
            if (CommonUtils.isFastClick(300L)) {
                return;
            }
            getSmsCode();
        } else if (id == R.id.iv_delete) {
            ((LoginActivity) getActivity()).mFinish();
            if (this.splash == "1") {
                getActivity().getSharedPreferences("android", 0).edit().clear().commit();
                ((SplashActivity) getActivity()).finish();
            }
        } else if (id == R.id.iv_pw) {
            if (!this.iskan) {
                this.ivPw.setImageResource(R.mipmap.kan);
                this.etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                this.ivPw.setImageResource(R.mipmap.bukan);
                this.etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            this.iskan = !this.iskan;
            this.etPwd.postInvalidate();
            Editable text = this.etPwd.getText();
            if (text instanceof Spannable) {
                Selection.setSelection(text, text.length());
            }
        } else if (id != R.id.iv_ylsp) {
            switch (id) {
                case R.id.fragment_psd_login /* 2131296467 */:
                    this.issm = false;
                    this.llPw.setVisibility(0);
                    this.llSms.setVisibility(8);
                    this.fragmentPsdLogin.setVisibility(8);
                    this.fragmentPsdLoginSwitchSmsLogin.setVisibility(0);
                    return;
                case R.id.fragment_psd_login_button /* 2131296468 */:
                    if (this.issm) {
                        if (TextUtils.isEmpty(this.fragmentSmsEtPwd.getText().toString())) {
                            return;
                        }
                        this.mPresenter.submitLoginsm(this.etPhone.getText().toString(), this.fragmentSmsEtPwd.getText().toString());
                        return;
                    } else if (!TextUtils.isEmpty(this.etPhone.getText().toString()) && StringUtils.isMobile(this.etPhone.getText().toString())) {
                        if (this.etPwd.getText().toString().length() >= 6) {
                            this.mPresenter.submitLogin(this.etPhone.getText().toString(), this.etPwd.getText().toString());
                            return;
                        } else {
                            ToastUtil.showMiddleScreenToast("密码格式不正确");
                            return;
                        }
                    } else {
                        ToastUtil.showMiddleScreenToast("手机号格式不正确！");
                        return;
                    }
                case R.id.fragment_psd_login_forgetpwd /* 2131296469 */:
                    getActivity().startActivity(ForgetPasswordActivity.getCallingIntent(getActivity()));
                    return;
                case R.id.fragment_psd_login_register /* 2131296470 */:
                    startActivityForResult(RegisterActivity.getCallingIntent(getActivity()), 1);
                    return;
                case R.id.fragment_psd_login_switch_sms_login /* 2131296471 */:
                    this.issm = true;
                    this.llPw.setVisibility(8);
                    this.llSms.setVisibility(0);
                    this.fragmentPsdLogin.setVisibility(0);
                    this.fragmentPsdLoginSwitchSmsLogin.setVisibility(8);
                    return;
                default:
                    return;
            }
        } else if (getActivity().getPackageManager().getLaunchIntentForPackage("com.yltx.nonoil") != null) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.yltx.nonoil", "com.yltx.nonoil.SplashActivity"));
            intent.putExtra("type", c.U);
            startActivityForResult(intent, 0);
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "哟，赶紧下载安装这个APP吧", 1).show();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Log.v("requestCode==", i + "" + i);
        if (i == 0 && i2 == -1) {
            this.mPresenter.Login(intent.getStringExtra("name"), intent.getStringExtra("userid"));
        }
        if (i == 1 && i2 == -1) {
            ((LoginActivity) getActivity()).mFinish();
        }
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
    }

    private void getSmsCode() {
        String trim = this.etPhone.getText().toString().trim();
        if (trim.equals("")) {
            ToastUtil.showMiddleScreenToast(getString(R.string.pls_enter_phone_num));
        } else if (!StringUtils.isMobile(trim)) {
            ToastUtil.showMiddleScreenToast(getString(R.string.pls_enter_right_phonenum));
        } else {
            this.getValidCodePresenter.submitLoginsm(trim, "loginOrRegister", "", "");
            this.getValidCodePresenter.submitLoginsm(trim, "loginOrRegister", "", "");
        }
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
        this.handler.removeCallbacksAndMessages(null);
    }

    @Override // com.yltx.oil.partner.modules.login.view.LoginView, com.yltx.oil.partner.modules.main.view.SplashView
    public void onLoginSuccess(HttpResult<LoginInfo> httpResult) {
        PartnerApplication.getInstance().initLogin(httpResult.getData());
        ((LoginActivity) getActivity()).mFinish();
    }

    @Override // com.yltx.oil.partner.modules.login.view.GetValidCodeView
    public void onsmsSuccess(HttpResult<String> httpResult) {
        ToastUtil.showMiddleScreenToast("验证码已发送");
        this.handler.removeCallbacksAndMessages(null);
        this.time = 60;
        this.handler.post(this.timer);
    }
}