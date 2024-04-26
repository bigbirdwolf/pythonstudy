package com.facebook.stetho.inspector.elements.android;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.StringUtil;
import com.facebook.stetho.common.android.FragmentCompat;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.Descriptor;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
final class ActivityDescriptor extends AbstractChainedDescriptor<Activity> implements HighlightableDescriptor {
    @Override // com.facebook.stetho.inspector.elements.AbstractChainedDescriptor
    protected /* bridge */ /* synthetic */ void onGetChildren(Activity activity, Accumulator accumulator) {
        onGetChildren2(activity, (Accumulator<Object>) accumulator);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.stetho.inspector.elements.AbstractChainedDescriptor
    public String onGetNodeName(Activity activity) {
        return StringUtil.removePrefix(activity.getClass().getName(), "android.app.");
    }

    /* renamed from: onGetChildren  reason: avoid collision after fix types in other method */
    protected void onGetChildren2(Activity activity, Accumulator<Object> accumulator) {
        getDialogFragments(FragmentCompat.getSupportLibInstance(), activity, accumulator);
        getDialogFragments(FragmentCompat.getFrameworkInstance(), activity, accumulator);
        Window window = activity.getWindow();
        if (window != null) {
            accumulator.store(window);
        }
    }

    @Override // com.facebook.stetho.inspector.elements.android.HighlightableDescriptor
    public View getViewForHighlighting(Object obj) {
        Descriptor.Host host = getHost();
        if (host instanceof AndroidDescriptorHost) {
            return ((AndroidDescriptorHost) host).getHighlightingView(((Activity) obj).getWindow());
        }
        return null;
    }

    private static void getDialogFragments(@Nullable FragmentCompat fragmentCompat, Activity activity, Accumulator<Object> accumulator) {
        Object fragmentManager;
        List addedFragments;
        if (fragmentCompat == null || !fragmentCompat.getFragmentActivityClass().isInstance(activity) || (fragmentManager = fragmentCompat.forFragmentActivity().getFragmentManager(activity)) == null || (addedFragments = fragmentCompat.forFragmentManager().getAddedFragments(fragmentManager)) == null) {
            return;
        }
        int size = addedFragments.size();
        for (int i = 0; i < size; i++) {
            Object obj = addedFragments.get(i);
            if (fragmentCompat.getDialogFragmentClass().isInstance(obj)) {
                accumulator.store(obj);
            }
        }
    }
}