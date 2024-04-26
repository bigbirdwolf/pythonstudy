package dagger.android.support;

import android.support.v4.app.Fragment;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class DaggerAppCompatDialogFragment_MembersInjector implements MembersInjector<DaggerAppCompatDialogFragment> {
    private final Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider;

    public DaggerAppCompatDialogFragment_MembersInjector(Provider<DispatchingAndroidInjector<Fragment>> provider) {
        this.childFragmentInjectorProvider = provider;
    }

    public static MembersInjector<DaggerAppCompatDialogFragment> create(Provider<DispatchingAndroidInjector<Fragment>> provider) {
        return new DaggerAppCompatDialogFragment_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DaggerAppCompatDialogFragment daggerAppCompatDialogFragment) {
        injectChildFragmentInjector(daggerAppCompatDialogFragment, this.childFragmentInjectorProvider.get());
    }

    public static void injectChildFragmentInjector(DaggerAppCompatDialogFragment daggerAppCompatDialogFragment, DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector) {
        daggerAppCompatDialogFragment.childFragmentInjector = dispatchingAndroidInjector;
    }
}