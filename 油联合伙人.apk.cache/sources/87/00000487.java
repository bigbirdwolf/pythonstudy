package android.support.v4.os;

import android.support.annotation.RestrictTo;
import java.util.Locale;
import org.apache.commons.cli.HelpFormatter;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
final class LocaleHelper {
    LocaleHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Locale forLanguageTag(String str) {
        if (str.contains(HelpFormatter.DEFAULT_OPT_PREFIX)) {
            String[] split = str.split(HelpFormatter.DEFAULT_OPT_PREFIX);
            if (split.length > 2) {
                return new Locale(split[0], split[1], split[2]);
            }
            if (split.length > 1) {
                return new Locale(split[0], split[1]);
            }
            if (split.length == 1) {
                return new Locale(split[0]);
            }
        } else if (str.contains("_")) {
            String[] split2 = str.split("_");
            if (split2.length > 2) {
                return new Locale(split2[0], split2[1], split2[2]);
            }
            if (split2.length > 1) {
                return new Locale(split2[0], split2[1]);
            }
            if (split2.length == 1) {
                return new Locale(split2[0]);
            }
        } else {
            return new Locale(str);
        }
        throw new IllegalArgumentException("Can not parse language tag: [" + str + "]");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String toLanguageTag(Locale locale) {
        StringBuilder sb = new StringBuilder();
        sb.append(locale.getLanguage());
        String country = locale.getCountry();
        if (country != null && !country.isEmpty()) {
            sb.append(HelpFormatter.DEFAULT_OPT_PREFIX);
            sb.append(locale.getCountry());
        }
        return sb.toString();
    }
}