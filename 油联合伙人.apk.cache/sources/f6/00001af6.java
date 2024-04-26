package dagger.android;

import dagger.BindsInstance;

/* loaded from: classes.dex */
public interface AndroidInjector<T> {

    /* loaded from: classes.dex */
    public interface Factory<T> {
        AndroidInjector<T> create(T t);
    }

    void inject(T t);

    /* loaded from: classes.dex */
    public static abstract class Builder<T> implements Factory<T> {
        public abstract AndroidInjector<T> build();

        @BindsInstance
        public abstract void seedInstance(T t);

        @Override // dagger.android.AndroidInjector.Factory
        public final AndroidInjector<T> create(T t) {
            seedInstance(t);
            return build();
        }
    }
}