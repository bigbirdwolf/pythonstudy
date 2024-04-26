package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.home.activity.MessageHomeActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {MessageHomeActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_MessageHomeActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface MessageHomeActivitySubcomponent extends AndroidInjector<MessageHomeActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<MessageHomeActivity> {
        }
    }

    @ActivityKey(MessageHomeActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(MessageHomeActivitySubcomponent.Builder builder);

    private BuildersModule_MessageHomeActivity() {
    }
}