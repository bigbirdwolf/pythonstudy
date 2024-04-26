package com.facebook.stetho.common.android;

import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public interface FragmentManagerAccessor<FRAGMENT_MANAGER, FRAGMENT> {
    @Nullable
    List<FRAGMENT> getAddedFragments(FRAGMENT_MANAGER fragment_manager);
}