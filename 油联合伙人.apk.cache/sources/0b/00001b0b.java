package dagger.android;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.android.AndroidInjector;
import dagger.internal.DaggerCollections;
import dagger.internal.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class DispatchingAndroidInjector<T> implements AndroidInjector<T> {
    private static final String NO_SUPERTYPES_BOUND_FORMAT = "No injector factory bound for Class<%s>";
    private static final String SUPERTYPES_BOUND_FORMAT = "No injector factory bound for Class<%1$s>. Injector factories were bound for supertypes of %1$s: %2$s. Did you mean to bind an injector factory for the subtype?";
    private final Map<String, Provider<AndroidInjector.Factory<? extends T>>> injectorFactories;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public DispatchingAndroidInjector(Map<Class<? extends T>, Provider<AndroidInjector.Factory<? extends T>>> map, Map<String, Provider<AndroidInjector.Factory<? extends T>>> map2) {
        this.injectorFactories = merge(map, map2);
    }

    private static <C, V> Map<String, V> merge(Map<Class<? extends C>, V> map, Map<String, V> map2) {
        if (map.isEmpty()) {
            return map2;
        }
        LinkedHashMap newLinkedHashMapWithExpectedSize = DaggerCollections.newLinkedHashMapWithExpectedSize(map.size() + map2.size());
        newLinkedHashMapWithExpectedSize.putAll(map2);
        for (Map.Entry<Class<? extends C>, V> entry : map.entrySet()) {
            newLinkedHashMapWithExpectedSize.put(entry.getKey().getName(), entry.getValue());
        }
        return Collections.unmodifiableMap(newLinkedHashMapWithExpectedSize);
    }

    @CanIgnoreReturnValue
    public boolean maybeInject(T t) {
        Provider<AndroidInjector.Factory<? extends T>> provider = this.injectorFactories.get(t.getClass().getName());
        if (provider == null) {
            return false;
        }
        AndroidInjector.Factory<? extends T> factory = provider.get();
        try {
            ((AndroidInjector) Preconditions.checkNotNull(factory.create(t), "%s.create(I) should not return null.", factory.getClass())).inject(t);
            return true;
        } catch (ClassCastException e) {
            throw new InvalidInjectorBindingException(String.format("%s does not implement AndroidInjector.Factory<%s>", factory.getClass().getCanonicalName(), t.getClass().getCanonicalName()), e);
        }
    }

    @Override // dagger.android.AndroidInjector
    public void inject(T t) {
        if (!maybeInject(t)) {
            throw new IllegalArgumentException(errorMessageSuggestions(t));
        }
    }

    /* loaded from: classes.dex */
    public static final class InvalidInjectorBindingException extends RuntimeException {
        InvalidInjectorBindingException(String str, ClassCastException classCastException) {
            super(str, classCastException);
        }
    }

    private String errorMessageSuggestions(T t) {
        ArrayList arrayList = new ArrayList();
        for (Class<?> cls = t.getClass(); cls != null; cls = cls.getSuperclass()) {
            if (this.injectorFactories.containsKey(cls.getCanonicalName())) {
                arrayList.add(cls.getCanonicalName());
            }
        }
        return arrayList.isEmpty() ? String.format(NO_SUPERTYPES_BOUND_FORMAT, t.getClass().getCanonicalName()) : String.format(SUPERTYPES_BOUND_FORMAT, t.getClass().getCanonicalName(), arrayList);
    }
}