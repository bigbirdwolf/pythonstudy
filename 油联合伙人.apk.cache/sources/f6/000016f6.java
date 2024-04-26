package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.home.activity.MessageDetailsActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {MessageDetailsActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_MessageDetailsActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface MessageDetailsActivitySubcomponent extends AndroidInjector<MessageDetailsActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<MessageDetailsActivity> {
        }
    }

    @ActivityKey(MessageDetailsActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(MessageDetailsActivitySubcomponent.Builder builder);

    private BuildersModule_MessageDetailsActivity() {
    }
}