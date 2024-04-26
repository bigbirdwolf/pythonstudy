package com.yltx.oil.partner.base;

import android.app.Activity;
import android.os.Build;
import android.util.Log;

/* loaded from: classes.dex */
public class ScreenManager {
    private static ScreenManager instance;

    private ScreenManager() {
    }

    public static synchronized ScreenManager getInstance() {
        ScreenManager screenManager;
        synchronized (ScreenManager.class) {
            if (instance == null) {
                instance = new ScreenManager();
            }
            screenManager = instance;
        }
        return screenManager;
    }

    public void setFullScreen(boolean z, Activity activity) {
        if (z) {
            activity.getWindow().setFlags(1024, 1024);
            activity.requestWindowFeature(1);
        }
    }

    public void setStatusBar(boolean z, Activity activity) {
        if (z) {
            Log.d(">>设置状态栏", z + "");
            if (Build.VERSION.SDK_INT >= 19) {
                activity.getWindow().addFlags(67108864);
                activity.getWindow().addFlags(134217728);
            }
        }
    }

    public void setScreenRoate(boolean z, Activity activity) {
        if (!z) {
            activity.setRequestedOrientation(0);
        } else {
            activity.setRequestedOrientation(1);
        }
    }
}