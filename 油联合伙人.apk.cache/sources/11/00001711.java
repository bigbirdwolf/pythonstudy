package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.profit.activity.OrderdetailsActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {OrderdetailsActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_OrderdetailsActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface OrderdetailsActivitySubcomponent extends AndroidInjector<OrderdetailsActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<OrderdetailsActivity> {
        }
    }

    @ActivityKey(OrderdetailsActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(OrderdetailsActivitySubcomponent.Builder builder);

    private BuildersModule_OrderdetailsActivity() {
    }
}