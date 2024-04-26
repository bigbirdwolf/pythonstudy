package com.facebook.stetho.inspector.helper;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.ViewDebug;

/* loaded from: classes.dex */
public class IntegerFormatter {
    private static IntegerFormatter cachedFormatter;

    public static IntegerFormatter getInstance() {
        if (cachedFormatter == null) {
            synchronized (IntegerFormatter.class) {
                if (cachedFormatter == null) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        cachedFormatter = new IntegerFormatterWithHex();
                    } else {
                        cachedFormatter = new IntegerFormatter();
                    }
                }
            }
        }
        return cachedFormatter;
    }

    private IntegerFormatter() {
    }

    public String format(Integer num, @Nullable ViewDebug.ExportedProperty exportedProperty) {
        return String.valueOf(num);
    }

    /* loaded from: classes.dex */
    private static class IntegerFormatterWithHex extends IntegerFormatter {
        private IntegerFormatterWithHex() {
            super();
        }

        @Override // com.facebook.stetho.inspector.helper.IntegerFormatter
        @TargetApi(21)
        public String format(Integer num, @Nullable ViewDebug.ExportedProperty exportedProperty) {
            if (exportedProperty != null && exportedProperty.formatToHexString()) {
                return "0x" + Integer.toHexString(num.intValue());
            }
            return super.format(num, exportedProperty);
        }
    }
}