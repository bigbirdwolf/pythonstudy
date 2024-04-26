package dagger.releasablereferences;

import dagger.internal.GwtIncompatible;
import java.lang.annotation.Annotation;

@GwtIncompatible
@Deprecated
/* loaded from: classes.dex */
public interface TypedReleasableReferenceManager<M extends Annotation> extends ReleasableReferenceManager {
    M metadata();
}