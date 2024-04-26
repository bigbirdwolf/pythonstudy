package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.home.activity.ShareDetailsActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {ShareDetailsActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_ShareDetailsActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface ShareDetailsActivitySubcomponent extends AndroidInjector<ShareDetailsActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<ShareDetailsActivity> {
        }
    }

    @ActivityKey(ShareDetailsActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(ShareDetailsActivitySubcomponent.Builder builder);

    private BuildersModule_ShareDetailsActivity() {
    }
}