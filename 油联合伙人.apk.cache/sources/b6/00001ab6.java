package com.yltx.oil.partner.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class FileUtil {
    public static String createTmpDir(Context context) {
        String str = Environment.getExternalStorageDirectory().toString() + "/baiduTTS";
        if (!makeDir(str)) {
            str = context.getExternalFilesDir("baiduTTS").getAbsolutePath();
            if (!makeDir("baiduTTS")) {
                throw new RuntimeException("create model resources dir failed :" + str);
            }
        }
        return str;
    }

    public static boolean fileCanRead(String str) {
        return new File(str).canRead();
    }

    public static boolean makeDir(String str) {
        File file = new File(str);
        if (file.exists()) {
            return true;
        }
        return file.mkdirs();
    }

    public static void copyFromAssets(AssetManager assetManager, String str, String str2, boolean z) throws IOException {
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        File file = new File(str2);
        if (z || !(z || file.exists())) {
            try {
                inputStream = assetManager.open(str);
                try {
                    fileOutputStream = new FileOutputStream(str2);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = null;
                }
            } catch (Throwable th2) {
                th = th2;
                inputStream = null;
                fileOutputStream = null;
            }
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr, 0, 1024);
                    if (read < 0) {
                        try {
                            break;
                        } finally {
                            if (inputStream != null) {
                                inputStream.close();
                            }
                        }
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.close();
            } catch (Throwable th3) {
                th = th3;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    } finally {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    }
                }
                throw th;
            }
        }
    }
}