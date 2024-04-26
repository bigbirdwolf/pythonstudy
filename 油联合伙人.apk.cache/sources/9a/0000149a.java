package com.umeng.socialize.net.dplus.cache;

import android.util.Log;
import com.umeng.socialize.utils.SLog;
import com.umeng.socialize.utils.UmengText;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class AtomicFile {
    private final File a;
    private final File b;

    public AtomicFile(File file) {
        this.a = file;
        this.b = new File(file.getPath() + ".bak");
    }

    public File getBaseFile() {
        return this.a;
    }

    public void delete() {
        this.a.delete();
        this.b.delete();
    }

    public FileOutputStream startWrite(boolean z) throws IOException {
        if (this.a.exists()) {
            if (!this.b.exists()) {
                if (!this.a.renameTo(this.b)) {
                    Log.w("AtomicFile", "Couldn't rename file " + this.a + " to backup file " + this.b);
                } else {
                    a(this.b, this.a);
                }
            } else {
                this.a.delete();
            }
        }
        try {
            return new FileOutputStream(this.a, z);
        } catch (FileNotFoundException e) {
            if (!this.a.getParentFile().mkdirs()) {
                SLog.error(UmengText.CACHE.CACHEFILE, e);
            }
            try {
                return new FileOutputStream(this.a, z);
            } catch (FileNotFoundException unused) {
                SLog.error(UmengText.CACHE.CACHEFILE, e);
                return null;
            }
        }
    }

    private static void a(File file, File file2) throws IOException {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
            fileInputStream = null;
        }
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                    Log.d("AtomicFile", read + "");
                } else {
                    fileInputStream.close();
                    fileOutputStream.close();
                    Log.d("AtomicFile", "comsum time:" + (System.currentTimeMillis() - currentTimeMillis));
                    return;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    public void finishWrite(FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            a(fileOutputStream);
            try {
                fileOutputStream.close();
                this.b.delete();
            } catch (IOException e) {
                SLog.error(UmengText.CACHE.CACHEFILE, e);
            }
        }
    }

    public void failWrite(FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            a(fileOutputStream);
            try {
                fileOutputStream.close();
                this.a.delete();
                this.b.renameTo(this.a);
            } catch (IOException e) {
                SLog.error(UmengText.CACHE.CACHEFILE, e);
            }
        }
    }

    public FileInputStream openRead() throws FileNotFoundException {
        if (this.b.exists()) {
            this.a.delete();
            this.b.renameTo(this.a);
        }
        return new FileInputStream(this.a);
    }

    public byte[] readFully() throws IOException {
        FileInputStream openRead = openRead();
        try {
            byte[] bArr = new byte[openRead.available()];
            int i = 0;
            while (true) {
                int read = openRead.read(bArr, i, bArr.length - i);
                if (read <= 0) {
                    return bArr;
                }
                i += read;
                int available = openRead.available();
                if (available > bArr.length - i) {
                    byte[] bArr2 = new byte[available + i];
                    System.arraycopy(bArr, 0, bArr2, 0, i);
                    bArr = bArr2;
                }
            }
        } finally {
            openRead.close();
        }
    }

    static boolean a(FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.getFD().sync();
                return true;
            } catch (IOException e) {
                SLog.error(UmengText.CACHE.CACHEFILE, e);
                return false;
            }
        }
        return true;
    }
}