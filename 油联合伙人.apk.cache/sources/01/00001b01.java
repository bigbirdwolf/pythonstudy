package dagger.android;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import com.google.errorprone.annotations.ForOverride;
import javax.inject.Inject;

/* loaded from: classes.dex */
public abstract class DaggerApplication extends Application implements HasActivityInjector, HasFragmentInjector, HasServiceInjector, HasBroadcastReceiverInjector, HasContentProviderInjector {
    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;
    @Inject
    DispatchingAndroidInjector<BroadcastReceiver> broadcastReceiverInjector;
    @Inject
    DispatchingAndroidInjector<ContentProvider> contentProviderInjector;
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;
    private volatile boolean needToInject = true;
    @Inject
    DispatchingAndroidInjector<Service> serviceInjector;

    @ForOverride
    protected abstract AndroidInjector<? extends DaggerApplication> applicationInjector();

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        injectIfNecessary();
    }

    private void injectIfNecessary() {
        if (this.needToInject) {
            synchronized (this) {
                if (this.needToInject) {
                    applicationInjector().inject(this);
                    if (this.needToInject) {
                        throw new IllegalStateException("The AndroidInjector returned from applicationInjector() did not inject the DaggerApplication");
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public void setInjected() {
        this.needToInject = false;
    }

    @Override // dagger.android.HasActivityInjector
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return this.activityInjector;
    }

    @Override // dagger.android.HasFragmentInjector
    public DispatchingAndroidInjector<Fragment> fragmentInjector() {
        return this.fragmentInjector;
    }

    @Override // dagger.android.HasBroadcastReceiverInjector
    public DispatchingAndroidInjector<BroadcastReceiver> broadcastReceiverInjector() {
        return this.broadcastReceiverInjector;
    }

    @Override // dagger.android.HasServiceInjector
    public DispatchingAndroidInjector<Service> serviceInjector() {
        return this.serviceInjector;
    }

    @Override // dagger.android.HasContentProviderInjector
    public AndroidInjector<ContentProvider> contentProviderInjector() {
        injectIfNecessary();
        return this.contentProviderInjector;
    }
}