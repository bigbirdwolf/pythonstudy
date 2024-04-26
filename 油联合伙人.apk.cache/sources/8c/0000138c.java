package com.umeng.commonsdk.statistics.common;

import android.support.v4.view.InputDeviceCompat;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes.dex */
public class HelperUtils {
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final String TAG = "helper";

    public static String subStr(String str, int i) {
        String str2 = "";
        try {
            if (!TextUtils.isEmpty(str)) {
                String substring = str.substring(0, str.length() < i ? str.length() : i);
                try {
                    int length = substring.getBytes("UTF-8").length;
                    String str3 = substring;
                    int i2 = i;
                    while (length > i) {
                        i2--;
                        try {
                            String substring2 = str.substring(0, i2 > str.length() ? str.length() : i2);
                            str3 = substring2;
                            length = substring2.getBytes("UTF-8").length;
                        } catch (Exception e) {
                            e = e;
                            str2 = str3;
                            MLog.e(e);
                            return str2;
                        }
                    }
                    return str3;
                } catch (Exception e2) {
                    e = e2;
                    str2 = substring;
                }
            }
        } catch (Exception e3) {
            e = e3;
        }
        return str2;
    }

    public static boolean checkStrLen(String str, int i) {
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            return str.length() <= i;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String MD5(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(bytes);
            byte[] digest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                stringBuffer.append(String.format("%02X", Byte.valueOf(digest[i])));
            }
            return stringBuffer.toString();
        } catch (Exception unused) {
            return str.replaceAll("[^[a-z][A-Z][0-9][.][_]]", "");
        }
    }

    public static String getUmengMD5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                stringBuffer.append(Integer.toHexString(b & 255));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            MLog.i(TAG, "getMD5 error", e);
            return "";
        }
    }

    public static String getMD5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                stringBuffer.append(Integer.toHexString((b & 255) | InputDeviceCompat.SOURCE_ANY).substring(6));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            MLog.i(TAG, "getMD5 error", e);
            return "";
        }
    }

    public static String getFileMD5(File file) {
        byte[] bArr = new byte[1024];
        try {
            if (!file.isFile()) {
                return "";
            }
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int read = fileInputStream.read(bArr, 0, 1024);
                if (read != -1) {
                    messageDigest.update(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    return String.format("%1$032x", new BigInteger(1, messageDigest.digest()));
                }
            }
        } catch (Exception unused) {
            return null;
        }
    }

    public static String readStreamToString(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        char[] cArr = new char[1024];
        StringWriter stringWriter = new StringWriter();
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (-1 != read) {
                stringWriter.write(cArr, 0, read);
            } else {
                return stringWriter.toString();
            }
        }
    }

    public static byte[] readStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 != read) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public static void writeFile(File file, byte[] bArr) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            fileOutputStream.write(bArr);
            fileOutputStream.flush();
        } finally {
            safeClose(fileOutputStream);
        }
    }

    public static void writeFile(File file, String str) throws IOException {
        writeFile(file, str.getBytes());
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public static java.lang.String readFile(java.io.File r3) {
        /*
            r0 = 0
            boolean r1 = r3.exists()     // Catch: java.lang.Throwable -> L2a
            if (r1 != 0) goto Lb
            safeClose(r0)
            return r0
        Lb:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L2a
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L2a
            int r3 = r1.available()     // Catch: java.lang.Throwable -> L2b
            byte[] r3 = new byte[r3]     // Catch: java.lang.Throwable -> L2b
            r1.read(r3)     // Catch: java.lang.Throwable -> L2b
            java.lang.String r2 = new java.lang.String     // Catch: java.lang.Throwable -> L2b
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L2b
            safeClose(r1)
            return r2
        L22:
            r3 = move-exception
            goto L26
        L24:
            r3 = move-exception
            r1 = r0
        L26:
            safeClose(r1)
            throw r3
        L2a:
            r1 = r0
        L2b:
            safeClose(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.common.HelperUtils.readFile(java.io.File):java.lang.String");
    }

    public static void safeClose(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    public static void safeClose(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Exception unused) {
            }
        }
    }
}