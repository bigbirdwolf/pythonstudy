package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.mine.activity.ModifyNicknameActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {ModifyNicknameActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_ModifyNicknameActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface ModifyNicknameActivitySubcomponent extends AndroidInjector<ModifyNicknameActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<ModifyNicknameActivity> {
        }
    }

    @ActivityKey(ModifyNicknameActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(ModifyNicknameActivitySubcomponent.Builder builder);

    private BuildersModule_ModifyNicknameActivity() {
    }
}