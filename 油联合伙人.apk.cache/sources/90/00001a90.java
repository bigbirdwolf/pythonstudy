package com.yltx.oil.partner.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.umeng.commonsdk.proguard.ap;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.widget.CommonDialog;
import com.yltx.oil.partner.widget.DialogViewHolder;
import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint({"WrongConstant"})
/* loaded from: classes.dex */
public class CommonUtils {
    public static final String ACTION_REFRESH_ACTIVITY_ICON = "ACTION_REFRESH_ACTIVITY_ICON";
    public static final String DCMI_CAMERA_PATH = "/Camera/";
    private static final String DOT = ".";
    public static final int NO_NETWORK = 0;
    public static final int NO_WIFI = 2;
    public static final String SUFFIX_AUDIO_FILE = "arm";
    public static final String SUFFIX_IMAGE_FILE = "png";
    public static final String SUFFIX_IMAGE_THUMBNAIL = "thumbnail";
    public static final String SUFFIX_TMP_FILE = "tmp";
    public static final String SUFFIX_VIDEO_FILE = "mp4";
    private static final String TAG = "CommonUtils";
    public static final int WIFI = 1;
    private static OnSureClickListener onSureClickListener;
    private static ApplicationEnv mApplicationEnv = new ApplicationEnv("app");
    private static long lastClickTime = 0;

    /* loaded from: classes.dex */
    public enum FileType {
        FILE_TYPE_AUDIO,
        FILE_TYPE_VIDEO,
        FILE_TYPE_IMAGE,
        FILE_TYPE_TMP,
        FILE_TYPE_THUMBNAIL
    }

    /* loaded from: classes.dex */
    public interface OnSureClickListener {
        void onSureClick(View view);
    }

    public static int dip2px(float f, float f2) {
        return (int) ((f2 * f) + 0.5f);
    }

    public static boolean hasPermission() {
        return true;
    }

    public static int px2dip(float f, float f2) {
        return (int) ((f2 / f) + 0.5f);
    }

    public static void setupApplicationEnv(ApplicationEnv applicationEnv) {
        mApplicationEnv = applicationEnv;
    }

    public static ApplicationEnv getApplicationEnv() {
        return mApplicationEnv;
    }

    public static String getMetaDataValue(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            Bundle bundle = applicationInfo != null ? applicationInfo.metaData : null;
            if (bundle != null) {
                return bundle.getString(str);
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static int getNetWorkType(Context context) {
        if (isNetWorkAvailable(context)) {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1).isConnectedOrConnecting() ? 1 : 2;
        }
        return 0;
    }

    public static boolean isNetWorkAvailable(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) ? false : true;
    }

