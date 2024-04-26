package dagger.android;

import android.content.ContentProvider;
import android.support.annotation.CallSuper;

/* loaded from: classes.dex */
public abstract class DaggerContentProvider extends ContentProvider {
    @Override // android.content.ContentProvider
    @CallSuper
    public boolean onCreate() {
        AndroidInjection.inject(this);
        return true;
    }
}