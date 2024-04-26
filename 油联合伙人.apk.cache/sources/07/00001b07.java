package dagger.android;

import android.app.Fragment;
import android.content.Context;
import javax.inject.Inject;

@Deprecated
/* loaded from: classes.dex */
public abstract class DaggerFragment extends Fragment implements HasFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> childFragmentInjector;

    @Override // android.app.Fragment
    public void onAttach(Context context) {
        AndroidInjection.inject(this);
        super.onAttach(context);
    }

    @Override // dagger.android.HasFragmentInjector
    public AndroidInjector<Fragment> fragmentInjector() {
        return this.childFragmentInjector;
    }
}