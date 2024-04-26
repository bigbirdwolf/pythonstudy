package dagger.android;

import android.app.Fragment;
import dagger.MapKey;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@MapKey
@Target({ElementType.METHOD})
@Documented
/* loaded from: classes.dex */
public @interface FragmentKey {
    Class<? extends Fragment> value();
}