package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.mine.activity.InviteCourtesyActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {InviteCourtesyActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_InviteCourtesyActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface InviteCourtesyActivitySubcomponent extends AndroidInjector<InviteCourtesyActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<InviteCourtesyActivity> {
        }
    }

    @ActivityKey(InviteCourtesyActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(InviteCourtesyActivitySubcomponent.Builder builder);

    private BuildersModule_InviteCourtesyActivity() {
    }
}