package dagger.android;

import android.app.Fragment;
import dagger.MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class DaggerDialogFragment_MembersInjector implements MembersInjector<DaggerDialogFragment> {
    private final Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider;

    public DaggerDialogFragment_MembersInjector(Provider<DispatchingAndroidInjector<Fragment>> provider) {
        this.childFragmentInjectorProvider = provider;
    }

    public static MembersInjector<DaggerDialogFragment> create(Provider<DispatchingAndroidInjector<Fragment>> provider) {
        return new DaggerDialogFragment_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DaggerDialogFragment daggerDialogFragment) {
        injectChildFragmentInjector(daggerDialogFragment, this.childFragmentInjectorProvider.get());
    }

    public static void injectChildFragmentInjector(DaggerDialogFragment daggerDialogFragment, DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector) {
        daggerDialogFragment.childFragmentInjector = dispatchingAndroidInjector;
    }
}