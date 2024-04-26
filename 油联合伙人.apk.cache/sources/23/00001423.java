package com.umeng.socialize.b.b;

import android.os.Environment;
import android.text.TextUtils;
import com.umeng.socialize.utils.ContextUtil;
import com.umeng.socialize.utils.DefaultClass;
import com.umeng.socialize.utils.SLog;
import com.umeng.socialize.utils.SocializeUtils;
import com.umeng.socialize.utils.UmengText;
import java.io.File;
import java.io.IOException;

/* compiled from: FileUtil.java */
/* loaded from: classes.dex */
public class b {
    private static b b = new b();
    private String a;

    private b() {
        this.a = "";
        try {
            this.a = ContextUtil.getContext().getCacheDir().getCanonicalPath();
        } catch (IOException e) {
            SLog.error(e);
        }
    }

    public static b a() {
        if (b == null) {
            return new b();
        }
        return b;
    }

    public File b() throws IOException {
        File file = new File(c(), d());
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return file;
    }

    public File c() throws IOException {
        String string;
        if (Environment.getExternalStorageDirectory() != null && !TextUtils.isEmpty(Environment.getExternalStorageDirectory().getCanonicalPath())) {
            string = Environment.getExternalStorageDirectory().getCanonicalPath();
        } else if (!TextUtils.isEmpty(this.a)) {
            string = this.a;
            SLog.E(UmengText.CACHE.SD_NOT_FOUNT);
        } else {
            string = DefaultClass.getString();
            SLog.E(UmengText.CACHE.SD_NOT_FOUNT);
        }
        File file = new File(string + c.f);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public byte[] a(java.io.File r6) {
        /*
            r5 = this;
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L41
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L41
            java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L39
            r6.<init>()     // Catch: java.lang.Throwable -> L39
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L32
        Lf:
            int r2 = r1.read(r0)     // Catch: java.lang.Throwable -> L32
            r3 = -1
            if (r2 == r3) goto L1b
            r3 = 0
            r6.write(r0, r3, r2)     // Catch: java.lang.Throwable -> L32
            goto Lf
        L1b:
            byte[] r0 = r6.toByteArray()     // Catch: java.lang.Throwable -> L32
            r1.close()     // Catch: java.io.IOException -> L26
            r6.close()     // Catch: java.io.IOException -> L26
            goto L2c
        L26:
            r6 = move-exception
            java.lang.String r1 = com.umeng.socialize.utils.UmengText.IMAGE.CLOSE
            com.umeng.socialize.utils.SLog.error(r1, r6)
        L2c:
            return r0
        L2d:
            r0 = move-exception
            r4 = r0
            r0 = r6
            r6 = r4
            goto L64
        L32:
            r0 = move-exception
            r4 = r1
            r1 = r6
            r6 = r0
            goto L3c
        L37:
            r6 = move-exception
            goto L64
        L39:
            r6 = move-exception
            r4 = r1
            r1 = r0
        L3c:
            r0 = r4
            goto L43
        L3e:
            r6 = move-exception
            r1 = r0
            goto L64
        L41:
            r6 = move-exception
            r1 = r0
        L43:
            java.lang.String r2 = com.umeng.socialize.utils.UmengText.IMAGE.READ_IMAGE_ERROR     // Catch: java.lang.Throwable -> L60
            com.umeng.socialize.utils.SLog.error(r2, r6)     // Catch: java.lang.Throwable -> L60
            byte[] r6 = com.umeng.socialize.utils.DefaultClass.getBytes()     // Catch: java.lang.Throwable -> L60
            if (r0 == 0) goto L54
            r0.close()     // Catch: java.io.IOException -> L52
            goto L54
        L52:
            r0 = move-exception
            goto L5a
        L54:
            if (r1 == 0) goto L5f
            r1.close()     // Catch: java.io.IOException -> L52
            goto L5f
        L5a:
            java.lang.String r1 = com.umeng.socialize.utils.UmengText.IMAGE.CLOSE
            com.umeng.socialize.utils.SLog.error(r1, r0)
        L5f:
            return r6
        L60:
            r6 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L64:
            if (r1 == 0) goto L6c
            r1.close()     // Catch: java.io.IOException -> L6a
            goto L6c
        L6a:
            r0 = move-exception
            goto L72
        L6c:
            if (r0 == 0) goto L77
            r0.close()     // Catch: java.io.IOException -> L6a
            goto L77
        L72:
            java.lang.String r1 = com.umeng.socialize.utils.UmengText.IMAGE.CLOSE
            com.umeng.socialize.utils.SLog.error(r1, r0)
        L77:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.b.b.b.a(java.io.File):byte[]");
    }

    public String d() {
        return SocializeUtils.hexdigest(String.valueOf(System.currentTimeMillis()));
    }
}