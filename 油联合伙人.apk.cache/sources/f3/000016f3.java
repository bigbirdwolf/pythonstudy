package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.home.activity.MessageBulletinActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {MessageBulletinActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_MessageBulletinActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface MessageBulletinActivitySubcomponent extends AndroidInjector<MessageBulletinActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<MessageBulletinActivity> {
        }
    }

    @ActivityKey(MessageBulletinActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(MessageBulletinActivitySubcomponent.Builder builder);

    private BuildersModule_MessageBulletinActivity() {
    }
}