package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.mine.activity.InvitationDetailsActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {InvitationDetailsActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_InvitationDetailsActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface InvitationDetailsActivitySubcomponent extends AndroidInjector<InvitationDetailsActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<InvitationDetailsActivity> {
        }
    }

    @ActivityKey(InvitationDetailsActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(InvitationDetailsActivitySubcomponent.Builder builder);

    private BuildersModule_InvitationDetailsActivity() {
    }
}