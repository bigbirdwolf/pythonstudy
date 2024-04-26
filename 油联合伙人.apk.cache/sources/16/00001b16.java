package dagger.android;

import dagger.internal.GwtIncompatible;
import dagger.releasablereferences.CanReleaseReferences;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@GwtIncompatible
@Target({ElementType.ANNOTATION_TYPE})
@Deprecated
@CanReleaseReferences
@Documented
/* loaded from: classes.dex */
public @interface ReleaseReferencesAt {
    int value();
}