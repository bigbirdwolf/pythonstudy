package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.profit.activity.WithdrawActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {WithdrawActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_WithdrawActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface WithdrawActivitySubcomponent extends AndroidInjector<WithdrawActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<WithdrawActivity> {
        }
    }

    @ActivityKey(WithdrawActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(WithdrawActivitySubcomponent.Builder builder);

    private BuildersModule_WithdrawActivity() {
    }
}