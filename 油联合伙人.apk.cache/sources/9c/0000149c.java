package com.umeng.socialize.net.dplus.cache;

import android.text.TextUtils;
import android.util.Log;
import com.umeng.socialize.utils.SLog;
import com.umeng.socialize.utils.UmengText;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

/* loaded from: classes.dex */
public class CacheExector {
    private static final String a = "CacheExector";
    private final int b = 32;
    private final int c = 5120;
    private final int d = 8;
    private String e;

    public CacheExector(String str) {
        this.e = null;
        this.e = str;
    }

    public double checkSize(String str) {
        File a2 = a();
        double d = 0.0d;
        if (a2 == null || !a2.isDirectory()) {
            return 0.0d;
        }
        File[] listFiles = a2.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i] != null && listFiles[i].getName().contains(str)) {
                d += a(listFiles[i].length());
            }
        }
        return d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.umeng.socialize.net.dplus.cache.CacheExector] */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v13, types: [java.io.Writer, java.io.OutputStreamWriter] */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9, types: [java.io.Closeable] */
    public boolean save(String str, String str2) {
        FileOutputStream fileOutputStream;
        String str3;
        File b = b(a(), str2);
        boolean z = false;
        if (b == null) {
            return false;
        }
        AtomicFile atomicFile = new AtomicFile(b);
        BufferedWriter bufferedWriter = null;
        try {
            fileOutputStream = atomicFile.startWrite(true);
            str3 = b;
        } catch (IOException e) {
            SLog.error(UmengText.CACHE.CACHEFILE, e);
            String name = b.getName();
            deleteFile(name);
            fileOutputStream = null;
            str3 = name;
        }
        try {
            if (fileOutputStream == null) {
                return false;
            }
            try {
            } catch (IOException e2) {
                e = e2;
                str3 = 0;
            } catch (Throwable th) {
                th = th;
                str3 = 0;
            }
            if (!TextUtils.isEmpty(str)) {
                str3 = new OutputStreamWriter(fileOutputStream);
                try {
                    BufferedWriter bufferedWriter2 = new BufferedWriter(str3);
                    try {
                        bufferedWriter2.write(str);
                        bufferedWriter2.newLine();
                        bufferedWriter2.flush();
                        atomicFile.finishWrite(fileOutputStream);
                        bufferedWriter = bufferedWriter2;
                        z = true;
                        str3 = str3;
                    } catch (IOException e3) {
                        e = e3;
                        bufferedWriter = bufferedWriter2;
                        atomicFile.failWrite(fileOutputStream);
                        SLog.error(UmengText.CACHE.CACHEFILE, e);
                        str3 = str3;
                        a(bufferedWriter);
                        a(str3);
                        a(fileOutputStream);
                        return z;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedWriter = bufferedWriter2;
                        a(bufferedWriter);
                        a(str3);
                        a(fileOutputStream);
                        throw th;
                    }
                } catch (IOException e4) {
                    e = e4;
                }
                a(bufferedWriter);
                a(str3);
                a(fileOutputStream);
                return z;
            }
            str3 = 0;
            a(bufferedWriter);
            a(str3);
            a(fileOutputStream);
            return z;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private File a() {
        if (TextUtils.isEmpty(this.e)) {
            Log.d(a, "Couldn't create directory mDirPath is null");
            return null;
        }
        File file = new File(this.e);
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        String str = a;
        Log.d(str, "Couldn't create directory" + this.e);
        return null;
    }

    private File a(File file, String str) {
        if (file == null || !file.isDirectory()) {
            return null;
        }
        return new File(file, a(str));
    }

    private String a(String str) {
        return String.valueOf(System.currentTimeMillis()) + str;
    }

    private File b(File file, String str) {
        if (file == null || !file.isDirectory()) {
            return null;
        }
        String[] list = file.list();
        if (list == null || list.length <= 0) {
            return a(file, str);
        }
        File c = c(file, str);
        return c == null ? a(file, str) : c;
    }

    private File c(File file, String str) {
        File[] a2 = a(file);
        if (a2 == null || a2.length <= 0 || a2.length <= 0) {
            return null;
        }
        File file2 = a2[0];
        if (a(file2.length()) > 32.0d) {
            return null;
        }
        return file2;
    }

    private File[] a(File file) {
        if (file == null || !file.isDirectory()) {
            return null;
        }
        File[] listFiles = file.listFiles();
        Arrays.sort(listFiles, b());
        return listFiles;
    }

    private Comparator<File> b() {
        return new Comparator<File>() { // from class: com.umeng.socialize.net.dplus.cache.CacheExector.1
            @Override // java.util.Comparator
            public int compare(File file, File file2) {
                return Long.valueOf(file.length()).compareTo(Long.valueOf(file2.length()));
            }
        };
    }

    public boolean deleteFile(String str) {
        File[] listFiles = a().listFiles();
        boolean z = false;
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i] != null && listFiles[i].getName().contains(str)) {
                z = listFiles[i].delete();
            }
        }
        return z;
    }

    private double a(long j) {
        if (j <= 0) {
            return 0.0d;
        }
        double d = j;
        Double.isNaN(d);
        return d / 1024.0d;
    }

    private void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                SLog.error(UmengText.CACHE.CACHEFILE, e);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.Class, java.lang.Class<T extends com.umeng.socialize.net.dplus.cache.IReader>] */
    /* JADX WARN: Type inference failed for: r11v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r11v5, types: [java.io.Closeable, java.io.Reader, java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r9v0, types: [com.umeng.socialize.net.dplus.cache.CacheExector] */
    public <T extends IReader> T readFile(String str, Class<T> cls) {
        ?? r2;
        FileInputStream fileInputStream;
        BufferedReader bufferedReader;
        T t;
        int i;
        File d = d(a(), str);
        if (d == null) {
            return null;
        }
        try {
            fileInputStream = new AtomicFile(d).openRead();
        } catch (IOException e) {
            r2 = UmengText.CACHE.CACHEFILE;
            SLog.error(r2, e);
            deleteFile(d.getName());
            fileInputStream = null;
        }
        try {
            if (fileInputStream == null) {
                return null;
            }
            try {
                t = (T) a(d.getName(), cls);
                cls = (Class<T>) new InputStreamReader(fileInputStream);
            } catch (IOException e2) {
                e = e2;
                cls = 0;
                bufferedReader = null;
            } catch (Throwable th) {
                th = th;
                cls = (Class<T>) null;
                r2 = 0;
            }
            try {
                bufferedReader = new BufferedReader(cls);
                i = 0;
            } catch (IOException e3) {
                e = e3;
                bufferedReader = null;
            } catch (Throwable th2) {
                th = th2;
                r2 = 0;
                a(fileInputStream);
                a(cls);
                a(r2);
                throw th;
            }
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    i++;
                    Log.d(a, "read file:" + i + readLine);
                    if (!TextUtils.isEmpty(readLine)) {
                        try {
                            sb.append(readLine);
                        } catch (Exception e4) {
                            SLog.error(UmengText.CACHE.CACHEFILE, e4);
                        }
                    }
                }
                if (t != null) {
                    t.create(sb.toString());
                }
                a(fileInputStream);
                a(cls);
                a(bufferedReader);
                return t;
            } catch (IOException e5) {
                e = e5;
                SLog.error(UmengText.CACHE.CACHEFILE, e);
                a(fileInputStream);
                a(cls);
                a(bufferedReader);
                return null;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private <T extends IReader> T a(String str, Class<T> cls) {
        try {
            return cls.getConstructor(String.class).newInstance(str);
        } catch (Throwable th) {
            SLog.error(UmengText.CACHE.CACHEFILE, th);
            return null;
        }
    }

    private File d(File file, String str) {
        String[] list;
        if (file == null || !file.isDirectory() || (list = file.list()) == null || list.length <= 0) {
            return null;
        }
        return e(file, str);
    }

    private File e(File file, String str) {
        File[] a2 = a(file);
        if (a2 == null || a2.length <= 0) {
            return null;
        }
        for (File file2 : a2) {
            if (a(file2.length()) <= 40 && file2.getName().endsWith(str)) {
                return file2;
            }
            Log.e(a, "getReadableFileFromFiles:file length don't legal" + file2.length());
            deleteFile(file2.getName());
        }
        return null;
    }
}