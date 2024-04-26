package com.yltx.oil.partner.modules;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import com.allenliu.versionchecklib.callback.OnCancelListener;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.ForceUpdateListener;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.PartnerApplication;
import com.yltx.oil.partner.base.StateActivity;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.LoginInfo;
import com.yltx.oil.partner.data.response.VersionResponse;
import com.yltx.oil.partner.modules.login.presenter.LoginPresenter;
import com.yltx.oil.partner.modules.login.view.LoginView;
import com.yltx.oil.partner.modules.main.MainActivity;
import com.yltx.oil.partner.modules.main.presenter.SplashPresenter;
import com.yltx.oil.partner.modules.main.view.SplashView;
import com.yltx.oil.partner.utils.CheckPermissionsUtils;
import com.yltx.oil.partner.utils.CommonUtils;
import com.yltx.oil.partner.utils.DataCache;
import com.yltx.oil.partner.utils.DialogUtils;
import com.yltx.oil.partner.utils.OfflineResource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class SplashActivity extends StateActivity implements LoginView, SplashView {
    private static final int PERMISSON_REQUESTCODE = 0;
    private static final String TAG = "SplashActivity";
    private static final int sleepTime = 2000;
    Context context;
    private boolean isFirstUse;
    SharedPreferences mPreference;
    @Inject
    LoginPresenter mPresenter;
    @Inject
    public SplashPresenter presenter;
    private String pw;
    private long start;
    private String token;
    private DialogUtils dialogUtils = new DialogUtils();
    protected String[] needPermissions = {"android.permission.ACCESS_NETWORK_STATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CHANGE_WIFI_STATE", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.GET_TASKS", "android.permission.RECORD_AUDIO", "android.permission.READ_CONTACTS", "android.permission.ACCESS_FINE_LOCATION", "android.permission.INTERNET", "android.permission.CAMERA", "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_WIFI_STATE"};

    public static /* synthetic */ void lambda$checkUpdatedVersion$2() {
    }

    public static /* synthetic */ void lambda$checkUpdatedVersion$3() {
    }

    protected void bindListener() {
    }

    private long delay(long j) {
        return Math.abs(2000 - (System.currentTimeMillis() - j));
    }

    private void jumpToMainAfterSleep() {
        Observable.timer(2000L, TimeUnit.MILLISECONDS).subscribe(new Action1() { // from class: com.yltx.oil.partner.modules.-$$Lambda$SplashActivity$5pBXkwYQUUpV4FeUDNbwpM8doF4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                SplashActivity.lambda$jumpToMainAfterSleep$0(SplashActivity.this, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$jumpToMainAfterSleep$0(SplashActivity splashActivity, Long l) {
        splashActivity.startActivity(new Intent(splashActivity, MainActivity.class));
        splashActivity.finish();
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, SplashActivity.class);
    }

    @Override // com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
        this.context = this;
        this.mPresenter.attachView(this);
        this.presenter.attachView(this);
        getSharedPreferences("android", 0).edit().clear().commit();
        setupUI();
    }

    protected void setupUI() {
        if (Build.VERSION.SDK_INT >= 23) {
            checkPermissions(this.needPermissions);
            return;
        }
        this.token = DataCache.getToken(this);
        if (!TextUtils.isEmpty(this.token) && !TextUtils.isEmpty(DataCache.getUserid(this))) {
            this.presenter.CheckVersionUseCase();
        } else {
            this.presenter.CheckVersionUseCase();
        }
    }

    public void toMain() {
        SharedPreferences sharedPreferences = getSharedPreferences("isFirstUse", 0);
        this.isFirstUse = sharedPreferences.getBoolean("isFirstUse", true);
        if (this.isFirstUse) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean("isFirstUse", false);
            edit.commit();
            jumpToMainAfterSleep();
            return;
        }
        jumpToMainAfterSleep();
    }

    private void checkPermissions(String... strArr) {
        List<String> findDeniedPermissions = findDeniedPermissions(strArr);
        if (findDeniedPermissions != null && findDeniedPermissions.size() > 0) {
            ActivityCompat.requestPermissions(this, (String[]) findDeniedPermissions.toArray(new String[findDeniedPermissions.size()]), 0);
            return;
        }
        this.token = DataCache.getToken(this);
        if (!TextUtils.isEmpty(this.token) && !TextUtils.isEmpty(DataCache.getUserid(this))) {
            this.presenter.CheckVersionUseCase();
        } else {
            this.presenter.CheckVersionUseCase();
        }
    }

    private List<String> findDeniedPermissions(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (ContextCompat.checkSelfPermission(this.context, str) != 0 || ActivityCompat.shouldShowRequestPermissionRationale(this, str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 0) {
            if (!CheckPermissionsUtils.verifyPermissions(iArr)) {
                CheckPermissionsUtils.showMissingPermissionDialog(this.context);
                return;
            }
            this.token = DataCache.getToken(this);
            if (!TextUtils.isEmpty(this.token) && !TextUtils.isEmpty(DataCache.getUserid(this))) {
                this.presenter.CheckVersionUseCase();
            } else {
                this.presenter.CheckVersionUseCase();
            }
        }
    }

    @Override // com.yltx.oil.partner.modules.login.view.LoginView, com.yltx.oil.partner.modules.main.view.SplashView
    public void onLoginSuccess(HttpResult<LoginInfo> httpResult) {
        PartnerApplication.getInstance().initLogin(httpResult.getData());
        toMain();
    }

    @Override // com.yltx.oil.partner.modules.main.view.SplashView
    public void onCheckVersionSuccess(VersionResponse versionResponse) {
        checkVersion(versionResponse);
    }

    @Override // com.yltx.oil.partner.modules.main.view.SplashView
    public void onCheckVersiononError(Throwable th) {
        toMain();
    }

    @Override // com.yltx.oil.partner.modules.login.view.LoginView, com.yltx.oil.partner.modules.main.view.SplashView
    public void onError(Throwable th) {
        toMain();
    }

    @Override // com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.mvp.views.ProgressView
    public void showProgress() {
        this.dialogUtils.showProgress(getContext());
    }

    @Override // com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.mvp.views.ProgressView
    public void hideProgress() {
        this.dialogUtils.hideProgress();
    }

    public static /* synthetic */ void lambda$checkUpdatedVersion$1(SplashActivity splashActivity) {
        splashActivity.toMain();
    }

    public void checkUpdatedVersion(int i, String str, String str2) {
        switch (i) {
            case 0:
                DownloadBuilder downloadOnly = AllenVersionChecker.getInstance().downloadOnly(UIData.create().setTitle("版本更新啦").setContent(str).setDownloadUrl(str2));
                downloadOnly.setOnCancelListener(new OnCancelListener() { // from class: com.yltx.oil.partner.modules.-$$Lambda$SplashActivity$YydiMYujOU8vcfnnTVzyQqsz_Vc
                    @Override // com.allenliu.versionchecklib.callback.OnCancelListener
                    public final void onCancel() {
                        SplashActivity.lambda$checkUpdatedVersion$1(SplashActivity.this);
                    }
                });
                downloadOnly.setForceUpdateListener(new ForceUpdateListener() { // from class: com.yltx.oil.partner.modules.-$$Lambda$SplashActivity$Qq-mJQDHe1yVXExPUUHMS2pCmGg
                    @Override // com.allenliu.versionchecklib.v2.callback.ForceUpdateListener
                    public final void onShouldForceUpdate() {
                        SplashActivity.lambda$checkUpdatedVersion$2();
                    }
                });
                downloadOnly.setShowDownloadingDialog(true).setShowNotification(true).setForceRedownload(true).executeMission(getContext());
                return;
            case 1:
                DownloadBuilder downloadOnly2 = AllenVersionChecker.getInstance().downloadOnly(UIData.create().setTitle("版本更新啦").setContent(str).setDownloadUrl(str2));
                downloadOnly2.setForceUpdateListener(new ForceUpdateListener() { // from class: com.yltx.oil.partner.modules.-$$Lambda$SplashActivity$Uqp2seJe_5p3Dku6q7hQkBQK4ww
                    @Override // com.allenliu.versionchecklib.v2.callback.ForceUpdateListener
                    public final void onShouldForceUpdate() {
                        SplashActivity.lambda$checkUpdatedVersion$3();
                    }
                });
                downloadOnly2.setShowDownloadingDialog(true).setForceRedownload(true).setShowNotification(true).executeMission(getContext());
                return;
            default:
                return;
        }
    }

    private void checkVersion(VersionResponse versionResponse) {
        if (versionResponse == null) {
            toMain();
        } else if (versionResponse.getSequence() != null) {
            int appVersionCode = CommonUtils.getAppVersionCode(getContext());
            String sequence = versionResponse.getSequence();
            if (appVersionCode < Integer.valueOf(sequence).intValue() && !OfflineResource.VOICE_DUYY.equals(versionResponse.getForced())) {
                checkUpdatedVersion(0, versionResponse.getChangeLog(), versionResponse.getVersionUrl());
            } else if (appVersionCode < Integer.valueOf(sequence).intValue() && OfflineResource.VOICE_DUYY.equals(versionResponse.getForced())) {
                checkUpdatedVersion(1, versionResponse.getChangeLog(), versionResponse.getVersionUrl());
            } else {
                toMain();
            }
        } else {
            toMain();
        }
    }
}