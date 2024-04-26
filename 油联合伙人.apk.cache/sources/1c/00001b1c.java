package dagger.android.support;

import android.support.v4.app.Fragment;
import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.multibindings.Multibinds;
import java.util.Map;

@Module(includes = {AndroidInjectionModule.class})
/* loaded from: classes.dex */
public abstract class AndroidSupportInjectionModule {
    @Multibinds
    abstract Map<Class<? extends Fragment>, AndroidInjector.Factory<? extends Fragment>> supportFragmentInjectorFactories();

    @Multibinds
    abstract Map<String, AndroidInjector.Factory<? extends Fragment>> supportFragmentInjectorFactoriesWithStringKeys();

    private AndroidSupportInjectionModule() {
    }
}