package android.support.v4.app;

import android.os.Bundle;
import java.util.Set;

@Deprecated
/* loaded from: classes.dex */
class RemoteInputCompatBase {

    @Deprecated
    /* loaded from: classes.dex */
    public static abstract class RemoteInput {

        @Deprecated
        /* loaded from: classes.dex */
        public interface Factory {
            RemoteInput build(String str, CharSequence charSequence, CharSequence[] charSequenceArr, boolean z, Bundle bundle, Set<String> set);

            RemoteInput[] newArray(int i);
        }

        @Deprecated
        protected abstract boolean getAllowFreeFormInput();

        @Deprecated
        protected abstract Set<String> getAllowedDataTypes();

        @Deprecated
        protected abstract CharSequence[] getChoices();

        @Deprecated
        protected abstract Bundle getExtras();

        @Deprecated
        protected abstract CharSequence getLabel();

        @Deprecated
        protected abstract String getResultKey();
    }

    RemoteInputCompatBase() {
    }
}