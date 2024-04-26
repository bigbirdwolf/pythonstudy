package com.yltx.oil.partner.modules.mine.activity;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.login.presenter.ForgetPwdPresenter;
import com.yltx.oil.partner.modules.login.view.ForgetPwdView;
import com.yltx.oil.partner.modules.mine.presenter.MinePhonePresenter;
import com.yltx.oil.partner.modules.mine.view.MineInfoPhoneView;
import com.yltx.oil.partner.utils.CommonUtils;
import com.yltx.oil.partner.utils.StringUtils;
import com.yltx.oil.partner.utils.ToastUtil;
import com.yltx.oil.partner.widget.ZoomButton;
import javax.inject.Inject;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class UpdatePwdActivity extends ToolBarActivity implements MineInfoPhoneView, ForgetPwdView {
    @BindView(R.id.activity_change_psd_et)
    EditText activityChangePsdEt;
    @BindView(R.id.activity_change_psd_et_twice)
    EditText activityChangePsdEtTwice;
    @BindView(R.id.activity_change_psd_ll)
    LinearLayout activityChangePsdLl;
    @BindView(R.id.code)
    ZoomButton code;
    @Inject
    public ForgetPwdPresenter forgetPwdPresenter;
    @BindView(R.id.iv_pw)
    ImageView ivPw;
    @BindView(R.id.iv_twicepw)
    ImageView ivTwicepw;
    @BindView(R.id.ll_sms)
    LinearLayout llSms;
    @Inject
    MinePhonePresenter mPresenter;
    @BindView(R.id.phone_sms_code)
    EditText phoneSmsCode;
    private int time;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    private Handler handler = new Handler();
    private boolean ispwkan = false;
    private boolean isagpwkan = false;
    private Runnable timer = new Runnable() { // from class: com.yltx.oil.partner.modules.mine.activity.UpdatePwdActivity.1
        {
            UpdatePwdActivity.this = this;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (UpdatePwdActivity.this.time <= 0) {
                UpdatePwdActivity.this.handler.removeCallbacksAndMessages(null);
                UpdatePwdActivity.this.code.setEnabled(true);
                UpdatePwdActivity.this.code.setText(UpdatePwdActivity.this.getString(R.string.get_sms_code));
            } else {
                UpdatePwdActivity.this.code.setEnabled(false);
                ZoomButton zoomButton = UpdatePwdActivity.this.code;
                zoomButton.setText(UpdatePwdActivity.this.time + UpdatePwdActivity.this.getString(R.string.send_again));
                UpdatePwdActivity.this.handler.postDelayed(this, 1000L);
            }
            UpdatePwdActivity.access$010(UpdatePwdActivity.this);
        }
    };

    @Override // com.yltx.oil.partner.modules.mine.view.MineInfoPhoneView
    public void onError(Throwable th) {
    }

    @Override // com.yltx.oil.partner.modules.mine.view.MineInfoPhoneView
    public void onSuccess(HttpResult<String> httpResult) {
    }

    @Override // com.yltx.oil.partner.modules.mine.view.MineInfoPhoneView
    public void onUpSuccess(HttpResult<String> httpResult) {
    }

    @Override // com.yltx.oil.partner.modules.login.view.ForgetPwdView
    public void onVcodeSuccess() {
    }

    static /* synthetic */ int access$010(UpdatePwdActivity updatePwdActivity) {
        int i = updatePwdActivity.time;
        updatePwdActivity.time = i - 1;
        return i;
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, UpdatePwdActivity.class);
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_phone);
        ButterKnife.bind(this);
        this.mPresenter.attachView(this);
        this.forgetPwdPresenter.attachView(this);
        setupUI();
        bindListener();
    }

    private void bindListener() {
        Rx.click(this.code, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$UpdatePwdActivity$GFd5UdT1RZSCIqmgEfy1lS8mIdo
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                UpdatePwdActivity.lambda$bindListener$0(UpdatePwdActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvNext, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$UpdatePwdActivity$9K-CYtflmnbZEICT_TmwsAwN5Ko
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                UpdatePwdActivity.lambda$bindListener$1(UpdatePwdActivity.this, (Void) obj);
            }
        });
        Rx.click(this.ivPw, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$UpdatePwdActivity$Tq92NTfKdNH4RhrwI7F5ZiZXv48
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                UpdatePwdActivity.lambda$bindListener$2(UpdatePwdActivity.this, (Void) obj);
            }
        });
        Rx.click(this.ivTwicepw, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$UpdatePwdActivity$tqSezWQ_eYx92gu--mKwWAWs9Mg
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                UpdatePwdActivity.lambda$bindListener$3(UpdatePwdActivity.this, (Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(UpdatePwdActivity updatePwdActivity, Void r3) {
        if (CommonUtils.isFastClick(300L)) {
            return;
        }
        updatePwdActivity.getSmsCode();
    }

    public static /* synthetic */ void lambda$bindListener$1(UpdatePwdActivity updatePwdActivity, Void r4) {
        if (StringUtils.isEmpty(updatePwdActivity.phoneSmsCode.getText().toString().trim())) {
            ToastUtil.showMiddleScreenToast(updatePwdActivity.getContext().getResources().getString(R.string.pls_enter_sms_code));
        } else if (StringUtils.isEmpty(updatePwdActivity.activityChangePsdEt.getText().toString().trim())) {
            ToastUtil.showMiddleScreenToast(updatePwdActivity.getContext().getResources().getString(R.string.pls_enter_phone_pwd1));
        } else if (StringUtils.isEmpty(updatePwdActivity.activityChangePsdEtTwice.getText().toString().trim())) {
            ToastUtil.showMiddleScreenToast(updatePwdActivity.getContext().getResources().getString(R.string.pls_enter_again_phone_pwd2));
        } else if (!updatePwdActivity.activityChangePsdEt.getText().toString().trim().equals(updatePwdActivity.activityChangePsdEtTwice.getText().toString().trim())) {
            ToastUtil.showMiddleScreenToast(updatePwdActivity.getContext().getResources().getString(R.string.password_not_right_twice));
        } else {
            updatePwdActivity.forgetPwdPresenter.forgetPwd(updatePwdActivity.tvPhone.getText().toString().trim(), updatePwdActivity.activityChangePsdEtTwice.getText().toString().trim(), updatePwdActivity.phoneSmsCode.getText().toString().trim());
        }
    }

    public static /* synthetic */ void lambda$bindListener$2(UpdatePwdActivity updatePwdActivity, Void r3) {
        if (updatePwdActivity.ispwkan) {
            updatePwdActivity.ispwkan = false;
        } else {
            updatePwdActivity.ispwkan = true;
        }
        updatePwdActivity.iskanjian(updatePwdActivity.ispwkan, updatePwdActivity.activityChangePsdEt, updatePwdActivity.ivPw);
    }

    public static /* synthetic */ void lambda$bindListener$3(UpdatePwdActivity updatePwdActivity, Void r3) {
        if (updatePwdActivity.isagpwkan) {
            updatePwdActivity.isagpwkan = false;
        } else {
            updatePwdActivity.isagpwkan = true;
        }
        updatePwdActivity.iskanjian(updatePwdActivity.isagpwkan, updatePwdActivity.activityChangePsdEtTwice, updatePwdActivity.ivTwicepw);
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

    private void setupUI() {
        setToolbarTitle("修改密码");
        this.tvNext.setText("完成");
        this.activityChangePsdLl.setVisibility(0);
    }

    private void getSmsCode() {
        String trim = this.tvPhone.getText().toString().trim();
        if (trim.equals("")) {
            ToastUtil.showMiddleScreenToast(getString(R.string.pls_enter_phone_num));
        } else if (!StringUtils.isMobile(trim)) {
            ToastUtil.showMiddleScreenToast(getString(R.string.pls_enter_right_phonenum));
        } else {
            this.mPresenter.getVCode(trim, "returnpwd");
        }
    }

    @Override // com.yltx.oil.partner.base.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacksAndMessages(null);
    }

    @Override // com.yltx.oil.partner.modules.mine.view.MineInfoPhoneView
    public void onSmsSuccess(HttpResult<String> httpResult) {
        ToastUtil.showMiddleScreenToast("发送验证码成功");
        this.handler.removeCallbacksAndMessages(null);
        this.time = 60;
        this.handler.post(this.timer);
    }

    @Override // com.yltx.oil.partner.modules.login.view.ForgetPwdView
    public void onSuccess() {
        ToastUtil.showMiddleScreenToast("修改成功");
        finish();
    }
}