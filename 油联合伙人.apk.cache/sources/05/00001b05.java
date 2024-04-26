package dagger.android;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import javax.inject.Inject;

/* loaded from: classes.dex */
public abstract class DaggerDialogFragment extends DialogFragment implements HasFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> childFragmentInjector;

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onAttach(Context context) {
        AndroidInjection.inject(this);
        super.onAttach(context);
    }

    @Override // dagger.android.HasFragmentInjector
    public AndroidInjector<Fragment> fragmentInjector() {
        return this.childFragmentInjector;
    }
}