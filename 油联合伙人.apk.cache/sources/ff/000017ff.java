package com.yltx.oil.partner.modules.login.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.BaseActivity;
import com.yltx.oil.partner.base.ErrorView;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.AppLoginStatusResp;
import com.yltx.oil.partner.modules.login.presenter.AppLoginStatusPresenter;
import com.yltx.oil.partner.modules.login.view.AppLoginStatusView;
import com.yltx.oil.partner.utils.DataCache;
import javax.inject.Inject;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class YLTXLoginActivity extends BaseActivity implements AppLoginStatusView {
    @BindView(R.id.head_left_image)
    ImageView commomHeadLeftImage;
    @BindView(R.id.head_title)
    TextView commomHeadTitle;
    SharedPreferences mPreference;
    @Inject
    AppLoginStatusPresenter presenter;
    @BindView(R.id.splogin_button)
    Button sploginButton;
    @BindView(R.id.tv_spphone)
    TextView tvSpphone;
    private String type = "2";

    @Override // com.yltx.oil.partner.mvp.views.InterfaceView
    public Context getContext() {
        return null;
    }

    @Override // com.yltx.oil.partner.mvp.views.ProgressView
    public void hideProgress() {
    }

    @Override // com.yltx.oil.partner.mvp.views.LoadDataView
    public void onLoadingComplete() {
    }

    @Override // com.yltx.oil.partner.modules.login.view.AppLoginStatusView
    public void onStatusLoginSuccess(HttpResult<AppLoginStatusResp> httpResult) {
    }

    @Override // com.yltx.oil.partner.mvp.views.LoadDataView
    public void showEmptyView(ErrorView.Config config, ErrorView.OnRetryListener onRetryListener) {
    }

    @Override // com.yltx.oil.partner.mvp.views.LoadDataView
    public void showError(String str) {
    }

    @Override // com.yltx.oil.partner.mvp.views.LoadDataView
    public void showErrorView(Throwable th, ErrorView.Config config, ErrorView.OnRetryListener onRetryListener) {
    }

    @Override // com.yltx.oil.partner.mvp.views.LoadDataView
    public void showLoadingView() {
    }

    @Override // com.yltx.oil.partner.mvp.views.ProgressView
    public void showProgress() {
    }

    @Override // com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_nonoil_login_activity);
        ButterKnife.bind(this);
        this.presenter.attachView(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.type = extras.getString("type", "-1");
            if (this.type.equals("android")) {
                SharedPreferences.Editor edit = getSharedPreferences("android", 0).edit();
                edit.putString("type", this.type);
                edit.commit();
                if (!TextUtils.isEmpty(DataCache.getToken(this))) {
                    this.presenter.submitLogin();
                } else {
                    LoginActivity.toAction(this, 0);
                }
            }
        }
        Rx.click(this.commomHeadLeftImage, new Action1() { // from class: com.yltx.oil.partner.modules.login.activity.-$$Lambda$YLTXLoginActivity$nihKBeVC_eEWUP-zFNXCMgCkgFk
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                YLTXLoginActivity.lambda$onCreate$0(YLTXLoginActivity.this, (Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$onCreate$0(YLTXLoginActivity yLTXLoginActivity, Void r2) {
        yLTXLoginActivity.getSharedPreferences("android", 0).edit().clear().commit();
        yLTXLoginActivity.finish();
    }

    @Override // com.yltx.oil.partner.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.mPreference = getSharedPreferences("userId", 0);
        this.commomHeadTitle.setText("油联天下");
        String string = this.mPreference.getString("Phone", "");
        if (!TextUtils.isEmpty(string)) {
            this.tvSpphone.setText(string.replace(string.substring(3, 7), "****"));
        }
        Rx.click(this.sploginButton, new Action1() { // from class: com.yltx.oil.partner.modules.login.activity.-$$Lambda$YLTXLoginActivity$VEuj32kLouTfzC1jf_1ezaRBLh4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                YLTXLoginActivity.lambda$onResume$1(YLTXLoginActivity.this, (Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$onResume$1(YLTXLoginActivity yLTXLoginActivity, Void r4) {
        if (TextUtils.isEmpty(DataCache.getToken(yLTXLoginActivity)) || !yLTXLoginActivity.type.equals("android") || TextUtils.isEmpty(DataCache.getToken(yLTXLoginActivity))) {
            return;
        }
        Log.v("requestCode==", "nonoil=");
        yLTXLoginActivity.getSharedPreferences("android", 0).edit().clear().commit();
        Intent intent = new Intent();
        intent.putExtra("name", DataCache.getToken(yLTXLoginActivity));
        intent.putExtra("userid", DataCache.getUserid(yLTXLoginActivity));
        Log.v("requestCode==", DataCache.getToken(yLTXLoginActivity) + "");
        yLTXLoginActivity.setResult(-1, intent);
        yLTXLoginActivity.finish();
    }

    @Override // com.yltx.oil.partner.base.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.yltx.oil.partner.modules.login.view.AppLoginStatusView
    public void onError(Throwable th) {
        LoginActivity.toAction(this, 0);
    }
}