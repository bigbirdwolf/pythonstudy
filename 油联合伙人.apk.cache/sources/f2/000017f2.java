package com.yltx.oil.partner.modules.login.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.BaseFragment;
import com.yltx.oil.partner.base.StateActivity;
import com.yltx.oil.partner.modules.login.fragment.FragmentPwdLogin;
import com.yltx.oil.partner.modules.main.MainActivity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class LoginActivity extends StateActivity {
    public static final int ToMain = 1;
    private FragmentPwdLogin fragmentPwdLogin;
    @BindView(R.id.activity_login_fl)
    FrameLayout frameLayout;
    private int fromType;
    private List<BaseFragment> mFragmentList;
    private BaseFragment preFragment;

    protected void bindListener() {
    }

    protected void setupUI() {
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    public static void toAction(Context context, int i) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("fromType", i);
        context.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        this.fromType = getIntent().getIntExtra("fromType", 0);
        initFragment();
    }

    public int getFromType() {
        return this.fromType;
    }

    private void initFragment() {
        this.mFragmentList = new ArrayList();
        this.fragmentPwdLogin = new FragmentPwdLogin();
        addShowAndHideFragment(this.fragmentPwdLogin);
        showPwdLogin();
    }

    public void showPwdLogin() {
        this.fragmentPwdLogin = new FragmentPwdLogin();
        Bundle bundle = new Bundle();
        bundle.putInt("fromType", this.fromType);
        this.fragmentPwdLogin.setArguments(bundle);
        addShowAndHideFragment(this.fragmentPwdLogin);
    }

    private void addShowAndHideFragment(BaseFragment baseFragment) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        if (!this.mFragmentList.contains(baseFragment)) {
            beginTransaction.add(R.id.activity_login_fl, baseFragment);
            this.mFragmentList.add(baseFragment);
        } else {
            beginTransaction.show(baseFragment);
        }
        if (this.preFragment != null) {
            beginTransaction.hide(this.preFragment);
        }
        beginTransaction.commit();
        this.preFragment = baseFragment;
    }

    public void mFinish() {
        if (this.fromType == 1) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
            return;
        }
        finish();
    }
}