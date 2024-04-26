package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.mine.activity.UpdatePwdActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {UpdatePwdActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_UpdatePwdActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface UpdatePwdActivitySubcomponent extends AndroidInjector<UpdatePwdActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<UpdatePwdActivity> {
        }
    }

    @ActivityKey(UpdatePwdActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(UpdatePwdActivitySubcomponent.Builder builder);

    private BuildersModule_UpdatePwdActivity() {
    }
}