    public static boolean isBackground(Context context) {
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName.equals(context.getPackageName())) {
                if (runningAppProcessInfo.importance == 400) {
                    Log.i("后台", runningAppProcessInfo.processName);
                    return true;
                }
                Log.i("前台", runningAppProcessInfo.processName);
                return false;
            }
        }
        return false;
    }

    public static boolean isTopActivity(Context context, String str) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        ComponentName componentName = activityManager.getRunningTasks(1).size() > 0 ? activityManager.getRunningTasks(1).get(0).topActivity : null;
        if (componentName == null) {
            return false;
        }
        return componentName.getClassName().equals(str);
    }

    public static boolean isSDCardMounted() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static String getDCMIPath() {
        String str = "";
        if (isSDCardMounted()) {
            File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            str = externalStoragePublicDirectory.getAbsolutePath() + DCMI_CAMERA_PATH;
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return str;
    }

    public static void clearCache(Context context) {
        File appCacheDir = getAppCacheDir(context);
        if (appCacheDir != null && appCacheDir.exists() && appCacheDir.isDirectory()) {
            for (File file : appCacheDir.listFiles()) {
                file.delete();
            }
        }
    }

    public static long calcCacheSize(Context context) {
        File appCacheDir = getAppCacheDir(context);
        if (appCacheDir != null) {
            try {
                return new ConcurrentTotalFileSizeWLatch().getTotalSizeOfFile(appCacheDir.getAbsolutePath());
            } catch (InterruptedException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
        return 0L;
    }

    public static File getAppCacheFile(String str, Context context) throws Exception {
        File appCacheDir = getAppCacheDir(context);
        if (appCacheDir == null || !appCacheDir.exists()) {
            return null;
        }
        return new File(appCacheDir, str);
    }

    public static File getAppCacheDir(Context context) {
        File file;
        boolean isSDCardMounted = isSDCardMounted();
        if (isSDCardMounted) {
            file = null;
        } else {
            Log.w(TAG, "SD card did not mounted");
            isSDCardMounted = false;
            file = context.getCacheDir();
        }
        if (isSDCardMounted) {
            File file2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath().concat(File.separator).concat(mApplicationEnv.getAppCacheDir()));
            if (file2.exists() || file2.mkdirs()) {
                return file2;
            }
            return null;
        }
        return file;
    }

    public static File getAppTmpFile(String str) throws Exception {
        if (!isSDCardMounted()) {
            Log.e(TAG, "SD card did not mounted");
            throw new Exception("SD card did not mounted");
        }
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath().concat(File.separator).concat(mApplicationEnv.getAppTmpDir()));
        if (!file.exists()) {
            if (file.mkdirs()) {
                return new File(file, str);
            }
            return null;
        }
        return new File(file, str);
    }

    public static File getAppThumbnailCacheFile(String str) throws Exception {
        if (!isSDCardMounted()) {
            Log.e(TAG, "SD card did not mounted");
            throw new Exception("SD card did not mounted");
        }
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath().concat(File.separator).concat(mApplicationEnv.getAppThumbnailDir()));
        if (!file.exists()) {
            if (file.mkdirs()) {
                return new File(file, str);
            }
            return null;
        }
        return new File(file, str);
    }

    public static File getLogFile(String str) {
        if (!isSDCardMounted()) {
            Log.e(TAG, "SD card did not mounted");
            return null;
        }
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath().concat(File.separator).concat(mApplicationEnv.getAppLogDir()));
        if (!file.exists()) {
            if (file.mkdirs()) {
                return new File(file, str);
            }
            return null;
        }
        return new File(file, str);
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static String generateFileName(FileType fileType) {
        String str = System.currentTimeMillis() + "";
        switch (fileType) {
            case FILE_TYPE_IMAGE:
                return str.concat(DOT).concat(SUFFIX_IMAGE_FILE);
            case FILE_TYPE_AUDIO:
                return str.concat(DOT).concat(SUFFIX_AUDIO_FILE);
            case FILE_TYPE_VIDEO:
                return str.concat(DOT).concat(SUFFIX_VIDEO_FILE);
            case FILE_TYPE_TMP:
                return str.concat(DOT).concat(SUFFIX_TMP_FILE);
            case FILE_TYPE_THUMBNAIL:
                return str.concat(DOT).concat(SUFFIX_IMAGE_THUMBNAIL);
            default:
                return str;
        }
    }

    public static long getSDFreeSize() {
        if (isSDCardMounted()) {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((statFs.getAvailableBlocks() * statFs.getBlockSize()) / 1024) / 1024;
        }
        return -1L;
    }

    public static byte[] intToBytes(int i) {
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < 4; i2++) {
            bArr[i2] = (byte) (i >> (24 - (i2 * 8)));
        }
        return bArr;
    }

    public static byte[] float2byte(float f) {
        int floatToIntBits = Float.floatToIntBits(f);
        byte[] bArr = new byte[4];
        for (int i = 0; i < 4; i++) {
            bArr[i] = (byte) (floatToIntBits >> (24 - (i * 8)));
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        for (int i2 = 0; i2 < length / 2; i2++) {
            byte b = bArr2[i2];
            int i3 = (length - i2) - 1;
            bArr2[i2] = bArr2[i3];
            bArr2[i3] = b;
        }
        return bArr2;
    }

    public static int byteArray2int(byte[] bArr) {
        return (bArr[0] << 24) + (bArr[1] << ap.n) + (bArr[2] << 8) + bArr[3];
    }

    private static String matchUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Matcher matcher = Pattern.compile("[a-zA-z]+://[^\\s]*", 2).matcher(str);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    private static String getMatchUrl(String str, String str2) {
        String matchUrl = matchUrl(str);
        if (matchUrl == null || !matchUrl.contains(str2)) {
            return null;
        }
        return matchUrl;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        Cursor cursor;
        try {
            cursor = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        String string = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
                        if (cursor != null) {
                            cursor.close();
                        }
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
    }

    public static void hideInputKeyboard(Activity activity) {
        View peekDecorView = activity.getWindow().peekDecorView();
        if (peekDecorView != null) {
            ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(peekDecorView.getWindowToken(), 0);
        }
    }

    public static String getAppVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "未知版本";
        }
    }

    public static int getAppVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    public static int getAndroidSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static String getRealFilePath(Context context, Uri uri) {
        String str;
        Cursor query;
        int columnIndex;
        String str2 = null;
        if (uri == null) {
            return null;
        }
        String scheme = uri.getScheme();
        if (scheme == null) {
            return uri.getPath();
        }
        if ("file".equals(scheme)) {
            return uri.getPath();
        }
        if (!"content".equals(scheme) || (query = context.getContentResolver().query(uri, new String[]{"_data"}, str, null, (null))) == null) {
            return null;
        }
        if (query.moveToFirst() && (columnIndex = query.getColumnIndex("_data")) > -1) {
            str2 = query.getString(columnIndex);
        }
        query.close();
        return str2;
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    /* loaded from: classes.dex */
    public static class ApplicationEnv {
        private String appBaseDir;
        private String appCacheDir;
        private String appImageDir;
        private String appLogDir;
        private String appSoundDir;
        private String appThumbnailDir;
        private String appTmpDir;
        private String appVideoDir;

        public ApplicationEnv(String str) {
            this.appBaseDir = str;
            this.appCacheDir = str + File.separator + "cache";
            this.appThumbnailDir = str + File.separator + CommonUtils.SUFFIX_IMAGE_THUMBNAIL;
            this.appTmpDir = str + File.separator + CommonUtils.SUFFIX_TMP_FILE;
            this.appLogDir = str + File.separator + "log";
            this.appSoundDir = str + File.separator + "sound";
            this.appVideoDir = str + File.separator + "video";
            this.appImageDir = str + File.separator + SocializeProtocolConstants.IMAGE;
        }

        public String getAppBaseDir() {
            return this.appBaseDir;
        }

        public String getAppCacheDir() {
            return this.appCacheDir;
        }

        public String getAppThumbnailDir() {
            return this.appThumbnailDir;
        }

        public String getAppTmpDir() {
            return this.appTmpDir;
        }

        public String getAppLogDir() {
            return this.appLogDir;
        }

        public String getAppSoundDir() {
            return this.appSoundDir;
        }

        public String getAppVideoDir() {
            return this.appVideoDir;
        }

        public String getAppImageDir() {
            return this.appImageDir;
        }
    }

    /* loaded from: classes.dex */
    public static class ConcurrentTotalFileSizeWLatch {
        private ExecutorService service;
        private final AtomicLong pendingFileVisits = new AtomicLong();
        private final AtomicLong totalSize = new AtomicLong();
        private final CountDownLatch latch = new CountDownLatch(1);

        /* JADX INFO: Access modifiers changed from: private */
        public void updateTotalSizeOfFilesInDir(File file) {
            long j;
            if (file.isFile()) {
                j = file.length();
            } else {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    long j2 = 0;
                    for (final File file2 : listFiles) {
                        if (file2.isFile()) {
                            j2 += file2.length();
                        } else {
                            this.pendingFileVisits.incrementAndGet();
                            this.service.execute(new Runnable() { // from class: com.yltx.oil.partner.utils.CommonUtils.ConcurrentTotalFileSizeWLatch.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    ConcurrentTotalFileSizeWLatch.this.updateTotalSizeOfFilesInDir(file2);
                                }
                            });
                        }
                    }
                    j = j2;
                } else {
                    j = 0;
                }
            }
            this.totalSize.addAndGet(j);
            if (this.pendingFileVisits.decrementAndGet() == 0) {
                this.latch.countDown();
            }
        }

        public long getTotalSizeOfFile(String str) throws InterruptedException {
            this.service = Executors.newFixedThreadPool(100);
            this.pendingFileVisits.incrementAndGet();
            try {
                updateTotalSizeOfFilesInDir(new File(str));
                this.latch.await(100L, TimeUnit.SECONDS);
                return this.totalSize.longValue();
            } finally {
                this.service.shutdown();
            }
        }
    }

    public static boolean isFastClick(long j) {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        if (timeInMillis - lastClickTime > j) {
            lastClickTime = timeInMillis;
            return false;
        }
        return true;
    }

    public static void setOnSureClickListener(OnSureClickListener onSureClickListener2) {
        onSureClickListener = onSureClickListener2;
    }

    public static void goToPhone(Context context, String str) {
        try {
            Intent intent = new Intent("android.intent.action.DIAL");
            intent.setData(Uri.parse("tel:" + str));
            context.startActivity(intent);
        } catch (Exception e) {
            ToastUtil.showMiddleScreenToast(context, "您的手机没有安装电话软件");
            e.printStackTrace();
        }
    }

    public static CommonDialog showSimpleDialog(Context context, final String str, final String str2) {
        CommonDialog commonDialog = new CommonDialog(context, R.layout.dialog_simple_prompt) { // from class: com.yltx.oil.partner.utils.CommonUtils.1
            @Override // com.yltx.oil.partner.widget.CommonDialog
            public void convert(DialogViewHolder dialogViewHolder) {
                EditText editText = (EditText) dialogViewHolder.getView(R.id.dialog_simple_prompt_edit);
                ((TextView) dialogViewHolder.getView(R.id.dialog_simple_prompt_message)).setText(str);
                ((TextView) dialogViewHolder.getView(R.id.dialog_simple_prompt_title)).setText(str2);
                ((TextView) dialogViewHolder.getView(R.id.dialog_simple_prompt_cancle)).setOnClickListener(new View.OnClickListener() { // from class: com.yltx.oil.partner.utils.CommonUtils.1.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        dismiss();
                    }
                });
                ((TextView) dialogViewHolder.getView(R.id.dialog_simple_prompt_sure)).setOnClickListener(new View.OnClickListener() { // from class: com.yltx.oil.partner.utils.CommonUtils.1.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (CommonUtils.onSureClickListener != null) {
                            CommonUtils.onSureClickListener.onSureClick(view);
                        }
                    }
                });
            }
        };
        commonDialog.showDialog();
        return commonDialog;
    }

    public static CommonDialog showGuizeDialog(Context context, final String str) {
        CommonDialog commonDialog = new CommonDialog(context, R.layout.dialog_guize) { // from class: com.yltx.oil.partner.utils.CommonUtils.2
            @Override // com.yltx.oil.partner.widget.CommonDialog
            public void convert(DialogViewHolder dialogViewHolder) {
                ((TextView) dialogViewHolder.getView(R.id.tv_dialog_guize)).setText(str);
                ((TextView) dialogViewHolder.getView(R.id.tv_dialog_qued)).setOnClickListener(new View.OnClickListener() { // from class: com.yltx.oil.partner.utils.CommonUtils.2.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (CommonUtils.onSureClickListener != null) {
                            CommonUtils.onSureClickListener.onSureClick(view);
                        }
                    }
                });
            }
        };
        commonDialog.showDialog();
        return commonDialog;
    }

    public static CommonDialog showPhoneDialog(Context context, final String str) {
        CommonDialog commonDialog = new CommonDialog(context, R.layout.dialog_phone) { // from class: com.yltx.oil.partner.utils.CommonUtils.3
            @Override // com.yltx.oil.partner.widget.CommonDialog
            public void convert(DialogViewHolder dialogViewHolder) {
                ((TextView) dialogViewHolder.getView(R.id.tv_dialog_phone)).setText(str);
                ((TextView) dialogViewHolder.getView(R.id.tv_dialog_quxiao)).setOnClickListener(new View.OnClickListener() { // from class: com.yltx.oil.partner.utils.CommonUtils.3.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        dismiss();
                    }
                });
                ((TextView) dialogViewHolder.getView(R.id.tv_dialog_qued)).setOnClickListener(new View.OnClickListener() { // from class: com.yltx.oil.partner.utils.CommonUtils.3.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (CommonUtils.onSureClickListener != null) {
                            CommonUtils.onSureClickListener.onSureClick(view);
                        }
                    }
                });
            }
        };
        commonDialog.showDialog();
        return commonDialog;
    }

    public static CommonDialog showYaoqDialog(Context context) {
        CommonDialog commonDialog = new CommonDialog(context, R.layout.dialog_ypyaoq) { // from class: com.yltx.oil.partner.utils.CommonUtils.4
            @Override // com.yltx.oil.partner.widget.CommonDialog
            public void convert(DialogViewHolder dialogViewHolder) {
                ((ImageView) dialogViewHolder.getView(R.id.iv_fx_close)).setOnClickListener(new View.OnClickListener() { // from class: com.yltx.oil.partner.utils.CommonUtils.4.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        dismiss();
                    }
                });
                ((LinearLayout) dialogViewHolder.getView(R.id.ll_wxhy)).setOnClickListener(new View.OnClickListener() { // from class: com.yltx.oil.partner.utils.CommonUtils.4.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (CommonUtils.onSureClickListener != null) {
                            CommonUtils.onSureClickListener.onSureClick(view);
                        }
                        dismiss();
                    }
                });
                ((LinearLayout) dialogViewHolder.getView(R.id.ll_wxhyq)).setOnClickListener(new View.OnClickListener() { // from class: com.yltx.oil.partner.utils.CommonUtils.4.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (CommonUtils.onSureClickListener != null) {
                            CommonUtils.onSureClickListener.onSureClick(view);
                        }
                        dismiss();
                    }
                });
                ((LinearLayout) dialogViewHolder.getView(R.id.ll_wxhmdm)).setOnClickListener(new View.OnClickListener() { // from class: com.yltx.oil.partner.utils.CommonUtils.4.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (CommonUtils.onSureClickListener != null) {
                            CommonUtils.onSureClickListener.onSureClick(view);
                        }
                        dismiss();
                    }
                });
                ((LinearLayout) dialogViewHolder.getView(R.id.ll_wxhlj)).setOnClickListener(new View.OnClickListener() { // from class: com.yltx.oil.partner.utils.CommonUtils.4.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (CommonUtils.onSureClickListener != null) {
                            CommonUtils.onSureClickListener.onSureClick(view);
                        }
                        dismiss();
                    }
                });
            }
        };
        commonDialog.showPyqDialog();
        return commonDialog;
    }

    public static CommonDialog showYaoqGzDialog(Context context) {
        CommonDialog commonDialog = new CommonDialog(context, R.layout.dialog_ypgz) { // from class: com.yltx.oil.partner.utils.CommonUtils.5
            @Override // com.yltx.oil.partner.widget.CommonDialog
            public void convert(DialogViewHolder dialogViewHolder) {
                ((ImageView) dialogViewHolder.getView(R.id.im_ypgb)).setOnClickListener(new View.OnClickListener() { // from class: com.yltx.oil.partner.utils.CommonUtils.5.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        dismiss();
                    }
                });
            }
        };
        commonDialog.showDialog();
        return commonDialog;
    }

    public static CommonDialog showYqCodeDialog(Context context, final Bitmap bitmap) {
        CommonDialog commonDialog = new CommonDialog(context, R.layout.dialog_mdmcode) { // from class: com.yltx.oil.partner.utils.CommonUtils.6
            @Override // com.yltx.oil.partner.widget.CommonDialog
            public void convert(DialogViewHolder dialogViewHolder) {
                ((Button) dialogViewHolder.getView(R.id.btn_qx)).setOnClickListener(new View.OnClickListener() { // from class: com.yltx.oil.partner.utils.CommonUtils.6.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        dismiss();
                    }
                });
                ((ImageView) dialogViewHolder.getView(R.id.iv_fx_scan)).setImageBitmap(bitmap);
                ((TextView) dialogViewHolder.getView(R.id.tv_sfcode)).setOnClickListener(new View.OnClickListener() { // from class: com.yltx.oil.partner.utils.CommonUtils.6.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (CommonUtils.onSureClickListener != null) {
                            CommonUtils.onSureClickListener.onSureClick(view);
                        }
                    }
                });
            }
        };
        commonDialog.showDialog();
        return commonDialog;
    }

    public static CommonDialog showPartnerDialog(Context context) {
        CommonDialog commonDialog = new CommonDialog(context, R.layout.dialog_hhr) { // from class: com.yltx.oil.partner.utils.CommonUtils.7
            @Override // com.yltx.oil.partner.widget.CommonDialog
            public void convert(DialogViewHolder dialogViewHolder) {
                ((TextView) dialogViewHolder.getView(R.id.tv_dialog_quxiao)).setOnClickListener(new View.OnClickListener() { // from class: com.yltx.oil.partner.utils.CommonUtils.7.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        dismiss();
                    }
                });
                ((TextView) dialogViewHolder.getView(R.id.tv_dialog_qued)).setOnClickListener(new View.OnClickListener() { // from class: com.yltx.oil.partner.utils.CommonUtils.7.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (CommonUtils.onSureClickListener != null) {
                            CommonUtils.onSureClickListener.onSureClick(view);
                        }
                    }
                });
            }
        };
        commonDialog.showDialog();
        return commonDialog;
    }

    public static void toPhotos(Activity activity, int i) {
        Intent intent = new Intent("android.intent.action.PICK", (Uri) null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(intent, i);
    }
}