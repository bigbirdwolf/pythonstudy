package dagger.android.support;

import android.support.v4.app.Fragment;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import javax.inject.Inject;

/* loaded from: classes.dex */
public abstract class DaggerApplication extends dagger.android.DaggerApplication implements HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;

    @Override // dagger.android.DaggerApplication
    protected abstract AndroidInjector<? extends DaggerApplication> applicationInjector();

    @Override // dagger.android.support.HasSupportFragmentInjector
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return this.supportFragmentInjector;
    }
}