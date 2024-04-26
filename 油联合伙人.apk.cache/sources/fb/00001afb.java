package dagger.android;

/* loaded from: classes.dex */
final class AutoAnnotation_ReleaseReferencesAtCreator_createReleaseReferencesAt implements ReleaseReferencesAt {
    private final int value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoAnnotation_ReleaseReferencesAtCreator_createReleaseReferencesAt(int i) {
        this.value = i;
    }

    @Override // java.lang.annotation.Annotation
    public Class<? extends ReleaseReferencesAt> annotationType() {
        return ReleaseReferencesAt.class;
    }

    @Override // dagger.android.ReleaseReferencesAt
    public int value() {
        return this.value;
    }

    @Override // java.lang.annotation.Annotation
    public String toString() {
        return "@dagger.android.ReleaseReferencesAt(" + this.value + ')';
    }

    @Override // java.lang.annotation.Annotation
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof ReleaseReferencesAt) && this.value == ((ReleaseReferencesAt) obj).value();
    }

    @Override // java.lang.annotation.Annotation
    public int hashCode() {
        return this.value ^ 1335633679;
    }
}