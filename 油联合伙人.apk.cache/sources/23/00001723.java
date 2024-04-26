package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.home.activity.SearchHomeActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {SearchHomeActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_SearchHomeActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface SearchHomeActivitySubcomponent extends AndroidInjector<SearchHomeActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<SearchHomeActivity> {
        }
    }

    @ActivityKey(SearchHomeActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(SearchHomeActivitySubcomponent.Builder builder);

    private BuildersModule_SearchHomeActivity() {
    }
}