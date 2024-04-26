package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.profit.activity.BindingbankcardsActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {BindingbankcardsActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_BindingbankcardsActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface BindingbankcardsActivitySubcomponent extends AndroidInjector<BindingbankcardsActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<BindingbankcardsActivity> {
        }
    }

    @ActivityKey(BindingbankcardsActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(BindingbankcardsActivitySubcomponent.Builder builder);

    private BuildersModule_BindingbankcardsActivity() {
    }
}