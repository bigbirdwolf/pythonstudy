package com.yltx.oil.partner.modules.mine.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.mine.presenter.ModifPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ModifyNicknameActivity_MembersInjector implements MembersInjector<ModifyNicknameActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<ModifPresenter> modifPresenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public ModifyNicknameActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<ModifPresenter> provider3) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.modifPresenterProvider = provider3;
    }

    public static MembersInjector<ModifyNicknameActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<ModifPresenter> provider3) {
        return new ModifyNicknameActivity_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ModifyNicknameActivity modifyNicknameActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(modifyNicknameActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(modifyNicknameActivity, this.frameworkFragmentInjectorProvider.get());
        injectModifPresenter(modifyNicknameActivity, this.modifPresenterProvider.get());
    }

    public static void injectModifPresenter(ModifyNicknameActivity modifyNicknameActivity, ModifPresenter modifPresenter) {
        modifyNicknameActivity.modifPresenter = modifPresenter;
    }
}