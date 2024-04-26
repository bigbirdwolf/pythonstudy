package dagger.internal;

import android.support.v7.widget.ActivityChooserView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes.dex */
public final class DaggerCollections {
    private static final int MAX_POWER_OF_TWO = 1073741824;

    private static int calculateInitialCapacity(int i) {
        return i < 3 ? i + 1 : i < MAX_POWER_OF_TWO ? (int) ((i / 0.75f) + 1.0f) : ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    }

    private DaggerCollections() {
    }

    public static <T> List<T> presizedList(int i) {
        if (i == 0) {
            return Collections.emptyList();
        }
        return new ArrayList(i);
    }

    public static boolean hasDuplicates(List<?> list) {
        if (list.size() < 2) {
            return false;
        }
        return list.size() != new HashSet(list).size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> HashSet<T> newHashSetWithExpectedSize(int i) {
        return new HashSet<>(calculateInitialCapacity(i));
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithExpectedSize(int i) {
        return new LinkedHashMap<>(calculateInitialCapacity(i));
    }
}