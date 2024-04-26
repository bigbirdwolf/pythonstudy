package com.umeng.socialize.net.dplus.cache;

import android.content.Context;
import android.os.Handler;

/* loaded from: classes.dex */
public class CacheApi {
    private static String a = "CacheApi";
    private static CacheApi e;
    private Handler b;
    private CacheExector c = new CacheExector(a());
    private Context d;

    public static CacheApi get(Context context) {
        if (e == null) {
            e = new CacheApi(context);
        }
        return e;
    }

    private CacheApi(Context context) {
        this.d = context;
    }

    public boolean save(String str, String str2) {
        if (this.c == null) {
            return false;
        }
        return this.c.save(str, str2);
    }

    public double checkSize(String str) {
        if (this.c == null) {
            return 0.0d;
        }
        return this.c.checkSize(str);
    }

    public IReader read(String str, Class cls) {
        if (this.c == null) {
            return null;
        }
        return this.c.readFile(str, cls);
    }

    public boolean delete(String str) {
        if (this.c == null) {
            return false;
        }
        return this.c.deleteFile(str);
    }

    private String a() {
        if (this.d == null) {
            return null;
        }
        return this.d.getFilesDir().getPath();
    }
}