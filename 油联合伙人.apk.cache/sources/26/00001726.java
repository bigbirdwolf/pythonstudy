package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.home.activity.SearchResultActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {SearchResultActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_SearchResultActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface SearchResultActivitySubcomponent extends AndroidInjector<SearchResultActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<SearchResultActivity> {
        }
    }

    @ActivityKey(SearchResultActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(SearchResultActivitySubcomponent.Builder builder);

    private BuildersModule_SearchResultActivity() {
    }
}