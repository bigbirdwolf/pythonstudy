package com.facebook.stetho.common.android;

import android.app.Activity;
import android.os.Build;
import com.facebook.stetho.common.ReflectionUtil;
import java.lang.reflect.Field;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
/* loaded from: classes.dex */
public abstract class FragmentCompat<FRAGMENT, DIALOG_FRAGMENT, FRAGMENT_MANAGER, FRAGMENT_ACTIVITY extends Activity> {
    private static FragmentCompat sFrameworkInstance;
    private static final boolean sHasSupportFragment;
    private static FragmentCompat sSupportInstance;

    public abstract DialogFragmentAccessor<DIALOG_FRAGMENT, FRAGMENT, FRAGMENT_MANAGER> forDialogFragment();

    public abstract FragmentAccessor<FRAGMENT, FRAGMENT_MANAGER> forFragment();

    public abstract FragmentActivityAccessor<FRAGMENT_ACTIVITY, FRAGMENT_MANAGER> forFragmentActivity();

    public abstract FragmentManagerAccessor<FRAGMENT_MANAGER, FRAGMENT> forFragmentManager();

    public abstract Class<DIALOG_FRAGMENT> getDialogFragmentClass();

    public abstract Class<FRAGMENT_ACTIVITY> getFragmentActivityClass();

    public abstract Class<FRAGMENT> getFragmentClass();

    static {
        sHasSupportFragment = ReflectionUtil.tryGetClassForName("android.support.v4.app.Fragment") != null;
    }

    @Nullable
    public static FragmentCompat getFrameworkInstance() {
        if (sFrameworkInstance == null && Build.VERSION.SDK_INT >= 11) {
            sFrameworkInstance = new FragmentCompatFramework();
        }
        return sFrameworkInstance;
    }

    @Nullable
    public static FragmentCompat getSupportLibInstance() {
        if (sSupportInstance == null && sHasSupportFragment) {
            sSupportInstance = new FragmentCompatSupportLib();
        }
        return sSupportInstance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class FragmentManagerAccessorViaReflection<FRAGMENT_MANAGER, FRAGMENT> implements FragmentManagerAccessor<FRAGMENT_MANAGER, FRAGMENT> {
        @Nullable
        private Field mFieldMAdded;

        @Override // com.facebook.stetho.common.android.FragmentManagerAccessor
        @Nullable
        public List<FRAGMENT> getAddedFragments(FRAGMENT_MANAGER fragment_manager) {
            Field tryGetDeclaredField;
            if (this.mFieldMAdded == null && (tryGetDeclaredField = ReflectionUtil.tryGetDeclaredField(fragment_manager.getClass(), "mAdded")) != null) {
                tryGetDeclaredField.setAccessible(true);
                this.mFieldMAdded = tryGetDeclaredField;
            }
            if (this.mFieldMAdded != null) {
                return (List) ReflectionUtil.getFieldValue(this.mFieldMAdded, fragment_manager);
            }
            return null;
        }
    }
}