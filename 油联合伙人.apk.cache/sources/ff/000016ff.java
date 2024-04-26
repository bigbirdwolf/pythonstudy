package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.profit.activity.ModificationBankCardsActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {ModificationBankCardsActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_ModificationBankCardsActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface ModificationBankCardsActivitySubcomponent extends AndroidInjector<ModificationBankCardsActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<ModificationBankCardsActivity> {
        }
    }

    @ActivityKey(ModificationBankCardsActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(ModificationBankCardsActivitySubcomponent.Builder builder);

    private BuildersModule_ModificationBankCardsActivity() {
    }
}