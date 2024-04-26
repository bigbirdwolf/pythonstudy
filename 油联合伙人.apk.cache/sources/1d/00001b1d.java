package dagger.android.support;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import javax.inject.Inject;

/* loaded from: classes.dex */
public abstract class DaggerAppCompatActivity extends AppCompatActivity implements HasFragmentInjector, HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> frameworkFragmentInjector;
    @Inject
    DispatchingAndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        AndroidInjection.inject(this);
        super.onCreate(bundle);
    }

    @Override // dagger.android.support.HasSupportFragmentInjector
    public AndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector() {
        return this.supportFragmentInjector;
    }

    @Override // dagger.android.HasFragmentInjector
    public AndroidInjector<Fragment> fragmentInjector() {
        return this.frameworkFragmentInjector;
    }
}