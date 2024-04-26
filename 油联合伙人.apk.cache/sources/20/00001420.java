package com.umeng.socialize.b.b;

import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.umeng.socialize.utils.SLog;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/* compiled from: CacheUtil.java */
/* loaded from: classes.dex */
public class a {
    public static void a() {
        if ((Environment.getExternalStorageDirectory() == null || TextUtils.isEmpty(Environment.getExternalStorageDirectory().getPath())) ? false : true) {
            c.d = Environment.getExternalStorageDirectory().getPath() + File.separator + c.e + File.separator;
        } else {
            c.d = Environment.getDataDirectory().getPath() + File.separator + c.e + File.separator;
        }
        File file = new File(c.d);
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            a(c.d);
        } catch (Exception e) {
            SLog.error(e);
        }
    }

    private static void a(String str) {
        File[] listFiles = new File(str).listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return;
        }
        int i = 0;
        for (File file : listFiles) {
            i = (int) (i + file.length());
        }
        if (i > 0 || 40 > c()) {
            Arrays.sort(listFiles, new C0033a());
            for (File file2 : listFiles) {
                file2.delete();
            }
        }
    }

    private static int c() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        double availableBlocks = statFs.getAvailableBlocks();
        double blockSize = statFs.getBlockSize();
        Double.isNaN(availableBlocks);
        Double.isNaN(blockSize);
        return (int) ((availableBlocks * blockSize) / 1048576.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CacheUtil.java */
    /* renamed from: com.umeng.socialize.b.b.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0033a implements Comparator<File> {
        private C0033a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(File file, File file2) {
            if (file.lastModified() > file2.lastModified()) {
                return 1;
            }
            return file.lastModified() == file2.lastModified() ? 0 : -1;
        }
    }

    public static void b() {
        a();
    }
}