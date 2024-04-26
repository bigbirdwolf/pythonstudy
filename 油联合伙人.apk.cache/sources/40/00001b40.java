package dagger.internal;

import com.umeng.analytics.pro.b;
import dagger.Lazy;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MapProviderFactory<K, V> implements Factory<Map<K, Provider<V>>>, Lazy<Map<K, Provider<V>>> {
    private final Map<K, Provider<V>> contributingMap;

    public static <K, V> Builder<K, V> builder(int i) {
        return new Builder<>(i);
    }

    private MapProviderFactory(Map<K, Provider<V>> map) {
        this.contributingMap = Collections.unmodifiableMap(map);
    }

    @Override // javax.inject.Provider
    public Map<K, Provider<V>> get() {
        return this.contributingMap;
    }

    /* loaded from: classes.dex */
    public static final class Builder<K, V> {
        private final LinkedHashMap<K, Provider<V>> map;

        private Builder(int i) {
            this.map = DaggerCollections.newLinkedHashMapWithExpectedSize(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Builder<K, V> put(K k, Provider<V> provider) {
            this.map.put(Preconditions.checkNotNull(k, "key"), Preconditions.checkNotNull(provider, b.L));
            return this;
        }

        public MapProviderFactory<K, V> build() {
            return new MapProviderFactory<>(this.map);
        }
    }
}