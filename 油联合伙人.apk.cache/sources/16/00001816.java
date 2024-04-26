package com.yltx.oil.partner.modules.login.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.widget.ZoomButton;

/* loaded from: classes.dex */
public class FragmentPwdLogin_ViewBinding implements Unbinder {
    private FragmentPwdLogin target;
    private View view2131296467;
    private View view2131296468;
    private View view2131296469;
    private View view2131296470;
    private View view2131296471;
    private View view2131296473;
    private View view2131296519;
    private View view2131296528;
    private View view2131296537;

    @UiThread
    public FragmentPwdLogin_ViewBinding(final FragmentPwdLogin fragmentPwdLogin, View view) {
        this.target = fragmentPwdLogin;
        View findRequiredView = Utils.findRequiredView(view, R.id.iv_delete, "field 'ivDelete' and method 'onViewClicked'");
        fragmentPwdLogin.ivDelete = (ImageView) Utils.castView(findRequiredView, R.id.iv_delete, "field 'ivDelete'", ImageView.class);
        this.view2131296519 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yltx.oil.partner.modules.login.fragment.FragmentPwdLogin_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                fragmentPwdLogin.onViewClicked(view2);
            }
        });
        fragmentPwdLogin.toolBarTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tool_bar_title, "field 'toolBarTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.fragment_psd_login_register, "field 'fragmentPsdLoginRegister' and method 'onViewClicked'");
        fragmentPwdLogin.fragmentPsdLoginRegister = (TextView) Utils.castView(findRequiredView2, R.id.fragment_psd_login_register, "field 'fragmentPsdLoginRegister'", TextView.class);
        this.view2131296470 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yltx.oil.partner.modules.login.fragment.FragmentPwdLogin_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                fragmentPwdLogin.onViewClicked(view2);
            }
        });
        fragmentPwdLogin.etPhone = (EditText) Utils.findRequiredViewAsType(view, R.id.et_phone, "field 'etPhone'", EditText.class);
        fragmentPwdLogin.etPwd = (EditText) Utils.findRequiredViewAsType(view, R.id.et_pwd, "field 'etPwd'", EditText.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.fragment_psd_login_switch_sms_login, "field 'fragmentPsdLoginSwitchSmsLogin' and method 'onViewClicked'");
        fragmentPwdLogin.fragmentPsdLoginSwitchSmsLogin = (TextView) Utils.castView(findRequiredView3, R.id.fragment_psd_login_switch_sms_login, "field 'fragmentPsdLoginSwitchSmsLogin'", TextView.class);
        this.view2131296471 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yltx.oil.partner.modules.login.fragment.FragmentPwdLogin_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                fragmentPwdLogin.onViewClicked(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.fragment_psd_login_forgetpwd, "field 'fragmentPsdLoginForgetpwd' and method 'onViewClicked'");
        fragmentPwdLogin.fragmentPsdLoginForgetpwd = (TextView) Utils.castView(findRequiredView4, R.id.fragment_psd_login_forgetpwd, "field 'fragmentPsdLoginForgetpwd'", TextView.class);
        this.view2131296469 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yltx.oil.partner.modules.login.fragment.FragmentPwdLogin_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                fragmentPwdLogin.onViewClicked(view2);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.fragment_psd_login_button, "field 'fragmentPsdLoginButton' and method 'onViewClicked'");
        fragmentPwdLogin.fragmentPsdLoginButton = (ZoomButton) Utils.castView(findRequiredView5, R.id.fragment_psd_login_button, "field 'fragmentPsdLoginButton'", ZoomButton.class);
        this.view2131296468 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yltx.oil.partner.modules.login.fragment.FragmentPwdLogin_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                fragmentPwdLogin.onViewClicked(view2);
            }
        });
        fragmentPwdLogin.llSms = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sms, "field 'llSms'", LinearLayout.class);
        fragmentPwdLogin.llPw = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_pw, "field 'llPw'", LinearLayout.class);
        View findRequiredView6 = Utils.findRequiredView(view, R.id.fragment_psd_login, "field 'fragmentPsdLogin' and method 'onViewClicked'");
        fragmentPwdLogin.fragmentPsdLogin = (TextView) Utils.castView(findRequiredView6, R.id.fragment_psd_login, "field 'fragmentPsdLogin'", TextView.class);
        this.view2131296467 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yltx.oil.partner.modules.login.fragment.FragmentPwdLogin_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                fragmentPwdLogin.onViewClicked(view2);
            }
        });
        fragmentPwdLogin.fragmentSmsEtPwd = (EditText) Utils.findRequiredViewAsType(view, R.id.fragment_sms_et_pwd, "field 'fragmentSmsEtPwd'", EditText.class);
        View findRequiredView7 = Utils.findRequiredView(view, R.id.fragment_sms_get_code, "field 'fragmentSmsGetCode' and method 'onViewClicked'");
        fragmentPwdLogin.fragmentSmsGetCode = (ZoomButton) Utils.castView(findRequiredView7, R.id.fragment_sms_get_code, "field 'fragmentSmsGetCode'", ZoomButton.class);
        this.view2131296473 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yltx.oil.partner.modules.login.fragment.FragmentPwdLogin_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                fragmentPwdLogin.onViewClicked(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.iv_pw, "field 'ivPw' and method 'onViewClicked'");
        fragmentPwdLogin.ivPw = (ImageView) Utils.castView(findRequiredView8, R.id.iv_pw, "field 'ivPw'", ImageView.class);
        this.view2131296528 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yltx.oil.partner.modules.login.fragment.FragmentPwdLogin_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                fragmentPwdLogin.onViewClicked(view2);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.iv_ylsp, "field 'ivYlsp' and method 'onViewClicked'");
        fragmentPwdLogin.ivYlsp = (ImageView) Utils.castView(findRequiredView9, R.id.iv_ylsp, "field 'ivYlsp'", ImageView.class);
        this.view2131296537 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yltx.oil.partner.modules.login.fragment.FragmentPwdLogin_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                fragmentPwdLogin.onViewClicked(view2);
            }
        });
        fragmentPwdLogin.tvYlsp = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ylsp, "field 'tvYlsp'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        FragmentPwdLogin fragmentPwdLogin = this.target;
        if (fragmentPwdLogin == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        fragmentPwdLogin.ivDelete = null;
        fragmentPwdLogin.toolBarTitle = null;
        fragmentPwdLogin.fragmentPsdLoginRegister = null;
        fragmentPwdLogin.etPhone = null;
        fragmentPwdLogin.etPwd = null;
        fragmentPwdLogin.fragmentPsdLoginSwitchSmsLogin = null;
        fragmentPwdLogin.fragmentPsdLoginForgetpwd = null;
        fragmentPwdLogin.fragmentPsdLoginButton = null;
        fragmentPwdLogin.llSms = null;
        fragmentPwdLogin.llPw = null;
        fragmentPwdLogin.fragmentPsdLogin = null;
        fragmentPwdLogin.fragmentSmsEtPwd = null;
        fragmentPwdLogin.fragmentSmsGetCode = null;
        fragmentPwdLogin.ivPw = null;
        fragmentPwdLogin.ivYlsp = null;
        fragmentPwdLogin.tvYlsp = null;
        this.view2131296519.setOnClickListener(null);
        this.view2131296519 = null;
        this.view2131296470.setOnClickListener(null);
        this.view2131296470 = null;
        this.view2131296471.setOnClickListener(null);
        this.view2131296471 = null;
        this.view2131296469.setOnClickListener(null);
        this.view2131296469 = null;
        this.view2131296468.setOnClickListener(null);
        this.view2131296468 = null;
        this.view2131296467.setOnClickListener(null);
        this.view2131296467 = null;
        this.view2131296473.setOnClickListener(null);
        this.view2131296473 = null;
        this.view2131296528.setOnClickListener(null);
        this.view2131296528 = null;
        this.view2131296537.setOnClickListener(null);
        this.view2131296537 = null;
    }
}