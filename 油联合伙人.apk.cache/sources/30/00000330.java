package android.support.v4.content;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import java.io.File;

/* loaded from: classes.dex */
public class ContextCompat {
    private static final String TAG = "ContextCompat";
    private static final Object sLock = new Object();
    private static TypedValue sTempValue;

    public static boolean startActivities(@NonNull Context context, @NonNull Intent[] intentArr) {
        return startActivities(context, intentArr, null);
    }

    public static boolean startActivities(@NonNull Context context, @NonNull Intent[] intentArr, @Nullable Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            context.startActivities(intentArr, bundle);
            return true;
        }
        context.startActivities(intentArr);
        return true;
    }

    public static void startActivity(@NonNull Context context, @NonNull Intent intent, @Nullable Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            context.startActivity(intent, bundle);
        } else {
            context.startActivity(intent);
        }
    }

    @Nullable
    public static File getDataDir(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return context.getDataDir();
        }
        String str = context.getApplicationInfo().dataDir;
        if (str != null) {
            return new File(str);
        }
        return null;
    }

    @NonNull
    public static File[] getObbDirs(@NonNull Context context) {
        return Build.VERSION.SDK_INT >= 19 ? context.getObbDirs() : new File[]{context.getObbDir()};
    }

    @NonNull
    public static File[] getExternalFilesDirs(@NonNull Context context, @Nullable String str) {
        return Build.VERSION.SDK_INT >= 19 ? context.getExternalFilesDirs(str) : new File[]{context.getExternalFilesDir(str)};
    }

    @NonNull
    public static File[] getExternalCacheDirs(@NonNull Context context) {
        return Build.VERSION.SDK_INT >= 19 ? context.getExternalCacheDirs() : new File[]{context.getExternalCacheDir()};
    }

    private static File buildPath(File file, String... strArr) {
        for (String str : strArr) {
            if (file == null) {
                file = new File(str);
            } else if (str != null) {
                file = new File(file, str);
            }
        }
        return file;
    }

    @Nullable
    public static final Drawable getDrawable(@NonNull Context context, @DrawableRes int i) {
        int i2;
        if (Build.VERSION.SDK_INT >= 21) {
            return context.getDrawable(i);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return context.getResources().getDrawable(i);
        }
        synchronized (sLock) {
            if (sTempValue == null) {
                sTempValue = new TypedValue();
            }
            context.getResources().getValue(i, sTempValue, true);
            i2 = sTempValue.resourceId;
        }
        return context.getResources().getDrawable(i2);
    }

    @Nullable
    public static final ColorStateList getColorStateList(@NonNull Context context, @ColorRes int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(i);
        }
        return context.getResources().getColorStateList(i);
    }

    @ColorInt
    public static final int getColor(@NonNull Context context, @ColorRes int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColor(i);
        }
        return context.getResources().getColor(i);
    }

    public static int checkSelfPermission(@NonNull Context context, @NonNull String str) {
        if (str == null) {
            throw new IllegalArgumentException("permission is null");
        }
        return context.checkPermission(str, Process.myPid(), Process.myUid());
    }

    @Nullable
    public static final File getNoBackupFilesDir(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            return context.getNoBackupFilesDir();
        }
        return createFilesDir(new File(context.getApplicationInfo().dataDir, "no_backup"));
    }

    public static File getCodeCacheDir(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            return context.getCodeCacheDir();
        }
        return createFilesDir(new File(context.getApplicationInfo().dataDir, "code_cache"));
    }

    private static synchronized File createFilesDir(File file) {
        synchronized (ContextCompat.class) {
            if (file.exists() || file.mkdirs()) {
                return file;
            }
            if (file.exists()) {
                return file;
            }
            Log.w(TAG, "Unable to create files subdir " + file.getPath());
            return null;
        }
    }

    @Nullable
    public static Context createDeviceProtectedStorageContext(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return context.createDeviceProtectedStorageContext();
        }
        return null;
    }

    public static boolean isDeviceProtectedStorage(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return context.isDeviceProtectedStorage();
        }
        return false;
    }

    public static void startForegroundService(@NonNull Context context, @NonNull Intent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }
}