package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.web.WebActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {WebActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_WebActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface WebActivitySubcomponent extends AndroidInjector<WebActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<WebActivity> {
        }
    }

    @ActivityKey(WebActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(WebActivitySubcomponent.Builder builder);

    private BuildersModule_WebActivity() {
    }
}