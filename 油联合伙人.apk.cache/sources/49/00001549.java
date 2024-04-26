package com.yltx.oil.partner.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.data.network.Config;
import com.yltx.oil.partner.data.network.UserToken;
import com.yltx.oil.partner.data.response.LoginInfo;
import com.yltx.oil.partner.injections.components.DaggerGlobalComponent;
import com.yltx.oil.partner.injections.instrumentation.ApplicationInstrumentation;
import com.yltx.oil.partner.navigation.Navigator;
import com.yltx.oil.partner.oss.OSSFileHelper;
import com.yltx.oil.partner.utils.ContextHolder;
import com.yltx.oil.partner.utils.DataCache;
import com.yltx.oil.partner.utils.UpdateConfiguration;
import com.yltx.oil.partner.utils.UpdateManager;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class PartnerApplication extends DaggerApplication {
    public static PartnerApplication instance;
    public static Context mContext;
    public static String name;
    public static OSSFileHelper ossFileHelper;
    private LoginInfo currentUser;
    public boolean isLoading;
    @Inject
    ApplicationInstrumentation mInstrumentation;
    @Inject
    Navigator mNavigator;
    public String token;
    private LoginInfo userInfos;
    private UserToken userToken;

    @Override // dagger.android.DaggerApplication, android.app.Application
    public void onCreate() {
        super.onCreate();
        instance = this;
        this.mInstrumentation.init();
        ContextHolder.initial(instance);
        mContext = this;
        initUpdateManager();
        this.userToken = UserToken.getInstance();
        if (!TextUtils.isEmpty(DataCache.getToken(this))) {
            this.isLoading = true;
            this.userInfos = (LoginInfo) new Gson().fromJson(DataCache.getUserToken(instance), new TypeToken<LoginInfo>() { // from class: com.yltx.oil.partner.base.PartnerApplication.1
            }.getType());
        }
        Stetho.initializeWithDefaults(mContext);
        ossFileHelper = new OSSFileHelper(this);
        UMConfigure.init(this, Config.UMENGKEY, "Umeng", 1, "");
        UMConfigure.setLogEnabled(true);
        PlatformConfig.setWeixin("wx7e89da4bf9137b69", "06ee399313c93bcf076902c896a6b4df");
    }

    public static PartnerApplication getInstance() {
        return instance;
    }

    private void initUpdateManager() {
        UpdateManager.getInstance(getApplicationContext()).init(new UpdateConfiguration.Builder(getApplicationContext()).setNotificationTitle(getString(R.string.app_name)).setNotificationDescription("正在下载").setDownloadIconBig(R.mipmap.ic_launcher).setDownloadIconSmall(R.mipmap.ic_launcher).build());
    }

    public void setCurrentUser(LoginInfo loginInfo) {
        this.currentUser = loginInfo;
    }

    public LoginInfo getCurrentUser() {
        return this.currentUser;
    }

    public Navigator getNavigator() {
        return this.mNavigator;
    }

    @Override // dagger.android.support.DaggerApplication, dagger.android.DaggerApplication
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerGlobalComponent.builder().create(this);
    }

    public void setUserInfos(LoginInfo loginInfo) {
        this.userInfos = loginInfo;
    }

    public LoginInfo getUserInfos() {
        return this.userInfos;
    }

    public void initLogin(LoginInfo loginInfo) {
        this.isLoading = true;
        this.token = loginInfo.getUserInfo().getToken();
        setUserInfos(loginInfo);
        TextUtils.isEmpty(loginInfo.getUserInfo().getRowId());
        getUserToken().setToken(instance.token);
        DataCache.setUserid(this, loginInfo.getUserInfo().getRowId());
        getUserToken().setPhone(loginInfo.getUserInfo().getPhone());
        DataCache.setToken(this, loginInfo.getUserInfo().getToken());
        DataCache.setPartnerInfo(this, loginInfo.getPartnerInfo());
        DataCache.setUserToken(this, loginInfo);
        SharedPreferences.Editor edit = getSharedPreferences("userId", 0).edit();
        edit.putString("Phone", loginInfo.getUserInfo().getPhone());
        edit.commit();
    }

    public UserToken getUserToken() {
        return this.userToken;
    }
}