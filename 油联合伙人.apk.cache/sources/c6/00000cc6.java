package com.facebook.stetho.common.android;

import android.app.Dialog;
import android.content.res.Resources;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import com.facebook.stetho.common.android.FragmentCompat;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
final class FragmentCompatSupportLib extends FragmentCompat<Fragment, DialogFragment, FragmentManager, FragmentActivity> {
    private static final FragmentAccessorSupportLib sFragmentAccessor = new FragmentAccessorSupportLib();
    private static final DialogFragmentAccessorSupportLib sDialogFragmentAccessor = new DialogFragmentAccessorSupportLib();
    private static final FragmentCompat.FragmentManagerAccessorViaReflection<FragmentManager, Fragment> sFragmentManagerAccessor = new FragmentCompat.FragmentManagerAccessorViaReflection<>();
    private static final FragmentActivityAccessorSupportLib sFragmentActivityAccessor = new FragmentActivityAccessorSupportLib();

    @Override // com.facebook.stetho.common.android.FragmentCompat
    public Class<Fragment> getFragmentClass() {
        return Fragment.class;
    }

    @Override // com.facebook.stetho.common.android.FragmentCompat
    public Class<DialogFragment> getDialogFragmentClass() {
        return DialogFragment.class;
    }

    @Override // com.facebook.stetho.common.android.FragmentCompat
    public Class<FragmentActivity> getFragmentActivityClass() {
        return FragmentActivity.class;
    }

    @Override // com.facebook.stetho.common.android.FragmentCompat
    public FragmentAccessor<Fragment, FragmentManager> forFragment() {
        return sFragmentAccessor;
    }

    @Override // com.facebook.stetho.common.android.FragmentCompat
    public DialogFragmentAccessor<DialogFragment, Fragment, FragmentManager> forDialogFragment() {
        return sDialogFragmentAccessor;
    }

    @Override // com.facebook.stetho.common.android.FragmentCompat
    public FragmentManagerAccessor<FragmentManager, Fragment> forFragmentManager() {
        return sFragmentManagerAccessor;
    }

    @Override // com.facebook.stetho.common.android.FragmentCompat
    public FragmentActivityAccessor<FragmentActivity, FragmentManager> forFragmentActivity() {
        return sFragmentActivityAccessor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class FragmentAccessorSupportLib implements FragmentAccessor<Fragment, FragmentManager> {
        private FragmentAccessorSupportLib() {
        }

        @Override // com.facebook.stetho.common.android.FragmentAccessor
        @Nullable
        public FragmentManager getFragmentManager(Fragment fragment) {
            return fragment.getFragmentManager();
        }

        @Override // com.facebook.stetho.common.android.FragmentAccessor
        public Resources getResources(Fragment fragment) {
            return fragment.getResources();
        }

        @Override // com.facebook.stetho.common.android.FragmentAccessor
        public int getId(Fragment fragment) {
            return fragment.getId();
        }

        @Override // com.facebook.stetho.common.android.FragmentAccessor
        @Nullable
        public String getTag(Fragment fragment) {
            return fragment.getTag();
        }

        @Override // com.facebook.stetho.common.android.FragmentAccessor
        @Nullable
        public View getView(Fragment fragment) {
            return fragment.getView();
        }

        @Override // com.facebook.stetho.common.android.FragmentAccessor
        @Nullable
        public FragmentManager getChildFragmentManager(Fragment fragment) {
            return fragment.getChildFragmentManager();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class DialogFragmentAccessorSupportLib extends FragmentAccessorSupportLib implements DialogFragmentAccessor<DialogFragment, Fragment, FragmentManager> {
        private DialogFragmentAccessorSupportLib() {
            super();
        }

        @Override // com.facebook.stetho.common.android.DialogFragmentAccessor
        public Dialog getDialog(DialogFragment dialogFragment) {
            return dialogFragment.getDialog();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class FragmentActivityAccessorSupportLib implements FragmentActivityAccessor<FragmentActivity, FragmentManager> {
        private FragmentActivityAccessorSupportLib() {
        }

        @Override // com.facebook.stetho.common.android.FragmentActivityAccessor
        @Nullable
        public FragmentManager getFragmentManager(FragmentActivity fragmentActivity) {
            return fragmentActivity.getSupportFragmentManager();
        }
    }
}