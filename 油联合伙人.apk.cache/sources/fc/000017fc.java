package com.yltx.oil.partner.modules.login.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.StateActivity;
import com.yltx.oil.partner.utils.ToastUtil;
import com.yltx.oil.partner.widget.ZoomButton;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class YLSPLoginActivity extends StateActivity {
    @BindView(R.id.head_left_image)
    ImageView headLeftImage;
    @BindView(R.id.head_title)
    TextView headTitle;
    private SharedPreferences mPreferences;
    @BindView(R.id.splogin_button)
    ZoomButton sploginButton;
    private Context targetAPPContext;
    @BindView(R.id.tv_spphone)
    TextView tvSpphone;

    public static /* synthetic */ void lambda$bindListener$1(Void r0) {
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, YLSPLoginActivity.class);
    }

    @Override // com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ylsplogin);
        ButterKnife.bind(this);
        setupUI();
        bindListener();
    }

    protected void setupUI() {
        this.headTitle.setText("尚品登录");
        try {
            this.targetAPPContext = createPackageContext("com.yltx.nonoil", 0);
            this.mPreferences = this.targetAPPContext.getSharedPreferences("userId", 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String string = this.mPreferences.getString("name", "000");
        this.tvSpphone.setText(string);
        ToastUtil.showMiddleScreenLongToast(string);
    }

    protected void bindListener() {
        Rx.click(this.headLeftImage, new Action1() { // from class: com.yltx.oil.partner.modules.login.activity.-$$Lambda$YLSPLoginActivity$5uWx6eWR7fYIBHCr1HuVMu-xzF0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                YLSPLoginActivity.lambda$bindListener$0(YLSPLoginActivity.this, (Void) obj);
            }
        });
        Rx.click(this.sploginButton, new Action1() { // from class: com.yltx.oil.partner.modules.login.activity.-$$Lambda$YLSPLoginActivity$aRRyQYdlPf9oEUmUDW4GyJmcnC8
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                YLSPLoginActivity.lambda$bindListener$1((Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(YLSPLoginActivity yLSPLoginActivity, Void r1) {
        yLSPLoginActivity.finish();
    }
}