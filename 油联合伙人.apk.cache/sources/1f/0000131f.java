package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.text.TextUtils;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/* compiled from: SDStorage.java */
/* loaded from: classes.dex */
public class i {
    private static final String a = "/.um/.umm.dat";
    private static final String b = "/.uxx/.cca.dat";
    private static final String c = "/.cc/.adfwe.dat";
    private static final String d = "/.a.dat";
    private static final String e = "umdat";

    public static void a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String g = g(context);
        if (TextUtils.isEmpty(g) || !str.equals(g)) {
            b(context, str);
        }
    }

    public static String a(Context context) {
        String b2 = b(context);
        if (b2 == null || b2.equals("")) {
            b2 = g(context);
        }
        if (b2 == null || b2.equals("")) {
            b2 = c(context);
        }
        if (b2 == null || b2.equals("")) {
            b2 = d(context);
        }
        if (b2 == null || b2.equals("")) {
            b2 = e(context);
        }
        return (b2 == null || b2.equals("")) ? f(context) : b2;
    }

    public static String b(Context context) {
        return h(context);
    }

    public static String c(Context context) {
        return c(context, a);
    }

    public static String d(Context context) {
        return c(context, b);
    }

    public static String e(Context context) {
        return c(context, c);
    }

    public static String f(Context context) {
        return c(context, d);
    }

    public static String g(Context context) {
        return i(context);
    }

    private static String h(Context context) {
        return com.umeng.commonsdk.framework.a.a(context, com.umeng.commonsdk.proguard.e.e, (String) null);
    }

    public static void b(Context context, String str) {
        a(context, str, a);
        a(context, str, b);
        a(context, str, c);
        a(context, str, d);
        d(context, str);
    }

    private static void a(Context context, String str, String str2) {
        if (DeviceConfig.checkPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            try {
                String externalStorageState = Environment.getExternalStorageState();
                if (externalStorageState == null || !externalStorageState.equalsIgnoreCase("mounted")) {
                    return;
                }
                String c2 = c(context, str2);
                if (c2 == null || !c2.equals(str)) {
                    File file = new File(Environment.getExternalStorageDirectory() + str2);
                    if (file.getParentFile() != null && !file.getParentFile().exists()) {
                        file.getParentFile().mkdir();
                    }
                    RandomAccessFile randomAccessFile = new RandomAccessFile(Environment.getExternalStorageDirectory() + str2, "rw");
                    randomAccessFile.setLength((long) str.getBytes().length);
                    FileChannel channel = randomAccessFile.getChannel();
                    FileLock tryLock = channel.tryLock();
                    ByteBuffer allocate = ByteBuffer.allocate(1024);
                    allocate.clear();
                    allocate.put(str.getBytes());
                    allocate.flip();
                    while (allocate.hasRemaining()) {
                        channel.write(allocate);
                    }
                    channel.force(true);
                    if (tryLock != null) {
                        tryLock.release();
                    }
                    channel.close();
                }
            } catch (Exception e2) {
                ULog.e("saveFileUmtt:" + e2.getMessage());
            }
        }
    }

    private static String c(Context context, String str) {
        String externalStorageState;
        try {
            if (DeviceConfig.checkPermission(context, "android.permission.READ_EXTERNAL_STORAGE") && (externalStorageState = Environment.getExternalStorageState()) != null && externalStorageState.equalsIgnoreCase("mounted")) {
                if (new File(Environment.getExternalStorageDirectory() + str).exists()) {
                    FileChannel channel = new RandomAccessFile(Environment.getExternalStorageDirectory() + str, "rw").getChannel();
                    FileLock tryLock = channel.tryLock();
                    StringBuilder sb = new StringBuilder();
                    ByteBuffer allocate = ByteBuffer.allocate(1024);
                    allocate.clear();
                    while (channel.read(allocate) != -1) {
                        byte[] bArr = new byte[allocate.position()];
                        for (int i = 0; i < allocate.position(); i++) {
                            bArr[i] = allocate.get(i);
                        }
                        sb.append(new String(bArr));
                        allocate.clear();
                    }
                    if (channel != null) {
                        tryLock.release();
                    }
                    channel.close();
                    return sb.toString();
                }
                return null;
            }
            return null;
        } catch (Exception e2) {
            ULog.e("getFileUmtt:" + e2.getMessage());
            return null;
        }
    }

    private static void d(Context context, String str) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(e, 0);
        if (sharedPreferences != null) {
            String string = sharedPreferences.getString("u", null);
            if (string == null || !string.equals(str)) {
                sharedPreferences.edit().putString("u", str).commit();
            }
        }
    }

    private static String i(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(e, 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getString("u", null);
        }
        return null;
    }
}