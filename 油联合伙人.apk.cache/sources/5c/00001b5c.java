package dagger.releasablereferences;

import dagger.internal.GwtIncompatible;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@GwtIncompatible
@Target({ElementType.ANNOTATION_TYPE})
@Deprecated
@Documented
/* loaded from: classes.dex */
public @interface CanReleaseReferences {
}