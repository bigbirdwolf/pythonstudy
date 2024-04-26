package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.web.JsBridgeWebActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {JsBridgeWebActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_InviteFriendsActivityInjector {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface JsBridgeWebActivitySubcomponent extends AndroidInjector<JsBridgeWebActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<JsBridgeWebActivity> {
        }
    }

    @ActivityKey(JsBridgeWebActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(JsBridgeWebActivitySubcomponent.Builder builder);

    private BuildersModule_InviteFriendsActivityInjector() {
    }
}