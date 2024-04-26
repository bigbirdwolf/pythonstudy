package dagger.android.support;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import javax.inject.Inject;

/* loaded from: classes.dex */
public abstract class DaggerAppCompatDialogFragment extends AppCompatDialogFragment implements HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> childFragmentInjector;

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override // dagger.android.support.HasSupportFragmentInjector
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return this.childFragmentInjector;
    }
}