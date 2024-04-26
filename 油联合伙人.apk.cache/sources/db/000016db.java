package com.yltx.oil.partner.injections.modules;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.login.fragment.FragmentPwdLogin;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {FragmentPwdLoginSubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_FragmentPwdLogin {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface FragmentPwdLoginSubcomponent extends AndroidInjector<FragmentPwdLogin> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<FragmentPwdLogin> {
        }
    }

    @FragmentKey(FragmentPwdLogin.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(FragmentPwdLoginSubcomponent.Builder builder);

    private BuildersModule_FragmentPwdLogin() {
    }
}