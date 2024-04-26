package dagger.android;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.umeng.analytics.pro.b;
import dagger.internal.Preconditions;

/* loaded from: classes.dex */
public final class AndroidInjection {
    private static final String TAG = "dagger.android";

    public static void inject(Activity activity) {
        Preconditions.checkNotNull(activity, "activity");
        Application application = activity.getApplication();
        if (!(application instanceof HasActivityInjector)) {
            throw new RuntimeException(String.format("%s does not implement %s", application.getClass().getCanonicalName(), HasActivityInjector.class.getCanonicalName()));
        }
        AndroidInjector<Activity> activityInjector = ((HasActivityInjector) application).activityInjector();
        Preconditions.checkNotNull(activityInjector, "%s.activityInjector() returned null", application.getClass());
        activityInjector.inject(activity);
    }

    public static void inject(Fragment fragment) {
        Preconditions.checkNotNull(fragment, "fragment");
        HasFragmentInjector findHasFragmentInjector = findHasFragmentInjector(fragment);
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, String.format("An injector for %s was found in %s", fragment.getClass().getCanonicalName(), findHasFragmentInjector.getClass().getCanonicalName()));
        }
        AndroidInjector<Fragment> fragmentInjector = findHasFragmentInjector.fragmentInjector();
        Preconditions.checkNotNull(fragmentInjector, "%s.fragmentInjector() returned null", findHasFragmentInjector.getClass());
        fragmentInjector.inject(fragment);
    }

    private static HasFragmentInjector findHasFragmentInjector(Fragment fragment) {
        Fragment fragment2 = fragment;
        do {
            fragment2 = fragment2.getParentFragment();
            if (fragment2 == null) {
                Activity activity = fragment.getActivity();
                if (activity instanceof HasFragmentInjector) {
                    return (HasFragmentInjector) activity;
                }
                if (activity.getApplication() instanceof HasFragmentInjector) {
                    return (HasFragmentInjector) activity.getApplication();
                }
                throw new IllegalArgumentException(String.format("No injector was found for %s", fragment.getClass().getCanonicalName()));
            }
        } while (!(fragment2 instanceof HasFragmentInjector));
        return (HasFragmentInjector) fragment2;
    }

    public static void inject(Service service) {
        Preconditions.checkNotNull(service, NotificationCompat.CATEGORY_SERVICE);
        Application application = service.getApplication();
        if (!(application instanceof HasServiceInjector)) {
            throw new RuntimeException(String.format("%s does not implement %s", application.getClass().getCanonicalName(), HasServiceInjector.class.getCanonicalName()));
        }
        AndroidInjector<Service> serviceInjector = ((HasServiceInjector) application).serviceInjector();
        Preconditions.checkNotNull(serviceInjector, "%s.serviceInjector() returned null", application.getClass());
        serviceInjector.inject(service);
    }

    public static void inject(BroadcastReceiver broadcastReceiver, Context context) {
        Preconditions.checkNotNull(broadcastReceiver, "broadcastReceiver");
        Preconditions.checkNotNull(context, b.Q);
        Application application = (Application) context.getApplicationContext();
        if (!(application instanceof HasBroadcastReceiverInjector)) {
            throw new RuntimeException(String.format("%s does not implement %s", application.getClass().getCanonicalName(), HasBroadcastReceiverInjector.class.getCanonicalName()));
        }
        AndroidInjector<BroadcastReceiver> broadcastReceiverInjector = ((HasBroadcastReceiverInjector) application).broadcastReceiverInjector();
        Preconditions.checkNotNull(broadcastReceiverInjector, "%s.broadcastReceiverInjector() returned null", application.getClass());
        broadcastReceiverInjector.inject(broadcastReceiver);
    }

    public static void inject(ContentProvider contentProvider) {
        Preconditions.checkNotNull(contentProvider, "contentProvider");
        Application application = (Application) contentProvider.getContext().getApplicationContext();
        if (!(application instanceof HasContentProviderInjector)) {
            throw new RuntimeException(String.format("%s does not implement %s", application.getClass().getCanonicalName(), HasContentProviderInjector.class.getCanonicalName()));
        }
        AndroidInjector<ContentProvider> contentProviderInjector = ((HasContentProviderInjector) application).contentProviderInjector();
        Preconditions.checkNotNull(contentProviderInjector, "%s.contentProviderInjector() returned null", application.getClass());
        contentProviderInjector.inject(contentProvider);
    }

    private AndroidInjection() {
    }
}