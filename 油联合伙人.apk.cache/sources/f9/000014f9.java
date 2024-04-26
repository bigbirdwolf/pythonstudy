package com.umeng.socialize.utils;

import android.content.Context;
import com.umeng.socialize.net.dplus.db.DBConfig;
import com.umeng.socialize.utils.UmengText;
import java.io.File;

/* loaded from: classes.dex */
public class ContextUtil {
    private static Context context;

    public static Context getContext() {
        if (context == null) {
            SLog.E(UmengText.INTER.CONTEXT_ERROR);
        }
        return context;
    }

    public static File getDataFile(String str) {
        if (context != null) {
            return context.getDatabasePath(DBConfig.DB_NAME);
        }
        return null;
    }

    public static void setContext(Context context2) {
        context = context2;
    }

    public static final String getPackageName() {
        return context == null ? "" : context.getPackageName();
    }

    public static final int getIcon() {
        if (context == null) {
            return 0;
        }
        return context.getApplicationInfo().icon;
    }
}