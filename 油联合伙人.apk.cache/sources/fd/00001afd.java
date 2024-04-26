package dagger.android;

import android.content.ContentProvider;
import dagger.MapKey;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@MapKey
@Target({ElementType.METHOD})
@Documented
/* loaded from: classes.dex */
public @interface ContentProviderKey {
    Class<? extends ContentProvider> value();
}