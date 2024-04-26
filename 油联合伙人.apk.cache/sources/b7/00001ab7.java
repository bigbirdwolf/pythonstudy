package com.yltx.oil.partner.utils;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* loaded from: classes.dex */
public final class IOUtils {
    private IOUtils() {
    }

    public static String readAllFromAssets(Context context, String str) throws IOException {
        InputStream inputStream;
        AssetManager assets = context.getApplicationContext().getResources().getAssets();
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            inputStream = assets.open(str);
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine != null) {
                            sb.append(readLine);
                            sb.append("\n");
                        } else {
                            closeQuietly(bufferedReader2);
                            closeQuietly(inputStream);
                            return sb.toString();
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        closeQuietly(bufferedReader);
                        closeQuietly(inputStream);
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }
}