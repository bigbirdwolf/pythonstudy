package dagger.android.support;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import dagger.android.AndroidInjector;
import dagger.internal.Preconditions;

/* loaded from: classes.dex */
public final class AndroidSupportInjection {
    private static final String TAG = "dagger.android.support";

    public static void inject(Fragment fragment) {
        Preconditions.checkNotNull(fragment, "fragment");
        HasSupportFragmentInjector findHasFragmentInjector = findHasFragmentInjector(fragment);
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, String.format("An injector for %s was found in %s", fragment.getClass().getCanonicalName(), findHasFragmentInjector.getClass().getCanonicalName()));
        }
        AndroidInjector<Fragment> supportFragmentInjector = findHasFragmentInjector.supportFragmentInjector();
        Preconditions.checkNotNull(supportFragmentInjector, "%s.supportFragmentInjector() returned null", findHasFragmentInjector.getClass());
        supportFragmentInjector.inject(fragment);
    }

    private static HasSupportFragmentInjector findHasFragmentInjector(Fragment fragment) {
        Fragment fragment2 = fragment;
        do {
            fragment2 = fragment2.getParentFragment();
            if (fragment2 == null) {
                FragmentActivity activity = fragment.getActivity();
                if (activity instanceof HasSupportFragmentInjector) {
                    return (HasSupportFragmentInjector) activity;
                }
                if (activity.getApplication() instanceof HasSupportFragmentInjector) {
                    return (HasSupportFragmentInjector) activity.getApplication();
                }
                throw new IllegalArgumentException(String.format("No injector was found for %s", fragment.getClass().getCanonicalName()));
            }
        } while (!(fragment2 instanceof HasSupportFragmentInjector));
        return (HasSupportFragmentInjector) fragment2;
    }

    private AndroidSupportInjection() {
    }
}