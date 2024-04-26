package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.profit.activity.AccountdetailsActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {AccountdetailsActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_AccountdetailsActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface AccountdetailsActivitySubcomponent extends AndroidInjector<AccountdetailsActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<AccountdetailsActivity> {
        }
    }

    @ActivityKey(AccountdetailsActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(AccountdetailsActivitySubcomponent.Builder builder);

    private BuildersModule_AccountdetailsActivity() {
    }
}