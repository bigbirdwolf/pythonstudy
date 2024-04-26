package com.yltx.oil.partner.modules.login.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.modules.login.presenter.ForgetPwdPresenter;
import com.yltx.oil.partner.modules.login.view.ForgetPwdView;
import com.yltx.oil.partner.utils.CommonUtils;
import com.yltx.oil.partner.utils.StringUtils;
import com.yltx.oil.partner.utils.ToastUtil;
import com.yltx.oil.partner.widget.ZoomButton;
import javax.inject.Inject;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class ForgetPasswordActivity extends ToolBarActivity implements ForgetPwdView {
    @BindView(R.id.btn_complete)
    TextView btnComplete;
    @BindView(R.id.et_confirm_pwd)
    EditText etConfirmPwd;
    @BindView(R.id.et_new_pwd)
    EditText etNewPwd;
    @BindView(R.id.et_valid_code)
    EditText etValidCode;
    @BindView(R.id.iv_agpw)
    ImageView ivAgpw;
    @BindView(R.id.iv_pw)
    ImageView ivPw;
    @Inject
    public ForgetPwdPresenter mPresenter;
    private int time;
    @BindView(R.id.tv_forget_validCode)
    ZoomButton tvForgetValidCode;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    private Handler handler = new Handler();
    private boolean ispwkan = false;
    private boolean isagpwkan = false;
    private Runnable timer = new Runnable() { // from class: com.yltx.oil.partner.modules.login.activity.ForgetPasswordActivity.1
        {
            ForgetPasswordActivity.this = this;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ForgetPasswordActivity.this.time <= 0) {
                ForgetPasswordActivity.this.handler.removeCallbacksAndMessages(null);
                ForgetPasswordActivity.this.tvForgetValidCode.setEnabled(true);
                ForgetPasswordActivity.this.tvForgetValidCode.setText(ForgetPasswordActivity.this.getString(R.string.get_sms_code));
            } else {
                ForgetPasswordActivity.this.tvForgetValidCode.setEnabled(false);
                ZoomButton zoomButton = ForgetPasswordActivity.this.tvForgetValidCode;
                zoomButton.setText(ForgetPasswordActivity.this.time + ForgetPasswordActivity.this.getString(R.string.send_again));
                ForgetPasswordActivity.this.handler.postDelayed(this, 1000L);
            }
            ForgetPasswordActivity.access$010(ForgetPasswordActivity.this);
        }
    };

    static /* synthetic */ int access$010(ForgetPasswordActivity forgetPasswordActivity) {
        int i = forgetPasswordActivity.time;
        forgetPasswordActivity.time = i - 1;
        return i;
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ForgetPasswordActivity.class);
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
        this.mPresenter.attachView(this);
        setupUI();
        bindListener();
    }

    protected void setupUI() {
        setToolbarTitle("忘记密码");
    }

    protected void bindListener() {
        Rx.click(this.btnComplete, new Action1() { // from class: com.yltx.oil.partner.modules.login.activity.-$$Lambda$ForgetPasswordActivity$bl_Es3hq0X24JUGqPIeo3AtW-jM
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ForgetPasswordActivity.lambda$bindListener$0(ForgetPasswordActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvForgetValidCode, new Action1() { // from class: com.yltx.oil.partner.modules.login.activity.-$$Lambda$ForgetPasswordActivity$jep2iWKppUjYx9hPqkwGtUXJ0B4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ForgetPasswordActivity.lambda$bindListener$1(ForgetPasswordActivity.this, (Void) obj);
            }
        });
        Rx.click(this.ivPw, new Action1() { // from class: com.yltx.oil.partner.modules.login.activity.-$$Lambda$ForgetPasswordActivity$B7bjg71xuGVp5T7_6HwneZY4gms
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ForgetPasswordActivity.lambda$bindListener$2(ForgetPasswordActivity.this, (Void) obj);
            }
        });
        Rx.click(this.ivAgpw, new Action1() { // from class: com.yltx.oil.partner.modules.login.activity.-$$Lambda$ForgetPasswordActivity$QIDoWeQm7hOD4tyKhASMYq84QKo
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ForgetPasswordActivity.lambda$bindListener$3(ForgetPasswordActivity.this, (Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(ForgetPasswordActivity forgetPasswordActivity, Void r4) {
        String trim = forgetPasswordActivity.tvPhone.getText().toString().trim();
        if (trim.equals("")) {
            ToastUtil.showMiddleScreenToast(forgetPasswordActivity.getString(R.string.pls_enter_phone_num));
        } else if (!StringUtils.isMobile(trim)) {
            ToastUtil.showMiddleScreenToast(forgetPasswordActivity.getString(R.string.pls_enter_right_phonenum));
        } else if (StringUtils.isEmpty(forgetPasswordActivity.etValidCode.getText().toString().trim())) {
            ToastUtil.showMiddleScreenToast(forgetPasswordActivity.getContext().getResources().getString(R.string.pls_enter_sms_code));
        } else if (StringUtils.isEmpty(forgetPasswordActivity.etNewPwd.getText().toString().trim())) {
            ToastUtil.showMiddleScreenToast(forgetPasswordActivity.getContext().getResources().getString(R.string.pls_enter_phone_pwd1));
        } else if (StringUtils.isEmpty(forgetPasswordActivity.etConfirmPwd.getText().toString().trim())) {
            ToastUtil.showMiddleScreenToast(forgetPasswordActivity.getContext().getResources().getString(R.string.pls_enter_again_phone_pwd2));
        } else if (!forgetPasswordActivity.etConfirmPwd.getText().toString().trim().equals(forgetPasswordActivity.etNewPwd.getText().toString().trim())) {
            ToastUtil.showMiddleScreenToast(forgetPasswordActivity.getContext().getResources().getString(R.string.password_not_right_twice));
        } else {
            forgetPasswordActivity.mPresenter.forgetPwd(trim, forgetPasswordActivity.etNewPwd.getText().toString().trim(), forgetPasswordActivity.etValidCode.getText().toString().trim());
        }
    }

    public static /* synthetic */ void lambda$bindListener$1(ForgetPasswordActivity forgetPasswordActivity, Void r3) {
        if (CommonUtils.isFastClick(300L)) {
            return;
        }
        forgetPasswordActivity.getSmsCode();
    }

    public static /* synthetic */ void lambda$bindListener$2(ForgetPasswordActivity forgetPasswordActivity, Void r3) {
        if (forgetPasswordActivity.ispwkan) {
            forgetPasswordActivity.ispwkan = false;
        } else {
            forgetPasswordActivity.ispwkan = true;
        }
        forgetPasswordActivity.iskanjian(forgetPasswordActivity.ispwkan, forgetPasswordActivity.etNewPwd, forgetPasswordActivity.ivPw);
    }

    public static /* synthetic */ void lambda$bindListener$3(ForgetPasswordActivity forgetPasswordActivity, Void r3) {
        if (forgetPasswordActivity.isagpwkan) {
            forgetPasswordActivity.isagpwkan = false;
        } else {
            forgetPasswordActivity.isagpwkan = true;
        }
        forgetPasswordActivity.iskanjian(forgetPasswordActivity.isagpwkan, forgetPasswordActivity.etConfirmPwd, forgetPasswordActivity.ivAgpw);
    }

    private void getSmsCode() {
        String trim = this.tvPhone.getText().toString().trim();
        if (trim.equals("")) {
            ToastUtil.showMiddleScreenToast(getString(R.string.pls_enter_phone_num));
        } else if (!StringUtils.isMobile(trim)) {
            ToastUtil.showMiddleScreenToast(getString(R.string.pls_enter_right_phonenum));
        } else {
            this.mPresenter.getVcode(this.tvPhone.getText().toString(), "returnpwd");
        }
    }

    @Override // com.yltx.oil.partner.base.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacksAndMessages(null);
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

    @Override // com.yltx.oil.partner.modules.login.view.ForgetPwdView
    public void onSuccess() {
        ToastUtil.showMiddleScreenToast("修改成功");
        finish();
    }

    @Override // com.yltx.oil.partner.modules.login.view.ForgetPwdView
    public void onVcodeSuccess() {
        ToastUtil.showMiddleScreenToast("发送验证码成功");
        this.handler.removeCallbacksAndMessages(null);
        this.time = 60;
        this.handler.post(this.timer);
    }

    @Override // com.yltx.oil.partner.modules.login.view.ForgetPwdView
    public void onError(Throwable th) {
        ToastUtil.showMiddleScreenToast(th.getMessage());
    }
}