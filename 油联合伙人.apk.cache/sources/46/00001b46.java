package dagger.internal;

/* loaded from: classes.dex */
public final class Preconditions {
    public static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    public static <T> T checkNotNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static <T> T checkNotNull(T t, String str, Object obj) {
        String valueOf;
        if (t == null) {
            if (!str.contains("%s")) {
                throw new IllegalArgumentException("errorMessageTemplate has no format specifiers");
            }
            if (str.indexOf("%s") != str.lastIndexOf("%s")) {
                throw new IllegalArgumentException("errorMessageTemplate has more than one format specifier");
            }
            if (obj instanceof Class) {
                valueOf = ((Class) obj).getCanonicalName();
            } else {
                valueOf = String.valueOf(obj);
            }
            throw new NullPointerException(str.replace("%s", valueOf));
        }
        return t;
    }

    private Preconditions() {
    }
}