package dagger.android;

import android.app.Fragment;
import dagger.MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class DaggerActivity_MembersInjector implements MembersInjector<DaggerActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> fragmentInjectorProvider;

    public DaggerActivity_MembersInjector(Provider<DispatchingAndroidInjector<Fragment>> provider) {
        this.fragmentInjectorProvider = provider;
    }

    public static MembersInjector<DaggerActivity> create(Provider<DispatchingAndroidInjector<Fragment>> provider) {
        return new DaggerActivity_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DaggerActivity daggerActivity) {
        injectFragmentInjector(daggerActivity, this.fragmentInjectorProvider.get());
    }

    public static void injectFragmentInjector(DaggerActivity daggerActivity, DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector) {
        daggerActivity.fragmentInjector = dispatchingAndroidInjector;
    }
}