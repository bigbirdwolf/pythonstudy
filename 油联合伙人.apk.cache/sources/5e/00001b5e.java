package dagger.releasablereferences;

import dagger.internal.GwtIncompatible;
import java.lang.annotation.Annotation;

@GwtIncompatible
@Deprecated
/* loaded from: classes.dex */
public interface ReleasableReferenceManager {
    void releaseStrongReferences();

    void restoreStrongReferences();

    Class<? extends Annotation> scope();
}